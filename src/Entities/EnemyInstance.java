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

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Enemy getEnemy() {
        return enemy;
    }
}
