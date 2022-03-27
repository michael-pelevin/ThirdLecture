package ThirdLectureTask2.Kotlin

class Card1 {
    var rank: String? = null
    var suit: String? = null

    enum class Rank(val rankUserEnter: String) {
        TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"), JACK("Jack"), QUEEN(
            "Queen"
        ),
        KING("King"), ACE("Ace"), JOKER("Joker");

    }

    enum class JokerColor(val colorUserEnter: String) {
        RED("Red"), BLACK("Black");

    }

    enum class Suit(val suitUserEnter: String) {
        CLUBS("Clubs"), DIAMONDS("Diamonds"), SPADES("Spades"), HEARTS("Hearts");

    }

    //конструктор по умолчанию с заданными значениями масти и дотоинства
    constructor() {
        rank = Rank.SIX.rankUserEnter
        suit = Suit.HEARTS.suitUserEnter
    }

    //конструктор для ввода масти и достоинства с проверкой наличия масти
    constructor(rank: String?, suit: String) {
        var suitVal = ""
        //        String rankVal = "";
        for (jokerColor in JokerColor.values()) {
            if (jokerColor.colorUserEnter.lowercase() == suit.lowercase()) {
                suitVal = jokerColor.colorUserEnter
                break
            } else {
                suitVal = ""
            }
        }
        for (cardSuit in Suit.values()) {
            if (suitVal === "" && cardSuit.suitUserEnter.lowercase() == suit.lowercase()) {
                suitVal = cardSuit.suitUserEnter
                break
            }
        }
        if (suitVal != "") {
            this.suit = suitVal
            this.rank = rank
        } else {
            println("You entered wrong card`s suit or rank \n")
        }
    }

    //проверка принадлежности карты стандартной колоде
    fun checkCard(): Boolean {
        var result = false
        if (rank != null && rank == "Joker") {
            for (jokerColor in JokerColor.values()) {
                if (jokerColor.colorUserEnter.contains(suit!!)) {
                    result = true
                    break
                }
            }
        } else {
            for (cardRank in Rank.values()) {
                if (rank != null && cardRank.rankUserEnter.lowercase() == rank!!.lowercase() && cardRank.ordinal >= 0 && cardRank.ordinal < 13) {
                    for (cardSuit in Suit.values()) {
                        if (cardSuit.suitUserEnter == suit) {
                            result = true
                            break
                        }
                    }
                }
            }
        }
        return result
    }

    // текущая карта сильнее (одинаковые масти)
    fun isStrongerWithEqualSuits(cardToCompare: Card1): Boolean {
        return if (suit == cardToCompare.suit) {
            getRankIndex(rank) > getRankIndex(cardToCompare.rank)
        } else {
            System.out.printf("Suit of cards are not equals \n")
            false
        }
    }

    // текущая карта сильнее
    fun isStronger(cardToCompare: Card1): Int {
        val currentSuitIndex = getSuitIndex(suit)
        val suitIndexToCompare = getSuitIndex(cardToCompare.suit)
        if (this == cardToCompare) {
            return 0
        }
        return if (currentSuitIndex === suitIndexToCompare) {
            if (isStrongerWithEqualSuits(cardToCompare)) 1 else -1
        } else {
            if (currentSuitIndex > suitIndexToCompare) 1 else -1
        }
    }

    //получение хеш-кода текущей карты
    override fun hashCode(): Int {
        return 31 * getRankIndex(rank) + suit.hashCode()
    }

    //сравнение текущей карты с новой
    override fun equals(obj: Any?): Boolean {
        if (obj !is Card1) {
            return false
        }
        val other = obj
        return rank === other.rank && suit === other.suit
    }

    //сравнение объектов по строковому представлению
    fun equalsByString(cardToCompare: Card1): Boolean {
        return toString() == cardToCompare.toString()
    }

    override fun toString(): String {
        return String.format("Rank: %s, suit: %s", rank, suit)
    }

    companion object {
        // текущая карта сильнее (static) сравнение двух карт
        fun isStrongerStat(firstCard: Card1, cardToCompare: Card1): Int {
            val currentSuitIndex = getSuitIndex(firstCard.suit)
            val suitIndexToCompare = getSuitIndex(cardToCompare.suit)
            if (firstCard == cardToCompare) {
                return 0
            }
            return if (currentSuitIndex === suitIndexToCompare) {
                if (firstCard.isStrongerWithEqualSuits(cardToCompare)) 1 else -1
            } else {
                if (currentSuitIndex > suitIndexToCompare) 1 else -1
            }
        }

        //получение индекса масти текущей карты
        private fun getSuitIndex(suit: String?): Int {
            for (cardSuit in Suit.values()) {
                if (cardSuit.suitUserEnter.lowercase() == suit!!.lowercase()) {
                    return cardSuit.ordinal
                }
            }
            return -1
        }

        //получение индекса достоинства карты
        private fun getRankIndex(rank: String?): Int {
            for (cardRank in Rank.values()) {
                if (cardRank.rankUserEnter.lowercase() == rank!!.lowercase()) {
                    return cardRank.ordinal
                }
            }
            println("Достоинство карты указано неверно")
            return -1
        }
    }
}