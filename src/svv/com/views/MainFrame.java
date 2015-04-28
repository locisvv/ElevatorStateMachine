package svv.com.views;

import svv.com.controlers.Elevator;

import javax.swing.*;
import java.awt.*;

public class MainFrame {
    private JPanel floorsPanel;
    private JPanel elevatorPanel;
    private JPanel elevatorButtonsPanel;
    private JPanel building;
    private Elevator elevator;

    public MainFrame(Elevator elevator) {
        this.elevator = elevator;
    }

    private void createUIComponents() {
        building = new JPanel();

        floorsPanel = new JPanel();
        floorsPanel.add(new NavigationButtons(elevator).getNavigationPanel());

        elevatorButtonsPanel = new JPanel();
        elevatorButtonsPanel.add(new ElevatorPanelButtons(elevator).getButtonsPanel());

        elevatorPanel = new JPanel();
        ElevatorView elevatorView = new ElevatorView();
        elevatorPanel.setLayout(new BorderLayout());
        elevatorPanel.add(elevatorView);

        elevator.setElevatorView(elevatorView);
    }

    public JPanel getBuilding() {
        return building;
    }
}
