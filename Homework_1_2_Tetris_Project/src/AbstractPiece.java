import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;

public class AbstractPiece implements piece {
	protected boolean ableToMove; // can this piece move

	protected Square[] square; // the squares that make up this piece

	// Made up of PIECE_COUNT squares
	protected Grid grid; // the board this piece is on

	// number of squares in one Tetris game piece
	protected static final int PIECE_COUNT = 4;

	/**
	 * Draws the piece on the given Graphics context
	 */
	public void draw(Graphics g) {
		for (int i = 0; i < PIECE_COUNT; i++) {
			square[i].draw(g);
		}
	}

	/**
	 * Moves the piece if possible Freeze the piece if it cannot move down
	 * anymore
	 * 
	 * @param direction
	 *                  the direction to move
	 */
	public void move(Direction direction) {
		if (canMove(direction)) {
			for (int i = 0; i < PIECE_COUNT; i++)
				square[i].move(direction);
		}
		// if we couldn't move, see if because we're at the bottom
		else if (direction == Direction.DOWN) {
			ableToMove = false;
		}
	}

	/**
	 * Returns the (row,col) grid coordinates occupied by this Piece
	 * 
	 * @return an Array of (row,col) Points
	 */
	public Point[] getLocations() {
		Point[] points = new Point[PIECE_COUNT];
		for (int i = 0; i < PIECE_COUNT; i++) {
			points[i] = new Point(square[i].getRow(), square[i].getCol());
		}
		return points;
	}

	/**
	 * Return the color of this piece
	 */
	public Color getColor() {
		// all squares of this piece have the same color
		return square[0].getColor();
	}

	/**
	 * Returns if this piece can move in the given direction
	 * 
	 */
	public boolean canMove(Direction direction) {
		if (!ableToMove)
			return false;

		// Each square must be able to move in that direction
		boolean answer = true;
		for (int i = 0; i < PIECE_COUNT; i++) {
			answer = answer && square[i].canMove(direction);
		}

		return answer;
	}
	public void rotatePiece(){
		Square[] squaresToMove = new Square[3];
		for(int i = 0; i<PIECE_COUNT;i++){
			if(i!=1){
				squaresToMove[i]=square[i];
			}
		}
		//make a list of squares 0, 2, and 3
		
		int[][] destinations = new int[3][];
		for(int i = 0;i<3;i++){
			destinations[i] = getRotateCoords(new int[]{squaresToMove[i].getRow(),squaresToMove[i].getCol()},new int[]{square[1].getRow(),square[1].getCol()});
		}
		//make a list of destination squares to check

		boolean canRotate = true;
		for(int i = 0;i<3;i++){
			if(destinations[i][0]>=grid.HEIGHT||destinations[i][1]>=grid.WIDTH||destinations[i][1]<0|| grid.isSet(destinations[i][0], destinations[i][1])){
				canRotate = false;
			}
		}
		//check the destination squares

		if(canRotate){
			//rotate the pieces!!!
		}

	}

	public int[] getRotateCoords(int[] coordinate, int[] origin) {

		int[] relativeCoords = new int[2];
		int[] targetCoords = new int[2];

		relativeCoords[0] = coordinate[0] - origin[0];
		relativeCoords[1] = coordinate[1] - origin[1];

		targetCoords[0] = -relativeCoords[1];
		targetCoords[1] = relativeCoords[0];

		targetCoords[0] += origin[0];
		targetCoords[1] += origin[1];

		return targetCoords;
	}
}
