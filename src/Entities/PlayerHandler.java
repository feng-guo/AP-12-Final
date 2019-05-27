package Entities;

public class PlayerHandler extends CharacterHandler {
    private PlayerInstance playerInstance;

    public PlayerHandler(PlayerInstance playerInstance) {
        super(playerInstance);
        this.playerInstance = playerInstance;
    }

    public PlayerInstance getPlayerInstance() {
        return playerInstance;
    }

    @Override
    public void move() {
        //Code here
    }

    public void attack() {
        //Code here
    }

    public void use() {
        //Code here
    }
}
