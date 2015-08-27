import java.util.ArrayList;

public class Board {
  public static final Long      PASSING_GO           = 2000000L;

  public static final int       TILE_COUNTY          = 0;
  public static final int       TILE_CARDS_COMMUNITY = 1;
  public static final int       TILE_CARDS_CHANCE    = 2;
  public static final int       TILE_INCOME_TAX      = 3;
  public static final int       TILE_PROVINCE        = 4;
  public static final int       TILE_JAIL            = 5;
  public static final int       TILE_UTILITY         = 6;
  public static final int       TILE_FREE_PARKING    = 7;
  public static final int       TILE_GO_TO_JAIL      = 8;
  public static final int       TILE_STAMP_DUTY      = 9;
  public static final int       TILE_GO              = 10;

  public static final int       GROUP_BROWN          = 0;
  public static final int       GROUP_BLUE           = 1;
  public static final int       GROUP_PINK           = 2;
  public static final int       GROUP_ORANGE         = 3;
  public static final int       GROUP_RED            = 4;
  public static final int       GROUP_YELLOW         = 5;
  public static final int       GROUP_GREEN          = 6;
  public static final int       GROUP_NAVY           = 7;

  private static final String[] COLORS               = { "Brown", "Blue", "Pink", "Orange", "Red", "Yellow", "Green", "Navy" };

  private ArrayList<BoardTile>  tiles;

