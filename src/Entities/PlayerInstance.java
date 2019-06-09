package Entities;

import Items.Inventory;

public class PlayerInstance extends CharacterInstance {
    private Inventory inventory;
    private ItemDropInstance nearbyItem;

    public PlayerInstance(int x, int y, Player player, double id) {
        super(x,y, player, id);
        inventory = new Inventory(36);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public ItemDropInstance getNearbyItem() {
        return nearbyItem;
    }

    public void setNearbyItem(ItemDropInstance nearbyItem) {
        this.nearbyItem = nearbyItem;
    }
}
