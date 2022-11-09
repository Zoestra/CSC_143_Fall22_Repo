import java.awt.Color;

/**
 * An Z-Shape piece in the Tetris Game.
 * 
 * This piece is made up of 4 squares in the following configuration:
 * 
 * Sq Sq
 *    Sq Sq
 * 
 * The (row, col) coordinates are the
 * location of the middle Square on the first row
 * 
 * @author CSC 143
 */
public class ZShape extends AbstractPiece{

	/**
	 * Creates an Z-Shape piece. See class description for actual location of r
	 * and c
	 * 
	 * @param r
	 *            row location for this piece
	 * @param c
	 *            column location for this piece
	 * @param g
	 *            the grid for this game piece
	 * 
	 */

	public ZShape(int r, int c, Grid g) {
		grid = g;
		square = new Square[PIECE_COUNT];
		ableToMove = true;

		// Create the squares
		square[0] = new Square(g, r, c - 1, Color.red, true);
		square[1] = new Square(g, r, c, Color.red, true);
		square[2] = new Square(g, r + 1, c, Color.red, true);
		square[3] = new Square(g, r + 1, c + 1, Color.red, true);
	}
}
