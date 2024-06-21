package ttfe.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.beans.Transient;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import ttfe.MoveDirection;
import ttfe.PlayerInterface;
import ttfe.SimulatorInterface;
import ttfe.TTFEFactory;
import ttfe.UserInterface;
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
		for (int i = 0; i < game.getBoardHeight(); i++) {
			for (int j = 0; j < game.getBoardWidth(); j++) {
				game.setPieceAt(i, j, (i + j) % 2 == 0 ? 2 : 4);
			}
		}
		assertFalse("There is no more space left in the board", game.isSpaceLeft());
	}

	@Test
	public void testEastWestMovePossible() {
		// only east and west moves are possible
		int [][] exampleBoard = {
			{2, 4, 8, 2},
			{4, 8, 16, 8},
			{2, 16, 4, 32},
			{4, 4, 2, 8}
		};
		
		//looping the numbers in the board
		for(int i = 0 ; i < game.getBoardWidth();i++){
			for(int j = 0; j < game.getBoardHeight();j++){
				game.setPieceAt(i, j, exampleBoard[i][j]);
			}
		}

		//moves for east and west should be possible
		assertTrue("Should be only east and west move possible ", game.isMovePossible());
	}

	@Test
	public void testNorthSouthMovePossible(){
		//only North and south moves are possible
		int [][] exampleBoard1 = {
			{2, 4, 8, 2},
			{4, 8, 16, 8},
			{2, 16, 4, 32},
			{4, 16, 2, 8}
		};

		for(int i = 0 ; i < game.getBoardWidth();i++){
			for(int j = 0; j < game.getBoardHeight();j++){
				game.setPieceAt(i, j, exampleBoard1[i][j]);
			}
		}

		//moves for north and south should be possible
		assertTrue("Only North and South move should be possible", game.isMovePossible());
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

	//if the direction is performed north
	@Test
	public void testWrongNorthPerformMove1(){
		int [][] performBoard = {
			{0, 0, 0, 4},
			{0, 0, 0, 4},
			{0, 0, 0, 0},
			{0, 0, 0, 0}	
		};

		for(int i = 0 ; i < game.getBoardWidth();i++){
			for(int j = 0; j < game.getBoardHeight();j++){
				game.setPieceAt(j, i, performBoard[i][j]);
			}
		}
		assertTrue("move should be possible",game.performMove(MoveDirection.NORTH));

	//if the direction is performed East
		int [][] performBoard1 = {
			{0, 0, 4, 4},
			{0, 0, 0, 0},
			{0, 0, 0, 0},
			{0, 0, 0, 0}
		};

		for(int i = 0 ; i < game.getBoardWidth();i++){
			for(int j = 0; j < game.getBoardHeight();j++){
				game.setPieceAt(j, i, performBoard1[i][j]);
			}
		}
		assertTrue("move should be possible",game.performMove(MoveDirection.EAST));

		//if the direction is performed west
			int [][] performBoard2 = {
				{0, 0, 4, 4},
				{0, 0, 0, 0},
				{0, 0, 0, 0},
				{0, 0, 0, 0}
			};
	
			for(int i = 0 ; i < game.getBoardWidth();i++){
				for(int j = 0; j < game.getBoardHeight();j++){
					game.setPieceAt(j, i, performBoard2[i][j]);
				}
			}
			assertTrue("move should be possible",game.performMove(MoveDirection.WEST));

		//if the direction is performed South
			int [][] performBoard3 = {
				{0, 0, 0, 4},
				{0, 0, 0, 4},
				{0, 0, 0, 0},
				{0, 0, 0, 0}
			};
	
			for(int i = 0 ; i < game.getBoardWidth();i++){
				for(int j = 0; j < game.getBoardHeight();j++){
					game.setPieceAt(j, i, performBoard3[i][j]);
				}
			}
			assertTrue("move should be possible",game.performMove(MoveDirection.SOUTH));
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


@Test
    public void testRun() {
        PlayerInterface player = TTFEFactory.createPlayer(false);
        UserInterface ui = TTFEFactory.createUserInterface(game);
        game.run(player, ui);

		assertNotNull(game);
        // more here
    }	

    @Test(expected = IllegalArgumentException.class)
    public void testRunNullPlayer() {
        UserInterface ui = TTFEFactory.createUserInterface(game);
    game.run(null, ui);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRunNullUI() {
        PlayerInterface player = TTFEFactory.createPlayer(false);
        game.run(player, null);
    }

}