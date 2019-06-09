package Entities;

public class WeaponEffectInstance extends EntityInstance {
    private WeaponEffect weaponEffect;
    private int timeCreated;
    private int initialX;
    private int initialY;
    private int targetX;
    private int targetY;

    public WeaponEffectInstance(int x, int y, WeaponEffect weaponEffect, int timeCreated, int targetX, int targetY, double id) {
        super(x, y, weaponEffect, id);
        this.weaponEffect = weaponEffect;
        this.timeCreated = timeCreated;
        this.initialX = x;
        this.initialY = y;
        this.targetX = targetX;
        this.targetY = targetY;

    }

    public WeaponEffect getWeaponEffect() {
        return weaponEffect;
    }

    public int getTimeCreated() {
        return timeCreated;
    }

    public int getInitialX() {
        return initialX;
    }

    public int getInitialY() {
        return initialY;
    }

    public int getTargetX() {
        return targetX;
    }

    public int getTargetY() {
        return targetY;
    }
}
