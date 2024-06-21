package ttfe;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Random;

public class SimulatorGame implements SimulatorInterface{
    //Board Eigenschaften
    private int boardHeight;
    private int boardWidth;
    private int numMoves;
    private int points;
    private Random random;
    private int[][] board;

    //this is constructor
    private SimulatorGame(int height, int width, int seed){
        this.boardHeight = height;
        this.boardWidth = width;
        this.numMoves = 0;
        this.points = 0;
        this.board = new int[height][width];
        this.random = new Random(seed);
    }


    @Override
    public void addPiece() {
        if(!isSpaceLeft()){
            assertTrue("There is no Space in the board", isSpaceLeft());
        }
        double rand = random.nextDouble();
        int value = (rand < 0.9) ? 2 : 4 ; // the random value generator

        int boardRandomX, boardRandomY;

        do {
            boardRandomX = random.nextInt(this.boardHeight);
            boardRandomY = random.nextInt(this.boardWidth);
        } while (getPieceAt(boardRandomX, boardRandomY) != 0);
        
        setPieceAt(boardRandomX, boardRandomY, value);

    }

    @Override
    public int getBoardHeight() {
        if (boardHeight < 2) {
            throw new IllegalArgumentException("Height is less than 2");
        }
        return boardHeight;
    }

    @Override
    public int getBoardWidth() {
        return boardWidth;
    }

    @Override
    public int getNumMoves() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNumMoves'");
    }

    @Override
    public int getNumPieces() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNumPieces'");
    }

    @Override
    public int getPieceAt(int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPieceAt'");
    }

    @Override
    public int getPoints() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPoints'");
    }

    @Override
    public boolean isMovePossible() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isMovePossible'");
    }

    @Override
    public boolean isMovePossible(MoveDirection direction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isMovePossible'");
    }

    @Override
    public boolean isSpaceLeft() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isSpaceLeft'");
    }

    @Override
    public boolean performMove(MoveDirection direction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'performMove'");
    }

    @Override
    public void run(PlayerInterface player, UserInterface ui) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

    @Override
    public void setPieceAt(int x, int y, int piece) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPieceAt'");
    }

}