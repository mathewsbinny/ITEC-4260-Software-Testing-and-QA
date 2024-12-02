public class Card {
    private String suit; // clubs, hearts, diamond, spades
    private String rank; // A, 2, 3, 4, 5, 6, ,7, 8, 9, 10, J, Q, K


    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getValue() {
        try {
            return Integer.parseInt(rank);
        } catch (NumberFormatException e) {
            if (rank.equals("A")) {
                return 11;
            } else {
                return 10;
            }
        }
    }

    public boolean isAce() {
        return rank.equals("A");
    }
}