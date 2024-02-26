// This class is describe an object of a player character.

import java.util.ArrayList;

public class PlayerCharacter 
{
	// In this game, a player character object has:
	private String Name; //The character's name,
	private ArrayList<Item> Inventory; //a list of inventory,
	private Room Position; // and the current position of the character.
	
	//Method 1 is a regular non-constructor method.
	public PlayerCharacter() /*Method 1*/
	{
		Name = null;
		Position = null;
		Inventory = new ArrayList<Item>();
	}
	
	//Method 2 is a constructor with name and the starting room at the beginning.
	public PlayerCharacter(String name, Room position) /*Method 2*/
	{
		Name = new String(name);
		Inventory = new ArrayList<Item>();
		Position = position;
	}
	
	//Method 3 is used to set character's name.
	public void setName(String CharacterName) /*Method 3*/
	{
		Name = CharacterName;
	}
	
	//Method 4 is used to set character's current position.
	public void setRoom(Room X) /*Method 4*/
	{
		Position = X;
	}
	
	/*Method 5 describe the ability to drop an item at the position x of the inventory to the current position room 
	 * So the inventory will lose that item and the position room will gain that item. */
	public void dropItem(Item x) /*Method 5*/
	{
		Position.addItem(x);
		Inventory.remove(x);
	}
	
	/*Method 6 describe the ability to pick up an item in the position room and add it into the inventory.
	 * That's mean the position room will lose that item. */
	public void pickupItem(Item x) /*Method 6*/
	{
		Inventory.add(x);
		Position.loseItem(x);
	}
	
	//Method 7 is a get method. It show the current position of the player character.
	public Room currentPosition() /*Method 7*/
	{
		return Position;
	}
	
	/*Method 8, 9, 10, and 11 describe the moving ability of character.
	 *For example: if I want to go North, I need to change my position from my current room to the North room of my current room.
	 *That's mean my Position need to be set to Position.North */
	public void moveToNorth() /*Method 8*/
	{
		Position = Position.getNorthRoom();
	}
	//Similar to South, East, and West room.
	public void moveToSouth() /*Method 9*/
	{
		Position = Position.getSouthRoom();
	}
	
	public void moveToWest() /*Method 10*/
	{
		Position = Position.getWestRoom();
	}
	
	public void moveToEast() /*Method 11*/
	{
		Position = Position.getEastRoom();
	}
	
	//Method 12 is just get the player character's name.
	public String getName() /*Method 12*/
	{
		return Name;
	}
	
	//Method 13 is the number of items in the inventory.
	public int numberOfItem() /*Method 13*/
	{
		return Inventory.size();
	}
	
	/*Method 14 is a String that show full of the current inventory list of player character,
	 * Written as "1. Item1 2. Item2 ........". */
	public String inventoryList() /*Method 14*/
	{
		String list = new String();
		for(int i = 0; i < Inventory.size(); i++)
		{
			list += (i + 1) + ". " + Inventory.get(i).toString() + "\n";
		}
		return list;
	}
	
	//Method 15 return the inventory of the character.
	public ArrayList<Item> getInventory() /*Method 15*/
	{
		return Inventory;
	}
	
	//Method 16 is used to get the item at the position x of the inventory.
	public Item getItem(int x) /*Method 16*/
	{
		return Inventory.get(x);
	}
	
	//Method 17 is add an item to into the inventory, this is used for load feature.
	public void addItem(Item x) /*Method 17*/
	{
		Inventory.add(x);
	}
}
