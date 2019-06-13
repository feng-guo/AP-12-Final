/**
 * Class that defines the weapon effect
 * @author David Bao
 * @author Armanya Dalmia
 * @author Feng Guo
 * @author Victor Lin
 * @author Arjun Pillai
 */
package Entities;

import Items.Weapon;

import java.awt.Image;

public class WeaponEffect extends Entity {
    private int effectDuration;
    private int damage;


    public WeaponEffect(Image sprite, int length, int width, Weapon weapon) {
        super(sprite, length, width);
        this.effectDuration = weapon.getRange();
        this.damage = weapon.getDamage();
    }

    public int getEffectDuration() {
        return effectDuration;
    }

    public int getDamage() {
        return damage;
    }
}
