/**
 * Class that defines instances of item drops
 * @author David Bao
 * @author Armanya Dalmia
 * @author Feng Guo
 * @author Victor Lin
 * @author Arjun Pillai
 */
package Entities;

import Items.Stack;

public class ItemDropInstance extends EntityInstance {
    private ItemDrop itemDrop;

    public ItemDropInstance(int x, int y, ItemDrop itemDrop, double id) {
        super(x, y, itemDrop, id);
        this.itemDrop = itemDrop;
    }

    public Stack getStack() {
        return itemDrop.getStack();
    }

    public ItemDrop getItemDrop() {
        return itemDrop;
    }
}
