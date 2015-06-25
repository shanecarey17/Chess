import java.util.ArrayList;
import java.util.Collection;

public abstract class Board {
	
	Piece[][] board;
	Collection<Piece> whitePiecesInPlay, blackPiecesInPlay, whitePiecesCaptured, blackPiecesCaptured;
	Piece whiteKing, blackKing;
	int width, height;
	ArrayList<Move> completedMoves;
	
	/**
	 * Abstract constructor for board
	 */
	public Board() {
		// Structures for pieces
		whitePiecesInPlay = new ArrayList<Piece>();
		blackPiecesInPlay = new ArrayList<Piece>();
		whitePiecesCaptured = new ArrayList<Piece>();
		blackPiecesCaptured = new ArrayList<Piece>();
		completedMoves = new ArrayList<Move>();
	}
	
	/**
	 * Adds pieces to play
	 */
	protected void addPiecesToPlay() {
		// Add pieces to in play set
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				Piece piece = getPieceAtSquare(new Square(x, y));
				if(piece != null) {
					if(piece.color == ChessColor.WHITE) {
						whitePiecesInPlay.add(piece);
						if(piece instanceof King) {
							whiteKing = piece;
						}
					} else {
						blackPiecesInPlay.add(piece);
						if(piece instanceof King) {
							blackKing = piece;
						}
					}
				}
			}
		}
	}
	
	/**
	 * Tells if the given player is in check
	 * @param player The player to check
	 * @return TRUE if in check, FALSE otherwise
	 */
	public boolean isInCheck(Player player) {
		// Get king of input color, and array of opposing pieces in play
		Piece king;
		Collection<Piece> opposingPieces;
		if(player.color == ChessColor.WHITE) {
			opposingPieces = blackPiecesInPlay;
			king = whiteKing;
		} else {
			opposingPieces = whitePiecesInPlay;
			king = blackKing;
		}
		// Check that move to the king is not valid for any of the opposing pieces in play
		for(Piece piece : opposingPieces) {
			if(piece.moveIsValid(king.position)) {
				return true;
			}
		}
		// The king is not in check
		return false;
	}
	
	/**
	 * Tests if the player is in check mate
	 * @param player The player to test
	 * @return TRUE if player is in check mate (or stale mate), FALSE if not
	 */
	public boolean isInCheckMate(Player player) {
		// For every piece in play, see if any move is valid
		Collection<Piece> piecesToTest = player.color == ChessColor.WHITE ? whitePiecesInPlay : blackPiecesInPlay;
		for(Piece piece : piecesToTest) {
			for(int x = 0; x < width; x++) {
				for(int y = 0; y < height; y++) {
					Square destination = new Square(x, y);					
					if(tryMovePiece(player, piece.position, destination)) {
						undoMove();
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * Determines if the game/player is in stale mate
	 * @param player The player to check
	 * @return True if stale mate, false otherwise
	 */
	public boolean isInStaleMate(Player player) {
		// Stale mate if no piece has a valid move but the king is not in check
		return isInCheckMate(player) && !isInCheck(player);
	}
	
	/**
	 * Attempts to move piece to destination
	 * @param origin Square to move piece from
	 * @param destination Square to move piece to
	 * @return TRUE if the move was successful FALSE otherwise
	 */
	public boolean tryMovePiece(Player player, Square origin, Square destination) {
		// Check that square is in bounds and not same square
		if(origin.isSameAs(destination) || !isInBounds(destination)) {
			return false;
		}
		// Get piece from origin and destination, make sure they are not same team
		Piece pieceToMove = getPieceAtSquare(origin);
		// Piece to move should be same color as player
		if(pieceToMove.color != player.color) {
			return false;
		}
		Piece pieceToCapture = getPieceAtSquare(destination);
		if(pieceToCapture != null && pieceToMove.color == pieceToCapture.color) {
			return false;
		}
		// Check if the destination is valid for the piece, handled totally internally
		if(pieceToMove.moveIsValid(destination) == false) {
			return false;
		}
		// Move the piece
		movePiece(origin, destination);
		// Record the move
		completedMoves.add(new Move(origin, destination, pieceToCapture));
		// Check that king is not in check, if so move piece back
		if(isInCheck(player)) {
			undoMove();
			return false;
		}
		// Piece was moved successfully
		return true;
	}
	
	/**
	 * Undoes the last move
	 * @return Boolean of whether the move was undone
	 */
	public boolean undoMove() {
		// If no moves, return
		if(completedMoves.isEmpty()) {
			return false;
		}
		// Get the last move completed
		Move toUndo = completedMoves.remove(completedMoves.size() - 1);
		// Move our piece back to origin of the move
		Piece toMoveBack = getPieceAtSquare(toUndo.destination);
		movePiece(toUndo.destination, toUndo.origin);
		// Reduce the count of the piece
		toMoveBack.undoMove();
		// Put captured piece back in play
		Piece captured = toUndo.captured;
		if(captured != null) {
			setPieceAtSquare(captured, toUndo.destination);
			if(captured.color == ChessColor.WHITE) {
				whitePiecesCaptured.remove(captured);
				whitePiecesInPlay.add(captured);
			} else {
				blackPiecesCaptured.remove(captured);
				blackPiecesInPlay.add(captured);
			}
		}
		return true;
	}
	
	/**
	 * Getter for piece at square
	 * @param square The square to get the piece from
	 * @return A reference to the piece at the square, NULL if the square is empty
	 */
	public Piece getPieceAtSquare(Square square) {
		return board[square.xPosition][square.yPosition];
	}
	
	/**
	 * Places a piece at a square
	 * @param piece The piece to place
	 * @param square The square to place the piece at
	 */
	private void setPieceAtSquare(Piece piece, Square square) {
		board[square.xPosition][square.yPosition] = piece;
	}
	
	/**
	 * Removes a piece from a square
	 * @param square The square to remove the piece from
	 */
	private void removePieceAtSquare(Square square) {
		board[square.xPosition][square.yPosition] = null;
	}
	
	/**
	 * Moves a piece on the board
	 * @param piece The piece to move
	 * @param origin
	 * @param destination
	 */
	private void movePiece(Square origin, Square destination) {
		// Reference piece to move
		Piece pieceToMove = getPieceAtSquare(origin);
		// Capture piece at destination if necessary
		Piece captured = getPieceAtSquare(destination);
		if(captured != null) {
			if(captured.color == ChessColor.WHITE) {
				whitePiecesInPlay.remove(captured);
				whitePiecesCaptured.add(captured);
			}
			else if(captured.color == ChessColor.BLACK) {
				blackPiecesInPlay.remove(captured);
				blackPiecesCaptured.add(captured);
			}
		}
		// Move piece from origin to destination
		removePieceAtSquare(origin);
		setPieceAtSquare(pieceToMove, destination);
		// Change piece's position to destination
		pieceToMove.moveToPosition(destination);
	}
	
	/**
	 * Determines if a square is in bounds of the board
	 * @param square The square to test
	 * @return True if in bounds, false otherwise
	 */
	private boolean isInBounds(Square square) {
		if(square.xPosition > width || square.xPosition < 0 || 
				square.yPosition > height || square.yPosition < 0) {
			return false;
		} else return true;
	}

}
