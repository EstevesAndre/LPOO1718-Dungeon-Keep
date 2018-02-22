package DKeep.logic;

public class Game {

	private char[][] map;
	private Hero h;
	private Guard g;
	private Ogre o;
	private int level;
	
	public Game()
	{
		startLevel1();
	}
	
	public void restartGame()
	{
		startLevel1();
	}
	
	public void advanceLevel()
	{
		startLevel2();
	}
	
	private void startLevel1()
	{
		level = 1;
		map = Level_1.createMap();
		h = Level_1.createHero();
		g = Level_1.createGuard();
	}
	
	private void startLevel2()
	{
		level = 2;
		map = Level_2.createMap();
		h = Level_2.createHero();
		o = Level_2.createOgre();
	}	
	
	public void heroMove(char m)
	{
		h.move(m, map);
	}
	
	/*
	 * return:
	 * 0: no end game reached
	 * 1: victory condition reached
	 * 2: defeat condition reached
	 * 3: error reached
	 */
	public int evalStatus()
	{
		switch(level)
		{
		case 1:
		{
			return Level_1.evalStatus(map, h, g);
		}
		case 2:
		{
			return Level_2.evalStatus(map, h, o);
		}
		default:
		{
			return 3;
		}
		}
	}
	
	public char[][] getMap()
	{
		return map;
	}
	
	public int getLevel()
	{
		return level;
	}
}
