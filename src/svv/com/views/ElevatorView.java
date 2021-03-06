package svv.com.views;

import svv.com.controlers.Elevator;

import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Color;
import java.util.concurrent.TimeUnit;

public class ElevatorView extends JComponent {
    private int currentFloor = 0;
    private int floorValue;
    private int openDoorValue;
    private Elevator elevator;

    public ElevatorView(Elevator elevator) {
        this.elevator = elevator;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);

        if (elevator.getStop()) {
            g.setColor(Color.RED);
        }
        //paint elevator box
        g.drawRect(180, 400 - (floorValue * 7), 36, 50);
        g.drawRect(180, 400 - (floorValue * 7), 18 - openDoorValue, 50);
        g.drawRect(198 + openDoorValue, 400 - (floorValue * 7), 18 - openDoorValue, 50);

        //paint cable
        g.setColor(Color.black);
        g.drawLine(194, 400 - (floorValue * 7), 194, 100);
        g.drawLine(202, 400 - (floorValue * 7), 202, 100);
    }

    public void oneFloorUp() {
        for (int i = 0; i <= 10; i++) {
            floorValue = (currentFloor * 10) + i;
            animation();

            if (elevator.getStop()) {
                stop();
            }
        }
        currentFloor++;
    }

    public void oneFloorDown() {
        for (int i = 0; i <= 10; i++) {
            floorValue = (currentFloor * 10) - i;
            animation();

            if (elevator.getStop()) {
                stop();
            }
        }
        currentFloor--;
    }

    private void stop() {
        try {
            elevator.getCountDownLatch().await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void openDoor() {
        while (openDoorValue < 10) {
            openDoorValue++;
            animation();
        }
    }

    public void closeDoor() {
        while (openDoorValue >= 0) {
            openDoorValue--;
            animation();
        }
    }

    private void animation() {
        try {
            repaint();
            TimeUnit.MICROSECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
