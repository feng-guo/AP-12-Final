/**
 * Class that defines specific type of quest
 * @author David Bao
 * @author Armanya Dalmia
 * @author Feng Guo
 * @author Victor Lin
 * @author Arjun Pillai
 */
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
