package Entities;

import World.Clock;
import World.Location;

public class EnemyHandler extends CharacterHandler {
    private EnemyInstance enemyInstance;
    private PlayerInstance playerInstance; // not sure how to get Player's X and Y location
    private boolean playerSpotted = false;

    public EnemyHandler(EnemyInstance enemyInstance, Location location) {
        super(enemyInstance, location);
        this.enemyInstance = enemyInstance;
    }

    public EnemyInstance getEnemyInstance() {
        return enemyInstance;
    }

    @Override
    public void move() {
        if (playerSpotted == false) {
            double rand = Math.random();
            if (rand < 0.25) {
                enemyInstance.moveX(-8 * enemyInstance.getSpeed());
            } else if (rand < 0.5) {
                enemyInstance.moveX(8 * enemyInstance.getSpeed());
            } else if (rand < 0.75) {
                enemyInstance.moveY(-8 * enemyInstance.getSpeed());
            } else {
                enemyInstance.moveY(8 * enemyInstance.getSpeed());
            }

        } else {
            double dX = playerInstance.getX() - enemyInstance.getX();
            double dY = playerInstance.getY() - enemyInstance.getY();

            double divider = Math.sqrt(dX * dX + dY * dY);

            dX /= divider;
            dY /= divider;

            dX *= enemyInstance.getSpeed();
            dY *= enemyInstance.getSpeed();

            enemyInstance.moveX((int)dX);
            enemyInstance.moveY((int)dY);
        }
    }

    public boolean playerInRange () {
        if (Math.sqrt(Math.pow(playerInstance.getX()-enemyInstance.getX(), 2)+ Math.pow(playerInstance.getY()-enemyInstance.getY(),2)) < 20){
            return true;
        }
        return false;
    }

    public void attack() {
        if (Math.sqrt(Math.pow(playerInstance.getX()-enemyInstance.getX(), 2)+ Math.pow(playerInstance.getY()-enemyInstance.getY(),2)) < 5){
            int weaponRange = enemyInstance.getWeapon().getRange();
            int weaponDamage = enemyInstance.getWeapon().getDamage();
            // not sure what to do

        }
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
