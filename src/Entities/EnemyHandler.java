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
        //Code here
    }

    public void attack() {
        //Code here
    }

    @Override
    public void run() {

    }
}
