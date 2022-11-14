import java.awt.*;
import java.util.Random;

public abstract class AbstractShape implements Shape {

    protected int level;
    public int CHILDCOUNT;
    public int MAXLEVEL;
    protected AbstractShape[] children;
    protected AbstractShape parent;
    protected Color color;

    public abstract String idBuilder();

    protected int position;

    // these define the available draw area
    protected int minX;
    protected int minY;
    protected int maxX;
    protected int width;
    protected int height;

    public abstract void createChildren();


    public boolean addLevel(){
        AbstractShape shape = this;

        System.out.print("\nAdd Level from: " + shape.idBuilder());

        if (shape.level >= shape.MAXLEVEL){
            System.out.println("Failed, level would exceed maximum of " + shape.MAXLEVEL);
            return false;
        }

        if (shape.children != null) {
            System.out.println("Level full, advancing to layer" + shape.children[0].level);
            for (AbstractShape child: shape.children) {
                child.addLevel();
            }
        }
        else shape.createChildren();
        System.out.println("Level added successfully");
        return true;
    }

    public boolean removeLevel(){
//        AbstractShape shape = this;
//
//        if (shape.level <= 1){
//            System.out.println("Failed, only one layer exists");
//            return false;
//        }
//
//        if (shape.children != null) {
//            for (AbstractShape child: shape.children) {
//                child.removeLevel();
//            }
//        }
//        else shape.createChildren();
//        System.out.println("Level added successfully");
//        return true;
//
        return false;
    }
    /**
     * Generates random color or sequenced color
     * Palettes generated on coolers.co
     *
     * @param palette selects palette to use
     * @param level determines color for sequenced case
     * @return returns a Color object
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
            case 2: // palette for H Shape
                switch (rand) {
                    case 1: // Space Cadet
                        return Color.decode("#1B1B3A");
                    case 2: // Palatinate Purple
                        return Color.decode("#693668");
                    case 3: // Maximum Red Purple
                        return Color.decode("#A74482");
                    case 4: // Magenta
                        return Color.decode("#F84AA7");
                    case 5: // Radical Red
                        return Color.decode("#FF3562");
                }
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
        return Color.BLACK;
    }
}





// The fields may be initialized by the AbstractShape constructor with the values
// received from the super() calls in the constructors of the concrete shape classes.
// For instance, the SierpinskiTriangle constructor may call the AbstractShape constructor with
// the max level value of 10 and a children array length of 3
// Alternatively the fields may be initialized in the concrete class constructors (they are visible by
// the concrete classes since they are declared protected)