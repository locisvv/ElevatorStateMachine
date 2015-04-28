package svv.com.views;

import javax.swing.*;
import java.awt.*;

/**
 * Created by svv on 4/28/15.
 */
public class DownButton extends JButton {

    @Override
    protected void paintComponent(Graphics g) {
        g.drawPolygon(new int[]{5, 10, 15}, new int[]{5,15,5}, 3);
    }
}
