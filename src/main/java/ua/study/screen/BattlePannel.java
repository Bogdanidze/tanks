package ua.study.screen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class BattlePannel extends JPanel {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 300;

    private boolean running;

    public BattlePannel() throws HeadlessException {
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    private void doDrawing() {

    }
}
