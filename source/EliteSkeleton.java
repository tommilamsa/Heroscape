import java.util.Random;

public class EliteSkeleton extends Enemy
{
	//Constructor for Elite Skeleton enemy
	public EliteSkeleton()
	{
		super("Elite Skeleton",4,150,15,50);
	}
	
	//Attacks player with random damage within specified range
	public void attack(Player player)
	{
		setDamage((int)(Math.random() * ((getAttack() + 1) - (getAttack() - 5)) + (getAttack() - 5)));
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