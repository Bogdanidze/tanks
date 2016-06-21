package ua.study.element.sprite;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import ua.study.field.BattlePanel;

public class Tank extends Sprite {

    private static final String TANK_IMAGE_NAME_LEFT = "Tank2Left.jpg";
    private static final String TANK_IMAGE_NAME_RIGHT = "Tank2Right.jpg";
    private static final String TANK_IMAGE_NAME_UP = "Tank2Up.jpg";
    private static final String TANK_IMAGE_NAME_DOWN = "Tank2Down.jpg";

    private static Image tankImageleft;
    private static Image tankImageRight;
    private static Image tankImageUp;
    private static Image tankImageDown;
    static {
        try {
            tankImageleft = ImageIO.read(Tank.class.getResource(TANK_IMAGE_NAME_LEFT));
            tankImageRight = ImageIO.read(Tank.class.getResource(TANK_IMAGE_NAME_RIGHT));
            tankImageUp = ImageIO.read(Tank.class.getResource(TANK_IMAGE_NAME_UP));
            tankImageDown = ImageIO.read(Tank.class.getResource(TANK_IMAGE_NAME_DOWN));
        } catch (IOException e) {
            System.out.println("Error while loading batank image: " + e);
        }
    }

    private static final int EDGE_SIZE = 90;

    public Tank(BattlePanel battlePanel) throws IOException {
        super(tankImageUp, EDGE_SIZE, battlePanel);
        speed = 3;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(image, x, y, null);
    }

    @Override
    public void setDirection(Direction direction) {
        super.setDirection(direction);
        switch (direction) {
            case LEFT: image = tankImageleft; break;
            case RIGHT: image = tankImageRight; break;
            case UP: image = tankImageUp; break;
            case DOWN: image = tankImageDown; break;
        }
    }
}
