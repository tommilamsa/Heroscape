public class Enemy
{
	private String enemyName;	//Name of enemy
	private int enemyLevel;	//Level of enemy
	private int currentEnemyHP;	//Enemy's current HP
	private int maxEnemyHP;	//Enemy's maximum HP
	private int attackPower;	//Enemy's attack power
	private int xpGain;	//XP gained from defeating enemy
	private int damage;	//Damage enemy does to player this turn
	
	//Constructor for enemy
	public Enemy(String name, int level, int hp, int power, int xp)
	{
		this.enemyName = name;
		this.enemyLevel = level;
		this.maxEnemyHP = hp;
		this.currentEnemyHP = this.maxEnemyHP;
		this.attackPower = power;
		this.xpGain = xp;
	}
	
	//Sets new name for enemy
	public void setName(String newName)
	{
		this.enemyName = newName;
	}
	
	//Returns enemy's name
	public String getName()
	{
		return this.enemyName;
	}
	
	//Returns enemy's level
	public int getLevel()
	{
		return this.enemyLevel;
	}
	
	//Sets new HP for enemy
	public void setHP(int newHP)
	{
		this.currentEnemyHP = newHP;
	}
	
	//Returns enemy's current HP
	public int getHP()
	{
		return this.currentEnemyHP;
	}
	
	//Returns enemy's maximum HP
	public int getMaxHP()
	{
		return this.maxEnemyHP;
	}
	
	//Returns enemy's attack power
	public int getAttack()
	{
		return this.attackPower;
	}
	
	//Sets damage done this turn
	public void setDamage(int newDamage)
	{
		this.damage = newDamage;
	}
	
	//Return damage done this turn
	public int getDamage()
	{
		return this.damage;
	}
	
	//Returns XP gained when defeating enemy
	public int getXP()
	{
		return this.xpGain;
	}
	
	//Attacks player
	public void attack(Player player)
	{
		player.setHP(player.getHP() - this.attackPower);
	}
	
	//Returns boolean of whether will drop loot or not
	public boolean drop()
	{
		return false;
	}
	
	//Returns loot item
	public Item getDrop()
	{
		return new ApPotion();
	}
}