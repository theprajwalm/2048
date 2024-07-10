package ttfe;

import java.util.Random;

public class SimulatorGame implements SimulatorInterface {
    // Board properties
    private final int boardHeight;
    private final int boardWidth;
    private final int[][] board;
    private final Random random;
    private int numMoves;
    private int points;

    // Constructor
    public SimulatorGame(int height, int width, Random random) {
        if (height < 2 || width < 2 || random == null) {
            throw new IllegalArgumentException("Invalid board dimensions or random generator");
        }

        this.boardHeight = height;
        this.boardWidth = width;
        this.board = new int[height][width];
        this.random = random;
        this.numMoves = 0;
        this.points = 0;

        // Add initial pieces to the board
        placeNewPiece();
        placeNewPiece();
    }

    // Adds a new piece (2 or 4) to a random empty position on the board
    @Override
    public void addPiece() {
        if (!isSpaceLeft()) {
            throw new IllegalStateException("No space left to add a new piece");
        }

        int x, y;
        do {
            x = random.nextInt(boardHeight);
            y = random.nextInt(boardWidth);
        } while (board[x][y] != 0);

        board[x][y] = random.nextDouble() < 0.9 ? 2 : 4;
    }

    @Override
    public int getBoardHeight() {
        return boardHeight;
    }

    @Override
    public int getBoardWidth() {
        return boardWidth;
    }

    @Override
    public int getNumMoves() {
        return numMoves;
    }

