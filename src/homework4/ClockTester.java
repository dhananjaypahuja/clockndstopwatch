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
      
      ClockFace icon = new ClockFace(0, 0, CLOCK_RADIUS);
      
       ClockHand hour = new ClockHand(250, 250);
       ClockHand min = new ClockHand(250, 250);
       ClockHand sec = new ClockHand(250, 250);
       MyClock clock = new MyClock(icon, hour, min, sec);
//       Stopwatch stopwatch = new Stopwatch(0, 0, CLOCK_RADIUS);
      
      frame.setLayout(new BorderLayout());
      frame.add(icon, BorderLayout.CENTER);

      ActionListener listner = event -> {
//          sec.translate(10, 10);
//          sec.paintComponent(frame.getGraphics());
          clock.showNow();
          clock.repaint();
      };

      Timer t = new Timer(DELAY, listner);
      t.start();
      
      JPanel topNav = new JPanel(new FlowLayout());
      topNav.add(new JButton("clock"));
      topNav.add(new JButton("stopwatch"));
      frame.add(topNav, BorderLayout.NORTH);
      icon.repaint();

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setVisible(true);

      
   }

   // Checking once a second is too slow because the timer has latency.
   private static final int DELAY = 100;
   private static final int CLOCK_RADIUS = 500;
}
