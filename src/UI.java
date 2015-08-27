import java.util.ArrayList;

/**
 * This class helps with user interface, it's displaying box menus, getting
 * input from the user and validating if it's withing required bounds.<br>
 * <br>
 * 
 * This helper class was designed to help abstract all (or most) user interface
 * code so the other classes can focus more on behaviour modeling and don't have
 * to worry about input validation and other tasks required with more hardened
 * UI.<br>
 * <br>
 * 
 * Still the UI uses readString so it can only accept input without any
 * whitspaces. But the rest of the UI tries to be mostly bullet proof. If other
 * methods using this class expect to get "int" within some bounds, this class
 * won't leave till the returning value won't break the program or cause runtime
 * error.<br>
 * <br>
 * 
 * NOTE:<br>
 * <br>
 * 
 * THIS file is in UTF-8 encoding!!<br>
 * <br>
 * 
 * If it will be interpreted as different encoding it can break visuals and even
 * build process (the project won't build).<br>
 * <br>
 * 
 * If non-monospace font is used it will break spacing and the graphics won't
 * align properly!<br>
 * <br>
 * 
 * @author Anton Krug 20062210 + Glen Malone (helping with artwork)
 * @version 2
 */

public class UI {


  public static boolean enhancedGraphics = true;
  public static boolean dice3D           = true;
  //@ff 
  public final static String logo = 
" _______  _______  _        _______  _______  _______  _             \n" +
"(       )(  ___  )( (    /|(  ___  )(  ____ )(  ___  )( \\   |\\     /|\n" +  
"| () () || (   ) ||  \\  ( || (   ) || (    )|| (   ) || (   ( \\   / )\n" +  
"| || || || |   | ||   \\ | || |   | || (____)|| |   | || |    \\ (_) / \n" +  
"| |(_)| || |   | || (\\ \\) || |   | ||  _____)| |   | || |     \\   /  \n" +  
"| |   | || |   | || | \\   || |   | || (      | |   | || |      ) (   \n" +  
"| )   ( || (___) || )  \\  || (___) || )      | (___) || (____/\\| |   \n" +  
"|/     \\|(_______)|/    )_)(_______)|/       (_______)(_______/\\_/   ";
  
  
  public final static String bill =
"────────────────────────────────────────────────────────────────────.\n"+
"| .--                      EUROPEAN UNION                        .-- |\n"+
"| |_       ......        REPUBLIC OF IRELAND                     |_  |\n"+
"| __)    ``````````             ______           C3l7ic 7i93R    __) |\n"+
"|      2        ___            /      \\                     2        |\n"+
"|              /|~ \\          /   _-\\  \\           __ _ _ _  __      |\n"+
"|             | |-< |        |  //   \\  |         |_  | | | |_       |\n"+
"|              \\|_//         | |-  o o| |         |   | `.' |__      |\n"+
"|               ~~~          | |\\   b.' |                            |\n"+
"|       C3l7ic 7i93R         |  \\ '~~|  |                            |\n"+
"| .--  2                      \\_/ ```__/    ....            2    .-- |\n"+
"| |_        ///// ///// ////   \\__\'`\\/      ``  //// / ////      |_  |\n"+
"| __)                     F I V E  E U R O S                     __) |\n"+
"`────────────────────────────────────────────────────────────────────'";

  public final static String winArt = 
"      ___                       ___     \n" + 
"     /\\__\\          ___        /\\__\\    \n" + 
"    /:/ _/_        /\\  \\      /::|  |   \n" + 
"   /:/ /\\__\\       \\:\\  \\    /:|:|  |   \n" + 
"  /:/ /:/ _/_      /::\\__\\  /:/|:|  |__ \n" + 
" /:/_/:/ /\\__\\  __/:/\\/__/ /:/ |:| /\\__\\\n" + 
" \\:\\/:/ /:/  / /\\/:/  /    \\/__|:|/:/  /\n" + 
"  \\::/_/:/  /  \\::/__/         |:/:/  / \n" + 
"   \\:\\/:/  /    \\:\\__\\         |::/  /  \n" + 
"    \\::/  /      \\/__/         /:/  /   \n" + 
"     \\/__/                     \\/__/    ";
            
  public final static String goodbey =
" .--.              . .             \n" +
":                  | |             \n" +
"| --. .-.  .-.  .-.| |.-. .  . .-. \n" +
":   |(   )(   )(   | |   )|  |(.-' \n" +
" `--' `-'  `-'  `-'`-'`-' `--| `--'\n" +
"                             ;     \n" +
"                          `-'      ";
  
