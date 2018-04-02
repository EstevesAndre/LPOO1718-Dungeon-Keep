package DKeep.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import DKeep.logic.Drunken;
import DKeep.logic.Game;
import DKeep.logic.Level_2;

public class TestDKLogic {

	@Test
	public void testMoveHeroIntoToFreeCell() {
		Game g = new Game();
		assertEquals('H', g.getMap()[g.getHero().getY()][g.getHero().getX()]);
		assertEquals(1, g.getHero().getX());
		assertEquals(1, g.getHero().getY());
		g.heroMove('d');
		assertEquals('H', g.getMap()[g.getHero().getY()][g.getHero().getX()]);
		assertEquals(2, g.getHero().getX());
		assertEquals(1, g.getHero().getY());
		g.heroMove('d');
		g.heroMove('s');
		assertEquals('H', g.getMap()[g.getHero().getY()][g.getHero().getX()]);
		assertEquals(3, g.getHero().getX());
		assertEquals(2, g.getHero().getY());
	}

	@Test
	public void testMoveHeroIntoToWall() {
		Game g = new Game();
		assertEquals('H', g.getMap()[g.getHero().getY()][g.getHero().getX()]);
		assertEquals(1, g.getHero().getX());
		assertEquals(1, g.getHero().getY());
		g.heroMove('s');
		assertEquals('H', g.getMap()[g.getHero().getY()][g.getHero().getX()]);
		assertEquals(1, g.getHero().getX());
		assertEquals(1, g.getHero().getY());
	}

	@Test
	public void testMoveHeroIntoToGuard() {
		Game g = new Game();
		g.setHero(8, 3);
		assertEquals('H', g.getMap()[g.getHero().getY()][g.getHero().getX()]);
		assertEquals(8, g.getHero().getX());
		assertEquals(3, g.getHero().getY());
		g.heroMove('w');
		assertEquals('H', g.getMap()[g.getHero().getY()][g.getHero().getX()]);
		assertEquals(8, g.getHero().getX());
		assertEquals(2, g.getHero().getY());
		assertEquals(2, g.evalStatus());
	}
	
	@Test
	public void testMoveGuardIntoHero() {
		Game g = new Game();
		g.setHero(7, 2);
		assertEquals('H', g.getMap()[g.getHero().getY()][g.getHero().getX()]);
		assertEquals(7, g.getHero().getX());
		assertEquals(2, g.getHero().getY());
		assertEquals(g.evalStatus(), 2);
		assertEquals('H', g.getMap()[g.getHero().getY()][g.getHero().getX()]);
		assertEquals(7, g.getHero().getX());
		assertEquals(2, g.getHero().getY());
	}

	@Test
	public void testMoveHeroIntoToClosedDoor() {
		Game g = new Game("Intermediate");
		g.setHero(1, 5);
		assertEquals('H', g.getMap()[g.getHero().getY()][g.getHero().getX()]);
		assertEquals(1, g.getHero().getX());
		assertEquals(5, g.getHero().getY());
		g.heroMove('a');
		assertEquals('H', g.getMap()[g.getHero().getY()][g.getHero().getX()]);
		assertEquals(1, g.getHero().getX());
		assertEquals(5, g.getHero().getY());
		assertEquals(0, g.evalStatus());
	}

	@Test
	public void testMoveHeroIntoToLever() {
		Game g = new Game("Advanced");
		g.setHero(8, 8);
		g.heroMove('a');
		assertEquals('I', g.getMap()[5][0]);
		g.evalStatus();
		assertEquals('S', g.getMap()[5][0]);
	}

	@Test
	public void testMoveHeroIntoToOpenDoor() {
		Game g = new Game();
		g.setHero(8, 8);
		g.heroMove('a');
		g.evalStatus();
		g.setHero(1, 5);
		assertEquals('H', g.getMap()[g.getHero().getY()][g.getHero().getX()]);
		assertEquals(1, g.getHero().getX());
		assertEquals(5, g.getHero().getY());
		g.heroMove('a');
		assertEquals('H', g.getMap()[g.getHero().getY()][g.getHero().getX()]);
		assertEquals(0, g.getHero().getX());
		assertEquals(5, g.getHero().getY());
		assertEquals(1, g.evalStatus());
	}

	@Test
	public void testStunOgre() {
		Game g = new Game();
		g.advanceLevel();
		g.setOgre(3, 7);
		g.heroMove('d');
		Level_2.evalStatus(g.getMap(), g.getHero(), g.getOgre());
		assertEquals('8', g.getMap()[7][3]);
		assertEquals(2, g.getOgre()[0].getSleepCount());
	}

	@Test
	public void testHeroRepresentationAtKeyCell() {
		Game g = new Game();
		g.advanceLevel();
		g.setOgre(7, 7);
		g.setHero(7, 2);
		assertEquals('A', g.getMap()[g.getHero().getY()][g.getHero().getX()]);
		g.heroMove('w');
		assertEquals('K', g.getMap()[g.getHero().getY()][g.getHero().getX()]);

	}

	@Test
	public void testHeroFailsOpenTheDoor() {
		Game g = new Game();
		g.advanceLevel();
		g.setOgre(7, 7);
		g.setHero(1, 1);
		assertEquals('A', g.getMap()[g.getHero().getY()][g.getHero().getX()]);
		g.heroMove('a');
		assertEquals(1, g.getHero().getX());
		assertEquals(1, g.getHero().getY());
		assertEquals('I', g.getMap()[1][0]);

	}

