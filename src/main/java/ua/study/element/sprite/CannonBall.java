package ua.study.element.sprite;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

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

    public static final int EDGE_SIZE = 20;

    public CannonBall(int x, int y) {
        super(cannonBallImage, EDGE_SIZE, x, y);
    }
}
