import javax.swing.*;

public class A3 extends Location
{
	private Player player; //Player-object used for checking progress
	
	//Constructor for Beach -location
	public A3(Player character)
	{
		super("Beach");
		this.player = character;
	}
	
	//Returns text shown when entering the area
	public String getText()
	{
		String areaText = "You arrive at a long, sandy beach. You can see a vast ocean ahead stretching out to the horizon.";
		
		return areaText;
	}
	
	//Returns array of names of possible travel destinations from this location
	public String[] getButtons()
	{
		String[] locations = {"River", "Desert"};
		
		return locations;
	}
	
	//Sets text to textarea when pressing Examine-button
	public void examine(JTextArea textArea)
	{
		if(player.getA3() == false)
		{
			textArea.setText("You can see something buried in the sand. You dig and find some potions.\n\nHP Potion and AP potion added to inventory!");
			player.addItem(new HpPotion());
			player.addItem(new ApPotion());
			player.setA3();
		}
		else
		{
			textArea.setText("The ocean goes further into the distance than you can see.\n\nA river starts at the ocean and continues to the south.\nThe sandy beach extends into a large desert to the west.");
		}
	}
}