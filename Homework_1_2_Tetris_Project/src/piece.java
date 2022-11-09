import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
interface piece {
    public void draw(Graphics g);
    public void move(Direction direction);
    public Point[] getLocations();
    public Color getColor();
    public boolean canMove(Direction direction);
    //Added empty methods so a class that implements it won't compile until these are replaced by it

    // public void rotate(boolean cw);
}
