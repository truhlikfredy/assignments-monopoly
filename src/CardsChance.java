public class CardsChance extends Cards
// Glen Malone
{

  public void pickCard() {
    int drawnCard = this.getTopCard();

    switch (drawnCard) {

      case (0):
        UI.displayCard("Advance to Roscommon");
        // Move current player to Roscommon.
        Game.getCurrentPlayer().jumpTo(Game.getBoard().getRoscommon());
        this.putCardOnBottom(drawnCard);
        
        Game.getBoard().tileActions();        
        break;

      case (1):
        UI.displayCard("Advance to Tyrone. If you pass Go, collect €2M");
        // Move current player to Tyrone.
        // If pass Go, add 2000000 to current player cash.
        if (Game.getCurrentPlayer().getPosition() > Game.getBoard().getTyrone())
        {
          Game.getBank().getMoneyFromBank(2000000L);
        }
        Game.getCurrentPlayer().jumpTo(Game.getBoard().getTyrone());        
        this.putCardOnBottom(drawnCard);
        Game.getBoard().tileActions();
        break;

      case (2):
        UI.displayCard("Pay private school fees of €1.5M");
        // Remove 1500000 from current player cash.
        Game.getBank().returnMoneyToBank(1500000L);
        this.putCardOnBottom(drawnCard);
        break;

      case (3):
        UI.displayCard("GET OUT OF JAIL FREE card.\n This card may be kept until needed or sold.");
        // keep card in possession
        // Game.getCurrentPlayer().receiveCardJail();
        Game.getCurrentPlayer().receiveCard(this, drawnCard);
        break;

      case (4):
        UI.displayCard("Rush hour traffic! Go back 3 spaces.");
        // Move back 3 spaces.
        Game.getCurrentPlayer().step(-3);
        this.putCardOnBottom(drawnCard);
        break;

      case (5):
        UI.displayCard("Advance to Go and collect €2M.");
        // Move player to Go.
        Game.getCurrentPlayer().jumpTo(0);
        // Add 2000000 to player cash.
        // Game.getCurrentPlayer().getMoney(2000000L);
        Game.getBank().getMoneyFromBank(2000000L);
        this.putCardOnBottom(drawnCard);
        break;

      case (8):
        UI.displayCard("Advance to Offaly. If you pass Go, collect €2M.");
        if (Game.getCurrentPlayer().getPosition() > Game.getBoard().getOffaly())
        {
          Game.getBank().getMoneyFromBank(2000000L);
        }
        Game.getCurrentPlayer().jumpTo(Game.getBoard().getOffaly());
        this.putCardOnBottom(drawnCard);
        Game.getBoard().tileActions();

        break;

      case (7):
        UI.displayCard("Sell your shares for a profit. Collect €1.5M.");
        // Add 1500000 to current play cash
        Game.getBank().getMoneyFromBank(1500000L);
        this.putCardOnBottom(drawnCard);
        break;

      case (6):
        UI.displayCard("GET OUT OF JAIL FREE card.\n This card may be kept until needed or sold.");
        // keep card in possession
        // Game.getCurrentPlayer().receiveCardJail();
        Game.getCurrentPlayer().receiveCard(this, drawnCard);
        break;

      default:
        break;
    }
  }

}
