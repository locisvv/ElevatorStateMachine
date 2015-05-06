package svv.com.views;

import svv.com.controlers.Elevator;

import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import javax.swing.border.LineBorder;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Insets;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.concurrent.CountDownLatch;

public class ElevatorButtons {
    private JPanel buttonsPanel;
    private Elevator elevator;

    public ElevatorButtons(Elevator elevator) {
        this.elevator = elevator;

        buttonsPanel = new JPanel();
        buttonsPanel.setBounds(0, 0, 700, 530);
        buttonsPanel.setLayout(null);
        buttonsPanel.setOpaque(false);

        addFloorButtons();
        addStopButton();
    }

    private void addFloorButtons() {
        for (int i = 1; i <= Elevator.FLOORS; i++) {
            final int floor = i;

            final JToggleButton floorButton = new JToggleButton(floor + "");
            floorButton.setBackground(Color.white);
            floorButton.setBounds(520, ((Elevator.FLOORS - floor) * 60) + 100, 50, 50);
            floorButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    floorButton.setEnabled(false);
                    elevator.getQueueInElevator().add(floor);
                }
            });
            buttonsPanel.add(floorButton);
        }
    }

    public void setEnabledByFloor(final int floor) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                getButtonByFloor(floor).setEnabled(true);
                getButtonByFloor(floor).setSelected(false);
            }
        });
    }

    private JToggleButton getButtonByFloor(int floor) {
        return (JToggleButton) buttonsPanel.getComponents()[floor - 1];
    }

    private void addStopButton() {
        final JButton stopButton = new JButton("STOP");
        stopButton.setBackground(Color.white);
        stopButton.setMargin(new Insets(5, 5, 5, 5));
        stopButton.setFont(new Font("Dialog", 0, 11));
        stopButton.setBounds(520, 400, 50, 50);
        stopButton.addActionListener(stopButtonClick(stopButton));
        buttonsPanel.add(stopButton);
    }

    private AbstractAction stopButtonClick(final JButton stopButton) {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (elevator.getStop()) {
                    elevator.setStop(false);
                    elevator.getCountDownLatch().countDown();

                    stopButton.setBorder(new LineBorder(Color.GRAY));
                    elevator.getMainView().getElevatorView().repaint();
                } else {
                    elevator.setCountDownLatch(new CountDownLatch(1));
                    elevator.setStop(true);

                    stopButton.setBorder(new LineBorder(Color.RED));
                    elevator.getMainView().getElevatorView().repaint();
                }
            }
        };
    }

    public JPanel getButtonsPanel() {
        return buttonsPanel;
    }

}