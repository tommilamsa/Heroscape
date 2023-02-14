import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class BattleScreen2 extends JPanel
{
	final private JFrame frame;	//JFrame used for application
	private Player player;	//Player character
	private Location location;	//Location to move to after defeating enemies
	private Enemy enemy1;	//Enemy the player has to fight
	private Enemy enemy2;	//Enemy the player has to fight	
	private boolean playerTurn;	//Boolean determining whether it is the players turn to fight or the enemy's turn
	private JDialog dialog;	//JDialog used for many menus
	private JDialog invDialog;	//JDialog used specifically for inventory
	private JList inventory;	//List of items in player's inventory
	private JLabel playerInfo;	//JLabel showing player's name and level
	private JProgressBar playerHealthBar;	//Player's healthbar
	private JProgressBar abilityBar;	//Player's AP bar
	private JLabel enemy1Info;	//JLabel showing enemy's name and level
	private JProgressBar enemy1HealthBar;	//Enemy's healthbar
	private JLabel enemy2Info;	//JLabel showing enemy's name and level
	private JProgressBar enemy2HealthBar;	//Enemy's healthbar
	private JTextArea textArea;	//Textarea used for battle information
	private JButton menuButton;	//Button to open menu
	private JButton statsButton;	//Button to open Stats window
	private JButton helpButton;	//Button to open Help window
	private JButton exitButton;	//Button to exit back to main menu
	private JButton fightButton;	//Button to open the enemy selection menu
	private JButton enemy1Button;	//Button for choosing first enemy to fight
	private JButton enemy2Button;	//Button for choosing second enemy to fight
	private JButton abilityButton;	//Button for opening ability selection menu
	private JButton ability1Button;	//Button for selecting the Super Slash -ability
	private JButton ability2Button;	//Button for selecting the Blitz -ability
	private JButton ability3Button;	//Button for selecting the Magic Beam -ability
	private JButton itemButton;	//Button for opening inventory
	private JButton cancelButton;	//Button for canceling inventory
	private JButton useButton;	//Button for using item
	private JButton examineButton;	//Button for examining item
	private JButton runButton;	//Button for attempting escape from fight
	
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
			String helpMessage = "How to play:\n\nFight - Attack enemy with current weapon\n\nAbility - Attack enemy with special ability (Consumes AP)\n\nItem - Use items to restore HP and AP\n\nRun - Attempt to run from the battle";
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
	
	//Creates enemy selection window
	private class FightAction implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == fightButton)
			{
				UIManager.put("OptionPane.okButtonText", "Cancel");
				dialog = null;
				JOptionPane optionPane = new JOptionPane();
				optionPane.setMessage("Choose enemy to attack");
				optionPane.setMessageType(JOptionPane.PLAIN_MESSAGE);
				
				JPanel panel = new JPanel();
				panel.setLayout(new GridLayout(3,1));
				
				if(enemy1.getHP() > 0)
				{
					enemy1Button = new JButton(enemy1.getName());
					enemy1Button.setFont(new Font("", Font.PLAIN, 25));
					enemy1Button.addActionListener(new EnemySelectAction());
					panel.add(enemy1Button);
				}
				
				if(enemy2.getHP() > 0)
				{
					enemy2Button = new JButton(enemy2.getName());
					enemy2Button.setFont(new Font("", Font.PLAIN, 25));
					enemy2Button.addActionListener(new EnemySelectAction());
					panel.add(enemy2Button);
				}
				
				optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
				optionPane.add(panel,1);
				dialog = optionPane.createDialog(frame, "Choose enemy");
				dialog.setVisible(true);
			}
			else if(e.getSource() == ability1Button)
			{
				dialog.dispose();
				UIManager.put("OptionPane.okButtonText", "Cancel");
				dialog = null;
				JOptionPane optionPane = new JOptionPane();
				optionPane.setMessage("Choose enemy to attack");
				optionPane.setMessageType(JOptionPane.PLAIN_MESSAGE);
				
				JPanel panel = new JPanel();
				panel.setLayout(new GridLayout(3,1));
				
				if(enemy1.getHP() > 0)
				{
					enemy1Button = new JButton(enemy1.getName());
					enemy1Button.setFont(new Font("", Font.PLAIN, 25));
					enemy1Button.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e)
						{
							dialog.dispose();
							if(player.getAP() >= 25)
							{
								superSlash(player, enemy1, textArea, enemy1HealthBar, abilityBar, enemy2);
								
								if(enemy1.getHP() > 0)
								{
									enemyAttack(player, enemy1, textArea, playerHealthBar);
								}
					
								if(enemy2.getHP() > 0)
								{
									enemyAttack(player, enemy2, textArea, playerHealthBar);
								}
							}
							else
							{
								UIManager.put("OptionPane.okButtonText", "OK");
								UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
								JOptionPane.showMessageDialog(frame, "You don't have enough AP!", "Not enough AP", JOptionPane.PLAIN_MESSAGE);
							}
						}
					});
					panel.add(enemy1Button);
				}
				
				if(enemy2.getHP() > 0)
				{
					enemy2Button = new JButton(enemy2.getName());
					enemy2Button.setFont(new Font("", Font.PLAIN, 25));
					enemy2Button.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e)
						{
							dialog.dispose();
							if(player.getAP() >= 25)
							{
								superSlash(player, enemy2, textArea, enemy2HealthBar, abilityBar, enemy1);
								
								if(enemy1.getHP() > 0)
								{
									enemyAttack(player, enemy1, textArea, playerHealthBar);
								}
					
								if(enemy2.getHP() > 0)
								{
									enemyAttack(player, enemy2, textArea, playerHealthBar);
								}
							}
							else
							{
								UIManager.put("OptionPane.okButtonText", "OK");
								UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
								JOptionPane.showMessageDialog(frame, "You don't have enough AP!", "Not enough AP", JOptionPane.PLAIN_MESSAGE);
							}
						}
					});
					panel.add(enemy2Button);
				}
				
				optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
				optionPane.add(panel,1);
				dialog = optionPane.createDialog(frame, "Choose enemy");
				dialog.setVisible(true);
			}
			else if(e.getSource() == ability3Button)
			{
				dialog.dispose();
				UIManager.put("OptionPane.okButtonText", "Cancel");
				dialog = null;
				JOptionPane optionPane = new JOptionPane();
				optionPane.setMessage("Choose enemy to attack");
				optionPane.setMessageType(JOptionPane.PLAIN_MESSAGE);
				
				JPanel panel = new JPanel();
				panel.setLayout(new GridLayout(3,1));
				
				if(enemy1.getHP() > 0)
				{
					enemy1Button = new JButton(enemy1.getName());
					enemy1Button.setFont(new Font("", Font.PLAIN, 25));
					enemy1Button.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e)
						{
							dialog.dispose();
							if(player.getAP() >= 75)
							{
								magicBeam(player, enemy1, textArea, enemy1HealthBar, abilityBar, enemy2);
								
								if(enemy1.getHP() > 0)
								{
									enemyAttack(player, enemy1, textArea, playerHealthBar);
								}
					
								if(enemy2.getHP() > 0)
								{
									enemyAttack(player, enemy2, textArea, playerHealthBar);
								}
							}
							else
							{
								UIManager.put("OptionPane.okButtonText", "OK");
								UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
								JOptionPane.showMessageDialog(frame, "You don't have enough AP!", "Not enough AP", JOptionPane.PLAIN_MESSAGE);
							}
						}
					});
					panel.add(enemy1Button);
				}
				
				if(enemy2.getHP() > 0)
				{
					enemy2Button = new JButton(enemy2.getName());
					enemy2Button.setFont(new Font("", Font.PLAIN, 25));
					enemy2Button.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e)
						{
							dialog.dispose();
							if(player.getAP() >= 75)
							{
								magicBeam(player, enemy2, textArea, enemy2HealthBar, abilityBar, enemy1);
								
								if(enemy1.getHP() > 0)
								{
									enemyAttack(player, enemy1, textArea, playerHealthBar);
								}
					
								if(enemy2.getHP() > 0)
								{
									enemyAttack(player, enemy2, textArea, playerHealthBar);
								}
							}
							else
							{
								UIManager.put("OptionPane.okButtonText", "OK");
								UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
								JOptionPane.showMessageDialog(frame, "You don't have enough AP!", "Not enough AP", JOptionPane.PLAIN_MESSAGE);
							}
						}
					});
					panel.add(enemy2Button);
				}
				
				optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
				optionPane.add(panel,1);
				dialog = optionPane.createDialog(frame, "Choose enemy");
				dialog.setVisible(true);
			}
		}
	}
	
	//Attacking selected enemy
	private class EnemySelectAction implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			dialog.dispose();
			if(playerTurn == true)
			{
				if(e.getSource() == enemy1Button)
				{
					playerAttack(player, enemy1, textArea, enemy1HealthBar, enemy2);
				
					if(enemy1.getHP() > 0)
					{
						enemyAttack(player, enemy1, textArea, playerHealthBar);
					}
					
					if(enemy2.getHP() > 0)
					{
						enemyAttack(player, enemy2, textArea, playerHealthBar);
					}
				}
				else if(e.getSource() == enemy2Button)
				{
					playerAttack(player, enemy2, textArea, enemy2HealthBar, enemy1);
				
					if(enemy1.getHP() > 0)
					{
						enemyAttack(player, enemy1, textArea, playerHealthBar);
					}
					
					if(enemy2.getHP() > 0)
					{
						enemyAttack(player, enemy2, textArea, playerHealthBar);
					}
				}
			}
		}
	}
	
	//Creates ability selection window
	private class AbilityAction implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			UIManager.put("OptionPane.okButtonText", "Cancel");
			dialog = null;
			JOptionPane optionPane = new JOptionPane();
			optionPane.setMessage("Choose ability to use");
			optionPane.setMessageType(JOptionPane.PLAIN_MESSAGE);
			
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(3,1));
			
			ability1Button = new JButton("Super Slash (25 AP)");
			ability1Button.setFont(new Font("", Font.PLAIN, 25));
			ability1Button.addActionListener(new FightAction());
			panel.add(ability1Button);
			
			if(player.getBlitz() == true)
			{
				ability2Button = new JButton("Blitz (20 AP)");
				ability2Button.setFont(new Font("", Font.PLAIN, 25));
				ability2Button.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e)
					{
						dialog.dispose();
						if(player.getAP() >= 20)
						{
							blitz(player, enemy1, enemy2, textArea, enemy1HealthBar, enemy2HealthBar, abilityBar);
							
							if(enemy1.getHP() > 0)
							{
								enemyAttack(player, enemy1, textArea, playerHealthBar);
							}
							
							if(enemy2.getHP() > 0)
							{
								enemyAttack(player, enemy2, textArea, playerHealthBar);
							}
						}
						else
						{
							UIManager.put("OptionPane.okButtonText", "OK");
							UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, "You don't have enough AP!", "Not enough AP", JOptionPane.PLAIN_MESSAGE);
						}
					}
				});
				panel.add(ability2Button);
			}
			
			if(player.getWeapon().getName() == "Magic Sword")
			{
				ability3Button = new JButton("Magic Beam (75 AP)");
				ability3Button.setFont(new Font("", Font.PLAIN, 25));
				ability3Button.addActionListener(new FightAction());
				panel.add(ability3Button);
			}
			
			optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
			optionPane.add(panel,1);
			dialog = optionPane.createDialog(frame, "Choose ability");
			dialog.setVisible(true);
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
			
			examineButton = new JButton("Examine");
			examineButton.addActionListener(new ExamineAction());
			
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
			buttonPanel.add(examineButton);
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
				potion.use(player, playerHealthBar);
			}
			else if(item == "AP Potion")
			{
				ApPotion potion = (ApPotion)player.getItem(item);
				potion.use(player, abilityBar);
			}
			else if(item == "Sword")
			{
				UIManager.put("OptionPane.okButtonText", "Close");
				UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
				JOptionPane.showMessageDialog(frame, "You cannot change weapons while in battle!", "Cannot change weapons", JOptionPane.PLAIN_MESSAGE);
			}
			else if(item == "Greatsword")
			{
				UIManager.put("OptionPane.okButtonText", "Close");
				UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
				JOptionPane.showMessageDialog(frame, "You cannot change weapons while in battle!", "Cannot change weapons", JOptionPane.PLAIN_MESSAGE);
			}
			else if(item == "Magic Sword")
			{
				UIManager.put("OptionPane.okButtonText", "Close");
				UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
				JOptionPane.showMessageDialog(frame, "You cannot change weapons while in battle!", "Cannot change weapons", JOptionPane.PLAIN_MESSAGE);
			}
			invDialog.dispose();
		}
	}
	
	//Examines selected item
	private class ExamineAction implements ActionListener
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
	
	//Calculates success of escaping from battle. Proceeds to next OverworldScreen if successful
	private class RunAction implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			boolean runSuccess;
			int runChance;
			
			if(player.getLevel() >= (((enemy1.getLevel() + enemy2.getLevel()) / 2) + 2))
			{
				runSuccess = true;
			}
			else if(player.getLevel() == (((enemy1.getLevel() + enemy2.getLevel()) / 2) + 1))
			{
				runChance = (int)(Math.random() * ((4-1) + 1)) + 1;
				if(runChance == 2 || runChance == 3 || runChance == 4)
					runSuccess = true;
				else
					runSuccess = false;
			}
			else if(player.getLevel() == ((enemy1.getLevel() + enemy2.getLevel()) / 2))
			{
				runChance = (int)(Math.random() * ((4-1) + 1)) + 1;
				if(runChance == 3 || runChance == 4)
					runSuccess = true;
				else 
					runSuccess = false;
			}
			else if(player.getLevel() == (((enemy1.getLevel() + enemy2.getLevel()) / 2) - 1))
			{
				runChance = (int)(Math.random() * ((4-1) + 1)) + 1;
				if(runChance == 4)
					runSuccess = true;
				else
					runSuccess = false;
			}
			else
			{
				runSuccess = false;
			}
			
			if(runSuccess == true)
			{
				UIManager.put("OptionPane.okButtonText", "OK");
				UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
				JOptionPane.showMessageDialog(frame, "You successfully escaped!", "Escape successful", JOptionPane.PLAIN_MESSAGE);
				frame.setContentPane(new OverworldScreen(frame, player, location));
				frame.revalidate();
			}
			else
			{
				UIManager.put("OptionPane.okButtonText", "OK");
				UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
				JOptionPane.showMessageDialog(frame, "You didn't manage to escape!", "Escape failed", JOptionPane.PLAIN_MESSAGE);
				if(enemy1.getHP() > 0)
				{
					enemyAttack(player, enemy1, textArea, playerHealthBar);
				}
				
				if(enemy2.getHP() > 0)
				{
					enemyAttack(player, enemy2, textArea, playerHealthBar);
				}
			}
		}
	}
	
	//Constructor for battle screen with two enemies
	public BattleScreen2(JFrame parent, Player character, Location area)
	{
		playerTurn = true;
		this.player = character;
		this.location = area;
		
		enemy1 = enemy1Select();
		enemy2 = enemy2Select();
		
		this.frame = parent;
		setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(1250,800));
		GridBagConstraints c = new GridBagConstraints();
		
		playerInfo = new JLabel(player.getName() + " (Lvl " + player.getLevel() + ")", SwingConstants.CENTER);
		playerInfo.setFont(new Font("", Font.BOLD, 25));
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(20,20,20,20);
		c.gridx = 0;
		c.gridy = 0;
		add(playerInfo, c);
		
		playerHealthBar = new JProgressBar(0,player.getMaxHP());
		playerHealthBar.setStringPainted(true);
		playerHealthBar.setForeground(Color.RED);
		playerHealthBar.setBackground(Color.BLACK);
		playerHealthBar.setString("Health " + player.getHP() + "/" + player.getMaxHP());
		playerHealthBar.setValue(player.getHP());
		playerHealthBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,25,0,25);
		c.gridx = 0;
		c.gridy = 1;
		add(playerHealthBar, c);
		
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
		c.gridy = 2;
		add(abilityBar, c);
		
		enemy1Info = new JLabel(enemy1.getName() + " (Lvl " + enemy1.getLevel() + ")", SwingConstants.CENTER);
		enemy1Info.setFont(new Font("", Font.BOLD, 25));
		enemy1Info.setPreferredSize(playerInfo.getPreferredSize());
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(20,20,20,20);
		c.gridwidth = 1;
		c.gridheight = 1;
		c.gridx = 4;
		c.gridy = 0;
		add(enemy1Info, c);
		
		enemy1HealthBar = new JProgressBar(0,enemy1.getMaxHP());
		enemy1HealthBar.setStringPainted(true);
		enemy1HealthBar.setForeground(Color.RED);
		enemy1HealthBar.setBackground(Color.BLACK);
		enemy1HealthBar.setString("Health " + enemy1.getHP() + "/" + enemy1.getMaxHP());
		enemy1HealthBar.setValue(enemy1.getHP());
		enemy1HealthBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 1;
		add(enemy1HealthBar, c);
		
		enemy2Info = new JLabel(enemy2.getName() + " (Lvl " + enemy2.getLevel() + ")", SwingConstants.CENTER);
		enemy2Info.setFont(new Font("", Font.BOLD, 25));
		enemy2Info.setPreferredSize(playerInfo.getPreferredSize());
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(20,20,20,20);
		c.gridwidth = 1;
		c.gridheight = 1;
		c.gridx = 4;
		c.gridy = 2;
		add(enemy2Info, c);
		
		enemy2HealthBar = new JProgressBar(0,enemy2.getMaxHP());
		enemy2HealthBar.setStringPainted(true);
		enemy2HealthBar.setForeground(Color.RED);
		enemy2HealthBar.setBackground(Color.BLACK);
		enemy2HealthBar.setString("Health " + enemy2.getHP() + "/" + enemy2.getMaxHP());
		enemy2HealthBar.setValue(enemy2.getHP());
		enemy2HealthBar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 3;
		add(enemy2HealthBar, c);
		
		textArea = new JTextArea("You encounter " + enemy1.getName() + " and " + enemy2.getName() + "!");
		textArea.setEditable(false);
		textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		textArea.setFont(new Font("", Font.PLAIN, 35));
		textArea.setPreferredSize(new Dimension(3,3));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(20,0,0,20);
		c.gridwidth = 3;
		c.gridheight = 4;
		c.gridx = 1;
		c.gridy = 0;
		add(textArea, c);
		
		menuButton = new JButton("Menu");
		menuButton.setFont(new Font("", Font.PLAIN, 25));
		menuButton.addActionListener(new MenuAction());
		menuButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(50,25,25,25);
		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 4;
		add(menuButton, c);
		
		fightButton = new JButton("Fight");
		fightButton.setFont(new Font("", Font.PLAIN, 25));
		fightButton.addActionListener(new FightAction());
		fightButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(50,25,25,25);
		c.gridx = 1;
		c.gridy = 4;
		add(fightButton, c);
		
		abilityButton = new JButton("Ability");
		abilityButton.setFont(new Font("", Font.PLAIN, 25));
		abilityButton.addActionListener(new AbilityAction());
		abilityButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(50,25,25,25);
		c.gridx = 2;
		c.gridy = 4;
		add(abilityButton, c);
		
		itemButton = new JButton("Item");
		itemButton.setFont(new Font("", Font.PLAIN, 25));
		itemButton.addActionListener(new ItemAction());
		itemButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(50,25,25,25);
		c.gridx = 3;
		c.gridy = 4;
		add(itemButton, c);
		
		runButton = new JButton("Run");
		runButton.setFont(new Font("", Font.PLAIN, 25));
		runButton.addActionListener(new RunAction());
		runButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(50,25,25,25);
		c.gridx = 4;
		c.gridy = 4;
		add(runButton, c);
		
		setBackground(Color.LIGHT_GRAY);
	}
	
	//Selects first enemy to fight based on player's level and random chance
	public Enemy enemy1Select()
	{
		Random rn = new Random();
		Enemy selectedEnemy = new Bat();
		int enemyNumber;
		
		if(player.getLevel() == 1)
		{
			enemyNumber = rn.nextInt(2);
			
			if(enemyNumber == 0)
				selectedEnemy = new Bat();
			else
				selectedEnemy = new Skeleton();
		}
		else if(player.getLevel() == 2)
		{
			enemyNumber = rn.nextInt(4);
			
			if(enemyNumber == 0)
				selectedEnemy = new Skeleton();
			else if(enemyNumber == 1)
				selectedEnemy = new Troll();
			else if(enemyNumber == 2)
				selectedEnemy = new Golem();
			else
				selectedEnemy = new EliteBat();
		}
		else if(player.getLevel() == 3)
		{
			enemyNumber = rn.nextInt(9);
			
			if((enemyNumber == 0) || (enemyNumber == 1))
				selectedEnemy = new Golem();
			else if((enemyNumber == 2) || (enemyNumber == 3))
				selectedEnemy = new EliteBat();
			else if((enemyNumber == 4) || (enemyNumber == 5))
				selectedEnemy = new EliteSkeleton();
			else if((enemyNumber == 6) || (enemyNumber == 7))
				selectedEnemy = new EliteTroll();
			else if(enemyNumber == 8)
				selectedEnemy = new EliteGolem();
		}
		else if(player.getLevel() == 4)
		{
			enemyNumber = rn.nextInt(4);
			
			if(enemyNumber == 0)
				selectedEnemy = new EliteBat();
			else if(enemyNumber == 1)
				selectedEnemy = new EliteSkeleton();
			else if(enemyNumber == 2)
				selectedEnemy = new EliteTroll();
			else
				selectedEnemy = new EliteGolem();
		}
		else if(player.getLevel() == 5)
		{
			enemyNumber = rn.nextInt(4);
			
			if(enemyNumber == 0)
				selectedEnemy = new EliteSkeleton();
			else if(enemyNumber == 1)
				selectedEnemy = new EliteTroll();
			else
				selectedEnemy = new EliteGolem();
		}
		
		return selectedEnemy;
	}
	
	//Selects second enemy to fight based on player's level and random chance. Also changes enemies names if both enemies are the same type
	public Enemy enemy2Select()
	{
		Random rn = new Random();
		Enemy selectedEnemy = new Bat();
		int enemyNumber;
		
		if(player.getLevel() == 1)
		{
			enemyNumber = rn.nextInt(2);
			
			if(enemyNumber == 0)
			{
				selectedEnemy = new Bat();
				
				if(enemy1 instanceof Bat)
				{
					enemy1.setName("Bat 1");
					selectedEnemy.setName("Bat 2");
				}
			}
			else
			{
				selectedEnemy = new Skeleton();
				
				if(enemy1 instanceof Skeleton)
				{
					enemy1.setName("Skeleton 1");
					selectedEnemy.setName("Skeleton 2");
				}
			}
		}
		else if(player.getLevel() == 2)
		{
			enemyNumber = rn.nextInt(4);
			
			if(enemyNumber == 0)
			{
				selectedEnemy = new Skeleton();
				
				if(enemy1 instanceof Skeleton)
				{
					enemy1.setName("Skeleton 1");
					selectedEnemy.setName("Skeleton 2");
				}
			}
			else if(enemyNumber == 1)
			{
				selectedEnemy = new Troll();
				
				if(enemy1 instanceof Troll)
				{
					enemy1.setName("Troll 1");
					selectedEnemy.setName("Troll 2");
				}
			}
			else if(enemyNumber == 2)
			{
				selectedEnemy = new Golem();
				
				if(enemy1 instanceof Golem)
				{
					enemy1.setName("Golem 1");
					selectedEnemy.setName("Golem 2");
				}
			}
			else
			{
				selectedEnemy = new EliteBat();
				
				if(enemy1 instanceof EliteBat)
				{
					enemy1.setName("Elite Bat 1");
					selectedEnemy.setName("Elite Bat 2");
				}
			}
		}
		else if(player.getLevel() == 3)
		{
			enemyNumber = rn.nextInt(9);
			
			if((enemyNumber == 0) || (enemyNumber == 1))
			{
				selectedEnemy = new Golem();
				
				if(enemy1 instanceof Golem)
				{
					enemy1.setName("Golem 1");
					selectedEnemy.setName("Golem 2");
				}
			}
			else if((enemyNumber == 2) || (enemyNumber == 3))
			{
				selectedEnemy = new EliteBat();
				
				if(enemy1 instanceof EliteBat)
				{
					enemy1.setName("Elite Bat 1");
					selectedEnemy.setName("Elite Bat 2");
				}
			}
			else if((enemyNumber == 4) || (enemyNumber == 5))
			{
				selectedEnemy = new EliteSkeleton();
				
				if(enemy1 instanceof EliteSkeleton)
				{
					enemy1.setName("Elite Skeleton 1");
					selectedEnemy.setName("Elite Skeleton 2");
				}
			}
			else if((enemyNumber == 6) || (enemyNumber == 7))
			{
				selectedEnemy = new EliteTroll();
				
				if(enemy1 instanceof EliteTroll)
				{
					enemy1.setName("Elite Troll 1");
					selectedEnemy.setName("Elite Troll 2");
				}
			}
			else if(enemyNumber == 8)
			{
				selectedEnemy = new EliteGolem();
				
				if(enemy1 instanceof EliteGolem)
				{
					enemy1.setName("Elite Golem 1");
					selectedEnemy.setName("Elite Golem 2");
				}
			}
		}
		else if(player.getLevel() == 4)
		{
			enemyNumber = rn.nextInt(4);
			
			if(enemyNumber == 0)
			{
				selectedEnemy = new EliteBat();
				
				if(enemy1 instanceof EliteBat)
				{
					enemy1.setName("Elite Bat 1");
					selectedEnemy.setName("Elite Bat 2");
				}
			}
			else if(enemyNumber == 1)
			{
				selectedEnemy = new EliteSkeleton();
				
				if(enemy1 instanceof EliteSkeleton)
				{
					enemy1.setName("Elite Skeleton 1");
					selectedEnemy.setName("Elite Skeleton 2");
				}
			}
			else if(enemyNumber == 2)
			{
				selectedEnemy = new EliteTroll();
				
				if(enemy1 instanceof EliteTroll)
				{
					enemy1.setName("Elite Troll 1");
					selectedEnemy.setName("Elite Troll 2");
				}
			}
			else
			{
				selectedEnemy = new EliteGolem();
				
				if(enemy1 instanceof EliteGolem)
				{
					enemy1.setName("Elite Golem 1");
					selectedEnemy.setName("Elite Golem 2");
				}
			}
		}
		else if(player.getLevel() == 5)
		{
			enemyNumber = rn.nextInt(4);
			
			if(enemyNumber == 0)
			{
				selectedEnemy = new EliteSkeleton();
				
				if(enemy1 instanceof EliteSkeleton)
				{
					enemy1.setName("Elite Skeleton 1");
					selectedEnemy.setName("Elite Skeleton 2");
				}
			}
			else if(enemyNumber == 1)
			{
				selectedEnemy = new EliteTroll();
				
				if(enemy1 instanceof EliteTroll)
				{
					enemy1.setName("Elite Troll 1");
					selectedEnemy.setName("Elite Troll 2");
				}
			}
			else
			{
				selectedEnemy = new EliteGolem();
				
				if(enemy1 instanceof EliteGolem)
				{
					enemy1.setName("Elite Golem 1");
					selectedEnemy.setName("Elite Golem 2");
				}
			}
		}
		
		return selectedEnemy;
	}
	
	//Attacks selected enemy with player's normal attack.
	public void playerAttack(Player player, Enemy enemy, JTextArea textArea, JProgressBar eHealthBar, Enemy other)
	{
		playerTurn = false;
		player.attack(enemy);
		textArea.setText("You attack the " + enemy.getName() + " and deal " + player.getDamage() + " damage!");
		
		if(enemy.getHP() <= 0 && other.getHP() <= 0)
		{
			if(enemy.drop() == true)
			{
				if(other.drop() == true)
				{
					eHealthBar.setValue(0);
					eHealthBar.setString("Health 0" + "/" + enemy.getMaxHP());
					UIManager.put("OptionPane.okButtonText", "OK");
					UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, "You defeated " + enemy.getName() + " and " + other.getName() + "! You gain " + (enemy.getXP() + other.getXP()) + "XP!\n\nNew loot gained: " + enemy.getDrop().getName() + ", " + other.getDrop().getName() + "!", "Enemies defeated!", JOptionPane.PLAIN_MESSAGE);
					player.addItem(enemy.getDrop());
					player.addItem(other.getDrop());
					player.setXP(player.getXP() + (enemy.getXP() + other.getXP()));
					if(player.getXP() >= player.getMaxXP())
					{
						player.levelUp();
						if(player.getLevel() == 6)
						{
							UIManager.put("OptionPane.okButtonText", "Back to Main Menu");
							UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, "Congratulations, you have become the strongest warrior in the land!", "You win!", JOptionPane.PLAIN_MESSAGE);
							frame.setContentPane(new MainMenu(frame));
							frame.revalidate();
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "You leveled up! You are now level " + player.getLevel() + "!", "Level Up", JOptionPane.PLAIN_MESSAGE);
							frame.setContentPane(new OverworldScreen(frame, player, location));
							frame.revalidate();
						}
					}	
					else
					{
						frame.setContentPane(new OverworldScreen(frame, player, location));
						frame.revalidate();
					}
				}
				else
				{
					eHealthBar.setValue(0);
					eHealthBar.setString("Health 0" + "/" + enemy.getMaxHP());
					UIManager.put("OptionPane.okButtonText", "OK");
					UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, "You defeated " + enemy.getName() + " and " + other.getName() + "! You gain " + (enemy.getXP() + other.getXP()) + "XP!\n\nNew loot gained: " + enemy.getDrop().getName() + "!", "Enemies defeated!", JOptionPane.PLAIN_MESSAGE);
					player.addItem(enemy.getDrop());
					player.setXP(player.getXP() + (enemy.getXP() + other.getXP()));
					if(player.getXP() >= player.getMaxXP())
					{
						player.levelUp();
						if(player.getLevel() == 6)
						{
							UIManager.put("OptionPane.okButtonText", "Back to Main Menu");
							UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, "Congratulations, you have become the strongest warrior in the land!", "You win!", JOptionPane.PLAIN_MESSAGE);
							frame.setContentPane(new MainMenu(frame));
							frame.revalidate();
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "You leveled up! You are now level " + player.getLevel() + "!", "Level Up", JOptionPane.PLAIN_MESSAGE);
							frame.setContentPane(new OverworldScreen(frame, player, location));
							frame.revalidate();
						}
					}	
					else
					{
						frame.setContentPane(new OverworldScreen(frame, player, location));
						frame.revalidate();
					}
				}
			}
			else
			{
				if(other.drop() == true)
				{
					eHealthBar.setValue(0);
					eHealthBar.setString("Health 0" + "/" + enemy.getMaxHP());
					UIManager.put("OptionPane.okButtonText", "OK");
					UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, "You defeated " + enemy.getName() + " and " + other.getName() + "! You gain " + (enemy.getXP() + other.getXP()) + "XP!\n\nNew loot gained: " + other.getDrop().getName() + "!", "Enemies defeated!", JOptionPane.PLAIN_MESSAGE);
					player.addItem(other.getDrop());
					player.setXP(player.getXP() + (enemy.getXP() + other.getXP()));
					if(player.getXP() >= player.getMaxXP())
					{
						player.levelUp();
						if(player.getLevel() == 6)
						{
							UIManager.put("OptionPane.okButtonText", "Back to Main Menu");
							UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, "Congratulations, you have become the strongest warrior in the land!", "You win!", JOptionPane.PLAIN_MESSAGE);
							frame.setContentPane(new MainMenu(frame));
							frame.revalidate();
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "You leveled up! You are now level " + player.getLevel() + "!", "Level Up", JOptionPane.PLAIN_MESSAGE);
							frame.setContentPane(new OverworldScreen(frame, player, location));
							frame.revalidate();
						}
					}	
					else
					{
						frame.setContentPane(new OverworldScreen(frame, player, location));
						frame.revalidate();
					}
				}
				else
				{
					eHealthBar.setValue(0);
					eHealthBar.setString("Health 0" + "/" + enemy.getMaxHP());
					UIManager.put("OptionPane.okButtonText", "OK");
					UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, "You defeated " + enemy.getName() + " and " + other.getName() + "! You gain " + (enemy.getXP() + other.getXP()) + "XP!", "Enemies defeated!", JOptionPane.PLAIN_MESSAGE);
					player.setXP(player.getXP() + (enemy.getXP() + other.getXP()));
					if(player.getXP() >= player.getMaxXP())
					{
						player.levelUp();
						if(player.getLevel() == 6)
						{
							UIManager.put("OptionPane.okButtonText", "Back to Main Menu");
							UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, "Congratulations, you have become the strongest warrior in the land!", "You win!", JOptionPane.PLAIN_MESSAGE);
							frame.setContentPane(new MainMenu(frame));
							frame.revalidate();
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "You leveled up! You are now level " + player.getLevel() + "!", "Level Up", JOptionPane.PLAIN_MESSAGE);
							frame.setContentPane(new OverworldScreen(frame, player, location));
							frame.revalidate();
						}
					}	
					else
					{
						frame.setContentPane(new OverworldScreen(frame, player, location));
						frame.revalidate();
					}
				}
			}
		}
		else if(enemy.getHP() <= 0 && other.getHP() > 0)
		{
			textArea.append("\n\n" + enemy.getName() + " died!");
			eHealthBar.setValue(0);
			eHealthBar.setString("Health 0" + "/" + enemy.getMaxHP());
		}
		else
		{
			eHealthBar.setValue(enemy.getHP());
			eHealthBar.setString("Health " + enemy.getHP() + "/" + enemy.getMaxHP());
		}
	}
	
	//Attacks player with enemy's attack
	public void enemyAttack(Player player, Enemy enemy, JTextArea textArea, JProgressBar pHealthBar)
	{
		enemy.attack(player);
		textArea.append("\n\n" + enemy.getName() + " attacks you and deals " + enemy.getDamage() + " damage!");
		
		if(player.getHP() <= 0)
		{
			pHealthBar.setValue(0);
			pHealthBar.setString("Health 0" + "/" + player.getMaxHP());
			UIManager.put("OptionPane.okButtonText", "Back to Main Menu");
			UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
			JOptionPane.showMessageDialog(frame, "You died!", "You died", JOptionPane.PLAIN_MESSAGE);
			frame.setContentPane(new MainMenu(frame));
			frame.revalidate();
		}
		else
		{
			pHealthBar.setValue(player.getHP());
			pHealthBar.setString("Health " + player.getHP() + "/" + player.getMaxHP());
			playerTurn = true;
		}
	}
	
	//Attacks selected enemy with Super Slash -ability
	public void superSlash(Player player, Enemy enemy, JTextArea textArea, JProgressBar eHealthBar, JProgressBar pAPBar, Enemy other)
	{
		playerTurn = false;
		player.superSlash(enemy);
		textArea.setText("You attack the " + enemy.getName() + " with Super Slash and deal " + player.getDamage() + " damage!");
		pAPBar.setValue(player.getAP());
		pAPBar.setString("AP " + player.getAP() + "/" + player.getMaxAP());
		
		if(enemy.getHP() <= 0 && other.getHP() <= 0)
		{
			if(enemy.drop() == true)
			{
				if(other.drop() == true)
				{
					eHealthBar.setValue(0);
					eHealthBar.setString("Health 0" + "/" + enemy.getMaxHP());
					UIManager.put("OptionPane.okButtonText", "OK");
					UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, "You defeated " + enemy.getName() + " and " + other.getName() + "! You gain " + (enemy.getXP() + other.getXP()) + "XP!\n\nNew loot gained: " + enemy.getDrop().getName() + ", " + other.getDrop().getName() + "!", "Enemies defeated!", JOptionPane.PLAIN_MESSAGE);
					player.addItem(enemy.getDrop());
					player.addItem(other.getDrop());
					player.setXP(player.getXP() + (enemy.getXP() + other.getXP()));
					if(player.getXP() >= player.getMaxXP())
					{
						player.levelUp();
						if(player.getLevel() == 6)
						{
							UIManager.put("OptionPane.okButtonText", "Back to Main Menu");
							UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, "Congratulations, you have become the strongest warrior in the land!", "You win!", JOptionPane.PLAIN_MESSAGE);
							frame.setContentPane(new MainMenu(frame));
							frame.revalidate();
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "You leveled up! You are now level " + player.getLevel() + "!", "Level Up", JOptionPane.PLAIN_MESSAGE);
							frame.setContentPane(new OverworldScreen(frame, player, location));
							frame.revalidate();
						}
					}	
					else
					{
						frame.setContentPane(new OverworldScreen(frame, player, location));
						frame.revalidate();
					}
				}
				else
				{
					eHealthBar.setValue(0);
					eHealthBar.setString("Health 0" + "/" + enemy.getMaxHP());
					UIManager.put("OptionPane.okButtonText", "OK");
					UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, "You defeated " + enemy.getName() + " and " + other.getName() + "! You gain " + (enemy.getXP() + other.getXP()) + "XP!\n\nNew loot gained: " + enemy.getDrop().getName() + "!", "Enemies defeated!", JOptionPane.PLAIN_MESSAGE);
					player.addItem(enemy.getDrop());
					player.setXP(player.getXP() + (enemy.getXP() + other.getXP()));
					if(player.getXP() >= player.getMaxXP())
					{
						player.levelUp();
						if(player.getLevel() == 6)
						{
							UIManager.put("OptionPane.okButtonText", "Back to Main Menu");
							UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, "Congratulations, you have become the strongest warrior in the land!", "You win!", JOptionPane.PLAIN_MESSAGE);
							frame.setContentPane(new MainMenu(frame));
							frame.revalidate();
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "You leveled up! You are now level " + player.getLevel() + "!", "Level Up", JOptionPane.PLAIN_MESSAGE);
							frame.setContentPane(new OverworldScreen(frame, player, location));
							frame.revalidate();
						}
					}	
					else
					{
						frame.setContentPane(new OverworldScreen(frame, player, location));
						frame.revalidate();
					}
				}
			}
			else
			{
				if(other.drop() == true)
				{
					eHealthBar.setValue(0);
					eHealthBar.setString("Health 0" + "/" + enemy.getMaxHP());
					UIManager.put("OptionPane.okButtonText", "OK");
					UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, "You defeated " + enemy.getName() + " and " + other.getName() + "! You gain " + (enemy.getXP() + other.getXP()) + "XP!\n\nNew loot gained: " + other.getDrop().getName() + "!", "Enemies defeated!", JOptionPane.PLAIN_MESSAGE);
					player.addItem(other.getDrop());
					player.setXP(player.getXP() + (enemy.getXP() + other.getXP()));
					if(player.getXP() >= player.getMaxXP())
					{
						player.levelUp();
						if(player.getLevel() == 6)
						{
							UIManager.put("OptionPane.okButtonText", "Back to Main Menu");
							UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, "Congratulations, you have become the strongest warrior in the land!", "You win!", JOptionPane.PLAIN_MESSAGE);
							frame.setContentPane(new MainMenu(frame));
							frame.revalidate();
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "You leveled up! You are now level " + player.getLevel() + "!", "Level Up", JOptionPane.PLAIN_MESSAGE);
							frame.setContentPane(new OverworldScreen(frame, player, location));
							frame.revalidate();
						}
					}	
					else
					{
						frame.setContentPane(new OverworldScreen(frame, player, location));
						frame.revalidate();
					}
				}
				else
				{
					eHealthBar.setValue(0);
					eHealthBar.setString("Health 0" + "/" + enemy.getMaxHP());
					UIManager.put("OptionPane.okButtonText", "OK");
					UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, "You defeated " + enemy.getName() + " and " + other.getName() + "! You gain " + (enemy.getXP() + other.getXP()) + "XP!", "Enemies defeated!", JOptionPane.PLAIN_MESSAGE);
					player.setXP(player.getXP() + (enemy.getXP() + other.getXP()));
					if(player.getXP() >= player.getMaxXP())
					{
						player.levelUp();
						if(player.getLevel() == 6)
						{
							UIManager.put("OptionPane.okButtonText", "Back to Main Menu");
							UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, "Congratulations, you have become the strongest warrior in the land!", "You win!", JOptionPane.PLAIN_MESSAGE);
							frame.setContentPane(new MainMenu(frame));
							frame.revalidate();
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "You leveled up! You are now level " + player.getLevel() + "!", "Level Up", JOptionPane.PLAIN_MESSAGE);
							frame.setContentPane(new OverworldScreen(frame, player, location));
							frame.revalidate();
						}
					}	
					else
					{
						frame.setContentPane(new OverworldScreen(frame, player, location));
						frame.revalidate();
					}
				}
			}
		}
		else if(enemy.getHP() <= 0 && other.getHP() > 0)
		{
			textArea.append("\n\n" + enemy.getName() + " died!");
			eHealthBar.setValue(0);
			eHealthBar.setString("Health 0" + "/" + enemy.getMaxHP());
		}
		else
		{
			eHealthBar.setValue(enemy.getHP());
			eHealthBar.setString("Health " + enemy.getHP() + "/" + enemy.getMaxHP());
		}
	}
	
	//Attacks all enemies with Blitz -ability
	public void blitz(Player player, Enemy enemy, Enemy other, JTextArea textArea, JProgressBar e1HealthBar, JProgressBar e2HealthBar, JProgressBar pAPBar)
	{
		playerTurn = false;
		player.blitz(enemy);
		player.blitz(other);
		player.setAP(player.getAP() + 20);
		textArea.setText("You attack the " + enemy.getName() + "and " + other.getName() + " with Blitz and deal " + player.getDamage() + " damage!");
		pAPBar.setValue(player.getAP());
		pAPBar.setString("AP " + player.getAP() + "/" + player.getMaxAP());
		
		if(enemy.getHP() <= 0 && other.getHP() <= 0)
		{
			if(enemy.drop() == true)
			{
				if(other.drop() == true)
				{
					e1HealthBar.setValue(0);
					e1HealthBar.setString("Health 0" + "/" + enemy.getMaxHP());
					e2HealthBar.setValue(0);
					e2HealthBar.setString("Health 0" + "/" + other.getMaxHP());
					UIManager.put("OptionPane.okButtonText", "OK");
					UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, "You defeated " + enemy.getName() + " and " + other.getName() + "! You gain " + (enemy.getXP() + other.getXP()) + "XP!\n\nNew loot gained: " + enemy.getDrop().getName() + ", " + other.getDrop().getName() + "!", "Enemies defeated!", JOptionPane.PLAIN_MESSAGE);
					player.addItem(enemy.getDrop());
					player.addItem(other.getDrop());
					player.setXP(player.getXP() + (enemy.getXP() + other.getXP()));
					if(player.getXP() >= player.getMaxXP())
					{
						player.levelUp();
						if(player.getLevel() == 6)
						{
							UIManager.put("OptionPane.okButtonText", "Back to Main Menu");
							UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, "Congratulations, you have become the strongest warrior in the land!", "You win!", JOptionPane.PLAIN_MESSAGE);
							frame.setContentPane(new MainMenu(frame));
							frame.revalidate();
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "You leveled up! You are now level " + player.getLevel() + "!", "Level Up", JOptionPane.PLAIN_MESSAGE);
							frame.setContentPane(new OverworldScreen(frame, player, location));
							frame.revalidate();
						}
					}	
					else
					{
						frame.setContentPane(new OverworldScreen(frame, player, location));
						frame.revalidate();
					}
				}
				else
				{
					e1HealthBar.setValue(0);
					e1HealthBar.setString("Health 0" + "/" + enemy.getMaxHP());
					e2HealthBar.setValue(0);
					e2HealthBar.setString("Health 0" + "/" + other.getMaxHP());
					UIManager.put("OptionPane.okButtonText", "OK");
					UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, "You defeated " + enemy.getName() + " and " + other.getName() + "! You gain " + (enemy.getXP() + other.getXP()) + "XP!\n\nNew loot gained: " + enemy.getDrop().getName() + "!", "Enemies defeated!", JOptionPane.PLAIN_MESSAGE);
					player.addItem(enemy.getDrop());
					player.setXP(player.getXP() + (enemy.getXP() + other.getXP()));
					if(player.getXP() >= player.getMaxXP())
					{
						player.levelUp();
						if(player.getLevel() == 6)
						{
							UIManager.put("OptionPane.okButtonText", "Back to Main Menu");
							UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, "Congratulations, you have become the strongest warrior in the land!", "You win!", JOptionPane.PLAIN_MESSAGE);
							frame.setContentPane(new MainMenu(frame));
							frame.revalidate();
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "You leveled up! You are now level " + player.getLevel() + "!", "Level Up", JOptionPane.PLAIN_MESSAGE);
							frame.setContentPane(new OverworldScreen(frame, player, location));
							frame.revalidate();
						}
					}	
					else
					{
						frame.setContentPane(new OverworldScreen(frame, player, location));
						frame.revalidate();
					}
				}
			}
			else
			{
				if(other.drop() == true)
				{
					e1HealthBar.setValue(0);
					e1HealthBar.setString("Health 0" + "/" + enemy.getMaxHP());
					e2HealthBar.setValue(0);
					e2HealthBar.setString("Health 0" + "/" + other.getMaxHP());
					UIManager.put("OptionPane.okButtonText", "OK");
					UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, "You defeated " + enemy.getName() + " and " + other.getName() + "! You gain " + (enemy.getXP() + other.getXP()) + "XP!\n\nNew loot gained: " + other.getDrop().getName() + "!", "Enemies defeated!", JOptionPane.PLAIN_MESSAGE);
					player.addItem(other.getDrop());
					player.setXP(player.getXP() + (enemy.getXP() + other.getXP()));
					if(player.getXP() >= player.getMaxXP())
					{
						player.levelUp();
						if(player.getLevel() == 6)
						{
							UIManager.put("OptionPane.okButtonText", "Back to Main Menu");
							UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, "Congratulations, you have become the strongest warrior in the land!", "You win!", JOptionPane.PLAIN_MESSAGE);
							frame.setContentPane(new MainMenu(frame));
							frame.revalidate();
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "You leveled up! You are now level " + player.getLevel() + "!", "Level Up", JOptionPane.PLAIN_MESSAGE);
							frame.setContentPane(new OverworldScreen(frame, player, location));
							frame.revalidate();
						}
					}	
					else
					{
						frame.setContentPane(new OverworldScreen(frame, player, location));
						frame.revalidate();
					}
				}
				else
				{
					e1HealthBar.setValue(0);
					e1HealthBar.setString("Health 0" + "/" + enemy.getMaxHP());
					e2HealthBar.setValue(0);
					e2HealthBar.setString("Health 0" + "/" + other.getMaxHP());
					UIManager.put("OptionPane.okButtonText", "OK");
					UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, "You defeated " + enemy.getName() + " and " + other.getName() + "! You gain " + (enemy.getXP() + other.getXP()) + "XP!", "Enemies defeated!", JOptionPane.PLAIN_MESSAGE);
					player.setXP(player.getXP() + (enemy.getXP() + other.getXP()));
					if(player.getXP() >= player.getMaxXP())
					{
						player.levelUp();
						if(player.getLevel() == 6)
						{
							UIManager.put("OptionPane.okButtonText", "Back to Main Menu");
							UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, "Congratulations, you have become the strongest warrior in the land!", "You win!", JOptionPane.PLAIN_MESSAGE);
							frame.setContentPane(new MainMenu(frame));
							frame.revalidate();
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "You leveled up! You are now level " + player.getLevel() + "!", "Level Up", JOptionPane.PLAIN_MESSAGE);
							frame.setContentPane(new OverworldScreen(frame, player, location));
							frame.revalidate();
						}
					}	
					else
					{
						frame.setContentPane(new OverworldScreen(frame, player, location));
						frame.revalidate();
					}
				}
			}
		}
		else if(enemy.getHP() <= 0 && other.getHP() > 0)
		{
			textArea.append("\n\n" + enemy.getName() + " died!");
			e1HealthBar.setValue(0);
			e1HealthBar.setString("Health 0" + "/" + enemy.getMaxHP());
		}
		else if(enemy.getHP() > 0 && other.getHP() <= 0)
		{
			textArea.append("\n\n" + other.getName() + " died!");
			e2HealthBar.setValue(0);
			e2HealthBar.setString("Health 0" + "/" + other.getMaxHP());
		}
		else
		{
			e1HealthBar.setValue(enemy.getHP());
			e1HealthBar.setString("Health " + enemy.getHP() + "/" + enemy.getMaxHP());
			e2HealthBar.setValue(other.getHP());
			e2HealthBar.setString("Health " + other.getHP() + "/" + other.getMaxHP());
		}
	}
	
	//Attacks selected enemy with Magic Beam -ability
	public void magicBeam(Player player, Enemy enemy, JTextArea textArea, JProgressBar eHealthBar, JProgressBar pAPBar, Enemy other)
	{
		playerTurn = false;
		player.magicBeam(enemy);
		textArea.setText("You attack the " + enemy.getName() + " with Magic Beam and deal " + player.getDamage() + " damage!");
		pAPBar.setValue(player.getAP());
		pAPBar.setString("AP " + player.getAP() + "/" + player.getMaxAP());
		
		if(enemy.getHP() <= 0 && other.getHP() <= 0)
		{
			if(enemy.drop() == true)
			{
				if(other.drop() == true)
				{
					eHealthBar.setValue(0);
					eHealthBar.setString("Health 0" + "/" + enemy.getMaxHP());
					UIManager.put("OptionPane.okButtonText", "OK");
					UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, "You defeated " + enemy.getName() + " and " + other.getName() + "! You gain " + (enemy.getXP() + other.getXP()) + "XP!\n\nNew loot gained: " + enemy.getDrop().getName() + ", " + other.getDrop().getName() + "!", "Enemies defeated!", JOptionPane.PLAIN_MESSAGE);
					player.addItem(enemy.getDrop());
					player.addItem(other.getDrop());
					player.setXP(player.getXP() + (enemy.getXP() + other.getXP()));
					if(player.getXP() >= player.getMaxXP())
					{
						player.levelUp();
						if(player.getLevel() == 6)
						{
							UIManager.put("OptionPane.okButtonText", "Back to Main Menu");
							UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, "Congratulations, you have become the strongest warrior in the land!", "You win!", JOptionPane.PLAIN_MESSAGE);
							frame.setContentPane(new MainMenu(frame));
							frame.revalidate();
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "You leveled up! You are now level " + player.getLevel() + "!", "Level Up", JOptionPane.PLAIN_MESSAGE);
							frame.setContentPane(new OverworldScreen(frame, player, location));
							frame.revalidate();
						}
					}	
					else
					{
						frame.setContentPane(new OverworldScreen(frame, player, location));
						frame.revalidate();
					}
				}
				else
				{
					eHealthBar.setValue(0);
					eHealthBar.setString("Health 0" + "/" + enemy.getMaxHP());
					UIManager.put("OptionPane.okButtonText", "OK");
					UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, "You defeated " + enemy.getName() + " and " + other.getName() + "! You gain " + (enemy.getXP() + other.getXP()) + "XP!\n\nNew loot gained: " + enemy.getDrop().getName() + "!", "Enemies defeated!", JOptionPane.PLAIN_MESSAGE);
					player.addItem(enemy.getDrop());
					player.setXP(player.getXP() + (enemy.getXP() + other.getXP()));
					if(player.getXP() >= player.getMaxXP())
					{
						player.levelUp();
						if(player.getLevel() == 6)
						{
							UIManager.put("OptionPane.okButtonText", "Back to Main Menu");
							UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, "Congratulations, you have become the strongest warrior in the land!", "You win!", JOptionPane.PLAIN_MESSAGE);
							frame.setContentPane(new MainMenu(frame));
							frame.revalidate();
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "You leveled up! You are now level " + player.getLevel() + "!", "Level Up", JOptionPane.PLAIN_MESSAGE);
							frame.setContentPane(new OverworldScreen(frame, player, location));
							frame.revalidate();
						}
					}	
					else
					{
						frame.setContentPane(new OverworldScreen(frame, player, location));
						frame.revalidate();
					}
				}
			}
			else
			{
				if(other.drop() == true)
				{
					eHealthBar.setValue(0);
					eHealthBar.setString("Health 0" + "/" + enemy.getMaxHP());
					UIManager.put("OptionPane.okButtonText", "OK");
					UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, "You defeated " + enemy.getName() + " and " + other.getName() + "! You gain " + (enemy.getXP() + other.getXP()) + "XP!\n\nNew loot gained: " + other.getDrop().getName() + "!", "Enemies defeated!", JOptionPane.PLAIN_MESSAGE);
					player.addItem(other.getDrop());
					player.setXP(player.getXP() + (enemy.getXP() + other.getXP()));
					if(player.getXP() >= player.getMaxXP())
					{
						player.levelUp();
						if(player.getLevel() == 6)
						{
							UIManager.put("OptionPane.okButtonText", "Back to Main Menu");
							UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, "Congratulations, you have become the strongest warrior in the land!", "You win!", JOptionPane.PLAIN_MESSAGE);
							frame.setContentPane(new MainMenu(frame));
							frame.revalidate();
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "You leveled up! You are now level " + player.getLevel() + "!", "Level Up", JOptionPane.PLAIN_MESSAGE);
							frame.setContentPane(new OverworldScreen(frame, player, location));
							frame.revalidate();
						}
					}	
					else
					{
						frame.setContentPane(new OverworldScreen(frame, player, location));
						frame.revalidate();
					}
				}
				else
				{
					eHealthBar.setValue(0);
					eHealthBar.setString("Health 0" + "/" + enemy.getMaxHP());
					UIManager.put("OptionPane.okButtonText", "OK");
					UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
					JOptionPane.showMessageDialog(frame, "You defeated " + enemy.getName() + " and " + other.getName() + "! You gain " + (enemy.getXP() + other.getXP()) + "XP!", "Enemies defeated!", JOptionPane.PLAIN_MESSAGE);
					player.setXP(player.getXP() + (enemy.getXP() + other.getXP()));
					if(player.getXP() >= player.getMaxXP())
					{
						player.levelUp();
						if(player.getLevel() == 6)
						{
							UIManager.put("OptionPane.okButtonText", "Back to Main Menu");
							UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
							JOptionPane.showMessageDialog(frame, "Congratulations, you have become the strongest warrior in the land!", "You win!", JOptionPane.PLAIN_MESSAGE);
							frame.setContentPane(new MainMenu(frame));
							frame.revalidate();
						}
						else
						{
							JOptionPane.showMessageDialog(frame, "You leveled up! You are now level " + player.getLevel() + "!", "Level Up", JOptionPane.PLAIN_MESSAGE);
							frame.setContentPane(new OverworldScreen(frame, player, location));
							frame.revalidate();
						}
					}	
					else
					{
						frame.setContentPane(new OverworldScreen(frame, player, location));
						frame.revalidate();
					}
				}
			}
		}
		else if(enemy.getHP() <= 0 && other.getHP() > 0)
		{
			textArea.append("\n\n" + enemy.getName() + " died!");
			eHealthBar.setValue(0);
			eHealthBar.setString("Health 0" + "/" + enemy.getMaxHP());
		}
		else
		{
			eHealthBar.setValue(enemy.getHP());
			eHealthBar.setString("Health " + enemy.getHP() + "/" + enemy.getMaxHP());
		}
	}
}