import static org.junit.Assert.*;
import org.junit.Test;

public class BoardTest {

	@Test
	public void testPawn() {
		Board board = new ClassicBoard();
		Square beginning = new Square(0, 1);
		Square end = new Square(0, 2);
		board.tryMovePiece(new Player(ChessColor.WHITE, ""), beginning, end);
		assertNotNull("Pawn not moved to 0, 2", board.board[0][2]);
		assertNull("Pawn still at origin", board.board[0][1]);
	}
	
	@Test
	public void testBlackPawn() {
		Board board = new ClassicBoard();
		Square beginning = new Square(0, 6);
		Square end = new Square(0, 4);
		assertTrue(board.tryMovePiece(new Player(ChessColor.BLACK, ""), beginning, end));
	}
	
	@Test
	public void testPawnTwoSquares() {
		Board board = new ClassicBoard();
		Square beginning = new Square(0, 1);
		Square end = new Square(0, 3);
		board.tryMovePiece(new Player(ChessColor.WHITE, ""), beginning, end);
		assertNotNull("Pawn not moved to 0, 2", board.board[0][3]);
		assertNull("Pawn still at origin", board.board[0][1]);
	}
	
	@Test
	public void pawnInvalidMove() {
		Board board = new ClassicBoard();
		Square beginning = new Square(0, 1);
		Square end = new Square(1, 4);
		board.tryMovePiece(new Player(ChessColor.WHITE, ""), beginning, end);
		assertNull("Pawn  moved to 0, 2", board.board[1][4]);
		assertNotNull("Pawn not still at origin", board.board[0][1]);
	}
	
	@Test
	public void testKnight() {
		Board board = new ClassicBoard();
		Square beginning = new Square(1, 0);
		Square end = new Square(0, 2);
		board.tryMovePiece(new Player(ChessColor.WHITE, ""), beginning, end);
		assertNotNull("Pawn not moved to 0, 2", board.board[0][2]);
		assertNull("Pawn still at origin", board.board[1][0]);
	}
	
	@Test
	public void testBishop() {
		Board board = new ClassicBoard();
		Square beginning = new Square(3, 1);
		Square end = new Square(3, 2);
		board.tryMovePiece(new Player(ChessColor.WHITE, ""), beginning, end);
		
		beginning = new Square(2, 0);
		end = new Square(0, 2);
		assertFalse(board.tryMovePiece(new Player(ChessColor.WHITE, ""), beginning, end));
		
		beginning = new Square(2, 0);
		end = new Square(5, 3);
		assertTrue("Bishop not moved", board.tryMovePiece(new Player(ChessColor.WHITE, ""), beginning, end));
		
		beginning = end;
		end = new Square(3, 5);
		assertTrue(board.tryMovePiece(new Player(ChessColor.WHITE, ""), beginning, end));
		
		beginning = end;
		end = new Square(6, 1);
		assertFalse(board.tryMovePiece(new Player(ChessColor.WHITE, ""), beginning, end));
		assertNotNull(board.getPieceAtSquare(beginning));
	}
	
	@Test
	public void testRook() {
		Board board = new ClassicBoard();
		
		Square beginning = new Square(0, 0);
		Square end = new Square(0, 5);
		assertFalse(board.tryMovePiece(new Player(ChessColor.WHITE, ""), beginning, end));
		
		beginning = new Square(0, 1);
		end = new Square(0, 3);
		board.tryMovePiece(new Player(ChessColor.WHITE, ""), beginning, end);
		
		beginning = new Square(0, 0);
		end = new Square(0, 2);
		assertTrue(board.tryMovePiece(new Player(ChessColor.WHITE, ""), beginning, end));
		
		beginning = new Square(0, 2);
		end = new Square(5, 2);
		assertTrue(board.tryMovePiece(new Player(ChessColor.WHITE, ""), beginning, end));
	}
	
	@Test
	public void testPieceCapture() {
		Board board = new ClassicBoard();
		
		Square beginning = new Square(4, 1);
		Square end = new Square(4, 2);
		assertTrue(board.tryMovePiece(new Player(ChessColor.WHITE, ""), beginning, end));
		
		beginning = new Square(0, 6);
		end = new Square(0, 5);
		assertTrue(board.tryMovePiece(new Player(ChessColor.BLACK, ""), beginning, end));
		Piece capture = board.getPieceAtSquare(end);
		
		beginning = new Square(5, 0);
		Piece take = board.getPieceAtSquare(beginning);
		end = new Square(0, 5);
		assertTrue(board.tryMovePiece(new Player(ChessColor.WHITE, ""), beginning, end));
		
		assertTrue(board.blackPiecesCaptured.contains(capture));
		assertFalse(board.blackPiecesInPlay.contains(capture));
		assertTrue(board.whitePiecesInPlay.contains(take));
		assertFalse(board.whitePiecesCaptured.contains(take));
	}
	
