package Entities;

import World.Location;
import World.LocationHandler;

public abstract class EntityHandler implements Runnable{
    private EntityInstance entity;
    private Location location;
    private LocationHandler locationHandler;

    public EntityHandler(EntityInstance entity, LocationHandler locationHandler) {
        this.entity = entity;
        this.location = locationHandler.getLocation();
        this.locationHandler = locationHandler;
    }

    public EntityInstance getEntityInstance() {
        return entity;
    }

    public Location getLocation() { return location; }

    public LocationHandler getLocationHandler() {
        return locationHandler;
    }

    public abstract void move();
}
