import java.util.ArrayList;
import java.util.Iterator;

//author: Dane
public class Bank {
  private int availableHouses;
  private int availableHotels;

  public Bank() {
    this.availableHouses = 36;
    this.availableHotels = 12;
  }
  

  // give money to current player
  public void getMoneyFromBank(Long amount) 
  {
    Game.getCurrentPlayer().setCash(Game.getCurrentPlayer().getCash() + amount);  
    StdOut.println(Game.getCurrentPlayer()+" got "+moneyEuro(amount)+" from bank.");
  }

  // get money from current player, return any standing
  public Long returnMoneyToBank(Long amount) 
  {
    if( Game.getCurrentPlayer().getCash() >= amount)
    {
      Game.getCurrentPlayer().setCash(Game.getCurrentPlayer().getCash() - amount);
      StdOut.println(Game.getCurrentPlayer()+" paid "+moneyEuro(amount)+" to bank.");
      return 0L;
    } 
    else 
    {
      Long ret;
      ret=amount - Game.getCurrentPlayer().getCash();
      Game.getCurrentPlayer().setCash(0L);
      StdOut.println(Game.getCurrentPlayer()+" paid "+moneyEuro(amount)+
          " to bank, but still owes "+moneyEuro(ret)+".");
      return ret;
    }
  }

  public static Long roundMoney(Long amount) 
  {
    return ((amount+5000L)/10000L)*10000L;
  }

  public int getAvailableHouses() 
  {
    return availableHouses;
  }
  
  public void setAvailableHouses(int availableHousesInit) 
  {
    availableHouses = availableHousesInit;
  }
     
  public boolean upgradeHouseGet() 
  {
    if (availableHouses>0) 
    {
      availableHouses = availableHouses-1;
      return true;
    } 
    else 
    {
      return false;
    }  
  }

  public void upgradeHouseReturn(int upgrades) 
  {
    availableHouses = availableHouses+ upgrades;
  }

  public int getAvailableHotels() 
  {
    return availableHotels;
  }
  
  public void setAvailableHotels(int availableHotelsInit) 
  {
    availableHotels = availableHotelsInit;
  }
  
  public boolean upgradeHotelGet() 
  {
    if (availableHotels>0) 
    {
      availableHotels = availableHotels-1;
      return true;
    } 
    else 
    {
      return false;
    } 
  }

  public void upgradeHotelReturn(int upgrades) 
  {
    availableHotels = availableHotels+ upgrades;

  }
  
  private static String moneyReturnThousand(Long money)
  {
//    return money/1000L+"k";
    Long ret = money/1000L;
    if (ret >= 1000L) {
//      return (ret/1000L) + " " + ret%1000L + "k";
      //anton fix, yours worked well but when it had output 9040k it forgot to put leading zero after 9
      return String.format("%d %03dk", ret/1000L, ret%1000L);
    } else {
      return ret+"k";
    }
  }

  public static String moneyEuro(Long money) 
  {
    if (money >= 1000000L)
    {
      if ((money % 100000L) == 0L )
      {
        Long hundredsThousands = money / 100000L;
        double milions = hundredsThousands / 10.0;
        //anton note for dane: this will not show comma for 1000 milions,
        //                     it's celtic tiger, but still hope this won't be issue
        return "€" + milions +"M";
      }
      else 
      {
        return "€" + moneyReturnThousand(money);        
      }
    }
    else
    {
      return "€" + moneyReturnThousand(money);
    }
  }

  //anton
  public Pair<Player, Long> auction(String title, ArrayList<Player> auctioneers, boolean highestBidderWin) {
    Long winningBid = 0L;
    boolean firstBid = true;
    Iterator<Player> players = auctioneers.iterator();

    if (!highestBidderWin) winningBid = Long.MAX_VALUE;

    //list avaible auctioneers, in some auctions they could be just players owning the jail card
    UI.displayChoiceItems(title + " auctioneers", UI.playersToItems(auctioneers), false, false);
    while (players.hasNext()) {
      Player player = players.next();

      player.setOffer(0L);
      if (!UI.askNgetBoolean(player + " are you interested in this auction?")) {
        players.remove();
      }
    }

    // nobody interested then return null player
    if (auctioneers.size() == 0) return new Pair<Player, Long>(null, 0L);

    // ask till only 1 left, but ask at least once (in case there is just 1 he
    // still needs make valid offer)
    while (auctioneers.size() > 1 || firstBid) {
      players = auctioneers.iterator();

      while (players.hasNext() || firstBid) {
        Player player = players.next();

        Long offer;
        boolean repeat = true;

        // if there is 1 auctioner already, doesn't have to finish round to
        // declare winner. unless he didn't made even single bid
        if (auctioneers.size() > 1 || firstBid) {
          // repeat until you overbid or gived up
          do {
            // depending if highest or lowest will win change behaviour
            if (highestBidderWin) {
              // repeat until it's at least 10k, but less than they own
              do {
                String question = player + " give me your offer (€10k - " + moneyEuro(player.getCash()) + ")";
                offer = Bank.roundMoney(UI.askNgetLong(question));
              } while ((offer <= 0L) || (offer > player.getCash()));

              if (offer > winningBid) {
                winningBid = offer;
                player.setOffer(offer);
                repeat = false;
                firstBid = false;
              } else {
                StdOut.println("You offered " + moneyEuro(offer) + ", it's not enough and max bid is " + moneyEuro(winningBid) + ".");
                if (!UI.askNgetBoolean("Do you want increase your bid? (or give up)")) {
                  players.remove();
                  repeat = false;
                }
              }
            } else {
           // repeat until it's at least 10k
              do {
                String question = player + " give me your lowest offer (min €10k )";
                offer = Bank.roundMoney(UI.askNgetLong(question));
              } while (offer <= 0L);

              if (offer < winningBid) {
                winningBid = offer;
                player.setOffer(offer);
                repeat = false;
                firstBid = false;
              } else {
                StdOut.println("You offered " + moneyEuro(offer) + ", it's more than previous lowest bid " + moneyEuro(winningBid) + ".");
                if (!UI.askNgetBoolean("Do you want lower your bid? (or give up)")) {
                  players.remove();
                  repeat = false;
                }
              }
              
            }
          } while (repeat);
        }
      }

      ArrayList<String> offers = new ArrayList<String>();
      for (Player player : auctioneers) {
        offers.add(player + " - " + moneyEuro(player.getOffer()));
      }
      UI.displayChoiceItems(title + " auction progress", offers, false, false);

    }
    StdOut.println(auctioneers.get(0) + " won auction with " + moneyEuro(winningBid) + ".");
    UI.pause();
    return new Pair<Player, Long>(auctioneers.get(0), winningBid);
  }

  // anton's overloading
  public Pair<Player, Long> auction(String title, ArrayList<Player> auctioneers) {
    return this.auction(title, auctioneers, true);
  }
}
