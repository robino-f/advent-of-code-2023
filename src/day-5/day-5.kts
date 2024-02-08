import java.io.File
import java.math.BigInteger
import java.util.WeakHashMap

val data = File("input.txt").readLines()

class AlmanacMap(title: String) {
    val title = title
    val maps = mutableListOf<List<BigInteger>>()

    fun addValue(value: List<BigInteger>) {
        maps.add(value)
    }

    fun mapValue(value: BigInteger): BigInteger {
        for (map in maps) {
            val destination = map[0]
            val source = map[1]
            if (value >= source && value < source + map[2]) {
                val mappedValue = destination + (value - source)
                return mappedValue
            }
        }

        return value
    }

    fun reversedMapValue(value: BigInteger): BigInteger {
        for (map in maps) {
            val destination = map[0]
            val source = map[1]
            if (value >= destination && value < destination + map[2]) {
                val mappedValue = source - destination + value
                return mappedValue
            }
        }
        return value
    }

    override fun toString(): String {
        return "title: $title\nmaps: $maps"
    }
}

fun getSeedsValues(almanacMaps: MutableList<AlmanacMap>, seeds: List<BigInteger>): List<BigInteger> {
    val seedsValues = seeds.map {
        var value = it
        for (almanacMap in almanacMaps) {
            value = almanacMap.mapValue(value)
        }
        value
    }
    return seedsValues
}

fun main() {
    val seeds = data[0].split(":")[1].trim().split(" ").map { it.toBigInteger() }

    val almanacMaps = mutableListOf<AlmanacMap>()

    for (i in 2..<data.size) {
        val line = data[i]
        if (line.isEmpty()) continue

        if (!line[0].isDigit()) {
            almanacMaps.add(AlmanacMap(line.split(" ")[0]))
        } else {
            almanacMaps.last().addValue(line.split(" ").map { it.toBigInteger() })
        }
    }

    println("min (part-1): " + getSeedsValues(almanacMaps, seeds).min()) // 84470622

    fun isSeedKnown(seed: BigInteger): Boolean {
        var i = 0
        while (i < seeds.size) {
            if (i % 2 == 1) {
                i += 1
                continue

            }
            if (seed >= seeds[i] && seed < (seeds[i] + seeds[i + 1])) return true
            i += 1
        }
        return false
    }

    var seedFound = BigInteger.ZERO
    var value = BigInteger.ZERO
    var cpt = BigInteger.ZERO
    var firstValue = BigInteger.ZERO

    while (seedFound == BigInteger.ZERO) {
        for (i in almanacMaps.size - 1 downTo 0) {
            value = almanacMaps[i].reversedMapValue(value)
            if (i == almanacMaps.size - 1) firstValue = value
        }

        if (!isSeedKnown(value)) {
            cpt += BigInteger.ONE
            value = BigInteger.ZERO + cpt
        } else {
            seedFound = firstValue
        }
    }

    println("min (part-2): $seedFound") // 26714516
}

main()