/**
 * abstract class for a specific instance of a character
 * @author David Bao
 * @author Armanya Dalmia
 * @author Feng Guo
 * @author Victor Lin
 * @author Arjun Pillai
 */

package Entities;

public abstract class CharacterInstance extends EntityInstance {
    private int maxHealth;
    private int currentHealth;
    private int speed;
    private int defense;
    private int dexterity;

    public CharacterInstance(int x, int y, Character character, double id) {
        super(x,y,character,id);
        this.maxHealth = character.getBaseHealth();
        this.currentHealth = character.getBaseHealth();
        this.speed = character.getBaseSpeed();
        this.defense = character.getBaseDefense();
        this.dexterity = character.getBaseDexterity();
    }

    /**
     * getMaxHealth
     * @return int the max health
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * getCurrentHealth
     * @return int the current health
     */
    public int getCurrentHealth() {
        return currentHealth;
    }

    /**
     * getSpeed
     * @return int the speed of the character
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * getDefense
     * @return int the defense of the character
     */
    public int getDefense() {
        return defense;
    }

    /**
     * getDexterity
     * @return int the dexterity of the character
     */
    public int getDexterity() {
        return dexterity;
    }

    /**
     * setMaxHealth
     * @param maxHealth change the max health
     */
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    /**
     * setCurrentHealth
     * @param currentHealth change current health
     */
    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    /**
     * setSpeed
     * @param speed change speed stat
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * setDefense
     * @param defense change def stat
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * setDexterity
     * @param dexterity change dex stat
     */
    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    /**
     * damage
     * Damage is adjusted according to defense
     * @param damage the amount of damage inflicted on the character
     */
    public void damage(int damage) {
        int damageDealt = damage - defense;
        if (damageDealt <= 0) {
            damageDealt = 1;
        }
        currentHealth -= damageDealt;
    }

    /**
     * heal
     * @param healAmount amount to raise health
     */
    public void heal(int healAmount) {
        if (healAmount + currentHealth > maxHealth) {
            currentHealth = maxHealth;
        } else {
            currentHealth += healAmount;
        }
    }
}
