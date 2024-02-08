import java.io.File
import java.util.*

val data = File("input.txt").readLines()

enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

class Coordinate(val x: Int, val y: Int) {

    override fun equals(other: Any?): Boolean {
        if (other is Coordinate) {
            return other.x == x && other.y == y
        }
        return false
    }

    override fun toString(): String {
        return "x: $x, y: $y"
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }
}

class ClumsyCrucible(private val tiles: List<String>) {
    private val lastTile = Coordinate(tiles[0].length - 1, tiles.size - 1)
    private var minHeatLossHeuristic = getMinHeatLossHeuristic()
    private val maxTilesCrossedHeuristic = getManhattanDistance(Coordinate(0, 0)) * 1.3
    private val cache = mutableMapOf<String, Int>()

    private fun getManhattanDistance(coordinate: Coordinate): Int {
        return (tiles.size - coordinate.y) + (tiles[0].length - coordinate.x)
    }

    private fun getMinHeatLossHeuristic(): Int {
        var heatLoss = 0
        for (x in 0..<tiles[0].length) {
            heatLoss += tiles[0][x].toString().toInt()
        }
        for (y in 1..<tiles.size) {
            heatLoss += tiles[y][tiles[0].length - 1].toString().toInt()
        }

        return heatLoss + 100
    }

    private fun getDirectionToExclude(lastDirections: MutableList<Direction>): Direction? {
        if (lastDirections.size < 3) return null
        val directionsSet = lastDirections.toSet()
        if (directionsSet.size == 1) return directionsSet.first()

        return null
    }

    private fun getNextPositions(currentPosition: Coordinate, excludedDirection: Direction?): List<Pair<Coordinate, Direction>> {
        val nextPositions = mutableListOf<Pair<Coordinate, Direction>>()

        if (currentPosition.x < tiles[0].length - 1 && excludedDirection != Direction.EAST) {
            nextPositions.add(Coordinate(currentPosition.x + 1, currentPosition.y) to Direction.EAST)
        }
        if (currentPosition.y < tiles.size - 1 && excludedDirection != Direction.SOUTH) {
            nextPositions.add(Coordinate(currentPosition.x, currentPosition.y + 1) to Direction.SOUTH)
        }
        if (currentPosition.x > 0 && excludedDirection != Direction.WEST) {
            nextPositions.add(Coordinate(currentPosition.x - 1, currentPosition.y) to Direction.WEST)
        }
        if (currentPosition.y > 0 && excludedDirection != Direction.NORTH) {
            nextPositions.add(Coordinate(currentPosition.x, currentPosition.y - 1) to Direction.NORTH)
        }

        return nextPositions
    }

    fun calculateShortestPathFromSource(
            currentDirections: MutableList<Direction> = mutableListOf(Direction.EAST),
            currentPositions: MutableList<Coordinate> = mutableListOf(Coordinate(0, 0)),
            currentHeatLoss: Int = 0,
            tilesCrossed: Int = 0
    ): Int {
        if (currentPositions.size > 2) {
            val key = currentPositions[currentPositions.size - 2].toString() + currentPositions[currentPositions.size - 1].toString() + currentDirections[currentDirections.size - 2].toString() + currentDirections[currentDirections.size - 1].toString()
            val cachedValue = cache[key]
            if (cachedValue != null && currentHeatLoss > cachedValue) return Int.MAX_VALUE
            else cache[key] = currentHeatLoss
        }

        if (currentDirections.size > 3) currentDirections.removeFirst()
        if (currentPositions.size > 5) currentPositions.removeFirst()

        if (currentHeatLoss >= minHeatLossHeuristic) return currentHeatLoss
        if (tilesCrossed > maxTilesCrossedHeuristic) return Int.MAX_VALUE
        if (currentPositions.last() == lastTile) return currentHeatLoss

        val excludedPosition = getDirectionToExclude(currentDirections)
        val nextPaths = getNextPositions(currentPositions.last(), excludedPosition)

        val manhattanDistance: Int? = if (currentPositions.size > 2) getManhattanDistance(currentPositions[currentPositions.size - 3]) else null
        var minimalHeatLoss = Int.MAX_VALUE

        for (path in nextPaths) {
            val nextManhattanDistance = getManhattanDistance(path.first)
            if (manhattanDistance != null && nextManhattanDistance > manhattanDistance) continue
            if (currentPositions.indexOf(path.first) != -1) continue

            val nextLastPositions = currentPositions.toMutableList()
            nextLastPositions.add(path.first)
            val nextLastDirections = currentDirections.toMutableList()
            nextLastDirections.add(path.second)

            val nextTileHeat = tiles[path.first.y][path.first.x].toString().toInt()
            val nextHeatLoss = calculateShortestPathFromSource(nextLastDirections, nextLastPositions, currentHeatLoss + nextTileHeat, tilesCrossed + 1)
            if (nextHeatLoss < minimalHeatLoss) {
                minimalHeatLoss = nextHeatLoss
                if (minHeatLossHeuristic > minimalHeatLoss) minHeatLossHeuristic = minimalHeatLoss
            }
        }

        return minimalHeatLoss
    }

