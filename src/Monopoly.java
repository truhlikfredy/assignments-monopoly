/**
 * Main class for game
 * 
 * @author Anton
 * @version 1
 */

import java.util.ArrayList;
import java.util.Arrays;

public class Monopoly {
  private boolean debug = true;

  /**
   * First method be called by our game, will display welcome art and run rest
   * of the application
   * 
   * @param argvs
   *          none file arguments are used
   */
  public static void main(String[] argvs) {

    Game.availableSaveFiles();

    if (UI.enhancedGraphics) {
      StdOut.println(UI.logo);
      StdOut.println(UI.bill);
      StdOut.println();
    }

    StdOut.println("                        Welcome to monopoly. ");

    Monopoly MyApplication = new Monopoly();
    MyApplication.run();
  }

  // initialize objects for game class
  private void initGame() {
    Game.setBank(new Bank());
    Game.setBoard(new Board());

    CardsChance chance = new CardsChance();
    chance.fillValues();
    chance.shuffleCards();
    Game.setCardsChance(chance);

    CardsCommunity community = new CardsCommunity();
    community.fillValues();
    community.shuffleCards();
    Game.setCardsCommunity(community);

    // ask for players and fill them up
    int choice;
    do {
      choice = UI.askNgetInt("How many players (2-4)?");
    } while (choice > 4 || choice < 2);

    Game.setNumPlayers(choice);

    for (int i = 0; i < Game.getNumPlayers(); i++) {
      Game.addPlayer(UI.askNgetString("Enter name for player #" + (i + 1)));
    }
    // display all players
    UI.displayChoiceItems("Players playing", UI.playersToItems(Game.getAllPlayingPlayers()), false, false);
  }

  // when game is initialized the game can start playing, this is main loop in
  // which will the game stay will somebody wins
  private void startGame() {
    // while there is still anybody playing loop this
    while (Game.anyPlayerPlaying()) {
      if (Game.getCurrentPlayer().isPlaying()) {
        if (Game.getCurrentPlayer().didYouWon()) {
          // somebody won, change him to "non-playing" state as well so the loop
          // will exit properly
          UI.anounceWinner(Game.getCurrentPlayer());

          Game.getCurrentPlayer().giveUp();
        } else {

          // in all other cases display board and his options depending on jail
          // status

          UI.displayBoard();

          if (Game.getCurrentPlayer().getJailTime() > 0) {
            jailOptions();
          } else {
            playerOptions();
          }

        }
      }
      Game.nextPlayer();
    }
  }

  // ****** Jail options menu displaying and condition handling ******

  private void jailOptions() {
    int choice = this.displayJailMenu();
    while (choice != 0) {
      switch (choice) {

      // player decided to roll dice
        case 1:
          PairDice dice = PairDice.getInstance(Game.getDebugDice());
          UI.diceDisplay(dice);
          if (dice.same) {
            StdOut.println("Double you are free, next turn you will be able to move");
            Game.getCurrentPlayer().setJailTime(0);
          } else {

            // this condition just protects if you mess to much with debug
            // options
            if (Game.getCurrentPlayer().getJailTime() > 0) {
              Game.getCurrentPlayer().setJailTime(Game.getCurrentPlayer().getJailTime() - 1);
            }

            if (Game.getCurrentPlayer().getJailTime() > 0) {
              StdOut.println("No luck. You will be stuck in jail for " + Game.getCurrentPlayer().getJailTime()
                  + " more turn(s).");
            } else {
              StdOut.println("You are free. You will be able to move in next turn.");
            }
          }
          choice = 0;
          UI.pause();
          break;

        // buy the card
        case 2:
          ArrayList<Player> players = new ArrayList<Player>();

          for (Player player : Game.getOtherPlayers()) {
            if (player.getCardsSize() > 0) {
              players.add(player);
            }
          }

          if (players.size() > 0) {
            Pair<Player, Long> winner = Game.getBank().auction("\"Get out\" card auction", players, false);

            // first hold Player object and second holds price
            if (winner.first != null) {
              if (winner.second <= Game.getCurrentPlayer().getCash()) {
                // get ACK from current player if he is willing pay the price
                if (UI
                    .askNgetBoolean(winner.first + " is asking " + Bank.moneyEuro(winner.second) + ", do you accept?")) {
                  // pay for the card
                  Game.getCurrentPlayer().payToPlayer(winner.first, winner.second);

                  // get the card itself
                  winner.first.transferCardJail(Game.getCurrentPlayer());
                }
                // in case you don't accept nothing will happen
              } else {
                StdOut.println("Sorry winner was asking more than you can afford to pay.");
              }
            } else {
              StdOut.println("Nobody was willing to sell you the card.");
            }
          } else {
            StdOut.println("There are no players who have the \"Get out\" card.");
          }

          UI.pause();
          break;

        // use the card
        case 3:
          if (Game.getCurrentPlayer().getCardsSize() > 0) {
            Game.getCurrentPlayer().setJailTime(0);
            Game.getCurrentPlayer().useCard();

            StdOut.println("You will be able to move next round.");

            // leave the menu
            choice = 0;
          } else {
            StdOut.println("You can't use a card, because you have none!");
          }
          UI.pause();
          break;

        // give up on this game
        case 4:
          if (UI.askNgetBoolean("Are you sure to give up?")) Game.getCurrentPlayer().giveUp();
          choice = 0;
          break;

        // save game status and exit the application
        case 5:
          if (UI.askNgetBoolean("Are you sure to suspend the game?")) {
            Game.save();
            Game.stopGame();
            choice = 0;
          }
          break;

        case 6:
          debugOptions();
          break;

        case 0:
        default:
          break;

      }
      if (choice != 0) choice = this.displayJailMenu();
    }
  }

