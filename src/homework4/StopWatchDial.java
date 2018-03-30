package homework4;

import javax.swing.*;
import java.awt.*;
import java.time.Clock;

public class StopWatchDial extends JPanel{

    private ClockFace clockFace;
    private Clock sysClock;
    private ClockHand clockHand; //min

    private int x, y, width;
    /**
	 Constructs a StopWatchDial
	 @param x the left of the bounding rectangle
	 @param y the top of the bounding rectangle
	 @param width the width of the bounding rectangle
	 */
	public StopWatchDial(int x, int y, int width) {
	    this.x = x;
	    this.y = y;
	    this.width = width;
		this.clockFace = new ClockFace(x, y, width);
		this.clockFace.setIncrement(ClockFace.size.FIVE);
		this.clockHand = new ClockHand(250, 250, Color.RED);
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(width, width));
	}

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        clockFace.paintComponent(g);
        clockHand.draw((Graphics2D) g);
    }

    //move minute hand
    public void tick() {
//        clockHand.move();
        sysClock = Clock.systemUTC();
        clockHand.showNow(sysClock);
        repaint();
    }

    public void reset(){
        clockHand.reset();
        repaint();
    }
}
