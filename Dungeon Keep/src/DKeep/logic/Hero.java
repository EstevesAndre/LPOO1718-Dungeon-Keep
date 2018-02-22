package DKeep.logic;

public class Hero extends Character{

	private char currentPos;
	private char symbol;

	public Hero(int x, int y, char pos, char sym)
	{
		super(x, y);
		currentPos = pos;
		symbol = sym;
	}

	public void move(char m, char[][] map)
	{
		switch(m)
		{
		case 'a':
		{
			if(map[yPos][xPos-1] == 'I' && symbol == 'K')
			{
				map[yPos][xPos-1] = 'S';
			}
			else if(map[yPos][xPos-1] != 'X' && map[yPos][xPos-1] != 'I') // if next position is not a wall or closed door
			{
				map[yPos][xPos] = currentPos;
				xPos--; //updates coordinates
				currentPos = map[yPos][xPos];
				map[yPos][xPos] = symbol; //moves hero
			}
			break;
		}
		case 'd':
		{
			if(map[yPos][xPos+1] != 'X' && map[yPos][xPos+1] != 'I')
			{
				map[yPos][xPos] = currentPos;
				xPos++;
				currentPos = map[yPos][xPos];
				map[yPos][xPos] = symbol;
			}
			break;
		}
		case 'w':
		{
			if(map[yPos-1][xPos] != 'X' && map[yPos-1][xPos] != 'I')
			{
				map[yPos][xPos] = currentPos;
				yPos--;
				currentPos = map[yPos][xPos];
				map[yPos][xPos] = symbol;
			}
			break;
		}
		case 's':
		{
			if(map[yPos+1][xPos] != 'X' && map[yPos+1][xPos] != 'I')
			{
				map[yPos][xPos] = currentPos;
				yPos++;
				currentPos = map[yPos][xPos];
				map[yPos][xPos] = symbol;
			}
			break;
		}
		}
		if(currentPos == 'k' && symbol == 'H')
		{
			symbol = 'K';
			currentPos = ' ';
			map[yPos][xPos] = symbol;
		}
	}
}
