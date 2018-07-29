package ImageProcessing;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class DoubleBuffer extends Applet {
    int gap = 3;
    int mx, my;
    boolean flicker = true;
    Image img = null;
    int w, h;

    @Override
    public void init() {
        Dimension d = getSize();
        w = d.width;
        h = d.height;

        img = createImage(w, h);
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                mx = e.getX();
                my = e.getY();
                flicker = false;
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mx = e.getX();
                my = e.getY();
                flicker = true;
                repaint();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        Graphics screengc = null;

        if (!flicker) {
            screengc = g;
            g = img.getGraphics();
        }
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, w, h);

        g.setColor(Color.RED);
        for (int i = 0; i < w; i += gap) {
            g.drawLine(i, 0, w - i, h);
        }
        for (int i = 0; i < h; i += gap)
            g.drawLine(0, i, w, h - i);

        g.setColor(Color.black);
        g.drawString("Press mouse button to double img", 10, h / 2);

        g.setColor(Color.yellow);
        g.fillOval(mx - gap, my - gap, gap * 2 + 1, gap * 2 + 1);

        if (!flicker) {
            screengc.drawImage(img, 0, 0, null);
        }
    }

    public void update(Graphics g) {
        paint(g);
    }
}
