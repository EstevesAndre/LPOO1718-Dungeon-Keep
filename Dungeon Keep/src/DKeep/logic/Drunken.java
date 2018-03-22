package DKeep.logic;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

public class Drunken extends Guard{

	boolean direction;
	float modifier;
	int indice;
	char symbol;
	int sleepCount;
	char[] path = {'l','d','d','d','d',
			'l','l','l','l','l','l',
			'd','r','r','r','r','r',
			'r','r','u','u','u','u','u'
	};

	public Drunken(int x, int y)
	{
		super(x,y);
		direction = true;
		modifier = (float)1/3;
		indice = 0;
		symbol = 'G';
		sleepCount = 0;
	}

	public Drunken(int x, int y, boolean dir, float mod, int ind, char sym, int sleep) {
		super(x,y);
		direction = dir;
		modifier = mod;
		indice = ind;
		symbol = sym;
		sleepCount = sleep;
	}

	public void move(char[][] map) 
	{

		if(sleepCount == 0)
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

			checkSleep();

			map[yPos][xPos] = symbol;
		}
		else
		{
			sleepCount--;

			if(sleepCount == 0)
			{
				Random nr = new Random();

				if(nr.nextBoolean()) {

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
				}				
				symbol = 'G';
			}
		}
	}

	private void checkSleep ()
	{
		Random nr = new Random();

		float value = nr.nextFloat() * modifier;

		if(value >= 0.8)
		{
			symbol = 'g';

			sleepCount = nr.nextInt(3) + 2;

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
			writer.write(this.symbol + "\n");
			writer.write(this.sleepCount + "\n");
		} catch (IOException e) {
			throw new IOException();
		}
		
	}

}
