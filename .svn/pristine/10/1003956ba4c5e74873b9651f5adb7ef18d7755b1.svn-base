
public class Zebra extends Piece {

	/**
	 * Constructor for zebra
	 * @param position Position on the board
	 * @param color Color of the piece
	 * @param board Reference to board the piece is on
	 */
	public Zebra(Square position, ChessColor color, Board board) {
		super(position, color, board);
		// TODO Auto-generated constructor stub
		name = "\u1f40e";
	}

	@Override
	boolean moveIsValid(Square destination) {
		// TODO Auto-generated method stub
		if(destination.xPosition == position.xPosition + 2 || destination.xPosition == position.xPosition - 2) {
			if(destination.yPosition == position.yPosition + 3 || destination.yPosition == position.yPosition - 3) {
				return true;
			}
		}
		if(destination.yPosition == position.yPosition + 2 || destination.yPosition == position.yPosition - 2) {
			if(destination.xPosition == position.xPosition + 3 || destination.xPosition == position.xPosition - 3) {
				return true;
			}
		}
		return false;
	}
}
