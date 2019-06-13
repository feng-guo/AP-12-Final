/**
 * Class that defines specific instances of NPCs
 * @author David Bao
 * @author Armanya Dalmia
 * @author Feng Guo
 * @author Victor Lin
 * @author Arjun Pillai
 */
package Entities;

import Items.Item;
import Items.Stack;
import NPCMechanics.Quest;
import NPCMechanics.Trade;

import java.util.ArrayList;

public class NPCInstance extends CharacterInstance {
    private ArrayList<Quest> quests;
    private ArrayList<Trade> trades;
    private NPC character;

    public NPCInstance(int x, int y, NPC character, ArrayList<Quest> quests, double id) {
        super(x, y, character, id);
        this.character = character;
        this.quests = quests;
    }

    public ArrayList<Quest> getQuests() {
        return quests;
    }

    private boolean remove(Quest quest) {
        for (int i=0; i<quests.size(); i++) {
            if (quests.get(i).equals(quest)) {
                quests.remove(i);
                return true;
            }
        }
        return false;
    }

    public Item resolveQuest(Quest quest) {
        if (remove(quest)) {
            return quest.getReward();
        }
        return null;
    }

    public String giveDialog() {
        return character.getDefaultDialogue();
    }

    //Might want to code this properly!!
    public Stack trade(Item item) {
        return character.getTrades().get(item);
    }
}
