package Entities;

import Items.Weapon;

public class EnemyInstance extends CharacterInstance {
    private Weapon weapon;

    public EnemyInstance(int x, int y, Character character, Weapon weapon) {
        super(x, y, character);
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
