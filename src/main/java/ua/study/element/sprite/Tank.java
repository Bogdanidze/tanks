package ua.study.element.sprite;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import ua.study.element.sprite.strategy.FireStrategy;

public class Tank extends Sprite {

    private static final String IMAGE_NAME_LEFT = "Tank3Left.png";
    private static final String IMAGE_NAME_RIGHT = "Tank3Right.png";
    private static final String IMAGE_NAME_UP = "Tank3Up.png";
    private static final String IMAGE_NAME_DOWN = "Tank3Down.png";

    private static Image imageLeft;
    private static Image imageRight;
    private static Image imageUp;
    private static Image imageDown;
    static {
        try {
            imageLeft = ImageIO.read(Tank.class.getResource(IMAGE_NAME_LEFT));
            imageRight = ImageIO.read(Tank.class.getResource(IMAGE_NAME_RIGHT));
            imageUp = ImageIO.read(Tank.class.getResource(IMAGE_NAME_UP));
            imageDown = ImageIO.read(Tank.class.getResource(IMAGE_NAME_DOWN));
        } catch (IOException e) {
            System.out.println("Error while loading batank image: " + e);
        }
    }

    private static final int EDGE_SIZE = 90;

    private static final int PLAYER_1_INITIAL_POSITION_X = 0;
    private static final int PLAYER_1_INITIAL_POSITION_Y = 0;

    private FireStrategy fireStrategy = new FireStrategy(this);

    public Tank() {
        super(imageUp, EDGE_SIZE, PLAYER_1_INITIAL_POSITION_X, PLAYER_1_INITIAL_POSITION_Y);
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
            case LEFT: image = imageLeft; break;
            case RIGHT: image = imageRight; break;
            case UP: image = imageUp; break;
            case DOWN: image = imageDown; break;
        }
    }

    public void fire() {
        fireStrategy.fire();
    }
}
