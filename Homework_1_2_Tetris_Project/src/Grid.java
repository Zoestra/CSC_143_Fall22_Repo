import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.lang.model.util.ElementScanner6;

/**
 * This is the Tetris board represented by a (HEIGHT - by - WIDTH) matrix of
 * Squares.
 * 
 * The upper left Square is at (0,0). The lower right Square is at (HEIGHT -1,
 * WIDTH -1).
 * 
 * Given a Square at (x,y) the square to the left is at (x-1, y) the square
 * below is at (x, y+1)
 * 
 * Each Square has a color. A white Square is EMPTY; any other color means that
 * spot is occupied (i.e. a piece cannot move over/to an occupied square). A
 * grid will also remove completely full rows.
 * 
 * @author CSC 143
 */
public class Grid {
	private Square[][] board;

	// Width and Height of Grid in number of squares
	public static final int HEIGHT = 20;

	public static final int WIDTH = 10;

	private static final int BORDER = 5;

	public static final int LEFT = 100; // pixel position of left of grid

	public static final int TOP = 50; // pixel position of top of grid

	public static final Color EMPTY = Color.WHITE;

	/**
	 * Creates the grid
	 */
	public Grid() {
		board = new Square[HEIGHT][WIDTH];

		// put squares in the board
		for (int row = 0; row < HEIGHT; row++) {
			for (int col = 0; col < WIDTH; col++) {
				board[row][col] = new Square(this, row, col, EMPTY, false);

			}
		}

	}

	/**
	 * Returns true if the location (row, col) on the grid is occupied
	 * 
	 * @param row
	 *            the row in the grid
	 * @param col
	 *            the column in the grid
	 */
	public boolean isSet(int row, int col) {
		return !board[row][col].getColor().equals(EMPTY);
	}

	/**
	 * Changes the color of the Square at the given location
	 * 
	 * @param row
	 *            the row of the Square in the Grid
	 * @param col
	 *            the column of the Square in the Grid
	 * @param c
	 *            the color to set the Square
	 * @throws IndexOutOfBoundsException
	 *             if row < 0 || row>= HEIGHT || col < 0 || col >= WIDTH
	 */
	public void set(int row, int col, Color c) {
		board[row][col].setColor(c);
	}

	/**
	 * Checks for and remove all solid rows of squares.
	 * 
	 * If a solid row is found and removed, all rows above it are moved down and
	 * the top row set to empty
	 */
	public void checkRows() {
		ArrayList<Integer> fullRows = new ArrayList<>();
		ArrayList<Integer> filledRows = new ArrayList<>();
		for (int row = HEIGHT - 1; row >= 0; row--) {
			int flag = checkRowFull(row);
			switch (flag){
				case 0:  //row is empty
					break;
				case 1:  //row filled but not full
					filledRows.add(row);
					break;
				case 2:  //row is full
					fullRows.add(row);
					break;
			}
			
		}
		for (Integer row : fullRows) {
			clearRow(row);	
		}
		//System.out.println(filledRows.toString());
		for (Integer i : filledRows) {
			//System.out.println("dropping row " + i);
			dropRow(i);
		}
	}
	
	/**
	 * Moves a whole row of squares down as far as they can go
	 * Private helper method
	 * 
	 * @param row index of row in grid matrix
	 */
	private void dropRow(int row){

		if (rowCanMove(row)){
			for (int col = 0; col < WIDTH; col ++){
				board[row + 1][col].setColor(board[row][col].getColor());
				board[row][col].setColor(EMPTY);
			}
		}
	}

	private Boolean rowCanMove(int row){
		if(row==HEIGHT-1){
			return false;
		}
		for (int col = 0; col < WIDTH; col ++) {
			if (  isSet(row+1, col)){
				//System.out.println("row" + row + "and col" + col + "cannot move");
				return false;
			}
		}
		//System.out.println("row" + row + "can move");
		return true;
	}

	/**
	 * Checks if row is full.
	 * Private helper method
	 * 
	 * @param row index of row in grid matrix
	 * @return boolean full or not
	 */
	private int checkRowFull(int row){
		int filled = 0;
		for (int col = 0; col < WIDTH; col ++) {
			if (isSet(row, col)){
				filled ++;
			}	
		}
		if (filled == 0){
			return filled;
		}
		else if (filled < WIDTH){
			return 1;
		}
		else {
			return 2;
		}
	}
	
		

	private void clearRow(int row){
		for (int col = 0 ; col < WIDTH; col++) {
			board[row][col].setColor(EMPTY);		
		}
	}

	/**
	 * Draws the grid on the given Graphics context
	 */
	public void draw(Graphics g) {

		// draw the edges as rectangles: left, right in blue then bottom in red
		g.setColor(Color.BLUE);
		g.fillRect(LEFT - BORDER, TOP, BORDER, HEIGHT * Square.HEIGHT);
		g.fillRect(LEFT + WIDTH * Square.WIDTH, TOP, BORDER, HEIGHT
				* Square.HEIGHT);
		g.setColor(Color.RED);
		g.fillRect(LEFT - BORDER, TOP + HEIGHT * Square.HEIGHT, WIDTH
				* Square.WIDTH + 2 * BORDER, BORDER);

		// draw all the squares in the grid
		// empty ones first (to avoid masking the black lines of the pieces that
		// have already fallen)
		for (int r = 0; r < HEIGHT; r++) {
			for (int c = 0; c < WIDTH; c++) {
				if (board[r][c].getColor().equals(EMPTY)) {
					board[r][c].draw(g);
				}
			}
		}
		for (int r = 0; r < HEIGHT; r++) {
			for (int c = 0; c < WIDTH; c++) {
				if (!board[r][c].getColor().equals(EMPTY)) {
					board[r][c].draw(g);
				}
			}
		}
	}
}
