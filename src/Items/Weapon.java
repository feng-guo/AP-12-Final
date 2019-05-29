package Items;

import java.awt.*;

public class Weapon extends Equipable {
    private int damage;
    private int range;

    public Weapon(String name, String description, Image sprite, int durability, int damage, int range){
        super(name, description, sprite, durability);
        this.damage = damage;
        this.range = range;
    }

    public int getDamage(){
        return damage;
    }

    public int getRange() {
        return range;
    }
}
