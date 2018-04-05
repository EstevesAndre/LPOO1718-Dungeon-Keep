package DKeep.logic;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Class Hero - Character -> Hero
 * 
 * Type of Character.
 * 
 * @author André Esteves && Luís Diogo Silva
 * @version 1.0
 * @since 2018-03-31
 */
public class Hero extends Character{

	/**
	 * Character behind Hero's position. To 'save' what's behind him when he moves on the map.
	 * Initial currentPos is most of times ' '. 
	 */
	private char currentPos;
	
	/**
	 * Symbol of the Hero.
	 * On the first level the symbol is equal to 'H'.
	 * On the second level the initial symbol is equal to 'A'. 
	 * If he reaches the key of the door ('k') the symbol of the Hero changes to 'K'.
	 */
	private char symbol;

	/**
	 * Creates a new Hero with the given position, x and y, currentPos and symbol.
	 * 
	 * @param x X position on the map.
	 * @param y Y position on the map.
	 * @param pos current character behind Hero's position.
	 * @param sym symbol of the current Hero.
	 */
	public Hero(int x, int y, char pos, char sym)
	{
		super(x, y);
		currentPos = pos;
		symbol = sym;
	}

	/**
	 * Gets current character behind Hero's position.
	 * @return currentPos character on the map.
	 */
	public char getCurrentPos()
	{
		return currentPos;
	}
	
	/**
	 * Gets symbol of this Hero.
	 * @return symbol of Hero.
	 */
	public char getSymbol()
	{
		return symbol;
	}
	
	/**
	 * Sets current position variable of this Hero.
	 * @param pos New character of currentPos variable, character behind this Hero.
	 */
	public void setCurrentPos(char pos)
	{
		currentPos = pos;
	}
	
	/**
	 * Sets the symbol of Hero.
	 * @param sym New symbol of Hero.
	 */
	public void setSymbol(char sym)
	{
		symbol = sym;
	}
	
	
	/**
	 * Moves the hero on the map given the respective move m.
	 * @param m Move of Hero. Can be:
	 * 		m == 'a' - moves left;
	 * 		m == 'd' - moves right;
	 * 		m == 'w' - moves up;
	 * 		m == 's' - moves down;
	 * @param map Map where Hero's going to move.
	 */
	public void move(char m, char[][] map)
	{
		if(m == 'a')
			moveA(map);		
		else if(m == 'd')
			moveD(map);		
		else if(m == 'w')
			moveW(map);		
		else if(m == 's')
			moveS(map);		
		
		if(currentPos == 'k' && symbol == 'A'){
			symbol = 'K';
			currentPos = ' ';
			map[yPos][xPos] = symbol;
		}
	}
	
	/**
	 * Moves Hero to left on the given map char[][].
	 * @param map Map where Hero's going to move.
	 */
	public void moveA(char [][] map)
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
	}

	/**
	 * Moves Hero to right on the given map char[][].
	 * @param map Map where Hero's going to move.
	 */
	public void moveD(char [][] map)
	{
		if(map[yPos][xPos+1] != 'X' && map[yPos][xPos+1] != 'I')
		{
			map[yPos][xPos] = currentPos;
			xPos++;
			currentPos = map[yPos][xPos];
			map[yPos][xPos] = symbol;
		}
	}

	/**
	 * Hero moves up on the given map char[][].
	 * @param map Map where Hero's going to move.
	 */
	public void moveW(char [][] map)
	{
		if(map[yPos-1][xPos] != 'X' && map[yPos-1][xPos] != 'I')
		{
			map[yPos][xPos] = currentPos;
			yPos--;
			currentPos = map[yPos][xPos];
			map[yPos][xPos] = symbol;
		}
	}

	/**
	 * Hero moves down on the given map char[][].
	 * @param map Map where Hero's going to move.
	 */
	public void moveS(char [][] map)
	{
		if(map[yPos+1][xPos] != 'X' && map[yPos+1][xPos] != 'I')
		{
			map[yPos][xPos] = currentPos;
			yPos++;
			currentPos = map[yPos][xPos];
			map[yPos][xPos] = symbol;
		}
	}
	
	/**
	 * Saves the information of the Hero.
	 * 
	 * @param writer BufferedWriter writer to be set with the respective info.
	 * @throws IOException Throws exception if fails on writing. 
	 */
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
