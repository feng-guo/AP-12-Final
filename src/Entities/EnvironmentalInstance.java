package Entities;

public class EnvironmentalInstance extends EntityInstance {
    private int currentDurability;
    private Environmental environmental;

    public EnvironmentalInstance(int x, int y, Environmental environmental, double id) {
        super(x, y, environmental, id);
        this.currentDurability = environmental.getDurability();
        this.environmental = environmental;
    }

    public int getCurrentDurability() {
        return currentDurability;
    }

    public void hit(int damage) {
        currentDurability -= damage;
    }

    public Environmental getEnvironmental() {
        return environmental;
    }
}
