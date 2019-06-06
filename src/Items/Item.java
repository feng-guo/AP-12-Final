package Items;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Item {
    private String name;
    private String description;
    private int maxStack;
    private BufferedImage sprite; //Might not use Image!

    public Item(String name, String description, int maxStack, Image sprite) {
        this.name = name;
        this.description = description;
        this.maxStack = maxStack;
        sprite = new ImageIcon(sprite).getImage();
        this.sprite = new BufferedImage(sprite.getWidth(null), sprite.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics g = this.sprite.createGraphics();
        g.drawImage(sprite, 0,0, null);
        g.dispose();
    }

    public Item(String name, int maxStack){
        this.name = name;
        this.maxStack = maxStack;
        this.sprite = null; //Will implement later
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getMaxStack(){
        return maxStack;
    }

    public Image getSprite() {
        return sprite;
    }
}