  private int displayJailMenu() {
    ArrayList<String> items = new ArrayList<String>(Arrays.asList("Attempt to roll a double", "Buy \"GET OUT\" card",
        "Use \"GET OUT\" card", "Give up", "Suspend game (save & exit)"));
    if (debug) {
      items.add("Debug options");
    }
    return UI.displayMenuNGetChoice(
        Game.getCurrentPlayer() + "'s options (" + Bank.moneyEuro(Game.getCurrentPlayer().getCash())
            + " cash, in jail for " + Game.getCurrentPlayer().getJailTime() + " turn(s) )", items, false, false);
  }

  // ****** Players options menu displaying and condition handling ******

  private void playerOptions() {
    int choice = this.displayPlayerMenu();
    while (choice != 0) {
      switch (choice) {

      // player decided to roll dice
        case 1:
          PairDice dice = PairDice.getInstance(Game.getDebugDice());
          UI.diceDisplay(dice);
          Game.getCurrentPlayer().step(dice.total);
          if (dice.same) {
            StdOut.println("\nYou threw a DOUBLE, you are rolling for the second time.");
            UI.pause();

            dice = PairDice.getInstance(Game.getDebugDice());
            UI.diceDisplay(dice);
            Game.getCurrentPlayer().step(dice.total);
            if (dice.same) {
              StdOut.println("\nYou threw a DOUBLE for the second time, you are rolling the third time.");
              StdOut.println("Note: be careful or you are going to jail.");
              UI.pause();

              dice = PairDice.getInstance(Game.getDebugDice());
              UI.diceDisplay(dice);
              if (dice.same) {
                StdOut.println("\nYou threw a DOUBLE for the third time, you are going straight to jail.");
                // Game.getCurrentPlayer().jumpTo(Game.getBoard().getJailPosition());
                // will change possition as well
                Game.getCurrentPlayer().setJailTime(3);
              } else {
                Game.getCurrentPlayer().step(dice.total);
              }
            }
          }
          // step is already called in tileActions
          // Game.getBoard().tileActions();
          choice = 0;
          UI.pause();
          break;

        // to see what tiles he owns
        case 2:
          UI.displayMyProperties();
          UI.pause();
          break;

        // to see stats of all players together
        case 3:
          UI.displayChoiceItems("Players", UI.playersToItems(Game.getAllPlayingPlayers()), false, false);
          UI.pause();
          break;
          
        //Sell properties to other player
        case 4:
          //TODO test it more
          ArrayList<BoardTile> tiles = new ArrayList<BoardTile>();
          
          for (BoardTile tile: BoardSearch.all().filterByOwner(Game.getCurrentPlayer()).getTiles()) {
            if (Game.getBoard().getTile(Game.getCurrentPlayer().getPosition()).ownAllofThisColorAreNotUpgraded()) {
              if (tile.getUpgradeLevel()==0) {
                tiles.add(tile);
              }
            }
          }
          
          if (tiles.size() > 0) {
            int forSale = UI.displayMenuNGetChoice("Choose tile to sell", BoardSearch.all(tiles).toMenuItems() ,false, true);
//            BoardTile tileForSale = tiles 
            
            Pair<Player, Long> winner = Game.getBank().auction("Auction for "+tiles.get(forSale),Game.getOtherPlayers());
            
            if (winner.first != null) {
              tiles.get(forSale).setOwned(true);
              tiles.get(forSale).setOwner(winner.first);
              winner.first.payToPlayer(Game.getCurrentPlayer() ,winner.second);
              
              
            } else {
              StdOut.println("Nobody was interested to buy your property.");
            }
            
          } else {
            StdOut.println("You don't own any properties avaiable for selling. Can't be upgraded and:");
            StdOut.println("To sell property all properties of this colour can't be upgraded!");
          }          
          UI.pause();
          break;
          
        //Downgrade tiles
        case 5:
          //TODO test it more
          UI.displayMyProperties();

          ArrayList<BoardTile> forDowngrade = new ArrayList<BoardTile>();
          
          for (BoardTile tile: BoardSearch.all().filterByOwner(Game.getCurrentPlayer()).getTiles()) {
            if (tile.getUpgradeLevel()>0) {
              forDowngrade.add(tile);
            }
          }
          
          if (forDowngrade.size() > 0) {
            if (UI.askNgetBoolean("Are you sure to downgrade some properties?")) {
              int downgradeIndex = UI.displayMenuNGetChoice("Choose property to downgrade", BoardSearch.all(forDowngrade).toMenuItems() ,false, true);
              
              forDowngrade.get(downgradeIndex).decUpgradeLevel();
            }
          } else {
            StdOut.println("No properties for downgrading");
          }
          UI.pause();          
          break;
          
        //Mortages
        case 6:
          //TODO test it more
          UI.displayMyProperties();
          
          ArrayList<BoardTile> mortaged = BoardSearch.all().filterByOwner(Game.getCurrentPlayer()).filterByMortaged(true).getTiles();
          
          if (mortaged.size() > 0) {
            UI.displayChoiceItems("Mortage properties", BoardSearch.all(mortaged).toMenuItems(), false, true);
            
            if (UI.askNgetBoolean("Do you want buy some of them back?")) {
              
              int buyBack = UI.displayMenuNGetChoice("Mortage properties", BoardSearch.all(mortaged).toMenuItems(), false, true);
              
              if (mortaged.get(buyBack).mortageBuyOfPrice() <= Game.getCurrentPlayer().getCash() ) {
                
                //you can afford it but still lets confirm it from you
                if (UI.askNgetBoolean("It will cost " + Bank.roundMoney(mortaged.get(buyBack).mortageBuyOfPrice()) + ", do you want it back?")) {
                  mortaged.get(buyBack).mortageFromBank();
                }
                
              } else {
                StdOut.println("You cannot affort to pay: "+Bank.roundMoney(mortaged.get(buyBack).mortageBuyOfPrice()));
              }
            }
            
          } else {
            StdOut.println("You didn't mortaged any properties.");
          }
          UI.pause();
          
          break;

        // give up on this game
        case 7:
          if (UI.askNgetBoolean("Are you sure to give up?")) Game.getCurrentPlayer().giveUp();
          choice = 0;
          break;

        // save game status and exit the application
        case 8:
          if (UI.askNgetBoolean("Are you sure to suspend the game?")) {
            Game.save();
            Game.stopGame();
            choice = 0;
          }
          break;

        case 9:
          boolean allAgree = true;
          Long max = 0L;

          UI.displayChoiceItems("Players", UI.playersToItems(Game.getAllPlayingPlayers()), false, false);

          // at least empty player, just in case so there will be no NULL
          // pointer issues later
          Player winner = new Player();

          for (Player player : Game.getAllPlayingPlayers()) {
            if (!UI.askNgetBoolean(player + " do you agree with stopping this game?")) {
              allAgree = false;
            }
            // while traversing all players we can also find who is the richest
            // one
            if (player.totalNetWorth() > max) {
              max = player.totalNetWorth();
              winner = player;
            }
          }
          if (allAgree) {
            // we will leave the menu
            choice = 0;

            UI.anounceWinner(winner);
            Game.stopGame();
          } else {
            StdOut.println("Not all players agreed with this request.");
          }
          break;

        case 10:
          debugOptions();
          break;

        case 0:
        default:
          break;

      }
      if (choice != 0) choice = this.displayPlayerMenu();
    }
  }

