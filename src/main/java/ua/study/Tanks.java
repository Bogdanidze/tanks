package ua.study;

import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import ua.study.field.BattlePanel;

public class Tanks extends JFrame {

    public static final String TITLE = "Tanks";

    public Tanks() throws HeadlessException, IOException {
        add(new BattlePanel());

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
                try {
                    JFrame tanks = new Tanks();
                    tanks.setVisible(true);
                } catch (IOException e) {
                    System.out.println("Exception while starting the game: " + e);
                }
            }
        });
    }
}
