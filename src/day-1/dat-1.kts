import java.io.File

val text = Trebuchet(File("input.txt").readLines())

class Trebuchet(private val dataset: List<String>) {

    fun computeCalibrationValues(part1: Boolean = true): List<Int> {
        var calibrationValues = mutableListOf<Int>()

        for (value in dataset) {
            var digits = if (part1) value else value.replace(Regex("one"), "o1e")
                    .replace(Regex("two"), "t2o")
                    .replace(Regex("three"), "t3e")
                    .replace(Regex("four"), "f4r")
                    .replace(Regex("five"), "f5e")
                    .replace(Regex("six"), "s6x")
                    .replace(Regex("seven"), "s7n")
                    .replace(Regex("eight"), "e8t")
                    .replace(Regex("nine"), "n9e")
            digits = digits.filter { it.isDigit() }
            var calibrationValue = (digits.first() + "" + digits.last()).toInt()
            calibrationValues.add(calibrationValue)
        }

        return calibrationValues
    }
}

fun main() {
    var calibrationValues = text.computeCalibrationValues()
    println(calibrationValues.sum()) // 53194
    calibrationValues = text.computeCalibrationValues(false)
    println(calibrationValues.sum()) // 54249
}

main()