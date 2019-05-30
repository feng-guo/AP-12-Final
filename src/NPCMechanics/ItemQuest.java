package NPCMechanics;

import Items.Item;

public class ItemQuest extends Quest {
    private Item requiredItem;

    public ItemQuest(Item reward, Item requiredItem) {
        super(reward);
        this.requiredItem = requiredItem;
    }

    public Item getRequiredItem() {
        return requiredItem;
    }
}
