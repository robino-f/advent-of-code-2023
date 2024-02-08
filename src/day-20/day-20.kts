import java.io.File
import kotlin.math.floor

val data = File("input.txt").readLines().toMutableList()

fun calculateGCD(a: Double, b: Double): Double {
    var num1 = a
    var num2 = b
    while (num2 != 0.0) {
        val temp = num2
        num2 = num1 % num2
        num1 = temp
    }
    return num1
}

enum class ModuleType {
    FlipFlop, Conjuction
}

class Module(val name: String, prefix: Char, val destinations: List<String>) {
    val type = if (prefix == '%') ModuleType.FlipFlop else ModuleType.Conjuction
    var isHigh: Boolean = false
    var cachedPulses = mutableMapOf<String, Boolean>()

    fun sendPulse(pulse: Boolean, name: String): Boolean {
        if (type == ModuleType.Conjuction) {
            cachedPulses[name] = pulse
            return cachedPulses.values.any { !it }
        } else {
            isHigh = !isHigh
            return isHigh
        }
    }

    override fun toString(): String {
        return "$type : $name ($isHigh) -> $destinations"
    }
}

class PulsePropagation(private val broadcasterDestinations: List<String>, private val modules: Map<String, Module>) {
    fun getTotalPulses(): List<Double> {
        var lowPulses = 1.0
        var highPulses = 0.0

        val currentSources = mutableListOf<String>()
        val currentDestinations = broadcasterDestinations.toMutableList()
        val currentPulses = mutableListOf<Boolean>()
        currentDestinations.forEach { _ -> currentPulses.add(false); currentSources.add("broadcaster") }

        while (currentDestinations.isNotEmpty()) {
            val source: String = currentSources.removeFirst()
            val destination: String = currentDestinations.removeFirst()
            val pulse: Boolean = currentPulses.removeFirst()

            if (pulse) highPulses += 1
            else lowPulses += 1

            val currentModule = modules[destination] ?: continue
            if (currentModule.type == ModuleType.FlipFlop && pulse) continue

            val pulseSent = currentModule.sendPulse(pulse, source)
            currentDestinations.addAll(currentModule.destinations)
            currentModule.destinations.forEach { _ -> currentSources.add(currentModule.name); currentPulses.add(pulseSent) }
        }

        return listOf(lowPulses, highPulses)
    }

    fun getRequiredPulsesForRXModule(rxModule: String): Double {
        var requiredPulses = 0.0
        var i = 1.0
        var rxModuleFound = false
        val distances = mutableMapOf<String, Double>()
        val visitedDestinations = mutableMapOf<String, Double>()
        modules.filter { it.value.destinations.indexOf(rxModule) != -1 }.forEach { visitedDestinations[it.key] = 0.0 }

        while (!rxModuleFound) {
            val currentSources = mutableListOf<String>()
            val currentDestinations = broadcasterDestinations.toMutableList()
            val currentPulses = mutableListOf<Boolean>()
            currentDestinations.forEach { _ -> currentPulses.add(false); currentSources.add("broadcaster") }

            while (currentDestinations.isNotEmpty()) {
                val source: String = currentSources.removeFirst()
                val destination: String = currentDestinations.removeFirst()
                val pulse: Boolean = currentPulses.removeFirst()

                val currentModule = modules[destination] ?: continue
                if (currentModule.type == ModuleType.FlipFlop && pulse) continue

                if (currentModule.name == rxModule && pulse) {
                    visitedDestinations[source] = visitedDestinations[source]!! + 1

                    if (distances.keys.indexOf(source) == -1) distances[source] = i
                    if (!visitedDestinations.values.any { it == 0.0 }) {
                        var product = 1.0
                        for (distance in distances.values) {
                            product = (product * distance) / calculateGCD(product, distance)
                        }
                        requiredPulses = product
                        rxModuleFound = true
                    }
                }

                if (rxModuleFound) break

                val pulseSent = currentModule.sendPulse(pulse, source)
                currentDestinations.addAll(currentModule.destinations)
                currentModule.destinations.forEach { _ -> currentSources.add(currentModule.name); currentPulses.add(pulseSent) }
            }

            i += 1
        }


        return requiredPulses
    }

    override fun toString(): String {
        return "broadcaster: $broadcasterDestinations\n" + modules.values.joinToString("\n")
    }
}

fun part1() {
    val modules = mutableMapOf<String, Module>()
    val broadcaster = mutableListOf<String>()
    for (line in data) {
        val splitLine = line.replace("\\s+".toRegex(), "").split("->")
        val first = splitLine[0]
        val second = splitLine[1].split(',')
        if (first == "broadcaster") broadcaster.addAll(second.toMutableList())
        else {
            val name = first.substring(1)
            modules[name] = Module(name, first[0], second)
        }
    }

    for (entry in modules) {
        for (destination in entry.value.destinations) {
            val module = modules[destination]
            if (module != null && module.type == ModuleType.Conjuction) {
                module.cachedPulses[entry.key] = false
            }
        }
    }

    val pulsePropagation = PulsePropagation(broadcaster, modules)

    var lowPulsesTotal = 0.0
    var highPulsesTotal = 0.0
    var i = 0
    val pulsesResults = mutableListOf<List<Double>>()
    val iterations = 1000.0

    while (i < iterations) {
        val totalPulses = pulsePropagation.getTotalPulses()
        lowPulsesTotal += totalPulses[0]
        highPulsesTotal += totalPulses[1]

        pulsesResults.add(totalPulses)
        i += 1
    }

    println("total load (part-1): %.0f".format(lowPulsesTotal * highPulsesTotal)) // 839775244
}

fun part2() {
    val modules = mutableMapOf<String, Module>()
    val broadcaster = mutableListOf<String>()
    var rxModule: String = ""
    for (line in data) {
        val splitLine = line.replace("\\s+".toRegex(), "").split("->")
        val first = splitLine[0]
        val second = splitLine[1].split(',')
        if (first == "broadcaster") broadcaster.addAll(second.toMutableList())
        else {
            val name = first.substring(1)
            modules[name] = Module(name, first[0], second)
            if (modules[name]!!.destinations.indexOf("rx") != -1) {
                rxModule = name
            }
        }
    }

    for (entry in modules) {
        for (destination in entry.value.destinations) {
            val module = modules[destination]
            if (module != null && module.type == ModuleType.Conjuction) {
                module.cachedPulses[entry.key] = false
            }
        }
    }

    val pulsePropagation = PulsePropagation(broadcaster, modules)

    println("total load (part-2): %.0f".format(pulsePropagation.getRequiredPulsesForRXModule(rxModule))) // 207787533680413
}

part1()
part2()