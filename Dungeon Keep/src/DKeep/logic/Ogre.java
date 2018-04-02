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

	private int xSwingPos;
	private int ySwingPos;
	private char symbol; 
	private int sleepCount;
	private char[][] m;

	public Ogre(int x, int y, char[][] m)
	{
		super(x, y);
		xSwingPos = 1;
		ySwingPos = 1;
		symbol = '0';
		sleepCount = 0;
		this.m = m;
	}
	
	public Ogre(int x, int y, int xS, int yS, char sym, int sleep, char[][] m)
	{
		super(x, y);
		xSwingPos = 1;
		ySwingPos = 1;
		symbol = '0';
		sleepCount = 0;
		this.m = m;
	}

	public char getSymbol()
	{
		return symbol;
	}
	
	public int getSleepCount()
	{
		return sleepCount;
	}
	
	
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

			Random nr = new Random();
			boolean muda_posicao = true;
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
					break;
				case 1:
					if(m[yPos][xPos-1] != 'X' && m[yPos][xPos-1] != 'I')
					{
						xPos--;
						muda_posicao = false;
					}
					break;
				case 2:
					if(m[yPos-1][xPos] != 'X' && m[yPos-1][xPos] != 'I')
					{
						yPos--;
						muda_posicao = false;
					}
					break;
				case 3:
					if(m[yPos+1][xPos] != 'X' && m[yPos+1][xPos] != 'I')
					{
						yPos++;
						muda_posicao = false;
					}
					break;
				}
			}while(muda_posicao);

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

		Random nr = new Random();
		boolean muda_posicao = true;
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
				break;
			case 1:
				if(m[yPos][xPos-1] != 'X' && m[yPos][xPos-1] != 'I')
				{
					ySwingPos = yPos;
					xSwingPos = xPos - 1;
					muda_posicao = false;
				}
				break;
			case 2:
				if(m[yPos-1][xPos] != 'X' && m[yPos-1][xPos] != 'I')
				{
					ySwingPos = yPos - 1;
					xSwingPos = xPos;
					muda_posicao = false;
				}
				break;
			case 3:
				if(m[yPos+1][xPos] != 'X' && m[yPos+1][xPos] != 'I')
				{
					ySwingPos = yPos + 1;
					xSwingPos = xPos;
					muda_posicao = false;
				}
				break;
			}
		}while(muda_posicao);

		if(map[ySwingPos][xSwingPos] == 'k')
		{
			map[ySwingPos][xSwingPos] = '$';
		}
		else
		{
			map[ySwingPos][xSwingPos] = '*';
		}
	}

	public void stun(char [][] map)
	{
		symbol = '8';
		map[yPos][xPos] = symbol;
		sleepCount = 2;
	}
	
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
}
