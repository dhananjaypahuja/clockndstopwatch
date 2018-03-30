package homework4;

import javafx.embed.swing.JFXPanel;

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
      
       ClockHand hour = new ClockHand(250, 250, Color.BLACK);
       ClockHand min = new ClockHand(250, 250, Color.BLACK);
       ClockHand sec = new ClockHand(1,250, 250, 200,Math.PI / 2, Color.RED);
       MyClock clock = new MyClock(icon, hour, min, sec);
       Stopwatch stopwatch = new Stopwatch(0, 0, CLOCK_RADIUS);
      
      frame.setLayout(new BorderLayout());
//      frame.add(icon, BorderLayout.CENTER);

      ActionListener listner = event -> {
//          sec.translate(10, 10);
//          sec.paintComponent(frame.getGraphics());
          clock.showNow();
          clock.repaint();
      };


      Timer t = new Timer(DELAY, listner);
      t.start();

      JPanel clockPanel = new JPanel();
      JPanel stopwatchPanel = new JPanel();
      clockPanel.add(icon, BorderLayout.CENTER);
      stopwatchPanel.add(stopwatch, BorderLayout.CENTER);

      clockButton.addActionListener(e -> switchPanel(frame, stopwatchPanel, clockPanel));
      stopwatchButton.addActionListener(e -> {
          switchPanel(frame, clockPanel, stopwatchPanel);
          stopwatch.reset();
          stopwatch.setVisible(true);
      });

      JPanel topNav = new JPanel(new FlowLayout());
      topNav.add(clockButton);
      topNav.add(stopwatchButton);
       frame.add(stopwatchPanel, BorderLayout.CENTER);
      frame.add(topNav, BorderLayout.NORTH);
      frame.add(clockPanel, BorderLayout.CENTER);
      icon.repaint();

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setVisible(true);

      
   }

    public static void switchPanel(JFrame mainFrame, JPanel oldPanel,
                                   JPanel newPanel) {
        mainFrame.remove(oldPanel);
        mainFrame.add(newPanel, BorderLayout.CENTER);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private static JButton clockButton = new JButton("clock");
    private static JButton stopwatchButton = new JButton("stopwatch");

   // Checking once a second is too slow because the timer has latency.
   private static final int DELAY = 100;
   private static final int CLOCK_RADIUS = 500;
}
