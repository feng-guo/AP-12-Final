package Entities;

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
            enemyInstance.moveX(-10);
        } else if (rand<0.5) {
            enemyInstance.moveX(10);
        } else if (rand<0.75) {
            enemyInstance.moveY(-10);
        } else {
            enemyInstance.moveY(10);
        }
    }

    public void attack() {
        //Code here
    }

    @Override
    public void run() {
        move();
    }
}