  private int displayPlayerMenu() {
    ArrayList<String> items = new ArrayList<String>(Arrays.asList("Roll dice", "Show my properties",
        "Show players stats", "Sell to other player", "Downgrade properties", "Mortages", "Give up",
        "Suspend game (save & exit)", "Stop on request (just exit)"));
    if (debug) {
      items.add("Debug options");
    }
    return UI.displayMenuNGetChoice(
        Game.getCurrentPlayer() + "'s options (" + Bank.moneyEuro(Game.getCurrentPlayer().getCash()) + " cash)", items,
        false, false);
  }

  // ****** debuggin menu displaying and condition handling ******

  private void debugOptions() {
    int choice = this.displayDebugMenu();
    while (choice != 0) {
      switch (choice) {

        case 1:
          Game.setDebugDice(!Game.getDebugDice());
          break;

        case 2:
          UI.displayBoard();
          int pos = -1;

          // keep asking while it's not positive number
          while ((pos = UI.askNgetInt("Where you want to jump?")) < 0)
            ;

          // if it's too big, inside the jump it will be fixed with MODULO
          Game.getCurrentPlayer().jumpTo(pos);
          break;

        case 3:
          Game.getBoard().tileActions();
          break;

        case 4:
          Long cash = -1L;

          while ((cash = UI.askNgetLong("How much cash you want to have?")) < 0) {
            // keep asking while it's not positive number
          }

          Game.getCurrentPlayer().setCash(cash);

          break;

        case 5:
          UI.displayBoard();
          int jail = -1;

          while ((jail = UI.askNgetInt("Jail time. How much?")) < 0) {
            // keep asking while it's not positive number
          }

          Game.getCurrentPlayer().setJailTime(jail);
          break;

        case 6:
          Game.getCardsCommunity().pickCard();
          break;

        case 7:
          Game.getCardsChance().pickCard();
          break;

        case 8:
          StdOut.println();
          StdOut.println("Community deck size: " + Game.getCardsCommunity().getCards().size());
          StdOut.println("Chance deck size:    " + Game.getCardsChance().getCards().size());
          StdOut.println();
          UI.pause();
          break;

        case 9:
          // it will allow you to switch even to non playing player, so be
          // careful
          ArrayList<Player> playersPlaying = Game.getPlayers();
          int swapTo = UI.displayMenuNGetChoice("Players", UI.playersToItems(playersPlaying), false, true);
          Game.setPlayersTurn(swapTo);
          break;

        case 10:
          UI.displayBoard();
          break;
          
        case 11:
          StdOut.println("Houses :" + Game.getBank().getAvailableHouses());
          StdOut.println("Hotels :" + Game.getBank().getAvailableHotels());
          Game.getBank().setAvailableHouses(UI.askNgetInt("How many houses you want to have spare?"));
          Game.getBank().setAvailableHotels(UI.askNgetInt("How many hotels you want to have spare?"));
          break;

        case 0:
        default:
          break;

      }
      if (choice != 0) choice = this.displayDebugMenu();
    }
  }

