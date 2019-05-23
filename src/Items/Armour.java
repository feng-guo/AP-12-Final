package Items;

import java.awt.*;

public class Armour extends Equipable {
    private int armourPoints;
    private String location;

    public Armour(String name, String description, Image sprite, int durability, int armourPoints, String location) {
        super(name, description, sprite, durability);
        this.armourPoints = armourPoints;
        this.location = location;
    }

    public int getArmourPoints() {
        return armourPoints;
    }

    public String getLocation() {
        return location;
    }
}
