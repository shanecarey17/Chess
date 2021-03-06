
public class Square {
	
	int xPosition;
	int yPosition;
	
	/**
	 * Creates a square on the chess board, used for position
	 * @param x The x location
	 * @param y The y location
	 */
	public Square(int x, int y) {
		this.xPosition = x;
		this.yPosition = y;
	}
	
	/**
	 * Compares two squares for equality
	 * @param compare The square to compare
	 * @return TRUE if the squares are the same, FALSE otherwise
	 */
	public boolean isSameAs(Square compare) {
		if(this.xPosition == compare.xPosition && this.yPosition == compare.yPosition) {
			return true;
		} else return false;
	}
	
	@Override
	public String toString() {
		char rank = (char)('A' + xPosition);
		return rank + "" + (yPosition + 1);
	}
	
}
