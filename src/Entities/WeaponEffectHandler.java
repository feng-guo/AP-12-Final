package Entities;

public class WeaponEffectHandler extends EntityHandler {
    private int initialX;
    private int initialY;
    private int targetX;
    private int targetY;
    private WeaponEffectInstance weaponEffectInstance;
    private double speed;

    public WeaponEffectHandler(WeaponEffectInstance weaponEffectInstance) {
        super(weaponEffectInstance);
        this.weaponEffectInstance = weaponEffectInstance;
        this.initialX = weaponEffectInstance.getInitialX();
        this.initialY = weaponEffectInstance.getInitialY();
        this.targetX = weaponEffectInstance.getTargetX();
        this.targetY = weaponEffectInstance.getTargetY();
    }

    @Override
    public void move() {

    }

    public WeaponEffectInstance getWeaponEffectInstance() {
        return weaponEffectInstance;
    }
}
