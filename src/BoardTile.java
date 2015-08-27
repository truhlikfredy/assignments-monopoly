/**
 * 
 * Contains all possible atributes for each single tile on the Board
 * 
 * @author Anton
 * @version 2
 * 
 */

public class BoardTile {
  private String  name;
  private String  shortName;
  private String  description;
  private int     type;
  /*
   * group is actually color for the tile, first I wanted to bundle it together
   * with
   */
  private int     group;
  private Long    price;
  private Long    rent;
  private Long    rent1;
  private Long    rent2;
  private Long    rent3;
  private Long    rent4;
  private Long    rentHotel;
  private Long    costHouse;
  private Long    costHotel;
  private Long    mortage;
  private boolean buyable;
  private boolean owned;
  private boolean mortaged;
  private Player  owner;
  private int     upgradeLevel;

  // private constructor, or public static ones call this to create BoardTile
  // instance

  private BoardTile(String name, String shortName, String description, int type, int group, Long price, Long rent,
      Long rent1, Long rent2, Long rent3, Long rent4, Long rentHotel, Long costHouse, Long costHotel, Long mortage,
      boolean buyable, boolean owned, Player owner, int upgradeLevel) {
    this.name = name;
    
    //if case it's empty just copy regular name
    if (shortName.length()>0) {
      this.shortName = shortName;
    } else {
      this.shortName = name;
    }
    
    this.description = description;
    this.type = type;
    this.group = group;
    this.price = price;
    this.rent = rent;
    this.rent1 = rent1;
    this.rent2 = rent2;
    this.rent3 = rent3;
    this.rent4 = rent4;
    this.rentHotel = rentHotel;
    this.costHouse = costHouse;
    this.costHotel = costHotel;
    this.mortage = mortage;
    this.buyable = buyable;
    this.mortaged = false;
    this.owned = owned;
    this.owner = owner;
    this.upgradeLevel = upgradeLevel;
  }

  /*
   * the static public semi-constructors
   */

  /**
   * Creates tile of GO type
   * 
   * @param name
   *          Name of the tile
   * @param shortName
   *          Abrevation of the name
   * @param description
   *          Short description for the tile
   * @return Returns instance of BoardTile class
   */
  public static BoardTile makeGo(String name, String shortName, String description) {
    return new BoardTile(name, shortName, description, Board.TILE_GO, 0, 0L, 2000000L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, false,
        false, new Player(), 0);
  }

  /**
   * Creates tile of COUNTY type
   * 
   * @param name
   *          Name of the tile
   * @param shortName
   *          Abrevation of the name
   * @param description
   *          Short description for the tile
   * @param group
   *          Color type group
   * @param price
   *          Price to buy it
   * @param rent
   *          Rent for non-upgraded tile
   * @param rent1
   *          Rent for tile with 1 house
   * @param rent2
   *          Rent for tile with 2 houses
   * @param rent3
   *          Rent for tile with 3 houses
   * @param rent4
   *          Rent for tile with 4 houses
   * @param rentHotel
   *          Rent for tile with Hotel upgrade
   * @param costHouse
   *          How much upgrading by 1 house cost
   * @param costHotel
   *          How much upgrading to a hotel cost
   * @param mortage
   *          Mortage value
   * @return Returns instance of BoardTile class
   */
  public static BoardTile makeCounty(String name, String shortName, String description, int group, Long price,
      Long rent, Long rent1, Long rent2, Long rent3, Long rent4, Long rentHotel, Long costHouse, Long costHotel,
      Long mortage) {
    return new BoardTile(name, shortName, description, Board.TILE_COUNTY, group, price, rent, rent1, rent2, rent3, rent4,
        rentHotel, costHouse, costHotel, mortage, true, false, new Player(), 0);

  }

  /**
   * Creates tile of "Take community card" type
   * 
   * @param name
   *          Name of the tile
   * @param shortName
   *          Abrevation of the name
   * @param description
   *          Short description for the tile
   * @return Returns instance of BoardTile class
   */
  public static BoardTile makeCardsCommunity(String name, String shortName, String description) {
    return new BoardTile(name, shortName, description, Board.TILE_CARDS_COMMUNITY, 0, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L,
        false, false, new Player(), 0);
  }

  /**
   * Creates tile of "Take chance card" type
   * 
   * @param name
   *          Name of the tile
   * @param shortName
   *          Abrevation of the name
   * @param description
   *          Short description for the tile
   * @return Returns instance of BoardTile class
   */
  public static BoardTile makeCardsChance(String name, String shortName, String description) {
    return new BoardTile(name, shortName, description, Board.TILE_CARDS_CHANCE, 0, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, false,
        false, new Player(), 0);
  }

