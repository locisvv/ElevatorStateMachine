package svv.com.views;

import svv.com.controlers.Elevator;

import javax.swing.*;
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

        for (int i = 1; i <= 5; i++) {
            final int floor = i;

            JButton floorButton = new JButton(floor + "");
            floorButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    elevator.getQueueInElevator().add(floor);
                    System.out.println("floor: " + floor);
                }
            });

            buttonsPanel.add(floorButton);
        }
    }
}
