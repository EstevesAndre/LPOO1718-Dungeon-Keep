package DKeep.test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

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
	public void testMoveHeroIntoToClosedDoor() {
		Game g = new Game();
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
		Game g = new Game();
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

	@Test
	public void testSwingRandomBehaviour(){
		Game g = new Game();
		g.advanceLevel();
		g.setOgre(4, 4);
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