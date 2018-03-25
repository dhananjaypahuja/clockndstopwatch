package homework4;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
   This program implements an animation that moves
   a car shape.
*/
public class ClockTester
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame();
      // TODO add hands
      
      ClockFace icon = new ClockFace(0, 0, CLOCK_RADIUS);
      
      frame.setLayout(new BorderLayout());
      frame.add(icon, BorderLayout.CENTER);
      
      JPanel topNav = new JPanel(new FlowLayout());
      topNav.add(new JButton("clock"));
      topNav.add(new JButton("stopwatch"));
      frame.add(topNav, BorderLayout.NORTH);
      icon.repaint();

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setVisible(true);

      
   }

   private static final int CLOCK_RADIUS = 500;
}
