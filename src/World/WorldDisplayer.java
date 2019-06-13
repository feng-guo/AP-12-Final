package World;

import Entities.EnemyInstance;
import Entities.*;
import Entities.PlayerInstance;
import Items.Consumable;
import Items.Stack;
import Items.Weapon;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.RasterFormatException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class WorldDisplayer extends JPanel implements Runnable {

	boolean running = true;
	PlayerInstance player;
	Location map;
	int[][] mapTile;
	String[][] mapBlocks;
	int size = 64;
	private int mouseClickX;
	private int mouseClickY;
	//ArrayList<Double> enemies;

	private String frameRate;
	private long lastTimeCheck;
	private long deltaTime;
	private int frameCount;

	BufferedImage healthEmpty;
	BufferedImage healthFull;
	BufferedImage hungerFull;
	BufferedImage hotbar;
	BufferedImage hotbarSelect;
	Color darken;

	BufferedImage grass;
	BufferedImage stone;
	BufferedImage water;
	BufferedImage wood;

	private GameMouseWheelListener gameMouseWheelListener;

	public WorldDisplayer(PlayerInstance player, Location map) {
		this.player = player;
		player.loadSprites();
		this.map = map;
		this.mapTile = map.getMap();
		this.mapBlocks = map.getBlocks();

		//move this later
		try {
			grass = ImageIO.read(new File("assets/blocks/grass.png"));
			stone = ImageIO.read(new File("assets/blocks/stone.png"));
			water = ImageIO.read(new File("assets/blocks/water.png"));
			wood = ImageIO.read(new File("assets/blocks/wood.png"));

			healthEmpty = ImageIO.read(new File("assets/gui/health_empty.png"));
			healthFull = ImageIO.read(new File("assets/gui/health_full.png"));
			hungerFull = ImageIO.read(new File("assets/gui/hunger_full.png"));


			hotbar = ImageIO.read(new File("assets/gui/hotbar.png"));
			hotbarSelect = ImageIO.read(new File("assets/gui/selected.png"));

			darken = new Color(0,0,0,127);

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		this.gameMouseWheelListener = new GameMouseWheelListener();
		this.addMouseWheelListener(gameMouseWheelListener);
	}

	public void run() {
		while (running) {
			this.setVisible(true);
			Clock.update();
			this.repaint();
		}
	}
	public void setDirection(int direction){
		player.setDirection(direction);
		player.move();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension panelSize = this.getSize();
		int[] center = {panelSize.width / 2, panelSize.height / 2};
		int[] relative = {center[0] - player.getX(), center[1] - player.getY()};
		for (int r = 0; r < mapTile.length; r++) {
			for (int c = 0; c < mapTile[r].length; c++) {
				if (mapTile[r][c] == 1) {
					g.setColor(Color.BLACK);
				}
				else {
					g.setColor(Color.WHITE);
				}
				//g.fillRect(r*size, c*size, size,size);
				g.fillRect((r*size) + relative[0],(c*size) + relative[1],size,size);
			}
		}
		for (int r = 0; r < mapBlocks.length; r++) {
			for (int c = 0; c < mapBlocks[r].length; c++) {
				if (mapBlocks[r][c].equals("g")) {
					g.drawImage(grass,(r*size) + relative[0],(c*size) + relative[1], size, size, null);
				}else if (mapBlocks[r][c].equals("s")){
					g.drawImage(stone,(r*size) + relative[0],(c*size) + relative[1], size, size, null);
				}else if (mapBlocks[r][c].equals("w")){
					g.drawImage(stone,(r*size) + relative[0],(c*size) + relative[1], size, size, null);
					g.drawImage(water,(r*size) + relative[0],(c*size) + relative[1], size, size, null);
				}else if (mapBlocks[r][c].equals("p")){
					g.drawImage(wood,(r*size) + relative[0],(c*size) + relative[1], size, size, null);
				}else{
					g.drawImage(grass,(r*size) + relative[0],(c*size) + relative[1], size, size, null);
				}
				//g.fillRect((r*size) + relative[0],(c*size) + relative[1],size,size);
			}
		}
		//enemies = map.getEnemyIDs();
		g.setColor(Color.GREEN);
		for (int i=0; i<map.getStructures().size(); i++) {
			Structure structure = map.getStructures().get(i);
			int x = structure.getX();
			int y = structure.getY();
			g.drawImage(structure.getImage(),x + relative[0] - (size/2),y + relative[1] - (size/2),structure.getWidth(),structure.getHeight(),null);
		}
		for (int i = 0; i < map.getEnemyIDs().size(); i++) {
			EnemyInstance enemy = map.getEnemy(map.getEnemyIDs().get(i));
			int x = enemy.getX();
			int y = enemy.getY();
			Entity e = enemy.getEntity();
			g.drawImage(e.getSprite(),x + relative[0] - (size/2),y + relative[1] - (size/2),e.getWidth(),e.getLength(),null);
			//g.fillRect(x + relative[0] - (size/2),y + relative[1] - (size/2),size,size);
		}
		for (int i=0; i<map.getWeaponEffectIDs().size(); i++) {
			WeaponEffectInstance weaponEffectInstance = map.getWeaponEffectInstance(map.getWeaponEffectIDs().get(i));
			int x = weaponEffectInstance.getX();
			int y = weaponEffectInstance.getY();
			Entity e = weaponEffectInstance.getEntity();
			g.drawImage(e.getSprite(),x + relative[0] - (size/2),y + relative[1] - (size/2),e.getWidth(),e.getLength(),null);
		}
		for (int i=0; i<map.getNpcIDs().size(); i++) {
			NPCInstance npcInstance = map.getNPC(map.getNpcIDs().get(i));
			int x = npcInstance.getX();
			int y = npcInstance.getY();
			Entity e = npcInstance.getEntity();
			g.drawImage(e.getSprite(),x + relative[0] - (size/2),y + relative[1] - (size/2),e.getWidth(),e.getLength(),null);
		}
//		for (int i=0; i<map.getPlayerIDs().size(); i++) {
//			PlayerInstance playerInstance = map.getPlayer(map.getPlayerIDs().get(i));
//			int x = playerInstance.getX();
//			int y = playerInstance.getY();
//			Entity e = playerInstance.getEntity();
//			g.drawImage(e.getSprite(),x + relative[0] - (size/2),y + relative[1] - (size/2),e.getWidth(),e.getLength(),null);
//		}
		for (int i=0; i<map.getEnvironmentalIDs().size(); i++) {
			EnvironmentalInstance environmentalInstance = map.getEnvironmental(map.getEnvironmentalIDs().get(i));
			int x = environmentalInstance.getX();
			int y = environmentalInstance.getY();
			Entity e = environmentalInstance.getEntity();
			g.drawImage(e.getSprite(),x + relative[0] - (size/2),y + relative[1] - (size/2),e.getWidth(),e.getLength(),null);
		}
		for (int i=0; i <map.getItemDropIDs().size(); i++) {
			ItemDropInstance itemDropInstance = map.getItemDrop(map.getItemDropIDs().get(i));
			int x = itemDropInstance.getX();
			int y = itemDropInstance.getY();
			Entity e = itemDropInstance.getEntity();
			g.drawImage(e.getSprite(),x + relative[0] - (size/2),y + relative[1] - (size/2),e.getWidth(),e.getLength(),null);
		}
		Font currentFont = g.getFont();
		if (player.getNearbyItem() != null) {
			ItemDropInstance e = player.getNearbyItem();
			int x = e.getX();
			int y = e.getY();

			Font newFont = currentFont.deriveFont(Font.BOLD);
			g.setFont(newFont);
			g.drawString(Integer.toString(e.getStack().getStackAmount()),x + relative[0] - (size/2),y + relative[1] - (size/2));
		}

		g.setFont(currentFont);

		switch(player.getDirection()){
			case 0:
				g.drawImage(player.drawDown(), center[0] - (size / 2), center[1] - (size / 2),size,size, null);
				break;
			case 1:
				g.drawImage(player.drawUp(),center[0] - (size / 2), center[1] - (size / 2),size,size, null);
				break;
			case 2:
				g.drawImage(player.drawRight(),center[0] - (size / 2), center[1] - (size / 2),size,size, null);
				break;
			case 3:
				g.drawImage(player.drawLeft(),center[0] - (size / 2), center[1] - (size / 2),size,size, null);
				break;
		}

		//g.setColor(Color.RED);
		//g.fillRect(center[0] - (size / 2), center[1] - (size / 2), size, size);

		//g.drawString(Double.toString(Clock.getFps()), 10,10);
		//g.setColor(Color.GRAY);
		//g.drawString(getFrameRate(),10,10);

		//Draw UI
		//Health bar
		g.drawImage(healthEmpty, center[0] - (hotbar.getWidth() / 2),(2 * center[1]) - (hotbar.getHeight()) - 40, null);

		//Crop full health bar to be a percentage of the max health
		double percentHealth = (double)(player.getCurrentHealth()) / (double)(player.getMaxHealth());
		g.drawImage(cropImage(healthFull, percentHealth), center[0] - (hotbar.getWidth() / 2),(2 * center[1]) - (hotbar.getHeight()) - 40, null);

		//Hunger bar
		g.drawImage(healthEmpty, center[0] + (hotbar.getWidth() / 2) - healthEmpty.getWidth(),(2 * center[1]) - (hotbar.getHeight()) - 40, null);

		//Crop full hunger bar to be a percentage of the max hunger
		double percentHunger = (double)(player.getCurrentHunger()) / 10.0;
		g.drawImage(cropImage(hungerFull, percentHunger), center[0] + (hotbar.getWidth() / 2) - healthEmpty.getWidth(),(2 * center[1]) - (hotbar.getHeight()) - 40, null);

		//Inventory hotbar
		g.drawImage(hotbar, center[0] - (hotbar.getWidth() / 2), (2 * center[1]) - (hotbar.getHeight()) - 8, null);

		//Currently selected item
		int current = player.getInventory().getCurrentItemIndex() - 27;
		g.drawImage(hotbarSelect, center[0] - (hotbar.getWidth() / 2) - 4 + (current * 80), (2 * center[1]) - (hotbar.getHeight()) - 12, null);

		//Other items
		double cooldown = (-1/Math.pow(player.getDexterity()/10, 2)+1) + 1;
		for (int i = 0; i <= 9; i++) {
			try {
				Stack currentStack = player.getInventory().get(i + 27);
				Image image = currentStack.getItem().getSprite();
				int quantity = currentStack.getStackAmount();
				g.drawImage(image, (center[0] - 29) + ((i - 4) * 80) + 1,(center[1] * 2) - 56 - 24,60,60,null);
				//Only draw stack amount if quantity > 1
				if (quantity > 1) {
					g.setColor(Color.WHITE);
					g.drawString(Integer.toString(quantity), (center[0] - 29) + ((i - 4) * 80) + 52, (center[1] * 2) - 24);
				}
				//Check for cooldown
				if (currentStack.getItem() instanceof Consumable) {
					double delta = System.nanoTime()/1e+9 - player.getLastConsumableUse();
					if (delta < 5) {
						g.setColor(darken);
						g.fillRect((center[0] - 29) + ((i - 4) * 80) - 3, (center[1] * 2) - 56 - 28, 64, 64);
						g.setColor(Color.WHITE);
					}
				}
				if (currentStack.getItem() instanceof Weapon) {
					double delta = System.nanoTime()/1e+9 - player.getLastWeaponUse();
					if (delta < cooldown) {
						g.setColor(darken);
						g.fillRect((center[0] - 29) + ((i - 4) * 80) - 3,(center[1] * 2) - 56 - 28,64,64);
					}
				}
			} catch (NullPointerException e) {
				//No item in slot
			}
		}
		repaint();
	}

	private BufferedImage cropImage(BufferedImage src, double percent) {
		BufferedImage dest;
		try {
			dest = src.getSubimage(0, 0, (int)(src.getWidth() * percent), src.getHeight());
		} catch (RasterFormatException e) {
			//health is <= 0
			dest = src.getSubimage(0, 0, 1, src.getHeight());
		}
		return dest;
	}

	private class GameMouseWheelListener implements MouseWheelListener {
		@Override
		public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
			int movement = mouseWheelEvent.getWheelRotation();
			player.getInventory().changeCurrentItem(movement);
		}
	}


}
