package ua.study.screen;

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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.Arrays;
import javax.swing.JPanel;
import javax.swing.Timer;
import ua.study.sprite.Tank;

public class BattlePanel extends JPanel implements ActionListener {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;
    private static final int DELAY = 140;

    private static final String LEVEL_1_FILE_NAME = "../map/level1.txt";

    private static final String TANK_IMAGE_NAME = "/ua/study/sprite/Tank1.jpg";
    private Tank tank = new Tank(TANK_IMAGE_NAME);

    private int tempX;

    private Timer timer;

    public BattlePanel() throws HeadlessException, IOException {
        addKeyListener(new BattleKeyAdapter());
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setDoubleBuffered(true);

        timer = new Timer(DELAY, this);
        timer.start();

        byte[] level1 = Files.readAllBytes(FileSystems.getDefault().getPath("c:\\source\\Test\\src\\main\\resources/map", "level1.txt"));
        InputStream stream = getClass().getResourceAsStream(LEVEL_1_FILE_NAME);
        byte[] bytes = new byte[30];
        int read = stream.read(bytes);
        System.out.println("Level 1 = " + Arrays.toString(bytes));

        InputStreamReader inputStreamReader = new InputStreamReader(stream);
        char[] chars = new char[30];
        System.out.println(inputStreamReader.read(chars));
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
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.drawImage(tank.getImage(), tank.getX(), tank.getY(), null);
    }
}
