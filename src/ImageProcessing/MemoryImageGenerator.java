package ImageProcessing;

import java.applet.Applet;
import java.awt.*;
import java.awt.image.MemoryImageSource;

public class MemoryImageGenerator extends Applet {
    Image img;

    public void init() {
        Dimension d = getSize();
        int pixels[] = new int[d.width * d.height];
        int i = 0;
        for (int y = 0; y < d.height; y++) {
            for (int x = 0; x < d.width; x++) {
                int r = (x^y) &0xff;
                int g = (x*2^y*2)&0xff;
                int b = (x*4^y*4)&0xff;
                pixels[i++] = (255 << 24) | (r << 16) | (g << 8) | b;
            }
        }
        img = createImage(new MemoryImageSource(d.width, d.height, pixels, 0, d.width));
    }

    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, this);
    }
}
