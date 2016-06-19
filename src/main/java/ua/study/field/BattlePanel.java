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
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;
import ua.study.field.barrier.Barrier;
import ua.study.field.barrier.BarrierType;
import ua.study.sprite.Tank;

public class BattlePanel extends JPanel implements ActionListener {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;
    private static final int DELAY = 140;

    private static final String LEVEL_1_FILE_NAME = "level1.txt";
    private final String ROW_SEPARATOR = ":";
    private final String BARRIER_SEPARATOR = ";";
    private final String COORDINATE_SEPARATOR = ",";

    private Tank tank = new Tank();

    private Timer timer;
    private boolean initialState = true;

    private List<Barrier> barriers = new ArrayList<>();

    public BattlePanel() throws HeadlessException, IOException {
        addKeyListener(new BattleKeyAdapter());
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDoubleBuffered(true);

        timer = new Timer(DELAY, this);
        timer.start();

        loadLevel();
    }

    private void loadLevel() throws IOException {
        try (
                InputStream inputStream = getClass().getResourceAsStream(LEVEL_1_FILE_NAME);
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println("line = " + line);
                if (line.contains(ROW_SEPARATOR)) {
                    loadLine(line);
                } else {
                    System.out.println("Level file has on-error entry!");
                }
            }
            System.out.println("Barriers = " + barriers);
        }
    }

    private void loadLine(String line) {
        String[] typeWithCoordinates = line.split(ROW_SEPARATOR);
        BarrierType barrierType = BarrierType.getBarrierType(typeWithCoordinates[0]);
        System.out.println("barrierType  = " + barrierType );
        if (barrierType == null) {
            System.out.println("Line '" + line + "' does not contain proper barrier type!");
            return;
        }

        for (String coordinate : typeWithCoordinates[1].split(BARRIER_SEPARATOR)) {
            String[] coordinates = coordinate.split(COORDINATE_SEPARATOR);
            System.out.println("coordinates = " + Arrays.toString(coordinates));
            barriers.add(
                    new Barrier(barrierType, coordinates[0], coordinates[1]));
        }
    }


    private class BattleKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT)) {
                System.out.println("Move left");
                tank.moveLeft();
            }

            if ((key == KeyEvent.VK_RIGHT)) {
                System.out.println("Move right");
                tank.moveRight();
            }

            if ((key == KeyEvent.VK_UP)) {
                System.out.println("Move up");
                tank.moveUp();
            }

            if ((key == KeyEvent.VK_DOWN)) {
                System.out.println("Move down");
                tank.moveDown();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintInitialState(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.drawImage(tank.getImage(), tank.getX(), tank.getY(), null);
    }

    private void paintInitialState(Graphics g) {
        if (initialState) {

        }
    }
}
