package Entities;

import World.Clock;
import World.Location;

public class EnemyHandler extends CharacterHandler {
    private EnemyInstance enemyInstance;
    private PlayerInstance p;

    public EnemyHandler(EnemyInstance enemyInstance, Location location) {
        super(enemyInstance, location);
        this.enemyInstance = enemyInstance;
    }

    public EnemyInstance getEnemyInstance() {
        return enemyInstance;
    }

    @Override
    public void move() {

        if (playerInRange() == false) {
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

            double dX = p.getX() - enemyInstance.getX();
            double dY = p.getY() - enemyInstance.getY();

            double divider = Math.sqrt(dX * dX + dY * dY);

            dX /= divider;
            dY /= divider;

            dX *= 8*enemyInstance.getSpeed();
            dY *= 8*enemyInstance.getSpeed();

            enemyInstance.moveX((int)dX);
            enemyInstance.moveY((int)dY);

        }
    }

    public boolean playerInRange () {
        getClosestPlayer();
        if (Math.sqrt(Math.pow(p.getX()-enemyInstance.getX(), 2)+ Math.pow(p.getY()-enemyInstance.getY(),2)) < 200){
            return true;
        }
        return false;
    }

    public void getClosestPlayer() {

        double minDistance = 0;
        double tempMinDistance = 0;
        int playerOnMapNumber = -1;

        for (int i =0; i < getLocation().getPlayerIDs().size(); i ++){
            p = getLocation().getPlayer(getLocation().getPlayerIDs().get(i));
            int playerX = p.getX();
            int playerY = p.getY();
            tempMinDistance = Math.sqrt(Math.pow(p.getX()-enemyInstance.getX(), 2)+ Math.pow(p.getY()-enemyInstance.getY(),2));
            if (i == 0) {
                minDistance = tempMinDistance;
                playerOnMapNumber = 0;
            }
            if (tempMinDistance < minDistance) {
                minDistance = tempMinDistance;
                playerOnMapNumber = i;
            }

        }
        p = getLocation().getPlayer(getLocation().getPlayerIDs().get(playerOnMapNumber));
    }

    public void attack() {
        if (Math.sqrt(Math.pow(p.getX()-enemyInstance.getX(), 2)+ Math.pow(p.getY()-enemyInstance.getY(),2)) < 5){
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
