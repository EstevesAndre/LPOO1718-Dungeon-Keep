package DKeep.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import DKeep.logic.Game;

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

}
