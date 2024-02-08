import java.io.File

val data = File("input.txt").readLines()[0]
class HASHAlgorithm() {
    fun getSequenceValue(sequence: String): Double {
        var totalValue = 0.0

        for (value in sequence.split(",")) {
            var currentValue = 0.0
            for (c in value) {
                currentValue = ((currentValue + c.code) * 17) % 256
            }
            totalValue += currentValue
        }

        return totalValue
    }

    fun getBoxesPower(sequences: List<String>): Double {
        val boxes = mutableMapOf<String, Int>()

        for (sequence in sequences) {
            if (sequence.last() == '-') {
                boxes.remove(sequence.substring(0, sequence.length - 1))
            } else {
                val splitedSequence = sequence.split('=')
                boxes[splitedSequence[0]] = splitedSequence[1].toInt()
            }
        }


        val groupedBoxes = boxes.entries.groupBy { getSequenceValue(it.key) }
        var power = 0.0

        for (box in groupedBoxes) {
            for (i in 0..<box.value.size) {
                power += (box.key + 1) * (i + 1) * box.value[i].value
            }
        }

        return power
    }
}

fun main() {
    val hashAlgorithm = HASHAlgorithm()

    println("hash sum (part-1): %.0f".format(hashAlgorithm.getSequenceValue(data))) // 519603
    println("hash sum (part-2): %.0f".format(hashAlgorithm.getBoxesPower(data.split(',')))) // 244342
}

main()