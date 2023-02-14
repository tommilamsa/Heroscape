import javax.swing.*;
import java.awt.*;

public class ApPotion extends Consumable
{
	private int apGain; //Number of AP points restored when using potion
	
	//Constructor for AP Potion -object
	public ApPotion()
	{
		super("AP Potion", 1);
		this.apGain = 25;
	}
	
	//Returns number of AP points restored
	public int getAPGain()
	{
		return this.apGain;
	}
	
	//Adds AP points to player character and updates AP bar when pressing Use-button
	public void use(Player player, JProgressBar apBar)
	{
		player.setAP(player.getAP() + this.apGain);
		apBar.setValue(player.getAP());
		apBar.setString("AP " + player.getAP() + "/" + player.getMaxAP());
		takeFromCount(1);
	}
	
	//Shows dialog with information about AP Potions when pressing Examine-button
	public void examine(JFrame frame)
	{
		String examineMessage = "AP Potion - Gives 25 AP\n\nCurrent AP Potions in inventory: " + getCount();
		UIManager.put("OptionPane.okButtonText", "Close");
		UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
		JOptionPane.showMessageDialog(frame, examineMessage, "AP Potion", JOptionPane.PLAIN_MESSAGE);
	}
}