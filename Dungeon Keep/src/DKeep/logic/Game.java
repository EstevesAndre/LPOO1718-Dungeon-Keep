package DKeep.logic;

import java.util.Random;

public class Game {

	private char[][] map;
	private Hero h;
	private Guard g;
	private Ogre[] o;
	private int level;
	private int nOgres;
	
	public Game()
	{
		startLevel1();
	}
	
	public Game(String personality)
	{
		startLevel1(personality);
	}
	
	public void restartGame()
	{
		startLevel1();
	}
	
	public void advanceLevel()
	{
		startLevel2();
	}
	
	public void advanceLevel(int numberOgres)
	{
		startLevel2(numberOgres);
	}
	
	public void advanceLevel(int numberOgres, char[][]map)
	{
		startLevel2(numberOgres, map);
	}
	
	private void startLevel1()
	{
		level = 1;
		map = Level_1.createMap();
		h = Level_1.createHero();
		g = Level_1.createGuard();
	}
	
	private void startLevel1(String personality)
	{
		level = 1;
		map = Level_1.createMap();
		h = Level_1.createHero();
		g = Level_1.createGuard(personality);
	}
	
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
				case ' ':
				{
					break;
				}
				case 'X':
				{
					break;
				}
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
				}
			}
		}
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
			Level_2.ogreMove(map, o);
			Level_2.swingMove(map,o);
			return Level_2.evalStatus(map, h, o);
		}
		default:
		{
			return 3;
		}
		}
	}
	
	public void setMap(char[][] map)
	{
		this.map = map;
	}
	
	public char[][] getMap()
	{
		return map;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public Guard getGuard()
	{
		return g;
	}
	
	public Hero getHero()
	{
		return h;
	}
	
	public Ogre[] getOgre()
	{
		return o;
	}
	
	public void setHero(int x, int y)
	{
		map[h.getY()][h.getX()] = h.getCurrentPos();
		h.setPos(x, y);
		map[h.getY()][h.getX()] = h.getSymbol();
	}
	
	public void setHero(int x, int y, char pos, char sym) {
		h.setPos(x, y);
		h.setCurrentPos(pos);
		h.setSymbol(sym);
		
	}
	
	public void setGuard(Guard g)
	{
		this.g = g;
	}
	
	public void setOgre(int x, int y)
	{
		for(Ogre ogre : o)
		{
			map[ogre.getY()][ogre.getX()] = ' ';
			ogre.setPos(x, y);
			map[ogre.getY()][ogre.getX()] = ogre.getSymbol();
		}
	}

	public void setOgre(Ogre[] ogres) {
		o = ogres;
		
	}

	
}
