package ua.study.element.sprite.strategy;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;
import ua.study.element.sprite.CannonBall;
import ua.study.element.sprite.Direction;
import ua.study.element.sprite.Tank;
import ua.study.field.BattlePanel;

public class EnemyStrategy implements ActionListener {

    private static final int MAX_ENEMIES = 3;

    private static final int DELAY = 1000;

    private List<Tank> activeEnemies = new ArrayList<>(MAX_ENEMIES);

    private Timer timer = new Timer(DELAY, this);

    private static final Random random = new Random();

    public EnemyStrategy() {
        activeEnemies.add(new Tank(0, 0));
        activeEnemies.add(new Tank(250, 0));
        activeEnemies.add(new Tank(500, 0));

        timer.start();
    }

    public List<Tank> getActiveEnemies() {
        return activeEnemies;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (BattlePanel.getInstance().isPause()) {
            return;
        }
        for (Tank enemy : activeEnemies) {
            enemy.setDirection(getRandomDirection());
            enemy.setMoving(random.nextBoolean());
            tryToFire(enemy);
        }
    }

    private void tryToFire(Tank enemy) {
        if (random.nextBoolean()) {
            CannonBall cannonBall = enemy.fire();
            if (cannonBall != null) {
                BattlePanel.getInstance().getCannonBalls().add(cannonBall);
            }
        }
    }

    private static Direction getRandomDirection() {
        switch (random.nextInt(4)) {
            case 0: return Direction.LEFT;
            case 1: return Direction.RIGHT;
            case 2: return Direction.UP;
            case 3: return Direction.DOWN;
        }
        throw new RuntimeException("Generator returned value not associated with any of directions.");
    }
}
