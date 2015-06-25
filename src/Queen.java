
public class Queen extends Piece {

	/**
	 * Constructor for queen
	 * @param position Position on the board
	 * @param color Color of the piece
	 * @param board Reference to board the piece is on
	 */
	public Queen(Square position, ChessColor color, Board board) {
		super(position, color, board);
		name = "\u265b";
	}

	@Override
	boolean moveIsValid(Square destination) {
		// Moves diagonal or straight
		if((Math.abs(destination.xPosition - position.xPosition) == Math.abs(destination.yPosition - position.yPosition))) {
			if(pathIsClearDiagonal(destination)) {
				return true;
			}
		} else if(destination.xPosition == position.xPosition ^ destination.yPosition == position.yPosition) {
			if(pathIsClearStraight(destination)) {
				return true;
			}
		}
		return false;
	}

}
