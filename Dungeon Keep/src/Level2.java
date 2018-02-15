
public class Level2 {

	public static char[][] createMap()
	{
		char[][] map = { {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
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
	public static int evalMove(char move, char[][] map, int[]pos, int[]guardPos, char[]currentPos)
	{
		/*DungeonKeep.heroMove(move, map, pos, currentPos);
		guardMove(map, guardPos);
		return evalStatus(move, map, pos, guardPos);*/
		return 0;
	}

}
