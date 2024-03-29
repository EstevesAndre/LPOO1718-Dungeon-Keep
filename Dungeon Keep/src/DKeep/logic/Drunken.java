package DKeep.logic;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Class Drunken - Character - Guard - Drunken 
 * 
 * Type of guard.
 * Which stops randomly for a specific time (2-5) seconds, also random.
 * 
 * @author Andr� Esteves and Lu�s Diogo Silva
 * @version 1.0
 * @since 2018-03-30
 */
public class Drunken extends Guard{

	/**
	 * Direction of the Drunken Guard.
	 * Can be true, normal path, or false, inverted path.
	 */
	boolean direction;

	/**
	 * Value of the initial probability of the Drunken Guard stop.
	 * Standard value is float(1/3).
	 */
	float modifier;

	/**
	 * Index of the Drunken Guard on his path.
	 * Initial value when created is 0.  
	 */
	int indice;

	/**
	 * Symbol of the Drunken Guard.
	 * When Guard is in moving state, symbol is equal to 'G'.
	 * When he is sleeping, symbol is equal to 'g'.
	 */
	char symbol;

	/**
	 * Number of movements of Hero can do without Drunken Guard move.
	 * Starts at 0.
	 * Drunken Guard moves normally.
	 * If Drunken fall asleep sleepCount is set.
	 * Each Hero move, sleepCount is decremented.
	 * If reaches 0, Drunken Guard wake up and move normally.
	 */
	int sleepCount;

	/**
	 * Path of the Drunken Guard.
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
	 * Creates a new Drunken Guard with the given position, x and y.
	 * @param x X position.
	 * @param y Y position.
	 */
	public Drunken(int x, int y)
	{
		super(x,y);
		direction = true;
		modifier = (float)1/3;
		indice = 0;
		symbol = 'G';
		sleepCount = 0;
	}

	/**
	 * Creates a new Drunken Guard with the given position, x and y, direction, modifier, indice, symbol and sleepCount.
	 * @param x X position.
	 * @param y Y position.
	 * @param dir direction of the Guard.
	 * @param mod modifier, probability of fall asleep.
	 * @param ind index on Drunken path.
	 * @param sleep sleepCount of Guard sleep.
	 */
	public Drunken(int x, int y, boolean dir, float mod, int ind, int sleep) {
		super(x,y);
		direction = dir;
		modifier = mod;
		indice = ind;
		if(sleep == 0)
			symbol = 'G';
		else
			symbol = 'g';
		sleepCount = sleep;
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

		if(sleepCount == 0)
		{
			moveWithoutSleepCount(map);
		}
		else
		{
			sleepCount--;
			if(sleepCount == 0){
				moveWithSleepCount(map);
			}
		}

	}

	/**
	 * Moves the Guard in the respective given map when sleepCount is not 0.
	 * 
	 * Respecting the logic of the game.
	 * 
	 * @param map char[][] Map to be set.
	 */
	public void moveWithSleepCount (char[][] map)
	{
		Random nr = new Random();
		if(nr.nextBoolean()) {
			if(direction)
				indice--;
			else
				indice ++;
			if(indice == -1)
				indice = path.length - 1;
			else if(indice == path.length)
				indice = 0;

			direction = !direction;
		}				
		symbol = 'G';

	}

	/**
	 * Moves the Guard in the respective given map when sleepCount is 0.
	 * 
	 * Respecting the logic of the game.
	 * 
	 * @param map char[][] Map to be set.
	 */
	public void moveWithoutSleepCount (char[][] map) 
	{
		map[yPos][xPos] = ' ';

		indice = super.positionChange(indice, path, direction);

		checkSleep();

		map[yPos][xPos] = symbol;
	}

	/**
	 * Increments modifier and sets sleepCount.
	 * 
	 * If sleepCount is set, Drunken Guard fall asleep.
	 * 
	 * @return true if set asleep, false otherwise
	 */
	public boolean checkSleep ()
	{
		Random nr = new Random();
		float value = nr.nextFloat() * modifier;
		if(value >= 0.8){
			symbol = 'g';
			sleepCount = nr.nextInt(3) + 2;
			modifier = (float)1/3;
			return true;
		}
		else{
			modifier += (float)1/3;
			return false;
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
			writer.write(this.sleepCount + "\n");
		} catch (IOException e) {
			throw new IOException();
		}

	}

}
