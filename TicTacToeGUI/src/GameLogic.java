public class GameLogic {
    private String[][] board = new String[3][3];
    private String currentPlayer = "X";
    private int moveCount = 0;

    public GameLogic() {
        reset();
    }

    public void reset() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = " ";
            }
        }
        currentPlayer = "X";
        moveCount = 0;
    }

    public boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    public void makeMove(int row, int col) {
        board[row][col] = currentPlayer;
        moveCount++;
    }

    public boolean checkWin() {
        return isRowWin() || isColWin() || isDiagonalWin();
    }

    public boolean checkTie() {
        return moveCount >= 7 && !checkWin();
    }

    private boolean isRowWin() {
        for (int row = 0; row < 3; row++) {
            if (board[row][0].equals(currentPlayer) && board[row][1].equals(currentPlayer) && board[row][2].equals(currentPlayer)) {
                return true;
            }
        }
        return false;
    }

    private boolean isColWin() {
        for (int col = 0; col < 3; col++) {
            if (board[0][col].equals(currentPlayer) && board[1][col].equals(currentPlayer) && board[2][col].equals(currentPlayer)) {
                return true;
            }
        }
        return false;
    }

    private boolean isDiagonalWin() {
        return (board[0][0].equals(currentPlayer) && board[1][1].equals(currentPlayer) && board[2][2].equals(currentPlayer)) ||
                (board[0][2].equals(currentPlayer) && board[1][1].equals(currentPlayer) && board[2][0].equals(currentPlayer));
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }
}