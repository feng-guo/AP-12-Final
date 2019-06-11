package World;

import Entities.EnemyInstance;
import Entities.*;
import Entities.PlayerInstance;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

public class WorldDisplayer extends JPanel implements Runnable {

	boolean running = true;
	PlayerInstance player;
	Location map;
	int[][] mapTile;
	int size = 64;
	//ArrayList<Double> enemies;

	private String frameRate;
	private long lastTimeCheck;
	private long deltaTime;
	private int frameCount;

	public WorldDisplayer(PlayerInstance player, Location map) {
		this.player = player;
		player.loadSprites();
		this.map = map;
		this.mapTile = map.getMap();
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
		if (player.getNearbyItem() != null) {
			ItemDropInstance e = player.getNearbyItem();
			int x = e.getX();
			int y = e.getY();
			g.setFont(g.getFont().deriveFont(40F));
			g.drawString(Integer.toString(e.getStack().getStackAmount()),x + relative[0] - (size/2),y + relative[1] - (size/2));
		}

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
		repaint();
	}
}