  private final static String[][] diceGFX = {
    {"   "," * ","   "},
    {"*  ","   ","  *"},
    {"*  "," * ","  *"},
    {"* *","   ","* *"},
    {"* *"," * ","* *"},
    {"* *","* *","* *"}    
  }; 

  private final static String[][] diceBigGFX = {
    {"     ","  o  ","     "},
    {"o    ","     ","    o"},
    {"o    ","  o  ","    o"},
    {"o   o","     ","o   o"},
    {"o   o","  o  ","o   o"},
    {"o   o","o   o","o   o"}    
  }; 
  
//@f

  /*
   * ***************************************************************************
   * Helper methods to get input from user and converting it into multiple types
   * safely.
   * ***************************************************************************
   */

  /**
   * Gets integer from a user, makes sure it's not string
   * 
   * @return Returns back the int as answer
   */
  private static int getIntFromUser() {
    String choice;
    int ret = -1;
    boolean loop = true;

    while (loop) {
      choice = StdIn.readString();
      try {
        ret = Integer.parseInt(choice);
        loop = false;
      } catch (NumberFormatException e) {
        StdOut.println("You entered text instead of number. Please try again.");
      }
    }

    return ret;
  }

  public static int askNgetInt(String question) {
    StdOut.print(question + " ");
    return getIntFromUser();
  }
  
  /**
   * Get integer suitable for dice, it's between 1-6
   * 
   * @param question Will be asking this question till you fit yourself into boundaries
   * @return Will return int between 1-6
   */
  
  public static int aksNgetIntDice(String question) {
    int ret = -1;
    // will be asking till it's between 1-6
    while ( ((ret = askNgetInt(question)) < 1) || (ret > 6)  );
    return ret;
  }

  /**
   * Gets Long from a user, makes sure it's not string
   * 
   * @return Returns back the int as answer
   */
  private static Long getLongFromUser() {
    String choice;
    String unit;
    Long multiply = 1L;
    Long ret = -1L;
    boolean loop = true;

    while (loop) {
      choice = StdIn.readString().toLowerCase();
      unit = choice.substring(choice.length() - 1, choice.length());

      if (unit.equals("k")) {
        multiply = 1000L;
        choice = choice.substring(0, choice.length() - 1);
      } else if (unit.equals("m")) {
        multiply = 1000000L;
        choice = choice.substring(0, choice.length() - 1);
      } else {
        multiply = 1L;
      }

      try {
        ret = Long.parseLong(choice);
        loop = false;
      } catch (NumberFormatException e) {
        StdOut.println("You entered text instead of number. Please try again.");
      }
    }

    return ret * multiply;
  }

  public static Long askNgetLong(String question) {
    StdOut.print(question + " ");
    return getLongFromUser();
  }

  /**
   * Prints question and get string back
   * 
   * @param question
   *          Print question
   * @return Returns the answer as String back
   */

  public static String askNgetString(String question) {
    StdOut.print(question + " ");
    return StdIn.readString();
  }

  /**
   * Prints question and get string back, but makes sure that the answer is not
   * found in the blackList. If it's in the blackList, it will force user to
   * type it again
   * 
   * @param question
   *          The string to be printed out
   * @param blackList
   *          ArrayList of disallowed strings which can't be used as answer
   */

  public static String askNgetUniqueString(String question, ArrayList<String> blackList) {
    String ret;
    boolean found;

    do {
      ret = askNgetString(question);

      found = false;
      for (String item : blackList) {
        if (item.equals(ret)) found = true;
      }
      if (found) StdOut.println("Duplicate records are not allowed!");
    } while (found);

    return ret;
  }

  /**
   * Prints question and returns boolean true/false, it accept multiple answers
   * like "yes" "y" "true" "1" etc...
   * 
   * @param question
   *          The string to be printed out
   * @return Boolean value as answer
   */
  public static boolean askNgetBoolean(String question) {
    String input;
    boolean notValid = true;
    boolean ret = false;

    do {
      // to lower case so the answer will ba case insensitive
      input = askNgetString(question + " (y/n)").toLowerCase();

      // switch & case for String is tricky, so I decided use if statements
      if (input.equals("true") || input.equals("yes") || input.equals("y") || input.equals("t") || input.equals("1")) {
        ret = true;
        notValid = false;
      } else if (input.equals("false") || input.equals("no") || input.equals("n") || input.equals("f")
          || input.equals("0")) {
        ret = false;
        notValid = false;
      }
      if (notValid) StdOut.println("Options: true,t,yes,y,1,false,f,no,n,0");
    } while (notValid);

    return ret;
  }

