import java.util.ArrayList;

public class GameHelper {
  public ArrayList<Player> players;
  public int               numPlayers;
  public int               playersTurn;
  public Bank              bank;
  public Board             board;
  public CardsChance       cardsChance;
  public CardsCommunity    cardsCommunity;
  public boolean           debugDice;

  public GameHelper() {
    this.players = new ArrayList<Player>();
    this.debugDice = false;
  }
}
