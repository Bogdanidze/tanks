package ua.study.element.sprite.strategy;

import ua.study.element.sprite.CannonBall;
import ua.study.element.sprite.Direction;
import ua.study.element.sprite.Tank;

public class FireStrategy {

    private static final int MIN_FIRE_INTERVAL = 1000;  // Fire not more than 1 time per second

    private long lastFiredAt;

    private final Tank tank;

    public FireStrategy(Tank tank) {
        this.tank = tank;
        lastFiredAt = System.currentTimeMillis();
    }

    public CannonBall fire() {
        if (System.currentTimeMillis() - MIN_FIRE_INTERVAL >= lastFiredAt) {
            lastFiredAt = System.currentTimeMillis();
            Direction direction = tank.getDirection();
            int x = 0;
            int y = 0;
            switch (direction) {
                case LEFT:
                    x = tank.getX() - CannonBall.EDGE_SIZE;
                    y = tank.getY() + Tank.EDGE_SIZE / 2 - CannonBall.EDGE_SIZE / 2;
                    break;
                case RIGHT:
                    x = tank.getX() + Tank.EDGE_SIZE;
                    y = tank.getY() + Tank.EDGE_SIZE / 2 - CannonBall.EDGE_SIZE / 2;
                    break;
                case UP:
                    x = tank.getX() + Tank.EDGE_SIZE / 2 - CannonBall.EDGE_SIZE / 2;
                    y = tank.getY() - CannonBall.EDGE_SIZE;
                    break;
                case DOWN:
                    x = tank.getX() + Tank.EDGE_SIZE / 2 - CannonBall.EDGE_SIZE / 2;
                    y = tank.getY() + Tank.EDGE_SIZE;
                    break;
            }
            return new CannonBall(x, y, direction);
        } else {
            return null;
        }
    }
}
