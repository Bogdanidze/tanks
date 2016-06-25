package ua.study.element.sprite;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import ua.study.element.barrier.Barrier;
import ua.study.element.barrier.BarrierType;
import ua.study.field.BattlePanel;

public class CannonBall extends Sprite {

    private static final String CANNON_BALL_IMAGE = "cannonBall.png";

    private static Image cannonBallImage;
    static {
        try {
            cannonBallImage = ImageIO.read(CannonBall.class.getResource(CANNON_BALL_IMAGE));
        } catch (IOException e) {
            System.out.println("Error while loading cannon ball image: " + e);
        }
    }

    private static final int SPEED = 5;

    public static final int EDGE_SIZE = 20;

    public CannonBall(int x, int y, Direction direction) {
        super(cannonBallImage, EDGE_SIZE, x, y, direction, SPEED, true);
    }

    @Override
    public void move() {
        if (moving) {
            super.move();
        } else {
            destroy();
        }
    }

    @Override
    protected boolean reactOnBarrier(Barrier barrier) {
        if (BarrierType.WATER == barrier.getBarrierType()) {
            return false;
        }
        if (BarrierType.BRICK == barrier.getBarrierType()) {
            barrier.destroy();
        }
        destroy();
        return true;
    }

    private void destroy() {
        BattlePanel.getInstance().getCannonBalls().remove(this);
    }
}
