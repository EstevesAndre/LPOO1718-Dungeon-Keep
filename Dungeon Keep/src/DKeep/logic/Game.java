package DKeep.logic;

import java.util.Random;

/**
 * Class Game - Main Class when the game runs.
 * 
 * @author André Esteves and Luís Diogo Silva
 * @version 1.0
 * @since 2018-03-30
 */
public class Game {

	/**
	 * Represents the current Map of the game.
	 */
	private char[][] map;
	
	/**
	 * Represents the Hero, main Character of the game.
	 */
	private Hero h;
	
	/**
	 * Represents the Guard of the game to Level 1.
	 */
	private Guard g;
	
	/**
	 * Represents the Ogres of the game, Level 2.
	 */
	private Ogre[] o;
	
	/**
	 * Represents the current Level.
	 */
	private int level;
	
	/**
	 * Represents the number of Ogres.
	 */
	private int nOgres;	
	
	/**
	 * Creates a new Game. Standard new Game, starting Level 1 with regular objects. 
	 */
	public Game()
	{
		startLevel1();
	}
	
	/**
	 * Creates a new Game with a given personality. 
	 * Types of Personality: 
	 * 	- Rookie
	 * 	- Suspicious
	 * 	- Drunken
	 * 
	 * @param personality String that refers to the personality of the Guard to Level 1.
	 */
	public Game(String personality)
	{
		startLevel1(personality);
	}
	
	
	/**
	 * Restarts the Game. Back to the standard Level 1.
	 */
	public void restartGame()
	{
		startLevel1();
	}
	
	/**
	 * Advance level. Current Level is Level 1 and advances to Level 2.
	 */
	public void advanceLevel()
	{
		startLevel2();
	}
	
	/**
	 * Advance level. Current Level is level 1 and advances to Level 2 with a given number of Ogres.
	 * 
	 * @param numberOgres Number of Ogres to Level 2.
	 */
	public void advanceLevel(int numberOgres)
	{
		startLevel2(numberOgres);
	}
	
	/**
	 * Advance Level. Current Level is Level 1 and advances to Level 2 with a give number of Ogres and Map.
	 * @param numberOgres Number of Ogres to Level 2.
	 * @param map Map to be played on Level 2.
	 */
	public void advanceLevel(int numberOgres, char[][]map)
	{
		startLevel2(numberOgres, map);
	}
	
	/**
	 * Prepare Objects of level 1. Standard.
	 * Sets level integer variable to 1.
	 * Sets map char[][] variable to the map of level 1.
	 * Sets hero and guard in correct positions.
	 */
	private void startLevel1()
	{
		level = 1;
		map = Level_1.createMap();
		h = Level_1.createHero();
		g = Level_1.createGuard();
	}
	
	/**
	 * Prepare Objects of Level 1. With given Guard personality.
	 * Sets level integer variable to 1.
	 * Sets map char[][] variable to the map of Level 1.
	 * Sets hero in correct position.
	 * @param personality Type of Guard (Rookie, Suspicious or Drunken) 
	 */
	private void startLevel1(String personality)
	{
		level = 1;
		map = Level_1.createMap();
		h = Level_1.createHero();
		g = Level_1.createGuard(personality);
	}
	
	/**
	 * Prepare Objects of Level 2.
	 * Sets level integer variable to 2.
	 * Sets map char[][] variable to the map of Level 2.
	 * Sets hero in correct position.
	 * Generates a random number of Ogres [1-4] in a random position between some restrictions.
	 */
	private void startLevel2()
	{
		level = 2;
		map = Level_2.createMap();
		h = Level_2.createHero();
		
		Random nr = new Random();
		nOgres = nr.nextInt(4) + 1;
		
		o = new Ogre[nOgres];
		
		for(int i = 0; i < nOgres; i++)
		{
			o[i]  = Level_2.createOgre(map);
			
			map[ o[i].getY() ] [ o[i].getX() ] = '0';
		}
	}	
	
	/** 
	 * Prepare Objects of Level 2.
	 * Sets level integer variable to 2.
	 * Sets map char[][] variable to the map of Level 2.
	 * Sets hero in correct position.
	 * Generates numberOgres Ogres in a random position in the current map.
	 * @param numberOgres Number of Ogres of to have on Level 2.
	 */
	private void startLevel2(int numberOgres)
	{
		level = 2;
		map = Level_2.createMap();
		h = Level_2.createHero();
		
		o = new Ogre[numberOgres];
		
		for(int i = 0; i < numberOgres; i++)
		{
			o[i]  = Level_2.createOgre(map);
			
			map[ o[i].getY() ] [ o[i].getX() ] = '0';
		}
	}	
	
