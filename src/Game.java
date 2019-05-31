import Entities.*;
import Items.*;
import World.Location;
import World.LocationHandler;
import World.WorldDisplayer;


import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JFrame {
	private GamePanel panel;
	private WorldDisplayer worldPanel;
	private InventoryMenu inventoryMenu;
	private Inventory inventory; // remove this later
	private String frameRate;
	private long lastTimeCheck;
	private long deltaTime;
	private int frameCount;
	private JPanel currentPanel;

	private Listener listener = new Listener();
	GameMouseListener mouseListener = new GameMouseListener();

	//*****TEMPORARY VARIABLES*****//
	//Replace later

	Player player;
	PlayerInstance playerInstance;
	Location map;

	Game() {
		this.setSize(1280,760);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(listener);
		this.addMouseListener(mouseListener);

		panel = new GamePanel();
		//Might want to remove this later
		this.inventory = new Inventory(36);
		Image woodSwordSprite = Toolkit.getDefaultToolkit().createImage("WoodenSword.png");
		Image stickSprite = Toolkit.getDefaultToolkit().createImage("Stick.png");
		Image cakeSprite = Toolkit.getDefaultToolkit().createImage("Cake.png");
		Image breadSprite = Toolkit.getDefaultToolkit().createImage("Bread.png");
		Item woodSword = new Sword("Wood Sword", "A wooden sword.", woodSwordSprite,2,2,2);
		Item stick = new Material("Stick", "A wood stick", stickSprite);
		Item cake = new Food("Cake", "A delicious cake lovingly baked by Feng", cakeSprite,0 ,8);
		Item bread = new Food("Bread", "A delicious loaf of bread lovingly baked by Feng", breadSprite, 0, 5);

		//********ACTUALLY CHANGE THIS LATER**********//
		player = new Player(breadSprite,10,10,10,10,"1","test");
		playerInstance = new PlayerInstance(0,0,player);
		map = new Location(breadSprite);
		LocationHandler mapHan = new LocationHandler(map);
		Thread t = new Thread(mapHan);
		HashMap<Stack, Double> temp = new HashMap<>();
		Stack stackOne = new Stack(1, woodSword);
		temp.put(stackOne, 0.4);
		Enemy enemy1 = new Enemy(cakeSprite, 64, 64, 20, 2, 2, 2, "Cake", "Cake", temp);
		Enemy enemy2 = new Enemy(stickSprite, 64, 64, 20, 3, 3, 3, "Stick", "Wood", temp);
		EnemyInstance enemy1Ins = new EnemyInstance(20, 40, enemy1, (Weapon)woodSword);
		EnemyInstance enemy2Ins = new EnemyInstance(100, 400, enemy2, (Weapon)woodSword);
		EnemyHandler enemy1Han = new EnemyHandler(enemy1Ins, map);
		EnemyHandler enemy2Han = new EnemyHandler(enemy2Ins, map);

		Environmental env = new Environmental(woodSwordSprite, 3, 3, 2, woodSword);
		EnvironmentalInstance envIns = new EnvironmentalInstance(400, 300, env);
		mapHan.addEnemy(enemy1Han, 0.4);
		mapHan.addEnemy(enemy2Han, 0.24);
		worldPanel = new WorldDisplayer(playerInstance,map);

		Stack cakeStack = new Stack(2, cake);
		Stack breadStack = new Stack(30, bread);
		inventory.add(cakeStack);
		inventory.add(breadStack);

		Stack stackTwo = new Stack(20, stick);
		Stack stackThree = new Stack(10, stick);
		Stack stackFour = new Stack(23, stick);
		Stack stackFive = new Stack(24, stick);
		Stack stackSix = new Stack(29, stick);
		Stack[] moreStack = new Stack[29];
		for (int i=0; i<29;i++) {
			moreStack[i] = new Stack(1, woodSword);
			inventory.add(moreStack[i]);
		}
		inventory.add(stackOne);
		inventory.add(stackTwo);
		inventory.add(stackThree);
		inventory.add(stackFour);
		inventory.add(stackFive);
		inventory.add(stackSix);
		inventoryMenu = new InventoryMenu(inventory);



		this.add(worldPanel);
		currentPanel = worldPanel;
//		this.add(inventoryMenu);

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
				Thread worldThread = new Thread(worldPanel);
				worldThread.run();
				while (true) {
					repaint();
				}
			}
		}

		/*public void paintComponent(Graphics g) {
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
		}*/

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

	private abstract class Menu extends JPanel {
		//lol
	}

	private class InventoryMenu extends Menu {
		private Image inventoryGUI = Toolkit.getDefaultToolkit().createImage("InventoryGUI.png");
		private Inventory inventory;
		private PanelMouseListener mouseListener = new PanelMouseListener();
		private PanelMouseMotionListener motionListener = new PanelMouseMotionListener();
		private Stack handStack;

		private int x;
		private int y;
		private int mouseX;
		private int mouseY;
		private int highlightX;
		private int highlightY;

		private int pressedX;
		private int pressedY;
		private int releasedX;
		private int releasedY;

		private MouseEvent pressedEvent;

		InventoryMenu(Inventory inventory) {
			this.inventory = inventory;
			this.addMouseListener(mouseListener);
			this.addMouseMotionListener(motionListener);
			this.setSize(1280,720);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.BLACK);
			g.fillRect(0,0, 1280, 720);

			g.drawImage(inventoryGUI,288,28,704, 664,null);


			for (int i = 0; i < 3; i++) {
				for (int j=0; j<9; j++) {
					try {
						g.drawImage(inventory.get(i * 9 + j).getItem().getSprite(), 323 + 72 * j, 367 + 72 * i, 58, 58, null);
						if (inventory.get(i*9+j).getStackAmount() > 1) {
							g.drawString(Integer.toString(inventory.get(i * 9 + j).getStackAmount()), 372 + 72 * j, 415 + 72 * i);
						}
					} catch (NullPointerException e) {
						//is empty
					}
				}
			}
			for (int i =0; i<9; i++) {
				try {
					g.drawImage(inventory.get(27 + i).getItem().getSprite(), 323 + 72 * i, 599, 58, 58, null);
					if (inventory.get(27 + i).getStackAmount() > 1) {
						g.drawString(Integer.toString(inventory.get(27 + i).getStackAmount()), 372 + 72 * i, 647);
					}
				} catch (NullPointerException e) {
					//Nothing here
				}
			}
			if (highlightX != 0 && highlightY != 0) {
				g.setColor(Color.BLUE);
				g.drawRect(highlightX, highlightY,63,63);
				if (handStack == null) {
					try {
						int r;
						if (highlightY == 596) {
							r = 3;
						} else {
							r = (highlightY-364)/72;
						}
						int c = (highlightX-320)/72;
						g.setColor(Color.WHITE);
//						int stackAmount = inventory.get(r*9+c).getStackAmount(); //This just forces a nullpointer if it is possible lol
						g.fillRect(highlightX+64, highlightY, 100, 200);
						g.setColor(Color.BLACK);
						g.drawString(inventory.get(r * 9 + c).getItem().getDescription(), highlightX + 74, highlightY + 30);
					} catch (NullPointerException e) {
						//oops
					}
				}
			}
			if (handStack != null) {
				g.drawImage(handStack.getItem().getSprite(), mouseX-27, mouseY-27, 58,58, null);
				if (handStack.getStackAmount() > 1) {
					g.setColor(Color.BLACK);
					g.drawString(Integer.toString(handStack.getStackAmount()), mouseX+22, mouseY+21);
				}
			}

			//g.fillRect(x,y,10,10);
			repaint();
		}

		private class PanelMouseListener implements MouseListener {
			public void mousePressed(MouseEvent e) {
//				System.out.println("Mouse clicked: " + e.getX() + " " + e.getY());
				x = e.getX();
				y = e.getY();
				int checkX = x-320;
				int checkY;
				boolean fourthRow = false;
				if (y<596) {
					checkY = y - 364;
				} else if (y<660){
					checkY = y - 596;
					fourthRow = true;
				} else {
					return;
				}
				if (checkX<0 || checkY<0) {
					return;
				}
				if (checkX%72<64) {
					if (checkY % 72 < 64) {
						int r;
						if (fourthRow) {
							r = 3;
						} else {
							r = checkY / 72;
						}
						int c = checkX / 72;
						int index = r*9+c;
						if (handStack == null) {
							if (e.getButton() == e.BUTTON1) {
								if ((e.getModifiersEx() & InputEvent.SHIFT_DOWN_MASK) != 0) {
									if (index < 27) {
										inventory.sendDown(index);
									} else {
										inventory.sendTop(index);
									}
								} else {
									handStack = inventory.remove(index);
								}
							} else if (e.getButton() == e.BUTTON3) {
								handStack = inventory.dropHalf(index);
							}
						} else {
							if (inventory.get(index) == null) {
								if (e.getButton() == e.BUTTON1) {
									inventory.add(handStack, index);
									handStack = null;
								} else if (e.getButton() == e.BUTTON3) {
									inventory.add(new Stack(1, handStack.getItem()), index);
									if (handStack.getStackAmount() > 1) {
										handStack.remove(1);
									} else {
										handStack = null;
									}
								}
							} else {
								if (handStack.getItem().getName().equals(inventory.get(index).getItem().getName()) && handStack.getItem().getMaxStack() > 1) {
									if (e.getButton() == e.BUTTON1) {
										int remaining = inventory.get(index).add(handStack.getStackAmount());
										if (remaining > 0) {
											handStack.setStackAmount(remaining);
										} else {
											handStack = null;
										}
									} else if (e.getButton() == e.BUTTON3) {
										inventory.get(index).add(1);
										if (handStack.getStackAmount() > 1) {
											handStack.remove(1);
										} else {
											handStack = null;
										}
									}
								} else {
									Stack temp = inventory.remove(index);
									inventory.add(handStack, index);
									handStack = temp;
								}
							}
						}
					}
				}
			}

			public void mouseEntered(MouseEvent e) {

			}

			public void mouseExited(MouseEvent e) {

			}

			public void mouseClicked(MouseEvent e) {
//				System.out.println("Mouse pressed: " + e.getX() + " " + e.getY());
//				pressedX = e.getX();
//				pressedY = e.getY();
//				pressedEvent = e;
			}

			public void mouseReleased(MouseEvent e) {
				/*System.out.println("Mouse released: " + e.getX() + " " + e.getY());
				releasedX = e.getX();
				releasedY = e.getY();
				int deltaX = Math.abs(releasedX-pressedX);
				int deltaY = Math.abs(releasedY-pressedY);
				if (deltaX<100 && deltaY<100 && deltaX!=0 && deltaY!=0) {
					this.mouseClicked(pressedEvent);
				}*/
			}
		}

		private class PanelMouseMotionListener implements MouseMotionListener {
			public void mouseDragged(MouseEvent e) {

			}

			public void mouseMoved(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
				int checkX = mouseX-320;
				int checkY;
				boolean fourthRow = false;
				if (mouseY<596) {
					if (mouseY > 572) {
						highlightX = 0;
						highlightY = 0;
						return;
					}
					checkY = mouseY - 364;
				} else if (mouseY < 660) {
					checkY = mouseY - 596;
					fourthRow = true;
				} else {
					highlightX = 0;
					highlightY = 0;
					return;
				}
				if (checkX<0 || checkY<0) {
					highlightX = 0;
					highlightY = 0;
					return;
				}
				if (checkX%72<64 && checkX<648 && checkY % 72 < 64) {
					int r;
					if (fourthRow) {
						highlightY = 596;
					} else {
						r = checkY / 72;
						highlightY = 364+72*r;
					}
					int c = checkX / 72;
					highlightX = 320+72*c;
				} else {
					highlightY = 0;
					highlightX = 0;
				}
			}
		}
	}

	private class Listener implements KeyListener {
		public void keyPressed(KeyEvent e) {
			if (KeyEvent.getKeyText(e.getKeyCode()).equals("W")) {  //W
				playerInstance.moveY(-8);
			}
			if (KeyEvent.getKeyText(e.getKeyCode()).equals("D")) {  //D
				playerInstance.moveX(8);
			}
			if (KeyEvent.getKeyText(e.getKeyCode()).equals("S")) {  //S
				playerInstance.moveY(8);
			}
			if (KeyEvent.getKeyText(e.getKeyCode()).equals("A")) {  //A
				playerInstance.moveX(-8);
			}
		}

		public void keyTyped(KeyEvent e) {}
		public void keyReleased(KeyEvent e) {
			if (KeyEvent.getKeyText(e.getKeyCode()).equals("E")) {
				if (currentPanel == worldPanel) {
					remove(worldPanel);
					add(inventoryMenu);
					currentPanel = inventoryMenu;
				} else if (currentPanel == inventoryMenu) {
					remove(inventoryMenu);
					add(worldPanel);
					currentPanel = worldPanel;
				}
				repaint();
			}
		}
	}

	private class GameMouseListener implements MouseListener {
		public void mouseClicked(MouseEvent e) {

		}

		public void mouseEntered(MouseEvent e) {

		}

		public void mouseExited(MouseEvent e) {

		}

		public void mousePressed(MouseEvent e) {

		}

		public void mouseReleased(MouseEvent e) {

		}
	}
}
