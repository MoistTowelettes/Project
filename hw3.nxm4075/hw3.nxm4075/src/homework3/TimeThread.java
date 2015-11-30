package homework3;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Clock;
import java.sql.Time;
import java.time.LocalTime;

import javafx.stage.Stage;

/**
 * Created by Manny on 11/29/15.
 */
public class TimeThread extends JFrame implements Runnable,ActionListener {
    Stage edit;
    LocalTime time;

    public TimeThread(Stage stage){
        this.edit = stage;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void run() {

        try {
            JFrame frame = new JFrame();
            JTextPane pane = new JTextPane();
            JLabel label = new JLabel();


            pane.setEditable(false);




            frame.setContentPane(pane);

            frame.setSize(200,200);
            frame.setVisible(true);


        } catch (Exception e) {
            System.err.println("Thread Crash!");

        }

    }
}
