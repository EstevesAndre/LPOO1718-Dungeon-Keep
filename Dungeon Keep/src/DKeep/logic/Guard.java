package DKeep.logic;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Class Guard - Character -> Guard
 * 
 * Type of Character.
 * SuperClass of each type of guards (Suspicious, Drunken and Rookie).
 * 
 * @author André Esteves && Luís Diogo Silva
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
			yPos++;			
		
		else if(xPos == 8 && yPos <= 6 && yPos > 1)		
			yPos--;
		
		else if(yPos == 1)		
			xPos--;
		else 
			movePart2(map);

		map[yPos][xPos] = 'G';
	}

	public void movePart2(char [][]map)
	{
		if(yPos == 6)
			xPos++;
		
		else if(xPos == 1)		
			yPos++;
		
		else if(yPos == 5)		
			xPos--;
		
		else if(xPos == 7)		
			yPos++;		
		
		else		
			xPos++;		
	}
	
	
	/**
	 * Auxiliary function to some Guards' movement
	 * Respecting some conditions (his path).
	 * 
	 * @param int index current position in path
	 * @param map char[][] path Guard's movement path.
	 * @param boolean direction current Guard's direction in path
	 */
	public int positionChange(int indice, char[] path, boolean direction)
	{
		int num;

		if(direction)		
			num = 1;		
		else
			num = -1;

		positionChangeSwitch(indice,path,num);
		indice += num;
		if(indice == -1)
			indice = path.length - 1;
		else if(indice == path.length)	
			indice = 0;

		return indice;
	}

	/**
	 * Auxiliary function to positionChange function
	 * Respecting some conditions (his path).
	 * 
	 * @param int index current position in path
	 * @param map char[][] path Guard's movement path.
	 * @param num direction number, can be 1 or -1.
	 */
	public void positionChangeSwitch(int indice, char[] path, int num)
	{
		switch(path[indice] )
		{
		case 'd':
		{
			yPos += num;
			break;
		}
		case 'u':
		{
			yPos -= num;
			break;
		}
		case 'l':
		{
			xPos -= num;
			break;
		}
		case 'r':
		{
			xPos += num;
			break;
		}
		default: break;
		}
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
