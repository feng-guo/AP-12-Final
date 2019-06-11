package Items;

import java.awt.Image;

abstract class Equipable extends Item {
    private int durability;

    public Equipable(String name, String description, Image sprite, int durability){
        super(name, description, 1, sprite);
        this.durability = durability;
    }

    public int getDurability(){
        return durability;
    }
}
