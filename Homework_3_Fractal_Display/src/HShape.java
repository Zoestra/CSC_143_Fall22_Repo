import java.awt.*;

/**
 * > The class `HShape` is a subclass of `AbstractShape` and implements the `Shape` interface
 */
public class HShape extends AbstractShape implements Shape{

        private Polygon   square;
        private Polygon[] gaps;
        private int third = width / 3;
        private int[] gridX =  {minX, (minX + third), (maxX - third)  , maxX};
        private int[] gridY =  {minY, (minY + third), (height - third), (minY + height)};

        public HShape(){

                level = 0;
                square = createSquare();
                color = Color.WHITE;
                init();
                createChildren();
        }

        private HShape(AbstractShape parent, int position){

                this.parent = parent;
                level = parent.level + 1;
                init();
        }

        /**
         * This function initializes the variables of the class
         */
        public void init(){
                CHILDCOUNT = 7;
                MAXLEVEL = 4;
                children = null;

        }

        /**
         * > Create a new HShape object for each of the 9 positions in the grid, and set the position, color, and
         * coordinates of each shape
         */
        @Override
        public void createChildren() {
                Color color = randColor(1, 1);

                for (int i = 0; i < 9; i++){

                        HShape shape                = new HShape(this, i);
                        children[i]                 = shape;
                        shape.position              = i;
                        shape.color                 = color;
                        int[] coords    = this.getCoords(i);
                        shape.minX      = coords[0];
                        shape.minY      = coords[1];
                        shape.maxX      = coords[2];
                        shape.width     = coords[2] - coords[0];
                        shape.height    = shape.width;

                        shape.square    = createSquare();
                }

        }

        private Polygon createSquare(){

                return new Polygon(new int[] {this.minX, this.maxX, this.maxX, this.minX},
                                   new int[] {this.minY, this.minY, this.minY + this.height, this.minY + this.height },
                                4);

        }
//
//        private Polygon[] createGaps(){
//
//                int[][] xPoints = new int[][] {{}};
//        }
        /**
         * overrides the addLevel() function in the superclass.
         *
         * @return returns false if super.addLevel fails
         */
        @Override
        public boolean addLevel(){
               return super.addLevel();
        }


        /**
         * This function draws the object on the screen.
         *
         * @param g The Graphics object to draw on.
         */
        @Override
        public void draw(Graphics g) {
                g.drawPolygon(this.square);
                g.setColor(this.color);
                g.fillPolygon(this.square);


                if (this.children[0] != null) {
                        for (AbstractShape child : children) {
                                child.draw(g);
                        }
                }
        }

        @Override
        public int countShapes() {
                return 0;
        }

        private int[] getCoords(int pos) {

                int[] coords = null;
                // sets the boundaries of the children of the HShape.
                switch (pos) {
                        case 0:// top left
                                coords = new int[] {gridX[0], gridY[0], gridX[1], gridY[1]};
                                break;
                        case 1: // top center -- gap
                                coords = new int[] {gridX[1], gridY[0], gridX[2], gridY[1]};
                                break;
                        case 2: // top right

                                coords = new int[] {gridX[2], gridY[0], gridX[3], gridY[1]};
                                break;
                        case 3: // center left
                                coords = new int[] {gridX[0], gridY[1], gridX[1], gridY[2]};
                                break;
                        case 4: // center
                                coords = new int[] {gridX[1], gridY[1], gridX[2], gridY[2]};
                                break;
                        case 5: // center right
                                coords = new int[] {gridX[2], gridY[1], gridX[3], gridY[2]};
                                break;
                        case 6: // bottom left
                                coords = new int[] {gridX[0], gridY[2], gridX[1], gridY[3]};
                                break;
                        case 7: // bottom center -- gap
                                coords = new int[] {gridX[1], gridY[2], gridX[2], gridY[3]};
                                break;
                        case 8: // bottom right
                                coords = new int[] {gridX[2], gridY[2], gridX[3], gridY[3]};
                                break;
                }
                return coords;
        }
        @Override
        public void update(int value) {

        }


        @Override
        public String PRINT_LOGS(int c) {
                return null;
        }
}