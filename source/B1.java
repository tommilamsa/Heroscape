import javax.swing.*;

public class B1 extends Location
{
	//Constructor for Deep Woods -location
	public B1()
	{
		super("Deep Woods");
	}
	
	//Returns text shown when entering the area
	public String getText()
	{
		String areaText = "The forest is very thick and you can barely see the sunlight through the trees. It would be very easy to get lost here.";
		
		return areaText;
	}
	
	//Returns array of names of possible travel destinations from this location
	public String[] getButtons()
	{
		String[] locations = {"Mountains", "Swamp", "Forest"};
		
		return locations;
	}
	
	//Sets text to textarea when pressing Examine-button
	public void examine(JTextArea textArea)
	{
		textArea.setText("You can hear something in the distance, but you can't see the source of the sound.\n\nTo the north you can see a range of tall mountains.\nTo the east is a vast wetland.\nThe forest seems to open up a little to the south.");
	}
}