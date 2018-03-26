package homework4;

public class StopWatchDial {

	public StopWatchDial(ClockHand dial) {
		this.dial = dial;
		dial.setAngle(90);
		dial.setLength(490);
		dial.setWidth(5);
	}

	ClockFace Stopwatch = new ClockFace(0,0,500);
	
	ClockHand dial; //Thin and long
}
