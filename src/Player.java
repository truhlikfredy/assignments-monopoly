import java.util.ArrayList;

//glen
public class Player {
  private String  name;
  private int     position;
  private boolean playing;
  private int     jailTime;
  private Long    cash;
  private ArrayList<Pair<Cards, Integer>> cards;
  //for auction functionality
  private Long    offer;

  public Player(String name) {
    this.name = name;
    this.position = 0;
    this.playing = true;
    this.jailTime = 0;
    this.cash = 15000000L;
    this.cards = new ArrayList<Pair<Cards, Integer>>();
  }
  
  /*
  public Player() {
    super("");
  }*/  
  //if we need empty player
  public Player() {
    this.name = "";
    this.position = 0;
    this.playing = false;
    this.jailTime = 0;
    this.cash = 0L;
    this.cards = new ArrayList<Pair<Cards, Integer>>();
  }

  public String getName() {
    return this.name;
  }

  public boolean isPlaying() {
    return this.playing;
  }

  //Includes stepping forwards and backwards
  public void step(int steps) {
    // make steps, check if you passed go, collect money
    // call tileActions();
	  
    this.position += steps;
    if (this.position < 0)
    {
     this.position += Game.getBoard().boardSize();
	  }
    //anton fix from > to >= if it's equal to 40 it needs to be set to 0 already
    if (this.position >= Game.getBoard().boardSize())
	  {
		  this.position = this.position % Game.getBoard().boardSize();
		  StdOut.println("Passed GO. Collect €2M.");
		  Game.getBank().getMoneyFromBank(2000000L);
	  }
    
    UI.displayBoard();
    Game.getBoard().tileActions();
  }

  // this players cash will increase by amount
  public void getMoney(Long amount) {
	  this.cash += amount;
    StdOut.println(this.name + " got "+ Bank.moneyEuro(amount)+ " (current cash "+Bank.moneyEuro(this.cash)+")");
  }

  // decrease this players cash by amount
  // return if you owe some resting standing (you didn't pay fully)
  public Long giveMoney(Long amount) {
    if (this.cash > amount) {
    	this.cash-=amount;
      StdOut.println(this.name + " paid "+ Bank.moneyEuro(amount)+ " (current cash "+Bank.moneyEuro(this.cash)+")");
    	return 0L;
    } else {
    	Long owed = amount - this.cash;

    	StdOut.println(this.name + " paid "+ Bank.moneyEuro(this.cash)+ " (owed "+Bank.moneyEuro(owed)+")");
    	this.cash=0L;
    	return owed;
    }
  }

  public void payToPlayer(Player payee, Long amount) {
    // if calling giveMoney will be anything but not 0 then all belongings go to payee
	  Long owed = this.giveMoney(amount);
	  payee.getMoney(amount-owed);
	  if (owed>0) {
	    StdOut.println(this.name+" owes to "+payee.name+" "+Bank.moneyEuro(owed));
	    if (this==Game.getCurrentPlayer()) {
	      // the moment player gave up it will stop recursing
	      if (this.playing) {
	        //allows player to sell something and then calls itself, 
	        //so it won't leave till it's paid ot player gave up 
	        this.sellProperty();
	        this.payToPlayer(payee, owed);
	      }
	    } else {
	      for (BoardTile tile : BoardSearch.all().filterByOwner(this).getTiles()) {
	        tile.changeOwner(payee);
	      }
	      //give payee money you got from selling of upgrades
	      StdOut.println(payee + " got "+Bank.moneyEuro(this.cash)+" from "+this.name+"'s upgrades.");
	      this.giveMoney(this.cash);
	      this.cash=0L;
	    }
	  }
  }
  
  //add to collection of owned cards
  public void receiveCard(Cards stack, Integer type) {
    this.cards.add(new Pair<Cards, Integer>(stack, type));
  }

  public Pair<Cards, Integer> seeTopCard() {
    return this.cards.get(0);
  }
  
  //will decrement collection and return it to stack from which it was taken
  public void useCard() {
    StdOut.println("Using the card.");
    this.cards.get(0).first.putCardOnBottom(this.cards.get(0).second);
    this.cards.remove(0);
  }
  
