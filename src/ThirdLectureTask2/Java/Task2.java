package ThirdLectureTask2.Java;

public class Task2 {
    public static void main(String[] args) {
        /*достоинства карт: 1,2,3,4,5,6,7,8,9,10, Jack, Queen, King, Ace, Joker
        * масти карт: Clubs, Diamonds, Spades, Hearts
        * цвета джокеров: Red, Black*/
        Card card1 = new Card("6", "Hearts");
        Card card2 = new Card("6", "Clubs");

        //проверка принадлежности карты колоде
        System.out.println(card1.checkCard());

        //сравнение карт с одинаковой мастью
        System.out.println(card1.isStrongerWithEqualSuits(card2)+ "   equal suits");

        //сравнение текущей карты с другой
        System.out.println(card1.isStronger(card2)+ "   different suits");

        //статический метод сравнения двух карт
        System.out.println(Card.isStrongerStat(card1, card2));

    }
}
