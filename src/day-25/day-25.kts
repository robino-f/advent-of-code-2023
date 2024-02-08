import java.io.File

val data = File("input.txt").readLines()

class Snowverload(private val nodes: MutableMap<String, MutableSet<String>>) {

    private fun removeLink(graph: MutableMap<String, MutableSet<String>>, nodeA: String, nodeB: String) {
        if (graph[nodeA] != null) graph[nodeA] = graph[nodeA]!!.filter { it != nodeB }.toMutableSet()
        if (graph[nodeB] != null) graph[nodeB] = graph[nodeB]!!.filter { it != nodeA }.toMutableSet()
    }

    private fun computeGraphSize(graph: Map<String, MutableSet<String>>, numberOfNodes: Int): Int {
        val firstNode = graph.entries.first()
        val graphNodes = mutableSetOf(firstNode.key)
        graphNodes.addAll(firstNode.value)

        val secondGroup = mutableSetOf<String>()

        for (node in graph) {
            if (node.value.any { it in graphNodes }) {
                graphNodes.add(node.key)
                graphNodes.addAll(node.value)
            }
        }

        for (node in graph) {
            if (secondGroup.size == 0 && !node.value.any { it in graphNodes }) {
                secondGroup.add(node.key)
                secondGroup.addAll(node.value)
            } else if (node.value.any { it in secondGroup }) {
                secondGroup.add(node.key)
                secondGroup.addAll(node.value)
            }
        }

        if (graphNodes.size + secondGroup.size != numberOfNodes) return numberOfNodes

        return graphNodes.size
    }

    private fun getAllNodes(): MutableList<String> {
        val allNodes = nodes.values.flatten().toMutableSet()
        allNodes.addAll(nodes.keys.toList())
        return allNodes.toMutableList()
    }

    fun getTwoGroupsProduct(): Int {
        val allNodes = getAllNodes()
        val numberOfNodes = allNodes.size
        var graphSize = numberOfNodes

        val combinations = mutableListOf<Set<String>>()
        for (i in allNodes.indices) {
            for (j in i+1..<allNodes.size) {
                combinations.add(setOf(allNodes[i], allNodes[j]))
            }
        }

        while (numberOfNodes == graphSize || graphSize == numberOfNodes - 1) {
            val graphClone = nodes.toMutableMap()
            combinations.shuffle()

            for (i in 0..2) {
                removeLink(graphClone, combinations[i].first(), combinations[i].last())
            }
            graphSize = computeGraphSize(graphClone, numberOfNodes)
        }

        return graphSize * (numberOfNodes - graphSize)
    }

    override fun toString(): String {
        return nodes.toString()
    }
}

fun main() {
    val nodes = mutableMapOf<String, MutableSet<String>>()
    for (line in data) {
        val splitLine = line.split(':')
        val nodeName = splitLine[0]
        val linkedNodes = splitLine[1].trim().split(' ').toMutableSet()

        if (nodes[nodeName] != null) {
            nodes[nodeName]!!.addAll(linkedNodes)
        } else {
            nodes[nodeName] = linkedNodes
        }
    }

    val snowverload = Snowverload(nodes)
    println("product (part-1): " + snowverload.getTwoGroupsProduct()) // 538560
    // otherwise graph visualization
}

main()