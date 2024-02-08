import java.io.File

val data = File("input.txt").readLines()

class CubesSet(redCubesNumber: Int, greenCubesNumber: Int, blueCubesNumber: Int) {
    val redCubesNumber = redCubesNumber
    val greenCubesNumber = greenCubesNumber
    val blueCubesNumber = blueCubesNumber

    fun getPower(): Int {
        return redCubesNumber * greenCubesNumber * blueCubesNumber
    }
}

class Game(id: Int, cubesSets: List<CubesSet>) {
    val id = id
    val cubesSets = cubesSets

    fun isGamePossible(redCubesNumber: Int, greenCubesNumber: Int, blueCubesNumber: Int): Boolean {
        for (cubesSet in cubesSets) {
            if (cubesSet.redCubesNumber > redCubesNumber) return false
            if (cubesSet.greenCubesNumber > greenCubesNumber) return false
            if (cubesSet.blueCubesNumber > blueCubesNumber) return false
        }

        return true
    }

    fun minimalCubesSet(): CubesSet {
        var minRed = 0
        var minGreen = 0
        var minBlue = 0
        for (cubesSet in cubesSets) {
            if (minRed < cubesSet.redCubesNumber) minRed = cubesSet.redCubesNumber
            if (minGreen < cubesSet.greenCubesNumber) minGreen = cubesSet.greenCubesNumber
            if (minBlue < cubesSet.blueCubesNumber) minBlue = cubesSet.blueCubesNumber
        }
        return CubesSet(minRed, minGreen, minBlue)
    }

    override fun toString(): String {
        var text = "Game $id:"

        for (cubesSet in cubesSets) {
            var pickedValues = mutableListOf<String>()
            if (cubesSet.redCubesNumber > 0) pickedValues.add("red " + cubesSet.redCubesNumber)
            if (cubesSet.greenCubesNumber > 0) pickedValues.add("green " + cubesSet.greenCubesNumber)
            if (cubesSet.blueCubesNumber > 0) pickedValues.add("blue " + cubesSet.blueCubesNumber)

            text += pickedValues.joinToString(separator = ", ", prefix = " ", postfix = ";")
        }

        return text
    }
}

fun main() {
    val games = mutableListOf<Game>()
    for (element in data) {
        val elementSplited = element.split(":")
        val id = elementSplited[0].split(" ")[1].toInt()
        val cubesSets = elementSplited[1]
                .split(";")
                .map {
                    val colors = it.split(",")
                    var red = 0
                    var green = 0
                    var blue = 0
                    for (color in colors) {
                        val splitedColor = color.trim().split(" ")
                        if (splitedColor[1] == "red") red = splitedColor[0].toInt()
                        else if (splitedColor[1] == "green") green = splitedColor[0].toInt()
                        else if (splitedColor[1] == "blue") blue = splitedColor[0].toInt()
                    }
                    CubesSet(red, green, blue)
                }
        games.add(Game(id, cubesSets))
    }

    val possibleGamesId = games
            .filter { it.isGamePossible(12, 13, 14) }
            .map { it.id }

    println("Sum: " + possibleGamesId.sum()) // 2176

    val power = games.sumOf { it.minimalCubesSet().getPower() }
    println("Power: $power") // 63700
}

main()