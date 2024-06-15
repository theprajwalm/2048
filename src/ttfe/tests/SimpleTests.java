package ttfe.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.beans.Transient;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import ttfe.MoveDirection;
import ttfe.SimulatorInterface;
import ttfe.TTFEFactory;

/**
 * This class provides a very simple example of how to write tests for this project.
 * You can implement your own tests within this class or any other class within this package.
 * Tests in other packages will not be run and considered for completion of the project.
 */
public class SimpleTests {

	private SimulatorInterface game;

	@Before
	public void setUp() {
		game = TTFEFactory.createSimulator(4, 4, new Random(0));
	}
	
	@Test
	public void testInitialGamePoints() {
		assertEquals("The initial game did not have zero points", 0,
				game.getPoints());
	}
	
	@Test
	public void testInitialBoardHeight() {
		assertTrue("The initial game board did not have correct height",
				4 == game.getBoardHeight());
	}

	@Test
	public void testInitialBoardwidth() {
		assertTrue("The initial game board did not have correct width",
		4 == game.getBoardWidth());
	}

	@Test
	public void testInitialGetNummoves(){
		assertTrue("The initial game board did not have correct ",
		0 == game.getNumMoves());
	}
	@Test
	public void testWrongGetNumMoves1() {
		// Initial number of moves should be 0
		assertEquals("Initial number of moves is not zero", 0, game.getNumMoves());

		// Simulate some valid moves
		game.performMove(MoveDirection.NORTH);
		game.performMove(MoveDirection.SOUTH);

		// Verify the number of moves made is correct
		assertEquals("Number of moves after two valid moves is not correct", 2, game.getNumMoves());

		// Simulate an invalid move (assuming performMove returns false and does not count as a move)
		boolean moveResult = game.performMove(null); // this should throw an IllegalArgumentException
		assertEquals("Number of moves after invalid move should remain the same", 2, game.getNumMoves());
	}

}