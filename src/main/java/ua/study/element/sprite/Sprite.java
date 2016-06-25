package ua.study.element.sprite;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import ua.study.element.Drawable;
import ua.study.element.barrier.Barrier;
import static ua.study.element.barrier.Barrier.BARRIER_SIZE;
import ua.study.field.BattlePanel;

public abstract class Sprite implements Drawable{

    protected Image image;
    private final int edgeSize;
    protected int x;
    protected int y;
    protected Direction direction;
    protected int speed;
    protected boolean moving;

    public Sprite(Image image, int edgeSize, int x, int y, int speed) {
        this.image = image;
        this.edgeSize = edgeSize;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public Sprite(Image image, int edgeSize, int x, int y, Direction direction, int speed, boolean moving) {
        this(image, edgeSize, x, y, speed);
        this.direction = direction;
        this.moving = moving;
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

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(image, x, y, null);
    }

    public void move() {
        switch (direction) {
            case LEFT: moveLeft(); break;
            case RIGHT: moveRight(); break;
            case UP: moveUp(); break;
            case DOWN: moveDown(); break;
        }
    }

    private void moveLeft() {
        int newX = x - speed;
        if (newX < 0 || isBarrierOnWay(newX, y)) {
            moving = false;
            return;
        }
        x -= speed;
    }

    private boolean isBarrierOnWay(int newX, int newY) {
        Rectangle rectangle = new Rectangle(newX, newY, edgeSize, edgeSize);
        for (Barrier barrier : BattlePanel.getInstance().getBarriers()) {
            if (rectangle.intersects(
                    new Rectangle(barrier.getX(), barrier.getY(), BARRIER_SIZE, BARRIER_SIZE))) {
                return reactOnBarrier(barrier);
            }
        }
        return false;
    }

    /**
     * Returns true if a barrier blocks the sprite form moving, otherwise returns false.
     * Also makes additional actions related to collision with the barrier (such as barrier destroying).
     * @param barrier barrier that was found on the way of this sprite
     * @return true if a barrier blocks the sprite form moving, otherwise returns false.
     */
    protected abstract boolean reactOnBarrier(Barrier barrier);

    private void moveRight() {
        int newX = x + speed;
        if (newX + edgeSize > BattlePanel.WIDTH || isBarrierOnWay(newX, y)) {
            moving = false;
            return;
        }
        x += speed;
    }

    private void moveUp() {
        int newY = y - speed;
        if (newY < 0 || isBarrierOnWay(x, newY)) {
            moving = false;
            return;
        }
        y -= speed;
    }

    private void moveDown() {
        int newY = y + speed;
        if (newY + edgeSize > BattlePanel.HEIGHT || isBarrierOnWay(x, newY)) {
            moving = false;
            return;
        }
        y += speed;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
