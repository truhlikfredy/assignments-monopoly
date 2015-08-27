
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestCards {
  Cards cards;

  @Before
  public void setUp() throws Exception {
    cards = new Cards();
  }

  @Test
  public void testFilling() {
    cards.fillValues();

    assertEquals(0, cards.getCards().get(0).intValue());
    assertEquals(1, cards.getCards().get(1).intValue());
  }

  @Test
  public void testShuffling() {
    int count = 0;
    cards.fillValues();
    while (cards.getCards().get(0) == 0 && count < 4) {
      cards.shuffleCards();
      count++;
    }
    assertNotEquals(0, cards.getCards().get(0).intValue());
  }

  @Test
  public void testPushBottom() {
    cards.fillValues();
    Integer card = cards.getTopCard();
    assertNotEquals(0, cards.getCards().get(0).intValue());
    cards.putCardOnBottom(card);
    assertEquals(0, cards.getCards().get(cards.getCards().size()-1).intValue());
  }
}