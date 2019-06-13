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

    private void starveToDeath() {
        double currentTime = (System.nanoTime()/1e+9);
        double delta = currentTime - playerInstance.getLastStarveChange();
        if (delta > 20) {
            playerInstance.heal(-1);
            playerInstance.setLastStarveChange(currentTime);
        }
    }

    private void decreaseHunger() {
        double currentTime = (System.nanoTime()/1e+9);
        double delta = currentTime - playerInstance.getLastHungerChange();
        if (delta > 120) {
            playerInstance.changeCurrentHunger(-1);
            playerInstance.setLastHungerChange(currentTime);
        }
    }

    public double getLastWeaponUse() {
        return playerInstance.getLastWeaponUse();
    }

    public void setLastWeaponUse(double lastWeaponUse) {
        playerInstance.setLastWeaponUse(lastWeaponUse);
    }

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

    public void drinkPotion(Potion potion, int index) {
        double currentTime = System.nanoTime()/1e+9;
        if (currentTime - playerInstance.getLastConsumableUse() > 5) {
            potion.use(playerInstance);
            playerInstance.getInventory().remove(index);
            playerInstance.setLastConsumableUse(currentTime);
        }
    }

    public int getxDirection() {
        return xDirection;
    }

    public int getyDirection() {
        return yDirection;
    }
}