  private int displayDebugMenu() {
    ArrayList<String> items = new ArrayList<String>(Arrays.asList("Exit debugging", ((Game.getDebugDice()) ? "Disable"
        : "Enable") + " dice debugging", "Jump to tile #", "Execute current tile action", "Set your cash",
        "Set jail time", "Pick community card", "Pick chance card", "Show deck sizes", "Switch to other player",
        "Display board", "Access available upgrades"));

    StdOut
        .println("\nWARNING !!! Be careful, you could break the game.\n Some of these functions do not do any sanity checks!!!");
    StdOut
        .println("And really if some of these functions is used in some situations, it will make the game misbehave.");
    return UI.displayMenuNGetChoice("Debug options (" + Bank.moneyEuro(Game.getCurrentPlayer().getCash()) + " cash)",
        items, true, true);
  }

  // ****** Main menu displaying and condition handling ******

  public void run() {
    int choice = this.displayMainMenu();
    while (choice != 0) {
      switch (choice) {
        case 1:
          this.initGame();
          this.startGame();
          choice = 0;
          break;

        case 2:
          ArrayList<String> saves = Game.availableSaveFiles();
          if (saves.size() > 0) {
            int fileIndex = UI.displayMenuNGetChoice("Choose saveFile to load", saves, false, true);
            Game.load(saves.get(fileIndex));
            this.startGame();
            choice = 0;
          }
          break;

        case 3:
          UI.dice3D = !UI.dice3D;
          break;

        case 0:
        default:
          break;

      }
      if (choice != 0) choice = this.displayMainMenu();
    }
    if (choice == 0) {
      StdOut.println();
      StdOut.println("Exiting...");
      if (UI.enhancedGraphics) {
        StdOut.println(UI.goodbey);
      }
      StdOut.println();
    }
  }

  private int displayMainMenu() {
    ArrayList<String> items = new ArrayList<String>(Arrays.asList("Exit monopoly", "Start playing", "Load saved game"));
    if (UI.enhancedGraphics) {
      items.add("Switch to " + (UI.dice3D ? "2D dices" : "3D dices"));
    }
    return UI.displayMenuNGetChoice("Main Menu", items, true, true);
  }

}
