package svv.com.views;

import svv.com.controlers.Elevator;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Graphics;

public class MainPanel extends JPanel{
    private Image img;
    private Elevator elevator;

    public MainPanel(Elevator elevator) {
        this.elevator = elevator;
        this.img = new ImageIcon("house.jpg").getImage();

        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);

        createNavigationButtons();
        createElevatorView();
        createElevatorButtons();

        JTextArea floorTextArea = new JTextArea(Elevator.FIRST_FLOOR + "");
        floorTextArea.setBounds(250, 10, 100, 20);
        elevator.setFloorTextArea(floorTextArea);
        add(floorTextArea);
    }

    private void createNavigationButtons() {
        add(new NavigationButtons(elevator).getNavigationPanel());
    }

    private void createElevatorView() {
        ElevatorView elevatorView = new ElevatorView(elevator);
        elevatorView.setSize(new Dimension(500,500));
        add(elevatorView);

        elevator.setElevatorView(elevatorView);
    }

    private void createElevatorButtons() {
        add(new ElevatorPanelButtons(elevator).getButtonsPanel());
    }

    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
}