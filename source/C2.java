import javax.swing.*;

public class C2 extends Location
{
	private Player player;	//Player-object used for checking progress
	
	//Constructor for Plains -location
	public C2(Player character)
	{
		super("Plains");
		this.player = character;
	}
	
	//Returns text shown when entering the area
	public String getText()
	{
		if(player.getStart() == true)
		{
			String areaText = "You wake up in a grassy field. In front of you is a lone tree with a chest next to it.";
			player.setStart();
			return areaText;
		}
		else
		{
			String areaText = "You enter the plains. A lone tree stands in front of you.";
			return areaText;
		}
	}
	
	//Returns array of names of possible travel destinations from this location
	public String[] getButtons()
	{
		String[] locations = {"Swamp", "Lake", "Forest"};
		
		return locations;
	}
	
	//Sets text to textarea when pressing Examine-button
	public void examine(JTextArea textArea)
	{
		if(player.getC2() == false)
		{
			textArea.setText("You open the chest. Inside you find an iron sword.\n\nSword added to inventory!");
			player.addItem(new Sword());
			player.setC2();
		}
		else
		{
			textArea.setText("In front of you is the chest you opened earlier.\n\nTo the north you can see a murky swamp\nTo the east you see a vast lake\nTo the west you see a dark forest");
		}
	}
}