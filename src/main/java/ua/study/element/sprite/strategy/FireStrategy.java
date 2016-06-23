package ua.study.element.sprite.strategy;

import ua.study.element.sprite.Tank;

public class FireStrategy {

    private static final int MIN_FIRE_INTERVAL = 1000;  // Fire not more than 1 time per second

    private long lastFiredAt;

    private final Tank tank;

    public FireStrategy(Tank tank) {
        this.tank = tank;
        lastFiredAt = System.currentTimeMillis();
    }

    public void fire() {
        if (System.currentTimeMillis() - MIN_FIRE_INTERVAL >= lastFiredAt) {
            lastFiredAt = System.currentTimeMillis();
        }
    }
}
