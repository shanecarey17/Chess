
public abstract class Piece {
	
	Square position;
	ChessColor color;
	Board board;
	String name;
	
	protected int timesMoved;
	
	/**
	 * Constructor for a piece
	 * @param position The position to initialize the piece
	 * @param color The color of the piece
	 * @param board The board the piece is on
	 */
	public Piece(Square position, ChessColor color, Board board) {
		this.position = position;
		this.color = color;
		this.board = board;
		timesMoved = 0;
	}
	
	/**
	 * Calculates whether the move is valid on the given board for this piece
	 * @param destination The square to test if the move to is valid
	 * @return TRUE if the move is valid FALSE otherwise
	 */
	abstract boolean moveIsValid(Square destination);

	/**
	 * Moves piece to destination square
	 * @param destination The square to move the piece to
	 */
	public void moveToPosition(Square destination) {
		timesMoved++;
		position = destination;
	}
	
	/**
	 * Sets back the times moved counter in case a move needs to be taken back
	 */
	public void undoMove() {
		timesMoved -= 2;
	}
	
	@Override
	public String toString() {
		return new String(this.color + " " + this.getClass() + "@" + this.position.xPosition + "," + this.position.yPosition +  " Moved " + timesMoved + " times\n");
	}
	
	/**
	 * Determines if straight path is clear
	 * @param destination The square to move to
	 * @return True if clear, false otherwise
	 */
	protected boolean pathIsClearStraight(Square destination) {
		if(destination.xPosition == position.xPosition) {
			if(destination.yPosition > position.yPosition) {
				for(int row = destination.yPosition - 1; row > position.yPosition; row--) {
					Square test = new Square(position.xPosition, row);
					if(board.getPieceAtSquare(test) != null) {
						return false;
					}
				}
			}
			if(destination.yPosition < position.yPosition) {
				for(int row = destination.yPosition + 1; row < position.yPosition; row++) {
					Square test = new Square(position.xPosition, row);
					if(board.getPieceAtSquare(test) != null) {
						return false;
					}
				}
			}
		}
		if(destination.yPosition == position.yPosition) {
			if(destination.xPosition > position.xPosition) {
				for(int column = destination.xPosition - 1; column > position.xPosition; column--) {
					Square test = new Square(column, position.yPosition);
					if(board.getPieceAtSquare(test) != null) {
						return false;
					}
				}
			}
			if(destination.xPosition < position.xPosition) {
				for(int column = destination.xPosition + 1; column < position.xPosition; column++) {
					Square test = new Square(column, position.yPosition);
					if(board.getPieceAtSquare(test) != null) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * Determines if diagonal path is clear
	 * @param destination The square to move to
	 * @return True if clear, false otherwise
	 */
	protected boolean pathIsClearDiagonal(Square destination) {
		if(destination.xPosition > position.xPosition && destination.yPosition > position.yPosition) {
			int x = destination.xPosition - 1;
			int y = destination.yPosition - 1;
			while(x > position.xPosition && y > position.yPosition) {
				Square test = new Square(x, y);
				if(board.getPieceAtSquare(test) != null) {
					return false;
				}
				x--;
				y--;
			}
		}
		else if(destination.xPosition > position.xPosition && destination.yPosition < position.yPosition) {
			int x = destination.xPosition - 1;
			int y = destination.yPosition + 1;
			while(x > position.xPosition && y < position.yPosition) {
				Square test = new Square(x, y);
				if(board.getPieceAtSquare(test) != null) {
					return false;
				}
				x--;
				y++;
			}
		}
		else if(destination.xPosition < position.xPosition && destination.yPosition > position.yPosition) {
			int x = destination.xPosition + 1;
			int y = destination.yPosition - 1;
			while(x < position.xPosition && y > position.yPosition) {
				Square test = new Square(x, y);
				if(board.getPieceAtSquare(test) != null) {
					return false;
				}
				x++;
				y--;
			}
		}
		else if(destination.xPosition < position.xPosition && destination.yPosition < position.yPosition) {
			int x = destination.xPosition + 1;
			int y = destination.yPosition + 1;
			while(x < position.xPosition && y < position.yPosition) {
				Square test = new Square(x, y);
				if(board.getPieceAtSquare(test) != null) {
					return false;
				}
				x++;
				y++;
			}
		}
		return true;
	}

}
