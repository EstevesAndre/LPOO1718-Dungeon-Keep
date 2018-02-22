package DKeep.cli;
import java.util.Scanner;

import DKeep.logic.Game;

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
		Game g = new Game();
		int victory; //victory status
		Scanner reader = new Scanner(System.in);

		while(true)
		{
			printMap(g.getMap());
			System.out.print("Select direction -  [WASD]: ");
			char m = reader.next().charAt(0);
			g.heroMove(m);
			victory = g.evalStatus();
			
			if(victory == 2)
			{
				printMap(g.getMap());
				System.out.print("Game Over!");
				break;
			}
			else if (victory == 1)
			{
				if(g.getLevel() == 1)
				{
					g.advanceLevel();
					victory = 0;
				}
				else
				{
					printMap(g.getMap());
					System.out.print("Victory!");
					break;
				}
			}
		}
		
		reader.close();
	}

}