  public Board() {
    this.tiles = new ArrayList<BoardTile>();

    // Heather
    this.tiles.add(BoardTile.makeGo("Go", "", "Collect €2M as you pass go"));
    this.tiles.add(BoardTile.makeCounty("Westmeath", "C:WH", "", GROUP_BROWN, 600000L, 20000L, 100000L, 300000L, 900000L, 1600000L,
        2500000L, 500000L, 500000L, 300000L));
    this.tiles.add(BoardTile.makeCardsCommunity("Community chest", "Card", "Draw a card"));
    this.tiles.add(BoardTile.makeCounty("Donegal", "C:DL", "", GROUP_BROWN, 600000L, 40000L, 200000L, 600000L, 1800000L, 3200000L,
        4500000L, 500000L, 500000L, 300000L));
    this.tiles.add(BoardTile.makeIncomeTax("Income Tax", "Tax", "Pay €2M or %2 (which is less)"));
    this.tiles.add(BoardTile.makeProvince("Munster", "P:Mu", "", 2000000L, 250000L, 500000L, 1000000L, 2000000L, 1000000L));

    this.tiles.add(BoardTile.makeCounty("Sligo", "C:SO", "", GROUP_BLUE, 1000000L, 60000L, 300000L, 900000L, 2700000L, 4000000L, 5500000L,
        500000L, 500000L, 500000L));
    this.tiles.add(BoardTile.makeCardsChance("Chance card", "Card", "Draw a card"));
    this.tiles.add(BoardTile.makeCounty("Down", "C:DN", "", GROUP_BLUE, 1000000L, 60000L, 300000L, 900000L, 2700000L, 4000000L, 5500000L,
        500000L, 500000L, 500000L));
    this.tiles.add(BoardTile.makeCounty("Laois", "C:LS", "", GROUP_BLUE, 1200000L, 80000L, 400000L, 1000000L, 3000000L, 4500000L, 6000000L,
        500000L, 500000L, 600000L));
    this.tiles.add(BoardTile.makeJail("Jail", "Jail", "Just visiting"));
    this.tiles.add(BoardTile.makeCounty("Tyrone", "C:TE", "", GROUP_PINK, 1400000L, 100000L, 500000L, 1500000L, 4500000L, 6250000L,
        7500000L, 1000000L, 1000000L, 700000L));
    this.tiles.add(BoardTile.makeUtility("Telecoms", "Tel.", "", 1500000L, 40000L, 100000L, 750000L));
    this.tiles.add(BoardTile.makeCounty("Cavan", "C:CN", "", GROUP_PINK, 1400000L, 100000L, 500000L, 1500000L, 4500000L, 6250000L,
        7500000L, 1000000L, 1000000L, 700000L));
    this.tiles.add(BoardTile.makeCounty("Kerry", "C:KY", "", GROUP_PINK, 1600000L, 120000L, 600000L, 1800000L, 5000000L, 7000000L,
        9000000L, 1000000L, 1000000L, 800000L));
    this.tiles.add(BoardTile.makeProvince("Connaught", "P:Co", "", 2000000L, 250000L, 500000L, 1000000L, 2000000L, 1000000L));
    this.tiles.add(BoardTile.makeCounty("Fermanagh", "C:FE", "", GROUP_ORANGE, 1800000L, 140000L, 700000L, 2000000L, 5500000L, 7500000L,
        9500000L, 1000000L, 1000000L, 900000L));
    this.tiles.add(BoardTile.makeCardsCommunity("Community chest", "Card", "Draw a card"));
    this.tiles.add(BoardTile.makeCounty("Leitrim", "C:LM", "", GROUP_ORANGE, 1800000L, 140000L, 700000L, 2000000L, 5500000L, 75000000L,
        9500000L, 1000000L, 1000000L, 900000L));
    this.tiles.add(BoardTile.makeCounty("Galway", "C:G", "", GROUP_ORANGE, 2000000L, 160000L, 800000L, 2200000L, 6000000L, 8000000L,
        10000000L, 1000000L, 1000000L, 1000000L));

    // Dane
    this.tiles.add(BoardTile.makeFreeParking("Free Parking", "Free", "You can stay here for free"));
    this.tiles.add(BoardTile.makeCounty("Meath", "C:MH", "", GROUP_RED, 2200000L, 180000L, 900000L, 2500000L, 7000000L, 8750000L,
        10500000L, 1500000L, 1500000L, 1100000L));
    this.tiles.add(BoardTile.makeCardsChance("Chance card", "Card", "Draw a card"));
    this.tiles.add(BoardTile.makeCounty("Kilkenny", "C:KK", "", GROUP_RED, 2200000L, 180000L, 900000L, 2500000L, 7000000L, 8750000L,
        10500000L, 1500000L, 1500000L, 1100000L));
    this.tiles.add(BoardTile.makeCounty("Offaly", "C:OY", "", GROUP_RED, 2400000L, 200000L, 1000000L, 3000000L, 7500000L, 9255000L,
        11000000L, 1500000L, 1500000L, 1200000L));
    this.tiles.add(BoardTile.makeProvince("Leinster", "P:Le", "", 2000000L, 250000L, 500000L, 1000000L, 2000000L, 1000000L));
    this.tiles.add(BoardTile.makeCounty("Mayo", "C:MO", "", GROUP_YELLOW, 2600000L, 220000L, 1100000L, 3300000L, 8000000L, 9750000L,
        11500000L, 1500000L, 1500000L, 1300000L));
    this.tiles.add(BoardTile.makeCounty("Dublin", "C:D", "", GROUP_YELLOW, 2600000L, 220000L, 1100000L, 3300000L, 8000000L, 9750000L,
        11500000L, 1500000L, 1500000L, 1300000L));
    this.tiles.add(BoardTile.makeUtility("Internet", "Inet", "", 1500000L, 40000L, 100000L, 750000L));
    this.tiles.add(BoardTile.makeCounty("Louth", "C:LH", "", GROUP_YELLOW, 2800000L, 240000L, 3600000L, 8500000L, 10250000L, 12000000L,
        1500000L, 1500000L, 1500000L, 1400000L));
    this.tiles.add(BoardTile.makeGoToJail("Go To Jail", "GO J", "Proceed directly to jail"));
    this.tiles.add(BoardTile.makeCounty("Wexford", "C:WX", "", GROUP_GREEN, 3000000L, 260000L, 1300000L, 3900000L, 9000000L, 11000000L,
        12750000L, 2000000L, 2000000L, 1500000L));
    this.tiles.add(BoardTile.makeCounty("Limerick", "C:L", "", GROUP_GREEN, 3000000L, 260000L, 1300000L, 3900000L, 9000000L, 11000000L,
        12750000L, 2000000L, 2000000L, 1500000L));
    this.tiles.add(BoardTile.makeCardsCommunity("Community Chest", "Card", "Draw a Card"));
    this.tiles.add(BoardTile.makeCounty("Waterford", "C:WD", "", GROUP_GREEN, 3200000L, 280000L, 1500000L, 4500000L, 10000000L, 12000000L,
        14000000L, 2000000L, 2000000L, 1600000L));
    this.tiles.add(BoardTile.makeProvince("Ulster", "P:Ul", "", 2000000L, 250000L, 5000000L, 1000000L, 2000000L, 1000000L));
    this.tiles.add(BoardTile.makeCardsChance("Chance card", "Card", "Draw a card"));

    // anton fix, not blue but navy (i know both of them are blue :-()
    this.tiles.add(BoardTile.makeCounty("Cork", "C:C", "", GROUP_NAVY, 3500000L, 350000L, 1750000L, 5000000L, 11000000L, 13000000L,
        15000000L, 2000000L, 2000000L, 1750000L));
    this.tiles.add(BoardTile.makeStampDuty("Stamp Duty", "Duty", "Pay 1 million"));
    this.tiles.add(BoardTile.makeCounty("Roscommon", "C:RN", "", GROUP_NAVY, 4000000L, 500000L, 2000000L, 6000000L, 14000000L, 17000000L,
        20000000L, 2000000L, 2000000L, 2000000L));

  }

