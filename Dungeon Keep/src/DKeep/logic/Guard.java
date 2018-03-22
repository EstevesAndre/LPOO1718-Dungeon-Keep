package DKeep.logic;

import java.io.BufferedWriter;
import java.io.IOException;

public class Guard extends Character{
	
	public Guard(int x, int y)
	{
		super(x, y);
	}
	
	
	public void move(char[][] map) 
	{
		map[yPos][xPos] = ' ';
		
		if( yPos == 1 && xPos == 7)
		{
			yPos++;			
		}
		else if(xPos == 8 && yPos <= 6 && yPos > 1)
		{
			yPos--;
		}
		else if(yPos == 1)
		{
			xPos--;
		}
		else if(yPos == 6)
		{
			xPos++;
		}
		else if(xPos == 1)
		{
			yPos++;
		}
		else if(yPos == 5)
		{
			xPos--;
		}
		else if(xPos == 7)
		{
			yPos++;
		}
		else
		{
			xPos++;
		}
		
		map[yPos][xPos] = 'G';
	}


	public void saveGame(BufferedWriter writer) throws IOException {
		try {
			writer.write(this.xPos + "\n");
			writer.write(this.yPos + "\n");
		} catch (IOException e) {
			throw new IOException();
		}
		
	}

}
