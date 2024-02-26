import javax.swing.*;

/*Similar to Background class, this class is the collection of game's consoles to call, not describe any objects.
 * So all are static and all consoles in the game will appear as Message Dialog Boxes.*/

public class GameConsole {
	
	public static int CallStartConsole()
	{
		String StartConsole[] = {"START NEW GAME", "LOAD GAME", "QUIT"};
		JFrame frame = new JFrame();
		return JOptionPane.showOptionDialog(frame, "Would you like to start a new game?", "Game Start:", 
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, StartConsole, StartConsole[0]); //This is the start console.
	}
	
	public static int CallMainConsole(Game game)
	{
		/*The name of the player character is appeared in the top bar of the main console's Dialog.
		 * The text are appeared in the main console show which room players are located in and which direction we can go.*/
		String[] MainConsole = new String[7];
		JFrame frame = new JFrame();
		MainConsole[0] = "MOVE"; // button 0 is move to the other room.
		MainConsole[1] = "CHECK INVENTORY"; // button 1 is show the inventory of the character.
		MainConsole[2] = "CHECK ITEMS IN THE ROOM"; // button 2 is show the list of item in the current position room that can be picked up.
		MainConsole[3] = "PICK UP AN ITEM OF THE ROOM"; // button 3 is the action pick up an item in the room.
		MainConsole[4] = "DROP AN ITEM TO THE ROOM"; // button 4 is the action drop an item in the inventory.
		MainConsole[5] = "RESTART"; // button 6 is restart the game.
		MainConsole[6] = "EXIT GAME"; // button 6 is exit the game.
		return JOptionPane.showOptionDialog(frame, game.MovingInformation(), game.gamePlayer().getName(), JOptionPane.YES_NO_OPTION, 
				JOptionPane.INFORMATION_MESSAGE, null, MainConsole, MainConsole[1]); // This is the main console.
	}
	
	public static int CallRestartConsole()
	{
		String[] RestartConsole = {"LOAD GAME", "RESTART", "Cancel"};
		JFrame frame = new JFrame();
		return JOptionPane.showOptionDialog(frame, "Would you like to load from saved?", "Restart:", 
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, RestartConsole, RestartConsole[1]);
	}
	
	public static int CallExitConsole()
	{
		String[] ExitConsole = {"SAVE GAME", "DON'T SAVE", "Cancel"};
		JFrame frame = new JFrame();
		return JOptionPane.showOptionDialog(frame, "Would you like to save your game?", "Exit game:", 
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, ExitConsole, ExitConsole[0]);
	}
	
	public static Item PickupItemConsole(Room X)
	{
	    return (Item)JOptionPane.showInputDialog(null, "Choose the item you want to pick up:", "Pick up item.", 
	    										JOptionPane.QUESTION_MESSAGE, null, X.getItemList().toArray(), X.getItem(0));
	}
	
	public static Item DropItemConsole(PlayerCharacter Player1)
	{
	    return (Item)JOptionPane.showInputDialog(null, "Choose the item you want to drop:", "Drop item.", 
	    								JOptionPane.QUESTION_MESSAGE, null, Player1.getInventory().toArray(), Player1.getItem(0));
	}
	
	public static int CallMoveConsole()
	{
		String MoveConsole[] = {"NORTH", "WEST", "SOUTH", "EAST", "Cancel"};
		JFrame frame = new JFrame();
		return JOptionPane.showOptionDialog(frame, "Move to the other rooms", "Chose the direction you want to go: ",
		JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, MoveConsole, MoveConsole[0]); // This is the console for moving ability.
	}
}
