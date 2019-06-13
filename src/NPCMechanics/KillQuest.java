/**
 * Class that defines specific type of quest
 * @author David Bao
 * @author Armanya Dalmia
 * @author Feng Guo
 * @author Victor Lin
 * @author Arjun Pillai
 */
package NPCMechanics;

import Entities.Enemy;
import Items.Item;

public class KillQuest extends Quest{
    private Enemy target;
    private int counter;
    private int targetAmount;

    public KillQuest(Item reward, Enemy target, int counter, int targetAmount) {
        super(reward);
        this.target = target;
        this.counter = counter;
        this.targetAmount = targetAmount;
    }

    public Enemy getTarget() {
        return target;
    }

    public int getCounter() {
        return counter;
    }

    public int getTargetAmount() {
        return targetAmount;
    }

    public void increaseCounter() {
        counter++;
    }
}
