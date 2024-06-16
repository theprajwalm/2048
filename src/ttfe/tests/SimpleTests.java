package ttfe.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

	//Test for getnummoves
	@Test
	public void testWrongGetNumMoves1() {

		// Simulate zero move
		assertTrue("The initial game board did not have correct ",
		0 == game.getNumMoves());

		// Simulate one moves
		game.performMove(MoveDirection.NORTH);


		// Verify the number of moves made is correct
		assertEquals("Number of moves after one valid move is not correct", 1, game.getNumMoves());
	}

	@Test
	public void testInitialGetNumPieces1(){
		//initial pieces
		assertEquals("Initial Pieces must be two",2,game.getNumPieces());

		//adding pieces
		game.addPiece();
		assertEquals("After adding a Piece it should be three",3,game.getNumPieces());

	}

	@Test
	public void testInitalIsSpaceLeft2(){
		//when board is empty.
		assertTrue("There is still space left", game.isSpaceLeft());

		//When some sort of places are occupied.
		game.setPieceAt(0, 3, 4);
		game.setPieceAt(3, 3, 2);
		assertTrue("There is still spaces left in the game",game.isSpaceLeft());

		//testing for the full board
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 5; j++) {
				if (j % 2 == 0 & i % 2 != 0) {
					game.setPieceAt(i, j, 4);
				} else {
					game.setPieceAt(i, j, 2);
				}
			}
		}
		assertTrue("There is no more space left in the board", game.isSpaceLeft());
	}

	@Test
	public void testWrongMovepossible1(){
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 5; j++) {
				if (j % 2 == 0 & i % 2 != 0) {
					game.setPieceAt(i, j, 4);
				} else {
					game.setPieceAt(i, j, 2);
				}
			}
		}
		assertFalse("There is move possible",game.isMovePossible());
	}
}