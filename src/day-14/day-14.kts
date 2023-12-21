import kotlin.math.floor

val dataExample = mutableListOf(
        "O....#....",
        "O.OO#....#",
        ".....##...",
        "OO.#O....O",
        ".O.....O#.",
        "O.#..O.#.#",
        "..O..#O..O",
        ".......O..",
        "#....###..",
        "#OO..#...."
)
val data = mutableListOf(
        ".....#...O..O...O.....O#.O.#..O..#...O#.#OOO.O..##O.O##O..O....#..#...O.##..O.....O#..O..O.O..O#...#",
        "O.OO...#OO.OO..#.OO.......O......O#..#O.#.OOO.#O.##.O#OOO...O..#...O#..#.OOO..#...#....O........O.O#",
        ".#.#.O##O...##.#.OO..O.O#..OO.O.OO..O..O#.##O.....#..##.#.##.#..O.O...#.O..#...#.....#....OO#OOOO..#",
        "O..O.O.#.#...O.OOO.O.O..O.O..OO#O...........#O.O.O.....#O..O....#O..#OO.O...O#...O.#..#..#..O...#...",
        "..O.#....OOO..OO....#OOO.#O...#O...O#.........O..#.##...OOO.OO.......OOO........O.##..O.#.O.OO.#O.O.",
        "...###...........#.O#..OO....OO##O.##..O..O...#O#.OO.O.O...#.#OO.#O.#.......#O..#.##O.O....#........",
        ".O...#.#.O.......O.O..O#..#O..#.....O.O...O#.#.O.....#..#.O...#......#..O.#..O###O..O....##.O#.#O...",
        "#..#OO.O#..##..OO..O...#.OOO..##O#...#..#......OO#...O.O..O#.#.#....OO.O...O.....O.OOO.#O.O#O.....#.",
        "OO.O.#.O#.O.####O.O..O..O......#.OOOO#...O....O..#....O......O..OO.O..#.....O..#...O#.O..O##.......O",
        "O..##.#.....##...O.O##....OO..O#..#......O.#.O#..#....##...#..........O.O#O..#O.O...O...O...#O...O.O",
        ".......O#.O.#...O.OO#.OO...O...##OO..OO...#.O....##O..O.O#..#...O#......#.O.#O..##.#OO.O....##O.#.O.",
        "..OO.O.#..#...O.OO...#.#..#O....#.O....O..O..O..O#.##.O..O...O......##.......#.#.....O.#..O..#...O##",
        ".#...#...O..#.O.O......O..O.O...O...O.O.O..OO....OOO.....#..O...O..OO....#..#.O..O.OOO.#..O..#O..O..",
        "O...OO.#...O.O#O..#.#O.#.O.O#.#..#....O#.O....O..O........#..OO.#..#..O.#OO.#.O.......#...#OO.......",
        ".O..#.O..##OO....O.O#O........O..#O.#...O#.O#..O.O..#..#O....O...O#.....O.O.O.###....O#OOOO...#...O.",
        ".....O.....#O.#.O.#..O....O.#..##..O.O#O..O.#O....#..#...#O#..O......#OOOOOO...O....####..#.O...#O..",
        "O...O..#...#O......#....OO...OO.#..#..#...##..#.....O##.#.O.#.O..#.O...#.#..#.OO..O....#...O.#...OOO",
        ".O..O.................O.O##O.....O..O#.O..#...##OOO...O.O#O.OO..........#O.....#....#.#.#..O.#O#...O",
        "#OO.O.O#O#.#......##.#...OO.........#..#.O......O..O.#OO#.#....O.#..O.O..#..#O..OO..#...O.....#OO...",
        "##.........O..OOOO......#.#....#.#.......#O..#.#..O..O.O....O..#...##......O..O.OO..O.......##......",
        "....O.##........#...#.O.#OO#..#.....O.#....O...O.#...........#..#.#O.#.OO#.O...#..O#....##..#.O..O.#",
        ".......O....O.#O...#.#O#..O#..O.#...#O.O..O..............OO#O..#.##.O.#O...O.......OO..O...O....OO..",
        ".......O.OO#..#.....O.....#....O..OO.O.OO..O..###..O..O.#.O##OO....OO.OO.####...O.....O.O....#....O.",
        "####....O.OO..OO.....O..#..#....O.#.O.#.O....#...O.O....#..#.O...#......O...#.#.O.#...O..OO...#.....",
        ".#...##.##O.O#..#.#.OO....O..O###....##O..#..#.O...O...O.OO.....##..O.......O.O#....#.OO#.#...O.#...",
        "#.O.O.OO..O..O..O...O..#........#...O..##O###.O#...O#.....#.....O#O.#..#.#...O......#..O..#..O.O..#.",
        "#......O..#..O.......O..#.OOO...O.#........O...#..OO...O#O....#........##O...#..##..O.#..OOO#..O.#..",
        ".O...OO.O..O#...O....O#..#...............#O...OO........O....OO#.#...O#...O#..OO.O.O.O.#.#O.#O......",
        "..##..O.......##..OOO#..O..O#....OO..O.#...........#...O...#.O...OO.#.O..#.#.......##O##O.OO#..O....",
        "OO...O....#......#.O.#.#..........#..O..O..O...O....O#......O......#.....#..#..OO..O........O.#.#O..",
        "...OO..OO#O.O..O#O.#....O.#.O......O.#...O........O....#.....OO......O..#.OOO........#.O.O...O#....O",
        ".#.#....O.###...##.....O......#.O........OO.O..OO#...O.#....O...##...O.O.#..O..O..O....OOO.#.#O..O.#",
        "O.OO##.O.......O#.O.#O..O..O.....##O....O..#.O..#..#.....O.#O#...##..#O.O..O...OO#O.##.##.O#..#....#",
        "......O..##.....#.#.#OO....#..O....OO..........#...##..........#OO.OO...#.O.OO..O..#O..##..O.O....O#",
        "OO.O.O#.O.#.O#O#...##......O.#O...OO..O...O....#...OO...OO..O....#..OO..#OO..O.O.....OO#..O#..#O..O.",
        ".O..#O#...O####..#...#..#.O...............#....#OO##OO..O.O.#...O.....OO.#OO..O..O.O.O.O..#..O....O#",
        "....#.#.OOO....#......#...O.O..O.###..OO..#..#.........#.O.O.O#.O......O.#..O....#..O.#.......#.....",
        "O#O.#OO#O.O.#O#.O#O...OO#O.O.O....OO....O.#..O....O..O#........#..O#.....#O.O..O#..O......O..O.O...#",
        ".......#O...O....#..O....#O#O...#O#.O#...##.O...O.O.O...O.....O....OO...O..............O.O##.##....#",
        "...O.O........O.##.O.O.O.....O.#O#....O.....#..##...OO....O#O..#..#..O..#O##OO..O.....#..O#...#.#O..",
        "......O.O...O....#..OO.O#....O.O.#.....O.#O..O#...#.#.O...........##.#...OO#O..O......O.........O.O.",
        "..O.O#......OO#.O#O.....#O...OOOO#.#O.#.##O.....O#..#.O.....#.....O#....#OO...#.O....O.....O.....#..",
        "..O.OO.......###OO..#.#..O##OOO.#..O...OO..O#O#....#.......OO.....O.O.O..#...OO...O...O......#..O.O.",
        "OO#.O#O....#O#.O.#....O..O.OO.#..O..#O....##O.#OOO..#....O......OO...O...O.#..OOO.OOO###.........O..",
        "##..##O...O#......O..#O..O..O#O....#........O..##.O.#...#.#......##O##..#.#.##.....O....#O..#.....OO",
        "........#.O#..#...#...O..#..#.O.#O#..#.O..O#......#....O.O.O#O.O....##...##.O..O..#.OOO.........O...",
        ".....O......#.#OOOO#O...OO..#...#......O..O..#O..O#..O.......#.#O....O#.##.O...O..#O.O...#..O.#OO..O",
        "..#.O....O...#...##.##O#.O#.O...OO.#..OO.O..O.O...#.O..O.OO##.......#.OOOO....O.....O......O....#..O",
        ".#..#.#.....OO.###....#.##..O.....#...O#.#.#...OOOO#.#.O..OO.....OO......OO......O.O.OO.#.....#OO.O.",
        "..O#.##..#.OO#..O.O.O...O#..O..#..O.#.#O...#...O.O..#.##...OO##..OO#....OO#.O.O#..O#.O.....O....#.O.",
        "O.O.#..O#..O.#.....#.O.#..O...#..O.O.O....##O.##....O..#OO#OO...#..O.#O........O#.#...#OOO...O#.O...",
        "#O.####..O.#..........O..#.....#..#O.O..OOO#...O.O...##....O...O...#..#....O...#........O.O..O.O..#.",
        ".O..OOO.O.##O..O....#.##OO..O...OO.......#.O#..........#.O..OO#..O...#...O.#..#........#..O.#OOOO..O",
        ".#....#.O#.....O...O.OO.#OO#..O##...O.#.#O..O..O.....O.OOOO.O.#.......OO..#..O.#O.....#..O..OO.#.#.O",
        "................#OO.#......#...#...#...O.......O.....#...#O..O.O.O.##....O.O..O##...#....O.....OO#O#",
        "..O......O#O..O.....O..#O#..##.O....O#.O.............#.O.O#OO.O.....O.#.O....#..O......O..O#.....#O#",
        "O.#..O#.OO..........#.#O.O#OO.....#...OOO....#.O..O.....##..OO###......#....O...#......#O.......#.O.",
        ".#O#.OO.O.O#O...O....OO#.O..#O......OO..O.#.....O..#O..O.#....O.OOO.O.....O.O#.O#O.O.....OO.OO.O.#.#",
        ".O......O..O...O#O.#....O...#..##...OOO#.O.#...#.#....#.##...O..OO.O..O..O.##O#.OO...#.O......O.#..#",
        ".###.#......#..O.....#.O.OO#O...#.####....O.#O..#.........O...#.O...#.##OO...O##...............#....",
        "...#...O............O.....OOO...#........O#.....#....O..##....#....O.O.O.O...O.#....OO..O#.....OOO#O",
        "..........#....O.#.O#O....#O...........#OOO........O#OOO...#...O.#.#....O..O..O....O..O.#.O.....O#..",
        "..OO....O....O#O..O........O.#.#.#.##O.#.#...#.##....#.#.O...O.O..#.#.##..O#.....O.O.O..#.#O#.......",
        "O.O...O...O.........O.#..OO...#...#O.#.#.#..#....OO..OOO....OO..O...O...O#.O#......O...#..#O...O..O.",
        "#...O##....O.#.#.O.#OO...#O..#O#O.O#..#.........OO#..OOO.OO.....#...O......##.......#.OO....O....O#.",
        "..O..##...........#.OO...O..#OO....#O.#...#.O....#.O......#..O.O......##O..O.O#O.O...........OO.#O.O",
        "...O.........#.#.#.##..OO......O.#O.....#.OO..O..#..O.....O..O.#.#OO.....#...#.O...O..#........#....",
        "......O.###...O...#.O....O....O#...O#......OO...O......O..O..O.#.....O..O....###.O.#O#O..O.#.O......",
        "#.OO#..O..#.O........O....O.....O.#OO..#.#...#.##O...#..O..#.....#....#.....O.OO.......O......O.....",
        "O..O........O..O...#..O..#...#.#.....#..O...##O.#....O.....#.....O..#.O....O...O......##.O#..#...O..",
        "...##O.OO...#.#...#.O.....O..#.OO#....O.OO.#.O##....O....O..#....#.O..OOO....OO##OO......OO..#..#...",
        ".O..O#O..#......O....OO.#....O##O#.....###..........##....#O.O.###..#O...O.....OOO#..#....#.#O##.#..",
        "#.O..O....#..#.O.#....#OO....##.O....O.....#...O#....O#OO....##O.O.#.....#O#O#.#O....OO..........#O#",
        "..#OOO..O.O.#.#.O.#.......#.O#....#..##...........O...........#..#O......O...O.#.OO#O.O#....O..O..#.",
        "#......O.O...O....O.##.OO.OO.OOO....OOO...O...#..#.......O.O............O.OOO...#..#.O.#...#O#.....#",
        "...#.O..#..#..##.............O.##..#.#......O.O....#.....#...........O#O#OOO...#.##.#.#OO.#O.#.#.#..",
        ".#..##OO#O.....#..#..#....O.#.#...O..O..#..#....##.O...##..###OO..O.OO...OO#OO....#OOOOO.O#O........",
        "#.#......O..#.#.OO...O.#....O#O.#.#O#........#.....##O.#.O.......##.#O#.OOO..OO.O..OO..O#..OOOO#....",
        ".##.#O.#....O.#...OO.#...OO.O..O#..#..O.O#....OO.OO.#O.O...OO.#.#O#..O..O..O#..#.#.O....###O.#...O.#",
        "..............O.O....##.#.#......O..OO.....#...O.O..O..#..O#...O....O##.O#.....O...#..O...O#.#.O#..O",
        "O...O..O.O....O.O.......#..#...#O.....#...........O..O....#......O#....O..#O....OO.#.##.O.O.O.##.O..",
        ".#...O.O##O.#...#.....O.#...#...##...O#O..#O....O..##.O.O...#.......OO#O......#O.....OO...#O#O##...O",
        "#...##.#O...#..#.O.#O...#......#..#..#.#.....#.#.#.....OOO...###..#..O...#..O#......O.O....O#O.OO..O",
        "..O..O.........#...#.#.O.O.O...#.O....O#OO#..O.#.O.....O.O#O..#.#.##.O#...OO.....##...##...O.##....#",
        "O...#.#O.......##.........O.O.#....O..##.O#...OO.#.O#OO.#.OO#OOO...O.O....O.....#O.#O........O.OO.##",
        ".O......O..OO.O.#.OOO.#O.........#..#O..O###O.O..O##.#..O........O#.....#..#..#.O#..#..#O..........O",
        "O#..O.OO...#...O.#.............##......OOOO#OO.O.O...O...#.....#O.O#.##O...O..........O....O..OO..#.",
        "#.O.O..#O..#..##O.......O.O#OO....#..O...O.#....#............##O......#O.#......O.......##..O....O..",
        "O..O...O#O.OO#....O...O#......##...O..O.#O.....OO.O...##..#.O..O#O#..#O......O#.#.#OO..OOO.O.....O..",
        "..O...O.....O..#..#....OO.O....O.O...O#OO.....OO#.O......O.##.O...O..O.........##...##.O.##.O...O..#",
        "..##...##O.O..O..#.....O.O.O...#......#OO.#OO...O#.O..#.....#...O.#.O#...OO.O....#..#.#O......#..O.O",
        "OO.....#...........O#..O#...O......O.O..#.O#............#..O....OO.O.....O.....OO##O..#.O.#..O...OO.",
        "......O.O.....OO#..O#.O....#.O....#....OO..O...#..#..#.O..O#O...O..O#.O..#.#......#.O...#.....O#...O",
        "O...O...O#OO.#..O..O.OOO.##.#.#..#....#...O.O...O#O...#.......OO...O....###.#..O#...###O.....###.O..",
        "OO#.O..O#...O...O..O..##O........O#O#O....O.O...#.....##...##.#.#.O#..#.O..#......O.#...........O...",
        ".O...O#OO.O#.OOOO..O..O....#...O.O..O#.O.....#O.O.#...OO.O.O#....#O.#....O..OO..##.O...O...O..#....O",
        "..###.....#.#.#.##......##O..OOO....##.O.O##.#..#.#.O.O.#O.....#.#O...#O.O.#..O....#.O....O...OOO...",
        "......O#.##.O..#.O#.#......O.OO..##O#.O.O..#..###..........O..OO..O..O...OO#.......#.O...#..O#...O..",
        "O......#....O...##.O.#..O......OO##.#.O....OO#O....O.O..#O#O##.....###..O..O.O.OOO#O#......O.OO.#O#.",
        "...O...OO..#OO..#...O..#...##...#....O.#.O.O.#..#.###.O#.#.#O..O....#..##..##.OOO...O...O..O#O...#.#"
)