  /**
   * Creates tile of "Income tax" type
   * 
   * @param name
   *          Name of the tile
   * @param shortName
   *          Abrevation of the name
   * @param description
   *          Short description for the tile
   * @return Returns instance of BoardTile class
   */
  public static BoardTile makeIncomeTax(String name, String shortName, String description) {
    return new BoardTile(name, shortName, description, Board.TILE_INCOME_TAX, 0, 0L, 2000000L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L,
        false, false, new Player(), 0);
  }

  /**
   * Creates tile of "Income tax" type
   * 
   * @param name
   *          Name of the tile
   * @param shortName
   *          Abrevation of the name
   * @param description
   *          Short description for the tile
   * @param price
   * @param province1
   * @param province2
   * @param province3
   * @param province4
   * @param mortage
   * @return Returns instance of BoardTile class
   */
  public static BoardTile makeProvince(String name, String shortName, String description, Long price, Long province1,
      Long province2, Long province3, Long province4, Long mortage) {
    return new BoardTile(name, shortName, description, Board.TILE_PROVINCE, 0, price, 0L, province1, province2, province3,
        province4, 0L, 0L, 0L, mortage, true, false, new Player(), 0);
  }

  /**
   * Creates tile of jail type
   * 
   * @param name
   *          Name of the tile
   * @param shortName
   *          Abrevation of the name
   * @param description
   *          Short description for the tile
   * @return Returns instance of BoardTile class
   */
  public static BoardTile makeJail(String name, String shortName, String description) {
    return new BoardTile(name, shortName, description, Board.TILE_JAIL, 0, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, false, false,
        new Player(), 0);
  }

  /**
   * Creates tile of utility type
   * 
   * @param name
   *          Name of the tile
   * @param shortName
   *          Abrevation of the name
   * @param description
   *          Short description for the tile
   * @param price
   * @param rent
   * @param rentBoth
   * @param mortage
   * @return Returns instance of BoardTile class
   */
  public static BoardTile makeUtility(String name, String shortName, String description, Long price, Long rent, Long rentBoth,
      Long mortage) {
    return new BoardTile(name, shortName, description, Board.TILE_UTILITY, 0, price, 0L, rent, rentBoth, 0L, 0L, 0L, 0L, 0L,
        mortage, true, false, new Player(), 0);
  }

  /**
   * Creates tile of free parking type
   * 
   * @param name
   *          Name of the tile
   * @param shortName
   *          Abrevation of the name
   * @param description
   *          Short description for the tile
   * @return Returns instance of BoardTile class
   */
  public static BoardTile makeFreeParking(String name, String shortName, String description) {
    return new BoardTile(name, shortName, description, Board.TILE_FREE_PARKING, 0, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, false,
        false, new Player(), 0);
  }

  /**
   * Creates tile of "Income tax" type
   * 
   * @param name
   *          Name of the tile
   * @param shortName
   *          Abrevation of the name
   * @param description
   *          Short description for the tile
   * @param price
   * @param province1
   * @param province2
   * @param province3
   * @param province4
   * @param mortage
   * @return Returns instance of BoardTile class
   */
  public static BoardTile makeGoToJail(String name, String shortName, String description) {
    return new BoardTile(name, shortName, description, Board.TILE_GO_TO_JAIL, 0, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, false,
        false, new Player(), 0);
  }

  /**
   * Creates tile of stamp duty type
   * 
   * @param name
   *          Name of the tile
   * @param shortName
   *          Abrevation of the name
   * @param description
   *          Short description for the tile
   * @return Returns instance of BoardTile class
   */
  public static BoardTile makeStampDuty(String name, String shortName, String description) {
    return new BoardTile(name, shortName, description, Board.TILE_STAMP_DUTY, 0, 0L, 1000000L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L,
        false, false, new Player(), 0);
  }

  /*
   * simple getters & setters
   */

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name
   *          the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the shortName
   */
  public String getShortName() {
    return shortName;
  }

  /**
   * @param shortName
   *          the shortName to set
   */
  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description
   *          the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @return the type
   */
  public int getType() {
    return type;
  }

  /**
   * @param type
   *          the type to set
   */
  public void setType(int type) {
    this.type = type;
  }

  /**
   * @return the group
   */
  public int getGroup() {
    return group;
  }

  /**
   * @param group
   *          the group to set
   */
  public void setGroup(int group) {
    this.group = group;
  }

  /**
   * @return the price
   */
  public Long getPrice() {
    return price;
  }

  /**
   * @param price
   *          the price to set
   */
  public void setPrice(Long price) {
    this.price = price;
  }

  /**
   * @return the rent
   */
  public Long getRent() {
    return rent;
  }
  
  /**
   * @param rent
   *          the rent to set
   */
  public void setRent(Long rent) {
    this.rent = rent;
  }  

  /**
   * @return the rent1
   */
  public Long getRent1() {
    return rent1;
  }

