
public class Mermaid extends Piece {

	/**
	 * Constructor for mermaid
	 * @param position Position on the board
	 * @param color Color of the piece
	 * @param board Reference to board the piece is on
	 */
	public Mermaid(Square position, ChessColor color, Board board) {
		super(position, color, board);
		// TODO Auto-generated constructor stub
		name = "\u1f704";
	}

	@Override
	boolean moveIsValid(Square destination) {
		// TODO Auto-generated method stub
		if(Math.abs(destination.xPosition - position.xPosition) == 2 && Math.abs(destination.yPosition - position.yPosition) == 2
				|| Math.abs(destination.xPosition - position.xPosition) == 2 && Math.abs(destination.yPosition - position.yPosition) == 0
				|| Math.abs(destination.xPosition - position.xPosition) == 0 && Math.abs(destination.yPosition - position.yPosition) == 2) {
			return true;
		}
		return false;
	}
}
