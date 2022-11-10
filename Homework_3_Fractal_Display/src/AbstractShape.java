import java.awt.*;

public abstract class AbstractShape implements Shape {

    protected int level;
    public int CHILDCOUNT;
    public int MAXLEVEL;
    protected AbstractShape[] children;
    protected Color color;


    // these define the available draw area
    protected int minX;
    protected int minY;
    protected int maxX;

    public abstract void addChildren() ;

    public  boolean addLevel(AbstractShape shape, int CHILDCOUNT, int MAXLEVEL){

        for (int i = 0; i < CHILDCOUNT; i++) {
            if (shape.children[i] != null){
                if (shape.children[i].level > MAXLEVEL){
                    return false;
                }
                addLevel(shape.children[i], CHILDCOUNT, MAXLEVEL);
            }
            else shape.addChildren();

            }

        return true;
        }
}





// The fields may be initialized by the AbstractShape constructor with the values
// received from the super() calls in the constructors of the concrete shape classes.
// For instance, the SierpinskiTriangle constructor may call the AbstractShape constructor with
// the max level value of 10 and a children array length of 3
// Alternatively the fields may be initialized in the concrete class constructors (they are visible by
// the concrete classes since they are declared protected)