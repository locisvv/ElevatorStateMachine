package svv.com.views;

import svv.com.controlers.Elevator;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.Color;
import java.awt.event.ActionEvent;

public class ElevatorPanelButtons {
    private JPanel buttonsPanel;
    private Elevator elevator;

    public ElevatorPanelButtons(Elevator elevator) {
        this.elevator = elevator;

        buttonsPanel = new JPanel();
        buttonsPanel.setBounds(0, 0, 700, 530);
        buttonsPanel.setLayout(null);
        buttonsPanel.setOpaque(false);

        addFloorButtons();
    }

    private void addFloorButtons() {
        for (int i = 1; i <= Elevator.FLOORS; i++) {
            final int floor = i;

            JButton floorButton = new JButton(floor + "");
            floorButton.setBackground(Color.white);
            floorButton.setBounds(520, ((Elevator.FLOORS - floor) * 60) + 100, 50,50);
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
