import java.util.ArrayList;
import java.util.Scanner;

/*
 * @author Aalea
 * 
 * this class will receive the player and virtually control the player's actions.
 * it will consult the user and perform planting and harvesting
 * 
 */
public class Play {

	/*
	 * the class will receive the player to play and it will be held
	 * in this variable. After the play is over, this variable will be 
	 * sent back to where it was called from
	 */
	private static Player player; 

	private Scanner input;

	/*
	 * not constructor. will receive the current player, start keyboard input, 
	 * call necessary methods then return the player back after it has played
	 */
	public Player start(Player player) {

		this.player = player;

		input = new Scanner(System.in);

		plant();
		trade();

		//at the end of turn, draw 3 cards
		ArrayList<Card> temp = new ArrayList<Card>();

		for(int i = 0; i <= 2; i++)
			temp.add(Deck.takeCardFromDeck());

		player.setHand(temp);

		return player;

	}

	/*
	 * this method performs all steps of the turn
	 */
	void plant() {

		int field = 0; //variable to hold which field player wishes to plant/harvest in
		ArrayList<Card> temp = new ArrayList<Card>();

		System.out.println("The card dealt least recently to you is " + player.getHand().get(0));
		System.out.println("You have two fields " + player.getField(1) + " and " + player.getField(2));
		//check if fields occupied
		if (player.getField(1).getQuantity() > 0 || player.getField(2).getQuantity() > 0) {	
			System.out.println("Do you wish to harvest? (y/n)");

			if (input.next().equals("y")) {
				//if both fields can be harvested, give them a choice
				if (player.getField(1).getQuantity() > 0 && player.getField(2).getQuantity() > 0) {
					System.out.println("Which field? (1 or 2)");
					harvest(input.nextInt());
				}
				//if only field 1 can be harvested
				else if (player.getField(1).getQuantity() > 0)
					harvest(1);
				//if only field 2 can be harvested
				else if (player.getField(2).getQuantity() > 0)
					harvest(2);
			}
		}

		//if type of both field 1 and 2 are the same as the card, or both fields are empty
		if (player.getField(1).getType().equals(player.getHand().get(0).getType())
				&& player.getField(2).getType().equals(player.getHand().get(0).getType())
				|| player.getField(1).getType().equals("new")
				&& player.getField(2).getType().equals("new")) { //TO DO add condition for crop amount 1 or less

			System.out.println("Your card can be planted in both of your fields.");
			System.out.println("Do you wish to plant it in the field with " + 
					player.getField(1).getQuantity() + " crops or the field with " + 
					player.getField(1).getQuantity() + " crops? (1 or 2)");

			field = input.nextInt();

		}
		//if field 1 is same type as card or is empty
		else if(player.getField(1).getType().equals(player.getHand().get(0).getType())
				|| player.getField(1).getType().equals("new")) {

			System.out.println("Currently, you can only plant in field 1, "
					+ "do you wish to harvest and plant the card in the other field? (y/n)");

			if (input.next().equals("y")) {

				field = 2;
				harvest(field);

			}
			else {
				field = 1;
				System.out.println("You will now plant in field 1");
			}

		}
		//if field 2 is the same type as the card or is empty
		else if(player.getField(2).getType().equals(player.getHand().get(0).getType())
				|| player.getField(2).getType().equals("new")) {

			System.out.println("Currently, you can only plant in field 2, "
					+ "do you wish to harvest and plant the card in the other field? (y/n)");

			if (input.next().equals("y")) {

				field = 1;
				harvest(field);

			}
			else {
				field = 2;
				System.out.println("You will now plant in field 2");
			}

		}
		else { //both fields have different types

			System.out.println("Both cannot be planted unless you harvest.");
			System.out.println("Field 1: " + player.getField(1));
			System.out.println("Field 2: " + player.getField(2));

			System.out.println("Which field do you wish to harvest and plant in? (1 or 2)");
			field = input.nextInt();

			harvest(field);

		}

		player.getField(field).addToField(player.getHand().get(0)); //add to field will 
		//be able to detect if field 
		//is empty or not and follow
		//the correct procedure
		temp = player.getHand();
		Bohnanza.getDiscard().addCardToDiscard(temp.get(0));
		temp.remove(0);
		player.setHand(temp); //send card to discard

	}

