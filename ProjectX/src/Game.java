import javax.swing.*;

// This class describe the game world.
public class Game {
	private Room[] Map; //The map of the game is the list of room, for this game we make 5 rooms only.
	private Room North, West, East, South, Middle; //I make 5 more pointers to make my works easier.
	private PlayerCharacter Player1;
	
	//While the game running, we might need to access to the character.
	public PlayerCharacter gamePlayer() /*Method 1*/
	{
		return Player1;
	}
	
	//The method 2 is the constructor, it'll build the map of the game.
	public Game() /*Method 2*/
	{
		Map = new Room[5]; //The game contain 5 rooms.
		Map[0] = Middle = new Room(0); //In this game, we'll make the middle room is room 0,
		Map[1] = North = new Room(1); //North room is room number 1,
		Map[2] = West = new Room(2);	//West room is number 2,
		Map[3] = South = new Room(3); //South room is number 3,
		Map[4] = East = new Room(4); /*And East room is room number 4.
		The room numbers are used for saving and loading purpose. */
		Middle.setEastRoom(East);
		Middle.setSouthRoom(South);
		Middle.setNorthRoom(North);
		Middle.setWestRoom(West);
		Player1 = new PlayerCharacter();
	}
	
	//Method 3 describe the moving ability of the character from a room to the other rooms around it.
	public void MoveAbility() /*Method 3*/
	{
		JFrame frame = new JFrame();
		int MoveOption = GameConsole.CallMoveConsole();
		/*We got an integer because it's the choices:
		 * choice 0 is NORTH
		 * choice 1 is WEST
		 * choice 2 is SOUTH
		 * choice 3 is EAST
		 * and choice 4 is Cancel or do nothing*/
		switch(MoveOption)
		{
			case 0:
				if(Player1.currentPosition().AvailableToMoveNorth()) Player1.moveToNorth();
				else JOptionPane.showMessageDialog(frame, "There is no room at North, choose another way.", "Can't move!", JOptionPane.ERROR_MESSAGE);
				break;
			case 1:
				if(Player1.currentPosition().AvailableToMoveWest()) Player1.moveToWest();
				else JOptionPane.showMessageDialog(frame, "There is no room at West, choose another way.", "Can't move!", JOptionPane.ERROR_MESSAGE);
				break;
			case 2:
				if(Player1.currentPosition().AvailableToMoveSouth()) Player1.moveToSouth();
				else JOptionPane.showMessageDialog(frame, "There is no room at South, choose another way.", "Can't move!", JOptionPane.ERROR_MESSAGE);
				break;
			case 3:
				if(Player1.currentPosition().AvailableToMoveEast()) Player1.moveToEast();
				else JOptionPane.showMessageDialog(frame, "There is no room at East, choose another way.", "Can't move!", JOptionPane.ERROR_MESSAGE);
				break;
		}
	}
	
