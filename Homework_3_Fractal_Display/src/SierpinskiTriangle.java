import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * The type Sierpinski triangle.
 */
public class SierpinskiTriangle extends AbstractShape {

    private Polygon triangle;


    public SierpinskiTriangle(){


        this.minX   =   0;
        this.maxX   =   FractalDisplay.WIDTH;
        this.minY   =   0;
        this.level  =   1;

        this.setter();
    }

    public SierpinskiTriangle(AbstractShape par, int pos){


        int position = pos;
        AbstractShape parent = par;
        int x1 =        0;
        int x2 =        0;
        int  y =        0;

        switch (position) {
            case 1 -> { // top center
                x1 = parent.minX + (parent.width / 4);
                x2 = parent.maxX - (parent.width / 4);
                y  = parent.minY;
            }
            case 2 -> { // bottom left
                x1 = parent.minX + (parent.width / 2);
                x2 = parent.maxX;
                y  = parent.minY + (parent.height / 2);
            }
            case 3 -> { // bottom right
                x1 = parent.minX;
                x2 = parent.minX + (parent.width / 2);
                y  = parent.minY + (parent.height / 2);
            }
        }

        this.minX =     x1;
        this.maxX =     x2;
        this.minY =     y;

        this.level = parent.level +1;

        this.setter();
    }

    private void createTriangle(){

        int[] xPoints = new int[]{ minX + (width / 2),  minX, maxX};
        int[] yPoints = new int[]{ minY, minY + height, minY + height};

        this.triangle = new Polygon(xPoints, yPoints, 3);
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

    private void setter(){

        this.CHILDCOUNT = 3;
        this.MAXLEVEL   = 10;

        this.color  =   randColor(1,level);
        this.children = null;

        this.width  =   maxX - minX;
        this.height =   (int) (width * .86); // multiply width by (sqrt3)/2 to get equilateral triangle

        this.createTriangle();

        System.out.println("New Sierpinsky Triangle Created");
        System.out.println("Level        = " + level + " / " + MAXLEVEL );
        System.out.println("ChildCount   = " + CHILDCOUNT);
        System.out.println("Color        = " + color);
        System.out.println("Width        = " + width);
        System.out.println("Height       = " + height);
        System.out.println("Bounding Box = (" + minX + ", " + minY + "), (" + maxX + ", " + (minY + height ) + ")");

    }
    /**
     * Passes self through to super.
     * @return Returns result of super method.
     */
    @Override
    public boolean addLevel() {
        return super.addLevel(this, this.MAXLEVEL);
    }

    public boolean createChildren(@NotNull AbstractShape shape){
        System.out.print("\nCreating children........");

        if (shape.children != null) {
            System.out.println("Failed to create children, they already exist");
            return false;
        }
        shape.children = new AbstractShape[]{
                new SierpinskiTriangle(shape, 1),
                new SierpinskiTriangle(shape, 2),
                new SierpinskiTriangle(shape, 3)
                };
        System.out.println("Complete");
        return true;
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