  public ArrayList<BoardTile> getTiles() {
    return this.tiles;
  }

  public int boardSize() {
    return this.tiles.size();
  }

  public int size() {
    return this.boardSize();
  }

  public static String getColor(int index) {
    return COLORS[index];
  }

  public void setTiles(ArrayList<BoardTile> tiles) {
    this.tiles = tiles;
  }

  public BoardTile getTile(int index) {
    return this.tiles.get(index);
  }

  // upgrade tiles, count total worth of player
  // get location of jail tile
  public int getJailPosition() {
    for (int i = 0; i < this.tiles.size(); i++) {
      if (this.tiles.get(i).getType() == TILE_JAIL) return i;
    }
    return 0;
  }

  private int searchTileByName(String name) {
    for (int i = 0; i < this.tiles.size(); i++) {
      if (this.tiles.get(i).getName().toLowerCase().equals(name)) return i;
    }
    return 0;
  }

  public int getWestMeath() {
    return this.searchTileByName("westmeath");
  }

  public int getRoscommon() {
    return this.searchTileByName("roscommon");
  }

  public int getTyrone() {
    return this.searchTileByName("tyrone");
  }

  public int getOffaly() {
    return this.searchTileByName("offaly");
  }

  // options for current player on current tile
  public void tileActions() {
    BoardTile tile = this.tiles.get(Game.getCurrentPlayer().getPosition());
    StdOut.println(Game.getCurrentPlayer() + " landed on tile #" + Game.getCurrentPlayer().getPosition() + ": " + tile);
    UI.pause();

    switch (tile.getType()) {
      //anton
      case TILE_COUNTY:

        if (tile.isOwned()) {
          UI.displayCounty(tile);

          if (tile.getOwner() == Game.getCurrentPlayer()) {
            // TODO test it more
            this.tileUpgradeOptions(tile);
          } else {
            Game.getCurrentPlayer().payToPlayer(tile.getOwner(), tile.getCurrentRent());
          }
        } else {
          this.offerTileForSale(tile);
        }
        break;

      //heather
      case TILE_CARDS_COMMUNITY:
        Game.getCardsCommunity().pickCard();
        break;

      //heather
      case TILE_CARDS_CHANCE:
        Game.getCardsChance().pickCard();
        break;

      //dane:
      case TILE_INCOME_TAX:
        Long tenPercent = Game.getCurrentPlayer().totalNetWorth() / 10L;
        Long owe = 0L;

        // pay value (depending which is less)
        if (tenPercent < 2000000L) {
          owe = Game.getBank().returnMoneyToBank(tenPercent);
        } else {
          owe = Game.getBank().returnMoneyToBank(2000000L);
        }

        while (owe > 0) {
          Game.getCurrentPlayer().sellProperty();
          owe = Game.getBank().returnMoneyToBank(owe);
        }
        break;

      //anton
      case TILE_PROVINCE:
        // TODO test it more
        UI.displayProvince(tile);
        if (tile.isOwned()) {
          if (tile.getOwner() != Game.getCurrentPlayer()) {
            Game.getCurrentPlayer().payToPlayer(tile.getOwner(),tile.getCurrentRent());
          }
        } else {
          this.offerTileForSale(tile);
        }

        break;

      //glen
      case TILE_JAIL:
        StdOut.println(tile.getDescription());
        break;

      //anton
      case TILE_UTILITY:
        // TODO test it more 
        UI.displayUtility(tile);

        if (tile.isOwned()) {
          //get rent from player, but not from you
          if (tile.getOwner() != Game.getCurrentPlayer()) {
            // you need to throw dice and multiply that value with current rent of
            // this tile
            PairDice dice = PairDice.getInstance(Game.getDebugDice());
          
            //it will ask for selling properties if he has not enough
            Game.getCurrentPlayer().payToPlayer(tile.getOwner(), tile.getCurrentRent() * dice.total);
          }
        } else {
          this.offerTileForSale(tile);
        }
        break;

      //glen
      case TILE_FREE_PARKING:
        StdOut.println("You are in Free Parking");
        break;

      //glen
      case TILE_GO_TO_JAIL:
        Game.getCurrentPlayer().setJailTime(3);
        break;

      //dane
      case TILE_STAMP_DUTY:
        Long oweDuty = Game.getBank().returnMoneyToBank(1000000L);

        while (oweDuty > 0) {
          Game.getCurrentPlayer().sellProperty();
          oweDuty = Game.getBank().returnMoneyToBank(oweDuty);
        }

        break;

      //glen
      case TILE_GO:
        // does something happen when you step on GO?
        // when you pass GO you get money, but that Player.step does for us
        // already
        // Game.getCurrentPlayer().
        break;

      default:
        StdOut.println("Error! Tile type " + tile.getType() + " not implemented yet.");
        // UI.pause();
        break;
    }
  }

