public class Consumable extends Item
{
	private int itemCount;	//Number of item in player's inventory
	
	//Constructor for Consumable-object
	public Consumable(String name, int count)
	{
		super(name);
		this.itemCount = count;
	}
	
	//Adds more items to count
	public void addToCount(int plus)
	{
		this.itemCount = this.itemCount + plus;
	}
	
	//Takes items from count
	public void takeFromCount(int minus)
	{
		this.itemCount = this.itemCount - minus;
	}
	
	//Returns number of items in player's inventory
	public int getCount()
	{
		return this.itemCount;
	}
}