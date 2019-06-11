package World;

import Entities.EnemyInstance;
import Entities.*;
import Entities.PlayerInstance;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
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
	int size = 64;
	private int mouseClickX;
	private int mouseClickY;
	//ArrayList<Double> enemies;

	private String frameRate;
	private long lastTimeCheck;
	private long deltaTime;
	private int frameCount;

	BufferedImage hotbar;
	BufferedImage hotbarSelect;
	private GameMouseWheelListener gameMouseWheelListener;

	public WorldDisplayer(PlayerInstance player, Location map) {
		this.player = player;
		player.loadSprites();
		this.map = map;
		this.mapTile = map.getMap();

		//move this later
		try {
			hotbar = ImageIO.read(new File("assets/gui/hotbar.png"));
			hotbarSelect = ImageIO.read(new File("assets/gui/selected.png"));
		} catch (IOException e) {
			//image not found ?
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
		for (int i=0; i<map.getPlayerIDs().size(); i++) {
			PlayerInstance playerInstance = map.getPlayer(map.getPlayerIDs().get(i));
			int x = playerInstance.getX();
			int y = playerInstance.getY();
			Entity e = playerInstance.getEntity();
			g.drawImage(e.getSprite(),x + relative[0] - (size/2),y + relative[1] - (size/2),e.getWidth(),e.getLength(),null);
		}
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
			case 1:
				g.drawImage(player.drawUp(),center[0] - (size / 2), center[1] - (size / 2),size,size, null);
			case 2:
				g.drawImage(player.drawRight(),center[0] - (size / 2), center[1] - (size / 2),size,size, null);
			case 3:
				g.drawImage(player.drawLeft(),center[0] - (size / 2), center[1] - (size / 2),size,size, null)	;
		}

		g.setColor(Color.RED);
		//g.fillRect(center[0] - (size / 2), center[1] - (size / 2), size, size);

		//g.drawString(Double.toString(Clock.getFps()), 10,10);
		//g.setColor(Color.GRAY);
		//g.drawString(getFrameRate(),10,10);

		//Draw UI
		//Health bar
		g.setColor(Color.BLACK);
		g.fillRect(16, 16, 256,32);
		g.setColor(Color.RED);
		g.fillRect(16,16,256 * (player.getCurrentHealth() / player.getMaxHealth()), 32);

		//Inventory hotbar
		g.drawImage(hotbar, center[0] - (hotbar.getWidth() / 2), (2 * center[1]) - (hotbar.getHeight()) - 8, null);

		//Currently selected item
		int current = player.getInventory().getCurrentItem() - 27;
		g.drawImage(hotbarSelect, center[0] - (hotbar.getWidth() / 2) - 4 + (current * 80), (2 * center[1]) - (hotbar.getHeight()) - 12, null);

		//Other items
		g.setColor(Color.BLACK);
		for (int i = 0; i <= 9; i++) {
			try {
				Image image = player.getInventory().get(i + 27).getItem().getSprite();
				int quantity = player.getInventory().get(i + 27).getStackAmount();
				g.drawImage(image, (center[0] - 29) + ((i - 4) * 80) + 1,(center[1] * 2) - 56 - 24,60,60,null);
				g.drawString(Integer.toString(quantity), (center[0] - 29) + ((i - 4) * 80) + 52,(center[1] * 2) - 24);
			} catch (NullPointerException e) {
				//No item in slot
			}
		}
		repaint();
	}

	private class GameMouseWheelListener implements MouseWheelListener {
		@Override
		public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {
			int movement = mouseWheelEvent.getWheelRotation();
			player.getInventory().changeCurrentItem(movement);
		}
	}


}
