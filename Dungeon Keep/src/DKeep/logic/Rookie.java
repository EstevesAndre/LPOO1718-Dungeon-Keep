package DKeep.logic;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Class Rookie - Character - Guard - Rookie 
 * 
 * Type of guard.
 * Standard Guard.
 * 
 * @author André Esteves and Luís Diogo Silva
 * @version 1.0
 * @since 2018-04-02
 */
public class Rookie extends Guard{
	
	/**
	 * Creates a new Rookie Guard with the given position, x and y.
	 * @param x X position.
	 * @param y Y position.
	 */
	public Rookie(int x, int y)
	{
		super(x,y);
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
		} catch (IOException e) {
			throw new IOException();
		}
		
	}
}
