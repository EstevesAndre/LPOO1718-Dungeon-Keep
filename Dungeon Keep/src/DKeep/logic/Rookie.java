package DKeep.logic;

import java.io.BufferedWriter;
import java.io.IOException;

public class Rookie extends Guard{
	
	
	public Rookie(int x, int y)
	{
		super(x,y);
	}
	
	public void saveGame(BufferedWriter writer) throws IOException {
		try {
			super.saveGame(writer);
		} catch (IOException e) {
			throw new IOException();
		}
		
	}
}
