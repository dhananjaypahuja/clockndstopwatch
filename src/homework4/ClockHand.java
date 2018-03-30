package homework4;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.time.Clock;

public class ClockHand extends JComponent implements MoveableShape {
    private double centerX, centerY, length, angle;
    private int width;
    private double deltaX;
    private double deltaY;
    Color dialColor;

    private double iterator;

    /**
     * Construct a new ClockHand with the specified width, center, length, and $
     *
     * @param width   the hand's width
     * @param centerX the hand's center x-coordinate
     * @param centerY the hand's center y-coordinate
     * @param length  the hand's length
     * @param angle   the hand's angle, counterclockwise from 3:00
     */
    public ClockHand(int width, double centerX, double centerY, double length, double angle, Color dialColor) {
        this.width = width;
        this.centerX = centerX;
        this.centerY = centerY;
        this.length = length;
        this.angle = angle;
        this.dialColor = dialColor;
        setOpaque(false);
        setVisible(true);
        setPreferredSize(new Dimension((int) (centerX * 2), (int) (centerY * 2)));
    }

    public ClockHand(double centerX, double centerY, Color dialColor) {
        this(2, centerX, centerY, 200, Math.PI / 2, dialColor);
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }
    public double getAngle() {
        return angle;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public void translate(int dx, int dy) {

        iterator++;
        double angleFrom12 = iterator / 60.0 * 2.0 * Math.PI;
        double angleFrom3 = Math.PI * 1.5 + angleFrom12;
        deltaX = Math.cos(angleFrom3) * length;
        deltaY = Math.sin(angleFrom3) * length;

        if (iterator == 60) {
            iterator = 0;
        }
    }

    public void reset() {
        angle = Math.PI / 2;
//        iterator = 0;
    }

    // This doesn't fit the iterator design pattern; maybe it should be renamed?
    public double getIterator() {
        return iterator;
    }

    @Override
    public void draw(Graphics2D g2) {
//        g2.setColor(dialColor);
        super.paintComponent(g2);
        paintComponent(g2);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // Don't change the position of the center.
//        centerX = iterator/60.0*2.0*Math.PI;
//        centerY = Math.PI*1.5+centerX;
        double endX = centerX + Math.cos(angle) * length;
        double endY = centerY - Math.sin(angle) * length;

        GeneralPath path = new GeneralPath();

        g2.setColor(dialColor);
        g2.setStroke(new BasicStroke(width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1f));
        path.moveTo(centerX, centerY);
        path.lineTo(endX, endY);
        g2.draw(path);
    }

    // Not needed
    public void showNow(Clock sysClock) {
        long now = sysClock.millis()/1000; // Round to the nearest second
        this.setAngle(Math.PI/2 - now%3600 * Math.PI/1800);
    }
}
