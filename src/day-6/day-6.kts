import java.io.File
import java.math.BigInteger


val data = File("input.txt").readLines()

class Race(time: BigInteger, record: BigInteger) {
    val time = time
    val record = record

    fun winPossibilities(): Int {
        var possibilities = 0

        var speed = BigInteger.ZERO
        while (speed < time) {
            val timeToRace = time - speed
            val distance = timeToRace * speed

            if (distance > record) possibilities += 1
            speed += BigInteger.ONE
        }

        return possibilities
    }

    override fun toString(): String {
        return "time: $time, record: $record"
    }
}

fun extractValues(data: String, replacement: String = " "): List<BigInteger> {
    return data.split(":")[1].trim().replace("\\s+".toRegex(), replacement).split(" ").map { it.toBigInteger() }
}

fun main() {
    val times = extractValues(data[0])
    val distances = extractValues(data[1])

    val numberOfWays = times.zip(distances).map { Race(it.first, it.second).winPossibilities() }.reduce { acc, factor -> acc * factor }
    println("possibilities (part-1): $numberOfWays") // 281600

    val race = Race(extractValues(data[0], "")[0], extractValues(data[1], "")[0])
    println("possibilities (part-2): " + race.winPossibilities()) // 33875953
}

main()