import javax.swing.*;

public class C1 extends Location
{
	//Constructor for Forest -location
	public C1()
	{
		super("Forest");
	}
	
	//Returns text shown when entering the area
	public String getText()
	{
		String areaText = "You enter a dark forest. It's mostly quiet, but you can still hear the faint sounds of some kind of creatures in the distance";
		
		return areaText;
	}
	
	//Returns array of names of possible travel destinations from this location
	public String[] getButtons()
	{
		String[] locations = {"Deep Woods", "Plains"};
		
		return locations;
	}
	
	//Sets text to textarea when pressing Examine-button
	public void examine(JTextArea textArea)
	{
		textArea.setText("The forest seems to grow thicker as you look north.\n\nYou can see some sunlight shining in the east. There seems to be a grassy field in that direction.");
	}
}