	public void plant(Player player) {

		this.player = player;

		int field = 0; //variable to hold which field player wishes to plant/harvest in
		ArrayList<Card> temp = new ArrayList<Card>();

		System.out.println("The card dealt least recently to you is" + player.getHand().get(0));
		System.out.println("You have two fields" + player.getField(1) + " and " + player.getField(2));
		//check if fields occupied
		if (player.getField(1).getQuantity() > 0 || player.getField(2).getQuantity() > 0) {	
			System.out.println("Do you wish to harvest? (y/n)");

			if (input.next().equals("y")) {
				//if both fields can be harvested, give them a choice
				if (player.getField(1).getQuantity() > 0 && player.getField(2).getQuantity() > 0) {
					System.out.println("Which field? (1 or 2)");
					harvest(input.nextInt());
				}
				//if only field 1 can be harvested
				else if (player.getField(1).getQuantity() > 0)
					harvest(1);
				//if only field 2 can be harvested
				else if (player.getField(2).getQuantity() > 0)
					harvest(2);
			}
		}
		//if type of both field 1 and 2 are the same as the card, or both fields are empty
		if (player.getField(1).getType().equals(player.getHand().get(0).getType())
				&& player.getField(2).getType().equals(player.getHand().get(0).getType())
				|| player.getField(1).getType().equals("new")
				&& player.getField(2).getType().equals("new")) { //TO DO add condition for crop amount 1 or less

			System.out.println("Your card can be planted in both of your fields.");
			System.out.println("Do you wish to plant it in the field with " + 
					player.getField(1).getQuantity() + " crops or the field with " + 
					player.getField(1).getQuantity() + " crops? (1 or 2)");

			field = input.nextInt();

		}
		//if field 1 is same type as card or is empty
		else if(player.getField(1).getType().equals(player.getHand().get(0).getType())
				|| player.getField(1).getType().equals("new")) {

			System.out.println("Currently, you can only plant in field 1, "
					+ "do you wish to harvest and plant the card in the other field? (y/n)");

			if (input.next().equals("y")) {

				field = 2;
				harvest(field);

			}
			else {
				field = 1;
				System.out.println("You will now plant in field 1");
			}

		}
		//if field 2 is the same type as the card or is empty
		else if(player.getField(2).getType().equals(player.getHand().get(0).getType())
				|| player.getField(2).getType().equals("new")) {

			System.out.println("Currently, you can only plant in field 2, "
					+ "do you wish to harvest and plant the card in the other field? (y/n)");

			if (input.next().equals("y")) {

				field = 1;
				harvest(field);

			}
			else {
				field = 2;
				System.out.println("You will now plant in field 2");
			}

		}
		else { //both fields have different types

			System.out.println("Both cannot be planted unless you harvest.");
			System.out.println("Field 1: " + player.getField(1));
			System.out.println("Field 2: " + player.getField(2));

			System.out.println("Which field do you wish to harvest and plant in? (1 or 2)");
			field = input.nextInt();

			harvest(field);

		}

		player.getField(field).addToField(player.getHand().get(0)); //add to field will 
		//be able to detect if field 
		//is empty or not and follow
		//the correct procedure
		temp = player.getHand();
		Bohnanza.getDiscard().addCardToDiscard(temp.get(0));
		temp.remove(0);
		player.setHand(temp); //send card to discard

	}

