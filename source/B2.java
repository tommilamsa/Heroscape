import javax.swing.*;

public class B2 extends Location
{
	//Constructor for Swamp -location
	public B2()
	{
		super("Swamp");
	}
	
	//Returns text shown when entering the area
	public String getText()
	{
		String areaText = "A large wetland unfolds around you. The air is thick and you can hear the sounds of insects.";
		
		return areaText;
	}
	
	//Returns array of names of possible travel destinations from this location
	public String[] getButtons()
	{
		String[] locations = {"Desert", "River", "Plains", "Deep Woods"};
		
		return locations;
	}
	
	//Sets text to textarea when pressing Examine-button
	public void examine(JTextArea textArea)
	{
		textArea.setText("To the north you see a large desert.\nTo the east is a coursing river.\nThere is an open plain in the south.\nTo the west you can see a thick forest.");
	}
}