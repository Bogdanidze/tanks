package ua.study.element.barrier;

import java.awt.Graphics2D;
import ua.study.element.Drawable;

public class Barrier implements Drawable {

    public static final int BARRIER_SIZE = 100;

    private final BarrierType barrierType;

    private final int x;

    private final int y;

    public Barrier(BarrierType barrierType, String x, String y) {
        this.barrierType = barrierType;
        this.x = Integer.parseInt(x);
        this.y = Integer.parseInt(y);
    }

    public BarrierType getBarrierType() {
        return barrierType;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawString(barrierType.name(), x * BARRIER_SIZE, y * BARRIER_SIZE);
    }

    @Override
    public String toString() {
        return "Barrier{" +
                "barrierType=" + barrierType +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
