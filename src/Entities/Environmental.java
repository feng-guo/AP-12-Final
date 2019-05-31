package Entities;

import Items.Item;
import Items.Stack;

import java.awt.*;

public class Environmental extends Entity {
    private int durability;
    private Item drop;

    public Environmental(Image sprite, int length, int width, int durability, Item drop) {
        super(sprite, length, width);
        this.durability = durability;
        this.drop = drop;
    }

    public int getDurability() {
        return durability;
    }

    public Stack getDrop() {
        return new Stack(1,drop);
    }
}
