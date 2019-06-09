package Entities;

public class EnvironmentalInstance extends EntityInstance {
    private int currentDurability;

    public EnvironmentalInstance(int x, int y, Environmental environmental, double id) {
        super(x, y, environmental, id);
        this.currentDurability = environmental.getDurability();
    }

    public int getCurrentDurability() {
        return currentDurability;
    }

    public void hit(int damage) {
        currentDurability -= damage;
    }
}
