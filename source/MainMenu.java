import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JPanel
{
	final private JFrame frame;	//JFrame used for application
	
	//Constructor for main menu screen
	public MainMenu(JFrame parent)
	{
		this.frame = parent;
		
		setPreferredSize(new Dimension(1250,800));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Box.createVerticalGlue());
		
		JLabel title = new JLabel("Heroscape RPG");
		title.setFont(new Font("Times New Roman", Font.PLAIN, 100));
		title.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		add(title);
		
		add(Box.createVerticalGlue());
		
		JButton startButton = new JButton("Start");
		startButton.setFont(new Font("", Font.PLAIN, 50));
		startButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		startButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String inputMessage = "Enter your name:";
				UIManager.put("OptionPane.okButtonText", "OK");
				UIManager.put("OptionPane.messageFont", new Font("", Font.BOLD, 15));
				String name = JOptionPane.showInputDialog(frame, inputMessage, null);
				if(name == null || name.isEmpty())
				{
					UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 15));
					JOptionPane.showMessageDialog(frame, "You must enter a name!", "Invalid name", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					Player player = new Player(name);
					frame.setContentPane(new OverworldScreen(frame, player, new C2(player)));
					frame.revalidate();
				}
			}
		});
		add(startButton);
		
		add(Box.createVerticalGlue());
		
		JButton helpButton = new JButton("Help");
		helpButton.setFont(new Font("", Font.PLAIN, 50));
		helpButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		helpButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String helpMessage = "How to play Heroscape RPG:\n\nThe Goal: Defeat enough enemies to become the most powerful warrior in the land\n\nProgress: Move between areas to encounter enemies\n\nTips: Use potions to survive better. Get potions and new weapons by exploring the world.";
				UIManager.put("OptionPane.okButtonText", "Close");
				UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
				JOptionPane.showMessageDialog(frame, helpMessage, "Help", JOptionPane.PLAIN_MESSAGE);
			}
		});
		add(helpButton);
		
		add(Box.createVerticalGlue());
		
		JButton exitButton = new JButton("Exit Game");
		exitButton.setFont(new Font("", Font.PLAIN, 50));
		exitButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		add(exitButton);
		
		add(Box.createVerticalGlue());
	}
}