import java.awt.*;

public class SierpinskiTriangle extends AbstractShape {

    private final int CHILDCOUNT = 3;
    private final int MAXLEVEL = 10;

    private int triWidth;
    private int triHeight;


    public SierpinskiTriangle(Point minCorner, Point maxCorner, Graphics g) {

        this.minCorner = minCorner;
        this.maxCorner = maxCorner;
        this.triWidth = maxCorner.x - minCorner.x;
        this.triHeight = (int) (triWidth * .86);

        this.draw(g);

        if (this.children[0] != null){

        }
    }
    @Override
    public void draw(Graphics g) {
        int[] xPoints = new int[]{minCorner.x, triWidth / 2, maxCorner.x };
        int[] yPoints = new int[]{min};
        Polygon triangle = new Polygon(xPoints, yPoints, 3);


    }

    @Override
    public boolean addLevel() {
        super.addLevel(CHILDCOUNT, this, )
        return false;
    }

    @Override
    public boolean removeLevel() {
        return false;
    }

    @Override
    public int countShapes() {
        return 0;
    }

    @Override
    public void update(int value) {

    }
}
