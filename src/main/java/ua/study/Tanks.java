package ua.study;

import java.awt.EventQueue;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import ua.study.screen.BattlePannel;

public class Tanks extends JFrame {

    public static final String TITLE = "Tanks";

    public Tanks() throws HeadlessException {
        add(new BattlePannel());

        setResizable(false);
        pack();

        setTitle(TITLE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame tanks = new Tanks();
                tanks.setVisible(true);
            }
        });
    }
}
