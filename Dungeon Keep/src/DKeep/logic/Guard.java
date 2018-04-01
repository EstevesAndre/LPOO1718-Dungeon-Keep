package DKeep.logic;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Class Guard - Character -> Guard
 * 
 * Type of Character.
 * SuperClass of each type of guards (Suspicious, Drunken and Rookie).
 * 
 * @author André Esteves
 * @version 1.0
 * @since 2018-03-31
 */
public class Guard extends Character{
	
	/**
	 * Creates a new Guard with a given position, x and y.
	 * @param x X position of the Guard.
	 * @param y Y position of the Guard.
	 */
	public Guard(int x, int y)
	{
		super(x, y);
	}
	
	/**
	 * Moves the Guard in the respective given map.
	 * Respecting some conditions (his path).
	 * 
	 * @param map char[][] map to be set with Guard's movement.
	 */
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

	/**
	 * Saves the information of the Guard.
	 * 
	 * @param writer BufferedWriter writer to be set with the respective info.
	 * 			xPos and yPos.
	 * @throws IOException Throws exception if fails on writing. 
	*/
	public void saveGame(BufferedWriter writer) throws IOException {
		try {
			writer.write(this.xPos + "\n");
			writer.write(this.yPos + "\n");
		} catch (IOException e) {
			throw new IOException();
		}
		
	}

}
