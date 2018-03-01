package DKeep.logic;

public class Character {

	protected int xPos;
	protected int yPos;
	
	public Character(int x, int y)
	{
		xPos = x;
		yPos = y;
	}
	
	public int getX()
	{
		return xPos;
	}
	
	public int getY()
	{
		return yPos;
	}
	
	public void setPos(int x, int y)
	{
		xPos = x;
		yPos = y;
	}
}
