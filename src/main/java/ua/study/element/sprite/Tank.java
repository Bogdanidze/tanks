package ua.study.element.sprite;

import java.awt.Graphics2D;
import java.io.IOException;

public class Tank extends Sprite {

    private static final String TANK_IMAGE_NAME = "Tank1.jpg";
    private static final int EDGE_SIZE = 100;

    public Tank() throws IOException {
        super(TANK_IMAGE_NAME, EDGE_SIZE);
        speed = 5;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(image, x, y, null);
    }
}
