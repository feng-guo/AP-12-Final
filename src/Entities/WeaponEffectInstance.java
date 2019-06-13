/**
 * Class that defines specific instances of weapon effects
 * @author David Bao
 * @author Armanya Dalmia
 * @author Feng Guo
 * @author Victor Lin
 * @author Arjun Pillai
 */
package Entities;

public class WeaponEffectInstance extends EntityInstance {
    private WeaponEffect weaponEffect;
    private double timeCreated;
    private int initialX;
    private int initialY;
    private int targetX;
    private int targetY;

    public WeaponEffectInstance(int x, int y, WeaponEffect weaponEffect, double timeCreated, int targetX, int targetY, double id) {
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

    public double getTimeCreated() {
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
