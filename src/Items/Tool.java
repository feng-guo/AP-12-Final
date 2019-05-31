package Items;

import java.awt.*;

public class Tool extends Weapon {
    private int toolHit;

    public Tool (String name, String description, Image sprite, int durability, int damage, int range, int toolHit) {
        super(name, description, sprite, durability, damage, range);
        this.toolHit = toolHit;
    }

    public int getToolHit() {
        return toolHit;
    }
}
