package ua.study.field.barrier;

public class Barrier {

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
    public String toString() {
        return "Barrier{" +
                "barrierType=" + barrierType +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
