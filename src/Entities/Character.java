package Entities;

import java.awt.*;

abstract class Character extends Entity {
    private int maxHealth;
    private int currentHealth;
    private int speed;
    private int defense;
    private int dexterity;
    private String name;

    public Character(Image sprite, int x, int y, int length, int width, int maxHealth, int speed, int defense, int dexterity, String name) {
        super(sprite, x, y, length, width);
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.speed = speed;
        this.defense = defense;
        this.dexterity = dexterity;
        this.name = name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDefense() {
        return defense;
    }

    public int getDexterity() {
        return dexterity;
    }

    public String getName() {
        return name;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void damage(int damage) {
        int damageDealt = damage - defense;
        if (damageDealt <= 0) {
            damageDealt = 1;
        }
        currentHealth -= damageDealt;
        if (currentHealth < 0) {
            //rip
        }
    }

    public void heal(int healAmount) {
        if (healAmount + currentHealth > maxHealth) {
            currentHealth = maxHealth;
        } else {
            currentHealth += healAmount;
        }
    }
}
