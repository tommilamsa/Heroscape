import java.lang.Math;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Player
{
	private String playerName;	//Player's name
	private int playerLevel;	//Player's current level
	private int currentPlayerXP;	//Player's current XP
	private int maxPlayerXP;	//Player's maximum XP
	private int currentPlayerHP;	//Player's current HP
	private int maxPlayerHP;	//Player's maximum HP
	private int currentPlayerAP;	//Player's current AP
	private int maxPlayerAP;	//Player's maximum AP
	private int baseAttack;	//Player's base attack at current level
	private int damage;	//Damage done to enemy this turn
	private Weapon weapon;	//Currently equipped weapon
	private int weaponAttack;	//Attack power of current weapon
	private boolean blitzLearned;	//Checks if player has learned the Blitz-ability
	private boolean start;	//Checks if player has just started the game
	private boolean firstFight;	//Checks if player is entering their first battle
	private boolean a1;	//Checks if player has examined the Mountains-location
	private boolean a2;	//Checks if player has examined the Desert-location
	private boolean a3;	//Checks if player has examined the Beach-location
	private boolean b3;	//Checks if player has examined the River-location
	private boolean c2;	//Checks if player has examined the Plains-location
	private boolean c3;	//Checks if player has examined the Lake-location
	private Map<String,Item> inventory = new HashMap<>();	//Hashmap used to store player's items
	
	//Constructor for player character
	public Player(String nameInput)
	{
		this.playerName = nameInput;
		this.playerLevel = 1;
		this.currentPlayerXP = 0;
		this.maxPlayerXP = 100;
		this.currentPlayerHP = 100;
		this.maxPlayerHP = 100;
		this.currentPlayerAP = 50;
		this.maxPlayerAP = 50;
		this.baseAttack = 5;
		this.weapon = new Weapon("Fists", 0);
		this.weaponAttack = 0;
		blitzLearned = false;
		start = true;
		firstFight = true;
		a1 = false;
		a2 = false;
		a3 = false;
		b3 = false;
		c2 = false;
		c3 = false;
	}
	
	//Returns player's name
	public String getName()
	{
		return this.playerName;
	}
	
	//Returns player's level
	public int getLevel()
	{
		return this.playerLevel;
	}
	
	//Sets new XP for player
	public void setXP(int newXP)
	{
		this.currentPlayerXP = newXP;
	}
	
	//Returns player's current XP
	public int getXP()
	{
		return this.currentPlayerXP;
	}
	
	//Returns player's maximum XP
	public int getMaxXP()
	{
		return this.maxPlayerXP;
	}
	
	//Sets new HP for player
	public void setHP(int newHP)
	{
		if(newHP > this.maxPlayerHP)
			this.currentPlayerHP = this.maxPlayerHP;
		else
			this.currentPlayerHP = newHP;
	}
	
	//Returns player's current HP
	public int getHP()
	{
		return this.currentPlayerHP;
	}
	
	//Returns player's maximum HP
	public int getMaxHP()
	{
		return this.maxPlayerHP;
	}
	
	//Sets new AP for player
	public void setAP(int newAP)
	{
		if(newAP > this.maxPlayerAP)
			this.currentPlayerAP = this.maxPlayerAP;
		else if(newAP < 0)
			this.currentPlayerAP = 0;
		else
			this.currentPlayerAP = newAP;
	}
	
	//Returns player's current AP
	public int getAP()
	{
		return this.currentPlayerAP;
	}
	
	//Returns player's maximum AP
	public int getMaxAP()
	{
		return this.maxPlayerAP;
	}
	
	//Sets boolean when player has learned Blitz
	public void setBlitz()
	{
		blitzLearned = true;
	}
	
	//Returns Blitz-boolean
	public boolean getBlitz()
	{
		return blitzLearned;
	}
	
	//Sets boolean when player has started the game
	public void setStart()
	{
		start = false;
	}
	
	//Returns start-boolean
	public boolean getStart()
	{
		return start;
	}
	
	//Sets boolean when player enters first battle
	public void setFight()
	{
		firstFight = false;
	}
	
	//Returns First fight -boolean
	public boolean getFight()
	{
		return firstFight;
	}
	
	//Sets boolean when player examines Mountains-location
	public void setA1()
	{
		a1 = true;
	}
	
	//Returns a1-boolean
	public boolean getA1()
	{
		return a1;
	}
	
	//Sets boolean when player examines Desert-location
	public void setA2()
	{
		a2 = true;
	}
	
	//Returns a2-boolean
	public boolean getA2()
	{
		return a2;
	}
	
	//Sets boolean when player examines Beach-location
	public void setA3()
	{
		a3 = true;
	}
	
	//Returns a3-boolean
	public boolean getA3()
	{
		return a3;
	}
	
	//Sets boolean when player examines River-location
	public void setB3()
	{
		b3 = true;
	}
	
	//Returns b3-boolean
	public boolean getB3()
	{
		return b3;
	}
	
	//Sets boolean when player examines Plains-location
	public void setC2()
	{
		c2 = true;
	}
	
	//Returns c2-boolean
	public boolean getC2()
	{
		return c2;
	}
	
	//Sets boolean when player examines Lake-location
	public void setC3()
	{
		c3 = true;
	}
	
	//Returns c3-boolean
	public boolean getC3()
	{
		return c3;
	}
	
	//Adds item to player's inventory
	public void addItem(Item item)
	{
		if(item instanceof Consumable)
		{
			if(item instanceof HpPotion)
			{
				String itemName = "HP Potion";
				
				if(!inventory.containsKey(itemName))
				{
					inventory.put(itemName, new HpPotion());
				}
				else
				{
					HpPotion potion = (HpPotion)inventory.get(itemName);
					potion.addToCount(1);
					inventory.put(itemName, potion);
				}
			}
			else if(item instanceof ApPotion)
			{
				String itemName = "AP Potion";
				
				if(!inventory.containsKey(itemName))
				{
					inventory.put(itemName, new ApPotion());
				}
				else
				{
					ApPotion potion = (ApPotion)inventory.get(itemName);
					potion.addToCount(1);
					inventory.put(itemName, potion);
				}
			}
		}
		else
		{
			if(item instanceof Sword)
			{
				String itemName = "Sword";
				
				if(!inventory.containsKey(itemName))
				{
					inventory.put(itemName, new Sword());
				}
			}
			else if(item instanceof GreatSword)
			{
				String itemName = "Greatsword";
				
				if(!inventory.containsKey(itemName))
				{
					inventory.put(itemName, new GreatSword());
				}
			}
			else if(item instanceof MagicSword)
			{
				String itemName = "Magic Sword";
				
				if(!inventory.containsKey(itemName))
				{
					inventory.put(itemName, new MagicSword());
				}
			}
		}
	}
	
	//Returns item from player's inventory
	public Item getItem(String name)
	{
		Item item = inventory.get(name);
		
		return item;
	}
	
	//Returns player's inventory
	public Object[] getInventory()
	{
		
		ArrayList<Object> arrayList = new ArrayList<Object>();
		
		for(Map.Entry<String,Item> item : inventory.entrySet())
		{
			if(item.getValue() instanceof Consumable)
			{
				Consumable consumable = (Consumable)item.getValue();
				if(consumable.getCount() > 0)
				{
					arrayList.add(item.getKey());
				}
			}
			else
			{
				arrayList.add(item.getKey());
			}
		}
		
		Object[] items = arrayList.toArray();
		
		return items;
	}
	
	//Sets new attack power for player
	public void setAttack(int newBaseAttack)
	{
		this.baseAttack = newBaseAttack;
	}
	
	//Returns player's attack power
	public int getAttack()
	{
		return this.baseAttack;
	}
	
	//Equips new weapon for player
	public void equipWeapon(Weapon newWeapon)
	{
		if(this.weapon.getName() == "Fists")
		{
			this.weapon = newWeapon;
			inventory.remove(newWeapon.getName());
			this.weaponAttack = newWeapon.getAttack();
		}
		else
		{
			Weapon oldWeapon = this.weapon;
			this.weapon = newWeapon;
			inventory.remove(newWeapon.getName());
			inventory.put(oldWeapon.getName(), oldWeapon);
			this.weaponAttack = newWeapon.getAttack();
		}
	}
	
	//Returns player's current weapon
	public Weapon getWeapon()
	{
		return this.weapon;
	}
	
	//Returns damage done to enemy this turn
	public int getDamage()
	{
		return this.damage;
	}
	
	//Attacks enemy with random damage within range
	public void attack(Enemy enemy)
	{
		this.damage = (int)(Math.random() * (((this.baseAttack + this.weaponAttack) + 3) - ((this.baseAttack + this.weaponAttack) - 2)) + ((this.baseAttack + this.weaponAttack) - 2));
		enemy.setHP(enemy.getHP() - this.damage);
	}
	
	//Attacks enemy with Super Slash -ability
	public void superSlash(Enemy enemy)
	{
		this.damage = 25;
		enemy.setHP(enemy.getHP() - this.damage);
		setAP(getAP() - 25);
	}
	
	//Attacks enemy with Blitz-ability
	public void blitz(Enemy enemy)
	{
		this.damage = 15;
		enemy.setHP(enemy.getHP() - this.damage);
		setAP(getAP() - 20);
	}
	
	//Attacks enemy with Magic Beam -ability
	public void magicBeam(Enemy enemy)
	{
		this.damage = 90;
		enemy.setHP(enemy.getHP() - this.damage);
		setAP(getAP() - 75);
	}
	
	//Levels up player when enough XP gained
	public void levelUp()
	{
		this.playerLevel = this.playerLevel + 1;
		
		if(this.playerLevel == 2)
		{
			this.maxPlayerXP = 200;
			this.maxPlayerHP = 200;
			this.maxPlayerAP = 75;
			this.baseAttack = 7;
		}
		else if(this.playerLevel == 3)
		{
			this.maxPlayerXP = 300;
			this.maxPlayerHP = 400;
			this.maxPlayerAP = 100;
			this.baseAttack = 10;
		}
		else if(this.playerLevel == 4)
		{
			this.maxPlayerXP = 400;
			this.maxPlayerHP = 500;
			this.maxPlayerAP = 150;
			this.baseAttack = 13;
		}
		else if(this.playerLevel == 5)
		{
			this.maxPlayerXP = 500;
			this.maxPlayerHP = 700;
			this.maxPlayerAP = 200;
			this.baseAttack = 15;
		}
		
		this.currentPlayerXP = 0;
		this.currentPlayerHP = this.maxPlayerHP;
		this.currentPlayerAP = this.maxPlayerAP;
	}
}