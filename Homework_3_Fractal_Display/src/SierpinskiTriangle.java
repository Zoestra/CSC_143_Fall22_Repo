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
        this.position = 0;
        this.parent = null;
        this.init();
    }

    public SierpinskiTriangle(AbstractShape par, int pos){


        this.position = pos;
        this.parent = par;
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

        this.level = this.parent.level +1;

        this.init();


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

    private void init(){

        this.CHILDCOUNT = 3;
        this.MAXLEVEL   = 10;

        this.color  =   randColor(1,level);
        this.children = null;

        this.width  =   maxX - minX;
        this.height =   (int) (width * .86); // multiply width by (sqrt3)/2 to get equilateral triangle

        this.createTriangle();

    }
    /**
     * Passes self through to super.
     * @return Returns result of super method.
     */
//    @Override
//    public boolean addLevel() {
//        return super.addLevel();
//    }

    public void createChildren(){
        System.out.print("\nCreating children........");
        this.children = new AbstractShape[]{
                new SierpinskiTriangle(this, 1),
                new SierpinskiTriangle(this, 2),
                new SierpinskiTriangle(this, 3)
                };
        System.out.println("Complete");
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

    public String idBuilder(){
        StringBuilder build = new StringBuilder();
        build.append("Level: ").append(this.level).append(",");
        build.append(" Position: ").append(this.position).append(", ");
        if (this.parent != null){
            build.append("Parent Position: ").append(this.parent.position);
        }


        return build.toString();
    }

}
