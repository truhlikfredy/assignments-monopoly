import net.goui.util.MTRandom;

public class PairDice extends Pair<Integer, Integer> {
  public final Integer total;
  public final boolean same;

  public static PairDice getInstance(Integer first, Integer second) {
    return new PairDice(first, second);
  }

  public static PairDice getInstance() {
    MTRandom r1 = new MTRandom();
    return new PairDice((Integer) (Math.abs(r1.nextInt(16)) % 6 + 1), (Integer) (Math.abs(r1.nextInt(16)) % 6 + 1));
  }

  public static PairDice getInstance(boolean askForNumbers) {
    if (askForNumbers) {
      // if debuging is enable you can enter your numbers which will be thrown as regular dice object
      return getInstance(UI.aksNgetIntDice("Give me first number for dice:"),UI.aksNgetIntDice("Give me second number for dice:"));
    } else {
      return getInstance();
    }
  }
  
  private PairDice(Integer first, Integer second) {
    super(first, second);
    this.same = (first == second);
    this.total = first + second;
  }

  public String toString() {
    return (this.total.toString());
  }
}