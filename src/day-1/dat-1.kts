class Trebuchet(dataset: List<String>) {
    val dataset = dataset

    fun computeCalibrationValues(): List<Int> {
        var calibrationValues = mutableListOf<Int>()

        for (value in dataset) {
            var digits = value
                    .replace(Regex("one"), "o1e")
                    .replace(Regex("two"), "t2o")
                    .replace(Regex("three"), "t3e")
                    .replace(Regex("four"), "f4r")
                    .replace(Regex("five"), "f5e")
                    .replace(Regex("six"), "s6x")
                    .replace(Regex("seven"), "s7n")
                    .replace(Regex("eight"), "e8t")
                    .replace(Regex("nine"), "n9e")
                    .filter { it.isDigit() }
            var calibrationValue = (digits.first() + "" + digits.last()).toInt()
            calibrationValues.add(calibrationValue)
        }

        return calibrationValues
    }
}

fun main() {
    val text = Trebuchet(listOf(
            "two1nine",
            "eightwothree",
            "abcone2threexyz",
            "xtwone3four",
            "4nineeightseven2",
            "zoneight234",
            "7pqrstsixteen",
    ))
    val calibrationValues = text.computeCalibrationValues()
    println(calibrationValues)
    println(calibrationValues.sum()) // 142
}

main()