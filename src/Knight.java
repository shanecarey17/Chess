
public class Knight extends Piece {

	/**
	 * Constructor for knight
	 * @param position Position on the board
	 * @param color Color of the piece
	 * @param board Reference to board the piece is on
	 */
	public Knight(Square position, ChessColor color, Board board) {
		super(position, color, board);
		// TODO Auto-generated constructor stub
		name = "\u265e";
	}

	@Override
	boolean moveIsValid(Square destination) {
		if(destination.xPosition == position.xPosition + 2 || destination.xPosition == position.xPosition - 2) {
			if(destination.yPosition == position.yPosition + 1 || destination.yPosition == position.yPosition - 1) {
				return true;
			}
		}
		if(destination.yPosition == position.yPosition + 2 || destination.yPosition == position.yPosition - 2) {
			if(destination.xPosition == position.xPosition + 1 || destination.xPosition == position.xPosition - 1) {
				return true;
			}
		}
		return false;
	}

}
