import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

/**
 * Manages the game Tetris. Keeps track of the current piece and the grid.
 * Updates the display whenever the state of the game has changed.
 * 
 * @author CSC 143
 */
public class Game {

	private Grid grid; // the grid that makes up the Tetris board

	private Tetris display; // the visual for the Tetris game

	private AbstractPiece piece; // the current piece that is dropping
	//changed from type LShape to AbstractPiece

	private boolean isOver; // has the game finished?

	/**
	 * Creates a Tetris game
	 * 
	 * @param Tetris
	 *            the display
	 */
	public Game(Tetris display) {
		grid = new Grid();
		this.display = display;
		//piece = new LShape(1, Grid.WIDTH / 2 - 1, grid);
		//same line of code is called later, so will be easier to replace with calling a method there to randomly pick a piece if it's in only one place.
		
		isOver = false;
	}

	/**
	 * Draws the current state of the game
	 * 
	 * @param g
	 *            the Graphics context on which to draw
	 */
	public void draw(Graphics g) {
		grid.draw(g);
		if (piece != null) {
			piece.draw(g);
		}
	}

	/**
	 * Moves the piece in the given direction
	 * 
	 * @param the
	 *            direction to move
	 */
	public void movePiece(Direction direction) {
		if (piece!= null && direction == Direction.DROP){
			while (piece.canMove(Direction.DOWN)){
				piece.move(Direction.DOWN);
			}
		}
		else if (piece != null) {
			piece.move(direction);
		
		}

		updatePiece();
		display.update();
		grid.checkRows();
	}

	/**
	 * Returns true if the game is over
	 */
	public boolean isGameOver() {
		// game is over if the piece occupies the same space as some non-empty
		// part of the grid. Usually happens when a new piece is made
		if (piece == null) {
			return false;
		}

		// check if game is already over
		if (isOver) {
			return true;
		}

		// check every part of the piece
		Point[] p = piece.getLocations();
		for (int i = 0; i < p.length; i++) {
			if (grid.isSet((int) p[i].getX(), (int) p[i].getY())) {
				isOver = true;
				return true;
			}
		}
		return false;
	}

	private void spawnNewPiece(){
		Random r = new Random();
		//generate random number beweeen 0 and 6, pick a piece
		switch(r.nextInt(7)){
			case 0:
			piece = new BarShape(1, Grid.WIDTH/2 -1, grid);
			break;
			case 1:
			piece = new JShape(1, Grid.WIDTH/2 -1, grid);
			break;
			case 2:
			piece = new LShape(1, Grid.WIDTH/2 -1, grid);
			break;
			case 3:
			piece = new SquareShape(1, Grid.WIDTH/2 -1, grid);
			break;
			case 4:
			piece = new SShape(1, Grid.WIDTH/2 -1, grid);
			break;
			case 5:
			piece = new TShape(1, Grid.WIDTH/2 -1, grid);
			break;
			case 6:
			piece = new ZShape(1, Grid.WIDTH/2 -1, grid);
			break;
		}
	}

	/** Updates the piece */
	private void updatePiece() {
		if (piece == null) {
			//piece = new LShape(1, Grid.WIDTH/2 -1, grid);
			//should now spawn a random piece. COMPLETELY UNTESTED
			spawnNewPiece();
		}

		// set Grid positions corresponding to frozen piece
		// and then release the piece
		else if (!piece.canMove(Direction.DOWN)) {
			Point[] p = piece.getLocations();
			Color c = piece.getColor();
			for (int i = 0; i < p.length; i++) {
				grid.set((int) p[i].getX(), (int) p[i].getY(), c);
			}
			piece = null;
		}

	}

}
