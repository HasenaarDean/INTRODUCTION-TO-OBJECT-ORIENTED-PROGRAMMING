import java.util.Scanner;

/**
 * The Competition class represents a Nim competition between two players, consisting of a given number of
 * rounds. It also keeps track of the number of victories of each player.
 */


public class Competition {

	private Player player1;
	private Player player2;
	private boolean displayMessage;
	private int player1Score = 0;
	private int player2Score = 0;

	//Magic Numbers:
	private static final int VALID_MOVE = 0;
	private static final String WELCOME_GREETING = "Welcome to the sticks game!";
	private static final String INVALID_MOVE = "Invalid move. Enter another:";
	private static final int ILLEGAL_PLAYER_NUMBER = -1;

	/**
	 * The class constructor, which receives two Player objects representing the two competing opponents
	 and a flag indicating whether game play messages should be printed to screen.
	 * @param player1 this parameter represents player1.
	 * @param player2 this parameter represents player2.
	 * @param displayMessage this parameter is true when a human player is playing in the game. else: false.
	 */
	public Competition(Player player1, Player player2, boolean displayMessage) {

		this.player1 = player1;
		this.player2 = player2;
		this.displayMessage = displayMessage;

	}

	/**
	 * this function gets the player's score.
	 * @param playerPosition: the position of the player (1 or 2).
	 * @return Returns the number of victories of a player. playerPosition should be 1 or 2, corresponding
	 * to the first or the second player in the competition.
	 */
	public int getPlayerScore(int playerPosition) {

		if(playerPosition == player1.getPlayerId())
			return player1Score;
		 else if(playerPosition == player2.getPlayerId())
			return player2Score;
		 else {
			return ILLEGAL_PLAYER_NUMBER;
		}
	}

	/**
	 * This function checks whether or not a message should be printed:
	 * @param text: the text that should be printed.
	 */
	private void shouldMsgBePrinted(String text){
		if(player1.getPlayerType() == Player.HUMAN || player2.getPlayerType() == Player.HUMAN) {
			this.displayMessage = true;
		}
		if(this.displayMessage){
			System.out.println(text);
		}
	}

	/**
	 * this function checks whose turn it should be during the game: of player1 or player2.
	 * @param numOfMoves: the number of moves played in the game.
	 * @return the player that should play now.
	 */
	private Player whoseTurn(int numOfMoves){
		if((numOfMoves % 2) == 0){
			return player1;
		}
		else{
			return player2;
		}
	}

	/**
	 * This method runs the competition for ”numRounds” rounds.
	 * @param numberOfRounds: the number of rounds in the game.
	 */
	public void playMultipleRounds(int numberOfRounds) {

		Player currentPlayer;
		for(int i = 0; i < numberOfRounds; i++) {
			Board board = new Board();
			int numOfMoves = 0;
			shouldMsgBePrinted(WELCOME_GREETING);

			while(board.getNumberOfUnmarkedSticks() != 0) {

				currentPlayer = whoseTurn(numOfMoves);
				shouldMsgBePrinted("Player " + currentPlayer.getPlayerId() + ", it is now your turn!");
				Move move = currentPlayer.produceMove(board);
				int moveResult = board.markStickSequence(move);

				while(moveResult != VALID_MOVE) {
					shouldMsgBePrinted(INVALID_MOVE);
					move = currentPlayer.produceMove(board);
					moveResult = board.markStickSequence(move);
				}
				numOfMoves++;
				shouldMsgBePrinted("Player " + currentPlayer.getPlayerId() + " made the move: " +
						move.toString());
			}

			currentPlayer = whoseTurn(numOfMoves);
			shouldMsgBePrinted("Player " + currentPlayer.getPlayerId() + " won!");
			if(currentPlayer == player1)
				player1Score ++;
			else
				player2Score++;

		}
		System.out.println("The results are " + getPlayerScore(player1.getPlayerId()) + ":" +
				getPlayerScore(player2.getPlayerId()));
	}

	/**
	 * The method runs a Nim competition between two players according to the three user-specified arguments. 
	 * (1) The type of the first player, which is a positive integer between 1 and 4: 1 for a Random computer
	 *     player, 2 for a Heuristic computer player, 3 for a Smart computer player and 4 for a human player.
	 * (2) The type of the second player, which is a positive integer between 1 and 4.
	 * (3) The number of rounds to be played in the competition.
	 * @param args an array of string representations of the three input arguments, as detailed above.
	 */
	public static void main(String[] args) {
		
		int p1Type = Integer.parseInt(args[0]);
		int p2Type = Integer.parseInt(args[1]);
		int numGames = Integer.parseInt(args[2]);
		final int PLAYER_1_ID = 1;
		final int PLAYER_2_ID = 2;
		Scanner scanner = new Scanner(System.in);
		Player player1 = new Player(p1Type, PLAYER_1_ID, scanner);
		Player player2 = new Player(p2Type, PLAYER_2_ID, scanner);
		boolean isThereHuman = false;
		if(player1.getPlayerType() == Player.HUMAN ||
				player2.getPlayerType() == Player.HUMAN)
			isThereHuman = true;
		Competition competition = new Competition(player1, player2, isThereHuman);
		System.out.println("Starting a Nim competition of " + numGames + " rounds between a " +
				player1.getTypeName() +
				" player and a " + player2.getTypeName() + " player.");
		competition.playMultipleRounds(numGames);
	}
}
