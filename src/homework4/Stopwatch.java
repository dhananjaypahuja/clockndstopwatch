package homework4;

import javax.swing.*;
import java.awt.*;

public class Stopwatch extends JPanel{

    private int x, y, width;
    private ClockFace clockFace;
    private StopWatchDial stopwatchDial;
    private ClockHand clockHand;
    private int tick = 0;


	public Stopwatch(int x, int y, int width) {
        this.x = x;
        this.y = y;
        this.width = width;
        int radius = width/2;
        this.clockFace = new ClockFace(x, y, width);
        this.clockFace.setIncrement(ClockFace.size.FIVE);
        this.stopwatchDial = new StopWatchDial(radius - radius / 2, (int) (y + width * 0.1), radius);
        this.clockHand = new ClockHand(radius, radius, Color.RED);
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(width, width));
	}

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        clockFace.paintComponent(g);
        clockHand.draw((Graphics2D) g);
        stopwatchDial.paintComponent(g);
    }

    private boolean shouldIncrementInnerDial() {
        return tick == 59;
    }

    public void reset(){
        clockHand.reset();
        repaint();
        tick = 0;
        stopwatchDial.reset();
    }
}
