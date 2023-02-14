import java.util.Random;

public class Golem extends Enemy
{
	//Constructor for Golem enemy
	public Golem()
	{
		super("Golem",3,150,13,50);
	}
	
	//Attacks player with random damage within specified range
	public void attack(Player player)
	{
		setDamage((int)(Math.random() * ((getAttack() + 3) - (getAttack() - 1)) + (getAttack() - 1)));
		player.setHP(player.getHP() - getDamage());
	}
	
	//Calculates random chance of dropping loot after dying. Returns boolean of whether will drop loot or not
	public boolean drop()
	{
		Random rn = new Random();
		int dropChance = rn.nextInt(4);
		
		if(dropChance == 0)
			return true;
		else
			return false;
	}
	
	//Returns loot item
	public Item getDrop()
	{
		return new ApPotion();
	}
}