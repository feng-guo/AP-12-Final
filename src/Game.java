import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener {
	private String frameRate;
	private long lastTimeCheck;
	private long deltaTime;
	private int frameCount;

	Player player = new Player();

	Game() {
		this.setPreferredSize(new Dimension(1280,720));
		this.setVisible(true);
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
		g.drawString(getFrameRate(),10,10);
		g.drawRect(player.x,player.y,100,100);
		repaint();
	}

	public void keyPressed(KeyEvent e) {
		//Player 1 controls
		if (KeyEvent.getKeyText(e.getKeyCode()).equals("W")) {  //W
			player.y++;
		}
		if (KeyEvent.getKeyText(e.getKeyCode()).equals("D")) {  //D
			player.x++;
		}
		if (KeyEvent.getKeyText(e.getKeyCode()).equals("S")) {  //S
			player.y--;
		}
		if (KeyEvent.getKeyText(e.getKeyCode()).equals("A")) {  //A
			player.x--;
		}
	}

	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}

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
