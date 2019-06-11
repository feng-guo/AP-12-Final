package Items;

import java.awt.Image;

public abstract class Consumable extends Item{
    private int healthGain;

    public Consumable(String name, String description, int maxStack, Image sprite, int healthGain) {
        super(name, description, maxStack, sprite);
        this.healthGain = healthGain;
    }

    public abstract void use();
}
