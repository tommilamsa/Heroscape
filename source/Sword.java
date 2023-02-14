import javax.swing.*;
import java.awt.*;

public class Sword extends Weapon
{
	//Constructor for Sword
	public Sword()
	{
		super("Sword", 5);
	}
	
	//Equips weapon when pressing Use-button
	public void use(Player player, JTextArea textArea)
	{
		player.equipWeapon(this);
		textArea.setText("You equipped the Sword");
	}
	
	//Shows dialog with information about Sword when pressing Examine-button
	public void examine(JFrame frame)
	{
		String examineMessage = "Sword - A basic iron sword\n\nAttack Power: 5";
		UIManager.put("OptionPane.okButtonText", "Close");
		UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
		JOptionPane.showMessageDialog(frame, examineMessage, "Sword", JOptionPane.PLAIN_MESSAGE);
	}
}