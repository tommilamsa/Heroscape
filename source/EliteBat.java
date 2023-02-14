import java.util.Random;

public class EliteBat extends Enemy
{
	//Constructor for Elite Bat enemy
	public EliteBat()
	{
		super("Elite Bat",3,125,12,40);
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
		int dropChance = rn.nextInt(2);
		
		if(dropChance == 0)
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