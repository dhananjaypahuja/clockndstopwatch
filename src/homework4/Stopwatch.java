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
        this.stopwatchDial = new StopWatchDial(radius / 2, (int) (y + width * 0.1), radius);
        this.clockHand = new ClockHand(radius, radius, Color.RED);
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(width, width));

        final int DELAY = 1000;
        Timer t = new Timer(DELAY, event -> {
            tick++;
            if (shouldIncrementInnerDial())
                stopwatchDial.tick(); //this tick method in stopwatch needs fixing i think
            tick %= 60;
//            clockHand.translate(0, 0);
            clockHand.setAngle(Math.PI/2 - tick*Math.PI/30);
            repaint();
        });
        t.start();
	}

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        clockFace.paintComponent(g);
        stopwatchDial.paintComponent(g);
        clockHand.draw((Graphics2D) g);
    }

    private boolean shouldIncrementInnerDial() {
        return tick == 60;
    }

    public void reset(){
        clockHand.reset();
        repaint();
        tick = 0;
        stopwatchDial.reset();
    }
}
