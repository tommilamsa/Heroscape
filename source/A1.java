import javax.swing.*;

public class A1 extends Location
{
	private Player player; //Player-object used for checking progress
	
	//Constructor for Mountains -location
	public A1(Player character)
	{
		super("Mountains");
		this.player = character;
	}
	
	//Returns text shown when entering the area
	public String getText()
	{
		String areaText = "A range of mountains extend in front of you. They're so tall you can barely see the top.";
		
		return areaText;
	}
	
	//Returns array of names of possible travel destinations from this location
	public String[] getButtons()
	{
		String[] locations = {"Desert", "Deep Woods"};
		
		return locations;
	}
	
	//Sets text to textarea when pressing Examine-button
	public void examine(JTextArea textArea)
	{
		if(player.getA1() == false)
		{
			textArea.setText("You find a small hole in the mountain. Inside you find a sword. It's giving off a magical energy\n\nMagic Sword added to inventory!");
			player.addItem(new MagicSword());
			player.setA1();
		}
		else
		{
			textArea.setText("You get an eerie vibe from the mountains.\n\nTo the east you see a large desert.\nTo the south is a thick forest.");
		}
	}
}