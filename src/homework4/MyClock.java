package homework4;

import java.time.Clock;
import javax.swing.*;

public class MyClock{
    private Clock sysClock;

    ClockFace clock;
    ClockHand hour;
    ClockHand min;
    ClockHand sec;

    public MyClock(ClockHand hour, ClockHand min, ClockHand sec) {
        this(new ClockFace(0, 0, 500), hour, min, sec);
    }
    public MyClock(ClockFace clock, ClockHand hour, ClockHand min, ClockHand sec) {
        clock.setLayout(new OverlayLayout(clock));
        this.clock = clock;
        this.hour = hour;//Thick and Small
        this.hour.setWidth(12);
        this.hour.setLength(150);
        this.min = min;//Medium and Long
        this.min.setWidth(6);
        this.min.setLength(200);
        this.sec = sec;//Thin and long
        this.sec.setWidth(3);
        this.sec.setLength(205);
        clock.add(hour);
        clock.add(min);
        clock.add(sec);
        sysClock = Clock.systemUTC();
        showNow();
    }

    public void showNow() {
        long now = sysClock.millis();
        sec.setAngle(Math.PI/2 - now%60000 * Math.PI/30000);
        min.setAngle(Math.PI/2 - now%3600000 * Math.PI/1800000);
        hour.setAngle(Math.PI/2 - now%43200000 * Math.PI/21600000);
    }
}
