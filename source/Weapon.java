public class Weapon extends Item
{
	private int power;	//Attack power of weapon
	
	//Constructor for weapon
	public Weapon(String name, int attack)
	{
		super(name);
		this.power = attack;
	}
	
	//Returns weapon's attack power
	public int getAttack()
	{
		return this.power;
	}
}