	public static void harvest(int field) {

		//Count the number of crops of bean
		int cropAmount = player.getField(field).getQuantity();
		int coins = 0;
		int[] values = player.getField(field).getCrops().get(0).getValues();
		ArrayList<Card> temp = new ArrayList<Card>();

		//Consider the gold coin award based on the number of crops
		for(int i = 3; i >= 0; i++) {
			if (values[i]==cropAmount) {
				coins = i + 1;
			}
			else if (cropAmount % values[i] == 0) {
				coins = (i+1) *(cropAmount / values[i]);
			}
		}
		//Add coins to treasury (cards disguised as coins)
		player.setTreasuryArea(player.getTreasuryArea() + coins);
		System.out.println("You now have " + player.getTreasuryArea() + " gold coins in your treasury.");

		//Place remaining cards in discard pile
		for (int i = 0; i < cropAmount - coins; i++) {
			//add card to discard pile
			Bohnanza.getDiscard().addCardToDiscard(player.getField(field).getCrops().get(0));
			//remove card from field
			temp = player.getField(field).getCrops();
			temp.remove(0);
			player.getField(field).setCrops(temp);
		}

		player.setField(field, new Field());

	}


	private void plant(ArrayList<Card> temp) {

		int field = 0; //variable to hold which field player wishes to plant/harvest in

		System.out.println("You have two fields" + player.getField(1) + " and " + player.getField(2));
		//check if fields occupied
		if (player.getField(1).getQuantity() > 0 || player.getField(2).getQuantity() > 0) {	
			System.out.println("Do you wish to harvest? (y/n)");

			if (input.next().equals("y")) {
				//if both fields can be harvested, give them a choice
				if (player.getField(1).getQuantity() > 0 && player.getField(2).getQuantity() > 0) {
					System.out.println("Which field? (1 or 2)");
					harvest(input.nextInt());
				}
				//if only field 1 can be harvested
				else if (player.getField(1).getQuantity() > 0)
					harvest(1);
				//if only field 2 can be harvested
				else if (player.getField(2).getQuantity() > 0)
					harvest(2);
			}
		}
		//if type of both field 1 and 2 are the same as the card, or both fields are empty
		if (player.getField(1).getType().equals(player.getHand().get(0).getType())
				&& player.getField(2).getType().equals(player.getHand().get(0).getType())
				|| player.getField(1).getType().equals("new")
				&& player.getField(2).getType().equals("new")) { //TO DO add condition for crop amount 1 or less

			System.out.println("Your card can be planted in both of your fields.");
			System.out.println("Do you wish to plant it in the field with " + 
					player.getField(1).getQuantity() + " crops or the field with " + 
					player.getField(1).getQuantity() + " crops? (1 or 2)");

			field = input.nextInt();

		}
		//if field 1 is same type as card or is empty
		else if(player.getField(1).getType().equals(player.getHand().get(0).getType())
				|| player.getField(1).getType().equals("new")) {

			System.out.println("Currently, you can only plant in field 1, "
					+ "do you wish to harvest and plant the card in the other field? (y/n)");

			if (input.next().equals("y")) {

				field = 2;
				harvest(field);

			}
			else {
				field = 1;
				System.out.println("You will now plant in field 1");
			}

		}
		//if field 2 is the same type as the card or is empty
		else if(player.getField(2).getType().equals(player.getHand().get(0).getType())
				|| player.getField(2).getType().equals("new")) {

			System.out.println("Currently, you can only plant in field 2, "
					+ "do you wish to harvest and plant the card in the other field? (y/n)");

			if (input.next().equals("y")) {

				field = 1;
				harvest(field);

			}
			else {
				field = 2;
				System.out.println("You will now plant in field 2");
			}

		}
		else { //both fields have different types

			System.out.println("Both cannot be planted unless you harvest.");
			System.out.println("Field 1: " + player.getField(1));
			System.out.println("Field 2: " + player.getField(2));

			System.out.println("Which field do you wish to harvest and plant in? (1 or 2)");
			field = input.nextInt();

			harvest(field);

		}

		player.getField(field).addToField(player.getHand().get(0)); //add to field will 
		//be able to detect if field 
		//is empty or not and follow
		//the correct procedure
		temp = player.getHand();
		Bohnanza.getDiscard().addCardToDiscard(temp.get(0));
		temp.remove(0);
		player.setHand(temp); //send card to discard

	}

