package Items;

import java.awt.*;

abstract class Tool extends Equipable {
    int toolHit;

    public Tool (String name, String description, Image sprite, int durability, int toolHit) {
        super(name, description, sprite, durability);
        this.toolHit = toolHit;
    }

    public int getToolHit() {
        return toolHit;
    }
}