  /*
   * ***************************************************************************
   * Helper methods for displaying menus/lists
   * ***************************************************************************
   */

  /**
   * Just displays items with title over the top. Depending on the zeroAsLast
   * this could be list, or menu. Because menu has 0 item as last and it's the
   * "exit" option. It draws box window decorations. But it doesn't have any
   * input
   * 
   * @param title
   *          Will print this title on the top of menu/list
   * @param items
   *          Contain list of all Strings (options for the menu/list)
   * @param zeroAsLast
   *          Boolean parameter to state if item 0 from the list will be
   *          displayed on the bottom or on the top of the list
   */
  public static void displayChoiceItems(String title, ArrayList<String> items, boolean zeroAsLast, boolean countFromZero) {
    int maxLen = 0;
    int offset = 0;
    if (!countFromZero) offset = 1;

    /* Find the maximal lenght of the items inside the menu */
    for (String item : items) {
      if (maxLen < item.length()) maxLen = item.length();
    }
    /*
     * -2 because I will use the edge characters in the begining and in the end
     * +4 because I'm printing for the items 4 characters more " 1. " +2 because
     * the box ornaments +1 just white space spacing to it at the end
     * 
     * result is maxLen+=5;
     */
    maxLen += 5;

    /*
     * if title is bigger than menu items make lines big as the title, not as
     * items
     */
    if (title.length() > maxLen) maxLen = title.length();

    String line = new String(new char[maxLen]).replace('\0', '─');
    String doubleLine = new String(new char[maxLen]).replace('\0', '═');
    String shadowLine = new String(new char[maxLen + 2]).replace('\0', '▒');
    shadowLine = " " + shadowLine;

    StdOut.println("\n┌" + line + '┐');

    /*
     * print out centered title of the menu if there is enough spacing, if title
     * is bigger than menu, do not put any spacing there
     */
    int spacing = (maxLen - title.length()) / 2;

    if (spacing < 1) {
      StdOut.println("│" + title.toUpperCase() + (((maxLen - title.length()) == 1) ? " " : "") + "│▒");

    } else {

      StdOut.println(String.format("│%" + spacing + "s%s%" + (maxLen - (spacing + title.length())) + "s│▒", "", title.toUpperCase(),
          ""));
    }

    StdOut.println('╞' + doubleLine + "╡▒");

    if (items.size() > 0) {
      /*
       * Item with 0 index will be displayed as first or as last depending of
       * the zeroAsLast value. In the middle will be displayed all other menu
       * options.
       */
      if (!zeroAsLast) StdOut.println(String.format("│%2d. %s%" + (maxLen - items.get(0).length() - 2) + "s",
          0 + offset, items.get(0), "│▒"));

      for (int i = 1; i < items.size(); i++) {
        StdOut.println(String.format("│%2d. %s%" + (maxLen - items.get(i).length() - 2) + "s", i + offset,
            items.get(i), "│▒"));
      }

      if (zeroAsLast) StdOut.println(String.format("│%2d. %s%" + (maxLen - items.get(0).length() - 2) + "s",
          0 + offset, items.get(0), "│▒"));
    }

    StdOut.println('└' + line + "┘▒");
    StdOut.println(shadowLine);

  }

  /**
   * Wrapper around displayChoiceItems to get input from user.
   * 
   * It will display all items from the list and let user choose one. title will
   * be displayed on the top of the menu items are all items to be displayed
   * zeroAsLast will decide if 0 item will be displayed as first or as last (for
   * menu it good as last becasue 0 is exit, and for the list is good to be
   * displayed as first becasue in the list 0 is just regular data item as other
   * ones
   * 
   * @param title
   *          Will print this title on the top of menu/list
   * @param items
   *          Contain list of all Strings (options for the menu/list)
   * @param zeroAsLast
   *          Boolean parameter to state if item 0 from the list will be
   *          displayed on the bottom or on the top of the list
   * 
   * @return Returns int which menu/list option was selected
   */
  public static int displayMenuNGetChoice(String title, ArrayList<String> items, boolean zeroAsLast,
      boolean countFromZero) {
    int choice;
    int offset = 0;
    if (!countFromZero) offset = 1;

    do {
      displayChoiceItems(title, items, zeroAsLast, countFromZero);
      StdOut.print("Enter your choice here and press enter (" + (0 + offset) + " - " + (items.size() + offset - 1)
          + "): ");
    } while ((choice = getIntFromUser()) >= (items.size() + offset) || choice < (0 + offset) );

    return choice;
  }

