import javax.swing.*;
import java.awt.*;

public class HpPotion extends Consumable
{
	private int hpGain;	//Number of HP points restored when using potion
	
	//Constructor for HP Potion -object
	public HpPotion()
	{
		super("HP Potion", 1);
		this.hpGain = 15;
	}
	
	//Returns number of HP points restored
	public int getHPGain()
	{
		return this.hpGain;
	}
	
	//Adds HP points to player character and updates HP bar when pressing Use-button
	public void use(Player player, JProgressBar healthBar)
	{
		player.setHP(player.getHP() + this.hpGain);
		healthBar.setValue(player.getHP());
		healthBar.setString("Health " + player.getHP() + "/" + player.getMaxHP());
		takeFromCount(1);
	}
	
	//Shows dialog with information about HP Potions when pressing Examine-button
	public void examine(JFrame frame)
	{
		String examineMessage = "HP Potion - Heals 15 HP\n\nCurrent HP Potions in inventory: " + getCount();
		UIManager.put("OptionPane.okButtonText", "Close");
		UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
		JOptionPane.showMessageDialog(frame, examineMessage, "HP Potion", JOptionPane.PLAIN_MESSAGE);
	}
}