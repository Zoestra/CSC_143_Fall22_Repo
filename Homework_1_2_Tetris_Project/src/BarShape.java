import java.awt.Color;

/**
 * An Z-Shape piece in the Tetris Game.
 * 
 * This piece is made up of 4 squares in the following configuration:
 * 
 * Sq Sq Sq Sq
 * 
 * The (row, col) coordinates are the
 * location of the second square
 * 
 * @author CSC 143
 */
public class BarShape extends AbstractPiece{

	/**
	 * Creates an Square Shape piece. See class description for actual location of r
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

	public BarShape(int r, int c, Grid g) {
		grid = g;
		square = new Square[PIECE_COUNT];
		ableToMove = true;

		// Create the squares
		square[0] = new Square(g, r, c - 1, Color.cyan, true);
		square[1] = new Square(g, r, c, Color.cyan, true);
		square[2] = new Square(g, r, c + 1, Color.cyan, true);
		square[3] = new Square(g, r, c + 2, Color.cyan, true);
	}
}
