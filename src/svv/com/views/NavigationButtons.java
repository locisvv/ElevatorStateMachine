package svv.com.views;

import svv.com.controlers.Elevator;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavigationButtons {
    private JPanel navigationPanel;
    private Elevator elevator;

    public NavigationButtons(Elevator elevator) {
        this.elevator = elevator;

        navigationPanel = new JPanel();
        navigationPanel.setLayout(null);
        navigationPanel.setSize(new Dimension(500, 500));
        navigationPanel.setOpaque(false);

        for (int i = 1; i <= Elevator.FLOORS; i++) {
            newNavigationButtons(i);
        }
    }

    private void newNavigationButtons(final int currentFloor) {
        if (currentFloor < Elevator.FLOORS) {
            UpButton upButton = new UpButton();
            upButton.setBounds(225, 470 - (currentFloor * 70), 20, 20);
            upButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    elevator.addUpWaiter(currentFloor);
                }
            });
            navigationPanel.add(upButton);
        }

        if (currentFloor > Elevator.FIRST_FLOOR) {
            DownButton downButton = new DownButton();
            downButton.setBounds(225, 495 - (currentFloor * 70), 20, 20);
            downButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    elevator.addDownWaiter(currentFloor);
                }
            });
            navigationPanel.add(downButton);
        }
    }

    public JPanel getNavigationPanel() {
        return navigationPanel;
    }
}