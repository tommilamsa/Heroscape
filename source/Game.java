import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game
{
	//Constructor for Game
	public Game()
	{
		OverworldScreen overworld;
		BattleScreen1 battle1;
		BattleScreen2 battle2;
		Player player;
		Location location;
		
		JFrame frame = new JFrame("Heroscape RPG");
		frame.setSize(1250,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setContentPane(new MainMenu(frame));
		
		frame.setVisible(true);
		
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
	}
	
	//Main method for starting the game
	public static void main(String [] args)
	{
		Game game = new Game();
	}
}