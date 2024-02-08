import java.io.File

val data = File("input.txt").readLines().toMutableList()

class BrokenSprings(private val springs: String, private val groups: MutableList<Int>) {
    private val cache = HashMap<String, Double>()
    fun getPossibleArrangements(currentSprings: String = springs, currentGroups: MutableList<Int> = groups): Double {
        if (cache.containsKey(currentSprings + currentGroups)) {
            return cache[currentSprings + currentGroups]!!
        }

        if (currentGroups.size == 0) {
            if (currentSprings.any { it == '#' }) return 0.0
            return 1.0 // only '.' remaining, it's a match
        }

        // if there are too much chars to fill
        if (currentSprings.length < currentGroups.sum() + currentGroups.size - 1) {
            return 0.0
        }

        if (currentSprings[0] == '#') {
            for (i in 0..<currentGroups[0]) {
                // group is broken if there is a '.'
                if (i < currentSprings.length && currentSprings[i] == '.') return 0.0
            }
            // group is not properly separated if followed by '#'
            if (currentGroups[0] < currentSprings.length && currentSprings[currentGroups[0]] == '#') return 0.0

            val nextSprings: String = if (currentGroups[0] + 1 < currentSprings.length) currentSprings.substring(currentGroups[0] + 1)
            else ""

            val nextGroups = currentGroups.subList(1, currentGroups.size)
            val possibleArrangements = getPossibleArrangements(nextSprings, nextGroups)
            cache[nextSprings + nextGroups] = possibleArrangements
            return possibleArrangements
        }
        // ignore '.'
        else if (currentSprings[0] == '.') {
            return getPossibleArrangements(currentSprings.substring(1), currentGroups)
        }

        val nextSpringsA = "#" + currentSprings.substring(1)
        val nextSpringsB = "." + currentSprings.substring(1)
        val arrangementsA = getPossibleArrangements(nextSpringsA, currentGroups)
        val arrangementsB = getPossibleArrangements(nextSpringsB, currentGroups)
        cache[currentSprings + currentGroups] = arrangementsA + arrangementsB

        return arrangementsA + arrangementsB
    }

    override fun toString(): String {
        return "springs: $springs, groups: $groups"
    }
}

fun main() {
    val brokenSprings = data.map {
        val parts = it.split(" ")
        BrokenSprings(parts[0], parts[1].split(",").map { it.toInt() }.toMutableList())
    }
    println("possible-arrangements (part-1): " + brokenSprings.sumOf { it.getPossibleArrangements() }) // 7407

    val brokenSprings2 = data.map {
        val parts = it.split(" ")
        val groups = listOf(parts[1].split(","), parts[1].split(","), parts[1].split(","), parts[1].split(","), parts[1].split(",")).flatten()
        BrokenSprings(
                listOf(parts[0], parts[0], parts[0], parts[0], parts[0]).joinToString("?"),
                groups.map { it.toInt() }.toMutableList()
        )
    }
    println("possible-arrangements (part-2): %.0f".format(brokenSprings2.sumOf { it.getPossibleArrangements() })) // 30568243604962
}

main()