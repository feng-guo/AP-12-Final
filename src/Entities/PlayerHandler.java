package Entities;

import World.Location;

public class PlayerHandler extends CharacterHandler {
    private PlayerInstance playerInstance;

    public PlayerHandler(PlayerInstance playerInstance, Location location) {
        super(playerInstance, location);
        this.playerInstance = playerInstance;
    }

    public PlayerInstance getPlayerInstance() {
        return playerInstance;
    }

    @Override
    public void move() {
        //Code here
    }

    @Override
    public void run() {

    }

    public void attack() {
        //Code here
    }

    public void use() {
        //Code here
    }
}
