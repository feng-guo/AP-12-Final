package Entities;

import World.Clock;
import World.Location;

public class EnemyHandler extends CharacterHandler {
    private EnemyInstance enemyInstance;

    public EnemyHandler(EnemyInstance enemyInstance, Location location) {
        super(enemyInstance, location);
        this.enemyInstance = enemyInstance;
    }

    public EnemyInstance getEnemyInstance() {
        return enemyInstance;
    }

    @Override
    public void move() {
        double rand = Math.random();
        if (rand<0.25) {
            enemyInstance.moveX(-enemyInstance.getSpeed());
        } else if (rand<0.5) {
            enemyInstance.moveX(enemyInstance.getSpeed());
        } else if (rand<0.75) {
            enemyInstance.moveY(-enemyInstance.getSpeed());
        } else {
            enemyInstance.moveY(enemyInstance.getSpeed());
        }
    }

    public void attack() {
        //Code here
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            move();
        }
    }
}
