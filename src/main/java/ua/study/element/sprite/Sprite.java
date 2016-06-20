package ua.study.element.sprite;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import ua.study.element.Drawable;

public abstract class Sprite implements Drawable{
    protected final Image image;
    protected int x;
    protected int y;
    protected Direction direction = Direction.NONE;
    protected int speed = 1;

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

    public void moveLeft() {
        x -= speed;
    }

    public void moveRight() {
        x += speed;
    }

    public void moveUp() {
        y -= speed;
    }

    public void moveDown() {
        y += speed;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
