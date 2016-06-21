package ua.study.element.sprite;

import java.awt.Graphics2D;
import java.io.IOException;
import ua.study.field.BattlePanel;

public class Tank extends Sprite {

    private static final String TANK_IMAGE_NAME = "Tank2.jpg";
    private static final int EDGE_SIZE = 90;

    public Tank(BattlePanel battlePanel) throws IOException {
        super(TANK_IMAGE_NAME, EDGE_SIZE, battlePanel);
        speed = 3;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(image, x, y, null);
    }
}
