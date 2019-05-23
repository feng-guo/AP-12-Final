package Items;

import java.awt.Image;

abstract class Consumable extends Item{
    private int healthGain;

    public Consumable(String name, String description, Image sprite, int healthGain) {
        super(name, description, 1, sprite);
        this.healthGain = healthGain;
    }

    abstract void use();
}
