package svv.com.views;

import svv.com.controlers.Elevator;

import javax.swing.*;

public class ElevatorView {
    private Elevator elevator;
    private JProgressBar elevatorProgressBar;
    private JPanel elevatorPanel;

    public ElevatorView (Elevator elevator) {
        this.elevator = elevator;
        elevator.setProgressBar(elevatorProgressBar);
    }

    public JPanel getElevatorPanel() {
        return elevatorPanel;
    }

    private void createUIComponents() {
        elevatorPanel = new JPanel();
    }
}
