package Entities;

import Items.Inventory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class PlayerInstance extends CharacterInstance {
    private int direction;
    private int steps = 0;
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
        this.lastWeaponUse = 0;
        this.lastConsumableUse = 0;
        this.lastHungerChange = System.nanoTime()/1e+9;
        this.lastStarveChange = 0;
        this.lastMove = 0;
    }

    public void loadSprites() {
        try {
            BufferedImage sheet = ImageIO.read(new File("assets/player/PlayerPose.png"));
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
        steps++;
        if (steps == 4) {
            steps = 0;
            currentSprite++;
            if (currentSprite >= 4)
                currentSprite = 0;
        }
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
        if (c < 0 && currentHunger <= 0) {
            currentHunger = 0;
        } else if (currentHunger + c > 10) {
            this.currentHunger = 10;
        } else {
            this.currentHunger += c;
        }
    }

    private double lastWeaponUse;
    private double lastConsumableUse;
    private double lastHungerChange;
    private double lastStarveChange;
    private double lastMove;

    public double getLastWeaponUse() {
        return lastWeaponUse;
    }

    public void setLastWeaponUse(double lastWeaponUse) {
        this.lastWeaponUse = lastWeaponUse;
    }

    public double getLastConsumableUse() {
        return lastConsumableUse;
    }

    public void setLastConsumableUse(double lastConsumableUse) {
        this.lastConsumableUse = lastConsumableUse;
    }

    public double getLastHungerChange() {
        return lastHungerChange;
    }

    public void setLastHungerChange(double lastHungerChange) {
        this.lastHungerChange = lastHungerChange;
    }

    public double getLastStarveChange() {
        return lastStarveChange;
    }

    public void setLastStarveChange(double lastStarveChange) {
        this.lastStarveChange = lastStarveChange;
    }

}
