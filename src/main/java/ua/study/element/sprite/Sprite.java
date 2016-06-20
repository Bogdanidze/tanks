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
        int newX = x - speed;
        if (newX < 0) {
            return;
        }
        if (isBarrierOnWay(newX, y)) {
           return;
        }
        x -= speed;
    }

    private boolean isBarrierOnWay(int newX, int newY) {
        Rectangle rectangle = new Rectangle(newX, newY, edgeSize, edgeSize);
        System.out.println("Start Check for new Tank position x=" + newX + ", y=" + newY);
        for (Barrier barrier : battlePanel.getBarriers()) {
            if (rectangle.intersects(
                    new Rectangle(barrier.getX(), barrier.getY(), BARRIER_SIZE, BARRIER_SIZE))) {
                System.out.println("Tank intersects with barrier " + barrier);
                return true;
            } else {
                System.out.println("Tank does NOT intersect with barrier " + barrier);
            }
        }
        System.out.println("Stop Check");
        return false;
    }

    public void moveRight() {
        int newX = x + speed;
        if (newX + edgeSize > BattlePanel.WIDTH) {
            return;
        }
        if (isBarrierOnWay(newX, y)) {
            return;
        }
        x += speed;
    }

    public void moveUp() {
        int newY = y - speed;
        if (newY <0) {
            return;
        }
        if (isBarrierOnWay(x, newY)) {
            return;
        }
        y -= speed;
    }

    public void moveDown() {
        int newY = y + speed;
        if (newY + edgeSize > BattlePanel.HEIGHT) {
            return;
        }
        if (isBarrierOnWay(x, newY)) {
            return;
        }
        y += speed;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
