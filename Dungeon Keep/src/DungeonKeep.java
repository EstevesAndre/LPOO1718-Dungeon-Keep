import java.util.Scanner;

public class DungeonKeep {

	public static char[][] createMap()
	{
		char[][] map = { {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
				{'X', 'H', ' ', ' ', 'I', ' ', 'X', ' ', 'G', 'X'},
				{'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'},
				{'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X'},
				{'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'},
				{'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X'},
				{'X', ' ', 'I', ' ', 'I', ' ', 'X', 'k', ' ', 'X'},
				{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'} };

		return map;

	}

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
	
	/*
	 * return:
	 * 0: no end game reached
	 * 1: victory condition reached
	 * 2: defeat condition reached
	 */
	public static int evalMove(char move, char[][] map, int[]pos, int[]guardPos, char[]currentPos)
	{
		heroMove(move, map, pos, currentPos);
		guardMove(map, guardPos);
		return evalStatus(move, map, pos, guardPos);
	}
	
	public static int evalStatus(char move, char[][] map, int[]pos, int[]guardPos)
	{
		if(pos[1] == 0 && (pos[0] == 5 || pos[0] == 6)) // if is at exit doors
		{
			return 1;
		}
		else if ((pos[0] == guardPos[0] && (pos[1] == guardPos[1] - 1 || pos[1] == guardPos[1] + 1)) || 
				 (pos[1] == guardPos[1] && (pos[0] == guardPos[0] - 1 || pos[0] == guardPos[0] + 1))) //if next to guard
		{
			return 2;
		}
		else if (pos[0] == 8 && pos[1] == 7) //if at lever
		{
			for(int i = 0; i < map.length; i++) //opens every door
				for(int j = 0; j < map[i].length; j++)
				{
					if(map[i][j] == 'I')
						map[i][j] = 'S';
				}
		}
		
		return 0;
	}
	
	public static void heroMove(char move, char[][] map, int[]pos, char[]currentPos)
	{
		switch(move)
		{
		case 'l':
		{
			if(map[pos[0]][pos[1]-1] != 'X' && map[pos[0]][pos[1]-1] != 'I') // if next position is not a wall or closed door
			{
				map[pos[0]][pos[1]] = currentPos[0]; //clears current position
				pos[1]--; //updates coordinates
				currentPos[0] = map[pos[0]][pos[1]]; //saves current position
				map[pos[0]][pos[1]] = 'H'; //moves hero
			}
			break;
		}
		case 'r':
		{
			if(map[pos[0]][pos[1]+1] != 'X' && map[pos[0]][pos[1]+1] != 'I')
			{
				map[pos[0]][pos[1]] = currentPos[0];
				pos[1]++;
				currentPos[0] = map[pos[0]][pos[1]]; //saves current position
				map[pos[0]][pos[1]] = 'H';
			}
			break;
		}
		case 'u':
		{
			if(map[pos[0]-1][pos[1]] != 'X' && map[pos[0]-1][pos[1]] != 'I')
			{
				map[pos[0]][pos[1]] = currentPos[0];
				pos[0]--;
				currentPos[0] = map[pos[0]][pos[1]]; //saves current position
				map[pos[0]][pos[1]] = 'H';
			}
			break;
		}
		case 'd':
		{
			if(map[pos[0]+1][pos[1]] != 'X' && map[pos[0]+1][pos[1]] != 'I')
			{
				map[pos[0]][pos[1]] = currentPos[0];
				pos[0]++;
				currentPos[0] = map[pos[0]][pos[1]]; //saves current position
				map[pos[0]][pos[1]] = 'H';
			}
			break;
		}
		}
	}
	
	public static void guardMove(char[][] map, int[]guardPos) 
	{
		map[guardPos[0]][guardPos[1]] = ' ';
		
		if( guardPos[0] == 1 && guardPos[1] == 7)
		{
			guardPos[0]++;			
		}
		else if(guardPos[1] == 8 && guardPos[0] <= 6 && guardPos[0] > 1)
		{
			guardPos[0]--;
		}
		else if(guardPos[0] == 1)
		{
			guardPos[1]--;
		}
		else if(guardPos[0] == 6)
		{
			guardPos[1]++;
		}
		else if(guardPos[1] == 1)
		{
			guardPos[0]++;
		}
		else if(guardPos[0] == 5)
		{
			guardPos[1]--;
		}
		else if(guardPos[1] == 7)
		{
			guardPos[0]++;
		}
		else
		{
			guardPos[1]++;
		}
		
		map[guardPos[0]][guardPos[1]] = 'G';
	}
	
	public static void main(String[] args)
	{
		char[][] mapInit = createMap();
		int[] pos = {1, 1}; //initial position
		int[] guardPos = {1, 8}; //guard initial position
		char currentPos[] = {' '}; //char under the Hero
		int victory; //victory status
		Scanner reader = new Scanner(System.in);

		while(true)
		{
			printMap(mapInit);
			System.out.print("Select direction: (u)p, (d)own, (l)eft or (r)ight: ");
			char move = reader.next().charAt(0);
			victory = evalMove(move, mapInit, pos, guardPos, currentPos);
			if(victory != 0)
				break;
			
		}
		
		printMap(mapInit);
		if(victory == 1)
			System.out.print("Victory!");
		else
			System.out.print("Game Over!");
		
		reader.close();
	}

}
