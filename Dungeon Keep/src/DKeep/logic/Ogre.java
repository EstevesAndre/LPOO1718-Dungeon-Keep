package DKeep.logic;

import java.util.Random;

public class Ogre extends Character{
	
	private int xSwingPos;
	private int ySwingPos;
	
	public Ogre(int x, int y)
	{
		super(x, y);
		xSwingPos = 1;
		ySwingPos = 1;
	}

	public void move( char[][] map)
	{
		if(map[yPos][xPos] == '$')
		{
			map[yPos][xPos] = 'k';
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
			map[yPos][xPos] = '$';
		}
		else 
		{
			map[yPos][xPos] = '0';
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
}
