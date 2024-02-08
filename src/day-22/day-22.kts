import java.io.File
import kotlin.math.max
import kotlin.math.min

val data = File("input.txt").readLines().toMutableList()

class Coordinate(val x: Int, val y: Int, var z: Int) {
    override fun toString(): String {
        return "x: $x, y: $y, z: $z"
    }
}

class Brick(val id: Int, val a: Coordinate, val b: Coordinate) {
    override fun toString(): String {
        return "$id: $a ~ $b"
    }
}

class SandSlabs(private val bricks: List<Brick>) {

    private fun hasXYIntersection(a: Brick, b: Brick): Boolean {
        return max(a.a.x, b.a.x) <= min(a.b.x, b.b.x) &&
                max(a.a.y, b.a.y) <= min(a.b.y, b.b.y)
    }

    fun disintegrableBricks(part2: Boolean = false): Int {
        val supportMap = mutableMapOf<Int, MutableList<Int>>()
        for (brick in bricks) {
            supportMap[brick.id] = mutableListOf()
        }

        // make brick fall
        var brickFell = true;
        while (brickFell) {
            brickFell = false
            for (brick in bricks) {
                if (brick.a.z == 1) continue

                val hasBrickBelow = bricks.any { (brick.a.z - 1 == it.b.z) && hasXYIntersection(brick, it) }
                if (!hasBrickBelow) {
                    brick.a.z -= 1
                    brick.b.z -= 1
                    brickFell = true
                }
            }
        }

        // fill supportMap
        for (brick in bricks) {
            bricks.filter {
                it.id != brick.id && hasXYIntersection(brick, it) && (brick.b.z + 1 == it.a.z)
            }.forEach {
                supportMap[brick.id]?.add(it.id)
            }
        }

        var disintegrableBricks = 0

        if (!part2) {
            // check if removing the brick doesn't change the number of supported bricks
            val numberOfBricks = bricks.filter { it.a.z != 1 }.size
            for (key in supportMap.keys) {
                val supportedBricks = mutableSetOf<Int>()
                for (entry in supportMap) {
                    if (entry.key != key) {
                        supportedBricks.addAll(entry.value)
                    }
                }
                if (supportedBricks.size == numberOfBricks) disintegrableBricks += 1
            }
            return disintegrableBricks
        }

        for (disintegratedBrick in bricks) {
            val unstableBrickIds = mutableSetOf<Int>()
            for(i in bricks.indices){
                val brick = bricks[i]
                val isUnstable = brick.id != disintegratedBrick.id
                        && brick.a.z != 1
                        && bricks.subList(max(i-75, 0), i).none { otherBrick ->
                             otherBrick.id != disintegratedBrick.id
                                     && otherBrick.id !in unstableBrickIds
                                     // is below
                                     && bricks.any { (brick.a.z - 1 == otherBrick.b.z) && hasXYIntersection(brick, otherBrick) }
                        }
                if (isUnstable) {
                    unstableBrickIds.add(brick.id)
                }
            }
            disintegrableBricks += unstableBrickIds.size
        }

        return disintegrableBricks
    }

    override fun toString(): String {
        return bricks.joinToString("\n")
    }
}

fun main() {
    var i = 0
    val bricks = mutableListOf<Brick>()
    for (line in data) {
        val splitLine = line.split('~')
        val a = splitLine[0].split(',').map { it.toInt() }
        val b = splitLine[1].split(',').map { it.toInt() }
        bricks.add(Brick(
                i,
                Coordinate(a[0], a[1], a[2]),
                Coordinate(b[0], b[1], b[2])
        ))
        i += 1
    }
    val sandSlabs = SandSlabs(bricks.sortedBy { it.a.z })

    println("steps (part-1): " + sandSlabs.disintegrableBricks()) // 512
    println("steps (part-2): " + sandSlabs.disintegrableBricks(true)) // 98167
}

main()