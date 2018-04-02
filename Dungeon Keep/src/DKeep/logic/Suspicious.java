package DKeep.logic;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Class Suspicious - Character -> Guard -> Suspicious 
 * 
 * Type of guard.
 * Which stops randomly and changes direction.
 * 
 * 
 * @author André Esteves && Luís Diogo Silva
 * @version 1.0
 * @since 2018-04-02
 */
public class Suspicious extends Guard {

	/**
	 * Direction of the Suspicious Guard.
	 * Can be true, normal path, or false, inverted path.
	 */
	boolean direction;
	
	/**
	 * Value of the initial probability of the Suspicious Guard stop.
	 * Standard value is float(1/3).
	 */
	float modifier;
	
	/**
	 * Indice of the Suspicious Guard on his path.
	 * Initial value when created is 0.  
	 */
	int indice;
	
	/**
	 * Path of the Suspicious Guard.
	 * Array of Char's.
	 * Meaning:
	 * 'l' - left.
	 * 'd' - down.
	 * 'r' - right.
	 * 'u' - up.
	 */
	char[] path = {'l','d','d','d','d',
			'l','l','l','l','l','l',
			'd','r','r','r','r','r',
			'r','r','u','u','u','u','u'
	};
	
	/**
	 * Creates a new Suspicious Guard with the given position, x and y.
	 * @param x X position.
	 * @param y Y position.
	 */
	public Suspicious(int x, int y)
	{
		super(x,y);
		direction = true;
		modifier = (float)1/3;
		indice = 0;
	}

	/**
	 * Creates a new Suspicious Guard with the given position, x and y, direction, modifier, indice.
	 * @param x X position.
	 * @param y Y position.
	 * @param dir direction of the Guard.
	 * @param mod modifier, probability of fall asleep.
	 * @param ind indice on Suspicious path.
	 */
	public Suspicious(int x, int y, boolean dir, float mod, int ind) {
		super(x,y);
		direction = dir;
		modifier = mod;
		indice = ind;
	}

	/**
	 * Moves the Guard in the respective given map.
	 * 
	 * Respecting the logic of the game.
	 * 
	 * @param map char[][] Map to be set.
	 */
	public void move(char[][] map) 
	{
		map[yPos][xPos] = ' ';

		int num;

		if(direction)
		{
			num = 1;
		}
		else
		{
			num = -1;
		}

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

		indice += num;

		if(indice == -1)
		{
			indice = path.length - 1;
		}
		else if(indice == path.length)
		{
			indice = 0;
		}
		
		checkChangeDirection();

		map[yPos][xPos] = 'G';
	}

	/**
	 * Increments modifier by 1/3.
	 * 
	 * If the value set in this function is greater or equal than 0.8 the Guard changes direction.
	 */
	private void checkChangeDirection ()
	{
		Random nr = new Random();

		float value = nr.nextFloat() * modifier;

		if(value >= 0.8)
		{
			if(direction)
			{
				indice--;
			}
			else
			{
				indice ++;
			}
			
			if(indice == -1)
			{
				indice = path.length - 1;
			}
			else if(indice == path.length)
			{
				indice = 0;
			}
			
			direction = !direction;
			modifier = (float)1/3;
		}
		else
		{
			modifier += (float)1/3;
		}
	}
	
	/**
	 * Saves the information of the Guard.
	 * 
	 * @param writer BufferedWriter writer to be set with the respective info.
	 * @throws IOException Throws exception if fails on writing. 
	 */
	public void saveGame(BufferedWriter writer) throws IOException {
		try {
			super.saveGame(writer);
			writer.write(this.direction + "\n");
			writer.write(this.modifier + "\n");
			writer.write(this.indice + "\n");
		} catch (IOException e) {
			throw new IOException();
		}
		
	}
}
