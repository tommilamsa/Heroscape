import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OverworldScreen extends JPanel
{
	final private JFrame frame;	//JFrame used for application
	private Player player;	//Player character
	private Location location;	//Current location
	private JDialog dialog;	//JDialog used for many menus
	private JDialog invDialog;	//JDialog used specifically for inventory
	private JList inventory;	//List of items in player's inventory
	private JLabel locationInfo;	//JLabel showing current location name
	private JTextArea playerInfo;	//JLabel showing player's name and level
	private JProgressBar healthBar;	//Player's healthbar
	private JProgressBar abilityBar;	//Player's AP bar
	private JButton menuButton;	//Button to open menu
	private JButton statsButton;	//Button to open Stats window
	private JButton helpButton;	//Button to open Help window
	private JButton exitButton;	//Button to exit back to main menu
	private JButton moveButton;	//Button to move to another location
	private JButton examineButton;	//Button to examine surroundings
	private JButton itemButton;	//Button for opening inventory
	private JButton cancelButton;	//Button for canceling inventory
	private JButton useButton;	//Button for using item
	private JButton itemExamineButton;	//Button for examining item
	private JTextArea textArea;	//Textarea used for location text
	
	//Creates window for menu
	private class MenuAction implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			UIManager.put("OptionPane.okButtonText", "Close Menu");
			dialog = null;
			JOptionPane optionPane = new JOptionPane();
			optionPane.setMessage(null);
			optionPane.setMessageType(JOptionPane.PLAIN_MESSAGE);
			
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(3,1));
				
			statsButton = new JButton("Stats");
			statsButton.setFont(new Font("", Font.PLAIN, 25));
			statsButton.addActionListener(new StatsAction());
			panel.add(statsButton);
				
			helpButton = new JButton("Help");
			helpButton.setFont(new Font("", Font.PLAIN, 25));
			helpButton.addActionListener(new HelpAction());
			panel.add(helpButton);
				
			exitButton = new JButton("Back to Main Menu");
			exitButton.setFont(new Font("", Font.PLAIN, 25));
			exitButton.addActionListener(new ExitAction());
			panel.add(exitButton);
				
			optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
			optionPane.add(panel,1);
			dialog = optionPane.createDialog(frame, "Menu");
			dialog.setVisible(true);
		}
	}
	
	//Creates Stats window
	private class StatsAction implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			dialog.dispose();
			String statsMessage = "Character Statictics:\n\nName: " + player.getName() + "\nLevel: " + player.getLevel() + "\nXP: " + player.getXP() + "/" + player.getMaxXP() + "\nBase Attack: " + player.getAttack() + "\nWeapon: " + player.getWeapon().getName();
			UIManager.put("OptionPane.okButtonText", "Close");
			UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
			JOptionPane.showMessageDialog(frame, statsMessage, "Statictics", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	//Creates Help window
	private class HelpAction implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			dialog.dispose();
			String helpMessage = "How to play:\n\nMove - Select new location to move to\n\nExamine - Investigate surroundings\n\nItem - Use and examine items or equip new weapons";
			UIManager.put("OptionPane.okButtonText", "Close");
			UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
			JOptionPane.showMessageDialog(frame, helpMessage, "Help", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	//Creates confirmation window asking the user if they want to return to main menu
	private class ExitAction implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			dialog.dispose();
			int input = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit the game?", "Are you sure?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if(input == 0)
			{
				frame.setContentPane(new MainMenu(frame));
				frame.revalidate();
			}
		}
	}
	
	//Creates window with possible locations to travel to
	private class MoveAction implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			UIManager.put("OptionPane.okButtonText", "Cancel");
			dialog = null;
			JOptionPane optionPane = new JOptionPane();
			optionPane.setMessage("Choose location to move to");
			optionPane.setMessageType(JOptionPane.PLAIN_MESSAGE);
			
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(3,1));
			
			String[] locations = location.getButtons();
			
			for(int i=0; i < locations.length; i++)
			{
				String areaName = locations[i];
				
				if(areaName == "Mountains")
				{
					JButton A1Button = new JButton("Mountains");
					A1Button.setFont(new Font("", Font.PLAIN, 25));
					A1Button.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e)
						{
							dialog.dispose();
							A1 mountains = new A1(player);
							frame.setContentPane(new BattleScreen2(frame, player, mountains));
							frame.revalidate();
						}
					});
					panel.add(A1Button);
				}
				else if(areaName == "Desert")
				{
					JButton A2Button = new JButton("Desert");
					A2Button.setFont(new Font("", Font.PLAIN, 25));
					A2Button.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e)
						{
							dialog.dispose();
							A2 desert = new A2(player);
							frame.setContentPane(new BattleScreen2(frame, player, desert));
							frame.revalidate();
						}
					});
					panel.add(A2Button);
				}
				else if(areaName == "Beach")
				{
					JButton A3Button = new JButton("Beach");
					A3Button.setFont(new Font("", Font.PLAIN, 25));
					A3Button.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e)
						{
							dialog.dispose();
							A3 beach = new A3(player);
							frame.setContentPane(new BattleScreen2(frame, player, beach));
							frame.revalidate();
						}
					});
					panel.add(A3Button);
				}
				else if(areaName == "Deep Woods")
				{
					JButton B1Button = new JButton("Deep Woods");
					B1Button.setFont(new Font("", Font.PLAIN, 25));
					B1Button.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e)
						{
							dialog.dispose();
							B1 woods = new B1();
							frame.setContentPane(new BattleScreen2(frame, player, woods));
							frame.revalidate();
						}
					});
					panel.add(B1Button);
				}
				else if(areaName == "Swamp")
				{
					JButton B2Button = new JButton("Swamp");
					B2Button.setFont(new Font("", Font.PLAIN, 25));
					B2Button.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e)
						{
							dialog.dispose();
							B2 swamp = new B2();
							frame.setContentPane(new BattleScreen1(frame, player, swamp));
							frame.revalidate();
						}
					});
					panel.add(B2Button);
				}
				else if(areaName == "River")
				{
					JButton B3Button = new JButton("River");
					B3Button.setFont(new Font("", Font.PLAIN, 25));
					B3Button.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e)
						{
							dialog.dispose();
							B3 river = new B3(player);
							frame.setContentPane(new BattleScreen2(frame, player, river));
							frame.revalidate();
						}
					});
					panel.add(B3Button);
				}
				else if(areaName == "Forest")
				{
					JButton C1Button = new JButton("Forest");
					C1Button.setFont(new Font("", Font.PLAIN, 25));
					C1Button.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e)
						{
							dialog.dispose();
							C1 forest = new C1();
							frame.setContentPane(new BattleScreen1(frame, player, forest));
							frame.revalidate();
						}
					});
					panel.add(C1Button);
				}
				else if(areaName == "Plains")
				{
					JButton C2Button = new JButton("Plains");
					C2Button.setFont(new Font("", Font.PLAIN, 25));
					C2Button.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e)
						{
							dialog.dispose();
							C2 plains = new C2(player);
							frame.setContentPane(new BattleScreen1(frame, player, plains));
							frame.revalidate();
						}
					});
					panel.add(C2Button);
				}
				else if(areaName == "Lake")
				{
					JButton C3Button = new JButton("Lake");
					C3Button.setFont(new Font("", Font.PLAIN, 25));
					C3Button.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e)
						{
							dialog.dispose();
							C3 lake = new C3(player);
							frame.setContentPane(new BattleScreen1(frame, player, lake));
							frame.revalidate();
						}
					});
					panel.add(C3Button);
				}
			}
			
			optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
			optionPane.add(panel,1);
			dialog = optionPane.createDialog(frame, "Choose location");
			dialog.setVisible(true);
		}
	}
	
	//Sets text to location's examine-text
	private class ExamineAction implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			location.examine(textArea);
		}
	}
	
	//Creates inventory window
	private class ItemAction implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			invDialog = new JDialog(frame, "Inventory");
			
			cancelButton = new JButton("Cancel");
			cancelButton.addActionListener(new CancelAction());
			
			useButton = new JButton("Use");
			useButton.addActionListener(new UseAction());
			
			itemExamineButton = new JButton("Examine");
			itemExamineButton.addActionListener(new ItemExamineAction());
			
			inventory = new JList<Object>(player.getInventory());
			inventory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			inventory.setLayoutOrientation(JList.VERTICAL);
			inventory.setVisibleRowCount(-1);
			
			JScrollPane scroller = new JScrollPane(inventory);
			scroller.setPreferredSize(new Dimension(200,150));
			scroller.setAlignmentX(LEFT_ALIGNMENT);
			
			JPanel inventoryPanel = new JPanel();
			inventoryPanel.setLayout(new BoxLayout(inventoryPanel, BoxLayout.PAGE_AXIS));
			
			JLabel label = new JLabel("Inventory");
			label.setLabelFor(inventory);
			inventoryPanel.add(label);
			inventoryPanel.add(Box.createRigidArea(new Dimension(0,5)));
			inventoryPanel.add(scroller);
			
			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
			buttonPanel.add(Box.createHorizontalGlue());
			buttonPanel.add(useButton);
			buttonPanel.add(Box.createRigidArea(new Dimension(10,0)));
			buttonPanel.add(itemExamineButton);
			buttonPanel.add(Box.createRigidArea(new Dimension(10,0)));
			buttonPanel.add(cancelButton);
			
			Container contentPane = invDialog.getContentPane();
			contentPane.add(inventoryPanel, BorderLayout.CENTER);
			contentPane.add(buttonPanel, BorderLayout.PAGE_END);
			
			invDialog.pack();
			invDialog.setLocationRelativeTo(frame);
			invDialog.setModal(true);
			invDialog.setVisible(true);
		}
	}
	
	//Exits the inventory
	private class CancelAction implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			invDialog.dispose();
		}
	}
	
	//Uses selected item
	private class UseAction implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String item = (String)inventory.getSelectedValue();
			
			if(item == "HP Potion")
			{
				HpPotion potion = (HpPotion)player.getItem(item);
				potion.use(player, healthBar);
			}
			else if(item == "AP Potion")
			{
				ApPotion potion = (ApPotion)player.getItem(item);
				potion.use(player, abilityBar);
			}
			else if(item == "Sword")
			{
				Sword sword = (Sword)player.getItem(item);
				sword.use(player, textArea);
			}
			else if(item == "Greatsword")
			{
				GreatSword sword = (GreatSword)player.getItem(item);
				sword.use(player, textArea);
			}
			else if(item == "Magic Sword")
			{
				MagicSword sword = (MagicSword)player.getItem(item);
				sword.use(player, textArea);
			}
			invDialog.dispose();
		}
	}
	
	//Examines selected item
	private class ItemExamineAction implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			String item = (String)inventory.getSelectedValue();
			
			if(item == "HP Potion")
			{
				HpPotion potion = (HpPotion)player.getItem(item);
				potion.examine(frame);
			}
			else if(item == "AP Potion")
			{
				ApPotion potion = (ApPotion)player.getItem(item);
				potion.examine(frame);
			}
			else if(item == "Sword")
			{
				Sword sword = (Sword)player.getItem(item);
				sword.examine(frame);
			}
			else if(item == "Greatsword")
			{
				GreatSword sword = (GreatSword)player.getItem(item);
				sword.examine(frame);
			}
			else if(item == "Magic Sword")
			{
				MagicSword sword = (MagicSword)player.getItem(item);
				sword.examine(frame);
			}
		}
	}
	
	//Constructor for overworld screen
	public OverworldScreen(JFrame parent, Player character, Location area)
	{
		this.frame = parent;
		this.player = character;
		this.location = area;
		setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(1250,800));
		GridBagConstraints c = new GridBagConstraints();
		
		if(player.getStart() == true)
		{
			UIManager.put("OptionPane.okButtonText", "OK");
			UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
			String tutorialMessage = "Welcome to Heroscape RPG! Here are the controls to get you started:\n\nMove - Select new location to move to\n\nExamine - Investigate surroundings\n\nItem - Use and examine items or equip new weapons";
			JOptionPane.showMessageDialog(frame, tutorialMessage, "Tutorial", JOptionPane.PLAIN_MESSAGE);
			player.addItem(new HpPotion());
			player.addItem(new HpPotion());
			player.addItem(new ApPotion());
			player.addItem(new ApPotion());
		}
		
		locationInfo = new JLabel("Current Location: " + location.getName(), SwingConstants.CENTER);
		locationInfo.setFont(new Font("", Font.PLAIN, 25));
		locationInfo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		locationInfo.setOpaque(true);
		locationInfo.setBackground(Color.WHITE);
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(20,20,20,20);
		c.gridheight = 1;
		c.gridx = 0;
		c.gridy = 1;
		add(locationInfo, c);
		
		playerInfo = new JTextArea(player.getName() + " Lvl " + player.getLevel() + "\nXP: " + player.getXP() + "/" + player.getMaxXP());
		playerInfo.setEditable(false);
		playerInfo.setFont(new Font("", Font.PLAIN, 40));
		playerInfo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		playerInfo.setBackground(Color.WHITE);
		playerInfo.setPreferredSize(locationInfo.getPreferredSize());
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(0,20,60,20);
		c.gridheight = 1;
		c.gridx = 0;
		c.gridy = 3;
		add(playerInfo, c);
		
		healthBar = new JProgressBar(0,player.getMaxHP());
		healthBar.setStringPainted(true);
		healthBar.setForeground(Color.RED);
		healthBar.setBackground(Color.BLACK);
		healthBar.setString("Health " + player.getHP() + "/" + player.getMaxHP());
		healthBar.setValue(player.getHP());
		healthBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		add(healthBar, c);
		
		abilityBar = new JProgressBar(0,player.getMaxAP());
		abilityBar.setStringPainted(true);
		abilityBar.setForeground(Color.BLUE);
		abilityBar.setBackground(Color.BLACK);
		abilityBar.setString("AP " + player.getAP() + "/" + player.getMaxAP());
		abilityBar.setValue(player.getAP());
		abilityBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 5;
		add(abilityBar, c);
		
		
		menuButton = new JButton("Menu");
		menuButton.setFont(new Font("", Font.PLAIN, 25));
		menuButton.addActionListener(new MenuAction());
		menuButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		c.weightx = 1;
		c.weighty = 0.5;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(20,25,25,25);
		c.gridx = 0;
		c.gridy = 6;
		add(menuButton, c);
		
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 6;
		add(Box.createHorizontalStrut(10), c);
		
		moveButton = new JButton("Move");
		moveButton.setFont(new Font("", Font.PLAIN, 25));
		moveButton.addActionListener(new MoveAction());
		moveButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(20,25,25,25);
		c.gridx = 2;
		c.gridy = 6;
		add(moveButton, c);
		
		examineButton = new JButton("Examine");
		examineButton.setFont(new Font("", Font.PLAIN, 25));
		examineButton.addActionListener(new ExamineAction());
		examineButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(20,25,25,25);
		c.gridx = 3;
		c.gridy = 6;
		add(examineButton, c);
		
		itemButton = new JButton("Item");
		itemButton.setFont(new Font("", Font.PLAIN, 25));
		itemButton.addActionListener(new ItemAction());
		itemButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(20,25,25,25);
		c.gridx = 4;
		c.gridy = 6;
		add(itemButton, c);
		
		textArea = new JTextArea(location.getText());
		textArea.setEditable(false);
		textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		textArea.setFont(new Font("", Font.PLAIN, 35));
		textArea.setPreferredSize(new Dimension(4,5));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 4;
		c.gridheight = 6;
		c.gridx = 1;
		c.gridy = 0;
		add(textArea, c);
		
		
		if(location.getName() == "Mountains")
		{
			setBackground(Color.LIGHT_GRAY);
		}
		else if(location.getName() == "Desert")
		{
			setBackground(Color.ORANGE);
		}
		else if(location.getName() == "Beach" || location.getName() == "River" || location.getName() == "Lake")
		{
			setBackground(Color.CYAN);
		}
		else if(location.getName() == "Deep Woods" || location.getName() == "Forest" || location.getName() == "Plains")
		{
			setBackground(Color.GREEN);
		}
		else if(location.getName() == "Swamp")
		{
			setBackground(Color.GRAY);
		}
	}
}