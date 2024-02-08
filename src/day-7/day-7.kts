import java.io.File
import kotlin.math.pow

val data = File("input.txt").readLines()

val CARDS = mapOf(
        '2' to 2.0,
        '3' to 3.0,
        '4' to 4.0,
        '5' to 5.0,
        '6' to 6.0,
        '7' to 7.0,
        '8' to 8.0,
        '9' to 9.0,
        'T' to 10.0,
        'J' to 11.0,
        'Q' to 12.0,
        'K' to 13.0,
        'A' to 14.0
)

val CARDS2 = mapOf(
        '2' to 2.0,
        '3' to 3.0,
        '4' to 4.0,
        '5' to 5.0,
        '6' to 6.0,
        '7' to 7.0,
        '8' to 8.0,
        '9' to 9.0,
        'T' to 10.0,
        'J' to 1.0,
        'Q' to 12.0,
        'K' to 13.0,
        'A' to 14.0
)

class Hand(cards: String, bid: Double) {
    val cards = cards
    val bid = bid
    var score = computeValue()
    var score2 = computeValuePart2()

    private fun computeValue(): Double {
        val handMap = mutableMapOf<Char, Double>()
        for(card in CARDS){
            handMap[card.key] = 0.0
        }

        var score = 0.0
        var i = 100_000_000

        for (card in cards) {
            handMap[card] = handMap[card]!! + 1
            score += CARDS[card]!! * i
            i /= 100
        }

        for (card in handMap.filter { it.value > 1 }) {
            if (card.value == 2.0) score += 1_000_000_000_000.0
            else if (card.value == 3.0) score += 3_000_000_000_000.0
            else if (card.value == 4.0) score += 5_000_000_000_000.0
            else if (card.value == 5.0) score += 6_000_000_000_000.0
        }

        return score
    }

    private fun computeValuePart2(): Double {
        val handMap = mutableMapOf<Char, Double>()
        for(card in CARDS2){
            handMap[card.key] = 0.0
        }

        for (card in cards) {
            handMap[card] = handMap[card]!! + 1
        }

        val numberOfJokers = handMap['J']!!
        handMap['J'] = 0.0

        var mostCommonChar = 'A'
        var mostCommonCard = 0.0

        for (hand in handMap) {
            if (hand.value > mostCommonCard) {
                mostCommonChar = hand.key
                mostCommonCard = hand.value
            }
        }

        if (numberOfJokers > 0) {
            handMap[mostCommonChar] = handMap[mostCommonChar]!! + numberOfJokers
        }

        var score = 0.0
        var i = 100_000_000

        for (card in cards) {
            score += CARDS2[card]!! * i
            i /= 100
        }

        for (card in handMap.filter { it.value > 1 }) {
            if (card.value == 2.0) score += 1_000_000_000_000.0
            else if (card.value == 3.0) score += 3_000_000_000_000.0
            else if (card.value == 4.0) score += 5_000_000_000_000.0
            else if (card.value == 5.0) score += 6_000_000_000_000.0
        }

        return score
    }

    override fun toString(): String {
        return "cards: $cards, bid: $bid"
    }
}

fun extractValues(data: List<String>): List<Hand> {
    return data.map {
        val values = it.trim().replace("\\s+".toRegex(), " ").split(" ")
        Hand(values[0], values[1].toDouble())
    }
}

fun main() {
    var hands = extractValues(data).sortedBy { it.score }.map { it.bid }
    var score = 0.0
    for (i in hands.indices) {
        score += hands[i] * (i + 1)
    }
    println("score (part-1): %.0f".format(score)) // 248217452

    hands = extractValues(data).sortedBy { it.score2 }.map { it.bid }
    score = 0.0
    for (i in hands.indices) {
        score += hands[i] * (i + 1)
    }
    println("score (part-2): %.0f".format(score)) // 245576185

}

main()