  /**
   * Overrided method, just to force "zeroAsLast=true" this variant is useful
   * for displaying menus where 0 is always exit option and is displayed as last
   * one.
   * 
   * @param title
   *          Will print this title on the top of menu/list
   * @param items
   *          Contain list of all Strings (options for the menu/list)
   * 
   * @return Returns int which menu/list option was selected
   */
  public static int displayMenuNGetChoice(String title, ArrayList<String> items) {
    return displayMenuNGetChoice(title, items, true, true);
  }

  /**
   * Supply dice object and will render pretty dice graphics
   * 
   * @param dice
   *          PairDice object with dice to draw
   */
  public static void diceDisplay(PairDice dice) {
    if (enhancedGraphics) {
      if (dice3D) {
        StdOut.println("               _______.");
        StdOut.println("    ______    | " + diceBigGFX[dice.second - 1][0] + " |\\");
        StdOut.println("   /" + diceBigGFX[dice.first - 1][0] + "/\\   | " + diceBigGFX[dice.second - 1][1] + " | \\");
        StdOut.println("  /" + diceBigGFX[dice.first - 1][1] + "/  \\  | " + diceBigGFX[dice.second - 1][2] + " |  |");
        StdOut.println(" /" + diceBigGFX[dice.first - 1][2] + "/    \\ |_______|  |");
        StdOut.println("/_____/     /  \\       \\ |");
        StdOut.println("\\     \\    /    \\_______\\|");
        StdOut.println(" \\     \\  /");
        StdOut.println("  \\_____\\/");
      } else {
        StdOut.println("┌─────┐ ┌─────┐");
        for (int i = 0; i < 3; i++) {
          StdOut.println("│ " + diceGFX[dice.first - 1][i] + " │ │ " + diceGFX[dice.second - 1][i] + " │");
        }
        StdOut.println("└─────┘ └─────┘");
      }
    } else {
      StdOut.println("Dice rolled: you got " + dice.first + " and " + dice.second + ",together: " + dice.total);
    }
  }

  /**
   * Will diplay content of a card
   * 
   * @param text
   *          Descrition of a card
   */
  public static void displayCard(String text) {
    String shadowLine = new String(new char[76]).replace('\0', '▒');
    
    //split by new line to multiple Strings so it's easier to displa a string per line
    String lines[] = text.split("\\n");
    
    StdOut.println();
    StdOut.println("┌──────────────────────────────────────────────────────────────────────────┐");
    StdOut.println("│                                C A R D                                   │▒");
    StdOut.println("╞══════════════════════════════════════════════════════════════════════════╡▒");
    for (int i=0;i<lines.length;i++) {
      StdOut.println(String.format("│%74s│▒",lines[i]));
    }
    StdOut.println("└──────────────────────────────────────────────────────────────────────────┘▒");
    StdOut.println(" " + shadowLine);
  }

  /**
   * Will pause for specified time (in seconds)
   * 
   * @param count
   *          Counter how long pause
   */
  public static void pause(int count) {
    StdOut.print("Continuing in ... ");
    try {
      for (int i = (count+1); i > 1; i--) {
        StdOut.print(i - 1 + " ");
        java.lang.Thread.sleep(1000);
      }
    } catch (InterruptedException ie) {
    }
    StdOut.println();
  }

  /**
   * Will pause for couple seconds
   * 
   */
  public static void pause() {
    pause(3);
  }

  /**
   * Covert players collection into string collection which can be used for
   * menus
   * 
   * @param players
   *          Collection of players
   */
  public static ArrayList<String> playersToItems(ArrayList<Player> players) {
    ArrayList<String> items = new ArrayList<String>();
    for (Player player : players) {
      String text = player + " ( Cash: " + Bank.moneyEuro(player.getCash());
      text += ", Net: " + Bank.moneyEuro(player.totalNetWorth()) + ", Tile #" + player.getPosition();
      if (player.getJailTime()>0) {
        text += ", in JAIL ";
      }
      text += ")";
      items.add(text);
    }

    return items;
  }

