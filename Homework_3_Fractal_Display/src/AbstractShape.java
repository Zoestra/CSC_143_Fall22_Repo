import java.awt.*;

public abstract class AbstractShape implements Shape {

    protected int level;

    protected AbstractShape[] children;
    protected Color color;


    // these define the available draw area
    protected Point minCorner;
    protected Point maxCorner;

    public boolean addChildren(int childCount, int maxLevel, AbstractShape parent) {
        if (maxLevel >= level) {
            return false;
        }
        else {
            for (int i = 0; i < childCount; i++) {
            }
        }
    }

}



// The fields may be initialized by the AbstractShape constructor with the values
// received from the super() calls in the constructors of the concrete shape classes.
// For instance, the SierpinskiTriangle constructor may call the AbstractShape constructor with
// the max level value of 10 and a children array length of 3
// Alternatively the fields may be initialized in the concrete class constructors (they are visible by
// the concrete classes since they are declared protected)