class Platform(private var loadedPlatform: MutableList<String>) {

    private fun getNumberOfEmptySpaces(startX: Int, startY: Int): Double {
        var emptySpaces = 0.0
        var y = startY - 1
        var canFall = true;
        while (canFall) {
            if (y < 0 || loadedPlatform[y][startX] == '#') canFall = false
            else if (loadedPlatform[y][startX] == '.') {
                emptySpaces += 1
            }

            y -= 1
        }
        return emptySpaces
    }

    fun applyGravity(): Double {
        var totalLoad = 0.0

        val nextPlatform = mutableListOf<String>()
        for (y in loadedPlatform.indices) {
            nextPlatform.add(".".repeat(loadedPlatform[0].length))
        }

        for (x in 0..<loadedPlatform[0].length) {
            for (y in loadedPlatform.indices) {
                if (loadedPlatform[y][x] == 'O') {
                    val load = loadedPlatform.size - y + getNumberOfEmptySpaces(x, y)
                    totalLoad += load

                    val loadIndex = loadedPlatform.size - load.toInt()
                    nextPlatform[loadIndex] = nextPlatform[loadIndex].replaceRange(x, x + 1, "O")
                } else if (loadedPlatform[y][x] == '#') {
                    nextPlatform[y] = nextPlatform[y].replaceRange(x, x + 1, "#")
                }
            }
        }

        loadedPlatform = nextPlatform

        return totalLoad
    }

