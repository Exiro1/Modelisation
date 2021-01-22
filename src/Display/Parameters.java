package Display;

import java.awt.*;

    public class Parameters {

    Color color;
    Shape shape;
    boolean show;

        public Parameters(Color color, Shape shape, boolean show) {
            this.color = color;
            this.shape = shape;
            this.show = show;
        }
    }

enum Shape {
    CIRCLE,
    SQUARE,
    TRIANGLE
}