  /**
   * @param rent1
   *          the rent1 to set
   */
  public void setRent1(Long rent1) {
    this.rent1 = rent1;
  }

  /**
   * @return the rent2
   */
  public Long getRent2() {
    return rent2;
  }

  /**
   * @param rent2
   *          the rent2 to set
   */
  public void setRent2(Long rent2) {
    this.rent2 = rent2;
  }

  /**
   * @return the rent3
   */
  public Long getRent3() {
    return rent3;
  }

  /**
   * @param rent3
   *          the rent3 to set
   */
  public void setRent3(Long rent3) {
    this.rent3 = rent3;
  }

  /**
   * @return the rent4
   */
  public Long getRent4() {
    return rent4;
  }

  /**
   * @param rent4
   *          the rent4 to set
   */
  public void setRent4(Long rent4) {
    this.rent4 = rent4;
  }

  /**
   * @return the rentHotel
   */
  public Long getRentHotel() {
    return rentHotel;
  }

  /**
   * @param rentHotel
   *          the rentHotel to set
   */
  public void setRentHotel(Long rentHotel) {
    this.rentHotel = rentHotel;
  }

  /**
   * @return the costHouse
   */
  public Long getCostHouse() {
    return costHouse;
  }

  /**
   * @param costHouse
   *          the costHouse to set
   */
  public void setCostHouse(Long costHouse) {
    this.costHouse = costHouse;
  }

  /**
   * @return the costHotel
   */
  public Long getCostHotel() {
    return costHotel;
  }

  /**
   * @param costHotel
   *          the costHotel to set
   */
  public void setCostHotel(Long costHotel) {
    this.costHotel = costHotel;
  }

  /**
   * @return the mortage
   */
  public Long getMortage() {
    return mortage;
  }

  /**
   * @param mortage
   *          the mortage to set
   */
  public void setMortage(Long mortage) {
    this.mortage = mortage;
  }

  /**
   * @return the buyable
   */
  public boolean isBuyable() {
    return buyable;
  }

  /**
   * @param buyable
   *          the buyable to set
   */
  public void setBuyable(boolean buyable) {
    this.buyable = buyable;
  }

  /**
   * @return the owned
   */
  public boolean isOwned() {
    return owned;
  }

  /**
   * @param owned
   *          the owned to set
   */
  public void setOwned(boolean owned) {
    this.owned = owned;
  }

  /**
   * @return the mortaged
   */
  public boolean isMortaged() {
    return mortaged;
  }

  /**
   * @param mortaged
   *          the mortaged to set
   */
  public void setMortaged(boolean mortaged) {
    this.mortaged = mortaged;
  }

  /**
   * @return the owner
   */
  public Player getOwner() {
    return owner;
  }

  /**
   * @param owner
   *          the owner to set
   */
  public void setOwner(Player owner) {
    this.owner = owner;
  }

  /**
   * @return the upgradeLevel
   */
  public int getUpgradeLevel() {
    return upgradeLevel;
  }

  /**
   * @param upgradeLevel
   *          the upgradeLevel to set
   */
  public void setUpgradeLevel(int upgradeLevel) {
    this.upgradeLevel = upgradeLevel;
  }
  
  /**
   * Increments upgrade level of this tile
   */
  public void incUpgradeLevel() {
    StdOut.println(this + " was upgraded from " + this.upgradeLevel + " to " + (this.upgradeLevel+1));
    this.upgradeLevel++;
    if (this.upgradeLevel>4) {
      this.owner.giveMoney(this.costHotel);      
    } else {
      this.owner.giveMoney(this.costHouse);
    }
  }

  /**
   * Decrements upgrade level of this tile
   */
  public void decUpgradeLevel() {
    StdOut.println(this + " was downgraded from " + this.upgradeLevel + " to " + (this.upgradeLevel-1));
    if (this.upgradeLevel>4) {
      this.owner.getMoney(this.costHotel);
    } else {
      this.owner.getMoney(this.costHouse);
    }
    this.upgradeLevel--;
  }
  
  /*
   * other methods
   */

  public Long getValueOfUpgradesOnly() {
    if (this.owned && !this.mortaged && this.type == Board.TILE_COUNTY) {
      // upgrade price for hotel and house is the same anyway and hotel is
      // like just 5th upgrade of a house
      return this.upgradeLevel * this.costHouse;
    } else {
      return 0L;
    }
  }

  /**
   * Calculates value for this current tile, upgrades are considered as well
   * 
   * @return the final value of this tile
   */

  public Long getValue() {
    Long ret = 0L;
    if (this.owned) {
      // for all including province and utility it's just a price/mortage
      if (this.mortaged) {
        ret += this.mortage;
      } else {
        ret += this.price;
      }
      // for county we include the upgrades as well
      if (this.type == Board.TILE_COUNTY) {
        ret += this.getValueOfUpgradesOnly();
      }
    }
    return ret;
  }

