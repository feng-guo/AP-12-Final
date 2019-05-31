package Entities;

abstract public class EntityInstance {
    private int x;
    private int y;
    private Entity entity;

    public EntityInstance(int x, int y, Entity entity) {
        this.x = x;
        this.y = y;
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void moveX(int x) {
        this.x += x;
    }

    public void moveY(int y) {
        this.y += y;
    }
}
