package svv.com;

import svv.com.controlers.Elevator;
import svv.com.controlers.ElevatorWorker;
import svv.com.views.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;

public class Main extends JFrame{

    public Main() {
        Elevator elevator = new Elevator();
        ElevatorWorker elevatorWorker = new ElevatorWorker(elevator);

        Executors.newSingleThreadExecutor().execute(elevatorWorker);

        MainPanel mainPanel = new MainPanel(elevator);
        this.add(mainPanel);
    }

    public static void main(String... args) {
        Main main = new Main();
        main.setVisible(true);
        main.setSize(600, 530);
        main.setBackground(Color.WHITE);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}