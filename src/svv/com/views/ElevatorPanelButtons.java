package svv.com.views;

import svv.com.controlers.Elevator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ElevatorPanelButtons {
    private JPanel buttonsPanel;
    private Elevator elevator;

    public ElevatorPanelButtons(final Elevator elevator) {

        buttonsPanel = new JPanel();
        buttonsPanel.setBounds(500, 100, 200, 400);
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.setOpaque(false);

        for (int i = Elevator.FLOORS; i >= Elevator.FIRST_FLOOR; i--) {
            final int floor = i;

            JButton floorButton = new JButton(floor + "");
            floorButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    elevator.getQueueInElevator().add(floor);
                }
            });

            buttonsPanel.add(floorButton);
        }
    }

    public JPanel getButtonsPanel() {
        return buttonsPanel;
    }

}
