import java.io.*;
import java.util.ArrayList;

/*This class is not to describe objects.
 * This is the collection of the ways that we use to start, save, and load everything
 * The methods in here is all static. */

public class Background {
	
	/*This method describe how to start a new map from default data.
	 * It is used for start a new game and restart the game. */
	public static void NewMap(Room Map[]) throws Exception 
	{
		 /* At the beginning, each room will have 8 items to pick up, except the middle room.
		 * That's mean we need to move around to pick up item.*/
		LoadRoom(Map[1], "DefaultNorthRoom.txt");
		/*North room has: Armour Coating, Efficiency Control, Shield Generator, EXP Capsule, 
		 					Special Skill Opening Card, Mighty Spear, Blue Sapphire, and Galaxy Orb. */
		LoadRoom(Map[3], "DefaultSouthRoom.txt");
		/*South room has: Bank Chip, Fake Ruby, Picture Of Lover, Evasion Safeguard, 
		 					Repair Kit, Perfect Sight, Blue Mountain Coffee, and Supercluster Orb. */
		LoadRoom(Map[2], "DefaultWestRoom.txt");
		/*West room has: Can Coffee, Fake Sapphire, Lucky Coin, Tooth Of Beast, 
		 					Shield Kit, Goggles, Skill Strengthening Card, Quasar Orb. */
		LoadRoom(Map[4], "DefaultEastRoom.txt");
		/*East room has: Claw of Beast, Extra Fuel Tank, Defence Safeguard, Recovery Set, 
		 					Item Seeker Unit, Red Ruby, Solar Orb, Speaker. */
		
		/*Note: All of the items' name above are taken from Ace Online of SubaGames Company. */
	}
	
	//This method describe how to load the game from saved data.
	public static void LoadGame(PlayerCharacter Player1, Room Map[]) throws Exception 
	{
		//if we want to load game, load all from saved data.
		int RoomNumber = LoadCharacter(Player1, "SavedCharacter.txt");
		/*The LoadCharacter method is already load the character's name and the inventory.
		 * it is returned the room number which determined the current position of character which will be solved here */
		Player1.setRoom(Map[RoomNumber]);
		//And then load all room from saved.
		LoadRoom(Map[0], "SavedMiddleRoom.txt");
		LoadRoom(Map[1], "SavedNorthRoom.txt");
		LoadRoom(Map[3], "SavedSouthRoom.txt");
		LoadRoom(Map[2], "SavedWestRoom.txt");
		LoadRoom(Map[4], "SavedEastRoom.txt");
	}
	
	//This method describe how to save the game to files.
	public static void SaveGame(PlayerCharacter Player1, Room Map[]) throws Exception 
	{
		//It save all character and rooms' data into files.
		SaveCharacter(Player1, "SavedCharacter.txt");
		saveRoom(Map[0], "SavedMiddleRoom.txt");
		saveRoom(Map[1], "SavedNorthRoom.txt");
		saveRoom(Map[2], "SavedWestRoom.txt");
		saveRoom(Map[3], "SavedSouthRoom.txt");
		saveRoom(Map[4], "SavedEastRoom.txt");
	}

//This method describe the way to read the items' name in a file to a Room.
	public static void LoadRoom(Room X, String FileName) throws Exception 
	{
		//Because I made all the files have each line is a name of an items.
		File file = new File(FileName);
		//So I use Scanner to read each line of the text.
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		String st;
		while ((st = br.readLine()) != null) 
		{
			X.addItem(new Item(st)); 
			// Each line in the text is the name of an item.
		}
		br.close();
	}
	
	/*This method describe the way to read character data in a file.
	 * This return the saved room number which determined the current position of the character which is hard to solve in here */
	public static int LoadCharacter(PlayerCharacter Player1, String FileName) throws Exception 
	{
		//Because I made all the files have each line is a name of an items.
		File file = new File(FileName);
		//So I use Scanner to read each line of the text.
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		String st;
		ArrayList<String> list = new ArrayList<String>();
		while ((st = br.readLine()) != null) 
		{
			list.add(st); /* Each line in the text is the name of an item.
			except the first line is the character's name and the second line is the room number*/
		}
		/*After this, we have a list of String that contains:
		 * The first line is the name of characters.
		 * The second line is the room number of where the character locate.
		 * And the other lines are the items' name in character's inventory. */
		br.close();
		
		String CharacterSavedName = list.get(0); //The character's name is the first line of the text.
		Player1.setName(CharacterSavedName);
		list.remove(0); //Because we got the name so we don't need the first line anymore.
		int savedRoomNumber = Integer.parseInt(list.get(0)); // The current room number is in the second line of the text.
		list.remove(0); //Because we got the room number so we don't need the second line line anymore.
		/*The savedRoomNumber determined the current position room, it'll be solve outside, inside this static method is really hard.
		 * Every thing left from the list is the items in the character's inventory. */
		if(!list.isEmpty())
		{
			for(int i = 0; i < list.size(); i ++)
			{
				Player1.addItem(new Item(list.get(i))); 
				// Each line in the text is the name of an item.
			}
		}
		return savedRoomNumber;
		
	}
	
	//This method describe the way to save the items' data from a room to a file
	public static void saveRoom(Room X, String OutputFileName) throws IOException 
	{
		String s = new String(); //First, I need to have a String of items' list of the room.
		int i;
		for(i = 0; i < X.numberOfItem(); i++) //And this string has to be written line by line.
		{
			s += X.getItem(i).toString() + "\n"; 
			//Each line is the name of an item.
		}
		byte data[] = s.getBytes(); //And then get every single character of that String.
		FileWriter out = null;
		try 
	    {
	         out = new FileWriter(OutputFileName);
	         //And then use the byte to write all the characters to the file.
	         for(i = 0; i < s.length(); i++) out.write(data[i]); 
	    }
	    finally {} 
		out.close();
	}
	
	//This method describe the way to save the character data to a file
	public static void SaveCharacter(PlayerCharacter Player1, String OutputFileName) throws IOException 
	{
		String s = new String(); //First, I need a String to put character's data in.
		s += Player1.getName(); //The first line of this String will be the name of the character.
		s += "\n" + Player1.currentPosition().getRoomNumber() + "\n"; //The second line will be the room number where the character located.
		int i;
		//And then the other next lines will be the name of the items in the inventory.
		if(!Player1.getInventory().isEmpty())
		{
			for(i = 0; i < Player1.numberOfItem(); i++) 
			{
				s += Player1.getItem(i).toString() + "\n"; 
				//Each line is the name of an item.
			}
		}
		//Similar to save room method above:
		byte data[] = s.getBytes(); //Get every single character of that String.
		FileWriter out = null;
		try 
	    {
	         out = new FileWriter(OutputFileName);
	       //And then use the byte to write all the characters to the file.
	         for(i = 0; i < s.length(); i++) out.write(data[i]);     
	    }
	    finally {} 
		out.close();
	}
}

