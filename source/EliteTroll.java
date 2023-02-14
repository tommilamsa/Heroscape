import java.util.Random;

public class EliteTroll extends Enemy
{
	//Constructor for Elite Troll enemy
	public EliteTroll()
	{
		super("Elite Troll",4,160,15,60);
	}
	
	//Attacks player with random damage within specified range
	public void attack(Player player)
	{
		setDamage((int)(Math.random() * ((getAttack() + 2) - (getAttack() - 2)) + (getAttack() - 5)));
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
		return new HpPotion();
	}
}