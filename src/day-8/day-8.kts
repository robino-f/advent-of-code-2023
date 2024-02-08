import java.io.File
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min


val data = File("input.txt").readLines().toMutableList()

val instructions = mutableMapOf<String, Instruction>()

class Instruction(directionL: String, directionR: String) {
    val directionL = directionL
    val directionR = directionR

    fun getDirection(direction: Char): String {
        if (direction == 'R') return directionR
        return directionL
    }

    override fun toString(): String {
        return "($directionL, $directionR)"
    }
}

fun lcm(number1: Double, number2: Double): Double {
    if (number1 == 0.0 || number2 == 0.0) {
        return 0.0
    }
    val absNumber1 = abs(number1)
    val absNumber2 = abs(number2)
    val absHigherNumber = max(absNumber1, absNumber2)
    val absLowerNumber = min(absNumber1, absNumber2)
    var lcm = absHigherNumber
    while (lcm % absLowerNumber != 0.0) {
        lcm += absHigherNumber
    }
    return lcm
}


fun extractValues(data: List<String>) {
    data.forEach {
        val values = it.trim().replace("\\s+".toRegex(), " ").split("=").map { e -> e.trim() }
        val directionsLR = values[1].replace("(", "").replace(")", "").replace(" ", "").split(",")
        instructions[values[0]] = Instruction(directionsLR[0], directionsLR[1])
    }
}

fun main() {
    var directions = data[0].trim()
    data.removeFirst()
    data.removeFirst()
    extractValues(data)

    var steps = 0
    var currentPosition = "AAA"
    var currentInstruction = instructions[currentPosition]

    while (currentPosition != "ZZZ") {
        for (direction in directions) {
            if (currentInstruction != null) {
                currentPosition = currentInstruction.getDirection(direction)
                currentInstruction = instructions[currentPosition]
                steps += 1
                if (currentPosition == "ZZZ") break
            }
        }
    }

    println("steps (part-1): $steps") // 11309

    val stepsList = mutableListOf<Int>()
    val startingPositions = instructions.keys.filter { it.endsWith("A") }

    for (position in startingPositions) {
        currentPosition = position
        currentInstruction = instructions[currentPosition]
        steps = 0
        while (!currentPosition.endsWith("Z")) {
            for (direction in directions) {
                steps += 1
                if (currentInstruction != null) {
                    currentPosition = currentInstruction.getDirection(direction)
                    currentInstruction = instructions[currentPosition]
                }

                if (currentPosition.endsWith("Z")) break
            }
        }
        stepsList.add(steps)
    }

    var steps2 = stepsList[0].toDouble()
    for (i in 1..<stepsList.size) {
        steps2 = lcm(steps2, stepsList[i].toDouble())
    }

    println("steps (part-2): %.0f".format(steps2)) // 13740108158591
}

main()