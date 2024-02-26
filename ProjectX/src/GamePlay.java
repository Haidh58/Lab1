public class GamePlay {
	
	/*This class is the main program to running the game.
	 * It contain the main method and call all the abilities of the game. */
	
	public static void main(String[] args) throws Exception 
	{
		Game game = new Game();
		// The first thing we do is show the start console.
		game.StartGame();
		/*if player click "QUIT" button in the start console, there's no character is created
		 * we not gonna do anything next.*/
		if(game.gamePlayer().getName() == null) return;
		
		boolean exit = false;
		/*The while loop below will keep the game running.
		 * It will stop when we chose exit button.
		 * In that case, the value of the variable exit will be changed from false to true to stop the loop. */
		while(!exit)
		{
			int n = GameConsole.CallMainConsole(game);
			/*Because we have the while loop, so we don't have to worry about players click the "Cancel" button in any Dialog, 
			 * 		until the exit value is still false, the main console will be appear anyway after every single steps */
			switch(n) // n is the choice of the players at the main console.
			{
				case 0: // choice 0 is move to the other rooms.
					game.MoveAbility();
					break;
					
				case 1: // choice 1 is show the inventory of the character.
					game.showCharacterInventory();
					break;
					
				case 2: //choice 2 is show the list of item in the current position room that can be picked up.
					game.ShowItemInRoom();
					break;
					
				case 3: // choice 3 is the action pick up an item in the room.
					game.PickupItemAbility();
					break;
					
				case 4: // choice 4 is the action drop an item in the inventory.
					game.DropItemAbility();
					break;
					
				case 5: // choice 5 is restart the game.
					game.RestartGame();
					break;
					
				case 6: // choice 5 is exit the game.
					exit = game.ExitGame(); /*if exit = false that's mean player click "Cancel" button in the exit console.
					 							In that case, the game is still running. */
					break;
				case -1: // -1 is the close button up top-right, it's similar to exit choice.
					exit = game.ExitGame();
					break;
			}	
		}
	}

}
