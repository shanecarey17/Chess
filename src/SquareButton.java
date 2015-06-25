import java.awt.Font;

import javax.swing.JButton;


public class SquareButton extends JButton {

	private static final long serialVersionUID = 1L;
	Square position;
	
	/**
	 * JButton subclass representing a visual square on the board
	 * @param square The virtual position of the square
	 */
	public SquareButton(Square square) {
		this.position = square;
		setFont(new Font("Arial", Font.BOLD, 60));
		setOpaque(true);
		setBorderPainted(false);
		setFocusPainted(false);
	}

}
