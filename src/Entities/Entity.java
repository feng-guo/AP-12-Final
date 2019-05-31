package Entities;

import java.awt.*;

abstract class Entity {
    private Image sprite;
    private int length;
    private int width;

    public Entity(Image sprite, int length, int width) {
        this.sprite = sprite;
        this.length = length;
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public Image getSprite() {
        return sprite;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

}
