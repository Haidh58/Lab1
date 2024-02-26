// This class is describe an object of a room.

import java.util.ArrayList;

public class Room 
{
	//An object of room contain: 
	private int RoomNumber; //The room number,
	private ArrayList<Item> AvailableItem; //a list of available item that could be picked up
	private Room North, South, West, East; //and the 4 rooms around it. With these fields I can make unlimited rooms if I want.
	
	public Room() {}
	
	//Method 1 is a constructor with room number. The 4 rooms around should be null.

	public Room(int roomNumber) /*Method 1*/
	{
		RoomNumber = roomNumber;
		AvailableItem = new ArrayList<Item>();
		North = South = West = East = null;
	}
	
	/*Method 2, 3, 4, and 5 are used to connect room with room. In other word is connect rooms with each others.
	 *For example: If I want room X become the North room of room Y, that's mean Y will become South room of X.
	 *In that case, I need to set Y.North = X and X.North = Y */
	public void setNorthRoom(Room northRoom) /*Method 2*/
	{
		North = northRoom;
		northRoom.South = this;
	}
	
	public void setSouthRoom(Room southRoom) /*Method 3*/
	{
		South = southRoom;
		southRoom.North = this;
	}
	
	public void setWestRoom(Room westRoom) /*Method 4*/
	{
		West = westRoom;
		westRoom.East = this;
	}
	
	public void setEastRoom(Room eastRoom) /*Method 5*/
	{
		East = eastRoom;
		eastRoom.West = this;
	}
	
	//Method 6, 7, 8, and 9 are just the get methods.
	public Room getNorthRoom() /*Method 6*/
	{
		return North;
	}
	
	public Room getSouthRoom() /*Method 7*/
	{
		return South;
	}
	
	public Room getEastRoom() /*Method 8*/
	{
		return East;
	}
	
	public Room getWestRoom() /*Method 9*/
	{
		return West;
	}
	
	/*Method 10, 11, 12, and 13 are used to check the directions that we want to go are available or not.
	 * For example: we can't go to North if North room is null, because null means no room at there.
	 * So if the North room is not null, that's mean there's a room in there and we can go to North. */
	public boolean AvailableToMoveNorth() /*Method 10*/
	{
		return North != null;
	}
	//Similar to South, East, and West.
	public boolean AvailableToMoveSouth() /*Method 11*/
	{
		return South != null;
	}
	
	public boolean AvailableToMoveEast() /*Method 12*/
	{
		return East != null;
	}
	
	public boolean AvailableToMoveWest() /*Method 13*/
	{
		return West != null;
	}
	
	//Method 14 is a get that get an item in position x of the available item list of the room.
	public ArrayList<Item> getItemList() /*Method 14*/
	{
		return AvailableItem;
	}
	
	/*Method 15 is used when the player character in the room drop an item. 
	 * The dropped item need to be add to the items' list of the room.
	 * It's also used for load feature. */
	public void addItem(Item x) /*Method 15*/
	{
		AvailableItem.add(x);
	}
	
	/*Method 16 is used when the player character in the room pick up an item at the position x of the item list
	 * When the player character pick it up, it need to be removed from the item list of the room. */
	public void loseItem(Item x) /*Method 16*/
	{
		AvailableItem.remove(x);
	}
	
	//Method 17 is the number of items that are available to pick up in the room. This might be used in for loop.
	public int numberOfItem() /*Method 17*/
	{
		return AvailableItem.size();
	}
	
	/*Method 18 is a String that show full list of the current items that are available to pick up in the room,
	 * Written as "1. Item1 2. Item2 ........". */
	public String AvailableItemList() /*Method 18*/
	{
		String list = new String();
		for(int i = 0; i < AvailableItem.size(); i++)
		{
			list += (i + 1) + ". " + AvailableItem.get(i).toString() + "\n";
		}
		return list;
	}
	
	//Method 19 is used to get the item at the position x of the available item list
	public Item getItem(int x) /*Method 19*/
	{
		return AvailableItem.get(x);
	}
	
	//Method 20 is get the room number.
	public int getRoomNumber() /*Method 20*/
	{
		return RoomNumber;
	}
	
	//Method 21 is used for restart feature of the game. It clean the available item list.
	public void cleanRoom() /*Method 21*/
	{
		AvailableItem = new ArrayList<Item>();
	}
}
