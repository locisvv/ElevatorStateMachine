package svv.com.views;

import svv.com.controlers.Elevator;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import java.awt.Dimension;
import java.awt.Graphics;

public class MainPanel extends JPanel{
    private ImageIcon img;
    private Elevator elevator;

    private JLabel floorLabel;
    private ElevatorView elevatorView;
    private ElevatorButtons elevatorButtons;
    private NavigationButtons navigationButtons;

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

        elevator.setMainView(this);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(img.getImage(), 0, 0, null);
    }

    private void createNavigationButtons() {
        navigationButtons = new NavigationButtons(elevator);
        add(navigationButtons.getNavigationPanel());
    }

    private void createElevatorView() {
        elevatorView = new ElevatorView(elevator);
        elevatorView.setSize(new Dimension(500, 500));
        add(elevatorView);
    }

    private void createElevatorButtons() {
        elevatorButtons = new ElevatorButtons(elevator);
        add(elevatorButtons.getButtonsPanel());
    }

    private void createFloorLabel() {
        JLabel currentFloorLabel = new JLabel("Current floor:");
        currentFloorLabel.setBounds(200, 10, 140, 30);
        add(currentFloorLabel);

        floorLabel = new JLabel(Elevator.FIRST_FLOOR + "");
        floorLabel.setBounds(300, 10, 140, 30);
        add(floorLabel);
    }

    public void showCurrentFloor(final int floor) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                floorLabel.setText(String.valueOf(floor));
            }
        });
    }

    public ElevatorView getElevatorView() {
        return elevatorView;
    }

    public void setEnabledButtonsByCurrentFloor() {
        elevatorButtons.setEnabledByFloor(elevator.getCurrentFloor().get());
        navigationButtons.setEnabledByFloor(elevator.getCurrentFloor().get());
    }

}