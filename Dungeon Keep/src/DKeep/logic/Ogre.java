package DKeep.logic;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Class Ogre - Character -> Ogre
 * 
 * Ogre for level 2.
 * 
 * @author André Esteves && Luís Diogo Silva
 * @version 1.0
 * @since 2018-04-02
 */
public class Ogre extends Character{

	/**
	 * Represents the x position of the Swing.
	 */
	private int xSwingPos;
	
	/**
	 * Represents the y position of the Swing.
	 */
	private int ySwingPos;
	
	/**
	 * Symbol of the Ogre.
	 * Standard symbol is '0'.
	 * If Ogre is sleeping symbol is '8'.
	 */
	private char symbol; 
	
	/**
	 * Number of movements of Hero can do without Ogre move.
	 * Starts at 0.
	 * Ogre moves normally, randomly.
	 * Ogre fall asleep if Hero is next to him sleepCount is se to 2.
	 * Each Hero move, sleepCount is decremented.
	 * If reaches 0, Ogre wake up and move normally.
	 */
	private int sleepCount;
	
	/**
	 * Represents the map of Level.
	 */
	private char[][] m;

	/**
	 * Creates a new Ogre with the given position, x and y, and map.
	 * @param x X position.
	 * @param y Y position.
	 * @param m char[][] map.
	 */
	public Ogre(int x, int y, char[][] m)
	{
		super(x, y);
		xSwingPos = 1;
		ySwingPos = 1;
		symbol = '0';
		sleepCount = 0;
		this.m = m;
	}
	
	/**
	 * Creates a new Ogre with the given position, x and y, xSwing, ySwing, symbol, sleepCount and map.
	 * @param x X position of Ogre.
	 * @param y Y position of Ogre.
	 * @param xS X position of his Swing.
	 * @param yS Y position of his Swing. 
	 * @param sym symbol of the Ogre.
	 * @param sleep sleepCount of Ogre's sleep.
	 * @param m char[][] map.
	 */
	public Ogre(int x, int y, int xS, int yS, char sym, int sleep, char[][] m)
	{
		super(x, y);
		xSwingPos = 1;
		ySwingPos = 1;
		symbol = '0';
		sleepCount = 0;
		this.m = m;
	}

	/**
	 * Gets Ogre's symbol.
	 * 
	 * @return symbol variable of Ogre.
	 */
	public char getSymbol()
	{
		return symbol;
	}
	
	/**
	 * Gets Ogre's sleepCount.
	 * 
	 * @return sleepCount variable of Ogre.
	 */
	public int getSleepCount()
	{
		return sleepCount;
	}
	
	/**
	 * Moves the Ogre in the respective given map.
	 * 
	 * Respecting the logic of the game.
	 * 
	 * @param map char[][] Map to be set.
	 */
	public void move( char[][] map)
	{
		if(sleepCount == 0)
		{
			if(symbol == '$')
			{
				map[yPos][xPos] = 'k';
				symbol = '0';
			}
			else
			{
				map[yPos][xPos] = ' ';
			}

			

			if(map[yPos][xPos] == 'k')
			{
				symbol = '$';
			}

			map[yPos][xPos] = symbol;			

		}
		else
		{
			sleepCount--;

			if(sleepCount == 0)
			{
				symbol = '0';
			}
		}
	}

	/**
	 * Places the swing near Ogre's position.
	 * Can be in the top, down, left or right of Ogre.
	 * 
	 * @param map given map where is Ogre.
	 */
	public void swing( char[][] map)
	{
		if(map[ySwingPos][xSwingPos] == '$')
		{
			map[ySwingPos][xSwingPos] = 'k';
		}
		else if(map[ySwingPos][xSwingPos] == '0')
		{

		}
		else
		{
			map[ySwingPos][xSwingPos] = ' ';
		}
		
		

		if(map[ySwingPos][xSwingPos] == 'k')
		{
			map[ySwingPos][xSwingPos] = '$';
		}
		else
		{
			map[ySwingPos][xSwingPos] = '*';
		}
	}