  /**
   * Display given tile as county tile
   * 
   * @param tile
   *          Given tile
   */
  public static void displayCounty(BoardTile tile) {
    // 15 + 12 + 1 character for ':' + 1 character for space = 29
    // 29*2 +1 center char + 2 start & end = 61
    String line = new String(new char[29]).replace('\0', '─');
    String lineDouble = new String(new char[29]).replace('\0', '═');
    String shadowLine = new String(new char[61]).replace('\0', '▒');

    StdOut.println("┌" + line + "┬" + line + "┐");

    StdOut.println(String.format("│%10s:%17s │ %15s:%12s│▒", "County", tile, "Price",
        Bank.moneyEuro(tile.getPrice())));

    StdOut.println('╞' + lineDouble + "╪" + lineDouble + "╡▒");

    StdOut.println(String.format("│%15s:%12s │ %15s:%12s│▒", "Rent regular", Bank.moneyEuro(tile.getRent()), "Group",
        Board.getColor(tile.getGroup())));
    StdOut.println(String.format("│%15s:%12s │ %15s:%12s│▒", "Rent 1 house", Bank.moneyEuro(tile.getRent1()), "Owner",
        tile.getOwner()));
    StdOut.println(String.format("│%15s:%12s │ %15s:%12s│▒", "Rent 2 houses", Bank.moneyEuro(tile.getRent2()),
        "Upgrade house", Bank.moneyEuro(tile.getCostHouse())));
    StdOut.println(String.format("│%15s:%12s │ %15s:%12s│▒", "Rent 3 houses", Bank.moneyEuro(tile.getRent3()),
        "Upgrade hotel", Bank.moneyEuro(tile.getCostHotel())));
    StdOut.println(String.format("│%15s:%12s │ %15s:%12s│▒", "Rent 4 houses", Bank.moneyEuro(tile.getRent4()),
        "Mortage", Bank.moneyEuro(tile.getMortage())));
    StdOut.println(String.format("│%15s:%12s │ %15s:%12s│▒", "Rent hotel", Bank.moneyEuro(tile.getRentHotel()),
        "Mortaged", tile.isMortaged()));
    StdOut.println(String.format("│%15s:%12s │ %15s:%12s│▒", "Upgrades", tile.getUpgradeLevel(), "Current rent",
        Bank.moneyEuro(tile.getCurrentRent())));

    StdOut.println('└' + line + "┴" + line + "┘▒");
    StdOut.println(" " + shadowLine);

  }

  /**
   * Display given tile as province tile
   * 
   * @param tile
   *          Given tile
   */
  public static void displayProvince(BoardTile tile) {
    // 15 + 12 + 1 character for ':' + 1 character for space = 29
    // 29*2 +1 center char + 2 start & end = 61
    String line = new String(new char[29]).replace('\0', '─');
    String lineDouble = new String(new char[29]).replace('\0', '═');
    String shadowLine = new String(new char[61]).replace('\0', '▒');

    StdOut.println("┌" + line + "┬" + line + "┐");

    StdOut.println(String.format("│%10s:%17s │ %15s:%12s│▒", "Province", tile, "Price",
        Bank.moneyEuro(tile.getPrice())));

    StdOut.println('╞' + lineDouble + "╪" + lineDouble + "╡▒");

    StdOut.println(String.format("│%15s:%12s │ %28s│▒", "Rent", "", ""));
    StdOut.println(String.format("│%15s:%12s │ %15s:%12s│▒", "1 Province", Bank.moneyEuro(tile.getRent1()), "Owner",
        tile.getOwner()));
    StdOut.println(String.format("│%15s:%12s │ %15s:%12s│▒", "2 Provinces", Bank.moneyEuro(tile.getRent2()),
        "Current rent", Bank.moneyEuro(tile.getCurrentRent())));
    StdOut.println(String.format("│%15s:%12s │ %15s:%12s│▒", "3 Provinces", Bank.moneyEuro(tile.getRent3()),
        "Mortaged", tile.isMortaged()));
    StdOut.println(String.format("│%15s:%12s │ %15s:%12s│▒", "4 Provinces", Bank.moneyEuro(tile.getRent4()), "Mortage",
        Bank.moneyEuro(tile.getMortage())));

    StdOut.println('└' + line + "┴" + line + "┘▒");
    StdOut.println(" " + shadowLine);

  }

