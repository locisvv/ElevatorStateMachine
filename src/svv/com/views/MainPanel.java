package svv.com.views;

import svv.com.controlers.Elevator;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Graphics;

public class MainPanel extends JPanel{
    private ImageIcon img;
    private Elevator elevator;

    public MainPanel(Elevator elevator) {
        this.elevator = elevator;

        this.img = new javax.swing.ImageIcon(getClass().getResource("/img/house.jpg"));

        Dimension size = new Dimension(img.getIconWidth(), img.getIconHeight());
        setPreferredSize(size);
        setSize(size);
        setLayout(null);

        createNavigationButtons();
        createElevatorView();
        createElevatorButtons();
        createFloorLabel();
    }

    private void createNavigationButtons() {
        add(new NavigationButtons(elevator).getNavigationPanel());
    }

    private void createElevatorView() {
        ElevatorView elevatorView = new ElevatorView(elevator);
        elevatorView.setSize(new Dimension(500, 500));
        add(elevatorView);

        elevator.setElevatorView(elevatorView);
    }

    private void createElevatorButtons() {
        add(new ElevatorPanelButtons(elevator).getButtonsPanel());
    }

    private void createFloorLabel() {
        JLabel currentFloorLabel = new JLabel("Current floor:");
        currentFloorLabel.setBounds(200, 10, 140, 30);
        add(currentFloorLabel);

        JLabel floorLabel = new JLabel(Elevator.FIRST_FLOOR + "");
        floorLabel.setBounds(300, 10, 140, 30);
        elevator.setFloorLabel(floorLabel);
        add(floorLabel);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(img.getImage(), 0, 0, null);
    }
}