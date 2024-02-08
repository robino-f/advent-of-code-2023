import java.io.File
import kotlin.math.floor

val data = File("input.txt").readLines().toMutableList()

class Platform(private var loadedPlatform: MutableList<String>) {

    private fun getNumberOfEmptySpaces(startX: Int, startY: Int): Double {
        var emptySpaces = 0.0
        var y = startY - 1
        var canFall = true;
        while (canFall) {
            if (y < 0 || loadedPlatform[y][startX] == '#') canFall = false
            else if (loadedPlatform[y][startX] == '.') {
                emptySpaces += 1
            }

            y -= 1
        }
        return emptySpaces
    }

    fun applyGravity(): Double {
        var totalLoad = 0.0

        val nextPlatform = mutableListOf<String>()
        for (y in loadedPlatform.indices) {
            nextPlatform.add(".".repeat(loadedPlatform[0].length))
        }

        for (x in 0..<loadedPlatform[0].length) {
            for (y in loadedPlatform.indices) {
                if (loadedPlatform[y][x] == 'O') {
                    val load = loadedPlatform.size - y + getNumberOfEmptySpaces(x, y)
                    totalLoad += load

                    val loadIndex = loadedPlatform.size - load.toInt()
                    nextPlatform[loadIndex] = nextPlatform[loadIndex].replaceRange(x, x + 1, "O")
                } else if (loadedPlatform[y][x] == '#') {
                    nextPlatform[y] = nextPlatform[y].replaceRange(x, x + 1, "#")
                }
            }
        }

        loadedPlatform = nextPlatform

        return totalLoad
    }

    private fun rotatePlatform() {
        val nextPlatform = mutableListOf<String>()

        for (x in 0..<loadedPlatform[0].length) {
            var line = ""
            for (y in loadedPlatform.size - 1 downTo 0) {
                line += loadedPlatform[y][x]
            }
            nextPlatform.add(line)
        }

        loadedPlatform = nextPlatform
    }

    fun applyGravityAfterCycles(): Double {
        val loads = mutableListOf<String>()

        var i = 0
        var cycleFound = false
        while (i < 1000000000) {
            val cycleLoads = mutableListOf<Double>()
            for (j in 0..<4) {
                val load = applyGravity()
                cycleLoads.add(load)
                rotatePlatform()
            }

            val indexOfCurrentLoad = loads.indexOf(cycleLoads.toString())


            if (indexOfCurrentLoad > -1 && !cycleFound) {
                val cycleLength = i - indexOfCurrentLoad
                i += (floor((1000000000.0 - i) / cycleLength) * cycleLength).toInt()
                cycleFound = true
            }
            loads.add(cycleLoads.toString())
            i += 1
        }

        var loadAfterCycles = 0.0
        for (x in 0..<loadedPlatform[0].length) {
            for (y in loadedPlatform.indices) {
                if (loadedPlatform[y][x] == 'O') {
                    val load = loadedPlatform.size - y
                    loadAfterCycles += load
                }
            }
        }

        return loadAfterCycles
    }

    override fun toString(): String {
        return loadedPlatform.joinToString("\n")
    }
}

fun main() {
    val platform = Platform(data)

    println("total load (part-1): %.0f".format(platform.applyGravity())) // 113486
    println("total load (part-2): %.0f".format(platform.applyGravityAfterCycles())) // 104409
}

main()