import java.io.File

val data = File("input.txt").readLines()

class Coordinate(val x: Int, val y: Int) {

    override fun equals(other: Any?): Boolean {
        if (other is Coordinate) {
            return other.x == x && other.y == y
        }
        return false
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }


    override fun toString(): String {
        return "x: $x, y: $y"
    }
}

class LongWalk(private val trails: List<MutableList<Char>>) {
    private fun getNextPositions(position: Coordinate): List<Coordinate> {
        val nextPositions = mutableSetOf<Coordinate>()

        when (trails[position.y][position.x]) {
            '.' -> {
                // East
                if (position.x > 0 && trails[position.y][position.x - 1] != '#') {
                    nextPositions.add(Coordinate(position.x - 1, position.y))
                }
                // West
                if (position.x < trails[0].size - 1 && trails[position.y][position.x + 1] != '#') {
                    nextPositions.add(Coordinate(position.x + 1, position.y))
                }
                // North
                if (position.y > 0 && trails[position.y - 1][position.x] != '#') {
                    nextPositions.add(Coordinate(position.x, position.y - 1))
                }
                // South
                if (position.y < trails.size - 1 && trails[position.y + 1][position.x] != '#') {
                    nextPositions.add(Coordinate(position.x, position.y + 1))
                }
            }

            '<' -> nextPositions.add(Coordinate(position.x - 1, position.y))
            '>' -> nextPositions.add(Coordinate(position.x + 1, position.y))
            '^' -> nextPositions.add(Coordinate(position.x, position.y - 1))
            'v' -> nextPositions.add(Coordinate(position.x, position.y + 1))
        }

        return nextPositions.toList();
    }

    fun getLongestHike(currentPosition: Coordinate = Coordinate(1, 0),
                       visitedPositions: MutableMap<String, Boolean> = mutableMapOf(Coordinate(1, 0).toString() to true),
                       stopPosition: Coordinate = Coordinate(trails[0].size - 2, trails.size - 1)): Int {
        if (currentPosition == stopPosition) {
            return visitedPositions.size - 1
        }
        val nextPositions = getNextPositions(currentPosition)

        return nextPositions.maxOf {
            val key = it.toString()
            if (visitedPositions[key] == true) 0
            else {
                val positions = visitedPositions.toMutableMap()
                positions[key] = true
                getLongestHike(it, positions)
            }
        }
    }

    fun removeSlipperyTrails() {
        for (y in trails.indices) {
            for (x in trails[0].indices) {
                val trailValue = trails[y][x]
                when (trailValue) {
                    '<' -> trails[y][x] = '.'
                    '>' -> trails[y][x] = '.'
                    '^' -> trails[y][x] = '.'
                    'v' -> trails[y][x] = '.'
                }
            }
        }
    }

    override fun toString(): String {
        return trails.joinToString("\n")
    }
}

fun main() {
    val input = data.map { it.toMutableList() }
    val longWalk = LongWalk(input)

    println("longest hike (part-1): " + longWalk.getLongestHike()) // 2406
    longWalk.removeSlipperyTrails()
    // TODO remove corridors
    println("longest hike (part-2): " + longWalk.getLongestHike()) // 6630
}

main()