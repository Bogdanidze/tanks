package ua.study.element.barrier;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import ua.study.element.Drawable;
import ua.study.field.BattlePanel;

public class Barrier implements Drawable {

    public static final int BARRIER_SIZE = 100;

    private static final String BRICK_IMAGE_NAME = "brick.jpg";
    private static final String WATER_IMAGE_NAME = "water.jpg";
    private static final String CONCRETE_IMAGE_NAME = "concrete.jpg";
    private static final String TARGET_IMAGE_NAME = "target.png";

    private static Image brickImage;
    private static Image waterImage;
    private static Image concreteImage;
    private static Image targetImage;

    static {
        try {
            brickImage = ImageIO.read(Barrier.class.getResource(BRICK_IMAGE_NAME));
            waterImage = ImageIO.read(Barrier.class.getResource(WATER_IMAGE_NAME));
            concreteImage = ImageIO.read(Barrier.class.getResource(CONCRETE_IMAGE_NAME));
            targetImage = ImageIO.read(Barrier.class.getResource(TARGET_IMAGE_NAME));
        } catch (IOException e) {
            System.out.println("Error while loading barrier image: " + e);
        }
    }

    private final BarrierType barrierType;

    private final int x;

    private final int y;

    private Image image;

    public Barrier(BarrierType barrierType, String x, String y) {
        this.barrierType = barrierType;
        this.x = Integer.parseInt(x) * BARRIER_SIZE;
        this.y = Integer.parseInt(y) * BARRIER_SIZE;
        switch (barrierType) {
            case BRICK: image = brickImage; break;
            case WATER: image = waterImage; break;
            case CONCRETE: image = concreteImage; break;
            case TARGET: image = targetImage; break;
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
        graphics2D.drawImage(image, x, y, null);
    }

    @Override
    public String toString() {
        return "Barrier{" +
                "barrierType=" + barrierType +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    public void destroy() {
        BattlePanel.getInstance().getBarriers().remove(this);
    }
}
