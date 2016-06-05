package ua.study.screen;

import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class BattleScreen extends JFrame {

    public static final String TITLE = "Tanks";

    private boolean running;

    public BattleScreen() throws HeadlessException {
        setTitle(TITLE);
        setSize(new Dimension(400, 500));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void start() {
        running = true;
        while (running) {

        }
    }
}
