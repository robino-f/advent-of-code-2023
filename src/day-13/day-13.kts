import java.io.File

val data = File("input.txt").readLines().toMutableList()

class Mirror(private val lines: List<String>) {

    fun checkIfVerticalReflection(reflectionX: Int): Boolean {
        var i = reflectionX - 1
        if (i < 0) return true

        for (x in reflectionX + 2..<lines[0].length) {
            for (y in lines.indices) {
                if (lines[y][x] != lines[y][i]) return false
            }
            i -= 1
            if (i < 0) break
        }
        return true
    }

    fun checkIfHorizontalReflection(reflectionY: Int): Boolean {
        var i = reflectionY - 1
        if (i < 0) return true

        for (y in reflectionY + 2..<lines.size) {
            for (x in 0..<lines[0].length) {
                if (lines[y][x] != lines[i][x]) return false
            }
            i -= 1
            if (i < 0) break
        }
        return true
    }

    fun getReflectionValue(): Int {
        // vertical
        for (x in 0..<lines[0].length - 1) {
            for (y in lines.indices) {
                if (lines[y][x] != lines[y][x + 1]) break
                // possible reflection
                if (y == lines.size - 1 && checkIfVerticalReflection(x)) {
                    return x + 1
                }
            }
        }

        // horizontal
        for (y in 0..<lines.size - 1) {
            for (x in 0..<lines[0].length) {
                if (lines[y][x] != lines[y + 1][x]) break
                // possible reflection
                if (x == lines[0].length - 1 && checkIfHorizontalReflection(y)) {
                    return (y + 1) * 100
                }
            }
        }

        return 0
    }

    fun checkIfVerticalReflectionPart2(reflectionX: Int): Boolean {
        var i = reflectionX - 1
        var smacks = 0
        if (i < 0) return false

        for (x in reflectionX + 2..<lines[0].length) {
            for (y in lines.indices) {
                if (lines[y][x] != lines[y][i]) {
                    smacks += 1
                    if (smacks > 1) return false
                }
            }
            i -= 1
            if (i < 0) break
        }
        if (smacks == 1) return true
        return false
    }

    fun checkIfHorizontalReflectionPart2(reflectionY: Int): Boolean {
        var i = reflectionY - 1
        var smacks = 0
        if (i < 0) return false

        for (y in reflectionY + 2..<lines.size) {
            for (x in 0..<lines[0].length) {
                if (lines[y][x] != lines[i][x]) {
                    smacks += 1
                    if (smacks > 1) return false
                }
            }
            i -= 1
            if (i < 0) break
        }
        if (smacks == 1) return true
        return false
    }

    fun getReflectionValuePart2(): Int {
        var smacks = 0
        // vertical
        for (x in 0..<lines[0].length - 1) {
            for (y in lines.indices) {
                if (lines[y][x] != lines[y][x + 1]) {
                    smacks += 1
                    if (smacks > 1) break
                }
                // possible reflection
                if (y == lines.size - 1 && ((smacks == 0 && checkIfVerticalReflectionPart2(x)) || (smacks == 1 && checkIfVerticalReflection(x)))) {
                    return x + 1
                }
            }
            smacks = 0
        }

        // horizontal
        for (y in 0..<lines.size - 1) {
            for (x in 0..<lines[0].length) {
                if (lines[y][x] != lines[y + 1][x]) {
                    smacks += 1
                    if (smacks > 1) break
                }
                // possible reflection
                if (x == lines[0].length - 1 && ((smacks == 0 && checkIfHorizontalReflectionPart2(y)) || (smacks == 1 && checkIfHorizontalReflection(y)))) {
                    return (y + 1) * 100
                }
            }
            smacks = 0
        }

        return 0
    }

    override fun toString(): String {
        return lines.joinToString("\n")
    }
}

fun main() {
    val mirrors = mutableListOf<Mirror>()
    val lines = mutableListOf<String>()

    for (line in data) {
        if (line.isEmpty()) {
            mirrors.add(Mirror(lines.map { it }))
            lines.clear()
        } else {
            lines.add(line)
        }
    }
    if (lines.size > 0) mirrors.add(Mirror(lines))

    println("reflexions (part-1): " + mirrors.sumOf { it.getReflectionValue() }) // 30575
    println("reflexions (part-2): " + mirrors.sumOf { it.getReflectionValuePart2() }) // 37478
}

main()