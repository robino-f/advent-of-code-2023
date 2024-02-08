import java.io.File
import kotlin.math.abs


val data = File("input.txt").readLines()

fun shoelaceArea(v: List<Coordinate>): Double {
    val n = v.size
    var a = 0.0
    for (i in 0 until n - 1) {
        a += v[i].x * v[i + 1].y - v[i + 1].x * v[i].y
    }
    return abs(a + v[n - 1].x * v[0].y - v[0].x * v[n - 1].y) / 2.0
}

class Coordinate(val x: Double, val y: Double) {
    override fun toString(): String {
        return "x: $x, y: $y"
    }
}

class LavaductLagoon(private val input: List<String>) {

    fun getLagoonCapacity(instructions: List<String> = input): Double {
        val coordinates = mutableListOf(Coordinate(0.0, 0.0))
        var perimeter = 0L

        for (line in instructions) {
            val lastCoordinate = coordinates.last()
            var x = lastCoordinate.x
            var y = lastCoordinate.y

            val splitLine = line.split(" ")
            val direction = splitLine[0]
            val value = splitLine[1].toInt()
            perimeter += value
            when (direction) {
                "R" -> x += value
                "L" -> x -= value
                "U" -> y -= value
                "D" -> y += value
            }

            coordinates.add(Coordinate(x, y))
        }

        return shoelaceArea(coordinates) + (perimeter / 2.0) + 1.0
    }

    fun getLagoonCapacityPart2(): Double {
        val instructions = mutableListOf<String>()

        for (line in input) {
            val hexadecimalCode = line.split(" ")[2].substring(2, 8)
            var instruction = "R"
            when (hexadecimalCode.last()) {
                '1' -> instruction = "D"
                '2' -> instruction = "L"
                '3' -> instruction = "U"
            }
            instruction += " " + hexadecimalCode.substring(0, 5).toInt(radix = 16)
            instructions.add(instruction)
        }

        return getLagoonCapacity(instructions)
    }

    override fun toString(): String {
        return input.joinToString("\n")
    }
}

fun main() {
    val lavaductLagoon = LavaductLagoon(data)

    println("lagoon capacity (part-1): %.0f".format(lavaductLagoon.getLagoonCapacity())) // 70026
    println("lagoon capacity (part-2): %.0f".format(lavaductLagoon.getLagoonCapacityPart2())) // 68548301037382
}

main()