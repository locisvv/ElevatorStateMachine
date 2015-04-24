package svv.com.views;

import svv.com.controlers.Elevator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavigationButtons {
    private JPanel navigationPanel;
    private Elevator elevator;

    public NavigationButtons(Elevator elevator) {
        this.elevator = elevator;
    }

    private void createUIComponents() {
        navigationPanel = new JPanel();
        navigationPanel.setLayout(new BoxLayout(navigationPanel, BoxLayout.Y_AXIS));
        for (int i = Elevator.FLOORS; i >= Elevator.FIRST_FLOOR; i--) {
            navigationPanel.add(newNavigationButtons(i));
        }
    }

    private JPanel newNavigationButtons(final int currentFloor) {
        JPanel buttons = new JPanel();

        JButton upButton = new JButton("Up");
        upButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                elevator.addUpWaiter(currentFloor);
                System.out.println("Up");
            }
        });

        JButton downButton = new JButton("Down");
        downButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Down");
                elevator.addDownWaiter(currentFloor);
            }
        });

        buttons.add(upButton);
        buttons.add(downButton);

        return buttons;
    }

    public JPanel getNavigationPanel() {
        return navigationPanel;
    }
}
