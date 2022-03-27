package ThirdLectureTask2.Java;

public class Card {
    public String rank;
    public String suit;

    public enum Rank {
        TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"),
        JACK("Jack"), QUEEN("Queen"), KING("King"), ACE("Ace"), JOKER("Joker");
        private final String rankValue;

        Rank(String rv) {
            rankValue = rv;
        }

        public String getRankUserEnter() {
            return rankValue;
        }
    }

    public enum JokerColor {
        RED("Red"), BLACK("Black");
        private final String colorValue;

        JokerColor(String cv) {
            colorValue = cv;
        }

        public String getColorUserEnter() {
            return colorValue;
        }
    }

    public enum Suit {
        CLUBS("Clubs"), DIAMONDS("Diamonds"), SPADES("Spades"), HEARTS("Hearts");
        private final String suitValue;

        Suit(String sv) {
            suitValue = sv;
        }

        public String getSuitUserEnter() {
            return suitValue;
        }
    }

    //конструктор по умолчанию с заданными значениями масти и дотоинства
    public Card() {
        this.rank = Rank.SIX.getRankUserEnter();
        this.suit = Suit.HEARTS.getSuitUserEnter();
    }

    //конструктор для ввода масти и достоинства с проверкой наличия масти
    public Card(String rank, String suit) {
        String suitVal = "";
//        String rankVal = "";
        for (JokerColor jokerColor : JokerColor.values()) {
            if (jokerColor.getColorUserEnter().toLowerCase().equals(suit.toLowerCase())) {
                suitVal = jokerColor.getColorUserEnter();
                break;
            } else {
                suitVal = "";
            }
        }
        System.out.println(suitVal);
        for (Suit cardSuit : Suit.values()) {
            if ( suitVal == "" && cardSuit.getSuitUserEnter().toLowerCase().equals(suit.toLowerCase())) {
                suitVal = cardSuit.getSuitUserEnter();
                break;
            }
        }

        if (!suitVal.equals("")) {
            this.suit = suitVal;
            this.rank = rank;
        } else {
            System.out.println("You entered wrong card`s suit or rank");
        }
    }

    //проверка принадлежности карты стандартной колоде
    public boolean checkCard() {
        Boolean result = false;
        if (this.rank != null && this.rank.equals("Joker")) {
            for (JokerColor jokerColor : JokerColor.values()) {
                if (jokerColor.getColorUserEnter().contains(suit)) {
                    result = true;
                    break;
                }
            }
        } else {
            for (Rank cardRank : Rank.values()) {
                if (this.rank != null && cardRank.getRankUserEnter().toLowerCase().equals(this.rank.toLowerCase()) &&
                        cardRank.ordinal() >=0 &&
                        cardRank.ordinal() < 13) {
                    for (Suit cardSuit : Suit.values()) {
                        if (cardSuit.getSuitUserEnter().equals(suit)) {
                            result = true;
                            break;
                        }
                    }
                }
            }
        }
        return result;
    }

    // текущая карта сильнее (одинаковые масти)
    public boolean isStrongerWithEqualSuits(Card cardToCompare) {
        if (this.suit.equals(cardToCompare.suit)) {
            return getRankIndex(this.rank) > getRankIndex(cardToCompare.rank);
        } else {
            System.out.printf("Suit of cards are not equals \n");
            return false;
        }
    }

    // текущая карта сильнее
    public Integer isStronger(Card cardToCompare) {
        Integer currentSuitIndex = getSuitIndex(this.suit);
        Integer suitIndexToCompare = getSuitIndex(cardToCompare.suit);
        if (this.equals(cardToCompare)) {
            return 0;
        }
        if (currentSuitIndex == suitIndexToCompare) {
            return this.isStrongerWithEqualSuits(cardToCompare) ? 1 : -1;
        } else {
            return currentSuitIndex > suitIndexToCompare ? 1 : -1;
        }
    }

    // текущая карта сильнее (static) сравнение двух карт
    public static Integer isStrongerStat(Card firstCard, Card cardToCompare) {
        Integer currentSuitIndex = getSuitIndex(firstCard.suit);
        Integer suitIndexToCompare = getSuitIndex(cardToCompare.suit);
        if (firstCard.equals(cardToCompare)) {
            return 0;
        }
        if (currentSuitIndex == suitIndexToCompare) {
            return firstCard.isStrongerWithEqualSuits(cardToCompare) ? 1 : -1;
        } else {
            return currentSuitIndex > suitIndexToCompare ? 1 : -1;
        }
    }

    //получение индекса масти текущей карты
    private static Integer getSuitIndex(String suit) {
        for (Suit cardSuit: Suit.values()) {
            if (cardSuit.getSuitUserEnter().toLowerCase().equals(suit.toLowerCase())) {
                return cardSuit.ordinal();
            }
        }
        return -1; //элемент не найден
    }

    //получение индекса достоинства карты
    private static Integer getRankIndex(String rank) {
        for (Rank cardRank: Rank.values()) {
            if (cardRank.getRankUserEnter().toLowerCase().equals(rank.toLowerCase())) {
                return cardRank.ordinal();
            }
        }
        System.out.println("Достоинство карты указано неверно");
        return -1; //элемент не найден
    }


    //получение хеш-кода текущей карты
    @Override
    public int hashCode() {
        return 31 * getRankIndex(rank) + suit.hashCode();
    }

    //сравнение текущей карты с новой
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Card)) {
            return false;
        }
        Card other = (Card) obj;
        return rank == other.rank && suit == other.suit;
    }

    //сравнение объектов по строковому представлению
    public boolean equalsByString(Card cardToCompare) {
        return (toString().equals(cardToCompare.toString()));
    }

    @Override
    public String toString() {
        return String.format("Rank: %s, suit: %s", this.rank, this.suit);
    }


}