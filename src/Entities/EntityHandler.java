package Entities;

import World.Location;

public abstract class EntityHandler implements Runnable{
    private EntityInstance entity;
    private Location location;

    public EntityHandler(EntityInstance entity, Location location) {
        this.entity = entity;
        this.location = location;
    }

    public EntityInstance getEntityInstance() {
        return entity;
    }

    public Location getLocation() { return location; }

    public abstract void move();
}
