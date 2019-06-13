/**
 * abstract class that branches into player, enemy, and npc
 * @author David Bao
 * @author Armanya Dalmia
 * @author Feng Guo
 * @author Victor Lin
 * @author Arjun Pillai
 */

package Entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

abstract class Character extends Entity {
    private int baseHealth;
    private int baseSpeed;
    private int baseDefense;
    private int baseDexterity;
    private String name;

    public Character(Image sprite, int length, int width, int maxHealth, int speed, int defense, int dexterity, String name) {
        super(sprite, length, width);
        this.baseHealth = maxHealth;
        this.baseSpeed = speed;
        this.baseDefense = defense;
        this.baseDexterity = dexterity;
        this.name = name;
    }

    /**
     * getBaseHealth
     * @return int the base health
     */
    public int getBaseHealth() {
        return baseHealth;
    }

    /**
     * getBaseSpeed
     * @return int the base speed
     */
    public int getBaseSpeed() {
        return baseSpeed;
    }

    /**
     * getBaseDefense
     * @return int the base defense
     */
    public int getBaseDefense() {
        return baseDefense;
    }

    /**
     * getBaseDexterity
     * @return int the base dexterity
     */
    public int getBaseDexterity() {
        return baseDexterity;
    }

    /**
     * getName
     * @return String the name of the character
     */
    public String getName() {
        return name;
    }
}
