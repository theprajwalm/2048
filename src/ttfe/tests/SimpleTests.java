package ttfe.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
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
	public void testWrongAddPiece1() {
		// Fill the board completely
		for (int x = 0; x < game.getBoardWidth(); x++) {
			for (int y = 0; y < game.getBoardHeight(); y++) {
				game.setPieceAt(x, y, 2); // Setting each position with a piece (value 2)
			}
		}
		
		// Verify the board is full
		assertEquals("The number of pieces should equal the number of tiles on the board", 
			game.getBoardWidth() * game.getBoardHeight(), game.getNumPieces());

		// Attempt to add a piece and expect an IllegalStateException
		assertThrows("Adding a piece to a full board should throw an IllegalStateException",
			IllegalStateException.class, () -> {
				game.addPiece();
			});
	}

	@Test
	public void testInitalIsSpaceLeft(){
		//when board is empty.
		assertTrue("There is still space left", game.isSpaceLeft());
	}

	@Test
	public void testPartialIsSpaceLeft(){
		//When some sort of places are occupied.
		game.setPieceAt(0, 3, 4);
		game.setPieceAt(3, 3, 2);
		assertTrue("There is still spaces left in the game",game.isSpaceLeft());
	}

	@Test
	public void testFullIsSpaceLeft(){
		//testing for the full board
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 5; j++) {
				game.setPieceAt(i, j, (i + j) % 2 == 0 ? 2 : 4);
			}
		}
		assertFalse("There is no more space left in the board", game.isSpaceLeft());
	}

	@Test
	public void testEastWestMovePossible() {
		// only east and west moves are possible
		game.setPieceAt(0, 0, 2);
		game.setPieceAt(0, 1, 0);
		game.setPieceAt(0, 2, 2);
		game.setPieceAt(0, 3, 0);
		game.setPieceAt(1, 0, 4);
		game.setPieceAt(1, 1, 0);
		game.setPieceAt(1, 2, 4);
		game.setPieceAt(1, 3, 0);
		game.setPieceAt(2, 0, 2);
		game.setPieceAt(2, 1, 0);
		game.setPieceAt(2, 2, 2);
		game.setPieceAt(2, 3, 0);
		game.setPieceAt(3, 0, 4);
		game.setPieceAt(3, 1, 0);
		game.setPieceAt(3, 2, 4);
		game.setPieceAt(3, 3, 0);
		
		//moves for east and west should be possible
		assertTrue("There is no Space to move to east",game.isMovePossible(MoveDirection.EAST));
		assertTrue("There is no Space to move to west", game.isMovePossible(MoveDirection.WEST));

		//moves for north and sourth should not be possible
		assertTrue("Move to the north is not possible", !game.isMovePossible(MoveDirection.NORTH));
		assertTrue("Move to the south is not possible", !game.isMovePossible(MoveDirection.SOUTH));
	}

	@Test
	public void testNorthSouthMovePossible(){
		//only North and south moves are possible
		game.setPieceAt(0, 0, 2);
		game.setPieceAt(1, 0, 0);
		game.setPieceAt(2, 0, 2);
		game.setPieceAt(3, 0, 0);
		game.setPieceAt(0, 1, 4);
		game.setPieceAt(1, 1, 0);
		game.setPieceAt(2, 1, 4);
		game.setPieceAt(3, 1, 0);
		game.setPieceAt(0, 2, 2);
		game.setPieceAt(1, 2, 0);
		game.setPieceAt(2, 2, 2);
		game.setPieceAt(3, 2, 0);
		game.setPieceAt(0, 3, 4);
		game.setPieceAt(1, 3, 0);
		game.setPieceAt(2, 3, 4);
		game.setPieceAt(3, 3, 0);

		//moves for north and south should be possible
		assertTrue("There is no Space to move to North", game.isMovePossible(MoveDirection.NORTH));
		assertTrue("There is no Space to move to South", game.isMovePossible(MoveDirection.SOUTH));

		//moves for west and east should not be possible
		assertTrue("There is Space to move to east",game.isMovePossible(MoveDirection.EAST));
		assertTrue("There is  Space to move to west", game.isMovePossible(MoveDirection.WEST));
	}

	//if the direction is null
	@Test
	public void testWrongPerformMoveNULL() {
		// Attempt to perform a move with a null direction and expect an IllegalArgumentException
		assertThrows("Performing a move with null direction should throw an IllegalArgumentException",
			IllegalArgumentException.class, () -> {
				game.performMove(null);
			});
	}

	//if the direction is performed south
	@Test
	public void testWrongPerformMove1(){
		game.setPieceAt(0, 0, 2);
		game.setPieceAt(0, 1, 2);
		game.setPieceAt(0, 2, 2);
		game.setPieceAt(0, 3, 4);
		game.setPieceAt(1, 0, 2);
		game.setPieceAt(1, 1, 2);
		game.setPieceAt(1, 2, 2);
		game.setPieceAt(1, 3, 4);
		game.setPieceAt(2, 2, 2);		
		game.setPieceAt(2, 3, 2);
		game.setPieceAt(3, 2, 2);
		game.setPieceAt(3, 3, 2);
		game.performMove(MoveDirection.EAST);

		assertEquals("Perform Move in East was not implemented correctly.", 0, game.getPieceAt(0, 0));
		assertEquals("Perform Move in East was not implemented correctly.", 2, game.getPieceAt(0, 1));
		assertEquals("Perform Move in East was not implemented correctly.", 4, game.getPieceAt(0, 2));
		assertEquals("Perform Move in East was not implemented correctly.", 4, game.getPieceAt(0, 3));

		assertEqprog2uals("Perform Move in East was not implemented correctly.", 0, game.getPieceAt(1, 0));
		assertEquals("Perform Move in East was not implemented correctly.", 0, game.getPieceAt(1, 1));
		assertEquals("Perform Move in East was not implemented correctly.", 4, game.getPieceAt(1, 2));
		assertEquals("Perform Move in East was not implemented correctly.", 8, game.getPieceAt(1, 3));

		assertEquals("Perform Move in East was not implemented correctly.", 0, game.getPieceAt(2, 2));
		assertEquals("Perform Move in East was not implemented correctly.", 0, game.getPieceAt(2, 3));
		assertEquals("Perform Move in East was not implemented correctly.", 4, game.getPieceAt(2, 3));

		assertEquals("Perform Move in East was not implemented correctly.", 0, game.getPieceAt(3, 2));
		assertEquals("Perform Move in East was not implemented correctly.", 4, game.getPieceAt(3, 3));

		game.performMove(MoveDirection.SOUTH);
		assertEquals("Perform Move in South was not implemented correctly.", 4, game.getPieceAt(1, 3));
		assertEquals("Perform Move in South was not implemented correctly.", 8, game.getPieceAt(2, 3));
		assertEquals("Perform Move in South was not implemented correctly.", 0, game.getPieceAt(2, 0));
		assertEquals("Perform Move in South was not implemented correctly.", 2, game.getPieceAt(3, 0));
		assertEquals("Perform Move in South was not implemented correctly.", 0, game.getPieceAt(3, 1));
		assertEquals("Perform Move in South was not implemented correctly.", 8, game.getPieceAt(3, 2));
		assertEquals("Perform Move in South was not implemented correctly.", 8, game.getPieceAt(3, 3));

		game.performMove(MoveDirection.WEST);
		assertEquals("Perform Move in West was not implemented correctly.", 4, game.getPieceAt(1, 0));
		assertEquals("Perform Move in West was not implemented correctly.", 8, game.getPieceAt(2, 0));
		assertEquals("Perform Move in West was not implemented correctly.", 2, game.getPieceAt(3, 0));
		assertEquals("Perform Move in West was not implemented correctly.", 16, game.getPieceAt(3, 1));

		game.performMove(MoveDirection.NORTH);
		assertEquals("Perform Move in North was not implemented correctly.", 4, game.getPieceAt(0, 0));
		assertEquals("Perform Move in North was not implemented correctly.", 16,game.getPieceAt(0, 1));
		assertEquals("Perform Move in North was not implemented correctly.", 8, game.getPieceAt(1, 0));
		assertEquals("Perform Move in North was not implemented correctly.", 2, game.getPieceAt(2, 0));
	}

	@Test
	public void testWrongPoints1(){
		assertEquals("Initial game points should be zero", 0,game.getPoints());
		game.performMove(MoveDirection.SOUTH);
		game.performMove(MoveDirection.EAST);
		game.performMove(MoveDirection.NORTH);
		game.performMove(MoveDirection.WEST);
		assertTrue("After some moves points should increase", game.getPoints() > 0);
	}
}