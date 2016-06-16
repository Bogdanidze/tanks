package ua.study.sprite;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class Sprite {
    protected final Image image;
    protected int x;
    protected int y;
    protected Direction direction = Direction.NONE;

    public Sprite(String imageName) throws IOException {
        image = ImageIO.read(getClass().getResource(imageName));

    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }
}
