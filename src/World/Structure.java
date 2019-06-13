/**
 * Structure
 * class for the in game buildings
 */
package World;

import java.awt.*;

public class Structure {
    private Image image;
    private Location location; //This location is the INTERIOR
    private String name;
    private int x;
    private int y;
    private int height;
    private int width;

    public Structure(Image image, Location location, String name, int x, int y, int height, int width) {
        this.image = image;
        this.location = location;
        this.name = name;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public Image getImage() {
        return image;
    }

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
