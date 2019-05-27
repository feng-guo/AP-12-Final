package Entities;

public class EnemyHandler extends CharacterHandler {
    private EnemyInstance enemyInstance;

    public EnemyHandler(EnemyInstance enemyInstance) {
        super(enemyInstance);
        this.enemyInstance = enemyInstance;
    }

    public EnemyInstance getEnemyInstance() {
        return enemyInstance;
    }

    @Override
    public void move() {
        //Code here
    }

    public void attack() {
        //Code here
    }
}
