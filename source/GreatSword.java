import javax.swing.*;
import java.awt.*;

public class GreatSword extends Weapon
{
	//Constructor for Greatsword
	public GreatSword()
	{
		super("Greatsword", 10);
	}
	
	//Equips weapon when pressing Use-button
	public void use(Player player, JTextArea textArea)
	{
		player.equipWeapon(this);
		textArea.setText("You equipped the Greatsword");
	}
	
	//Shows dialog with information about Greatsword when pressing Examine-button
	public void examine(JFrame frame)
	{
		String examineMessage = "Greatsword - A heavy sword that does massive damage to enemies\n\nAttack Power: 10";
		UIManager.put("OptionPane.okButtonText", "Close");
		UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
		JOptionPane.showMessageDialog(frame, examineMessage, "Greatsword", JOptionPane.PLAIN_MESSAGE);
	}
}