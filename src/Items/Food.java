package Items;

import Entities.PlayerInstance;

import java.awt.*;

public class Food extends Consumable {
    private int restorationValue;

    public Food(String name, String description, Image sprite, int healthGain, int restorationValue) {
        super(name, description, 64, sprite, healthGain);
        this.restorationValue = restorationValue;
    }

    public void use(PlayerInstance player){
        player.changeCurrentHunger(restorationValue);
        player.heal(super.getHealthGain());
    }

    public int getRestorationValue() {
        return restorationValue;
    }
}
