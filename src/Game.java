import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JFrame {
	private GamePanel panel;
	private String frameRate;
	private long lastTimeCheck;
	private long deltaTime;
	private int frameCount;

	Listener listener = new Listener();

	//*****TEMPORARY VARIABLES*****//
	//Replace later
	Player player = new Player();
	int[][] map = {
			{1,1,1,1,1},
			{1,0,0,0,1},
			{1,0,0,0,1},
			{1,0,0,0,1},
			{1,1,1,1,1}};

	Game() {
		this.setSize(1280,720);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(listener);
		panel = new GamePanel();
		this.add(panel);
		this.setVisible(true);
		this.requestFocusInWindow();
	}

	private class GamePanel extends JPanel {
		GamePanel() {
			Thread t = new Thread(new GameLoop());
			t.start();
		}

		private class GameLoop implements Runnable {
			public void run() {
				while (true) {
					repaint();
				}
			}
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Dimension panelSize = this.getSize();
			int[] center = {panelSize.width / 2, panelSize.height / 2};
			int[] relative = {center[0] - player.x, center[1] - player.y};
			for (int r = 0; r < map.length; r++) {
				for (int c = 0; c < map[r].length; c++) {
					if (map[r][c] == 1) {
						g.setColor(Color.BLACK);
					}
					else {
						g.setColor(Color.WHITE);
					}
					g.fillRect((r*100) + relative[0],(c*100) + relative[1],100,100);
				}
			}
			g.setColor(Color.RED);
			g.fillRect(center[0] - (player.size / 2), center[1] - (player.size / 2), player.size, player.size);
			g.setColor(Color.GRAY);
			g.drawString(getFrameRate(),10,10);
			repaint();
		}

		public String getFrameRate()  {
			long currentTime = System.currentTimeMillis();  //get the current time
			deltaTime += currentTime - lastTimeCheck; //add to the elapsed time
			lastTimeCheck = currentTime; //update the last time var
			frameCount++; //Every time this method is called it is a new frame
			if (deltaTime>=1000) { //when a second has passed, update the string message
				frameRate = frameCount + " fps";
				frameCount = 0; //reset the number of frames since last update
				deltaTime = 0;  //reset the elapsed time
			}
			return frameRate;
		}
	}

	private class Listener implements KeyListener {
		public void keyPressed(KeyEvent e) {
			if (KeyEvent.getKeyText(e.getKeyCode()).equals("W")) {  //W
				player.y -= 10;
			}
			if (KeyEvent.getKeyText(e.getKeyCode()).equals("D")) {  //D
				player.x += 10;
			}
			if (KeyEvent.getKeyText(e.getKeyCode()).equals("S")) {  //S
				player.y += 10;
			}
			if (KeyEvent.getKeyText(e.getKeyCode()).equals("A")) {  //A
				player.x -= 10;
			}
		}

		public void keyTyped(KeyEvent e) {}
		public void keyReleased(KeyEvent e) {}
	}
}
