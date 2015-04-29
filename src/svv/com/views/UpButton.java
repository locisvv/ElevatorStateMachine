package svv.com.views;

import javax.swing.JButton;
import java.awt.Graphics;

public class UpButton extends JButton {

    @Override
    protected void paintComponent(Graphics g) {
        g.drawPolygon(new int[]{5, 10, 15}, new int[]{15,5,15}, 3);
    }
}
