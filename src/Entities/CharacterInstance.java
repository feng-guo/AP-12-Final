package Entities;

public abstract class CharacterInstance extends EntityInstance {
    private int maxHealth;
    private int currentHealth;
    private int speed;
    private int defense;
    private int dexterity;

    public CharacterInstance(int x, int y, Character character) {
        super(x,y,character);
        this.maxHealth = character.getBaseHealth();
        this.currentHealth = character.getBaseHealth();
        this.speed = character.getBaseSpeed();
        this.defense = character.getBaseDefense();
        this.dexterity = character.getBaseDexterity();
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDefense() {
        return defense;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void damage(int damage) {
        int damageDealt = damage - defense;
        if (damageDealt <= 0) {
            damageDealt = 1;
        }
        currentHealth -= damageDealt;
        if (currentHealth < 0) {
            //rip
        }
    }

    public void heal(int healAmount) {
        if (healAmount + currentHealth > maxHealth) {
            currentHealth = maxHealth;
        } else {
            currentHealth += healAmount;
        }
    }
}
