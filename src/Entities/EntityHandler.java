/**
 * Abstract class for handling the thread of the specific entity instance
 * @author David Bao
 * @author Armanya Dalmia
 * @author Feng Guo
 * @author Victor Lin
 * @author Arjun Pillai
 */

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

    /**
     * getEntityInstance
     * @return EntityInstance the specific entity that is being handled by this
     */
    public EntityInstance getEntityInstance() {
        return entity;
    }

    /**
     * getLocation
     * @return Location return the location of the entity
     */
    public Location getLocation() { return location; }

    /**
     * getLocationHandler
     * @return LocationHandler return the location handler of the entity
     */
    public LocationHandler getLocationHandler() {
        return locationHandler;
    }


    //remove
    public abstract void move();
}
