package ua.study.element.barrier;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import ua.study.element.Drawable;

public class Barrier implements Drawable {

    public static final int BARRIER_SIZE = 100;

    private static final String BRICK_IMAGE_NAME = "brick.jpg";
    private static final String WATER_IMAGE_NAME = "water.jpg";
    private static final String CONCRETE_IMAGE_NAME = "concrete.jpg";
    private static Image brickImage;
    private static Image waterImage;
    private static Image concreteImage;

    static {
        try {
            brickImage = ImageIO.read(Barrier.class.getResource(BRICK_IMAGE_NAME));
            waterImage = ImageIO.read(Barrier.class.getResource(WATER_IMAGE_NAME));
            concreteImage = ImageIO.read(Barrier.class.getResource(CONCRETE_IMAGE_NAME));
        } catch (IOException e) {
            System.out.println("Error while loading brick image: " + e);
        }
    }

    private final BarrierType barrierType;

    private final int x;

    private final int y;

    private Image image;

    public Barrier(BarrierType barrierType, String x, String y) {
        this.barrierType = barrierType;
        this.x = Integer.parseInt(x);
        this.y = Integer.parseInt(y);
        switch (barrierType) {
            case BRICK: image = brickImage; break;
            case WATER: image = waterImage; break;
            case CONCRETE: image = concreteImage; break;
        }
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
        graphics2D.drawImage(image, x * BARRIER_SIZE, y * BARRIER_SIZE, null);
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
