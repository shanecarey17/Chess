import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ChessBoardGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int squarePixelWidth = 100;
	
	JPanel squaresPanel;
	JLabel console, whiteScore, blackScore;
	SquareButton whiteKingSquare, blackKingSquare;
	Board board;
	Game game;
	
	/**
	 * Initializes and displays GUI for chess game
	 * @param board The board to display and draw from
	 */
	public ChessBoardGUI(Game game, Board board) {
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		// References
		this.game = game;
		this.board = board;
		
		// Setup
		createMenu();
		createBoard();
		createConsole();
		
		// General UI properties
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		// Draw the board
		drawPieces();
	}
	
	/**
	 * Initializes the menu
	 */
	private void createMenu() {
		// Menu
		JMenuBar menuBar = new JMenuBar();
		JMenu optionsMenu = new JMenu("Game");
		JMenuItem forfeit = new JMenuItem("Forfeit");
		forfeit.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent evt) {
		        game.forfeitGame();
		    }
		});
		JMenuItem restart = new JMenuItem("New Game");
		restart.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent evt) {
		        game.restartGame();
		    }
		});
		JMenuItem undo = new JMenuItem("Undo");
		undo.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent evt) {
		        game.undoMove();
		    }
		});
		undo.setAccelerator(KeyStroke.getKeyStroke('Z', KeyEvent.CTRL_DOWN_MASK));
		optionsMenu.add(restart);
		optionsMenu.add(forfeit);
		optionsMenu.add(undo);
		menuBar.add(optionsMenu);
		setJMenuBar(menuBar);
	}
	
	/**
	 * Creates the board visual
	 */
	private void createBoard() {
		// Board panel
		setBounds(0, 0, board.height * squarePixelWidth, board.width * squarePixelWidth);
		GridLayout boardLayout = new GridLayout(board.width, board.height);
		squaresPanel = new JPanel();
		squaresPanel.setLayout(boardLayout);
		getContentPane().add(squaresPanel, BorderLayout.CENTER);
		
		// Squares
		for(int i = 0; i < board.width; i++) {
			for(int j = 0; j < board.height; j++) {
				// Create a button
				SquareButton square = new SquareButton(new Square(i, j));
				square.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent evt) {
						SquareButton squareClicked = (SquareButton) evt.getSource();
						game.selectSquare(squareClicked.position);
					}
				});
				
				// Create checker board
				if((i + j) % 2 == 0) {
					square.setBackground(new Color(199, 155, 93));
				} else {
					square.setBackground(new Color(245, 224, 154));
				}
				
				// Add to panel
				squaresPanel.add(square);
			}
		}
	}
	
	/**
	 * Creates the console
	 */
	private void createConsole() {
		// Console
		JPanel consolePanel = new JPanel();
		GridLayout consoleLayout = new GridLayout(1, 3);
		consolePanel.setLayout(consoleLayout);
		console = new JLabel("Welcome to Chess");
		console.setHorizontalAlignment(JLabel.CENTER);
		whiteScore = new JLabel("0");
		whiteScore.setHorizontalAlignment(JLabel.CENTER);
		blackScore = new JLabel("0");
		blackScore.setHorizontalAlignment(JLabel.CENTER);
		consolePanel.add(whiteScore);
		consolePanel.add(console, BorderLayout.CENTER);
		consolePanel.add(blackScore, BorderLayout.EAST);
		getContentPane().add(consolePanel, BorderLayout.SOUTH);
	}
	/**
	 * Sets the console output
	 * @param string The string to display
	 */
	public void setConsole(String string) {
		console.setText(string);
	}
	
	/**
	 * Updates the scores on the console
	 */
	public void updateScores() {
		whiteScore.setText("" + game.whitePlayer.score);
		blackScore.setText("" + game.blackPlayer.score);
	}
	
	/**
	 * Draws pieces on the board
	 */
	public void drawPieces() {
		for(Component component : squaresPanel.getComponents()) {
			SquareButton square = (SquareButton)component;
			Piece piece = board.getPieceAtSquare(square.position);
			if(piece != null) {
				square.setText(piece.name);
				if(piece.color == ChessColor.WHITE) {
					square.setForeground(Color.WHITE);
					if(piece instanceof King) {
						whiteKingSquare = square;
					}
				} else {
					square.setForeground(Color.BLACK);
					if(piece instanceof King) {
						blackKingSquare = square;
					}
				}
			} else {
				square.setText("");
			}
		}
			displayCheck();
	}
	
	/**
	 * If either player is in check we change the color of the square
	 */
	private void displayCheck() {
		if(board.isInCheck(this.game.whitePlayer)) {
			if(board.isInCheckMate(this.game.whitePlayer)) {
				whiteKingSquare.setForeground(Color.RED);
			} else {
				whiteKingSquare.setForeground(Color.PINK);
			}
		} else if(board.isInCheck(this.game.blackPlayer)) {
			if(board.isInCheckMate(this.game.blackPlayer)) {
				blackKingSquare.setForeground(Color.RED);
			} else {
				blackKingSquare.setForeground(Color.PINK);
			}
		}
	}
}
