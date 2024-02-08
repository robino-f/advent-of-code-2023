import java.io.File
import kotlin.math.pow


val data = File("input.txt").readLines()

class Card(id: Int, winningNumbers: List<Int>, pickedNumbers: List<Int>, numberOfCopies: Int = 0) {
    val id = id
    val winningNumbers = winningNumbers
    val pickedNumbers = pickedNumbers
    var numberOfCopies = numberOfCopies

    fun getScore(): Int {
        var includedNumbers = pickedNumbers.filter { winningNumbers.indexOf(it) >= 0 }.size
        return includedNumbers
    }

    fun getScoreWithPower(): Int {
        val score = getScore()
        if (score == 0) return 0

        return 2.0.pow(score - 1).toInt()
    }

    override fun toString(): String {
        return ""
    }
}

fun main() {
    val cards = mutableListOf<Card>()
    for (element in data) {
        val cardElementSplit = element.split(":")
        val id = cardElementSplit[0].replace("\\s+".toRegex(), " ").split(" ")[1].toInt()

        val pickedNumbersWinningNumbersSplit = cardElementSplit[1].split("|")

        val winningNumbers = pickedNumbersWinningNumbersSplit[0].trim().replace("\\s+".toRegex(), " ").split(" ").map { it.toInt() }
        val pickedNumbers = pickedNumbersWinningNumbersSplit[1].trim().replace("\\s+".toRegex(), " ").split(" ").map { it.toInt() }

        cards.add(Card(id, winningNumbers, pickedNumbers))
    }

    println("score (part-1): " + cards.sumOf { it.getScoreWithPower() }) // 22488

    val initialCardsSize = cards.size
    for (i in 0..<initialCardsSize) {
        val score = cards[i].getScore()
        val instances = 1 + cards[i].numberOfCopies
        var j = i + 1
        var currentScore = 0
        while (currentScore < score*instances) {
            cards[j].numberOfCopies += 1
            currentScore += 1
            j += 1
            if (j == i + score + 1) j = i + 1
        }
    }
    println("score (part-2): " +cards.sumOf { it.numberOfCopies + 1 }) // 7013204
}

main()