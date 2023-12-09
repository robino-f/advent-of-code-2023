import kotlin.streams.toList

class CubesSet(redCubesNumber: Int, greenCubesNumber: Int, blueCubesNumber: Int) {
    val redCubesNumber = redCubesNumber
    val greenCubesNumber = greenCubesNumber
    val blueCubesNumber = blueCubesNumber
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
    val game1 = Game(1, listOf(CubesSet(4, 0, 3), CubesSet(1, 2, 6), CubesSet(0, 2, 0)))
    val game2 = Game(2, listOf(CubesSet(0, 2, 1), CubesSet(1, 3, 4), CubesSet(0, 1, 1)))
    val game3 = Game(3, listOf(CubesSet(20, 8, 6), CubesSet(4, 13, 5), CubesSet(1, 5, 0)))
    val game4 = Game(4, listOf(CubesSet(3, 1, 6), CubesSet(6, 3, 0), CubesSet(14, 3, 15)))
    val game5 = Game(5, listOf(CubesSet(6, 3, 1), CubesSet(1, 2, 2)))

    val possibleGamesId = listOf(game1, game2, game3, game4, game5)
            .stream()
            .filter { println(it); it.isGamePossible(12, 13, 14) }
            .map { it.id }
            .toList()

    println("Possible gamesId $possibleGamesId")
    println("Sum: " + possibleGamesId.sum()) // 8
}

main()