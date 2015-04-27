package svv.com;

import svv.com.controlers.Elevator;
import svv.com.controlers.ElevatorWorker;
import svv.com.views.MainFrame;

import javax.swing.*;

public class Main extends JFrame{

    public Main() {
        Elevator elevator = new Elevator();
        ElevatorWorker elevatorWorker = new ElevatorWorker(elevator);
        elevatorWorker.execute();

        MainFrame mainFrame = new MainFrame(elevator);
        this.add(mainFrame.getBuilding());
    }

    public static void main(String... args) {
        Main main = new Main();
        main.setVisible(true);
        main.setSize(500, 300);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}
