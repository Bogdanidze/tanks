package ua.study;

import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class BattleFiled extends JFrame {

    public static final String TITLE = "Tanks";

    public BattleFiled() throws HeadlessException {
        setTitle(TITLE);
        setSize(new Dimension(400, 500));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
