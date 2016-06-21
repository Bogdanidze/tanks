package ua.study.field;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

import ua.study.element.barrier.Barrier;
import ua.study.element.sprite.Direction;
import ua.study.element.sprite.Tank;

public class BattlePanel extends JPanel implements ActionListener {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    private static final int DELAY = 25;    // 40 FPS

    private static final String LEVEL_1_FILE_NAME = "level1.txt";

    private Tank tank = new Tank(this);

    private Timer timer;

    protected List<Barrier> barriers = new ArrayList<>();

    public BattlePanel() throws HeadlessException, IOException {
        addKeyListener(new BattleKeyAdapter());
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDoubleBuffered(true);

        timer = new Timer(DELAY, this);
        timer.start();

        barriers = LevelLoader.loadLevel(LEVEL_1_FILE_NAME);
    }

    private class BattleKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT && Direction.LEFT != tank.getDirection())) {
                tank.setDirection(Direction.LEFT);
            }

            if ((key == KeyEvent.VK_RIGHT && Direction.RIGHT != tank.getDirection())) {
                tank.setDirection(Direction.RIGHT);
            }

            if ((key == KeyEvent.VK_UP && Direction.UP != tank.getDirection())) {
                tank.setDirection(Direction.UP);
            }

            if ((key == KeyEvent.VK_DOWN && Direction.DOWN != tank.getDirection())) {
                tank.setDirection(Direction.DOWN);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && Direction.LEFT == tank.getDirection()) {
                tank.setDirection(Direction.NONE);
            }

            if ((key == KeyEvent.VK_RIGHT)  && Direction.RIGHT == tank.getDirection()) {
                tank.setDirection(Direction.NONE);
            }

            if ((key == KeyEvent.VK_UP) && Direction.UP == tank.getDirection()) {
                tank.setDirection(Direction.NONE);
            }

            if ((key == KeyEvent.VK_DOWN) && Direction.DOWN == tank.getDirection()) {
                tank.setDirection(Direction.NONE);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moveSprites();
        repaint();
    }

    private void moveSprites() {
        tank.move();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        drawBarriers(graphics2D);
        tank.draw(graphics2D);
    }

    private void drawBarriers(Graphics2D graphics2D) {
        for (Barrier barrier : barriers) {
            barrier.draw(graphics2D);
        }
    }

    public List<Barrier> getBarriers() {
        return barriers;
    }
}
