package DKeep.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import DKeep.logic.Game;
import DKeep.logic.Level_2;

class TestDungeonGameLogic {

	@Test
	void testMoveHeroIntoToFreeCell() {
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
	void testMoveHeroIntoToWall() {
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
	void testMoveHeroIntoToGuard() {
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
	void testMoveHeroIntoToClosedDoor() {
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
	void testMoveHeroIntoToLever() {
		Game g = new Game();
		g.setHero(8, 8);
		g.heroMove('a');
		assertEquals('I', g.getMap()[5][0]);
		g.evalStatus();
		assertEquals('S', g.getMap()[5][0]);
	}
	
	@Test
	void testMoveHeroIntoToOpenDoor() {
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
	void testStunOgre() {
		Game g = new Game();
		g.advanceLevel();
		g.setOgre(3, 7);
		g.heroMove('d');
		Level_2.evalStatus(g.getMap(), g.getHero(), g.getOgre());
		assertEquals('8', g.getMap()[7][3]);
		assertEquals(2, g.getOgre()[0].getSleepCount());
	}
	
	@Test
	void testHeroRepresentationAtKeyCell() {
		Game g = new Game();
		g.advanceLevel();
		g.setOgre(7, 7);
		g.setHero(7, 2);
		assertEquals('A', g.getMap()[g.getHero().getY()][g.getHero().getX()]);
		g.heroMove('w');
		assertEquals('K', g.getMap()[g.getHero().getY()][g.getHero().getX()]);
		
	}
	
	@Test
	void testHeroFailsOpenTheDoor() {
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
	void testHeroOpensTheDoor() {
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
	void testHeroWinsLevel() {
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
}
















