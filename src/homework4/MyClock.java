package homework4;

public class MyClock{

	ClockFace clock = new ClockFace(0,0,500);
	
	ClockHand hour = new ClockHand(250, 250); //Thick and Small
    ClockHand min = new ClockHand(250, 250); //Medium and Long
	ClockHand sec = new ClockHand(250, 250); //Thin and long
}