	@Test
	public void testKingInCheckMoveInvalid() {
		Board board = new ClassicBoard();
		
		Square beginning = new Square(4, 1);
		Square end = new Square(4, 2);
		board.tryMovePiece(new Player(ChessColor.WHITE, ""), beginning, end);
		
		beginning = new Square(5, 0);
		end = new Square(1, 4);
		board.tryMovePiece(new Player(ChessColor.WHITE, ""), beginning, end);
		
		beginning = new Square(3, 6);
		end = new Square(3, 5);
		assertFalse(board.tryMovePiece(new Player(ChessColor.BLACK, ""), beginning, end));
	}
	
	@Test 
	public void testZebra() {
		Board board = new AugmentedBoard();
		
		Square beginning = new Square(4, 0);
		Square end = new Square(7, 2);
		
		assertTrue(board.tryMovePiece(new Player(ChessColor.WHITE,  ""), beginning, end));
	}
	
	@Test
	public void testMermaid() {
		Board board = new AugmentedBoard();
		
		Square beginning = new Square(2, 0);
		Square end = new Square(2, 2);
		
		assertTrue(board.tryMovePiece(new Player(ChessColor.WHITE,  ""), beginning, end));
	}
	
	@Test
	public void testCheck() {
		Board board = new ClassicBoard();
		assertTrue(board.tryMovePiece(new Player(ChessColor.WHITE, ""), new Square(4, 1), new Square(4, 3)));
		assertTrue(board.tryMovePiece(new Player(ChessColor.WHITE, ""), new Square(3, 0), new Square(5, 2)));
		assertTrue(board.tryMovePiece(new Player(ChessColor.WHITE, ""), new Square(5, 2), new Square(5, 6)));
		assertTrue(board.isInCheck(new Player(ChessColor.BLACK, "")));
	}
	
	@Test
	public void testCheckMate() {
		Board board = new ClassicBoard();
		assertTrue(board.tryMovePiece(new Player(ChessColor.WHITE, ""), new Square(4, 1), new Square(4, 3)));
		assertTrue(board.tryMovePiece(new Player(ChessColor.WHITE, ""), new Square(3, 0), new Square(5, 2)));
		assertTrue(board.tryMovePiece(new Player(ChessColor.WHITE, ""), new Square(5, 0), new Square(2, 3)));
		assertTrue(board.tryMovePiece(new Player(ChessColor.WHITE, ""), new Square(5, 2), new Square(5, 6)));
		assertTrue(board.isInCheckMate(new Player(ChessColor.BLACK, "")));
	}
	
	@Test
	public void testStaleMate() {
		Board board = new ClassicBoard();
		assertTrue(board.tryMovePiece(new Player(ChessColor.WHITE, ""), new Square(2, 1), new Square(2, 3)));
		assertTrue(board.tryMovePiece(new Player(ChessColor.BLACK, ""), new Square(7, 6), new Square(7, 4)));
		assertTrue(board.tryMovePiece(new Player(ChessColor.WHITE, ""), new Square(7, 1), new Square(7, 3)));
		assertTrue(board.tryMovePiece(new Player(ChessColor.BLACK, ""), new Square(0, 6), new Square(0, 4)));
		assertTrue(board.tryMovePiece(new Player(ChessColor.WHITE, ""), new Square(3, 0), new Square(0, 3)));
		assertTrue(board.tryMovePiece(new Player(ChessColor.BLACK, ""), new Square(0, 7), new Square(0, 5)));
		assertTrue(board.tryMovePiece(new Player(ChessColor.WHITE, ""), new Square(0, 3), new Square(0, 4)));
		assertTrue(board.tryMovePiece(new Player(ChessColor.BLACK, ""), new Square(0, 5), new Square(7, 5)));
		assertTrue(board.tryMovePiece(new Player(ChessColor.WHITE, ""), new Square(0, 4), new Square(2, 6)));
		assertTrue(board.tryMovePiece(new Player(ChessColor.BLACK, ""), new Square(5, 6), new Square(5, 5)));
		assertTrue(board.tryMovePiece(new Player(ChessColor.WHITE, ""), new Square(2, 6), new Square(3, 6)));
		assertTrue(board.tryMovePiece(new Player(ChessColor.BLACK, ""), new Square(4, 7), new Square(5, 6)));
		assertTrue(board.tryMovePiece(new Player(ChessColor.WHITE, ""), new Square(3, 6), new Square(1, 6)));
		assertTrue(board.tryMovePiece(new Player(ChessColor.BLACK, ""), new Square(3, 7), new Square(3, 2)));
		assertTrue(board.tryMovePiece(new Player(ChessColor.WHITE, ""), new Square(1, 6), new Square(1, 7)));
		assertTrue(board.tryMovePiece(new Player(ChessColor.BLACK, ""), new Square(3, 2), new Square(7, 6)));
		assertTrue(board.tryMovePiece(new Player(ChessColor.WHITE, ""), new Square(1, 7), new Square(2, 7)));
		assertTrue(board.tryMovePiece(new Player(ChessColor.BLACK, ""), new Square(5, 6), new Square(6, 5)));
		assertTrue(board.tryMovePiece(new Player(ChessColor.WHITE, ""), new Square(2, 7), new Square(4, 5)));
		assertTrue(board.isInStaleMate(new Player(ChessColor.BLACK, "")));

	}
	
}
