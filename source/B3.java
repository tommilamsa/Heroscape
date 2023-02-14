import javax.swing.*;

public class B3 extends Location
{
	private Player player; //Player-object used for checking progress
	
	//Constructor for River -location
	public B3(Player character)
	{
		super("River");
		this.player = character;
	}
	
	//Returns text shown when entering the area
	public String getText()
	{
		String areaText = "The river flows swiftly through the valley. Watching the stream go by gives you a calming feeling.";
		
		return areaText;
	}
	
	//Returns array of names of possible travel destinations from this location
	public String[] getButtons()
	{
		String[] locations = {"Beach", "Lake", "Swamp"};
		
		return locations;
	}
	
	//Sets text to textarea when pressing Examine-button
	public void examine(JTextArea textArea)
	{
		if(player.getB3() == false)
		{
			textArea.setText("You find a greatsword next to the river. It looks used. Perhaps it belonged to a passing warrior.\n\nGreatsword added to inventory!");
			player.addItem(new GreatSword());
			player.setB3();
		}
		else
		{
			textArea.setText("The flow of the river calms you down.\n\nTo the north you can see a beach by the ocean, it seems to be the river's point of origin.\nThe river flows south where you see a large lake.\nTo the east you can see a swampland.");
		}
	}
}