  // doesn't need to be public
  private void offerTileForSale(BoardTile tile) {
    Player currentPlayer = Game.getCurrentPlayer();
    Long cash = currentPlayer.getCash();

    UI.displayMyProperties();
    StdOut.println();

    switch (tile.getType()) {
      
      case Board.TILE_COUNTY:
        UI.displayCounty(tile);
        break;

      case Board.TILE_PROVINCE:
        UI.displayProvince(tile);
        break;

      case Board.TILE_UTILITY:
        UI.displayUtility(tile);
        break;
      
      default:
        StdOut.println("ERROR!!! You should get here, bad tile type: #"+tile.getType());
        break;        
    }

    if ((cash > tile.getPrice())
        && UI.askNgetBoolean("Nobody owns it yet. Do you want to buy it? (you have " + Bank.moneyEuro(cash) + ")")) {

      //buy it for yourself
      tile.setOwned(true);
      tile.setOwner(currentPlayer);
      currentPlayer.giveMoney(tile.getPrice());

    } else {
      //offer it for auction
      StdOut.println("You refused to buy it (or can't afford it), it's going into auction.");

      Pair<Player, Long> winner = Game.getBank().auction(tile.getName(), Game.getAllPlayingPlayers());

      //in case at least somebody was interested and somebody won transfer the owner 
      if (winner.first != null) {
        tile.setOwned(true);
        tile.setOwner(winner.first);
        winner.first.giveMoney(winner.second);
      }
    }
    
  }
  
  private void tileUpgradeOptions(BoardTile tile) {
    if (tile.ownAllofThisColor()) {
      if (UI.askNgetBoolean("Do you want upgrade this tile?")) {
        
        //get all tiles of this color by this owner except the one I'm standing on
        ArrayList<BoardTile> tiles = BoardSearch.all().filterByOwner(tile.getOwner()).filterByColor(tile.getGroup()).getTiles();
        tiles.remove(tile);
        
        // detect if you are higher than others
        boolean lowest = true;
        for (BoardTile mineTile : tiles) {
          if (tile.getUpgradeLevel() > mineTile.getUpgradeLevel()) {
            lowest = false;
          }
        }
        
        if (lowest) {
          // all other tiles matched your level so you can upgrade
          if (tile.getUpgradeLevel()>4) {
            
            //have hotel already
            StdOut.println("Sorry, but you reached max upgrade possible.");
            
          } else if (tile.getUpgradeLevel()==4) {
            
            // to hotel
            if (Game.getBank().getAvailableHotels() > 0) {
              tile.incUpgradeLevel();
              Game.getBank().upgradeHotelGet();
            } else {
              StdOut.println("Sorry, bank has no hotels avaiable.");              
            }
            
          } else {
            
            // to more houses
            if (Game.getBank().getAvailableHouses() > 0) {
              tile.incUpgradeLevel();
              Game.getBank().upgradeHouseGet();
            } else {
              StdOut.println("Sorry, bank has no houses avaiable.");              
            }
            
          }          
        } else {
          
          //some tiles are much smaller level and need be upgraded first
          StdOut.println("Sorry, you have to first upgrade other tiles of this colour to upgrade this one.");
          UI.displayMyProperties();
          UI.pause();
        }
        
      }      
    } else {
      StdOut.println("You don't own all tiles of this colour");
    }
    
  }

}
