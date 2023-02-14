import javax.swing.*;
import java.awt.*;

public class Location
{
	private String locName;	//Location name
	
	//Constructor for Location object
	public Location(String name)
	{
		this.locName = name;
	}
	
	//Returns location's name
	public String getName()
	{
		return this.locName;
	}
	
	//Returns text shown when entering area
	public String getText()
	{
		String areaText = "You are currently here";
		return areaText;
	}
	
	//Returns array of names of locations to travel to
	public String[] getButtons()
	{
		String[] locations = {"Area 1"};
		
		return locations;
	}
	
	//Sets textarea text when pressing Examine-button
	public void examine(JTextArea textArea)
	{
		textArea.setText("Nothting here");
	}
}