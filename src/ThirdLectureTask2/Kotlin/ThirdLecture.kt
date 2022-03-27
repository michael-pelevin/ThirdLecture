import ThirdLectureTask2.Kotlin.Card1

fun main(){
    val card1 = Card1("10", "Hearts")
    val card2 = Card1("6", "Hearts")

    //проверка принадлежности карты колоде
    println(card1.checkCard())

    //сравнение карт с одинаковой мастью
    println(card1.isStrongerWithEqualSuits(card2).toString() + "   equal suits")

    //сравнение текущей карты с другой
    println(card1.isStronger(card2).toString() + "   different suits")

    //статический метод сравнения двух карт
    println(Card1.isStrongerStat(card1, card2))
}