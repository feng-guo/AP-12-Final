package Entities;

import java.awt.*;

abstract class Entity {
    private Image sprite;
    private int x;
    private int y;
    private int length;
    private int width;

    public Entity(Image sprite, int x, int y, int length, int width) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
        this.length = length;
        this.width = width;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
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

    public void moveX(int x) {
        this.x += x;
    }

    public void moveY(int y) {
        this.y += y;
    }

}
