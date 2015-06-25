
public class Player {
	
	ChessColor color;
	String name;
	int score;
	
	/**
	 * Creates a player object
	 * @param color The color of the player's pieces
	 */
	public Player(ChessColor color, String name) {
		this.color = color;
		this.name = name;
	}
	
	/**
	 * Increases the score of the player
	 */
	public void increaseScore() {
		score++;
	}

}
