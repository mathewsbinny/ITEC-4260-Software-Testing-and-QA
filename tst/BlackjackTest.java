import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class BlackjackTest {

    @Test
    public void testTwoNumCard() {
        Card c = new Card("2", "H");
        Card c2 = new Card("3", "D");

        List<Card> cards = new ArrayList<Card>();
        cards.add(c);
        cards.add(c2);
        Blackjack game = new Blackjack();
        int score = game.calculateHand(cards);
        Assert.assertEquals(5, score);
    }

    @Test
    public void testAceHand() {
        Card c = new Card("A", "H");
        Card c2 = new Card("A", "D");
        List<Card> cards = new ArrayList<>();
        cards.add(c);
        cards.add(c2);
        Blackjack game = new Blackjack();

        int score = game.calculateHand(cards);
        Assert.assertEquals(12, score);
    }

    @Test
    public void testCardValue() {
        for (int i = 2; i <= 10; i++) {
            Card c2 = new Card(String.valueOf(i), "D");
            Assert.assertEquals(i, c2.getValue());
        }
        Card c3 = new Card("J", "D");
        Assert.assertEquals(10, c3.getValue());
        Card c4 = new Card("Q", "D");
        Assert.assertEquals(10, c4.getValue());
        Card c5 = new Card("K", "D");
        Assert.assertEquals(10, c5.getValue());
    }
}

