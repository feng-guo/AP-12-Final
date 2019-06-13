/**
 * Abstract class for entity objects
 * @author David Bao
 * @author Armanya Dalmia
 * @author Feng Guo
 * @author Victor Lin
 * @author Arjun Pillai
 */

package Entities;

import java.awt.*;

public abstract class Entity {
    private Image sprite;
    private int length;
    private int width;

    public Entity(Image sprite, int length, int width) {
        this.sprite = sprite;
        this.length = length;
        this.width = width;
    }

    /**
     * getLength
     * @return int length of the entity
     */
    public int getLength() {
        return length;
    }

    /**
     * getWidth
     * @return int width of the entity
     */
    public int getWidth() {
        return width;
    }

    /**
     * getSprite
     * @return Image sprite of the entity
     */
    public Image getSprite() {
        return sprite;
    }

    /**
     * setLength
     * @param length value to set length to
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * setWidth
     * @param width value to set width to
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * setSprite
     * @param sprite set image for entity
     */
    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

}
