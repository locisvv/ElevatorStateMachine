package svv.com.views;

import build.tools.javazic.Main;
import svv.com.controlers.Elevator;

import javax.swing.*;

/**
 * Created by svv on 4/21/15.
 */
public class MainFrame {

    private JPanel floorsPanel;
    private JPanel elevatorPanel;
    private JPanel elevatorButtonsPanel;
    private JPanel building;
    private Elevator elevator;

    public JPanel getBuilding() {
        return building;
    }

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
        elevatorPanel.add(new ElevatorView(elevator).getElevatorPanel());
    }
}
