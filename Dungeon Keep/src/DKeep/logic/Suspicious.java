package DKeep.logic;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

public class Suspicious extends Guard {

	boolean direction;
	float modifier;
	int indice;
	char[] path = {'l','d','d','d','d',
			'l','l','l','l','l','l',
			'd','r','r','r','r','r',
			'r','r','u','u','u','u','u'
	};

	public Suspicious(int x, int y)
	{
		super(x,y);
		direction = true;
		modifier = (float)1/3;
		indice = 0;
	}

	public Suspicious(int x, int y, boolean dir, float mod, int ind) {
		super(x,y);
		direction = dir;
		modifier = mod;
		indice = ind;
	}

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
