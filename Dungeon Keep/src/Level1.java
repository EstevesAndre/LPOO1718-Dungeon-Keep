public class Level1 {

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
	
	/*
	 * return:
	 * 0: no end game reached
	 * 1: victory condition reached
	 * 2: defeat condition reached
	 */
	public static int evalMove(char move, char[][] map, int[]pos, int[]guardPos, char[]currentPos)
	{
		DungeonKeep.heroMove(move, map, pos, currentPos);
		if ((pos[0] == guardPos[0] && (pos[1] == guardPos[1] - 1 || pos[1] == guardPos[1] + 1)) || 
				 (pos[1] == guardPos[1] && (pos[0] == guardPos[0] - 1 || pos[0] == guardPos[0] + 1))) //if next to guard
		{
			return 2;
		}
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
}
