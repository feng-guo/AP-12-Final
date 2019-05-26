package Items;

import java.awt.Image;

public abstract class Item {
    private String name;
    private String description;
    private int maxStack;
    private Image sprite; //Might not use Image!

    public Item(String name, String description, int maxStack, Image sprite) {
        this.name = name;
        this.description = description;
        this.maxStack = maxStack;
        this.sprite = sprite;
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
