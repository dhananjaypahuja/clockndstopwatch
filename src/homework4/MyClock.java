package homework4;

import java.awt.*;
import java.time.Clock;
import javax.swing.*;

public class MyClock {
    private Clock sysClock;

    private final static int WIDTH = 500;
//    private static final int DELAY = 1000;

    ClockFace clock;
    ClockHand hour;
    ClockHand min;
    ClockHand sec;

    public MyClock(ClockHand hour, ClockHand min, ClockHand sec) {
        this(new ClockFace(0, 0, WIDTH), hour, min, sec);
    }

    public MyClock(ClockFace clock, ClockHand hour, ClockHand min, ClockHand sec) {

        //setLayout(new OverlayLayout(this));
        this.clock = clock;
        this.hour = hour;//Thick and Small
//        this.hour.setWidth(12);
//        this.hour.setLength(150);
        this.min = min;//Medium and Long
//        this.min.setWidth(6);
//        this.min.setLength(200);
        this.sec = sec;//Thin and long
//        this.sec.setWidth(3);
//        this.sec.setLength(205);
        clock.add("hour", hour);
        clock.add("minute", min);
        clock.add("second", sec);
        sysClock = Clock.systemUTC();
    }

    public void showNow() {
        long now = sysClock.millis()/1000; // Round to the nearest second
        sec.setAngle(Math.PI/2 - now%60 * Math.PI/30);
        min.setAngle(Math.PI/2 - now%3600 * Math.PI/1800);
        hour.setAngle(Math.PI/2 - now%43200 * Math.PI/21600);
    }

    public void repaint() {
        clock.repaint();
    }
}
