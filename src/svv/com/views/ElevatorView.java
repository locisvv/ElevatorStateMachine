package svv.com.views;


import svv.com.controlers.Elevator;

import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Color;
import java.util.concurrent.TimeUnit;

public class ElevatorView extends JComponent {
    private int currentFloor = 1;
    private int value;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.cyan);
        g.drawRect(0, 0, 20, value * 2);
    }

    public void oneFloorUp() {
        for (int i = 0; i < 20; i++) {
            value = (currentFloor * 10) + i;
            animation();
        }
        currentFloor++;
        System.out.println(currentFloor);
    }

    public void oneFloorDown() {
        for (int i = 0; i < 20; i++) {
            value = (currentFloor * 10) - i;
            animation();
        }
        currentFloor--;
        System.out.println(currentFloor);
    }

    private void animation() {
        try {
            repaint();
            TimeUnit.MICROSECONDS.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
