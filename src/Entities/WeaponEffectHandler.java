package Entities;

import World.LocationHandler;

public class WeaponEffectHandler extends EntityHandler {
    private int initialX;
    private int initialY;
    private int targetX;
    private int targetY;
    private WeaponEffectInstance weaponEffectInstance;
    private double speed;

    public WeaponEffectHandler(WeaponEffectInstance weaponEffectInstance, LocationHandler location) {
        super(weaponEffectInstance, location);
        this.weaponEffectInstance = weaponEffectInstance;
        this.initialX = weaponEffectInstance.getInitialX();
        this.initialY = weaponEffectInstance.getInitialY();
        this.targetX = weaponEffectInstance.getTargetX();
        this.targetY = weaponEffectInstance.getTargetY();
    }

    @Override
    public void move() {
        double deltaX=targetX-initialX;
        double deltaY=targetY-initialY;
        double hypotenuse=Math.sqrt(deltaX*deltaX+deltaY*deltaY);

        //change per tick for x is cosine and for y is sine
        deltaX /= hypotenuse;
        deltaY /= hypotenuse;

        /*
        deltaX*=speed;
        deltaY*=speed;
         */
        weaponEffectInstance.moveX((int)deltaX);
        weaponEffectInstance.moveY((int)deltaY);

    }

    @Override
    public void run() {

    }

    public WeaponEffectInstance getWeaponEffectInstance() {
        return weaponEffectInstance;
    }
}
