package Entities;

import Items.Stack;

import java.awt.*;
import java.util.HashMap;

public class Enemy extends Character {
    private String species;
    private HashMap<Stack, Double> lootTable;

    public Enemy(Image sprite, int length, int width, int maxHealth, int speed, int defense, int dexterity, String name, String species, HashMap<Stack, Double> lootTable) {
        super(sprite, length, width, maxHealth, speed, defense, dexterity, name);
        this.species = species;
        this.lootTable = lootTable;
    }

    public String getSpecies() {
        return species;
    }

    public HashMap<Stack, Double> getLootTable() {
        return lootTable;
    }
}
