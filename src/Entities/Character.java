package Entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

abstract class Character extends Entity {
    private int direction;
    private BufferedImage[] spritesDown;
    private BufferedImage[] spritesUp;
    private BufferedImage[] spritesRight;
    private BufferedImage[] spritesLeft;
    private int baseHealth;
    private int baseSpeed;
    private int baseDefense;
    private int baseDexterity;
    private String name;
    private int currentSprite;

    public Character(Image sprite, int direction, int length, int width, int maxHealth, int speed, int defense, int dexterity, String name) {
        super(sprite, length, width);
        loadSprites();
        currentSprite = 0;
        this.direction = direction;
        this.baseHealth = maxHealth;
        this.baseSpeed = speed;
        this.baseDefense = defense;
        this.baseDexterity = dexterity;
        this.name = name;
    }

    public void loadSprites() {
        try {
            BufferedImage sheet = ImageIO.read(new File("Zombie Spritesheet.png"));
            final int width = 50;
            final int height = 100;
            final int cols = 4;
            spritesDown = new BufferedImage[cols];
            spritesUp = new BufferedImage[cols];
            spritesRight = new BufferedImage[cols];
            spritesLeft = new BufferedImage[cols];
            for (int j = 0; j < cols; j++) {
                spritesDown[j] = sheet.getSubimage(j * width, 0 * height, width, height);
                spritesUp[j] = sheet.getSubimage(j * width, height, width, height);
                spritesRight[j] = sheet.getSubimage(j * width, 2 * height, width, height);
                spritesLeft[j] = sheet.getSubimage(j * width, 3 * height, width, height);
            }
        } catch(Exception e) { System.out.println("error loading sheet");};
    }

    public void drawDown(Graphics g) {
        g.drawImage(spritesDown[currentSprite],500,500,null);
    }
    public void drawUp(Graphics g) {
        g.drawImage(spritesUp[currentSprite],500,500,null);
    }
    public void drawRight(Graphics g) {
        g.drawImage(spritesRight[currentSprite],500,500,null);
    }
    public void drawLeft(Graphics g) {
        g.drawImage(spritesLeft[currentSprite],500,500,null);
    }

    public void move() {
        currentSprite++;
        if (currentSprite >= 4)
            currentSprite = 0;
    }

    public void setDirection(char direction){
        this.direction = direction;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public int getBaseSpeed() {
        return baseSpeed;
    }

    public int getBaseDefense() {
        return baseDefense;
    }

    public int getBaseDexterity() {
        return baseDexterity;
    }

    public String getName() {
        return name;
    }
}
