/**
 * class for specific instance of an enemy
 * @author David Bao
 * @author Armanya Dalmia
 * @author Feng Guo
 * @author Victor Lin
 * @author Arjun Pillai
 */
package Entities;

import Items.Weapon;

public class EnemyInstance extends CharacterInstance {
    private Weapon weapon;
    private Enemy enemy;

    public EnemyInstance(int x, int y, Enemy enemy, Weapon weapon, double id) {
        super(x, y, enemy, id);
        this.weapon = weapon;
        this.enemy = enemy;
    }

    /**
     * getWeapon
     * @return Weapon the weapon being used by the enemy
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * setWeapon
     * @param weapon the weapon to set for the enemy
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    /**
     * getEnemy
     * @return Enemy the type of Enemy
     */
    public Enemy getEnemy() {
        return enemy;
    }
}