    // Counts the number of non-zero pieces on the board
    @Override
    public int getNumPieces() {
        int numPieces = 0;
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j < boardWidth; j++) {
                if (board[i][j] != 0) {
                    numPieces++;
                }
            }
        }
        return numPieces;
    }

    @Override
    public int getPieceAt(int x, int y) {
        if (x < 0 || x >= boardWidth || y < 0 || y >= boardHeight) {
            throw new IllegalArgumentException("Coordinates out of bounds");
        }
        return board[x][y];
    }

    @Override
    public int getPoints() {
        return points;
    }

    // Checks if any move is possible in any direction
    @Override
    public boolean isMovePossible() {
        for (MoveDirection direction : MoveDirection.values()) {
            if (isMovePossible(direction)) {
                return true;
            }
        }
        return false;
    }

    // Checks if a move is possible in the specified direction
    @Override
    public boolean isMovePossible(MoveDirection direction) {
        if (direction == null) {
            throw new IllegalArgumentException("Direction cannot be null");
        }

        switch (direction) {
            case NORTH:
                return canMoveUp();
            case SOUTH:
                return canMoveDown();
            case EAST:
                return canMoveRight();
            case WEST:
                return canMoveLeft();
        }
        return false;
    }

    // Checks if there is any empty space left on the board
    @Override
    public boolean isSpaceLeft() {
        for (int x = 0; x < boardWidth; x++) {
            for (int y = 0; y < boardHeight; y++){
                if (board[y][x] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    // Performs a move in the specified direction
    @Override
    public boolean performMove(MoveDirection direction) {
        if (direction == null) {
            throw new IllegalArgumentException("Direction cannot be null");
        }

        boolean moved = false;
        switch (direction) {
            case NORTH:
                moved = moveUp();
                break;
            case SOUTH:
                moved = moveDown();
                break;
            case EAST:
                moved = moveRight();
                break;
            case WEST:
                moved = moveLeft();
                break;
        }

        if (moved) {
            numMoves++;
            placeNewPiece();
        }

        return moved;
    }

    // Runs the game loop
    @Override
    public void run(PlayerInterface player, UserInterface ui) {
        if (player == null || ui == null) {
            throw new IllegalArgumentException("Player and UserInterface cannot be null");
        }

        while (isMovePossible()) {
            ui.updateScreen(this);
            MoveDirection direction = player.getPlayerMove(this, ui);
            if (direction != null && performMove(direction)) {
                ui.updateScreen(this);
            }
        }

        ui.showGameOverScreen(this);
    }

    @Override
    public void setPieceAt(int x, int y, int piece) {
        if (x < 0 || x >= boardWidth || y < 0 || y >= boardHeight || piece < 0) {
            throw new IllegalArgumentException("Invalid coordinates or piece value");
        }
        board[x][y] = piece;
    }

    // Helper method to check if a move up is possible
    private boolean canMoveUp() {
        for (int x = 0; x < boardWidth; x++) {
            for (int y = 1; y < boardHeight; y++) {
                if (board[x][y] != 0 && (board[x][y - 1] == 0 || board[x][y - 1] == board[x][y])) {
                    return true;
                }
            }
        }
        return false;
    }

    // Helper method to check if a move down is possible
    private boolean canMoveDown() {
        for (int x = 0; x < boardWidth; x++) {
            for (int y = boardHeight - 2; y >= 0; y--) {
                if (board[x][y] != 0 && (board[x][y + 1] == 0 || board[x][y + 1] == board[x][y])) {
                    return true;
                }
            }
        }
        return false;
    }

    // Helper method to check if a move right is possible
    private boolean canMoveRight() {
        for (int y = 0; y < boardHeight; y++) {
            for (int x = boardWidth - 2; x >= 0; x--) {
                if (board[x][y] != 0 && (board[x + 1][y] == 0 || board[x + 1][y] == board[x][y])) {
                    return true;
                }
            }
        }
        return false;
    }

    // Helper method to check if a move left is possible
    private boolean canMoveLeft() {
        for (int y = 0; y < boardHeight; y++) {
            for (int x = 1; x < boardWidth; x++) {
                if (board[x][y] != 0 && (board[x - 1][y] == 0 || board[x - 1][y] == board[x][y])) {
                    return true;
                }
            }
        }
        return false;
    }

    // Performs the move up action
    private boolean moveUp() {
        boolean moved = false;

        for (int x = 0; x < boardWidth; x++) {
            int[] newCol = new int[boardHeight];
            int index = 0;
            for (int y = 0; y < boardHeight; y++) {
                if (board[x][y] != 0) {
                    newCol[index++] = board[x][y];
                }
            }
            for (int y = 0; y < boardHeight; y++) {
                if (board[x][y] != newCol[y]) {
                    moved = true;
                }
                board[x][y] = newCol[y];
            }
        }

        for (int x = 0; x < boardWidth; x++) {
            for (int y = 0; y < boardHeight - 1; y++) {
                if (board[x][y] != 0 && board[x][y] == board[x][y + 1]) {
                    board[x][y] *= 2;
                    points += board[x][y];
                    board[x][y + 1] = 0;
                    moved = true;
                }
            }
        }

        for (int x = 0; x < boardWidth; x++) {
            int[] newCol = new int[boardHeight];
            int index = 0;
            for (int y = 0; y < boardHeight; y++) {
                if (board[x][y] != 0) {
                    newCol[index++] = board[x][y];
                }
            }
            for (int y = 0; y < boardHeight; y++) {
                board[x][y] = newCol[y];
            }
        }

        return moved;
    }

    // Performs the move down action
    private boolean moveDown() {
        boolean moved = false;

        for (int x = 0; x < boardWidth; x++) {
            int[] newCol = new int[boardHeight];
            int index = boardHeight - 1;
            for (int y = boardHeight - 1; y >= 0; y--) {
                if (board[x][y] != 0) {
                    newCol[index--] = board[x][y];
                }
            }
            for (int y = 0; y < boardHeight; y++) {
                if (board[x][y] != newCol[y]) {
                    moved = true;
                }
                board[x][y] = newCol[y];
            }
        }

        for (int x = 0; x < boardWidth; x++) {
            for (int y = boardHeight - 1; y > 0; y--) {
                if (board[x][y] != 0 && board[x][y] == board[x][y - 1]) {
                    board[x][y] *= 2;
                    points += board[x][y];
                    board[x][y - 1] = 0;
                    moved = true;
                }
            }
        }

        for (int x = 0; x < boardWidth; x++) {
            int[] newCol = new int[boardHeight];
            int index = boardHeight - 1;
            for (int y = boardHeight - 1; y >= 0; y--) {
                if (board[x][y] != 0) {
                    newCol[index--] = board[x][y];
                }
            }
            for (int y = 0; y < boardHeight; y++) {
                board[x][y] = newCol[y];
            }
        }

        return moved;
    }

    // Performs the move right action
    private boolean moveRight() {
        boolean moved = false;

        for (int y = 0; y < boardHeight; y++) {
            int[] newRow = new int[boardWidth];
            int index = boardWidth - 1;
            for (int x = boardWidth - 1; x >= 0; x--) {
                if (board[x][y] != 0) {
                    newRow[index--] = board[x][y];
                }
            }
            for (int x = 0; x < boardWidth; x++) {
                if (board[x][y] != newRow[x]) {
                    moved = true;
                }
                board[x][y] = newRow[x];
            }
        }

        for (int y = 0; y < boardHeight; y++) {
            for (int x = boardWidth - 1; x > 0; x--) {
                if (board[x][y] != 0 && board[x][y] == board[x - 1][y]) {
                    board[x][y] *= 2;
                    points += board[x][y];
                    board[x - 1][y] = 0;
                    moved = true;
                }
            }
        }

        for (int y = 0; y < boardHeight; y++) {
            int[] newRow = new int[boardWidth];
            int index = boardWidth - 1;
            for (int x = boardWidth - 1; x >= 0; x--) {
                if (board[x][y] != 0) {
                    newRow[index--] = board[x][y];
                }
            }
            for (int x = 0; x < boardWidth; x++) {
                board[x][y] = newRow[x];
            }
        }

        return moved;
    }

    // Performs the move left action
    private boolean moveLeft() {
        boolean moved = false;

        for (int y = 0; y < boardHeight; y++) {
            int[] newRow = new int[boardWidth];
            int index = 0;
            for (int x = 0; x < boardWidth; x++) {
                if (board[x][y] != 0) {
                    newRow[index++] = board[x][y];
                }
            }
            for (int x = 0; x < boardWidth; x++) {
                if (board[x][y] != newRow[x]) {
                    moved = true;
                }
                board[x][y] = newRow[x];
            }
        }

        for (int y = 0; y < boardHeight; y++) {
            for (int x = 0; x < boardWidth - 1; x++) {
                if (board[x][y] != 0 && board[x][y] == board[x + 1][y]) {
                    board[x][y] *= 2;
                    points += board[x][y];
                    board[x + 1][y] = 0;
                    moved = true;
                }
            }
        }

        for (int y = 0; y < boardHeight; y++) {
            int[] newRow = new int[boardWidth];
            int index = 0;
            for (int x = 0; x < boardWidth; x++) {
                if (board[x][y] != 0) {
                    newRow[index++] = board[x][y];
                }
            }
            for (int x = 0; x < boardWidth; x++) {
                board[x][y] = newRow[x];
            }
        }

        return moved;
    }

    // Places a new piece on the board in a random empty spot
    private void placeNewPiece() {
        if (!isSpaceLeft()) {
            return;
        }

        int value = random.nextDouble() < 0.9 ? 2 : 4;
        int x, y;
        do {
            x = random.nextInt(boardHeight);
            y = random.nextInt(boardWidth);
        } while (board[x][y] != 0);

        board[x][y] = value;
    }
}
