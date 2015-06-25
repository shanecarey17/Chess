
public class Move {
	
	Square origin;
	Square destination;
	Piece captured;
	
	/**
	 * Constructor for a move object
	 * @param origin The origin square of the move
	 * @param destination The destination square of the move
	 * @param captured The piece that was captured during the move
	 */
	public Move(Square origin, Square destination, Piece captured) {
		this.origin = origin;
		this.destination = destination;
		this.captured = captured;
	}

}
