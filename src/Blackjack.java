import java.util.List;

public class Blackjack {


    public int calculateHand(List<Card> cards) {
        int score = 0;
        for (Card card : cards) {
            if (card.isAce() && score < 21) {  // what do we do if card is an "Ace"
                score = score + 11;
            }

        }
        return score;
    }


}