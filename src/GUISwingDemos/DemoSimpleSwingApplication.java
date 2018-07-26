package GUISwingDemos;

import javax.swing.*;

public class DemoSimpleSwingApplication {
    private DemoSimpleSwingApplication() {
        JFrame jfrm = new JFrame("A simple Swing application");
        jfrm.setSize(400, 300);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel jLabel = new JLabel("Swing means powerful GUIs");

        jfrm.add(jLabel);

        jfrm.setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DemoSimpleSwingApplication();
            }
        });
    }
}
