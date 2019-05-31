package Entities;

import java.awt.*;

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

    public int getBaseHealth() {
        return baseHealth;
    }

    public int getBaseSpeed() {
        return baseSpeed;
    }

    public int getBaseDefense() {
        return baseDefense;
    }

    public int getBaseDexterity() {
        return baseDexterity;
    }

    public String getName() {
        return name;
    }
}
