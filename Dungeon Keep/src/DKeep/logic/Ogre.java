package DKeep.logic;

import java.util.Random;

public class Ogre extends Character{

	private int xSwingPos;
	private int ySwingPos;
	private char symbol; 
	private int sleepCount;

	public Ogre(int x, int y)
	{
		super(x, y);
		xSwingPos = 1;
		ySwingPos = 1;
		symbol = '0';
		sleepCount = 0;
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
					if(xPos != 7)
					{
						xPos++;
						muda_posicao = false;
					}
					break;
				case 1:
					if(xPos != 1)
					{
						xPos--;
						muda_posicao = false;
					}
					break;
				case 2:
					if(yPos != 1)
					{
						yPos--;
						muda_posicao = false;
					}
					break;
				case 3:
					if(yPos != 7)
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
				if(xPos != 7)
				{
					ySwingPos = yPos;
					xSwingPos = xPos + 1;
					muda_posicao = false;
				}
				break;
			case 1:
				if(xPos != 1)
				{
					ySwingPos = yPos;
					xSwingPos = xPos - 1;
					muda_posicao = false;
				}
				break;
			case 2:
				if(yPos != 1)
				{
					ySwingPos = yPos - 1;
					xSwingPos = xPos;
					muda_posicao = false;
				}
				break;
			case 3:
				if(yPos != 7)
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
}
