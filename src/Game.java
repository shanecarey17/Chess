
public class Game {
	
	Board board;
	ChessBoardGUI gui;
	Player whitePlayer, blackPlayer, playerToMove;
	Square startMove, endMove;
	
	/**
	 * Constructor for game, initializes data structure and GUI
	 */
	public Game() {
		whitePlayer = new Player(ChessColor.WHITE, "White");
		blackPlayer = new Player(ChessColor.BLACK, "Black");
		board = new ClassicBoard();
		gui = new ChessBoardGUI(this, board);
		play();
	}
	
	/**
	 * Begins the game
	 */
	public void play() {
		playerToMove = whitePlayer;
		gui.setConsole(playerToMove.name + " to move");
	}
	
	/**
	 * Chooses a square as part of making a move
	 * @param square The square chosen
	 */
	public void selectSquare(Square square) {
		if(startMove != null) {
			Piece toMove = board.getPieceAtSquare(square);
			if(toMove != null && toMove.color == playerToMove.color) {
				startMove = square;
				gui.setConsole(playerToMove.name + " selected " + board.getPieceAtSquare(square).name + " at " + square);
			} else {
				endMove = square;
				playMove();
			}
		} else {
			Piece toMove = board.getPieceAtSquare(square);
			if(toMove != null && toMove.color == playerToMove.color) {
				startMove = square;
				gui.setConsole(playerToMove.name + " selected " + board.getPieceAtSquare(square).name + " at " + square);
			} else {
				gui.setConsole("Invalid selection, " + playerToMove.name + " choose a piece to move");
			}
		}
	}
	
	/**
	 * Forfeits the game on behalf of the player who's turn it is
	 */
	public void forfeitGame() {
		increaseScore(playerToMove == whitePlayer ? blackPlayer : whitePlayer);
		restartGame();
	}
	
	/**
	 * Restarts the game
	 */
	public void restartGame() {
		board = new ClassicBoard();
		gui.board = board;
		gui.drawPieces();
		play();
	}
	
	/**
	 * Undoes the last completed move and returns to the player's turn who made it
	 */
	public void undoMove() {
		if(board.undoMove()) {
			gui.drawPieces();
			switchPlayer();
			gui.setConsole("Took back move, " + playerToMove.name + " to move");
		} else {
			gui.setConsole("No moves to take back, " + playerToMove.name + " to move");
		}
	}
	
	/**
	 * Plays a move when two squares are selected
	 */
	private void playMove() {
		// Attempt to make the move
		if(board.tryMovePiece(playerToMove, startMove, endMove)) {
			// The move was valid
			gui.drawPieces();
			switchPlayer();
			if(board.isInStaleMate(playerToMove)) {
				gui.setConsole("Stalemate, draw");
			} else if(board.isInCheckMate(playerToMove)) {
				gui.setConsole("Checkmate, " + playerToMove.name);
			} else if(board.isInCheck(playerToMove)) {
				gui.setConsole(playerToMove.name + " king is checked");
			} else gui.setConsole(board.getPieceAtSquare(endMove).name + " from " + startMove + " to " + endMove + ", " + playerToMove.name + " to move");
		} else {
			// The move was not valid
			if(board.isInCheck(playerToMove)) {
				gui.setConsole("Invalid move, " + playerToMove.name + " king is checked");
			} else gui.setConsole("Invalid move, " + playerToMove.name);
		}
		startMove = endMove = null;
	}
	
	/**
	 * Switches the turn of the player
	 */
	private void switchPlayer() {
		playerToMove = playerToMove == whitePlayer ? blackPlayer : whitePlayer;
	}
	
	/**
	 * Increases the score
	 * @param winner The player who won the game
	 */
	private void increaseScore(Player winner) {
		winner.score++;
		gui.updateScores();
	}
}
