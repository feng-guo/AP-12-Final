package Entities;

import Items.Stack;

import java.awt.*;
import java.util.HashMap;

public class Enemy extends Character {
    private String species;
    private HashMap<Double, Stack> lootTable;

    public Enemy(Image sprite, int length, int width, int maxHealth, int speed, int defense, int dexterity, String name, String species, HashMap<Double, Stack> lootTable) {
        super(sprite, length, width, maxHealth, speed, defense, dexterity, name);
        this.species = species;
        this.lootTable = lootTable;
    }

    public String getSpecies() {
        return species;
    }

    public HashMap<Double, Stack> getLootTable() {
        return lootTable;
    }

}
