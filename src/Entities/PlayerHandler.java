package Entities;

import World.Location;

public class PlayerHandler extends CharacterHandler {
    private PlayerInstance playerInstance;
    private boolean[] keyPresses;
    private int xDirection;
    private int yDirection;

    public PlayerHandler(PlayerInstance playerInstance, Location location) {
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
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void move() {
        int d = (int)Math.sqrt(2)*10;
        if (yDirection == -1) {
            if (xDirection == -1) {
                playerInstance.moveX(-d);
                playerInstance.moveY(-d);
            } else if (xDirection == 1) {
                playerInstance.moveX(d);
                playerInstance.moveY(-d);
            } else {
                playerInstance.moveY(-10);
            }
        } else if (yDirection == 1) {
            if (xDirection == -1) {
                playerInstance.moveX(-d);
                playerInstance.moveY(d);
            } else if (xDirection == 1) {
                playerInstance.moveX(d);
                playerInstance.moveY(d);
            } else {
                playerInstance.moveY(10);
            }
        } else if (xDirection == -1){
            playerInstance.moveX(-10);
        } else if (xDirection == 1) {
            playerInstance.moveX(10);
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

    public void attack() {
        //Code here
    }

    public void use() {
        //Code here
    }
}
