package Entities;

public abstract class EntityHandler {
    private EntityInstance entity;

    public EntityHandler(EntityInstance entity) {
        this.entity = entity;
    }

    public EntityInstance getEntity() {
        return entity;
    }

    public abstract void move();
}
