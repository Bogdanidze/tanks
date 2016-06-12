package ua.study.screen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BattlePannel extends JPanel implements ActionListener {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;
    private static final int DELAY = 140;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame;

    private Timer timer;

    public BattlePannel() throws HeadlessException {
        addKeyListener(new BattleKeyAdapter());
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        timer = new Timer(DELAY, this);
        timer.start();
    }


    private class BattleKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                System.out.println("Direction changed to left");
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                System.out.println("Direction changed to right");
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                System.out.println("Direction changed to up");
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                System.out.println("Direction changed to down");
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Action performed = " + e);
    }
}
