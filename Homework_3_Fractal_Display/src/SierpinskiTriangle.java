import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * The type Sierpinski triangle.
 */
public class SierpinskiTriangle extends AbstractShape {

    private Polygon triangle;

    /**
     * Default constructor
     */
    public SierpinskiTriangle(){

        init();
    }

    /**
     * This is the secondary constructor for the SierpinskiTriangle class. It is called by the createChildren() function.
     * It takes in a parent shape and a position. The position is used to determine the x and y coordinates of the new triangle.
     * Called by createChildren()
     *
     * @param par the parent shape
     * @param pos 1 = top center, 2 = bottom left, 3 = bottom right
     */
    //
    private SierpinskiTriangle(AbstractShape par, int pos){


        position = pos;
        parent = par;
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

        this.minX  =     x1;
        this.maxX  =     x2;
        this.minY  =     y;

        this.level = this.parent.level + 1;

        this.init();


    }


    /**
     * The init function initializes the triangle's color, width, height, and creates the triangle
     */
    private void init(){
        this.CHILDCOUNT = 3;
        this.MAXLEVEL   = 10;
        this.color      = randColor(1, level);
        this.children   = null;

        this.width      = maxX - minX;
        this.height     = (int) (width * .86);                                          // multiply width by (sqrt3)/2 to get equilateral triangle

        int[] xPoints   = new int[]{ minX + (width / 2), minX         , maxX};
        int[] yPoints   = new int[]{ minY              , minY + height, minY + height};

        this.triangle   = new Polygon(xPoints          , yPoints      , 3);
    }

    public void createChildren(){
        System.out.print("\nCreating children........");
        this.children   = new AbstractShape[]{
                new SierpinskiTriangle(this, 1),
                new SierpinskiTriangle(this, 2),
                new SierpinskiTriangle(this, 3)
        };
        System.out.println("Complete");
    }

    /**
     * If the shape has children, draw them.
     * This is a recursive function
     *
     * @param g Graphics object
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.drawPolygon(this.triangle);
        g.setColor(this.color);
        g.fillPolygon(this.triangle);


        if (this.children != null) {
            for (AbstractShape child : children) {
                child.draw(g);
            }
        }
    }

    /**
     * > Count the number of shapes in the tree, including the root
     *
     * @return The number of shapes in the tree.
     */
    @Override
    public int countShapes() {
        int count = 0;
        if (this.children != null){
            for (AbstractShape child : this.children) {
                count += child.countShapes();
            }
        }
        count += 1;
        return count;
    }

    @Override
    public void update(int value) {

    }

    public String PRINT_LOGS(int flag){

        if (this.PRINT_LOGS_FLAG) {
            StringBuilder build = new StringBuilder();
            switch (flag) {
                case 1:
                    //insert builder here
                    return build.toString();
                case 2:
                    //insert builder here
                    return build.toString();
                case 3:
                    //insert builder here
                    return build.toString();
                case 4:
                    //insert builder here
                    return build.toString();
            }
        }
        return this.toString();
    }

}
