package Entities;

import java.util.ArrayList;

public class EnvironmentalInstance extends EntityInstance {
    private int currentDurability;
    private Environmental environmental;
    private ArrayList<Integer> hits;
    private ArrayList<Double> hitTime;

    public EnvironmentalInstance(int x, int y, Environmental environmental, double id) {
        super(x, y, environmental, id);
        this.currentDurability = environmental.getDurability();
        this.environmental = environmental;
        this.hits = new ArrayList<>();
        this.hitTime = new ArrayList<>();
    }

    public int getCurrentDurability() {
        return currentDurability;
    }

    public void hit(int damage) {
        currentDurability -= damage;
        hits.add(damage);
        hitTime.add(System.nanoTime()/1e+9);
    }

    public Environmental getEnvironmental() {
        return environmental;
    }

    public int getHit(int i) {
        return hits.get(i);
    }

    public double getHitTime(int i) {
        return hitTime.get(i);
    }

    public void removeHit(int i) {
        hits.remove(i);
        hitTime.remove(i);
    }

    public int hitSize() {
        return hits.size();
    }
}
