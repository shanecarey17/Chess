
public class Rook extends Piece {

	/**
	 * Constructor for rook
	 * @param position Position on the board
	 * @param color Color of the piece
	 * @param board Reference to board the piece is on
	 */
	public Rook(Square position, ChessColor color, Board board) {
		super(position, color, board);
		name = "\u265c";
	}

	@Override
	boolean moveIsValid(Square destination) {
		// Can move in straight line any direction and any distance
		if(destination.xPosition == position.xPosition ^ destination.yPosition == position.yPosition) {
			if(pathIsClearStraight(destination)) {
				return true;
			}
		}
		return false;
	}
	
}
