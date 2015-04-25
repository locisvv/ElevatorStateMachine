package svv.com.views;

import svv.com.controlers.Elevator;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ElevatorPanelButtons {
    private JPanel buttonsPanel;
    private Elevator elevator;

    public ElevatorPanelButtons(Elevator elevator) {
        this.elevator = elevator;
    }

    public JPanel getButtonsPanel() {
        return buttonsPanel;
    }

    private void createUIComponents() {
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

        for (int i = Elevator.FLOORS; i >= Elevator.FIRST_FLOOR; i--) {
            final int floor = i;

            JButton floorButton = new JButton(floor + "");
            floorButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    elevator.getQueueInElevator().add(floor);
                    elevator.moving();
                    System.out.println("floor: " + floor);
                }
            });

            buttonsPanel.add(floorButton);
        }
    }
}
