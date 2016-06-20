package ua.study.element.sprite;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;
import ua.study.element.Drawable;
import ua.study.element.barrier.Barrier;
import static ua.study.element.barrier.Barrier.BARRIER_SIZE;
import ua.study.field.BattlePanel;

public abstract class Sprite implements Drawable{
    protected final Image image;
    protected int x;
    protected int y;
    protected Direction direction = Direction.NONE;
    protected int speed = 1;
    private final int edgeSize;
    private final BattlePanel battlePanel;

    public Sprite(String imageName, int edgeSize, BattlePanel battlePanel) throws IOException {
        image = ImageIO.read(getClass().getResource(imageName));
        this.edgeSize = edgeSize;
        this.battlePanel = battlePanel;
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
        Rectangle rectangle = new Rectangle(x - speed, y, edgeSize, edgeSize);
        System.out.println("Start Check for new Tank position x=" + x + ", y=" + y);
        for (Barrier barrier : battlePanel.getBarriers()) {
            if (rectangle.intersects(
                    new Rectangle(barrier.getX(), barrier.getY(), BARRIER_SIZE, BARRIER_SIZE))) {
                System.out.println("Tank intersects with barrier " + barrier);
                return;
            } else {
                System.out.println("Tank does NOT intersect with barrier " + barrier);
            }
        }
        System.out.println("Stop Check");
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
