package Entities;

import World.Location;
import World.LocationHandler;

public class EnemyHandler extends CharacterHandler {
    private EnemyInstance enemyInstance;
    private PlayerInstance p;

    private double lastWeaponUse;

    public EnemyHandler(EnemyInstance enemyInstance, LocationHandler location) {
        super(enemyInstance, location);
        this.enemyInstance = enemyInstance;
        this.lastWeaponUse = 0;
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

            if (Math.abs(p.getX() - enemyInstance.getX()) >= Math.abs(p.getY() - enemyInstance.getY())){
                if ((p.getX() - enemyInstance.getX())>=0) {
                    enemyInstance.moveX(8*enemyInstance.getSpeed());
                } else {
                    enemyInstance.moveX(-8*enemyInstance.getSpeed());
                }
            } else {
                if ((p.getY() - enemyInstance.getY())>=0) {
                    enemyInstance.moveY(8*enemyInstance.getSpeed());
                } else {
                    enemyInstance.moveY(-8*enemyInstance.getSpeed());
                }
            }

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
        if (Math.sqrt(Math.pow(p.getX()-enemyInstance.getX(), 2)+ Math.pow(p.getY()-enemyInstance.getY(),2)) <= enemyInstance.getWeapon().getRange()){
            getLocationHandler().enemyAttack(p.getX(), p.getY(), this);
        }
    }

    public double getLastWeaponUse() {
        return lastWeaponUse;
    }

    public void setLastWeaponUse(double lastWeaponUse) {
        this.lastWeaponUse = lastWeaponUse;
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
