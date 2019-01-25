import java.util.ArrayList;
/*
 * @author Nithiiyan Skhanthan
 * 
 * MAIN CLASS
 * - calls classes
 * - creates player array
 * - deals 5 cards to player
 * - manages win's 
 */
import java.util.Arrays;

public class Bohnanza {
	
	/*
	 * holds the array for all players
	 */
	static Player[] players;
	
	/*
	 * creates an instance of the play class to use
	 */
	private Play play;
	
	/*
	 * holds the deck
	 */
	private Deck deck;
	
	/*
	 * hold the discard pile
	 */
	private static Discard discard;
	
	private ArrayList<Card> temp;

	/*
	 * this constructor 
	 */
	public Bohnanza() {

		//int currentTurn = 1;
		
		play = new Play();
		
		players =  new Player[4];	//creates 4 players
		players[0] = new Player();
		players[1] = new Player();
		players[2] = new Player();
		players[3] = new Player();
		
		deck = new Deck();
		discard = new Discard();
		
		//creates a temp array to hold cards being transported
		temp = new ArrayList<Card>();
		
		//iterates through each player, 0-3
		for(int c = 0; c < 4; c++) {
			
			//players[c] = new Player();
			
			//adds five cards from deck to each player's hand
			for(int i = 0; i <= 5; i++){
			
				temp.add(deck.takeCardFromDeck());
		
			}
			//System.out.println(Arrays.toString(temp.toArray()));
			//System.out.println(c);
			players[c].setHand(temp);
			temp = new ArrayList<Card>(); //resets temp arraylist
		}


		while (deck.getDepletionCounter() !=3) {
			System.out.println("PLAYER 1");
			players[0] = play.start(players[0]);
			System.out.println("PLAYER 2");
			players[1] = play.start(players[1]);
			System.out.println("PLAYER 3");
			players[2] = play.start(players[2]);
			System.out.println("PLAYER 4");
			players[3] = play.start(players[3]);

		}
		deck.checkWin(players);
	}

	public static Player[] getPlayers() {
		return players;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}

	public Play getPlay() {
		return play;
	}

	public void setPlay(Play play) {
		this.play = play;
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public static Discard getDiscard() {
		return discard;
	}

	public void setDiscard(Discard discard) {
		this.discard = discard;
	}

}