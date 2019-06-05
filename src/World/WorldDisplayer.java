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
	ArrayList<Double> enemies;

	public WorldDisplayer(PlayerInstance player, Location map) {
		this.player = player;
		this.map = map;
		this.mapTile = map.getMap();
	}

	public void run() {
		while (running) {
			this.setVisible(true);
			this.repaint();
		}
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
		enemies = map.getEnemyIDs();
		g.setColor(Color.GREEN);
		for (int i = 0; i < enemies.size(); i++) {
			EnemyInstance enemy = map.getEnemy(enemies.get(i));
			int x = enemy.getX();
			int y = enemy.getY();
			Entity e = enemy.getEntity();
			g.drawImage(e.getSprite(),x + relative[0] - (size/2),y + relative[1] - (size/2),e.getWidth(),e.getLength(),null);
			//g.fillRect(x + relative[0] - (size/2),y + relative[1] - (size/2),size,size);
		}
		g.setColor(Color.RED);
		g.fillRect(center[0] - (size / 2), center[1] - (size / 2), size, size);
		//g.setColor(Color.GRAY);
		//g.drawString(getFrameRate(),10,10);
		repaint();
	}
}
