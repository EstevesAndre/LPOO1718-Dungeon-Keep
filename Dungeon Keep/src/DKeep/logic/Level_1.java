package DKeep.logic;

public class Level_1 {

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

	public static Hero createHero()
	{
		Hero h = new Hero(1, 1, ' ', 'H');
		return h;
	}

	public static Guard createGuard()
	{
		Guard g = new Rookie(8, 1);
		return g;
	}

	/*
	 * return:
	 * 0: no end game reached
	 * 1: victory condition reached
	 * 2: defeat condition reached
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
