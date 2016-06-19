package ua.study.sprite;

import java.io.IOException;

public class Tank extends Sprite{

    private static final String TANK_IMAGE_NAME = "Tank1.jpg";

    public Tank() throws IOException {
        super(TANK_IMAGE_NAME);
    }
}
