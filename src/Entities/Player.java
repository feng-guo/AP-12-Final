package Entities;

import java.awt.Image;

public class Player extends Character {
    private String id;
    private String playerName;

    public Player(Image sprite, int baseHealth, int baseSpeed, int baseDefense, int baseDexterity, String id, String playerName) {
        super(sprite, 0, 0, baseHealth, baseSpeed, baseDefense,baseDexterity,"Player");
        this.id = id;
        this.playerName = playerName;
    }

    public String getId() {
        return id;
    }

    public String getPlayerName() {
        return playerName;
    }
}