	/**
	 * Stuns Ogre for 2 seconds. 
	 * Changes his symbol to '8'.
	 * Sets sleepCount variable to 2.
	 * 
	 * @param map given map where Ogre is stunned.
	 */
	public void stun(char [][] map)
	{
		symbol = '8';
		map[yPos][xPos] = symbol;
		sleepCount = 2;
	}
	
	/**
	 * Saves the information of the Guard.
	 * 
	 * @param writer BufferedWriter writer to be set with the respective info.
	 * @throws IOException Throws exception if fails on writing. 
	 */
	public void saveGame(BufferedWriter writer) throws IOException {
		try {
			writer.write(this.xPos + "\n");
			writer.write(this.yPos + "\n");
			writer.write(this.xSwingPos + "\n");
			writer.write(this.ySwingPos + "\n");
			writer.write(this.symbol + "\n");
			writer.write(this.sleepCount + "\n");
		} catch (IOException e) {
			throw new IOException();
		}
		
	}
	
	/**
	 * Changes position of guard randomly
	 * Verifies for possible infinite loops
	 *  
	 */
	void changePos()
	{
		Random nr = new Random();
		boolean muda_posicao = true;
		boolean left = false,right = false,down = false,up = false;
		
		do {
			int num = nr.nextInt(4); // 0(r), 1(l), 2(u) and 3(d)
			switch(num)
			{
			case 0:
				if(m[yPos][xPos+1] != 'X' && m[yPos][xPos+1] != 'I')
				{
					xPos++;
					muda_posicao = false;
				}
				right = true;
				break;
			case 1:
				if(m[yPos][xPos-1] != 'X' && m[yPos][xPos-1] != 'I')
				{
					xPos--;
					muda_posicao = false;
				}
				left = true;
				break;
			case 2:
				if(m[yPos-1][xPos] != 'X' && m[yPos-1][xPos] != 'I')
				{
					yPos--;
					muda_posicao = false;
				}
				up = true;
				break;
			case 3:
				if(m[yPos+1][xPos] != 'X' && m[yPos+1][xPos] != 'I')
				{
					yPos++;
					muda_posicao = false;
				}
				down = true;
				break;
			}
		}while(muda_posicao || !(up && down && left && right));
	}
	
	
	/**
	 * Changes swing position randomly
	 * Verifies for possible infinite loops
	 *  
	 */
	void changeSwingPos()
	{
		Random nr = new Random();
		boolean muda_posicao = true;
		
		boolean left = false,right = false,down = false,up = false;
		
		
		do {
			int num = nr.nextInt(4); // 0(r), 1(l), 2(u) and 3(d)
			switch(num)
			{
			case 0:
				if(m[yPos][xPos+1] != 'X' && m[yPos][xPos+1] != 'I')
				{
					ySwingPos = yPos;
					xSwingPos = xPos + 1;
					muda_posicao = false;
				}
				right = true;
				break;
			case 1:
				if(m[yPos][xPos-1] != 'X' && m[yPos][xPos-1] != 'I')
				{
					ySwingPos = yPos;
					xSwingPos = xPos - 1;
					muda_posicao = false;
				}
				left = true;
				break;
			case 2:
				if(m[yPos-1][xPos] != 'X' && m[yPos-1][xPos] != 'I')
				{
					ySwingPos = yPos - 1;
					xSwingPos = xPos;
					muda_posicao = false;
				}
				up = true;
				break;
			case 3:
				if(m[yPos+1][xPos] != 'X' && m[yPos+1][xPos] != 'I')
				{
					ySwingPos = yPos + 1;
					xSwingPos = xPos;
					muda_posicao = false;					
				}
				down = true;
				break;
			}
		}while(muda_posicao || !(up && down && left && right));
	}
}
