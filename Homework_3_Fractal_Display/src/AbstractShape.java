import java.awt.*;
import java.util.Random;

public abstract class AbstractShape implements Shape {


    public int CHILDCOUNT;
    public int MAXLEVEL;
    protected Color color;
    protected AbstractShape[] children      = null;
    protected AbstractShape parent          = null;
    protected int position                  = 0;
    protected int level                     = 1;
    // these define the available draw area
    protected int minX                      = 0;
    protected int minY                      = 0;
    protected int maxX                      = FractalDisplay.WIDTH;
    protected int width                     = FractalDisplay.WIDTH;
    protected int height                    = FractalDisplay.HEIGHT;


    public abstract String PRINT_LOGS(int c);
    protected final boolean PRINT_LOGS_FLAG = true;


    public abstract void createChildren();

    /**
     * Recursively adds a fractal layer to the displayed shape.
     * If the shape has children, add a level to each child. If the shape doesn't have children, create them
     *
     * @return Returns false when max level is exceeded.
     */
    public boolean addLevel(){
        AbstractShape shape = this;

        //System.out.print("\nAdd Level from: " + shape.idBuilder());

        if (shape.level >= shape.MAXLEVEL){
            //System.out.println("Failed, level would exceed maximum of " + shape.MAXLEVEL);
            return false;
        }

        if (shape.children != null ) {
            for (AbstractShape child: shape.children) {
                child.addLevel();
            }
        }
        else shape.createChildren();
        return true;
    }


    /**
     * If the shape has children, call removeLevel() on each child. If the shape has no children,
     * set the parent's children to null
     *
     * @return returns false if only one layer exists
     */
    public boolean removeLevel(){
        AbstractShape shape = this;

        if (shape.level == 1 && shape.children == null){
            System.out.println("Failed, only one layer exists");
            return false;
        }

        if (shape.children != null) {
            for (AbstractShape child: shape.children) {
                child.removeLevel();
            }
        }
        else
            this.parent.children = null;


        System.out.println("Level added successfully");
        return true;

    }

    /**
     * This function takes in a palette number and a level number and returns a random color from the palette
     *
     * @param palette 1 = sierpinsky triangle, 2 = H Shape, 3 = MyShape
     * @param level the level of recursion
     * @return A random color from a palette of colors.
     */
    public Color randColor(int palette, int level) {

        Random r =      new Random();
        int rand =      r.nextInt(5) + 1;

        switch (palette) {
            case 1: // palette for sierpinsky triangle
                switch (rand) {
                    case 1: // Indigo Dye
                        return Color.decode("#083D77");
                    case 2: // Russian Green
                        return Color.decode("#648767");
                    case 3: // Cerise
                        return Color.decode("#DA4167");
                    case 4: // Orange Yellow
                        return Color.decode("#F4D35E");
                    case 5: // Coral
                        return Color.decode("#F78764");

                }
            case 2: // palette for sierpinsky triangle
                switch (rand) {
                    case 1: // Indigo Dye
                        return Color.decode("#1B1B3A");
                    case 2: // Russian Green
                        return Color.decode("#693668");
                    case 3: // Cerise
                        return Color.decode("#A74482");
                    case 4: // Orange Yellow
                        return Color.decode("#F84AA7");
                    case 5: // Coral
                        return Color.decode("#FF3562");

                }
//            case 2: // palette for H Shape
//                switch (rand) {
//                    case 1: // Space Cadet
//                        return Color.decode("#1B1B3A");
//                    case 2: // Palatinate Purple
//                        return Color.decode("#693668");
//                    case 3: // Maximum Red Purple
//                        return Color.decode("#A74482");
//                    case 4: // Magenta
//                        return Color.decode("#F84AA7");
//                    case 5: // Radical Red
//                        return Color.decode("#FF3562");
//                }
            case 3: // palette for MyShape
                switch (level) {
                    case 1: // Rosewood
                        return Color.decode("#520000");
                    case 2: // Blood Red
                        return Color.decode("#710000");
                    case 3: // Kobe
                        return Color.decode("#8C1E03");
                    case 4: // Rust
                        return Color.decode("#A63C06");
                    case 5: // Windsor TanS
                        return Color.decode("#B55608");
                    case 6: // Ochre
                        return Color.decode("#C36F09");
                    case 7: // Harvest Gold
                        return Color.decode("#D9950A");
                    case 8: // Orange Yellow
                        return Color.decode("#EEBA0B");
                    case 9: // Yellow Munsell
                        return Color.decode("#F1CF0A");
                    case 0: // Titanium Yellow
                        return Color.decode("#F4E409");
                }
            }
        return Color.WHITE;
    }
}

