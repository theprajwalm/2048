package ttfe;

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
    private int numPieces;

    //this is constructor
    public SimulatorGame(int height, int width, Random random){
        this.boardHeight = height;
        this.boardWidth = width;
        this.numMoves = 0;
        this.points = 0;
        this.board = new int[height][width];
        this.random = random;
        this.numPieces = 2; //intial number of pieces
        this.numMoves = 0; //inital number of moves
    }


    @Override
    public void addPiece() {
        if(!isSpaceLeft()){
            assertTrue("There is no Space in the board", isSpaceLeft());
        }
        int value = (random.nextDouble() < 0.9) ? 2 : 4 ; // the random value generator

        int boardRandomX, boardRandomY;

        do {
            boardRandomX = random.nextInt(this.boardHeight);
            boardRandomY = random.nextInt(this.boardWidth);
        } while (board[boardRandomX][boardRandomY] != 0);
        
        board[boardRandomX][boardRandomY] = value;


    }

    @Override
    public int getBoardHeight() {
        if ( boardHeight < 0) {
            throw new IllegalArgumentException("Height is less than 0");
        }
        else{
        return boardHeight;
        }
    }

    @Override
    public int getBoardWidth() {
        if ( boardWidth < 0) {
            throw new IllegalArgumentException("Height is less than 0");
        }
        else{
        return boardWidth;
        }
    }

    @Override
    public int getNumMoves() {
       return numMoves;
    }

    @Override
    public int getNumPieces() {
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardWidth; j++) {
                if(board[i][j] != 0){
                    numPieces++;
                }
            }
        }
        return numPieces;
        }

    @Override
    public int getPieceAt(int x, int y) {
        if ((x < 0 || x > boardHeight) || ( y < 0 || y > boardWidth)){
            throw new IllegalArgumentException("A piece exists in the given location");
        }

        return board[x][y];
    }

    @Override
    public int getPoints() {
        return points;
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
        if (player == null || ui == null) {
        }
    }

    @Override
    public void setPieceAt(int x, int y, int piece) {
        // TODO Auto-generated method stub
       if ((x < 0 || x >= boardHeight) || (y < 0 || y >= boardWidth)) {
        throw new IllegalArgumentException("A piece exists in the given location");
       }
       else{
        board[x][y] = piece; //setting the piece at given location
       }
    }

}