import java.io.File

val data = File("input.txt").readLines().toMutableList()

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

class StepCounter(private val garden: List<List<Char>>) {
    private val startingPosition = getStartingPosition()

    private fun getStartingPosition(): Coordinate {
        for (x in 0..<garden[0].size) {
            for (y in 0..<garden.size) {
                if (garden[y][x] == 'S') {
                    return Coordinate(x, y)
                }
            }
        }
        return Coordinate(0, 0)
    }

    private fun getNextPositions(positions: List<Coordinate>): List<Coordinate> {
        val nextPositions = mutableSetOf<Coordinate>()

        for (position in positions) {
            if (position.x > 0 && garden[position.y][position.x - 1] != '#') {
                nextPositions.add(Coordinate(position.x - 1, position.y))
            }
            if (position.x < garden[0].size - 1 && garden[position.y][position.x + 1] != '#') {
                nextPositions.add(Coordinate(position.x + 1, position.y))
            }
            if (position.y > 0 && garden[position.y - 1][position.x] != '#') {
                nextPositions.add(Coordinate(position.x, position.y - 1))
            }
            if (position.y < garden.size - 1 && garden[position.y + 1][position.x] != '#') {
                nextPositions.add(Coordinate(position.x, position.y + 1))
            }
        }
        return nextPositions.toList();
    }

    fun countSteps(maxSteps: Int): Int {
        var positions = getNextPositions(listOf(startingPosition))
        for (i in 0..<maxSteps - 1) {
            positions = getNextPositions(positions)
        }
        return positions.size
    }

    override fun toString(): String {
        return garden.map { it.joinToString("") }.joinToString("\n")
    }
}

fun getLagrangeValues(lagrangeInput: List<Double>): List<Double> {
    return listOf(
            (lagrangeInput[0] / 2) - lagrangeInput[1] + (lagrangeInput[2] / 2),
            -3 * (lagrangeInput[0] / 2) + (2 * lagrangeInput[1]) - (lagrangeInput[2] / 2),
            lagrangeInput[0]
    )
};

fun main() {
    val garden = mutableListOf<List<Char>>()
    for (line in data) {
        garden.add(line.toList())
    }
    val stepCounter = StepCounter(garden)

    println("steps (part-1): " + stepCounter.countSteps(64)) // 3572

    val garden2 = mutableListOf<List<Char>>()
    for (j in 0..1) {
        for (line in data) {
            val input = mutableListOf<Char>()
            for (i in 0..4) {
                input.addAll(line.replace('S', '.').toList())
            }
            garden2.add(input)
        }
    }
    for (line in data) {
        val input = mutableListOf<Char>()
        for (i in 0..4) {
            if (i == 2) {
                input.addAll(line.toList())
            } else {
                input.addAll(line.replace('S', '.').toList())
            }
        }
        garden2.add(input)
    }
    for (j in 0..1) {
        for (line in data) {
            val input = mutableListOf<Char>()
            for (i in 0..4) {
                input.addAll(line.replace('S', '.').toList())
            }
            garden2.add(input)
        }
    }

    // https://en.wikipedia.org/wiki/Lagrange_polynomial
    val stepCounter2 = StepCounter(garden2)
    val lagrangeInput = listOf(stepCounter2.countSteps(65).toDouble(), stepCounter2.countSteps(65 + 131).toDouble(), stepCounter2.countSteps(65 + 131 * 2).toDouble())
    val lagrangeValues = getLagrangeValues(lagrangeInput)
    val target = (26_501_365.0 - 65) / 131;
    println("steps (part-2): %.0f".format(lagrangeValues[0] * target * target + lagrangeValues[1] * target + lagrangeValues[2])) // 594606492802848
}

main()