import java.awt.*;

/**
 * > The class `HShape` is a subclass of `AbstractShape` and implements the `Shape` interface
 */
public class HShape extends AbstractShape implements Shape{

        private Rectangle rectangle;

        public HShape(){
                this.CHILDCOUNT = 9;
                this.MAXLEVEL = 4;
                this.color = randColor(2, 0);
                this.children = new AbstractShape[9];
                this.createChildren();
                this.level = 0;

        }

        /**
         * > For each of the 9 children, set the color, level, parent, and boundaries
         */
        @Override
        public void createChildren() {
                color = randColor(2, 0);

                int n = this.width / 3;

                int x0                      = this.minX;
                int x1                      = x0 + n;
                int x2                      = this.maxX - n;
                int x3                      = this.maxX;

                int y0                      = this.minY;
                int y1                      = y0 + n;
                int y2                      = this.height - n;
                int y3                      = this.minY + this.height;

                for (int i                  = 0; i < 9; i++){
                        HShape shape                = new HShape();
                        this.children[i]            = shape;
                        shape.position              = i;
                        shape.color                 = color;
                        shape.level                 = this.level + 1;
                        shape.parent                = this;

                        Point minBoundary           = null;
                        Point maxBoundary           = null;

                        switch (i){
                                case 0:                                     // top left
                                        minBoundary = new Point(x0   , y0);
                                        maxBoundary = new Point(x1   , y1);
                                        break;
                                case 1: // top center -- gap
                                        minBoundary = new Point(x1, y0);
                                        maxBoundary = new Point(x2, y1);
                                        shape.color = Color.WHITE;
                                        break;
                                case 2: // top right
                                        minBoundary = new Point(x2, y0);
                                        maxBoundary = new Point(x3, y1);
                                        break;
                                case 3: // center left
                                        minBoundary = new Point(x0, y1);
                                        maxBoundary = new Point(x1, y2);
                                        break;
                                case 4: // center
                                        minBoundary = new Point(x1, y1);
                                        maxBoundary = new Point(x2, y2);
                                        break;
                                case 5: // center right
                                        minBoundary = new Point(x2, y1);
                                        maxBoundary = new Point(x3, y2);
                                        break;
                                case 6: // bottom left
                                        minBoundary = new Point(x0, y2);
                                        maxBoundary = new Point(x1, y3);
                                        break;
                                case 7: // bottom center -- gap
                                        minBoundary = new Point(x1, y2);
                                        maxBoundary = new Point(x2, y3);
                                        break;
                                case 8: // bottom right
                                        minBoundary = new Point(x2  , y2);
                                        maxBoundary = new Point(x3  , y3);
                                        break;
                        }
                        shape.minX      = minBoundary.x;
                        shape.minY      = minBoundary.y;
                        shape.maxX      = maxBoundary.x;
                        shape.width     = maxBoundary.x - minBoundary.x;
                        shape.height    = shape.width;

                        shape.rectangle = new Rectangle(shape.minX, shape.minY, shape.width, shape.height);
                }

        }

        /**
         * This function draws the object on the screen.
         *
         * @param g The Graphics object to draw on.
         */
        @Override
        public void draw(Graphics g) {
                if (this.children[0] != null) {
                        for (AbstractShape child : this.children) {


                        }
                }
        }

        @Override
        public int countShapes() {
                return 0;
        }

        @Override
        public void update(int value) {

        }


        @Override
        public String PRINT_LOGS(int c) {
                return null;
        }
}