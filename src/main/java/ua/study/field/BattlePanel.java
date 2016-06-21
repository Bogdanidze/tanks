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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

import ua.study.element.barrier.Barrier;
import ua.study.element.barrier.BarrierType;
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

    private static class LevelLoader {

        private final static String ROW_SEPARATOR = ":";
        private final static String BARRIER_SEPARATOR = ";";
        private final static String COORDINATE_SEPARATOR = ",";

        public static List<Barrier> loadLevel(String levelFileName) throws IOException {
            List<Barrier> tempBarriers = new ArrayList<>();
            try (
                    InputStream inputStream = BattlePanel.class.getResourceAsStream(levelFileName);
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            ) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println("line = " + line);
                    if (line.contains(ROW_SEPARATOR)) {
                        tempBarriers.addAll(loadLine(line));
                    } else {
                        System.out.println("Level file has on-error entry!");
                    }
                }
                System.out.println("Loaded barriers = " + tempBarriers);
            }
            return tempBarriers;
        }

        private static List<Barrier> loadLine(String line) {
            String[] typeWithCoordinates = line.split(ROW_SEPARATOR);

            BarrierType barrierType = BarrierType.getBarrierType(typeWithCoordinates[0]);
            System.out.println("barrierType  = " + barrierType);
            if (barrierType == null) {
                System.out.println("Line '" + line + "' does not contain proper barrier type!");
                return Collections.emptyList();
            }

            List<Barrier> tempBarriers = new ArrayList<>();
            for (String coordinate : typeWithCoordinates[1].split(BARRIER_SEPARATOR)) {
                String[] coordinates = coordinate.split(COORDINATE_SEPARATOR);
                System.out.println("coordinates = " + Arrays.toString(coordinates));
                tempBarriers.add(
                        new Barrier(barrierType, coordinates[0], coordinates[1]));
            }
            return tempBarriers;
        }
    }

    private class BattleKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT)) {
                tank.setDirection(Direction.LEFT);
            }

            if ((key == KeyEvent.VK_RIGHT)) {
                tank.setDirection(Direction.RIGHT);
            }

            if ((key == KeyEvent.VK_UP)) {
                tank.setDirection(Direction.UP);
            }

            if ((key == KeyEvent.VK_DOWN)) {
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
