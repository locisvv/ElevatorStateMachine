package svv.com.views;

import svv.com.controlers.Elevator;

import javax.swing.JToggleButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NavigationButtons {
    private JPanel navigationPanel;
    private Elevator elevator;
    private ArrayList<UpAndDownButton> navigationButtons;

    public NavigationButtons(Elevator elevator) {
        this.elevator = elevator;

        navigationButtons = new ArrayList<UpAndDownButton>(Elevator.FLOORS);

        navigationPanel = new JPanel();
        navigationPanel.setLayout(null);
        navigationPanel.setSize(new Dimension(500, 500));
        navigationPanel.setOpaque(false);

        for (int i = 1; i <= Elevator.FLOORS; i++) {
            newNavigationButtons(i);
        }
    }

    private void newNavigationButtons(final int currentFloor) {
        UpAndDownButton buttons = new UpAndDownButton(currentFloor);
        if (currentFloor > Elevator.FIRST_FLOOR) {
            final DownButton downButton = new DownButton();
            downButton.setBounds(225, 495 - (currentFloor * 70), 20, 20);
            downButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    downButton.setEnabled(false);
                    elevator.addDownWaiter(currentFloor);
                }
            });
            navigationPanel.add(downButton);
            buttons.setDown(downButton);
        }

        if (currentFloor < Elevator.FLOORS) {
            final UpButton upButton = new UpButton();
            upButton.setBounds(225, 470 - (currentFloor * 70), 20, 20);
            upButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    upButton.setEnabled(false);
                    elevator.addUpWaiter(currentFloor);
                }
            });
            navigationPanel.add(upButton);
            buttons.setUp(upButton);
        }

        navigationButtons.add(buttons);
    }

    public void setEnabledByFloor(final int floor) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                navigationButtons.get(floor - 1).setEnabledByFloor();
            }
        });
    }

    public JPanel getNavigationPanel() {
        return navigationPanel;
    }

    private class UpAndDownButton {
        private JToggleButton up;
        private JToggleButton down;
        private int floor;

        UpAndDownButton(int floor) {
            this.floor = floor;
        }

        public void setEnabledByFloor() {
            if (floor < Elevator.FLOORS) {
                up.setEnabled(true);
                up.setSelected(false);
            }

            if (floor > Elevator.FIRST_FLOOR) {
                down.setEnabled(true);
                down.setSelected(false);
            }
        }

        public void setUp(JToggleButton up) {
            this.up = up;
        }

        public void setDown(JToggleButton down) {
            this.down = down;
        }
    }
}
