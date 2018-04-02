package DKeep.logic;


/**
 * Character.java - Character class.
 * 
 * Represents the general character.
 * Which has x position and y position.
 * 
 * @author André Esteves && Luís Diogo Silva
 * @version 1.0
 * @since 2018-03-30
 */
public class Character {

	/**
	 * Represents the x position of the Character.
	 */
	protected int xPos;

	/**
	 * Represents the y position of the Character.
	 */
	protected int yPos;

	/**
	 * Creates a new Character with the given position, x and y.
	 * 
	 * @param x X position.
	 * @param y Y position.
	 */
	public Character(int x, int y)
	{
		xPos = x;
		yPos = y;
	}

	/**
	 * Gets the X position of this Character.
	 * @return this Character's X position, xPos.
	 */
	public int getX()
	{
		return xPos;
	}

	/**
	 * Gets the Y position of this Character.
	 * @return this Character's Y position, yPos.
	 */
	public int getY()
	{
		return yPos;
	}

	/**
	 * Changes the Position(x and y) of this Character.
	 * 
	 * @param x This Character's new x position.
	 * @param y This Character's new y position.
	 */
	public void setPos(int x, int y)
	{
		xPos = x;
		yPos = y;
	}
}
