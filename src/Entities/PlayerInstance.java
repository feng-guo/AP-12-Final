/**
 * class for a specific instance of the player
 * @author David Bao
 * @author Armanya Dalmia
 * @author Feng Guo
 * @author Victor Lin
 * @author Arjun Pillai
 */
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
    private Player player;
    private int currentSprite;
    private int currentHunger;
    private int lastDirection;

    public PlayerInstance(int x, int y, Player player, double id) {
        super(x,y, player, id);
        loadSprites();
        lastDirection = 0;
        this.direction = 0;
        currentSprite = 0;
        this.inventory = new Inventory(36);
        this.player=player;
        this.currentHunger = 10;
        this.lastWeaponUse = 0;
        this.lastConsumableUse = 0;
        this.lastHungerChange = System.nanoTime()/1e+9;
        this.lastStarveChange = 0;
        this.lastMove = 0;
    }

    /**
     * loadSprites
     * loads the sprites for the player from the spritesheet
     */
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

    /**
     * getInventory
     * @return Inventory the inventory of the player
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * getPlayer
     * @return Player the player of this instance
     */
    public Player getPlayer(){
        return this.player;
    }

    /**
     * getNearbyItem
     * @return ItemDropInstance the nearbyItem
     */
    public ItemDropInstance getNearbyItem() {
        return nearbyItem;
    }

    /**
     * setNearbyItem
     * @param nearbyItem set the nearbyItem
     */
    public void setNearbyItem(ItemDropInstance nearbyItem) {
        this.nearbyItem = nearbyItem;
    }

    /**
     * drawDown
     * @return the image of the character facing down
     */
    public BufferedImage drawDown() {
        lastDirection = 0;
        return spritesDown[currentSprite];
    }

    /**
     * drawUp
     * @return the image of the character facing up
     */
    public BufferedImage drawUp() {
        lastDirection = 1;
        return spritesUp[currentSprite];
    }

    /**
     * drawRight
     * @return the image of the character facing right
     */
    public BufferedImage drawRight() {
        lastDirection = 2;
        return spritesRight[currentSprite];
    }

    /**
     * drawLeft
     * @return the image of the character facing left
     */
    public BufferedImage drawLeft() {
        lastDirection = 3;
        return spritesLeft[currentSprite];
    }

    /**
     * drawLast
     * @return the image of the character
     */
    public BufferedImage drawLast() {
        if (lastDirection == 0) {
            return spritesDown[0];
        }else if (lastDirection == 1){
            return spritesUp[0];
        }else if (lastDirection == 2){
            return spritesRight[0];
        }else{
            return spritesLeft[0];
        }
    }

    /**
     * move
     * changes sprite according to steps taken
     */
    public void move() {
        steps++;
        if (steps == 6) {
            steps = 0;
            currentSprite++;
            if (currentSprite >= 4)
                currentSprite = 0;
        }

    }

    /**
     * setDirection
     * @param direction the direction faced
     */
    public void setDirection(int direction){
        this.direction = direction;
    }

    /**
     * getDirection
     * @return int the direction faced
     */
    public int getDirection(){
        return direction;
    }

    /**
     * getCurrentHunger
     * @return int current hunger level
     */
    public int getCurrentHunger() {
        return currentHunger;
    }

    /**
     * setCurrentHunger
     * @param currentHunger set current hunger level
     */
    public void setCurrentHunger(int currentHunger) {
        this.currentHunger = currentHunger;
    }

    /**
     * changeCurrentHunger
     * @param c increments the hunger level by this
     */
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

    /**
     * getLastWeaponUse
     * @return double the last time the weapon was used
     */
    public double getLastWeaponUse() {
        return lastWeaponUse;
    }

    /**
     * setLastWeaponUse
     * @param lastWeaponUse when the weapon was last used
     */
    public void setLastWeaponUse(double lastWeaponUse) {
        this.lastWeaponUse = lastWeaponUse;
    }

    /**
     * getLastConsumableUse
     * @return double the last time the consumable was used
     */
    public double getLastConsumableUse() {
        return lastConsumableUse;
    }

    /**
     * setLastConsumableUse
     * @param lastConsumableUse when the consumable was last used
     */
    public void setLastConsumableUse(double lastConsumableUse) {
        this.lastConsumableUse = lastConsumableUse;
    }

    /**
     * getLastHungerChange
     * @return double the prior hunger change
     */
    public double getLastHungerChange() {
        return lastHungerChange;
    }

    /**
     * setLastHungerChange
     * @param lastHungerChange the value to set hunger change to
     */
    public void setLastHungerChange(double lastHungerChange) {
        this.lastHungerChange = lastHungerChange;
    }


    /**
     * getLastStarveChange
     * @return double the prior starve change
     */
    public double getLastStarveChange() {
        return lastStarveChange;
    }

    /**
     * setLastStarveChange
     * @param lastStarveChange the value to set starve change to
     */
    public void setLastStarveChange(double lastStarveChange) {
        this.lastStarveChange = lastStarveChange;
    }

}
