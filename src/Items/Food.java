package Items;

import java.awt.*;

class Food extends Consumable {
    private int restorationValue;

    public Food(String name, String description, Image sprite, int healthGain, int restorationValue) {
        super(name, description, sprite, healthGain);
        this.restorationValue = restorationValue;
    }

    public void use(){
        //Figure out later
    }

    public int getRestorationValue() {
        return restorationValue;
    }
}
