package DKeep.logic;

public class Level_2 {
	
	public static char[][] createMap()
	{
		char[][] map = { {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}, // size 9v9
				{'I', ' ', ' ', ' ', '0', ' ', ' ', 'k', 'X'},
				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', 'H', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'} };

		return map;

	}
	
	public static Hero createHero()
	{
		Hero h = new Hero(1, 7, ' ', 'H');
		return h;
	}
	
	public static Ogre createOgre()
	{
		Ogre o = new Ogre(4, 1);
		return o;
	}
	
	/*
	 * return:
	 * 0: no end game reached
	 * 1: victory condition reached
	 * 2: defeat condition reached
	 */
	public static int evalStatus(char[][] map, Hero h, Ogre o)
	{
		if ((h.getY() == o.getY() && (h.getX() == o.getX() - 1 || h.getX() == o.getX() + 1)) || 
				(h.getX() == o.getX() && (h.getY() == o.getY() - 1 || h.getY() == o.getY() + 1))) //if next to guard
		{
			return 2;
		}
		
		o.move(map);
		o.swing(map);
		
		if(h.getX() == 0 && h.getY() == 1) // at exit door
		{
			return 1;
		}
		
		else if ((h.getY() == o.getY() && (h.getX() == o.getX() - 1 || h.getX() == o.getX() + 1)) || 
				(h.getX() == o.getX() && (h.getY() == o.getY() - 1 || h.getY() == o.getY() + 1))) //if next to guard
		{
			return 2;
		}
		
		else if (map[h.getY() + 1][h.getX()] == '*'
				|| map[h.getY() - 1][h.getX()] == '*'
				|| map[h.getY()][h.getX() + 1] == '*'
				|| map[h.getY()][h.getX() - 1] == '*') //if next to lever
		{
			return 2;
		}

		return 0;
	}
}
