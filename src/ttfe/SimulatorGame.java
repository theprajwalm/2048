    package ttfe;

    import static org.junit.Assert.assertEquals;
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
            // test for illegal Board height
            if (height < 2 || width <2 || random == null) {
                throw new IllegalArgumentException("Invalid Inputs given");
            }

            //intializing the value
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
                boardRandomX = random.nextInt(boardHeight);
                boardRandomY = random.nextInt(boardWidth);
            } while (board[boardRandomX][boardRandomY] != 0);
            
            board[boardRandomX][boardRandomY] = value;
        }

        @Override
        public int getBoardHeight() {
            if ( boardHeight <= 0) {
                throw new IllegalArgumentException("Height is less than 0");
            }
            else{
            return boardHeight;
            }
        }

        @Override
        public int getBoardWidth() {
            if ( boardWidth <= 0 ) {
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
            if ((x < 0 || x >= boardHeight) || ( y < 0 || y >= boardWidth)){
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
            return isMovePossible(MoveDirection.EAST) || isMovePossible(MoveDirection.NORTH) || isMovePossible(MoveDirection.WEST) || isMovePossible(MoveDirection.SOUTH);
        }

        @Override
        public boolean isMovePossible(MoveDirection direction) {
            // TODO Auto-generated method stub
            if (direction == null) {
                throw new IllegalArgumentException("Invalid direction is given");
            }
            switch (direction) {
                case SOUTH:
                    // is there any space left in the south
                    for (int i = 0; i < boardHeight; i++) {
                        for (int j = 0; j < boardWidth; j++) {
                            if (board[i][j] == 0) {
                                return true;
                            }
                        }
                    }

                    break;
            
                default:
                    break;
            }   
            throw new UnsupportedOperationException("Unimplemented method 'isMovePossible'");
    
        }

        @Override
        public boolean isSpaceLeft() {
            // TODO Auto-generated method stub
            for (int i = 0; i < boardHeight; i++) {
                for (int j = 0; j < boardWidth; j++) {
                    if (board[i][j] == 0){
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public boolean performMove(MoveDirection direction) {
            // TODO Auto-generated method stub
            if (direction == null) {
                throw new IllegalArgumentException("Invalid Direction given");
            }
            return true;
        }

        @Override
        public void run(PlayerInterface player, UserInterface ui) {
            //Exceptional Case or Illegal  case
            if (player == null || ui == null) {
                throw new IllegalArgumentException("Player or UI is given null");
            }


        }

        @Override
        public void setPieceAt(int x, int y, int piece) {
            // TODO Auto-generated method stub
        if ((x < 0 || x >= boardHeight) || (y < 0 || y >= boardWidth)) 
        {
            throw new IllegalArgumentException("A piece exists in the given location");
        }
        if(piece == 2 || piece == 4 || piece == 0)
        {
            board[x][y] = piece; //setting the piece at given location
        }
        else{
            throw new IllegalArgumentException("Invalid piece given");
            }
        }
    }