	/**
	 * Prepare Objects of Level 2.
	 * Sets level integer variable to 2.
	 * Sets hero in correct position.
	 * Sets map char[][] variable with the given map.
	 * Generates numberOgres Ogres in a random position in the current map.
	 * @param numberOgres Number of Ogres of to have on Level 2.
	 * @param map Map where the game "runs".
	 */
	private void startLevel2(int numberOgres, char[][] map)
	{
		level = 2;
		this.map = map;
		o = new Ogre[numberOgres];
		int pos = 0;
		
		for(int i = 0; i < map.length; i++)
		{
			for(int j = 0; j < map[i].length; j++)
			{
				switch(map[i][j])
				{
				case 'A':
				{
					h = new Hero(j, i, ' ', 'A');
					break;
				}
				case '0':
				{
					o[pos] = new Ogre(j, i, map);
					pos++;
					break;
				}
				default:break;
				}
			}
		}
	}
	
	/**
	 * Moves the Hero in the respective map
	 *
	 * @param m Movement char of the hero.
	 * 	Can be:
	 * 		'a' - moves left.
	 * 		'd' - moves right.
	 *		'w' - moves up.
	 *		's' - moves down. 	
	 */
	public void heroMove(char m)
	{
		h.move(m, map);
	}
	
	/**
	 * Evaluates the game status in the exactly state.
	 * Responsible to the movement of Ogre and his Swing on the Level 2. 
	 * 
	 * @return Integer:
	 * 		- 0 , no end game reached. Game continues.
	 * 		- 1 , victory condition reached.
	 * 		- 2 , defeat condition reached.
	 * 		- 3 , error reached.
	 */
	public int evalStatus()
	{
		switch(level)
		{
		case 1:
			return Level_1.evalStatus(map, h, g);
		
		case 2:
			lvl2Check();
			return Level_2.evalStatus(map, h, o);
		default:
			return 3;
		}
	}
	
	/**
	 * Sets the map char[][] variable with the given map parameter.
	 * 
	 * @param map Variable to set the char[][] map of the class.
	 */
	public void setMap(char[][] map)
	{
		this.map = map;
	}
	
	/**
	 * Gets the current Map.
	 * 
	 * @return char[][] map of current Game.
	 */
	public char[][] getMap()
	{
		return map;
	}
	
	/**
	 * Gets the level of the current Game.
	 * @return level variable of Game.
	 */
	public int getLevel()
	{
		return level;
	}
	
	/**
	 * Gets the Guard of current Game.
	 * 
	 * @return Guard g of Game.
	 */
	public Guard getGuard()
	{
		return g;
	}
	
	/**
	 * Gets the Hero of current Game.
	 * 
	 * @return Hero h of Game.
	 */
	public Hero getHero()
	{
		return h;
	}
	
	/**
	 * Gets the Ogres of current Game.
	 * 
	 * @return Ogre[] o of Game.
	 */
	public Ogre[] getOgre()
	{
		return o;
	}
	
	/**
	 * Sets Hero position and his position on map with the given parameters.
	 * 
	 * @param x X position to set Hero xPos.
	 * @param y Y position to set Hero yPos.
	 */
	public void setHero(int x, int y)
	{
		map[h.getY()][h.getX()] = h.getCurrentPos();
		h.setPos(x, y);
		map[h.getY()][h.getX()] = h.getSymbol();
	}
	
	/**
	 * Sets Hero position with given parameters.
	 * 
	 * @param x X position to set Hero xPos.
	 * @param y Y position to set Hero yPos.
	 * @param pos Character char behind Hero position on current map.
	 * @param sym Symbol of Hero.
	 */
	public void setHero(int x, int y, char pos, char sym) {
		h.setPos(x, y);
		h.setCurrentPos(pos);
		h.setSymbol(sym);
		
	}
	
	/**
	 * Sets Guard personality and his attributes.
	 * 
	 * @param g Guard to set Guard g variable.
	 */
	public void setGuard(Guard g)
	{
		this.g = g;
	}
	
	/**
	 * Sets all Ogres with a given position, x and y.
	 * @param x X position to set Ogres position.
	 * @param y Y position to set Ogres position.
	 */
	public void setOgre(int x, int y)
	{
		for(Ogre ogre : o)
		{
			map[ogre.getY()][ogre.getX()] = ' ';
			ogre.setPos(x, y);
			map[ogre.getY()][ogre.getX()] = ogre.getSymbol();
		}
	}

	/**
	 * Sets Ogre[] o variable with a given Ogre[].
	 * 
	 * @param ogres Array of Ogre to set Ogre[] o variable.
	 */
	public void setOgre(Ogre[] ogres) {
		o = ogres;
		
	}

	/**
	 * Performs several maintenance for level 2
	 */
	void lvl2Check()
	{
		int keyPos[] = {0,0};
		if(h.getSymbol() != 'K')
		{
			for(int y = 0; y < map.length; y++)
				for(int x = 0; x < map[y].length; x++)
				{
					if(map[y][x] == 'k')
					{
						keyPos[0] = x;
						keyPos[1] = y;
						break;
					}
				}
		}
		Level_2.ogreMove(map, o);
		Level_2.swingMove(map,o);
		
		if(keyPos[0] != 0 && keyPos[1] != 0)
		{
			map[keyPos[1]][keyPos[0]] = 'k';
		}
		
	}
}
