package GUISwingDemos.paint;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class PaintPanel extends JPanel {
    private Insets ins;
    private Random rand;

    public PaintPanel() {
        setBorder(BorderFactory.createLineBorder(Color.RED, 5));
        rand = new Random();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x, y, x2, y2;
        // get the height and width of the component.
        int height = getHeight();
        int width = getWidth();

        // get the insets
        ins = getInsets();

        // Draw ten lines whose endpoints are randomly generated
        for (int i = 0; i < 10; i++) {
            x = rand.nextInt(width-ins.left);
            y = rand.nextInt(height-ins.bottom);
            x2 = rand.nextInt(width-ins.left);
            y2 = rand.nextInt(height-ins.bottom);

            g.drawLine(x, y, x2, y2);
        }
    }
}
