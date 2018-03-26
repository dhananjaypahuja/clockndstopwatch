package homework4;

import java.awt.Graphics2D;

public class ClockHand implements MoveableShape {

	private int x;
	private int y;

	@Override
	public void draw(Graphics2D g2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void translate(int dx, int dy) {
		 x+=dx;
		 y += dy;
		// TODO Auto-generated method stub
		
	}

}
