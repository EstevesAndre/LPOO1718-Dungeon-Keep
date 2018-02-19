import java.util.Scanner;

public class DungeonKeep {

	public static void printMap(char[][] map)
	{
		for(char[] i:map)
		{
			for(char j:i)
			{
				System.out.print(j);
				System.out.print(' ');
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args)
	{
		char[][] mapInit = Level1.createMap();
		int[] pos = {1, 1}; //initial position
		int[] guardPos = {1, 8}; //guard initial position
		char currentPos[] = {' '}; //char under the Hero
		int victory; //victory status
		Scanner reader = new Scanner(System.in);

		while(true)
		{
			printMap(mapInit);
			System.out.print("Select direction -  [WASD]: ");
			char move = reader.next().charAt(0);
			victory = Level1.evalMove(move, mapInit, pos, guardPos, currentPos);
			if(victory != 0)
				break;
		}
		
		printMap(mapInit);		
		if(victory == 2)
			System.out.print("Game Over!");
		else 
		
		if(true)
		{
			System.out.println("\n\n");
			mapInit = Level2.createMap();
			pos[0] = 7;
			pos[1] = 1;
			guardPos[0] = 1;
			guardPos[1] = 4;
			int[] swingPos = {1, 1}; //swing initial position - arbitrary
			victory = 0;
			while(true)
			{
				printMap(mapInit);
				System.out.print("Select direction -  [WASD]: ");
				char move = reader.next().charAt(0);
				victory = Level2.evalMove(move, mapInit, pos, guardPos, swingPos, currentPos);
				if(victory != 0)
					break;
			}
			printMap(mapInit);		
			if(victory == 2)
				System.out.print("Game Over!");
			else
				System.out.print("Victory!");
		}
		
		
		reader.close();
	}

}
