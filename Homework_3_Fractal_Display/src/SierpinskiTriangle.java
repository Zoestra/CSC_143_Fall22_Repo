import java.awt.*;

public class SierpinskiTriangle extends AbstractShape {

    public final int CHILDCOUNT = 3;
    public final int MAXLEVEL = 10;
    private int triWidth;
    private int triHeight;
    private Polygon triangle;

    /**
     *
     * @param x1 Minimum X coordinate
     * @param y1 Minimum Y coordinate
     * @param x2 Maximum X coordinate
     * @param l Level of triangle
     */
    public SierpinskiTriangle(int x1, int y1, int x2, int l ){

        this.minX = x1;
        this.minY = y1;
        this.maxX = x2;
        this.level = l;

        this.triWidth = maxX - minX;
        // multiply width by (sqrt3)/2 to get equilateral triangle
        this.triHeight = (int) (triWidth * .86);

        this.color = randColor(1,level);

        int[] xPoints = new int[]{minX, triWidth / 2, maxX };
        int[] yPoints = new int[]{minY + triHeight, minY, minY + triHeight};

        this.triangle = new Polygon(xPoints, yPoints, 3);
    }

    public SierpinskiTriangle(AbstractShape par, int pos){

        int position = pos;
        AbstractShape parent =

        switch position

    }
    @Override
    public void draw(Graphics g) {
        g.drawPolygon(this.triangle);
        if (this.children != null) {
            for (AbstractShape child : children) {
                child.draw(g);
            }
        }
    }

    /**
     * @return
     */
    @Override
    public boolean addLevel() {
        return super.addLevel(this, CHILDCOUNT, MAXLEVEL);
    }

    public void addChildren(){
        int newLevel = level + 1;
        this.children = new AbstractShape[3];

        // bottom left
        children[0] = new SierpinskiTriangle(minX,triHeight/2, (triWidth/2), newLevel);
        // bottom right
        children[1] = new SierpinskiTriangle(minX + (triWidth/2), triHeight/2, maxX, newLevel);
        // top center
        children[2] = new SierpinskiTriangle(minX + (triWidth/4), minY, maxX - (triWidth/4), newLevel);

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
