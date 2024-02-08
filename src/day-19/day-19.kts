import java.io.File

val data = File("input.txt").readLines()

class Rule(private val conditions: List<String>) {
    fun applyOnItem(item: Item): String {
        for (condition in conditions) {
            var splitCondition = condition.split(':')
            if (splitCondition.size == 1) return condition

            val destination = splitCondition[1]
            splitCondition = splitCondition[0].split('>')

            if (splitCondition.size == 2) {// >
                if (item.values[splitCondition[0][0]]!! > splitCondition[1].toInt()) return destination
            } else { // <
                splitCondition = splitCondition[0].split('<')
                if (item.values[splitCondition[0][0]]!! < splitCondition[1].toInt()) return destination
            }
        }

        return ""
    }

    fun getPossibilities(itemRange: ItemRange): List<Pair<String, ItemRange>> {
        val possibilities = mutableListOf<Pair<String, ItemRange>>()


        val nextItemRange = itemRange.getCopy()

        for (condition in conditions) {
            var splitCondition = condition.split(':')
            if (splitCondition.size == 1) {
                possibilities.add(splitCondition[0] to nextItemRange)
            } else {
                val destination = splitCondition[1]
                splitCondition = splitCondition[0].split('>')

                val currentItemRange = nextItemRange.getCopy()
                if (splitCondition.size == 2) {// >
                    currentItemRange.values[splitCondition[0][0]] = mutableListOf(splitCondition[1].toInt() + 1, (nextItemRange.values[splitCondition[0][0]]!!)[1])
                    possibilities.add(destination to currentItemRange)
                    nextItemRange.values[splitCondition[0][0]] = mutableListOf((nextItemRange.values[splitCondition[0][0]]!!)[0], splitCondition[1].toInt())
                } else { // <
                    splitCondition = splitCondition[0].split('<')

                    currentItemRange.values[splitCondition[0][0]] = mutableListOf((nextItemRange.values[splitCondition[0][0]]!!)[0], splitCondition[1].toInt() - 1)
                    possibilities.add(destination to currentItemRange)
                    nextItemRange.values[splitCondition[0][0]] = mutableListOf(splitCondition[1].toInt(), (nextItemRange.values[splitCondition[0][0]]!!)[1])
                }
            }
        }

        return possibilities
    }

    override fun toString(): String {
        return conditions.joinToString("\n")
    }
}

class Item(input: List<String>) {
    val values = mapOf(
            'x' to input[0].split('=')[1].toInt(),
            'm' to input[1].split('=')[1].toInt(),
            'a' to input[2].split('=')[1].toInt(),
            's' to input[3].split('=')[1].toInt()
    )

    override fun toString(): String {
        return values.toString()
    }
}

class ItemRange() {
    val values = mutableMapOf(
            'x' to mutableListOf(1, 4000),
            'm' to mutableListOf(1, 4000),
            'a' to mutableListOf(1, 4000),
            's' to mutableListOf(1, 4000),
    )

    fun computePossibilities(): Double {
        var possibilities = 1.0
        for (value in values.values) {
            possibilities *= value[1] - value[0] + 1
        }

        return possibilities
    }

    fun getCopy(): ItemRange {
        val copy = ItemRange()
        for (entry in values) {
            copy.values[entry.key] = mutableListOf(entry.value[0], entry.value[1])
        }

        return copy
    }

    override fun toString(): String {
        return values.toString()
    }
}

class Aplenty(rules: List<String>, items: List<String>) {
    val rules = computeRules(rules)
    val items = items.map { Item(it.replace("{", "").replace("}", "").split(',')) }

    private fun computeRules(items: List<String>): Map<String, Rule> {
        val computedRules = mutableMapOf<String, Rule>()

        items.forEach {
            val splitLine = it.split(('{'))
            computedRules[splitLine[0]] = Rule(splitLine[1].replace("}", "").split(','))
        }

        return computedRules
    }

    fun getRatingSum(): Double {
        return items.filter {
            var currentWorkflow = "in"
            while (currentWorkflow != "A" && currentWorkflow != "R") {
                currentWorkflow = rules[currentWorkflow]!!.applyOnItem(it)
            }
            currentWorkflow == "A"
        }.sumOf { it.values.values.sum().toDouble() }
    }

    fun getRatingPossibilities(): Double {
        val acceptedPossibilities = mutableListOf<ItemRange>()
        var possibilities = rules["in"]!!.getPossibilities(ItemRange()).toMutableList()

        while (possibilities.size > 0) {
            possibilities = possibilities.flatMap { rules[it.first]!!.getPossibilities(it.second) }.toMutableList()

            acceptedPossibilities.addAll(possibilities.filter { it.first == "A" }.map { it.second })
            possibilities = possibilities.filter { it.first != "R" && it.first != "A" }.toMutableList()
        }

        return acceptedPossibilities.sumOf { it.computePossibilities() }
    }

    override fun toString(): String {
        return rules.toString() + "\n" + items.joinToString("\n")
    }
}

fun main() {
    val rules = mutableListOf<String>()
    val items = mutableListOf<String>()
    var rulesFinished = false

    for (line in data) {
        if (line.isEmpty()) {
            rulesFinished = true
        } else {
            if (!rulesFinished) rules.add(line)
            else items.add(line)
        }
    }

    val aplenty = Aplenty(rules, items)

    println("rating numbers sum of accepted parts (part-1): %.0f".format(aplenty.getRatingSum())) // 449531
    println("rating numbers sum of accepted parts (part-2): %.0f".format(aplenty.getRatingPossibilities())) // 239823810777583

}

main()