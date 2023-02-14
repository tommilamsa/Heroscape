import javax.swing.*;
import java.awt.*;

public class MagicSword extends Weapon
{
	//Constructor for Magic Sword
	public MagicSword()
	{
		super("Magic Sword", 20);
	}
	
	//Equips weapon when pressing Use-button
	public void use(Player player, JTextArea textArea)
	{
		player.equipWeapon(this);
		textArea.setText("You equipped the Magic Sword\n\nYou can now use the ability: Magic Beam!");
	}
	
	//Shows dialog with information about Magic Sword when pressing Examine-button
	public void examine(JFrame frame)
	{
		String examineMessage = "Magic Sword - Sword strengthened by a mystical magical energy\n\nAttack Power: 20";
		UIManager.put("OptionPane.okButtonText", "Close");
		UIManager.put("OptionPane.messageFont", new Font("", Font.PLAIN, 20));
		JOptionPane.showMessageDialog(frame, examineMessage, "Magic Sword", JOptionPane.PLAIN_MESSAGE);
	}
}