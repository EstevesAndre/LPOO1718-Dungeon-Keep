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
				{'X', ' ', 'I', ' ', 'I', ' ', 'X', 'K', ' ', 'X'},
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
	
	public static void evalMove(char move, char[][] map, int[]pos)
	{
		switch(move)
		{
		case 'l':
		{
			if(map[pos[0]][pos[1]-1] != 'X' && map[pos[0]][pos[1]-1] != 'I') // if next position is not a wall or closed door
			{
				map[pos[0]][pos[1]] = ' '; //clears current position
				pos[1]--; //updates coordinates
				map[pos[0]][pos[1]] = 'H'; //moves hero
			}
			break;
		}
		case 'r':
		{
			if(map[pos[0]][pos[1]+1] != 'X' && map[pos[0]][pos[1]+1] != 'I')
			{
				map[pos[0]][pos[1]] = ' ';
				pos[1]++;
				map[pos[0]][pos[1]] = 'H';
			}
			break;
		}
		case 'u':
		{
			if(map[pos[0]-1][pos[1]] != 'X' && map[pos[0]-1][pos[1]] != 'I')
			{
				map[pos[0]][pos[1]] = ' ';
				pos[0]--;
				map[pos[0]][pos[1]] = 'H';
			}
			break;
		}
		case 'd':
		{
			if(map[pos[0]+1][pos[1]] != 'X' && map[pos[0]+1][pos[1]] != 'I')
			{
				map[pos[0]][pos[1]] = ' ';
				pos[0]++;
				map[pos[0]][pos[1]] = 'H';
			}
			break;
		}
		}
	}

	public static void main(String[] args)
	{
		char[][] mapInit = createMap();
		int[] pos = {1, 1}; //initial position
		Scanner reader = new Scanner(System.in);

		for(int i = 0; i < 5; i++)
		{
			printMap(mapInit);
			System.out.print("Select direction: (u)p, (d)own, (l)eft or (r)ight: ");
			char move = reader.next().charAt(0);
			evalMove(move, mapInit, pos);
		}
		
		printMap(mapInit);
		
		reader.close();
	}

}
