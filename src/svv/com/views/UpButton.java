package svv.com.views;

import javax.swing.JToggleButton;
import java.awt.Graphics;
import java.awt.Color;

public class UpButton extends JToggleButton {
    public UpButton() {
        setBackground(Color.white);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawPolygon(new int[]{5, 10, 15}, new int[]{15,5,15}, 3);
    }
}
