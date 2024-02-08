import java.io.File
import kotlin.math.abs
import kotlin.math.ceil

val data = File("input.txt").readLines().toMutableList()

fun shoelaceArea(v: List<Coordinate>): Double {
    val n = v.size
    var a = 0.0
    for (i in 0 until n - 1) {
        a += v[i].x * v[i + 1].y - v[i + 1].x * v[i].y
    }
    return abs(a + v[n - 1].x * v[0].y - v[0].x * v[n - 1].y) / 2.0
}

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

class PipesLoop(private val tiles: MutableList<String>) {
    private val startingPosition = setStartingPosition()
    val visitedPositions = mutableListOf(startingPosition)

    private fun getConnectedTiles(coordinate: Coordinate): MutableList<Coordinate> {
        val currentTile = tiles[coordinate.x][coordinate.y]
        val connectedTiles = mutableListOf<Coordinate>()

        // right
        if (coordinate.y + 1 < tiles[coordinate.x].length) {
            val tile = tiles[coordinate.x][coordinate.y + 1]
            if ((tile == '-' || tile == 'J' || tile == '7') && (currentTile == '-' || currentTile == 'L' || currentTile == 'F')) {
                connectedTiles.add(Coordinate(coordinate.x, coordinate.y + 1))
            }
        }

        // left
        if (coordinate.y - 1 >= 0) {
            val tile = tiles[coordinate.x][coordinate.y - 1]
            if ((tile == 'L' || tile == 'F' || tile == '-') && (currentTile == '-' || currentTile == '7' || currentTile == 'J')) {
                connectedTiles.add(Coordinate(coordinate.x, coordinate.y - 1))
            }
        }

        // top
        if (coordinate.x - 1 >= 0) {
            val tile = tiles[coordinate.x - 1][coordinate.y]
            if ((tile == '|' || tile == 'F' || tile == '7') && (currentTile == '|' || currentTile == 'J' || currentTile == 'L')) {
                connectedTiles.add(Coordinate(coordinate.x - 1, coordinate.y))
            }
        }

        // bottom
        if (coordinate.x + 1 < tiles.size) {
            val tile = tiles[coordinate.x + 1][coordinate.y]
            if ((tile == '|' || tile == 'L' || tile == 'J') && (currentTile == '|' || currentTile == '7' || currentTile == 'F')) {
                connectedTiles.add(Coordinate(coordinate.x + 1, coordinate.y))
            }
        }

        return connectedTiles
    }

    private fun setStartingPosition(): Coordinate {
        for (x in 0..<tiles.size) {
            for (y in 0..<tiles[x].length) {
                val tile = tiles[x][y]

                if (tile == 'S') {
                    var startingTile = '|'
                    val connectedTiles = getConnectedTiles(Coordinate(x, y))

                    // right
                    if (connectedTiles.indexOf(Coordinate(x, y + 1)) != -1) {
                        if (connectedTiles.indexOf(Coordinate(x, y - 1)) != -1) {
                            startingTile = '-' // left-right
                        }
                        if (connectedTiles.indexOf(Coordinate(x - 1, y)) != -1) {
                            startingTile = 'L' // top-right
                        }
                        if (connectedTiles.indexOf(Coordinate(x + 1, y)) != -1) {
                            startingTile = 'F' // bottom-right
                        }
                    }

                    // left
                    if (connectedTiles.indexOf(Coordinate(x, y - 1)) != -1) {
                        if (connectedTiles.indexOf(Coordinate(x - 1, y)) != -1) {
                            startingTile = 'J' // left-top
                        }
                        if (connectedTiles.indexOf(Coordinate(x + 1, y)) != -1) {
                            startingTile = '7' // left-bottom
                        }
                    }

                    tiles[x] = tiles[x].replace('S', startingTile)
                    return Coordinate(x, y)
                }
            }
        }
        return Coordinate(0, 0)
    }

    fun findLoopFarthestStep(): Int {
        var tilesToVisit = getConnectedTiles(startingPosition).filter { visitedPositions.indexOf(it) == -1 }

        while (visitedPositions.size < 3 || tilesToVisit.isNotEmpty()) {
            visitedPositions.add(tilesToVisit[0])
            tilesToVisit = getConnectedTiles(tilesToVisit[0]).filter { visitedPositions.indexOf(it) == -1 }
        }

        return ceil(visitedPositions.size / 2.0).toInt()

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
    val pipesLoop = PipesLoop(data)
    val steps = pipesLoop.findLoopFarthestStep()
    println("steps (part-1): $steps") // 6951
    println("nest size (part-2): "+ (shoelaceArea(pipesLoop.visitedPositions)-steps+1)) // 563
}

main()