	private void trade() {

		//create temporary array to hold the cards player choose to be traded from trading area
		ArrayList<Card> tradeFromTArea = new ArrayList<Card>();
		//create temporary array to hold the cards player choose to be traded from hand
		ArrayList<Card> tradeFromHand = new ArrayList<Card>();
		//create temporary array to hold the cards player wish to trade for
		ArrayList<Card> wishCards = new ArrayList<Card>();
		//create temporary array to hold the cards player is currently trading with
		ArrayList<Card> currentTradeCards = new ArrayList<Card>();

		//draw 2 card from deck and add to trading area
		for(int i = 0; i < 2; i++)
			player.getTradingArea().add(Deck.takeCardFromDeck());

		//show player the 2 cards drawn from deck to trading area
		System.out.println("in your trading area you have" + player.getTradingArea().toString());
		//ask player if they want to trade
		System.out.println("Do you wish to trade? (y/n)");

		//if player don't trade, plant the 2 cards
		if(input.next().equals('n'))
			plant(player.getTradingArea());

		//if player wants to trade, start trading process
		else{

			//ask if player want to trade cards in trading area
			System.out.println("do you wish to trade cards in your trading area? (y/n)");

			//if player want to trade card in trading area
			if (input.next().equals('y')){
				//ask which cards from trading area they wish to trade
				System.out.println("which card in your trading area do you wish to trade? "
						+ "(1 or 2 or 3(both))");

				//if player only wants to trade 1st card, add 1st card into array of cards 
				//that need to be traded
				if(input.nextInt() == 1)
					tradeFromTArea.add(player.getTradingArea().get(0));
				//if player only wants to trade 2nd card, add 2nd card into array of cards 
				//that need to be traded
				else if (input.nextInt() == 2)
					tradeFromTArea.add(player.getTradingArea().get(1));
				//if player wants to trade both cards in trading area, add both cards into 
				//array of cards that need to be traded
				else
					tradeFromTArea.addAll(player.getTradingArea());
			}

			//ask if player wants to trade any cards in hand
			System.out.println("the cards you have in hand are" + player.getHand());
			System.out.println("do you wish to trade cards in hand? (y/n)");

			//if player wants to trade card in hand, ask which cards
			if(input.next().equals('y')){

				System.out.println("how many of the cards in hand do you wish to trade?");

				for(int i = 0; i < input.nextInt(); i++){
					System.out.println("which card do you wish to trade? (");
					for (int c = 1; c <= player.getHand().size(); c++){
						System.out.print(c);

						while(c != player.getHand().size())
							System.out.print("or");
					}
					System.out.print(")");

					//add the card the player chose to array of cards to be traded
					tradeFromHand.add(player.getHand().get(input.nextInt()-1));
				}

			}

			//tell player all cards they have chosen to trade
			System.out.println("here are all the cards you wish to trade" + tradeFromTArea.toString() +
					tradeFromTArea.toString());
			//ask what card they want to trade for, how many of it, and what card
			//they are going to give in return

			do{
				System.out.println("please enter the type of cards you wish to trade for");
				String wishType = input.next();

				System.out.println("please enter the number of" + wishType + "cards you wish to trade for");
				int wishQuantity = input.nextInt();
				
				/*
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * 
				 * wishCards.add(); add a certain number of type of card to wish card array given only the card type
				 */

				System.out.println("please enter the card you wish to trade with (");
				for (int c = 1; c <= (tradeFromTArea.size() + tradeFromHand.size()); c++){
					System.out.print(c);

					while(c != (tradeFromTArea.size() + tradeFromHand.size()))
						System.out.print("or");
				}
				System.out.print(")");

				if(input.nextInt() <= tradeFromTArea.size())
					currentTradeCards.add(tradeFromTArea.get(input.nextInt()-1));
				else
					currentTradeCards.add(tradeFromHand.get(input.nextInt()-1));

				//which players agree to trade
				boolean[] yes = new boolean[Bohnanza.getPlayers().length];
				int yesCounter = 0;

				//loop through all other players to ask if they want to trade with active player
				for (int i = 1; i <= Bohnanza.getPlayers().length; i++){
					System.out.println("Player " + i);
					System.out.println("Do you wish to trade " + wishQuantity + " " + wishType + 
							" card with the current active player? (y/n)");
					System.out.println("If you are the current active player, please enter 'n'");

					if (input.next().equals('y')){
						yes[i] = true;
						yesCounter++;
					}

				}

				//if all other player do not agree to trade
				//ask current active player to end trade or up their deal
				if (yesCounter == 0){
					System.out.println("no player wants to trade with you, do you wish to "
							+ "end trading or up your deal (1 or 2)");

					if(input.nextInt() == 1)
						break;
					else
						continue;
				}//if only one player wants to trade, trade
				else if (yesCounter == 1){

					for (int i = 1; i <= Bohnanza.getPlayers().length; i++){
						if(yes[i] == true)
							swapCards(i, currentTradeCards, wishCards);
					}
				}else{	//if more than 1 player want to trade with active player

					//ask active player who they want to trade with
					for (int i = 1; i <= Bohnanza.getPlayers().length; i++){

						if(yes[i] == true)
							System.out.print(" player "+ i);
					}

					System.out.println("all wants to trade with you, who do you want to"
							+ "trade with? (enter player number)");

					swapCards(input.nextInt(), currentTradeCards, wishCards);
				}

				//after one whole process of trading finishes, ask active player
				//if they want to trade more or end trading
				System.out.println("do you wish to trade more cards? (y/n)");
				
				if(input.next().equals('n'))
					break;
				else
					continue;


			}while(input.nextInt() != -1);

			//if trading has ended, plant everything in trading area
			//including all cards received from trading and original 2 cards from deck
			//if any of the 2 cards have not been traded
			plant(player.getTradingArea());

		}


	}

