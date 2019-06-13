/**
 * class for the user player
 * @author David Bao
 * @author Armanya Dalmia
 * @author Feng Guo
 * @author Victor Lin
 * @author Arjun Pillai
 */
package Entities;

import java.awt.Image;

public class Player extends Character {
    private String id;
    private String playerName;

    public Player(Image sprite, int baseHealth, int baseSpeed, int baseDefense, int baseDexterity, String id, String playerName) {
        super(sprite, 20, 40, baseHealth, baseSpeed, baseDefense,baseDexterity,"Player");
        this.id = id;
        this.playerName = playerName;
    }

    /**
     * getID
     * @return String the unique ID of the player
     */
    public String getId() {
        return id;
    }

    /**
     * getPlayerName
     * @return String the name of the player
     */
    public String getPlayerName() {
        return playerName;
    }
}
