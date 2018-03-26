package homework4;

public class MyClock{


    ClockFace clock = new ClockFace(0,0,500);
    ClockHand hour;
    ClockHand min;
    ClockHand sec;

    public MyClock(ClockHand hour, ClockHand min, ClockHand sec) {
        this.hour = hour;//Thick and Small
        this.hour.setWidth(12);
        this.hour.setLength(150);
        this.hour.setAngle(Math.PI/2);
        this.min = min;//Medium and Long
        this.min.setWidth(6);
        this.min.setLength(200);
        this.min.setAngle(Math.PI/2);
        this.sec = sec;//Thin and long
        this.sec.setWidth(3);
        this.sec.setLength(205);
        this.sec.setAngle(Math.PI/2);
    }
}
