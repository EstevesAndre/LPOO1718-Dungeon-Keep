package DKeep.logic;

import java.io.BufferedWriter;
import java.io.IOException;

public class Hero extends Character{

	private char currentPos;
	private char symbol;

	public Hero(int x, int y, char pos, char sym)
	{
		super(x, y);
		currentPos = pos;
		symbol = sym;
	}

	public char getCurrentPos()
	{
		return currentPos;
	}
	
	public char getSymbol()
	{
		return symbol;
	}
	
	public void setCurrentPos(char pos)
	{
		currentPos = pos;
	}
	
	public void setSymbol(char sym)
	{
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
		if(currentPos == 'k' && symbol == 'A')
		{
			symbol = 'K';
			currentPos = ' ';
			map[yPos][xPos] = symbol;
		}
	}

	public void saveGame(BufferedWriter writer) throws IOException {
		try {
			writer.write(this.xPos + "\n");
			writer.write(this.yPos + "\n");
			writer.write(this.currentPos + "\n");
			writer.write(this.symbol + "\n");
		} catch (IOException e) {
			throw new IOException();
		}
		
	}
}