  /**
   * Strips down all upgrades from this property
   */
  public void downgradeToBasic() {
    if (owned) {
      // if there will any other type of tile, his upgrades will be 0 so it
      // won't change anything
      if (upgradeLevel > 0) {
        StdOut.println("Selling all " + owner + "'s upgrades of " + this.name + " to bank.");
        this.owner.getMoney(this.getValueOfUpgradesOnly() / 2L);

        if (this.upgradeLevel > 4) {
          Game.getBank().upgradeHotelReturn(1);
          this.upgradeLevel = 4;
        }
        Game.getBank().upgradeHotelReturn(this.upgradeLevel);
        this.upgradeLevel = 0;
      }
    }
  }
  
  /**
   * Mortages this property to the bank
   */
  public void mortageToBank() {
    if (this.owned) {
      this.downgradeToBasic();

      StdOut.println("Getting mortgage for " + owner + "'s " + this.name + ".");
      this.mortaged = true;
      this.owner.getMoney(this.mortage);
    }
  }
  
  /**
   * Get the price for mortage if you want get back this property.
   * @return Price to pay
   */
  public Long mortageBuyOfPrice() {
    return Bank.roundMoney((this.mortage/10L) + this.mortage);    
  }

  /**
   * Get the mortaged property back from the bank 
   */
  public void mortageFromBank() {
    if (this.owned && this.mortaged) {
      StdOut.println("Getting back mortgage for " + owner + "'s " + this.name + ".");
      this.mortaged = true;
      this.owner.giveMoney(mortageBuyOfPrice());
    }
  }
  
  /**
   * Prints out verbose information about changin owner and then override the
   * field
   * 
   * @param newOwner
   *          the reciever of this field
   */

  public void changeOwner(Player newOwner) {
    // before changin owner, return all upgrades
    this.downgradeToBasic();
    StdOut.println("Tile " + this + " owned by " + this.owner + " was transferred to " + newOwner);
    this.owned = true; // just in case
    this.owner = newOwner;
  }

  /**
   * Checks if owner of this tile owns all other tiles of the same color
   * 
   * @return true or false if owner owns all of them
   */
  public boolean ownAllofThisColor() {
    if (this.owned) {
      // if number of tiles of given type and color is the same as number of
      // tiles owned by owner of this tile is same, then it means you own all
      // avaiable tiles
      if (BoardSearch.all().filterByType(this.type).filterByColor(this.group).filterByOwner(this.owner).size() == BoardSearch
          .all().filterByType(this.type).filterByColor(this.group).size()) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  /**
   * Checks if owner of this tile didn't upgraded any other properties
   * 
   * @return true or false if owner owns all of them
   */
  public boolean ownAllofThisColorAreNotUpgraded() {
    if (this.owned) {
      for (BoardTile tile : BoardSearch.all().filterByType(this.type).filterByColor(this.group).filterByOwner(this.owner).getTiles()) {
        if (tile.getUpgradeLevel() > 0) return false;
      } 
      return true;
    } else {
      return false;
    }
  }

  /**
   * Calculate current rent for this tile, considering type and upgrade and what
   * other tiles you own
   * 
   * @return Rent to be paid for this current tile
   */
  public Long getCurrentRent() {
    // if the property is mortaged, nobody has to pay rent on it
    if (this.mortaged) return 0L;
    
    switch (this.upgradeLevel) {
      case 0:

        switch (this.type) {

          case Board.TILE_COUNTY:
            // for county pay double if you own all, but they are not upgraded
            if (this.ownAllofThisColor()) {
              return this.rent * 2L;
            } else {
              return this.rent;
            }

          case Board.TILE_UTILITY:
            // if you own 2 you pay rent2, else you should pay rent1
            if (BoardSearch.all().filterByType(this.type).filterByOwner(this.owner).size() > 1) {
              return this.rent2;
            } else {
              return this.rent1;
            }

          case Board.TILE_PROVINCE:

            switch (BoardSearch.all().filterByType(this.type).filterByOwner(this.owner).size()) {
              case 1:
                return this.rent1;

              case 2:
                return this.rent2;

              case 3:
                return this.rent3;

              case 4:
                return this.rent4;

              default:
                // fallback
                StdOut.print("ERROR! Asking for wrong rent.");
                return this.rent1;
            }

          default:
            // fallback
            StdOut.print("ERROR! Asking for wrong rent.");
            return this.rent;
        }

      case 1:
        return this.rent1;

      case 2:
        return this.rent2;

      case 3:
        return this.rent3;

      case 4:
        return this.rent4;

      case 5:
        return this.rentHotel;

      default:
        return this.rent;
    }
  }
  
  /**
   * Simple toString returning name of the tile
   * 
   * @return returns name of this tile
   */
  public String toString() {
    return this.name;
  }

}