	private void swapCards(int playerNum, ArrayList<Card> currentTradeCards, ArrayList<Card> wishCards){
		
		//add active player's card to other player's trading area
		Bohnanza.players[playerNum].getTradingArea().addAll(currentTradeCards);
		
		//let other player plant the received card from trading
		//need to plant in other player's field
		plantOtherPlayer(playerNum, Bohnanza.players[playerNum].getTradingArea());	
		
		//add wish card to active player's trading area
		player.getTradingArea().addAll(wishCards);
		
		//remove wish card from other player's hands
		Bohnanza.players[playerNum].getHand().remove(wishCards);
		
		/*
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * remove active player's card that they traded from their trading area or hand
		 */
		

	}

	private void plantOtherPlayer(int playerNum, ArrayList<Card> tradingArea) {
		
		int field = 0; //variable to hold which field player wishes to plant/harvest in

		System.out.println("You have two fields" + Bohnanza.players[playerNum].getField(1) + " and " 
							+ Bohnanza.players[playerNum].getField(2));
		//check if fields occupied
		if (Bohnanza.players[playerNum].getField(1).getQuantity() > 0 || Bohnanza.players[playerNum].getField(2).getQuantity() > 0) {	
			System.out.println("Do you wish to harvest? (y/n)");

			if (input.next().equals("y")) {
				//if both fields can be harvested, give them a choice
				if (Bohnanza.players[playerNum].getField(1).getQuantity() > 0 && Bohnanza.players[playerNum].getField(2).getQuantity() > 0) {
					System.out.println("Which field? (1 or 2)");
					harvestOtherPlayer(playerNum, input.nextInt());
				}
				//if only field 1 can be harvested
				else if (Bohnanza.players[playerNum].getField(1).getQuantity() > 0)
					harvestOtherPlayer(playerNum, 1);
				//if only field 2 can be harvested
				else if (Bohnanza.players[playerNum].getField(2).getQuantity() > 0)
					harvestOtherPlayer(playerNum, 2);
			}
		}
		//if type of both field 1 and 2 are the same as the card, or both fields are empty
		if (Bohnanza.players[playerNum].getField(1).getType().equals(Bohnanza.players[playerNum].getHand().get(0).getType())
				&& Bohnanza.players[playerNum].getField(2).getType().equals(Bohnanza.players[playerNum].getHand().get(0).getType())
				|| Bohnanza.players[playerNum].getField(1).getType().equals("new")
				&& Bohnanza.players[playerNum].getField(2).getType().equals("new")) { //TO DO add condition for crop amount 1 or less

			System.out.println("Your card can be planted in both of your fields.");
			System.out.println("Do you wish to plant it in the field with " + 
					Bohnanza.players[playerNum].getField(1).getQuantity() + " crops or the field with " + 
					Bohnanza.players[playerNum].getField(1).getQuantity() + " crops? (1 or 2)");

			field = input.nextInt();

		}
		//if field 1 is same type as card or is empty
		else if(Bohnanza.players[playerNum].getField(1).getType().equals(Bohnanza.players[playerNum].getHand().get(0).getType())
				|| Bohnanza.players[playerNum].getField(1).getType().equals("new")) {

			System.out.println("Currently, you can only plant in field 1, "
					+ "do you wish to harvest and plant the card in the other field? (y/n)");

			if (input.next().equals("y")) {

				field = 2;
				harvestOtherPlayer(playerNum, field);

			}
			else {
				field = 1;
				System.out.println("You will now plant in field 1");
			}

		}
		//if field 2 is the same type as the card or is empty
		else if(Bohnanza.players[playerNum].getField(2).getType().equals(Bohnanza.players[playerNum].getHand().get(0).getType())
				|| Bohnanza.players[playerNum].getField(2).getType().equals("new")) {

			System.out.println("Currently, you can only plant in field 2, "
					+ "do you wish to harvest and plant the card in the other field? (y/n)");

			if (input.next().equals("y")) {

				field = 1;
				harvestOtherPlayer(playerNum, field);

			}
			else {
				field = 2;
				System.out.println("You will now plant in field 2");
			}

		}
		else { //both fields have different types

			System.out.println("Both cannot be planted unless you harvest.");
			System.out.println("Field 1: " + Bohnanza.players[playerNum].getField(1));
			System.out.println("Field 2: " + Bohnanza.players[playerNum].getField(2));

			System.out.println("Which field do you wish to harvest and plant in? (1 or 2)");
			field = input.nextInt();

			harvestOtherPlayer(playerNum, field);

		}

		Bohnanza.players[playerNum].getField(field).addToField(Bohnanza.players[playerNum].getHand().get(0)); //add to field will 
		//be able to detect if field 
		//is empty or not and follow
		//the correct procedure
		tradingArea = Bohnanza.players[playerNum].getHand();
		Bohnanza.getDiscard().addCardToDiscard(tradingArea.get(0));
		tradingArea.remove(0);
		Bohnanza.players[playerNum].setHand(tradingArea); //send card to discard

		
	}

