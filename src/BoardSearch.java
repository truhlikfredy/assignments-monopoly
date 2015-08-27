import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 * @author Anton
 *
 */
//chainable class
public class BoardSearch {
  ArrayList<BoardTile> tiles;

  // make clone of the collection so we can remove from list freely
  private BoardSearch(ArrayList<BoardTile> originalTiles) {
    this.tiles = new ArrayList<BoardTile>();
    for (BoardTile tile : originalTiles) {
      this.tiles.add(tile);
    }
  }

  public static BoardSearch all() {
    return new BoardSearch(Game.getBoard().getTiles());
  }

  public static BoardSearch all(ArrayList<BoardTile> originalTiles) {
    return new BoardSearch(originalTiles);
  }

  // universal search and without caution, badly formed fieldName could make
  // problems at runtime, so it's private. maybe it's nasty workaround but
  // makes all other classes using so much simpler.
  private BoardSearch filterBy(String fieldName, Object needle) {
    Iterator<BoardTile> tiles = this.tiles.iterator();
    while (tiles.hasNext()) {
      BoardTile tile = tiles.next();
      try {
        Field field = tile.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        // if (!field.equals(needle)) tiles.remove();
        try {
          if (!field.get(tile).equals(needle)) tiles.remove();
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      } catch (NoSuchFieldException e) {
        e.printStackTrace();
      }
    }
    return this;
  }

  public BoardSearch filterByColor(int color) {
    return filterBy("group", color);
  }

  public BoardSearch filterByOwner(Player owner) {
    return filterBy("owner", owner);
  }

  public BoardSearch filterByType(int type) {
    return filterBy("type", type);
  }
  
  public BoardSearch filterByMortaged(boolean mortaged) {
    return filterBy("mortaged", mortaged);
  }

  public int size() {
    return this.tiles.size();
  }

  public ArrayList<BoardTile> getTiles() {
    return this.tiles;
  }

  public String toString() {
    String ret = "";
    for (BoardTile tile : this.tiles) {
      ret = ret + "Name: " + tile + ", Type: " + tile.getType() + ", Colour: " + Board.getColor(tile.getGroup()) + ", Owner: "
          + tile.getOwner() + "\n";
    }
    return ret;
  }

  public ArrayList<String> toMenuItems() {
    ArrayList<String> ret = new ArrayList<String>();

    for (BoardTile tile : this.tiles) {
      if (tile.getType() == Board.TILE_COUNTY) {
        ret.add(tile + " (County), Color: " + Board.getColor(tile.getGroup()) + ", Upgrade(s) " + tile.getUpgradeLevel()
            + ", Current rent: " + Bank.moneyEuro(tile.getCurrentRent()));
      } else if (tile.getType() == Board.TILE_UTILITY) {
        ret.add(tile.getName()+ " (Utility), Current rent: " + Bank.moneyEuro(tile.getCurrentRent()) );
      } else {
        ret.add(tile.getName()+ " (Province), Current rent: " + Bank.moneyEuro(tile.getCurrentRent()) );        
      }
    }

    return ret;
  }
}
