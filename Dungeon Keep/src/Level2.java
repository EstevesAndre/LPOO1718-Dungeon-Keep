import java.util.Random;

public class Level2 {

	public static char[][] createMap()
	{
		char[][] map = { {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}, // size 9v9
				{'I', ' ', ' ', ' ', 'O', ' ', ' ', 'k', 'X'},
				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'} };

		return map;

	}

	/*
	 * return:
	 * 0: no end game reached
	 * 1: victory condition reached
	 * 2: defeat condition reached
	 */
	public static int evalMove(char move, char[][] map, int[]pos, int[]ogrePos, char[]currentPos)
	{
		heroMove(move, map, pos);
		if ((pos[0] == ogrePos[0] && (pos[1] == ogrePos[1] - 1 || pos[1] == ogrePos[1] + 1)) || 
				(pos[1] == ogrePos[1] && (pos[0] == ogrePos[0] - 1 || pos[0] == ogrePos[0] + 1))) //if next to guard
		{
			return 2;
		}
		ogreMove(map, ogrePos);

		return evalStatus(move, map, pos, ogrePos);
	}

	public static void ogreMove( char[][] map, int[] ogrePos)
	{
		if(map[ogrePos[0]][ogrePos[1]] == '$')
		{
			map[ogrePos[0]][ogrePos[1]] = 'k';
		}
		else
		{
			map[ogrePos[0]][ogrePos[1]] = ' ';
		}

		Random nr = new Random();
		boolean muda_posicao = true;
		do {
			int num = nr.nextInt(4); // 0(r), 1(l), 2(u) and 3(d)
			System.out.println(num);
			switch(num)
			{
			case 0:
				if(ogrePos[1] != 7)
				{
					ogrePos[1]++;
					muda_posicao = false;
				}
				break;
			case 1:
				if(ogrePos[1] != 1)
				{
					ogrePos[1]--;
					muda_posicao = false;
				}
				break;
			case 2:
				if(ogrePos[0] != 1)
				{
					ogrePos[0]--;
					muda_posicao = false;
				}
				break;
			case 3:
				if(ogrePos[0] != 7)
				{
					ogrePos[0]++;
					muda_posicao = false;
				}
				break;
			}
		}while(muda_posicao);

		if(map[ogrePos[0]][ogrePos[1]] == 'k')
		{
			map[ogrePos[0]][ogrePos[1]] = '$';
		}
		else 
		{
			map[ogrePos[0]][ogrePos[1]] = '0';
		}
	}

	public static void heroMove(char move, char[][] map, int[]pos)
	{
		switch(move)
		{
		case 'a':
		{
			if(map[pos[0]][pos[1]-1] == 'I' && map[pos[0]][pos[1]] == 'K')
			{
				map[pos[0]][pos[1]-1] = 'S';
			}
			else if(map[pos[0]][pos[1]-1] != 'X') // if next position is not a wall or closed door
			{
				char symbol = map[pos[0]][pos[1]];
				map[pos[0]][pos[1]] = ' ';
				pos[1]--; //updates coordinates
				map[pos[0]][pos[1]] = symbol; //moves hero
			}
			break;
		}
		case 'd':
		{
			if(map[pos[0]][pos[1]+1] != 'X' && map[pos[0]][pos[1]+1] != 'I')
			{
				char symbol = map[pos[0]][pos[1]];
				map[pos[0]][pos[1]] = ' ';
				pos[1]++;
				map[pos[0]][pos[1]] = symbol;
			}
			break;
		}
		case 'w':
		{
			if(map[pos[0]-1][pos[1]] != 'X' && map[pos[0]-1][pos[1]] != 'I')
			{
				char symbol = map[pos[0]][pos[1]];
				map[pos[0]][pos[1]] = ' ';
				pos[0]--;
				map[pos[0]][pos[1]] = symbol;
			}
			break;
		}
		case 's':
		{
			if(map[pos[0]+1][pos[1]] != 'X' && map[pos[0]+1][pos[1]] != 'I')
			{
				char symbol = map[pos[0]][pos[1]];
				map[pos[0]][pos[1]] = ' ';
				pos[0]++;
				map[pos[0]][pos[1]] = symbol;
			}
			break;
		}
		}
		if(pos[0] == 1 && pos[1] == 7 && map[pos[0]][pos[1]] == 'H')
		{
			map[pos[0]][pos[1]] = 'K';
		}
	}


	public static int evalStatus(char move, char[][] map, int[]pos, int[]ogrePos)
	{
		if(pos[1] == 0 && pos[0] == 1) // at exit door
		{
			return 1;
		}
		else if ((pos[0] == ogrePos[0] && (pos[1] == ogrePos[1] - 1 || pos[1] == ogrePos[1] + 1)) || 
				(pos[1] == ogrePos[1] && (pos[0] == ogrePos[0] - 1 || pos[0] == ogrePos[0] + 1))) //if next to guard
		{
			return 2;
		}

		return 0;
	}

}
