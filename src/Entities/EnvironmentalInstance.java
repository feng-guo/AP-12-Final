package Entities;

public class EnvironmentalInstance extends EntityInstance {
    private int currentDurability;

    public EnvironmentalInstance(int x, int y, Environmental environmental) {
        super(x, y, environmental);
        this.currentDurability = environmental.getDurability();
    }

    public int getCurrentDurability() {
        return currentDurability;
    }

    public void hit(int damage) {
        currentDurability -= damage;
    }
}