    private fun getDirectionsToExcludePart2(lastDirections: MutableList<Direction>): List<Direction> {
        val excludedDirections = mutableListOf<Direction>()
        if (lastDirections.size == 10) {
            val directionsSet = lastDirections.toSet()
            if (directionsSet.size == 1) excludedDirections.add(directionsSet.first())
        }
        if (lastDirections.size > 3) {
            val directionsSet = lastDirections.subList(lastDirections.size - 4, lastDirections.size).toSet()
            if (directionsSet.size > 1) {
                listOf(Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.NORTH).forEach {
                    if (it != lastDirections.last()) excludedDirections.add(it)
                }
            }

        } else {
            listOf(Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.NORTH).forEach {
                if (it != lastDirections.last()) excludedDirections.add(it)
            }
        }
        return excludedDirections
    }

    private fun getNextPositionsPart2(currentPosition: Coordinate, excludedDirections: List<Direction>): List<Pair<Coordinate, Direction>> {
        val nextPositions = mutableListOf<Pair<Coordinate, Direction>>()

        if (currentPosition.x < tiles[0].length - 1 && excludedDirections.indexOf(Direction.EAST) == -1) {
            nextPositions.add(Coordinate(currentPosition.x + 1, currentPosition.y) to Direction.EAST)
        }
        if (currentPosition.y < tiles.size - 1 && excludedDirections.indexOf(Direction.SOUTH) == -1) {
            nextPositions.add(Coordinate(currentPosition.x, currentPosition.y + 1) to Direction.SOUTH)
        }
        if (currentPosition.x > 0 && excludedDirections.indexOf(Direction.WEST) == -1) {
            nextPositions.add(Coordinate(currentPosition.x - 1, currentPosition.y) to Direction.WEST)
        }
        if (currentPosition.y > 0 && excludedDirections.indexOf(Direction.NORTH) == -1) {
            nextPositions.add(Coordinate(currentPosition.x, currentPosition.y - 1) to Direction.NORTH)
        }

        return nextPositions
    }

    fun calculateShortestPathFromSourcePart2(
            currentDirections: MutableList<Direction> = mutableListOf(Direction.EAST),
            currentPositions: MutableList<Coordinate> = mutableListOf(Coordinate(0, 0)),
            currentHeatLoss: Int = 0,
            tilesCrossed: Int = 0
    ): Int {
        if (currentDirections.size > 10) currentDirections.removeFirst()
        if (currentPositions.size > 5) currentPositions.removeFirst()

        if (currentHeatLoss >= minHeatLossHeuristic) return currentHeatLoss
        if (tilesCrossed > maxTilesCrossedHeuristic) return Int.MAX_VALUE
        if (currentPositions.last() == lastTile) return currentHeatLoss

        val excludedDirections = getDirectionsToExcludePart2(currentDirections)
        val nextPaths = getNextPositionsPart2(currentPositions.last(), excludedDirections)

        val manhattanDistance: Int? = if (currentPositions.size > 2) getManhattanDistance(currentPositions[currentPositions.size - 3]) else null
        var minimalHeatLoss = Int.MAX_VALUE

        for (path in nextPaths) {
            val nextManhattanDistance = getManhattanDistance(path.first)
            if (manhattanDistance != null && nextManhattanDistance > manhattanDistance) continue
            if (currentPositions.indexOf(path.first) != -1) continue

            val nextLastPositions = currentPositions.toMutableList()
            nextLastPositions.add(path.first)
            val nextLastDirections = currentDirections.toMutableList()
            nextLastDirections.add(path.second)

            val nextTileHeat = tiles[path.first.y][path.first.x].toString().toInt()
            val nextHeatLoss = calculateShortestPathFromSourcePart2(nextLastDirections, nextLastPositions, currentHeatLoss + nextTileHeat, tilesCrossed + 1)
            if (nextHeatLoss < minimalHeatLoss) {
                minimalHeatLoss = nextHeatLoss
                if (minHeatLossHeuristic > minimalHeatLoss) minHeatLossHeuristic = minimalHeatLoss
            }
        }

        return minimalHeatLoss
    }

    override fun toString(): String {
        return tiles.joinToString("\n")
    }
}

fun main() {
    val clumsyCrucible = ClumsyCrucible(data)
    val minimalHeatLoss = clumsyCrucible.calculateShortestPathFromSource()
    val minimalHeatLossPart2 = clumsyCrucible.calculateShortestPathFromSourcePart2()

    println("minimal heat loss tiles (part-1): $minimalHeatLoss") // 855
    println("minimal heat loss tiles (part-2): $minimalHeatLossPart2") // 980
}

main()