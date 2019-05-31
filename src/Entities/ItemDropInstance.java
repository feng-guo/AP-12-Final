package Entities;

import Items.Stack;

public class ItemDropInstance extends EntityInstance {
    private ItemDrop itemDrop;

    public ItemDropInstance(int x, int y, ItemDrop itemDrop) {
        super(x, y, itemDrop);
        this.itemDrop = itemDrop;
    }

    public Stack getStack() {
        return itemDrop.getStack();
    }
}
