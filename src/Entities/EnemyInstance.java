package Entities;

import Items.Weapon;

public class EnemyInstance extends CharacterInstance {
    private Weapon weapon;

    public EnemyInstance(int x, int y, Enemy character, Weapon weapon, double id) {
        super(x, y, character, id);
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
