package Entities;

import Items.Item;
import Items.Stack;

import java.awt.Image;
import java.util.HashMap;

public class NPC extends Character{
    private String defaultDialogue;
    private String title;
    private HashMap<Item, Stack> trades;
    private int walkingRange;

    public NPC(Image sprite, int length, int width, int maxHealth, int speed, int defense, int dexterity, String name, String defaultDialogue, String title, HashMap<Item, Stack> trades, int walkingRange) {
        super(sprite, 0, length, width, maxHealth, speed, defense, dexterity, name);
        this.defaultDialogue = defaultDialogue;
        this.title = title;
        this.trades = trades;
        this.walkingRange = walkingRange;
    }

    String getDefaultDialogue() {
        return defaultDialogue;
    }

    HashMap<Item, Stack> getTrades() {
        return trades;
    }

    public String getTitle() {
        return title;
    }

    public int getWalkingRange() {
        return walkingRange;
    }
}
