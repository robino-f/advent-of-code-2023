import java.io.File
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.exp



val data = File("input.txt").readLines().toMutableList()

class Coordinate(val x: Int, val y: Int) {

    override fun equals(other: Any?): Boolean {
        if (other is Coordinate) {
            return other.x == x && other.y == y
        }
        return false
    }

    override fun hashCode(): Int {
        return 31 * x + y
    }

    override fun toString(): String {
        return "(x: $x, y: $y)"
    }
}

class Universe(private val tiles: MutableList<String>) {
    val emptyLines = mutableListOf<Int>()
    val emptyColumns = mutableListOf<Int>()
    val galaxies = computeGalaxies()

    private fun computeEmptySpaces() {
        for (y in 0..<tiles.size) {
            if (!tiles[y].contains('#')) emptyLines.add(y)
        }

        for (x in 0..<tiles[0].length) {
            var hasGalaxy = false
            for (y in 0..<tiles.size) {
                if (tiles[y][x] == '#') {
                    hasGalaxy = true
                    break
                }
            }
            if (!hasGalaxy) {
                emptyColumns.add(x)
            }
        }

    }

    private fun computeGalaxies(): List<Coordinate> {
        val galaxiesFound = mutableListOf<Coordinate>()
        for (x in 0..<tiles[0].length) {
            for (y in 0..<tiles.size) {
                if (tiles[y][x] == '#') galaxiesFound.add(Coordinate(x, y))
            }
        }

        computeEmptySpaces()

        return galaxiesFound
    }

    fun getDistances(expansionFactor: Double = 1.0): Double {
        val pairs = mutableListOf<List<Coordinate>>()
        for (i in galaxies.indices) {
            for (j in i + 1..<galaxies.size) {
                pairs.add(mutableListOf(galaxies[i], galaxies[j]))
            }
        }

        var distances = 0.0
        for (pair in pairs) {
            val distanceX = emptyColumns.filter { (it > pair[0].x && it < pair[1].x) || (it > pair[1].x && it < pair[0].x) }.size
            val distanceY = emptyLines.filter { (it > pair[0].y && it < pair[1].y) || (it > pair[1].y && it < pair[0].y) }.size
            val distance = abs(pair[0].x - pair[1].x) + abs(pair[0].y - pair[1].y) + (distanceX + distanceY) * expansionFactor
            distances += distance
        }
        return distances
    }

    override fun toString(): String {
        var printResult = ""

        for (line in tiles) {
            printResult += line + "\n"
        }

        return printResult
    }
}

fun main() {
    val universe = Universe(data)

    println("distances (part-1): %.0f".format(universe.getDistances())) // 9769724
    println("distances (part-2): %.0f".format(universe.getDistances(999999.0))) // 603020563700
}

main()