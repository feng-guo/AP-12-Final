package Entities;

import World.Location;

public class WeaponEffectHandler extends EntityHandler {
    private int initialX;
    private int initialY;
    private int targetX;
    private int targetY;
    private WeaponEffectInstance weaponEffectInstance;
    private double speed;

    public WeaponEffectHandler(WeaponEffectInstance weaponEffectInstance, Location location) {
        super(weaponEffectInstance, location);
        this.weaponEffectInstance = weaponEffectInstance;
        this.initialX = weaponEffectInstance.getInitialX();
        this.initialY = weaponEffectInstance.getInitialY();
        this.targetX = weaponEffectInstance.getTargetX();
        this.targetY = weaponEffectInstance.getTargetY();
    }

    @Override
    public void move() {

    }

    @Override
    public void run() {

    }

    public WeaponEffectInstance getWeaponEffectInstance() {
        return weaponEffectInstance;
    }
}
