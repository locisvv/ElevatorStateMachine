package svv.com.views;

import svv.com.controlers.Elevator;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
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
    }

    private void createNavigationButtons() {
        add(new NavigationButtons(elevator).getNavigationPanel());
    }

    private void createElevatorView() {
        ElevatorView elevatorView = new ElevatorView();
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