import java.util.Random;

public class Bat extends Enemy
{
	//Constructor for Bat enemy
	public Bat()
	{
		super("Bat",1,50,5,15);
	}
	
	//Attacks player with random damage within specified range
	public void attack(Player player)
	{
		setDamage((int)(Math.random() * ((getAttack() + 3) - (getAttack() - 2)) + (getAttack() - 2)));
		player.setHP(player.getHP() - getDamage());
	}
	
	//Calculates random chance of dropping loot after dying. Returns boolean of whether will drop loot or not
	public boolean drop()
	{
		Random rn = new Random();
		int dropChance = rn.nextInt(4);
		
		if((dropChance == 0) || (dropChance == 1) || (dropChance == 2))
			return true;
		else
			return false;
	}
	
	//Returns loot item
	public Item getDrop()
	{
		return new HpPotion();
	}
}