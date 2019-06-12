package Entities;

import Items.Inventory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class PlayerInstance extends CharacterInstance {
    private int direction;
    private BufferedImage[] spritesDown;
    private BufferedImage[] spritesUp;
    private BufferedImage[] spritesRight;
    private BufferedImage[] spritesLeft;
    private Inventory inventory;
    private ItemDropInstance nearbyItem;
    private int currentSprite;
    private int currentHunger;



    public PlayerInstance(int x, int y, Player player, double id) {
        super(x,y, player, id);
        loadSprites();
        this.direction = 0;
        currentSprite = 0;
        this.inventory = new Inventory(36);
        this.currentHunger = 10;
    }

    public void loadSprites() {
        try {
            BufferedImage sheet = ImageIO.read(new File("PlayerPose.png"));
            final int width = 42;
            final int height = 62;
            final int cols = 4;

            spritesDown = new BufferedImage[cols];
            spritesUp = new BufferedImage[cols];
            spritesRight = new BufferedImage[cols];
            spritesLeft = new BufferedImage[cols];

            for (int j = 0; j < cols; j++) {
                spritesDown[j] = sheet.getSubimage(j * width, 0 * height, width, height);
                spritesUp[j] = sheet.getSubimage(j * width, 3 * height, width, height);
                spritesRight[j] = sheet.getSubimage(j * width, 2 * height, width, height);
                spritesLeft[j] = sheet.getSubimage(j * width, 1 * height, width, height);
            }

        }catch(Exception e) { System.out.println(e.getMessage());};
    }
    public Inventory getInventory() {
        return inventory;
    }

    public ItemDropInstance getNearbyItem() {
        return nearbyItem;
    }

    public void setNearbyItem(ItemDropInstance nearbyItem) {
        this.nearbyItem = nearbyItem;
    }

    public BufferedImage drawDown() {
        return spritesDown[currentSprite];
    }
    public BufferedImage drawUp() {
        return spritesUp[currentSprite];
    }
    public BufferedImage drawRight() {
        return spritesRight[currentSprite];
    }
    public BufferedImage drawLeft() {
        return spritesLeft[currentSprite];
    }

    public void move() {
        currentSprite++;
        if (currentSprite >= 4)
            currentSprite = 0;
    }

    public void setDirection(int direction){
        this.direction = direction;
    }

    public int getDirection(){
        return direction;
    }

    public int getCurrentHunger() {
        return currentHunger;
    }

    public void setCurrentHunger(int currentHunger) {
        this.currentHunger = currentHunger;
    }

    public void changeCurrentHunger(int c) {
        if (currentHunger + c > 10) {
            this.currentHunger = 10;
        } else {
            this.currentHunger += c;
        }
    }
}
