import java.io.File
import kotlin.math.max
import kotlin.math.min

val data = File("input.txt").readLines()

class Coordinate(val x: Double, val y: Double, val z: Double) {
    override fun toString(): String {
        return "x: $x, y: $y, z: $z"
    }
}

class Trajectory(private val coordinates: List<Coordinate>, private val velocities: List<Coordinate>) {

//    private fun hasXYIntersection(a: Coordinate, b: Coordinate, c: Coordinate, d: Coordinate): Boolean {
//        return max(a.x, c.x) <= min(b.x, d.x) && max(a.y, c.y) <= min(b.y, d.y)
//    }

    fun getIntersections(minValue: Double, maxValue: Double): Int {
        val futureCoordinates = mutableListOf<List<Coordinate>>()

        var intersections = 0
        for (i in coordinates.indices) {
            for (j in i + 1..<coordinates.size) {
                val coordA = coordinates[i]
                val coordB = coordinates[j]
                val velA = velocities[i]
                val velB = velocities[j]
                val denominator = velA.y * velB.x - velA.x * velB.y
                if (denominator == 0.0) continue

                val diffX = coordA.x - coordB.x
                val diffY = coordA.y - coordB.y

                val factorA = (velB.y * diffX - velB.x * diffY) / denominator
                if (factorA <= 0) continue
                val factorB = (velA.y * diffX - velA.x * diffY) / denominator
                if (factorB <= 0) continue

                var value = coordA.x + factorA * velA.x
                if (!(minValue >= value && value <= maxValue)) continue

                value = coordA.y + factorA * velA.y
                if (!(minValue >= value && value <= maxValue)) continue

                intersections += 1
            }
        }

        return intersections
    }

    override fun toString(): String {
        return coordinates.zip(velocities).joinToString("\n")
    }
}

fun main() {
    val coordinates = mutableListOf<Coordinate>()
    val velocities = mutableListOf<Coordinate>()
    for (line in data) {
        val splitLine = line.replace(" ", "").split('@')

        val lineCoordinates = splitLine[0].split(',').map { it.toDouble() }
        coordinates.add(Coordinate(lineCoordinates[0], lineCoordinates[1], lineCoordinates[2]))

        val lineVelocities = splitLine[1].split(',').map { it.toDouble() }
        velocities.add(Coordinate(lineVelocities[0], lineVelocities[1], lineVelocities[2]))
    }

    val trajectory = Trajectory(coordinates, velocities)
    println("intersections (part-1): " + trajectory.getIntersections(200000000000000.0, 400000000000000.0)) // ?
}

main()