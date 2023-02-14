import javax.swing.*;

public class A2 extends Location
{
	private Player player; //Player-object used for checking progress
	
	//Constructor for Desert -location
	public A2(Player character)
	{
		super("Desert");
		this.player = character;
	}
	
	//Returns text shown when entering the area
	public String getText()
	{
		String areaText = "A huge desert fills the landscape. There is a small altar ahead of you.";
		
		return areaText;
	}
	
	//Returns array of names of possible travel destinations from this location
	public String[] getButtons()
	{
		String[] locations = {"Beach", "Swamp", "Mountains"};
		
		return locations;
	}
	
	//Sets text to textarea when pressing Examine-button
	public void examine(JTextArea textArea)
	{
		if(player.getA2() == false)
		{
			textArea.setText("There is an old scroll on the altar. There seems to be some ancient writing on it.\n\nYou learned a new ability: Blitz!");
			player.setBlitz();
			player.setA2();
		}
		else
		{
			textArea.setText("There is nothing on the altar anymore.\n\nTo the east the desert narrows into a beach by the ocean.\nTo the south you see a murky swampland.\nYou can see towering mountains to the west.");
		}
	}
}