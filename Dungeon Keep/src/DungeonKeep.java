
public class DungeonKeep {
	
	public static char[][] createMap()
	{
		char[][] map = { {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'},
						 {'X', 'H', ' ', ' ', 'I', ' ', 'X', ' ', 'G', 'X'},
						 {'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'},
						 {'X', ' ', 'I', ' ', 'I', ' ', 'X', ' ', ' ', 'X'},
						 {'X', 'X', 'X', ' ', 'X', 'X', 'X', ' ', ' ', 'X'},
						 {'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
						 {'I', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
						 {'X', 'X', 'X', ' ', 'X', 'X', 'X', 'X', ' ', 'X'},
						 {'X', ' ', 'I', ' ', 'I', ' ', 'X', 'K', ' ', 'X'},
						 {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'} };
		
		return map;
		
	}
	
	public static void printMap(char[][] map)
	{
		for(char[] i:map)
		{
			for(char j:i)
			{
				System.out.print(j);
				System.out.print(' ');
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args)
	{
		char[][] mapInit = createMap();
		
		printMap(mapInit);
	}

}
