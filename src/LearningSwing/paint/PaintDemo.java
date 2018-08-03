package LearningSwing.paint;

import javax.swing.*;

public class PaintDemo {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PaintDemo();
            }
        });
    }

    JLabel jLabel;
    PaintPanel pp;

    private PaintDemo() {
        JFrame jFrame = new JFrame("Paint Demo");

        jFrame.setSize(200, 150);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pp = new PaintPanel();

        jFrame.add(pp);
        jFrame.setVisible(true);
    }
}
