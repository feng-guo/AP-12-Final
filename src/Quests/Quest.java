package Quests;

import Items.Item;

public abstract class Quest {
    private Item reward;

    public Quest(Item reward) {
        this.reward = reward;
    }

    public Item getReward() {
        return reward;
    }
}
