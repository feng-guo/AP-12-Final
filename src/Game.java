import Database.DatabaseConnector;
import Entities.*;
import Items.*;
import World.Location;
import World.LocationHandler;
import World.WorldDisplayer;


import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.*;

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


	//START PANELS
	private JPanel optionPanel;
	private JPanel serverPanel;
	private JPanel ipPanel;
	private JPanel portPanel;


	private Listener listener = new Listener();

	//*****TEMPORARY VARIABLES*****//
	//Replace later

	Player player;
	PlayerInstance playerInstance;
	PlayerHandler playerHandler;
	Location map;

	Game() {
		this.setSize(1280,760);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(listener);

		//initializeAssets();

		//initializeStartPanels(); //FIX THIS
		//addOptionPanel here

		panel = new GamePanel();

		startNewSingleplayerGame();
		this.add(worldPanel);
		currentPanel = worldPanel;


		this.setVisible(true);
		this.requestFocusInWindow();
	}

	private void initializeStartPanels() {
		optionPanel = new JPanel();
		optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));

		serverPanel = new JPanel();
		serverPanel.setLayout(new BoxLayout(serverPanel, BoxLayout.Y_AXIS));

		ipPanel = new JPanel();
		portPanel = new JPanel();

		//Create JButtons
		JButton singlePlayerButton = new JButton();
		JButton multiplayerButton = new JButton(); //add later
		JButton instructionButton = new JButton();
		JButton quitButton = new JButton();
		JButton backButton = new JButton();

		singlePlayerButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage("assets/menu/Play.png")));
		multiplayerButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage("Bread.png")));
		instructionButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage("assets/menu/Instructions.png")));
		quitButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage("assets/menu/Quit.png")));
		backButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage("assets/menu/Back.png")));

		singlePlayerButton.addActionListener(actionEvent -> {
			startNewSingleplayerGame();
			removeAllPanels();
			addPanel(worldPanel);
			currentPanel = worldPanel;
            repaint();
        });

		instructionButton.addActionListener(actionEvent -> {
			//Code here
		});
		quitButton.addActionListener(actionEvent -> {
			//Code here
		});
		backButton.addActionListener(actionEvent -> {
			//Code here
		});

		ipPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		portPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

		serverPanel.add(ipPanel);
		serverPanel.add(Box.createVerticalStrut(10)); //Creates space between components
		serverPanel.add(portPanel);
		serverPanel.add(Box.createVerticalStrut(20));

		//Add buttons to panels
		singlePlayerButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		instructionButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		quitButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

		optionPanel.add(singlePlayerButton);
		optionPanel.add(Box.createVerticalStrut(10));
		optionPanel.add(instructionButton);
		optionPanel.add(Box.createVerticalStrut(10));
		optionPanel.add(quitButton);
	}

	private void startNewSingleplayerGame() {
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

		Armour breadHelmet = new Armour("Bread Helmet", "A helmet made of bread", Toolkit.getDefaultToolkit().createImage("assets/bread.png"), 200, 20, "Helmet");
		Armour titanium = new Armour("Titanium", "A helmet made of bread", Toolkit.getDefaultToolkit().createImage("assets/titanium.png"), 200, 20, "Armour");
		Armour gold = new Armour("Bread Helmet", "A helmet made of bread", Toolkit.getDefaultToolkit().createImage("assets/gold.png"), 200, 20, "Boots");
		Armour copper = new Armour("Bread Helmet", "A helmet made of bread", Toolkit.getDefaultToolkit().createImage("assets/copper.png"), 200, 20, "Arm");
		Armour silver = new Armour("Bread Helmet", "A helmet made of bread", Toolkit.getDefaultToolkit().createImage("assets/silver.png"), 200, 20, "Arm");
		Armour coal = new Armour("Bread Helmet", "A helmet made of bread", Toolkit.getDefaultToolkit().createImage("assets/coal.png"), 200, 20, "Leg");
		Armour cakeLeg = new Armour("Bread Helmet", "A helmet made of bread", Toolkit.getDefaultToolkit().createImage("assets/cake.png"), 200, 20, "Leg");
		Armour berryRing = new Armour("Bread Helmet", "A helmet made of bread", Toolkit.getDefaultToolkit().createImage("assets/berry.png"), 200, 20, "Ring");
		Armour riceRing = new Armour("Bread Helmet", "A helmet made of bread", Toolkit.getDefaultToolkit().createImage("assets/rice.png"), 200, 20, "Ring");
		Armour potato = new Armour("Bread Helmet", "A helmet made of bread", Toolkit.getDefaultToolkit().createImage("assets/root_vegetable.png"), 200, 20, "Ring");
		inventory.setArmour(titanium);
		inventory.setHelmet(breadHelmet);
		inventory.setBoots(gold);
		inventory.setLeftArm(copper);
		inventory.setRightArm(silver);
		inventory.setLeftLeg(coal);
		inventory.setRightLeg(cakeLeg);
		inventory.setRing1(berryRing);
		inventory.setRing2(riceRing);
		inventory.setRing3(potato);


		playerInstance = new PlayerInstance(0,0,player);
		playerHandler = new PlayerHandler(playerInstance, map);
		map = new Location(breadSprite);
		LocationHandler mapHan = new LocationHandler(map);
		Thread p = new Thread(playerHandler);
		p.start();
		Thread t = new Thread(mapHan);
		t.start();
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

		listener = new Listener();
	}

	private void initializeAssets() {
		DatabaseConnector.connect();
		ItemsList.initialize();
		EnemiesList.initialize();
	}

	private void removeAllPanels() {
		remove(optionPanel);
		removeAll();
	}

	private void addPanel(JPanel panel) {
		add(panel);
		repaint();
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
		private BufferedImage inventoryGUI;
		private Inventory inventory;
		private Stack[] tempInventory;
		private PanelMouseListener mouseListener = new PanelMouseListener();
		private PanelMouseMotionListener motionListener = new PanelMouseMotionListener();
		private Stack handStack;

		private int x;
		private int y;
		private int mouseX;
		private int mouseY;
		private int highlightX;
		private int highlightY;
		private int topItemHighlight;

		private int mouseButton = -1;
		private int totalHandStackItems;
		private int tempInventorySlots;
		private Item tempItem;

		InventoryMenu(Inventory inventory) {
			this.inventory = inventory;
			this.tempInventory = new Stack[36];
			File f = new File("InventoryGUI_2.png");
			try {
				this.inventoryGUI = ImageIO.read(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.addMouseListener(mouseListener);
			this.addMouseMotionListener(motionListener);
			this.setSize(1280,720);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.BLACK);
			g.fillRect(0,0, 1280, 720);

			g.drawImage(inventoryGUI,288,28,704, 664,null);


			paintInventory(g);

			if (topItemHighlight != -1) {
				highlightTopItem(g);
			} else if (highlightX != 0 && highlightY != 0) {
				highlightItem(g);
			}

			if (handStack != null) {
				paintHandStack(g);
			}
			repaint();
		}

		private void paintInventory(Graphics g) {
			for (int i = 0; i < 3; i++) {
				for (int j=0; j<9; j++) {
					try {
						if (inventory.get(i*9+j).getItem() != null) {
							g.drawImage(inventory.get(i * 9 + j).getItem().getSprite(), 323 + 72 * j, 367 + 72 * i, 58, 58, null);
							int itemCount = inventory.get(i * 9 + j).getStackAmount();
							if (tempInventory[i*9+j] != null) {
								itemCount += tempInventory[i*9+j].getStackAmount();
								g.setColor(Color.YELLOW);
							}
							if (itemCount > 1) {
								g.drawString(Integer.toString(itemCount), 372 + 72 * j, 415 + 72 * i);
								g.setColor(Color.BLACK);
							}
						} else if (tempInventory[i*9+j] != null) {
							g.drawImage(tempInventory[i*9+j].getItem().getSprite(), 323 + 72 * j, 367 + 72 * i, 58, 58, null);
								int itemCount = tempInventory[i*9+j].getStackAmount();
								if (itemCount > 1) {
									g.setColor(Color.YELLOW);
									g.drawString(Integer.toString(itemCount), 372 + 72 * j, 415 + 72 * i);
									g.setColor(Color.BLACK);
								}
						}
					} catch (NullPointerException e) {
						//is empty
					}
				}
			}
			for (int i =0; i<9; i++) {
				try {
					if (inventory.get(27+i) != null) {
						g.drawImage(inventory.get(27 + i).getItem().getSprite(), 323 + 72 * i, 599, 58, 58, null);
						int itemCount = inventory.get(27+i).getStackAmount();
						if (tempInventory[27+i] != null) {
							itemCount += tempInventory[27+i].getStackAmount();
							g.setColor(Color.YELLOW);
						}
						if (itemCount > 1) {
							g.drawString(Integer.toString(itemCount), 372 + 72 * i, 647);
							g.setColor(Color.BLACK);
						}
					} else if (tempInventory[27+i] != null) {
						g.drawImage(tempInventory[27+i].getItem().getSprite(), 323 + 72 * i, 599, 58, 58, null);
						int itemCount = tempInventory[27+i].getStackAmount();
						if (itemCount > 1) {
							g.setColor(Color.YELLOW);
							g.drawString(Integer.toString(itemCount), 372 + 72 * i, 647);
							g.setColor(Color.BLACK);
						}
					}
				} catch (NullPointerException e) {
					//Nothing here
				}
			}
			try {
				g.drawImage(inventory.getArms()[0].getSprite(), 605,135,58,58,null);
			} catch (NullPointerException e) {

			}

			try {
				g.drawImage(inventory.getLegs()[0].getSprite(), 605,135+72,58,58,null);
			} catch (NullPointerException e) {

			}

			try {
				g.drawImage(inventory.getHelmet().getSprite(), 605+72,99,58,58,null);
			} catch (NullPointerException e) {

			}
			try {
				g.drawImage(inventory.getArmour().getSprite(), 605+72,99+72,58,58,null);
			} catch (NullPointerException e) {

			}
			try {
				g.drawImage(inventory.getBoots().getSprite(), 605+72,99+72*2,58,58,null);
			} catch (NullPointerException e) {

			}
			try {
				g.drawImage(inventory.getArms()[1].getSprite(), 605+72*2,135,58,58,null);
			} catch (NullPointerException e) {

			}try {
				g.drawImage(inventory.getLegs()[1].getSprite(), 605+72*2,135+72,58,58,null);
			} catch (NullPointerException e) {

			}
			try {
				g.drawImage(inventory.getRings()[0].getSprite(), 895,99,58,58,null);
			} catch (NullPointerException e) {

			}
			try {
				g.drawImage(inventory.getRings()[1].getSprite(), 895,99+72,58,58,null);
			} catch (NullPointerException e) {

			}
			try {
				g.drawImage(inventory.getRings()[2].getSprite(), 895,99+72*2,58,58,null);
			} catch (NullPointerException e) {

			}


		}

		private void highlightItem(Graphics g) {
			g.setColor(Color.BLUE);
			if (highlightY == -1 || highlightX == -1) {
				return;
			}
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
					g.fillRect(highlightX+64, highlightY, 100, 200);
					g.setColor(Color.BLACK);
					g.drawString(inventory.get(r * 9 + c).getItem().getDescription(), highlightX + 74, highlightY + 30);
				} catch (NullPointerException e) {
					//oops
				}
			}
		}

		private void highlightTopItem(Graphics g) {
			g.setColor(Color.BLUE);
			int l = 63;
			if (topItemHighlight == 0) {
				g.drawRect(602, 132, l, l);
				if (handStack == null) {
					int topX = 602;
					int topY = 132;
					try {
						g.setColor(Color.WHITE);
						g.fillRect(topX+64, topY, 100, 200);
						g.setColor(Color.BLACK);
						g.drawString(inventory.getArms()[0].getDescription(), topX + 74, topY + 30);
					} catch (NullPointerException e) {
						
					}
				}
			} else if (topItemHighlight == 1) {
				g.drawRect(602, 132+72, l ,l);
				if (handStack == null) {
					int topX = 602;
					int topY = 132+72;
					try {
						g.setColor(Color.WHITE);
						g.fillRect(topX+64, topY, 100, 200);
						g.setColor(Color.BLACK);
						g.drawString(inventory.getLegs()[0].getDescription(), topX + 74, topY + 30);
					} catch (NullPointerException e) {

					}
				}
			} else if (topItemHighlight == 2) {
				g.drawRect(674, 96, l, l);
				if (handStack == null) {
					int topX = 674;
					int topY = 96;
					try {
						g.setColor(Color.WHITE);
						g.fillRect(topX+64, topY, 100, 200);
						g.setColor(Color.BLACK);
						g.drawString(inventory.getHelmet().getDescription(), topX + 74, topY + 30);
					} catch (NullPointerException e) {

					}
				}
			} else if (topItemHighlight == 3) {
				g.drawRect(674, 96+72, l, l);
				if (handStack == null) {
					int topX = 674;
					int topY = 96+72;
					try {
						g.setColor(Color.WHITE);
						g.fillRect(topX+64, topY, 100, 200);
						g.setColor(Color.BLACK);
						g.drawString(inventory.getArmour().getDescription(), topX + 74, topY + 30);
					} catch (NullPointerException e) {

					}
				}
			} else if (topItemHighlight == 4) {
				g.drawRect(674, 96+72*2, l, l);
				if (handStack == null) {
					int topX = 674;
					int topY = 96+72*2;
					try {
						g.setColor(Color.WHITE);
						g.fillRect(topX+64, topY, 100, 200);
						g.setColor(Color.BLACK);
						g.drawString(inventory.getBoots().getDescription(), topX + 74, topY + 30);
					} catch (NullPointerException e) {

					}
				}
			} else if (topItemHighlight == 5) {
				g.drawRect(746, 132, l, l);
				if (handStack == null) {
					int topX = 746;
					int topY = 132;
					try {
						g.setColor(Color.WHITE);
						g.fillRect(topX+64, topY, 100, 200);
						g.setColor(Color.BLACK);
						g.drawString(inventory.getArms()[1].getDescription(), topX + 74, topY + 30);
					} catch (NullPointerException e) {

					}
				}
			} else if (topItemHighlight == 6) {
				g.drawRect(746, 132+72, l, l);
				if (handStack == null) {
					int topX = 746;
					int topY = 132+72;
					try {
						g.setColor(Color.WHITE);
						g.fillRect(topX+64, topY, 100, 200);
						g.setColor(Color.BLACK);
						g.drawString(inventory.getLegs()[0].getDescription(), topX + 74, topY + 30);
					} catch (NullPointerException e) {

					}
				}
			} else if (topItemHighlight == 7) {
				g.drawRect(892, 96, l, l);
				if (handStack == null) {
					int topX = 892;
					int topY = 96;
					try {
						g.setColor(Color.WHITE);
						g.fillRect(topX+64, topY, 100, 200);
						g.setColor(Color.BLACK);
						g.drawString(inventory.getRings()[0].getDescription(), topX + 74, topY + 30);
					} catch (NullPointerException e) {

					}
				}
			} else if (topItemHighlight == 8) {
				g.drawRect(892, 96+72, l, l);
				if (handStack == null) {
					int topX = 892;
					int topY = 96+72;
					try {
						g.setColor(Color.WHITE);
						g.fillRect(topX+64, topY, 100, 200);
						g.setColor(Color.BLACK);
						g.drawString(inventory.getRings()[1].getDescription(), topX + 74, topY + 30);
					} catch (NullPointerException e) {

					}
				}
			} else if (topItemHighlight == 9) {
				g.drawRect(892, 96+72*2, l, l);
				if (handStack == null) {
					int topX = 892;
					int topY = 96+72*2;
					try {
						g.setColor(Color.WHITE);
						g.fillRect(topX+64, topY, 100, 200);
						g.setColor(Color.BLACK);
						g.drawString(inventory.getRings()[2].getDescription(), topX + 74, topY + 30);
					} catch (NullPointerException e) {

					}
				}
			}
		}

		private void paintHandStack(Graphics g) {
			g.drawImage(handStack.getItem().getSprite(), mouseX-27, mouseY-27, 58,58, null);
			if (handStack.getStackAmount() > 1) {
				g.setColor(Color.BLACK);
				g.drawString(Integer.toString(handStack.getStackAmount()), mouseX+22, mouseY+21);
			}
		}

		private void determineItemHighlight() {
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

		private void determineTopItemHighlight() {
			int row0 = 602;
			int row1 = 674;
			int row2 = 746;
			int row3 = 892;
			if (mouseX>row0 && mouseX<row0+64) {
				if (mouseY>132 && mouseY<132+64) {
					topItemHighlight = 0;
					return;
				} else if (mouseY>132+72 && mouseY<132+72+64) {
					topItemHighlight = 1;
					return;
				}

			} else if (mouseX>row1 && mouseX<row1+64) {
				if (mouseY>96 && mouseY<96+64) {
					topItemHighlight = 2;
					return;
				} else if (mouseY>96+72 && mouseY<96+72+64) {
					topItemHighlight = 3;
					return;
				} else if (mouseY>96+72*2 && mouseY<96+72*2+64) {
					topItemHighlight = 4;
					return;
				}
			} else if (mouseX>row2 && mouseX<row2+64) {
				if (mouseY>132 && mouseY<132+64) {
					topItemHighlight = 5;
					return;
				} else if (mouseY>132+72 && mouseY<132+72+64) {
					topItemHighlight = 6;
					return;
				}
			} else if (mouseX>row3 && mouseX<row3+64) {
				if (mouseY>96 && mouseY<96+64) {
					topItemHighlight = 7;
					return;
				} else if (mouseY>96+72 && mouseY<96+72+64) {
					topItemHighlight = 8;
					return;
				} else if (mouseY>96+72*2 && mouseY<96+72*2+64) {
					topItemHighlight = 9;
					return;
				}
			}
			topItemHighlight = -1;
		}

		public void resetHandStack(){
			if (handStack != null) {
				inventory.add(handStack);
				handStack = null;
			}
		}

		private void determineItemClick(MouseEvent e) {
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
			if (checkX<0 || checkX > 648 || checkY<0) {
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
								mouseButton = 0;
								totalHandStackItems = handStack.getStackAmount();
								tempInventorySlots = 1;
								tempItem = handStack.getItem();
								updateTempInventory(index);
							} else if (e.getButton() == e.BUTTON3) {
								mouseButton = 1;
								totalHandStackItems = handStack.getStackAmount();
								tempInventorySlots = 1;
								tempItem = handStack.getItem();
								updateTempInventory(index);
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
								//This is fine to keep for now
								Stack temp = inventory.remove(index);
								inventory.add(handStack, index);
								handStack = temp;
							}
						}
					}
				}
			}
		}

		private void determineArmourClick(MouseEvent e) {
			int row0 = 602;
			int row1 = 674;
			int row2 = 746;
			int row3 = 892;
			if (x>row0 && x<row0+64) {
				if (y>132 && y<132+64) {
					if (inventory.getArms()[0] != null && handStack == null) {
						Stack temp = new Stack(1, inventory.getArms()[0]);
						handStack = temp;
						inventory.setLeftArm(null);
					} else if (handStack.getItem() instanceof Armour) {
						Armour temp = (Armour)handStack.getItem();
						if (temp.getLocation().equals("Arm")) {
							if (inventory.getArms()[0] != null) {
								handStack = new Stack(1, inventory.getArms()[0]);
							} else {
								handStack = null;
							}
							inventory.setLeftArm(temp);
						}
					}
				} else if (mouseY>132+72 && mouseY<132+72+64) {
					if (inventory.getLegs()[0] != null && handStack == null) {
						Stack temp = new Stack(1, inventory.getLegs()[0]);
						handStack = temp;
						inventory.setLeftLeg(null);
					} else if (handStack.getItem() instanceof Armour) {
						Armour temp = (Armour)handStack.getItem();
						if (temp.getLocation().equals("Leg")) {
							if (inventory.getLegs()[0] != null) {
								handStack = new Stack(1, inventory.getLegs()[0]);
							} else {
								handStack = null;
							}
							inventory.setLeftLeg(temp);
						}
					}
				}

			} else if (x>row1 && x<row1+64) {
				if (y>96 && y<96+64) {
					if (inventory.getHelmet() != null && handStack == null) {
						Stack temp = new Stack(1, inventory.getHelmet());
						handStack = temp;
						inventory.setHelmet(null);
					} else if (handStack.getItem() instanceof Armour) {
						Armour temp = (Armour)handStack.getItem();
						if (temp.getLocation().equals("Helmet")) {
							if (inventory.getHelmet() != null) {
								handStack = new Stack(1, inventory.getHelmet());
							} else {
								handStack = null;
							}
							inventory.setHelmet(temp);
						}
					}
				} else if (y>96+72 && y<96+72+64) {
					if (inventory.getArmour() != null && handStack == null) {
						Stack temp = new Stack(1, inventory.getArmour());
						handStack = temp;
						inventory.setArmour(null);
					} else if (handStack.getItem() instanceof Armour) {
						Armour temp = (Armour)handStack.getItem();
						if (temp.getLocation().equals("Armour")) {
							if (inventory.getArmour() != null) {
								handStack = new Stack(1, inventory.getArmour());
							} else {
								handStack = null;
							}
							inventory.setArmour(temp);
						}
					}
				} else if (y>96+72*2 && y<96+72*2+64) {
					if (inventory.getBoots() != null && handStack == null) {
						Stack temp = new Stack(1, inventory.getBoots());
						handStack = temp;
						inventory.setBoots(null);
					} else if (handStack.getItem() instanceof Armour) {
						Armour temp = (Armour)handStack.getItem();
						if (temp.getLocation().equals("Boots")) {
							if (inventory.getBoots() != null) {
								handStack = new Stack(1, inventory.getBoots());
							} else {
								handStack = null;
							}
							inventory.setBoots(temp);
						}
					}
				}
			} else if (x>row2 && x<row2+64) {
				if (y>132 && y<132+64) {
					if (inventory.getArms()[1] != null && handStack == null) {
						Stack temp = new Stack(1, inventory.getArms()[1]);
						handStack = temp;
						inventory.setRightArm(null);
					} else if (handStack.getItem() instanceof Armour) {
						Armour temp = (Armour)handStack.getItem();
						if (temp.getLocation().equals("Arm")) {
							if (inventory.getArms()[1] != null) {
								handStack = new Stack(1, inventory.getArms()[1]);
							} else {
								handStack = null;
							}
							inventory.setRightArm(temp);
						}
					}
				} else if (y>132+72 && y<132+72+64) {
					if (inventory.getLegs()[1] != null && handStack == null) {
						Stack temp = new Stack(1, inventory.getLegs()[1]);
						handStack = temp;
						inventory.setRightLeg(null);
					} else if (handStack.getItem() instanceof Armour) {
						Armour temp = (Armour)handStack.getItem();
						if (temp.getLocation().equals("Leg")) {
							if (inventory.getLegs()[1] != null) {
								handStack = new Stack(1, inventory.getLegs()[1]);
							} else {
								handStack = null;
							}
							inventory.setRightLeg(temp);
						}
					}
				}
			} else if (x>row3 && x<row3+64) {
				if (y>96 && y<96+64) {
					if (inventory.getRings()[0] != null && handStack == null) {
						Stack temp = new Stack(1, inventory.getRings()[0]);
						handStack = temp;
						inventory.setRing1(null);
					} else if (handStack.getItem() instanceof Armour) {
						Armour temp = (Armour)handStack.getItem();
						if (temp.getLocation().equals("Ring")) {
							if (inventory.getRings()[0] != null) {
								handStack = new Stack(1, inventory.getRings()[0]);
							} else {
								handStack = null;
							}
							inventory.setRing1(temp);
						}
					}
				} else if (y>96+72 && y<96+72+64) {
					if (inventory.getRings()[1] != null && handStack == null) {
						Stack temp = new Stack(1, inventory.getRings()[1]);
						handStack = temp;
						inventory.setRing2(null);
					} else if (handStack.getItem() instanceof Armour) {
						Armour temp = (Armour)handStack.getItem();
						if (temp.getLocation().equals("Ring")) {
							if (inventory.getRings()[1] != null) {
								handStack = new Stack(1, inventory.getRings()[1]);
							} else {
								handStack = null;
							}
							inventory.setRing2(temp);
						}
					}
				} else if (y>96+72*2 && y<96+72*2+64) {
					if (inventory.getRings()[2] != null && handStack == null) {
						Stack temp = new Stack(1, inventory.getRings()[2]);
						handStack = temp;
						inventory.setRing3(null);
					} else if (handStack.getItem() instanceof Armour) {
						Armour temp = (Armour)handStack.getItem();
						if (temp.getLocation().equals("Ring")) {
							if (inventory.getRings()[2] != null) {
								handStack = new Stack(1, inventory.getRings()[2]);
							} else {
								handStack = null;
							}
							inventory.setRing3(temp);
						}
					}
				}
			}
		}

		private void changeTempInventory() {
			int checkX = mouseX-320;
			int checkY;
			boolean fourthRow = false;
			if (mouseY<596) {
				if (mouseY > 572) {
					return;
				}
				checkY = mouseY - 364;
			} else if (mouseY < 660) {
				checkY = mouseY - 596;
				fourthRow = true;
			} else {
				return;
			}
			if (checkX<0 || checkY<0) {
				return;
			}
			if (checkX%72<64 && checkX<648 && checkY % 72 < 64) {
				int r;
				if (fourthRow) {
					r=3;
				} else {
					r = checkY / 72;
				}
				int c = checkX / 72;
				if (inventory.get(r*9+c) == null || inventory.get(r*9+c).getItem() == tempItem) {
					updateTempInventory(r*9+c);
				}
			}
		}

		private void updateTempInventory(int index) {
			int amountPerStack = 0;
			if (mouseButton == 0) {
				amountPerStack = totalHandStackItems / tempInventorySlots;
			} else if (mouseButton == 1) {
				amountPerStack = 1;
			}
			int itemAmountCounter = 0;
			for (int i = 0; i < tempInventory.length; i++) {
				if (tempInventory[i] != null) {
					if (inventory.get(i) != null) {
						if (inventory.get(i).getItem() == tempItem) {
							if (inventory.get(i).getStackAmount() + amountPerStack > inventory.get(i).getItem().getMaxStack()) {
								tempInventory[i] = new Stack(inventory.get(i).getItem().getMaxStack() - inventory.get(i).getStackAmount(), tempInventory[i].getItem());
								itemAmountCounter += tempInventory[i].getStackAmount();
							}
						}
					} else {
						tempInventory[i] = new Stack(amountPerStack, tempInventory[i].getItem());
						itemAmountCounter += tempInventory[i].getStackAmount();
					}
				}
			}
			tempInventory[index] = new Stack(amountPerStack, tempItem);
			itemAmountCounter += tempInventory[index].getStackAmount();
			if (itemAmountCounter < totalHandStackItems) {
				handStack = new Stack(totalHandStackItems - itemAmountCounter, tempItem);
			} else {
				handStack = null;
			}
		}

		private void transferTempInventory() {
			for (int i=0; i<tempInventory.length; i++) {
				if (tempInventory[i] != null) {
					inventory.add(tempInventory[i], i);
				}
			}
			tempInventory = new Stack[36];
		}

		private void resetInteractions() {
			resetHandStack();
			transferTempInventory();
			mouseButton = -1;
			tempItem = null;
			tempInventorySlots = 0;
			totalHandStackItems = 0;
		}

		private class PanelMouseListener implements MouseListener {
			public void mousePressed(MouseEvent e) {
				x = e.getX();
				y = e.getY();
				if (y>360) {
					if (mouseButton == -1) {
						determineItemClick(e);
					}
				} else if (e.getButton() == e.BUTTON1) {
					determineArmourClick(e);
				}
			}

			public void mouseEntered(MouseEvent e) {

			}

			public void mouseExited(MouseEvent e) {

			}

			public void mouseClicked(MouseEvent e) {
				System.out.println("Mouse pressed: " + e.getX() + " " + e.getY());
//				pressedX = e.getX();
//				pressedY = e.getY();
//				pressedEvent = e;
			}

			public void mouseReleased(MouseEvent e) {
				if (e.getButton()-1 == mouseButton) {
					mouseButton = -1;
					tempInventorySlots = 0;
					tempItem = null;
					totalHandStackItems = 0;
					transferTempInventory();
				}
			}
		}

		private class PanelMouseMotionListener implements MouseMotionListener {
			public void mouseDragged(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
				topItemHighlight = -1;
				highlightY = 0;
				highlightX = 0;
				if (mouseButton != -1) {
					changeTempInventory();
				}
			}

			public void mouseMoved(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();

				if (mouseY < 360) {
					highlightX = 0;
					highlightY = 0;
					determineTopItemHighlight();
				} else {
					topItemHighlight = -1;
					if (mouseButton == -1) {
						determineItemHighlight();
					}
				}
			}
		}
	}

	private class Listener implements KeyListener {

		//keyPressed has a buffer, please add something to keyreleased to fix this

		public void keyPressed(KeyEvent e) {
			if (currentPanel == worldPanel) {
				if (KeyEvent.getKeyText(e.getKeyCode()).equals("W")) {  //W
					playerHandler.keyPressed("W");
				}
				if (KeyEvent.getKeyText(e.getKeyCode()).equals("D")) {  //D
					playerHandler.keyPressed("D");
				}
				if (KeyEvent.getKeyText(e.getKeyCode()).equals("S")) {  //S
					playerHandler.keyPressed("S");
				}
				if (KeyEvent.getKeyText(e.getKeyCode()).equals("A")) {  //A
					playerHandler.keyPressed("A");
				}
			}
		}

		public void keyTyped(KeyEvent e) {}

		//Have a boolean list
		public void keyReleased(KeyEvent e) {
			if (KeyEvent.getKeyText(e.getKeyCode()).equals("E")) {
				if (currentPanel == worldPanel) {
					remove(worldPanel);
					add(inventoryMenu);
					currentPanel = inventoryMenu;
				} else if (currentPanel == inventoryMenu) {
					inventoryMenu.resetInteractions();
					remove(inventoryMenu);
					add(worldPanel);
					currentPanel = worldPanel;
				}
				repaint();
			}
			if (KeyEvent.getKeyText(e.getKeyCode()).equals("W")) {  //W
				playerHandler.keyReleased("W");
			}
			if (KeyEvent.getKeyText(e.getKeyCode()).equals("D")) {  //D
				playerHandler.keyReleased("D");
			}
			if (KeyEvent.getKeyText(e.getKeyCode()).equals("S")) {  //S
				playerHandler.keyReleased("S");
			}
			if (KeyEvent.getKeyText(e.getKeyCode()).equals("A")) {  //A
				playerHandler.keyReleased("A");
			}
		}
	}

}
