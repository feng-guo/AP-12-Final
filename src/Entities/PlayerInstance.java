package Entities;

import Items.Inventory;

public class PlayerInstance extends CharacterInstance {
    private Inventory inventory;

    public PlayerInstance(int x, int y, Player player) {
        super(x,y, player);
    }

    public Inventory getInventory() {
        return inventory;
    }
}
