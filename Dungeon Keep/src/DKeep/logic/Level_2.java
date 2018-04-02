package DKeep.logic;

import java.util.Random;

/**
 * Level_2.java - Level 2 class.
 * 
 * Creates the objects of level 2. Map, Hero, Ogres and swing.
 * 
 * Evaluates level (complete, lost, uncompleted).
 * 
 * @author André Esteves && Luís Diogo Silva
 * @version 1.0
 * @since 2018-04-02
 */
public class Level_2 {

	/**
	 * Creates the map for level 2.
	 * Like a matrix, char[][]. 
	 * Size of 9x9.
	 * X -> wall.
	 * I -> Closed door.
	 * S -> Open door.
	 * A -> Hero without key.
	 * K -> Hero with key.
	 * k -> key to unlock the door.
	 * G -> Guard.
	 * 
	 * @return map of level 2, char[][] variable.
	 */
	public static char[][] createMap()
	{
		char[][] map = { {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
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

	/**
	 * Creates the Hero for level 2 on his correct position. Hard coded.
	 * On the position [1,7] of the current map.
	 *  
	 * @return Hero of level 2.
	 */
	public static Hero createHero()
	{
		Hero h = new Hero(1, 7, ' ', 'A');
		return h;
	}

	/**
	 * Creates one Ogre in the given map on a random position with some restrictions.
	 * 
	 * @param map Given map (map of level 2) where the Ogre is going to be created.
	 * @return Ogre created.
	 */
	public static Ogre createOgre(char[][]map)
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

		Ogre o = new Ogre(x, y, map);
		return o;
	}

	/**
	 * Movements of Ogres[] o in the given map char[][].
	 * 
	 * @param map Map where Ogre is going to move.
	 * @param o Set of Ogres created in the beginning.
	 */
	public static void ogreMove(char [][] map, Ogre[] o)
	{
		for(Ogre x : o)
		{
			x.move(map);
		}	
	}
	
	/**
	 * Position of swing of each Ogre.
	 * 
	 * @param map Map where the swing is going to be set.
	 * @param o Set of Ogres created in the beginning.
	 */
	public static void swingMove(char [][] map, Ogre[] o)
	{
		for(Ogre x : o)
		{
			x.swing(map);
		}
	}
	
	/**
	 * Evaluates the status of the current level.
	 * 
	 * @param map Map char[][] of current level.
	 * @param h Hero of the level.
	 * @param o set of Ogres of the level.
	 * @return 0, if no end game reached.
	 * 		1, if victory condition reached, level 1 completed.
	 * 		2, if defeat condition reached, level 1 lost, got caught by any Ogre's swing.
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
