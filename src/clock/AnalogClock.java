package clock;

import java.io.*;
import java.time.Clock;
import java.util.Scanner;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class AnalogClock extends Application {
    public static void main(String[] args) { launch(args); }

    private Hand hour, minute, second;
    private Thread th, save; // Threads for update and save.
    private Task<Integer> tSave; // Task to handle synchronization.
    private int offset; // Offset hour to desired time zone.
    private File fOffset; // File to save time offset preferences.
    private boolean end; // Signal offset save thread to safely end.

    // Set up all resources.
    @Override public void init() throws Exception {
        // Create hands.
        hour = new Hand(260, 290, 160, 9);
        minute = new Hand(260, 290, 210, 6);
        second = new Hand(260, 290, 214, 3, Color.RED);

        // Read offset.
        fOffset = new File("t_offset.txt");
        try (Scanner in = new Scanner(fOffset)) {
            offset = in.nextInt() % 12;
        } catch(Exception e) {
            offset = 4; // Default: Pacific Standard Time
        }

        // To update the clock.
        th = new Thread(new Task<Long>() {
            @Override protected Long call() throws Exception {
                long now = -1;
                // Automatic local time is a pain, so don't bother.
                Clock current = Clock.systemUTC();
                while (!isCancelled()) {
                    now = current.millis();
                    second.setAngle(now%60000 * Math.PI / 30000);
                    minute.setAngle(now%3600000 * Math.PI / 1800000);
                    hour.setAngle(now%43200000 * Math.PI / 21600000 +
                                  offset * Math.PI / 6);
                    Thread.sleep(40);
                }
                return now;
            }
        });
        th.setDaemon(true);

        // To save time offset.
        tSave = new Task<Integer>() {
            @Override protected Integer call() {
                while (true) {
                    try {
                        synchronized(this) { wait(); }
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                    if (end || isCancelled())
                        return offset;
                    try (OutputStream out = new FileOutputStream(fOffset)) {
                        byte[] value = String.format("%d", offset).getBytes();
                        out.write(value, 0, value.length);
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        save = new Thread(tSave);
        save.setDaemon(true);

        end = false;
        super.init();
    }

    // Run the clock.
    @Override public void start(Stage primaryStage) {
        // Prepare window.
        primaryStage.setTitle("Clock");
        AnchorPane root = new AnchorPane();

        // Prepare buttons to adjust time offset.
        Button back = new Button("◀ –1hr");
        back.setLayoutX(158);
        back.setLayoutY(4);
        back.setMinWidth(100);
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                offset --;
                saveOffset();
            }
        });
        Button fwd = new Button("+1hr ▶");
        fwd.setLayoutX(262);
        fwd.setLayoutY(4);
        fwd.setMinWidth(100);
        fwd.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                offset ++;
                saveOffset();
            }   
        });

        // Add buttons, clock face, numbers, hub, and hands.
        ObservableList<Node> children = root.getChildren();
        children.add(new Circle(260, 290, 256, Color.BLACK));
        for (int i = 1; i <= 60; i ++) {
            if (i % 5 == 0) {
                boolean large = i % 15 == 0;
                children.add(numCircle(i/5, large));
                children.add(numText(i/5, large));
            } else {
                double theta = i * Math.PI / 30;
                children.add(new Circle(x(theta), y(theta), 2, Color.WHITE));
            }
        }
        children.addAll(back, fwd, new Circle(260, 290, 8, Color.WHITE), hour,
                        minute, new Circle(260, 290, 4, Color.RED), second);
        // Begin background threads.
        th.start();
        save.start();
        // Display window.
        primaryStage.setScene(new Scene(root, 520, 550));
        primaryStage.show();
    }

    // Interrupt threads and end the program.
    @Override public void stop() throws Exception {
        th.interrupt();
        end = true;
        synchronized(tSave) { tSave.notify(); }
        save.interrupt();
        super.stop();
    }

    // Save time offset whenever it changes.
    private void saveOffset() {
        offset %= 12;
        synchronized(tSave) { tSave.notify(); }
    }
    // Create circle around number for clock face.
    private Circle numCircle(int num, boolean big) {
        double a = angle(num);
        Circle c = new Circle(x(a), y(a), big ? 16 : 14, Color.BLACK);
        c.setStroke(Color.WHITE);
        c.setStrokeWidth(2);
        return c;
    }
    // Create number for clock face.
    private Text numText(int num, boolean big) {
        double a = angle(num);
        Text t = new Text(x(a)-16, y(a)+6, String.format("%d", num));
        t.setTextAlignment(TextAlignment.CENTER);
        t.setFont(Font.font("helvetica", FontWeight.LIGHT, FontPosture.REGULAR,
                            big ? 20 : 16));
        t.setFill(Color.WHITE);
        t.setWrappingWidth(32);
        return t;
    }

    // Positioning utilities.
    private double angle(int num)  { return num * Math.PI / 6; }
    private double x(double theta) { return 260 + Math.sin(theta)*240; }
    private double y(double theta) { return 290 - Math.cos(theta)*240; }
}
