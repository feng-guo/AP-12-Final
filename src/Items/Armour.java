package Items;

import java.awt.*;

public class Armour extends Equipable {
    private int armourPoints;
    private String location;
    private int health;
    private int speed;
    private int dexterity;


    public Armour(String name, String description, Image sprite, int durability, int armourPoints, String location) {
        super(name, description, sprite, durability);
        this.armourPoints = armourPoints;
        this.location = location;
    }

    public Armour(String name, String description, Image sprite, int durability, int armourPoints, String location, int health, int speed, int dexterity) {
        super(name, description, sprite, durability);
        this.armourPoints = armourPoints;
        this.location = location;
        this.health = health;
        this.speed = speed;
        this.dexterity = dexterity;
    }

    public int getArmourPoints() {
        return armourPoints;
    }

    public String getLocation() {
        return location;
    }

    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDexterity() {
        return dexterity;
    }
}
