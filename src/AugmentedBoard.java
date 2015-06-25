
public class AugmentedBoard extends Board {
	
	/**
	 * Constructor for augmented chess board, includes extra pieces
	 */
	public AugmentedBoard() {
		// Initializes data structures
		super();
				
		// Size of board
		width = 12;
		height = 8;
		board = new Piece[width][height];
		
		// White Pieces
		board[0][0] = new Rook(new Square(0, 0), ChessColor.WHITE, this);
		board[1][0] = new Knight(new Square(1, 0), ChessColor.WHITE, this);
		board[2][0] = new Mermaid(new Square(2, 0), ChessColor.WHITE, this);
		board[3][0] = new Bishop(new Square(3, 0), ChessColor.WHITE, this);
		board[4][0] = new Zebra(new Square(4, 0), ChessColor.WHITE, this);
		board[5][0] = new Queen(new Square(5, 0), ChessColor.WHITE, this);
		board[6][0] = new King(new Square(6, 0), ChessColor.WHITE, this);
		board[7][0] = new Zebra(new Square(7, 0), ChessColor.WHITE, this);
		board[8][0] = new Bishop(new Square(8, 0), ChessColor.WHITE, this);
		board[9][0] = new Mermaid(new Square(9, 0), ChessColor.WHITE, this);
		board[10][0] = new Knight(new Square(10, 0), ChessColor.WHITE, this);
		board[11][0] = new Rook(new Square(11, 0), ChessColor.WHITE, this);
				
		board[0][1] = new Pawn(new Square(0, 1), ChessColor.WHITE, this);
		board[1][1] = new Pawn(new Square(1, 1), ChessColor.WHITE, this);
		board[2][1] = new Pawn(new Square(2, 1), ChessColor.WHITE, this);
		board[3][1] = new Pawn(new Square(3, 1), ChessColor.WHITE, this);
		board[4][1] = new Pawn(new Square(4, 1), ChessColor.WHITE, this);
		board[5][1] = new Pawn(new Square(5, 1), ChessColor.WHITE, this);
		board[6][1] = new Pawn(new Square(6, 1), ChessColor.WHITE, this);
		board[7][1] = new Pawn(new Square(7, 1), ChessColor.WHITE, this);
		board[8][1] = new Pawn(new Square(8, 1), ChessColor.WHITE, this);
		board[9][1] = new Pawn(new Square(9, 1), ChessColor.WHITE, this);
		board[10][1] = new Pawn(new Square(10, 1), ChessColor.WHITE, this);
		board[11][1] = new Pawn(new Square(11, 1), ChessColor.WHITE, this);
				
		// Black Pieces
		board[0][6] = new Pawn(new Square(0, 6), ChessColor.BLACK, this);
		board[1][6] = new Pawn(new Square(1, 6), ChessColor.BLACK, this);
		board[2][6] = new Pawn(new Square(2, 6), ChessColor.BLACK, this);
		board[3][6] = new Pawn(new Square(3, 6), ChessColor.BLACK, this);
		board[4][6] = new Pawn(new Square(4, 6), ChessColor.BLACK, this);
		board[5][6] = new Pawn(new Square(5, 6), ChessColor.BLACK, this);
		board[6][6] = new Pawn(new Square(6, 6), ChessColor.BLACK, this);
		board[7][6] = new Pawn(new Square(7, 6), ChessColor.BLACK, this);
		board[8][6] = new Pawn(new Square(8, 6), ChessColor.BLACK, this);
		board[9][6] = new Pawn(new Square(9, 6), ChessColor.BLACK, this);
		board[10][6] = new Pawn(new Square(10, 6), ChessColor.BLACK, this);
		board[11][6] = new Pawn(new Square(11, 6), ChessColor.BLACK, this);
				
		board[0][7] = new Rook(new Square(0, 7), ChessColor.BLACK, this);
		board[1][7] = new Knight(new Square(1, 7), ChessColor.BLACK, this);
		board[2][7] = new Mermaid(new Square(2, 7), ChessColor.BLACK, this);
		board[3][7] = new Bishop(new Square(3, 7), ChessColor.BLACK, this);
		board[4][7] = new Zebra(new Square(4, 7), ChessColor.BLACK, this);
		board[5][7] = new Queen(new Square(5, 7), ChessColor.BLACK, this);
		board[6][7] = new King(new Square(6, 7), ChessColor.BLACK, this);
		board[7][7] = new Zebra(new Square(7, 7), ChessColor.BLACK, this);
		board[8][7] = new Bishop(new Square(8, 7), ChessColor.BLACK, this);
		board[9][7] = new Mermaid(new Square(9, 7), ChessColor.BLACK, this);
		board[10][7] = new Knight(new Square(10, 7), ChessColor.BLACK, this);
		board[11][7] = new Rook(new Square(11, 7), ChessColor.BLACK, this);
		
		addPiecesToPlay();
	}
	
}
