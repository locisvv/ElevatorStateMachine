package svv.com.views;

import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Color;
import java.util.concurrent.TimeUnit;

public class ElevatorView extends JComponent {
    private int currentFloor = 0;
    private int floorValue;
    private int openDoorValue;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.drawRect(180,                 400 - (floorValue * 7), 18 - openDoorValue, 50);
        g.drawRect(198 + openDoorValue, 400 - (floorValue * 7), 18 - openDoorValue, 50);
    }

    public void oneFloorUp() {
        for (int i = 0; i <= 10; i++) {
            floorValue = (currentFloor * 10) + i;
            animation();
        }
        currentFloor++;
    }

    public void oneFloorDown() {
        for (int i = 0; i <= 10; i++) {
            floorValue = (currentFloor * 10) - i;
            animation();
        }
        currentFloor--;
    }


    public void openDoor() {
        for (; openDoorValue < 10; openDoorValue++) {
            animation();
        }
    }

    public void closeDoor() {
        for (; openDoorValue > 0; openDoorValue--) {
            animation();
        }
    }

    private void animation() {
        try {
            repaint();
            TimeUnit.MICROSECONDS.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