	public static void harvestOtherPlayer(int playerNum, int field) {

		//Count the number of crops of bean
		int cropAmount = Bohnanza.players[playerNum].getField(field).getQuantity();
		int coins = 0;
		int[] values = Bohnanza.players[playerNum].getField(field).getCrops().get(0).getValues();
		ArrayList<Card> temp = new ArrayList<Card>();

		//Consider the gold coin award based on the number of crops
		for(int i = 3; i >= 0; i++) {
			if (values[i]==cropAmount) {
				coins = i + 1;
			}
			else if (cropAmount % values[i] == 0) {
				coins = (i+1) *(cropAmount / values[i]);
			}
		}
		//Add coins to treasury (cards disguised as coins)
		Bohnanza.players[playerNum].setTreasuryArea(Bohnanza.players[playerNum].getTreasuryArea() + coins);
		System.out.println("You now have " + Bohnanza.players[playerNum].getTreasuryArea() + " gold coins in your treasury.");

		//Place remaining cards in discard pile
		for (int i = 0; i < cropAmount - coins; i++) {
			//add card to discard pile
			Bohnanza.getDiscard().addCardToDiscard(Bohnanza.players[playerNum].getField(field).getCrops().get(0));
			//remove card from field
			temp = Bohnanza.players[playerNum].getField(field).getCrops();
			temp.remove(0);
			Bohnanza.players[playerNum].getField(field).setCrops(temp);
		}

		Bohnanza.players[playerNum].setField(field, new Field());

	}


}
