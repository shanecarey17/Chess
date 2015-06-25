
public class Bishop extends Piece {

	/**
	 * Constructor for bishop
	 * @param position Position on the board
	 * @param color Color of the piece
	 * @param board Reference to board the piece is on
	 */
	public Bishop(Square position, ChessColor color, Board board) {
		super(position, color, board);
		name = "\u265d";
	}

	/**
	 * Overrides superclass abstract method, determines if move to a particular square is valid for piece
	 */
	@Override
	boolean moveIsValid(Square destination) {
		if(Math.abs(destination.xPosition - position.xPosition) == Math.abs(destination.yPosition - position.yPosition)) {
			if(pathIsClearDiagonal(destination)) {
				return true;
			}
		}
		return false;
	}

}
