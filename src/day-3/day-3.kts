import java.io.File

val data = File("input.txt").readLines()

class EngineSchematic(schematicLines: List<String>) {
    val schematicLines = schematicLines

    private fun isLinked(lineNumber: Int, index: Int): Boolean {
        val prevIndex = index - 1
        val nextIndex = index + 1
        if (lineNumber > 0) {
            if (schematicLines[lineNumber - 1][index] != '.' && !schematicLines[lineNumber - 1][index].isDigit()) {
                return true
            }
            if (prevIndex > 0 && schematicLines[lineNumber - 1][prevIndex] != '.' && !schematicLines[lineNumber - 1][prevIndex].isDigit()) {
                return true
            }
            if (nextIndex < schematicLines[lineNumber - 1].length - 1 && schematicLines[lineNumber - 1][nextIndex] != '.' && !schematicLines[lineNumber - 1][nextIndex].isDigit()) {
                return true
            }
        }
        if (lineNumber < schematicLines.size - 1) {
            if (schematicLines[lineNumber + 1][index] != '.' && !schematicLines[lineNumber + 1][index].isDigit()) {
                return true
            }
            if (prevIndex > 0 && schematicLines[lineNumber + 1][prevIndex] != '.' && !schematicLines[lineNumber + 1][prevIndex].isDigit()) {
                return true
            }
            if (nextIndex < schematicLines[lineNumber + 1].length - 1 && schematicLines[lineNumber + 1][nextIndex] != '.' && !schematicLines[lineNumber + 1][nextIndex].isDigit()) {
                return true
            }
        }

        if (index > 0) {
            if (schematicLines[lineNumber][prevIndex] != '.' && !schematicLines[lineNumber][prevIndex].isDigit()) {
                return true
            }
        }
        if (index < schematicLines[lineNumber].length - 1) {
            if (schematicLines[lineNumber][nextIndex] != '.' && !schematicLines[lineNumber][nextIndex].isDigit()) {
                return true
            }
        }
        return false
    }

    fun computePartMembersSum(): Int {
        var numberIndexes = mutableListOf<Int>()
        for (lineIndex in schematicLines.indices) {
            val line = schematicLines[lineIndex]
            var currentNumber = ""
            var isPartMemberOfEngine = false

            for (i in line.indices) {
                if (line[i].isDigit()) {
                    currentNumber += line[i]
                    if (isLinked(lineIndex, i)) isPartMemberOfEngine = isLinked(lineIndex, i)
                    if ((i == line.length - 1 || !line[i + 1].isDigit()) && isPartMemberOfEngine) {
                        numberIndexes.add(currentNumber.toInt())
                        currentNumber = ""
                        isPartMemberOfEngine = false
                    }
                } else {
                    currentNumber = ""
                    isPartMemberOfEngine = false
                }

            }


        }
        return numberIndexes.sum()
    }

    private fun getNumbers(lineNumber: Int, index: Int): List<Int> {
        var numbers = mutableListOf<Int>()
        var number = ""
        if (lineNumber > 0) {
            var startIndex = 0
            for( i in -1 .. 1) {
                if(i >= 0 && schematicLines[lineNumber - 1][index + i - 1].isDigit()) continue
                if(!schematicLines[lineNumber - 1][index + i].isDigit()) continue

                if (index > 0 && schematicLines[lineNumber - 1][index + i].isDigit()) startIndex = index + i

                while (startIndex > 0 && schematicLines[lineNumber - 1][startIndex - 1].isDigit()) {
                    startIndex -= 1
                }
                while (startIndex < schematicLines[lineNumber - 1].length && schematicLines[lineNumber - 1][startIndex].isDigit()) {
                    number += schematicLines[lineNumber - 1][startIndex]
                    startIndex += 1
                }
                if (number.length > 0) {
                    numbers.add(number.toInt())
                    number = ""
                }
            }

        }

        if (lineNumber < schematicLines.size - 1) {
            var startIndex = 0
            for( i in -1 .. 1){
                if(i >= 0 && schematicLines[lineNumber + 1][index + i - 1].isDigit()) continue
                if(!schematicLines[lineNumber + 1][index + i].isDigit()) continue

                if (index > 0 && schematicLines[lineNumber + 1][index + i].isDigit()) startIndex = index + i

                while (startIndex > 0 && schematicLines[lineNumber + 1][startIndex - 1].isDigit()) {
                    startIndex -= 1
                }
                while (startIndex < schematicLines[lineNumber + 1].length && schematicLines[lineNumber + 1][startIndex].isDigit()) {
                    number += schematicLines[lineNumber + 1][startIndex]
                    startIndex += 1
                }
                if (number.length > 0) {
                    numbers.add(number.toInt())
                    number = ""
                }
            }

        }

        // left
        var i = index - 1
        while (i >= 0 && schematicLines[lineNumber][i].isDigit()) {
            number = schematicLines[lineNumber][i] + number
            i -= 1
        }
        if (number.length > 0) {
            numbers.add(number.toInt())
            number = ""
        }

        // right
        i = index + 1
        while (i < schematicLines[lineNumber].length && schematicLines[lineNumber][i].isDigit()) {
            number += schematicLines[lineNumber][i]
            i += 1
        }
        if (number.length > 0) {
            numbers.add(number.toInt())
            number = ""
        }

        return numbers
    }

    fun computePartMembersPower(): Int {
        var numberIndexes = mutableListOf<Int>()
        for (lineIndex in schematicLines.indices) {
            val line = schematicLines[lineIndex]

            for (i in line.indices) {
                if (line[i] == '*') {
                    val numbers = getNumbers(lineIndex, i)
                    if (numbers.size > 1) {
                        numberIndexes.add(numbers[0] * numbers[1])
                    }

                }

            }


        }
        return numberIndexes.sum()
    }
}

fun main() {
    val engineSchematic = EngineSchematic(data)
    println(engineSchematic.computePartMembersSum()) // 539433
    println(engineSchematic.computePartMembersPower()) // 75847567
}

main()