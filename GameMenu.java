import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameMenu {
  
  private static JFrame mainFrame;
  
  // Declare JPanels
  private JPanel optionPanel;
  private JPanel serverPanel;
  private JPanel ipPanel;
  private JPanel portPanel;
  
  private JButton playButton;
  private JButton instructionButton;
  private JButton quitButton;
  private static JButton backButton;
  private JButton okButton;
  
  private ActionListener eventListener;
  
  public GameMenu() {
    
    //Create main frame
    mainFrame = new JFrame("Start Screen");
    mainFrame.setSize(1000, 650);
    mainFrame.setResizable(false);
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    optionPanel = new JPanel();
    optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
    
    serverPanel = new JPanel();
    serverPanel.setLayout(new BoxLayout(serverPanel, BoxLayout.Y_AXIS));
    
    ipPanel = new JPanel();
    portPanel = new JPanel();
        
    //Create JButtons
    playButton = new JButton();
    instructionButton = new JButton();
    quitButton = new JButton();
    backButton = new JButton();
    try {
    playButton.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("Assets/Play.png"))));
    instructionButton.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("Assets/Instructions.png"))));
    quitButton.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("Assets/Quit.png"))));
    backButton.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("Assets/Back.png"))));
    }catch (IOException e) {
    }

    //okButton = new JButton("Connect To Server");
    
    eventListener = new EventListener();
    
    playButton.addActionListener(eventListener);
    instructionButton.addActionListener(eventListener);
    quitButton.addActionListener(eventListener);
    backButton.addActionListener(eventListener);
    //okButton.addActionListener(eventListener);
    
    ipPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
    portPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
    
    serverPanel.add(ipPanel);
    serverPanel.add(Box.createVerticalStrut(10)); //Creates space between components
    serverPanel.add(portPanel);
    serverPanel.add(Box.createVerticalStrut(20));
    //okButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
    //serverPanel.add(okButton);
    //serverPanel.add(Box.createVerticalStrut(40));
    
    //Add buttons to panels
    playButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
    instructionButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
    quitButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
    
    optionPanel.add(playButton);
    optionPanel.add(Box.createVerticalStrut(10));
    optionPanel.add(instructionButton);
    optionPanel.add(Box.createVerticalStrut(10));
    optionPanel.add(quitButton);
    
    mainFrame.add(optionPanel);
    
    //ItemList.initialize();
    
    mainFrame.setVisible(true);
  }
  
  public class EventListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      
      if (e.getSource() == playButton) {
        mainFrame.getContentPane().removeAll();
        mainFrame.setTitle("Find Server");
        mainFrame.add(serverPanel);
        mainFrame.add(backButton);
        mainFrame.repaint();
      
    }
  }
  
}
}