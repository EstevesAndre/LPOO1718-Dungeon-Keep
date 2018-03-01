package DKeep.logic;

import java.util.Random;

public class Level_2 {

	public static char[][] createMap()
	{
		char[][] map = { {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}, // size 9v9
				{'I', ' ', ' ', ' ', ' ', ' ', ' ', 'k', 'X'},
				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', 'A', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'} };

		return map;

	}

	public static Hero createHero()
	{
		Hero h = new Hero(1, 7, ' ', 'A');
		return h;
	}

	public static Ogre createOgre()
	{
		boolean valid = true;
		int x = 1, y = 1;

		do
		{
			valid = true;
			Random nr = new Random();
			x = nr.nextInt(7) + 1;
			y = nr.nextInt(7) + 1;

			if((y >= 5 && x <= 3) || (x == 7 && y == 1))
			{
				valid = false;
			}

		}while(!valid);

		Ogre o = new Ogre(x, y);
		return o;
	}

	
	public static void ogreMove(char [][] map, Ogre[] o)
	{
		for(Ogre x : o)
		{
			x.move(map);
		}

		for(Ogre x : o)
		{
			x.swing(map);
		}
	}
	
	/*
	 * return:
	 * 0: no end game reached
	 * 1: victory condition reached
	 * 2: defeat condition reached
	 */
	public static int evalStatus(char[][] map, Hero h, Ogre[] o)
	{

		for(Ogre og : o) 
		{
			if((og.getX() - 1 == h.getX() && og.getY() == h.getY()) ||
					(og.getX() + 1 == h.getX() && og.getY() == h.getY()) ||
					(og.getX() == h.getX() && og.getY() - 1 == h.getY()) ||
					(og.getX() == h.getX() && og.getY() + 1 == h.getY()))
			{
				og.stun(map);
			}
		}

		if(h.getX() == 0 && h.getY() == 1) // at exit door
		{
			return 1;
		}

		else if (map[h.getY() + 1][h.getX()] == '*'
				|| map[h.getY() - 1][h.getX()] == '*'
				|| map[h.getY()][h.getX() + 1] == '*'
				|| map[h.getY()][h.getX() - 1] == '*'
				|| map[h.getY()][h.getX()] == '*')

		{
			return 2;
		}

		return 0;
	}
}
