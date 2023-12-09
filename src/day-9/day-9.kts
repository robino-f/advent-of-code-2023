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
        if (areAllZeros(lastDataSet)) return;

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
    val history1 = History(mutableListOf(0, 3, 6, 9, 12, 15))
    history1.computeDifference()

    val history2 = History(mutableListOf(1, 3, 6, 10, 15, 21))
    history2.computeDifference()

    val history3 = History(mutableListOf(10, 13, 16, 21, 30, 45))
    history3.computeDifference()

    history1.predictNext()
    history1.predictNext()
    println(history1)

    history2.predictNext()
    history2.predictNext()
    println(history2)

    history3.predictNext()
    history3.predictNext()
    println(history3)

    // => 158
}

main()