	/*Method 4 show full of the current inventory list of player character,
	 * Written as "1. Item1 2. Item2 ........". */
	public void showCharacterInventory() /*Method 4*/
	{
		JFrame frame = new JFrame();
		if(Player1.getInventory().isEmpty())
			JOptionPane.showMessageDialog(frame, "Your inventory is empty.", "You have nothing yet.", JOptionPane.INFORMATION_MESSAGE);
		
		else JOptionPane.showMessageDialog(frame, Player1.inventoryList(), "Your inventory: ", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/*Method 5 show full list of the current items that are available to pick up in the room,
	 * Written as "1. Item1 2. Item2 ........". */
	public void ShowItemInRoom() /*Method 5*/
	{
		JFrame frame = new JFrame();
		if(Player1.getInventory().isEmpty())
			JOptionPane.showMessageDialog(frame, "Your current room has no items.", 
					"Nothing to pick up.", JOptionPane.INFORMATION_MESSAGE);
		
		else JOptionPane.showMessageDialog(frame, Player1.currentPosition().AvailableItemList(), 
				"Items in your current room: ", JOptionPane.INFORMATION_MESSAGE);
	}
	
	//Method 6 describe the ability to pick up an item in the current position room of character.
	public void PickupItemAbility() /*Method 6*/
	{
		if(Player1.currentPosition().getItemList().isEmpty())
		{
			//First of all, you only can pick up item if the room have items to pick up.
			JFrame parent = new JFrame();
			JOptionPane.showMessageDialog(parent, "There is no items to pick up!", "Sorry!", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			//If the room has items, we can make a choice of which item we want to pick up.
			Item input = GameConsole.PickupItemConsole(Player1.currentPosition());
		    if(input != null) Player1.pickupItem(input); /* Pick up the item.
		    The input will be null if we click "Cancel" button. If it's null, just do nothing. */
		}
	}
	
	//Method 7 describe the ability to drop an item from the inventory of the character to the current position room.
	public void DropItemAbility() /*Method 7*/
	{
		if(Player1.getInventory().isEmpty())
		{
			//Similar to pick up method, Your inventory should have something to drop.
			JFrame parent = new JFrame();
			JOptionPane.showMessageDialog(parent, "You have no item to pick up now!", "Sorry!", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			//If you have some, make a choice of item you want to drop.
		    Item input = GameConsole.DropItemConsole(Player1);
		    if(input != null) Player1.dropItem(input); /* Drop down the item.
		    The input will be null if we click "Cancel" button. If it's null, just do nothing. */
		}
	}
	
	//Method 8, 9, 10 is the features to start, restart and exit the game. They contain the save/load features.
	public void StartGame() throws Exception /*Method 8*/
	{
		while(Player1.getName() == null)
		/*The purpose of this loop is make the start console always appear if players click "Cancel" button
		  when create character's name*/
		{
			int n = GameConsole.CallStartConsole();
			
			if(n == 0) //n = 0 when player click the "START NEW GAME" button
			{
				String PlayerName = JOptionPane.showInputDialog(null, "Enter Your Character Name:", "Create a new character.", 
						JOptionPane.INFORMATION_MESSAGE); 
				/*If player click "Cancel" button in this Dialog, there's no name to create a new character.
				 * That make the loop keep continuing and the start console appear until we get a name to make a new character.
				 * In that case, we can create characters, this loop will end and the program will move to another console*/
				if(PlayerName != null) Player1 = new PlayerCharacter(PlayerName, Middle);
				Background.NewMap(Map);
			}
			
			else if(n == 1) Background.LoadGame(Player1, Map);
			// n = 1 when player click the "LOAD GAME" button, of course Player1 will have a name from saved data.
				
			else return; //n = 2 when we click "QUIT" button, we not gonna do anything next.
		}
	}
	
	public void RestartGame()  throws Exception /*Method 9*/
	{
		int RestartOption = GameConsole.CallRestartConsole();
		//The restart option is only work if players don't click "Cancel" button, that make RestartOption = 2.
		
		if(RestartOption == 0) // option 0 is players agree to load the game by click "Yes" button.
		{
			//The first thing we have to do before load the game is clean out everything.
			Player1 = new PlayerCharacter();
			for(int i = 0; i < 5; i ++) Map[i].cleanRoom();
			Background.LoadGame(Player1, Map);
		}
		
		else if(RestartOption == 1) /* option 1 is players agree to start a new game.
		 							By click "No" button, the choose to start a new game instead of load from saved. */
		{
			// The first thing need to do is create character.
			String PlayerName = JOptionPane.showInputDialog(null, "Enter Your Character Name:", "Create a new character.", 
					JOptionPane.INFORMATION_MESSAGE);
			
			if(PlayerName != null) // If player enter a name and click "OK", we do this stuffs:
			{
				Player1 = new PlayerCharacter(PlayerName, Middle);
				for(int i = 0; i < 5; i ++) Map[i].cleanRoom();
				Background.NewMap(Map);
			}
			/*This method will do nothing if  player
		 	click "Cancel" button (RestartOption = 2) in the asking name Dialog*/
		}
	}
	
	public boolean ExitGame() throws Exception /*Method 10*/
	{
		int ExitOption = GameConsole.CallExitConsole();
		
		if(ExitOption == 0)
		{
			//If player put "YES" button, that make ExitOption = 0, save every thing into files before exit.
			Background.SaveGame(Player1, Map);
			return true;
		} 
		
		else if(ExitOption == 1) return true; /*If players don't want to save and click "NO" button, that's mean ExitOption = 1
		just change the exit value to break out the loop. */
		
		else return false; //If players put "Cancel" button, that's mean ExitOption = 2, we just do nothing. 
	}
	
	/*Method 11 is the String that describe the current position and which ways player can go in that position.
	 * This String will appear in the main console. */
	public String MovingInformation() /*Method 11*/
	{
		String s = "You are current locate in the ";
		String position = new String();
		switch(Player1.currentPosition().getRoomNumber())
		{
			case 0:
				position = "Middle";
				break;
			case 1:
				position = "North";
				break;
			case 2:
				position = "West";
				break;
			case 3:
				position = "South";
				break;
			case 4:
				position = "East";
				break;
		}
		s += position + " room.\nAt your current room, you can go: ";
		s += Player1.currentPosition().AvailableToMoveNorth()? "North  " : "";
		s += Player1.currentPosition().AvailableToMoveWest()? "West  " : "";
		s += Player1.currentPosition().AvailableToMoveSouth()? "South  " : "";
		s += Player1.currentPosition().AvailableToMoveEast()? "East  " : "";
		return s;
	}
}
