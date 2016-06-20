package ua.study.element.sprite;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import ua.study.element.Drawable;
import ua.study.field.BattlePanel;

public abstract class Sprite implements Drawable{
    protected final Image image;
    protected int x;
    protected int y;
    protected Direction direction = Direction.NONE;
    protected int speed = 1;
    private final int edgeSize;

    public Sprite(String imageName, int edgeSize) throws IOException {
        image = ImageIO.read(getClass().getResource(imageName));
        this.edgeSize = edgeSize;
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
        if (x - speed < 0) {
            return;
        }
        x -= speed;
    }

    public void moveRight() {
        if (x + edgeSize + speed > BattlePanel.WIDTH) {
            return;
        }
        x += speed;
    }

    public void moveUp() {
        if (y - speed <0) {
            return;
        }
        y -= speed;
    }

    public void moveDown() {
        if (y + edgeSize + speed > BattlePanel.HEIGHT) {
            return;
        }
        y += speed;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
