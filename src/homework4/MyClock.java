package homework4;

public class MyClock{


    ClockFace clock = new ClockFace(0,0,500);
    ClockHand hour;
    ClockHand min;
    ClockHand sec;

    public MyClock(ClockHand hour, ClockHand min, ClockHand sec) {
        this.hour = hour;//Thick and Small
        hour.setWidth(20);
        hour.setLength(200);
        hour.setAngle(90);
        this.min = min;//Medium and Long
        min.setWidth(10);
        min.setLength(490);
        min.setAngle(90);
        this.sec = sec;//Thin and long
        sec.setWidth(5);
        sec.setLength(495);
        sec.setAngle(90);
    }
}