import java.math.BigInteger

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
//    val data = listOf(
//            "Time:      7  15   30",
//            "Distance:  9  40  200"
//    )
    val data = listOf(
            "Time:        47     70     75     66",
            "Distance:   282   1079   1147   1062"
    )

    val times = extractValues(data[0])
    val distances = extractValues(data[1])

    val numberOfWays = times.zip(distances).map { Race(it.first, it.second).winPossibilities() }.reduce { acc, factor -> acc * factor }
    println("min (part-1): $numberOfWays")

    val race = Race(extractValues(data[0], "")[0], extractValues(data[1], "")[0])
    println(race)

    println("min (part-2): " + race.winPossibilities())
}

main()