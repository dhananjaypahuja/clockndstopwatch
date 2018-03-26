package clock;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;

class Hand extends Line {
    static final double TAU = 2 * Math.PI; // For angle conversion.
    protected double length;

    // White hand starting around (x, y) with length len and width w.
    Hand(double x, double y, double len, double w) {
        this(x, y, len, w, Color.WHITE);
    }
    // Hand of specified color.
    Hand(double x, double y, double len, double w, Color color) {
        super(x, y, x, y-len);
        setStrokeLineCap(StrokeLineCap.ROUND);
        setStrokeWidth(w);
        setStroke(color);
        length = len;
    }

    // Sets the end of the hand based on an angle.
    void setAngle(double theta) {
        theta -= TAU * Math.floor(theta/TAU); // Normalize the angle.
        setEndX(getStartX() + Math.sin(theta)*length);
        setEndY(getStartY() - Math.cos(theta)*length);
    }
}