  public void transferCardJail(Player payee) {
    Pair<Cards, Integer> top = this.seeTopCard();
    this.cards.remove(0);
    payee.receiveCard(top.first,top.second);
  }
  
  public int getJailTime() {
    return this.jailTime;
  }
  
  public void setJailTime(int time) {
    if (this.jailTime < time) {
      // if the time is increasing from old value, 
      // then you have bean thrown into jail
      this.jumpTo(Game.getBoard().getJailPosition());
    }
    this.jailTime = time;
  }
  
  public int getPosition() {
    return this.position;
  }

  public void jumpTo(int position) {
    //just bondaries safeguarding
    position = position % Game.getBoard().size();
    
    StdOut.println(this.name+" jumped to tile #"+position+" "+Game.getBoard().getTile(position));
    
	  this.position = position;
  }
  
  public void setPosition(int position) {
	  this.jumpTo(position);
  }
  
  public ArrayList<Pair<Cards, Integer>> getCards() {
    return this.cards;
  }
  
  public int getCardsSize() {
    return this.cards.size();
  }
  
  public void setCardJail(ArrayList<Pair<Cards, Integer>> cards) {
	  this.cards = cards;
  }
  
  public Long getCash() {
    return this.cash;
  }
  
  public void setCash(Long value) {
    this.cash = value;
  }
  
  /**
   * This player will stop playing<br>
   * <br>
   * NOTE:Check before you use giveUp that all properties transfers are made corectly.
   * Because this method will give everything to the bank!!!
   */
  public void giveUp() {
    StdOut.println();
    StdOut.println(this.name+" finished with playing, returning everything back to bank:");
    for(BoardTile tile:BoardSearch.all().filterByOwner(this).getTiles()) {
      //not all necessary but it's neat to clean up after he is done and politely return items back to bank
      tile.downgradeToBasic();
  
      StdOut.println(this.name+" returns "+tile+" to the bank.");
      tile.setOwned(false);
      tile.setOwner(new Player());
    }
    //not really required, but for neatness 
    if (this.cash > 0L) {
      StdOut.println(this.name+" returns "+Bank.moneyEuro(this.cash)+" to the bank.");
      this.cash=0L;
    }
    this.playing=false;
  }
  
  public boolean didYouWon() {
    int playing=0;
    for (Player player:Game.getPlayers()) {
      if (player.isPlaying()) playing++;
    }
    if (playing>1) {
      return false;
    } else {
      if (this.playing) {
        return true;
      } else {
        return false;
      }
    }
  }
  
  public Long getOffer() {
    return this.offer;
  }
  
  public void setOffer(Long offer) {
    this.offer = offer;
  }

  public Long propertiesWorth() {
    Long total = 0L;
    for (BoardTile tile : BoardSearch.all().filterByOwner(this).getTiles()) {
      total += tile.getValue();
    }
    return total;
  }
  
  public Long totalNetWorth() {
    return this.propertiesWorth() + this.cash;
  }
  
  public String toString() {
    return this.name;
  }
 
  public void sellProperty() {
    //TODO test it more
    int option = this.sellPropertyMenu();
    
    while (option != this.sellPropertyItems().size()) {
      if (option== this.sellPropertyItems().size()-1) {
        //give up
        if (UI.askNgetBoolean("Do you really want give up?")) {
          this.giveUp();
        }
      } else {
        //sellected property
        BoardTile sellingTile = BoardSearch.all().filterByOwner(this).getTiles().get(option);

        if (UI.askNgetBoolean("Are you sure you want mortage " + sellingTile + "?")) {
          sellingTile.mortageToBank();
        }
      }
    }
    
  }

  //doesn't have to be public
  private int sellPropertyMenu() {
    return UI.displayMenuNGetChoice(Game.getCurrentPlayer()+"'s properties", 
        this.sellPropertyItems(), true, true);
  }
  
  private ArrayList<String> sellPropertyItems() {
    ArrayList<String> items = BoardSearch.all().filterByOwner(this).filterByMortaged(false).toMenuItems();
    items.add("Give up");
    items.add("Exit this menu");
    return items;
  }
  
}
