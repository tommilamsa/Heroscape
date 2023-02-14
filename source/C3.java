import javax.swing.*;

public class C3 extends Location
{
	private Player player;	//Player-object used for checking progress
	
	//Constructor for Lake -location
	public C3(Player character)
	{
		super("Lake");
		this.player = character;
	}
	
	//Returns text shown when entering the area
	public String getText()
	{
		String areaText = "You see a wide blue lake ahead. Looking at the sunlight shining on the water gives you a serene feeling.";
		
		return areaText;
	}
	
	//Returns array of names of possible travel destinations from this location
	public String[] getButtons()
	{
		String[] locations = {"River", "Plains"};
		
		return locations;
	}
	
	//Sets text to textarea when pressing Examine-button
	public void examine(JTextArea textArea)
	{
		if(player.getC3() == false)
		{
			textArea.setText("You find a small wooden box at the edge of the water. Inside you find some potions.\n\n2 HP Potions and 2 AP Potions added to inventory!");
			player.addItem(new HpPotion());
			player.addItem(new HpPotion());
			player.addItem(new ApPotion());
			player.addItem(new ApPotion());
			player.setC3();
		}
		else
		{
			textArea.setText("The lake is calm and silence fills the air.\n\nYou can see a flowing river to the north.\nTo the west is a grassy plain.");
		}
	}
}