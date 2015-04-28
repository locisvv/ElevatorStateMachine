package svv.com.views;

import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Color;
import java.util.concurrent.TimeUnit;

public class ElevatorView extends JComponent {
    private int currentFloor = 0;
    private int value;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.cyan);
        g.drawRect(0, value * 4, 40, 80);
    }

    public void oneFloorUp() {
        for (int i = 0; i <= 10; i++) {
            value = (currentFloor * 10) + i;
            animation();
        }

        currentFloor++;
    }

    public void oneFloorDown() {
        for (int i = 0; i <= 10; i++) {
            value = (currentFloor * 10) - i;
            animation();
        }
        currentFloor--;
    }

    private void animation() {
        try {
            repaint();
            TimeUnit.MICROSECONDS.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
