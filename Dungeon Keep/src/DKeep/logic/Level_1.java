package DKeep.logic;

/**
 * Level_1.java - Level 1 class.
 * 
 * Creates the objects of level 1. Map, Hero and Guard.
 * 
 * Evaluates level (complete, lost, uncompleted).
 *  
 * @author André Esteves && Luís Diogo Silva
 * @version 1.0
 * @since 2018-04-01
 */
public class Level_1 {

	/**
	 * Creates the map for level 1.
	 * Like a matrix, char[][].
	 * Size of 10x10.
	 * X -> wall.
	 * I -> Closed door.
	 * S -> Open door.
	 * H -> Hero.
	 * G -> Guard.
	 * l -> Lever.
	 * 
	 * @return map of level 1, char[][] variable.
	 */
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
				{'X', ' ', 'I', ' ', 'I', ' ', 'X', 'l', ' ', 'X'},
				{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'} }; 

		return map;

	}

	/**
	 * Creates the Hero for level 1 on his correct position. Hard coded.
	 * On the position [1,1] of the current map.
	 *  
	 * @return Hero of level 1.
	 */
	public static Hero createHero()
	{
		Hero h = new Hero(1, 1, ' ', 'H');
		return h;
	}

	/**
	 * Creates the Guard for level 1 on his correct position. Hard coded.
	 * On the position [8,1] of the current map.
	 * There is created the standard Guard, Rookie Guard.
	 * 
	 * @return Guard of level 1.
	 */
	public static Guard createGuard()
	{
		Guard g = new Rookie(8, 1);
		return g;
	}

	/**
	 * Evaluates the status of the current level.
	 * 
	 * @param map Map char[][] of current level.
	 * @param h Hero of the level.
	 * @param g Guard [Rookie, Suspicious or Drunken] of the level.
	 * @return 0, if no end game reached.
	 * 		1, if victory condition reached, level 1 completed.
	 * 		2, if defeat condition reached, level 1 lost, got caught by Guard.
	 */
	public static int evalStatus(char[][] map, Hero h, Guard g)
	{
		if ((h.getY() == g.getY() && (h.getX() == g.getX() - 1 || h.getX() == g.getX() + 1)) || 
				(h.getX() == g.getX() && (h.getY() == g.getY() - 1 || h.getY() == g.getY() + 1))) //if next to guard
		{
			return 2;
		}

		g.move(map);

		if(h.getX() == 0 && (h.getY() == 5 || h.getY() == 6)) // if is at exit doors
		{
			return 1;
		}

		else if ((h.getY() == g.getY() && (h.getX() == g.getX() - 1 || h.getX() == g.getX() + 1)) || 
				(h.getX() == g.getX() && (h.getY() == g.getY() - 1 || h.getY() == g.getY() + 1))) //if next to guard
		{
			return 2;
		}

		else if (h.getY() == 8 && h.getX() == 7) //if at lever
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

	/**
	 * Creates the Guard for level 1 on his correct position. Hard coded.
	 * On the position [8,1] of the current map.
	 * There is created the Guard [Rookie, Suspicious or Drunken] depending on personality argument.
	 * 
	 * @param personality Personality of the guard. 
	 * if Novice -> Rookie Guard.
	 * else if Advanced -> Suspicious Guard.
	 * else -> Drunken Guard.
	 * 
	 * @return Guard of level 1.
	 */
	public static Guard createGuard(String personality) {
		// TODO Auto-generated method stub
		if(personality.equals("Novice"))
		{
			Guard g = new Rookie(8, 1);
			return g;
		}
		else if(personality.equals("Advanced"))
		{
			Guard g = new Suspicious(8, 1);
			return g;
		}
		else
		{
			Guard g = new Drunken(8, 1);
			return g;
		}
	}
}
