
public class CardsCommunity extends Cards
//Glen
{
	public void pickCard() 
	{
	    int drawnCard = this.getTopCard();
	    
	    switch (drawnCard) {
	      
	      case(0):
		    	UI.displayCard("You win tickets to a sold out All-Ireland final\n and sell for a huge profit. Collect €200K.");
		        //Add 200000 to current player cash.
	      		Game.getBank().getMoneyFromBank(200000L);
		        this.putCardOnBottom(drawnCard);
		        break;
	        
	      case(1):
		    	UI.displayCard("Advance to Go. Collect €2M.");
		        //Move player to Go.
	      		Game.getCurrentPlayer().jumpTo(0);
	      		//Add 2000000 to player cash.
	      		//Game.getCurrentPlayer().getMoney(2000000L);
	      		Game.getBank().getMoneyFromBank(2000000L);
		        this.putCardOnBottom(drawnCard);
		        break;
		        
	      case(2):
		    	UI.displayCard("Pay €500K for a 5-star weekend spa break in Donegal.");
		        //Remove 500000 from current player cash.
	      		Game.getBank().returnMoneyToBank(500000L);
		        this.putCardOnBottom(drawnCard);
		        break;
		        
	      case(3):
		    	UI.displayCard("Advance to Westmeath.");
	        	//Move current player to Westmeath.
	      		Game.getCurrentPlayer().jumpTo(Game.getBoard().getWestMeath());
		        this.putCardOnBottom(drawnCard);
		        Game.getBank().getMoneyFromBank(2000000L);
		        Game.getBoard().tileActions();
		        break;
		        
	      case(4):
		    	UI.displayCard("Collect €1M profits for chartering your private helicopter.");
		        //Add 1000000 to player cash.
	      		Game.getBank().getMoneyFromBank(1000000L);
		        this.putCardOnBottom(drawnCard);
		        break;
		        
	      case(5):
		    	UI.displayCard("Your colleagues hire your holiday home for a week.\n Collect €100K from each player.");
		        //Add 100000 * number of other players to current player cash.
	      		for (Player player:Game.getOtherPlayers()){
	      			player.payToPlayer(Game.getCurrentPlayer(), 100000L);
	      		}
		        this.putCardOnBottom(drawnCard);
		        break;
		        
	      case(6):
		    	UI.displayCard("Your car insurance claim is settled. Collect €250K.");
	      		//Add 250000 to current player cash.
	      		Game.getBank().getMoneyFromBank(250000L);
		        this.putCardOnBottom(drawnCard);
		        break;
		        
	      case(7):
		    	UI.displayCard("GET OUT OF JAIL FREE card.\n This card may be kept until needed or sold.");
	      		//keep card in possession
	      		//Game.getCurrentPlayer().receiveCardJail();
	          Game.getCurrentPlayer().receiveCard(this, drawnCard);
		        break;
	      
	      case(8):
		    	UI.displayCard("You are investigated for identity fraud. Go to jail.\n Move directly to jail. Do not pass go.\n Do not collect €2M.");
		        //Move current player to jail.
	      		Game.getCurrentPlayer().jumpTo(Game.getBoard().getJailPosition());
		        this.putCardOnBottom(drawnCard);
		        break;
	      
	      default:
	        break;
	    }
	}
}
