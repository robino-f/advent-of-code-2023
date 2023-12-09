class Trebuchet(dataset: List<String>) {
    val dataset = dataset

    fun computeCalibrationValues(): List<Int> {
        var calibrationValues = mutableListOf<Int>()

        for (value in dataset) {
            var digits = value.filter { it.isDigit() }
            var calibrationValue = (digits.first() + "" + digits.last()).toInt()
            calibrationValues.add(calibrationValue)
        }

        return calibrationValues
    }
}

fun main() {
    val text = Trebuchet(listOf(
            "1abc2",
            "pqr3stu8vwx",
            "a1b2c3d4e5f",
            "treb7uchet"
    ))
    val calibrationValues = text.computeCalibrationValues()
    println(calibrationValues)
    println(calibrationValues.sum()) // 142
}

main()