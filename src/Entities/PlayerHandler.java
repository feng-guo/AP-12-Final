/**
 * class to handle the unique players
 * @author David Bao
 * @author Armanya Dalmia
 * @author Feng Guo
 * @author Victor Lin
 * @author Arjun Pillai
 */

package Entities;

import Items.Food;
import Items.Potion;
import World.Clock;
import World.Location;
import World.LocationHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerHandler extends CharacterHandler {
    private PlayerInstance playerInstance;
    private boolean[] keyPresses;
    private int xDirection;
    private int yDirection;




    public PlayerHandler(PlayerInstance playerInstance, LocationHandler location) {
        super(playerInstance, location);
        this.playerInstance = playerInstance;
        this.keyPresses = new boolean[4];

    }

    /**
     * getPlayerInstance
     * @return PlayerInstance the specific instance connected to this handler
     */
    public PlayerInstance getPlayerInstance() {
        return playerInstance;
    }

    private void determineMovement() {
        if (keyPresses[2]) {
            if (!keyPresses[3]) {
                xDirection = -1;
            } else {
                xDirection = 0;
            }
        } else if (keyPresses[3]) {
            xDirection = 1;
        }
        if (keyPresses[0]) {
            if (!keyPresses[1]) {
                yDirection = -1;
            } else {
                yDirection = 0;
            }
        } else if (keyPresses[1]) {
            yDirection = 1;
        }
        if (!keyPresses[0] && !keyPresses[1]) {
            yDirection = 0;
        }
        if (!keyPresses[2] && !keyPresses[3]) {
            xDirection = 0;
        }
    }

    /**
     * run
     * the tasks for the player
     */
    @Override
    public void run() {
        while (true) {
            determineMovement();
            move();
            decreaseHunger();
            starveToDeath();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {

            }
        }
    }

    /**
     * move
     * player movement adjusted to speed and computer speed
     */
    @Override
    public void move() {
        int d = (int)Math.sqrt(2) * playerInstance.getSpeed() * Clock.getDelta();
        if (yDirection == -1) {
            if (xDirection == -1) {
                playerInstance.moveX(-d);
                playerInstance.moveY(-d);
            } else if (xDirection == 1) {
                playerInstance.moveX(d);
                playerInstance.moveY(-d);
            } else {
                playerInstance.moveY(-playerInstance.getSpeed());
            }

        } else if (yDirection == 1) {
            if (xDirection == -1) {
                playerInstance.moveX(-d);
                playerInstance.moveY(d);
            } else if (xDirection == 1) {
                playerInstance.moveX(d);
                playerInstance.moveY(d);
            } else {
                playerInstance.moveY(playerInstance.getSpeed());
            }
        } else if (xDirection == -1){
            playerInstance.moveX(-playerInstance.getSpeed());
        } else if (xDirection == 1) {
            playerInstance.moveX(playerInstance.getSpeed());
        }
    }

    /**
     * keyPressed
     * @param key String for which key was interacted with
     */
    public void keyPressed(String key){
        if ("W".equals(key)) {
            keyPresses[0] = true;
        } else if ("S".equals(key)) {
            keyPresses[1] = true;
        } else if ("A".equals(key)) {
            keyPresses[2] = true;
        } else if ("D".equals(key)) {
            keyPresses[3] = true;
        }
    }

    /**
     * keyReleased
     * @param key String for which key was interacted with
     */
    public void keyReleased(String key) {
        if ("W".equalsIgnoreCase(key)) {
            keyPresses[0] = false;
        } else if ("S".equalsIgnoreCase(key)) {
            keyPresses[1] = false;
        } else if ("A".equalsIgnoreCase(key)) {
            keyPresses[2] = false;
        } else if ("D".equalsIgnoreCase(key)) {
            keyPresses[3] = false;
        }
    }

    /**
     * starveToDeath
     * reduce player health when they get too hungry
     */
    private void starveToDeath() {
        double currentTime = (System.nanoTime()/1e+9);
        double delta = currentTime - playerInstance.getLastStarveChange();
        if (delta > 20 && playerInstance.getCurrentHunger() <= 0) {
            playerInstance.heal(-1);
            playerInstance.setLastStarveChange(currentTime);
        }
    }

    /**
     * decreaseHunger
     * reduces player hunger
     */
    private void decreaseHunger() {
        double currentTime = (System.nanoTime()/1e+9);
        double delta = currentTime - playerInstance.getLastHungerChange();
        if (delta > 120) {
            playerInstance.changeCurrentHunger(-1);
            playerInstance.setLastHungerChange(currentTime);
        }
    }

    /**
     * getLastWeaponUse
     * @return double when the last weapon use was
     */
    public double getLastWeaponUse() {
        return playerInstance.getLastWeaponUse();
    }

    /**
     * setLastWeaponUse
     * @param lastWeaponUse double the time to set the last weapon use to
     */
    public void setLastWeaponUse(double lastWeaponUse) {
        playerInstance.setLastWeaponUse(lastWeaponUse);
    }

    /**
     * eat
     * Consume a food item
     * @param food the type of food eaten
     * @param index the set of that food
     */
    public void eat(Food food, int index) {
        double currentTime = System.nanoTime()/1e+9;
        if (currentTime - playerInstance.getLastConsumableUse() > 5) {
            food.use(playerInstance);
            playerInstance.getInventory().get(index).remove(1);
            if (playerInstance.getInventory().get(index).getStackAmount() == 0) {
                playerInstance.getInventory().remove(index);
            }
            playerInstance.setLastConsumableUse(currentTime);
        }
    }

    /**
     * drinkPotion
     * consume a potion
     * @param potion the type of potion consumed
     * @param index the set of that potion
     */
    public void drinkPotion(Potion potion, int index) {
        double currentTime = System.nanoTime()/1e+9;
        if (currentTime - playerInstance.getLastConsumableUse() > 5) {
            potion.use(playerInstance);
            playerInstance.getInventory().remove(index);
            playerInstance.setLastConsumableUse(currentTime);
        }
    }

    /**
     * getxDirection
     * @return int the x direction being faced
     */
    public int getxDirection() {
        return xDirection;
    }

    /**
     * getyDirection
     * @return int the y direction being faced
     */
    public int getyDirection() {
        return yDirection;
    }
}
