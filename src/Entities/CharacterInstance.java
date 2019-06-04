package Entities;

public abstract class CharacterInstance extends EntityInstance {
    private int maxHealth;
    private int currentHealth;
    private int speed;
    private int defense;
    private int dexterity;
    private int direction;
    private boolean moving;

    public CharacterInstance(int x, int y, Character character) {
        super(x,y,character);
        this.maxHealth = character.getBaseHealth();
        this.currentHealth = character.getBaseHealth();
        this.speed = character.getBaseSpeed();
        this.defense = character.getBaseDefense();
        this.dexterity = character.getBaseDexterity();
        this.moving = false;
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

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
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

    public void move() {
        if (this.moving) {
            int x = (int) (Math.cos(direction));
            int y = 1 - x;
            super.moveX(this.speed * x);
            super.moveY(this.speed * y);
        }
    }
}
