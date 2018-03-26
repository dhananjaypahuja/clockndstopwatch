package homework4;

public class MyClock{


    ClockFace clock = new ClockFace(0,0,500);
    ClockHand hour;
    ClockHand min;
    ClockHand sec;

    public MyClock(ClockHand hour, ClockHand min, ClockHand sec) {
        this.hour = hour;//Thick and Small
        hour.setWidth(12);
        hour.setLength(150);
        hour.setAngle(Math.PI/2);
        this.min = min;//Medium and Long
        min.setWidth(6);
        min.setLength(200);
        min.setAngle(Math.PI/2);
        this.sec = sec;//Thin and long
        sec.setWidth(3);
        sec.setLength(205);
        sec.setAngle(Math.PI/2);
    }
}