    private fun rotatePlatform() {
        val nextPlatform = mutableListOf<String>()

        for (x in 0..<loadedPlatform[0].length) {
            var line = ""
            for (y in loadedPlatform.size - 1 downTo 0) {
                line += loadedPlatform[y][x]
            }
            nextPlatform.add(line)
        }

        loadedPlatform = nextPlatform
    }

    fun applyGravityAfterCycles(): Double {
        val loads = mutableListOf<String>()

        var i = 0
        var cycleFound = false
        while (i < 1000000000) {
            val cycleLoads = mutableListOf<Double>()
            for (j in 0..<4) {
                val load = applyGravity()
                cycleLoads.add(load)
                rotatePlatform()
            }

            val indexOfCurrentLoad = loads.indexOf(cycleLoads.toString())


            if (indexOfCurrentLoad > -1 && !cycleFound) {
                val cycleLength = i - indexOfCurrentLoad
                i += (floor((1000000000.0 - i) / cycleLength) * cycleLength).toInt()
                cycleFound = true
            }
            loads.add(cycleLoads.toString())
            i += 1
        }

        var loadAfterCycles = 0.0
        for (x in 0..<loadedPlatform[0].length) {
            for (y in loadedPlatform.indices) {
                if (loadedPlatform[y][x] == 'O') {
                    val load = loadedPlatform.size - y
                    loadAfterCycles += load
                }
            }
        }

        return loadAfterCycles
    }

    override fun toString(): String {
        return loadedPlatform.joinToString("\n")
    }
}

fun main() {
    val platform = Platform(data)

    println("total load (part-1): %.0f".format(platform.applyGravity()))
    println("total load (part-2): %.0f".format(platform.applyGravityAfterCycles()))
}

main()