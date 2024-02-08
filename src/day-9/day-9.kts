import java.io.File

val data = File("input.txt").readLines()

val histories = data.map {
    History(it.split(" ").map { it.toInt() }.toMutableList())
}

class History(dataset: MutableList<Int>) {
    val datasets = mutableListOf(dataset)

    private fun areAllZeros(dataset: List<Int>): Boolean {
        for (element in dataset) {
            if (element != 0) return false
        }
        return true
    }

    fun computeDifference() {
        val lastDataSet = datasets.last()
        if (areAllZeros(lastDataSet)) return

        val childDataSet = mutableListOf<Int>()
        for (i in 0..lastDataSet.size - 2) {
            childDataSet.add(lastDataSet[i + 1] - lastDataSet[i])
        }
        datasets.add(childDataSet)
        computeDifference()
    }

    fun predictNext() {
        for (i in 0..datasets.size - 2) {
            var sum = 0
            for (j in i..datasets.size - 2) {
                sum += datasets[j].last()
            }
            datasets[i].add(sum)
        }
        datasets.last().add(0)
    }

    fun predictPrevious() {
        datasets.last().add(0)

        for (i in datasets.size - 2 downTo 0) {
            val previousValue = datasets[i].first() - datasets[i + 1].first()
            datasets[i].add(0, previousValue)
        }
    }

    override fun toString(): String {
        var text = ""
        var tab = 0

        for (dataset in datasets) {
            text += "  ".repeat(tab)
            for (value in dataset) {
                text += value
                text += if (value > 9) "  "
                else "   "
            }
            text += "\n"
            tab += 1
        }

        return text
    }
}

fun main() {
    val sumNext = histories
            .map { it.computeDifference(); it.predictNext(); it.datasets.first().last() }
            .toList()
            .sum()
    println(sumNext) // 1731106378

    val sumPrevious = histories
            .map { it.computeDifference(); it.predictPrevious(); it.datasets.first().first() }
            .toList()
            .sum()
    println(sumPrevious) // 1087
}

main()

