import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class Game {
  // heather+anton fields
  private static ArrayList<Player> players     = new ArrayList<Player>(); ;
  private static int               numPlayers;
  private static int               playersTurn = 0;
  private static Bank              bank;
  private static Board             board;
  private static CardsChance       cardsChance;
  private static CardsCommunity    cardsCommunity;
  private static boolean           debugDice = false;
  
  // anton's code:
  public static Player getCurrentPlayer() {
    return (players.get(playersTurn));
  }

  public static void nextPlayer() {
    playersTurn = (playersTurn + 1) % numPlayers;
  }

  public static boolean anyPlayerPlaying() {
    for (Player player : players) {
      if (player.isPlaying()) return true;
    }
    return false;
  }

  public static boolean onlyOnePlaying() {
    int playing = 0;
    for (Player player : players) {
      if (player.isPlaying()) playing++;
    }
    if (playing > 1) {
      return false;
    } else {
      return true;
    }
  }

  public static void stopGame() {
    for (Player player : players) {
      player.giveUp();
    }
  }
  
  //overwride for heather's save method
  public static void save() {
    Game.save(UI.askNgetString("Filename for this save file?")+".xml");
  }

  //will return list of files from save directory
  public static ArrayList<String> availableSaveFiles() {
    String path = "saveFiles/";

    ArrayList<String> files = new ArrayList<String>();
    File folder = new File(path);
    
    //if the save folder exists
    if (folder.exists()) {
      //get list of XML files
      String[] listOfFiles = folder.list(new FilenameFilter() {
        public boolean accept(File directory, String fileName) {
          return fileName.endsWith(".xml");
        }
      });
      
      //if there are any XML files
      if (listOfFiles.length > 0) {
        for (int i = 0; i < listOfFiles.length; i++) {
          files.add(listOfFiles[i]);
        }
      } else {
        StdOut.println("No savefiles in " + path + " directory.");
      }
      
    } else {
      StdOut.println("No " + path + " directory!");
    }
    return files;
  }  

  // Heather's code:
  public static ArrayList<Player> getPlayers() {
    return players;
  }

  public static int getNumPlayers() {
    return numPlayers;
  }

  public static int getPlayersTurn() {
    return playersTurn;
  }

  public static Bank getBank() {
    return bank;
  }

  public static Board getBoard() {
    return board;
  }

  public static CardsChance getCardsChance() {
    return cardsChance;
  }

  public static CardsCommunity getCardsCommunity() {
    return cardsCommunity;
  }
  
  public static boolean getDebugDice() {
    return debugDice;
  }

  public static void setDebugDice(boolean debugDice) {
    Game.debugDice = debugDice;
  }

  // get all players from the game who are playing
  public static ArrayList<Player> getAllPlayingPlayers() {
    // Anton fix: Would copy the list itself, we want just the items.
    // Remove/add manipulations would affect original list
    /*
     * ArrayList<Player> all = players; for (Player player: all) { if
     * (!player.isPlaying()) { all.remove(player); } }
     */

    ArrayList<Player> all = new ArrayList<Player>();
    for (Player player : players) {
      if (player.isPlaying()) all.add(player);
    }

    return all;
  }

  // get all who are playing except yourself
  public static ArrayList<Player> getOtherPlayers() {
    ArrayList<Player> all = getAllPlayingPlayers();
    all.remove(getCurrentPlayer());

    return all;
  }
   
  public static void setPlayers(ArrayList<Player> playersInit) {
    players = playersInit;
  }

  public static void setNumPlayers(int numPlayersInit) {
    numPlayers = numPlayersInit;
  }

  public static void setPlayersTurn(int playersTurnInit) {
    playersTurn = playersTurnInit;
  }

  public static void setBank(Bank bankInit) {
    bank = bankInit;
  }

  public static void setBoard(Board boardInit) {
    board = boardInit;
  }

  public static void setCardsChance(CardsChance cardsChanceInit) {
    cardsChance = cardsChanceInit;
  }

  public static void setCardsCommunity(CardsCommunity cardsCommunityInit) {
    cardsCommunity = cardsCommunityInit;
  }

  public static void addPlayer(String Name) {
    Player playerToAdd;
    playerToAdd = new Player(Name);
    players.add(playerToAdd);
  }

  public static void save(String fileName) {
    GameHelper dataBase = new GameHelper();

    dataBase.bank = bank;
    dataBase.players = players;
    dataBase.numPlayers = numPlayers;
    dataBase.playersTurn = playersTurn;
    dataBase.board = board;
    dataBase.cardsChance = cardsChance;
    dataBase.cardsCommunity = cardsCommunity;
    dataBase.debugDice = debugDice;

    StdStream.saveToFile(dataBase, "saveFiles/" + fileName);
    StdOut.println("The file was saved under the name:" + fileName);
    UI.pause();
  }

  public static void load(String fileName) {
    GameHelper dataBase = new GameHelper();

    dataBase = (GameHelper) StdStream.readFromFile("saveFiles/" + fileName);

    bank = dataBase.bank;
    players = dataBase.players;
    numPlayers = dataBase.numPlayers;
    playersTurn = dataBase.playersTurn;
    board = dataBase.board;
    cardsChance = dataBase.cardsChance;
    cardsCommunity = dataBase.cardsCommunity;
    debugDice = dataBase.debugDice;

    StdOut.println("The game was loaded from the file: " + fileName);
  }  
 
}