  /**
   * Display given tile as province tile
   * 
   * @param tile
   *          Given tile
   */
  public static void displayUtility(BoardTile tile) {
    // 15 + 12 + 1 character for ':' + 1 character for space = 29
    // 29*2 +1 center char + 2 start & end = 61
    String line = new String(new char[29]).replace('\0', '─');
    String lineDouble = new String(new char[29]).replace('\0', '═');
    String shadowLine = new String(new char[61]).replace('\0', '▒');

    StdOut.println("┌" + line + "┬" + line + "┐");

    StdOut.println(String.format("│%10s:%17s │ %15s:%12s│▒", "Utility", tile, "Price",
        Bank.moneyEuro(tile.getPrice())));

    StdOut.println('╞' + lineDouble + "╪" + lineDouble + "╡▒");

    StdOut.println(String.format("│%15s:%12s │ %28s│▒", "Rent", "", ""));
    StdOut.println(String.format("│%15s:%12s │ %15s:%12s│▒", "Just a utility", Bank.moneyEuro(tile.getRent1()),
        "Owner", tile.getOwner()));
    StdOut.println(String.format("│%15s:%12s │ %15s:%12s│▒", "Both utilities", Bank.moneyEuro(tile.getRent2()),
        "Current rent", Bank.moneyEuro(tile.getCurrentRent())));
    StdOut.println(String.format("│%15s:%12s │ %15s:%12s│▒", "Mortaged", tile.isMortaged(), "Mortage",
        Bank.moneyEuro(tile.getMortage())));

    StdOut.println('└' + line + "┴" + line + "┘▒");
    StdOut.println(" " + shadowLine);

  }

  /**
   * Will display given player as winner and will wait for 8 seconds
   * 
   * @param player
   */
  public static void anounceWinner(Player player) {
    UI.displayChoiceItems("Players", UI.playersToItems(Game.getAllPlayingPlayers()), false, false);

    if (UI.enhancedGraphics) {
      StdOut.println(UI.winArt);
    }
    StdOut.println(player + " won the game!!!\n");
    StdOut.print("All glory to: ");
    for (int i = 0; i < 5; i++) {
      StdOut.print(player.getName().toUpperCase() + " ");
    }
    StdOut.println("!\n");

    UI.pause(8);
  }


  /**
   * Will display playing board from given position
   * 
   * @param fromPosition
   */
  public static void displayBoard(int fromPosition) {
    String shadowLine = new String(new char[76]).replace('\0', '▒');

    // fromPosstion + whole size of board so when I do -4 I'm sure it won't go into
    // negative numbers.
    // I guessed 4 just so player can see little bit behind him as well. 
    fromPosition = fromPosition + Game.getBoard().size() - 4;
    
    //top of board
    StdOut.println("┌────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┬────┐");

    //board tile names
    for (int i = 0; i < 15; i++) {
      int pos = (i + fromPosition ) % Game.getBoard().size();
      StdOut.print(String.format("│%4s", Game.getBoard().getTile(pos).getShortName()));
    }
    StdOut.println("│▒");

    //board tile colors
    for (int i = 0; i < 15; i++) {
      int pos = (i + fromPosition ) % Game.getBoard().size();
      BoardTile tile = Game.getBoard().getTile(pos);
      
      String color=Board.getColor(tile.getGroup());
      if (color.length()>4) color=color.substring(0,4);
      
      if (tile.getType() == Board.TILE_COUNTY) {
        StdOut.print(String.format("│%4s", color));
      } else {
        StdOut.print("│    ");
      }
    }
    StdOut.println("│▒");

    //board tile owned by somebody?
    for (int i = 0; i < 15; i++) {
      int pos = (i + fromPosition ) % Game.getBoard().size();
      
      if (Game.getBoard().getTile(pos).isOwned()) {
        StdOut.print("│ownr");
      } else {
        StdOut.print("│    ");
      }
    }
    StdOut.println("│▒");
    
    //bottom of board
    StdOut.println("└────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┴────┘▒");
    StdOut.println(" " + shadowLine);
  }

  /**
   * Override for displayBoard, will display board for current player
   */
  public static void displayBoard() {
    StdOut.println();
    
    //Manualy alligned to match the 4 possition from the side. 
    //If I will change the aligment here as well!!
    
    StdOut.println("                     " + Game.getCurrentPlayer() + " (tile #"
        + Game.getCurrentPlayer().getPosition() + ")");
    // StdOut.println(" ||||");
    StdOut.println("                     vvvv");
    displayBoard(Game.getCurrentPlayer().getPosition());
  }

  public static void displayMyProperties() {
    UI.displayChoiceItems("My properties",
        BoardSearch.all().filterByOwner(Game.getCurrentPlayer()).toMenuItems(), false, true);    
  }
}