	@Test
	public void testHeroOpensTheDoor() {
		Game g = new Game();
		g.advanceLevel();
		g.setOgre(7, 7);
		g.setHero(7, 2);
		g.heroMove('w');
		assertEquals('K', g.getMap()[g.getHero().getY()][g.getHero().getX()]);
		g.setHero(1, 1);
		assertEquals('I', g.getMap()[1][0]);
		g.heroMove('a');
		assertEquals('S', g.getMap()[1][0]);
		assertEquals(1, g.getHero().getX());
		assertEquals(1, g.getHero().getY());
	}

	@Test
	public void testHeroWinsLevel() {
		Game g = new Game();
		g.advanceLevel();
		g.setOgre(7, 7);
		g.setHero(7, 2);
		g.heroMove('w');
		g.setHero(1, 1);
		g.heroMove('a');
		g.heroMove('a');
		assertEquals(0, g.getHero().getX());
		assertEquals(1, g.getHero().getY());
		assertEquals(1, g.evalStatus());
	}

	@Test
	public void testOgreRandomBehaviour(){
		for(int i = 0; i < 30; i++)
		{
			Game g = new Game();
			g.advanceLevel();
			g.setOgre(4, 4);
			boolean valMove = false;

			Level_2.ogreMove(g.getMap(), g.getOgre());

			if(g.getMap()[4][3] == '0' || 
					g.getMap()[3][4] == '0' || 
					g.getMap()[5][4] == '0' ||
					g.getMap()[4][5] == '0')
			{
				valMove = true;
			}


			assertEquals(true, valMove);
		}
	}

	@Test
	public void testSwingRandomBehaviour(){
		Game g = new Game();
		g.advanceLevel();
		g.setOgre(4, 4);
		for(int i = 0; i < 30; i++)
		{
			boolean valMove = false;

			Level_2.swingMove(g.getMap(), g.getOgre());

			if(g.getMap()[4][3] == '*' || 
					g.getMap()[3][4] == '*' || 
					g.getMap()[5][4] == '*' ||
					g.getMap()[4][5] == '*') 
			{
				valMove = true;
			}


			assertEquals(true, valMove);
		}
	}

	@Test
	public void testMapTransmition(){
		Game g = new Game();
		char[][] map = { {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'}, // size 9v9
				{'I', ' ', ' ', ' ', ' ', ' ', ' ', 'k', 'X'},
				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', 'A', ' ', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'} };
		g.advanceLevel(0, map);
		Assert.assertArrayEquals(g.getMap(), map);
		g = new Game();
		g.advanceLevel(0);
		Assert.assertArrayEquals(g.getMap(), map);
	}

	@Test
	public void testGuardMove() {
		Game g = new Game();
		int x = g.getGuard().getX();
		int y = g.getGuard().getY();
		for(int i = 0; i < 24; i++)
		{
			g.evalStatus();
		}

		assertEquals(x, g.getGuard().getX());
		assertEquals(y, g.getGuard().getY());

		g = new Game("Intermediate");
		
		x = g.getGuard().getX();
		y = g.getGuard().getY();
		g.evalStatus();
		assertTrue(x != g.getGuard().getX() || y != g.getGuard().getY());
		
		for(int i = 0; i < 500; i++)
		{
			x = g.getGuard().getX();
			y = g.getGuard().getY();
			g.evalStatus();
			assertTrue(x == g.getGuard().getX() || x == g.getGuard().getX() + 1 || x == g.getGuard().getX() - 1);
			assertTrue(y == g.getGuard().getY() || y == g.getGuard().getY() + 1 || y == g.getGuard().getY() - 1);		
		}



		g = new Game("Advanced");
		
		x = g.getGuard().getX();
		y = g.getGuard().getY();
		g.evalStatus();
		assertTrue(x != g.getGuard().getX() || y != g.getGuard().getY());
		for(int i = 0; i < 500; i++)
		{
			x = g.getGuard().getX();
			y = g.getGuard().getY();
			g.evalStatus();
			assertTrue(x == g.getGuard().getX() || x == g.getGuard().getX() + 1 || x == g.getGuard().getX() - 1);
			assertTrue(y == g.getGuard().getY() || y == g.getGuard().getY() + 1 || y == g.getGuard().getY() - 1);		
		}


	}

	@Test
	public void testGuardSleep(){
		Game g = new Game("Intermediate");

		while(!((Drunken)g.getGuard()).checkSleep());

		int x = g.getGuard().getX();
		int y = g.getGuard().getY();
		g.evalStatus();
		assertTrue(x == g.getGuard().getX());
		assertTrue(y == g.getGuard().getY());

		x = g.getGuard().getX();
		y = g.getGuard().getY();
		g.evalStatus();
		assertTrue(x == g.getGuard().getX());
		assertTrue(y == g.getGuard().getY());

	}

	@Test
	public void testOgreBehaviour(){
		Game g = new Game("Intermediate");
		g.advanceLevel(1);

		for(int i = 0; i < g.getOgre().length; i++)
		{
			assertTrue(g.getOgre()[i].getSymbol() == '0' || g.getOgre()[i].getSymbol() == '$');
			g.getOgre()[i].stun(g.getMap());
			assertEquals(g.getOgre()[i].getSymbol(), '8');
		}
		
		g = new Game("Intermediate");
		g.advanceLevel(1);
		
		int x = g.getOgre()[0].getX();
		int y = g.getOgre()[0].getY();
		g.evalStatus();
		assertTrue(x != g.getOgre()[0].getX());
		assertTrue(y == g.getOgre()[0].getY());

	}

}