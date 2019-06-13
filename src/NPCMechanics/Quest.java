/**
 * Class that defines general specifications for quests
 * @author David Bao
 * @author Armanya Dalmia
 * @author Feng Guo
 * @author Victor Lin
 * @author Arjun Pillai
 */
package NPCMechanics;

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


//temp code for implementation in main game 
//ArrayList<Quest> questList = new ArrayList<Quest>();
//questList.add(new KillQuest(sword,appleTrees,0,10));// template, change reward and figure out a the actual enemy
  //if killed.Name== target 
