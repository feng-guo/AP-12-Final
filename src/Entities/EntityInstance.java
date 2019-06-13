/**
 * abstract class to create a specific instance of an entity
 * @author David Bao
 * @author Armanya Dalmia
 * @author Feng Guo
 * @author Victor Lin
 * @author Arjun Pillai
 */
package Entities;

import World.Clock;

abstract public class EntityInstance {
    private int x;
    private int y;
    private Entity entity;
    private double id;

    public EntityInstance(int x, int y, Entity entity, double id) {
        this.x = x;
        this.y = y;
        this.entity = entity;
        this.id = id;
    }

    /**
     * getEntity
     * @return Entity the type of entity
     */
    public Entity getEntity() {
        return entity;
    }

    /**
     * getX
     * @return int the x position of the top left of the entity
     */
    public int getX() {
        return x;
    }

    /**
     * getY
     * @return int the y position of the top left of the entity
     */
    public int getY() {
        return y;
    }

    /**
     * setX
     * @param x value to move entity's x position to
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * setY
     * @param y value to move entity's y position to
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * moveX
     * @param x how much to increment the x position
     */
    public void moveX(int x) {
        this.x += x;
    }

    /**
     * moveY
     * @param y how much to increment the y position
     */
    public void moveY(int y) {
        this.y += y;
    }

    /**
     * getID
     * @return double the unique identifier of the entity
     */
    public double getID() {
        return id;
    }
}
