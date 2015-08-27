import java.util.ArrayList;
import java.util.Collections;

//glen
public class Cards {
  private static final int   CARD_TYPES = 9;
  private ArrayList<Integer> cards;

  public Cards() {
    this.cards = new ArrayList<Integer>();
  }

  public void fillValues() {
    for (int i = 0; i < CARD_TYPES; i++) {
      cards.add(i);
    }
  }

  public void shuffleCards() {
    this.shuffleCards(this.cards.size());
  }

  public void shuffleCards(int count) {
    for (int i = 0; i < count; i++) {
      Collections.shuffle(this.cards);
    }
  }

  public void listCards() {
    for (Integer item : this.cards) {
      StdOut.println(item);
    }
  }
  
  public ArrayList<Integer> getCards() {
    return(this.cards);
  }

  public void pickCard() {

  }

  // maybe can be private, but want to use it in tester 
  public Integer getTopCard() {
    int ret = this.cards.get(0);
    this.cards.remove(0);
    return ret;
  }

  public void putCardOnBottom(Integer cardType) {
    this.cards.add(cardType);
  }
}
