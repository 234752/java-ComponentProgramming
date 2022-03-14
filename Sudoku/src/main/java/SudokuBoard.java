import java.util.Random;

public class SudokuBoard {

    Random random = new Random();
    private int[][] board = new int[9][9];

    public int getNumber(int row, int col) {
        return board[row][col];
    }

    private boolean isValid(int row, int col, int n) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == n) {                             //check for repetition in row
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (board[i][col] == n) {                             //check for repetition in column
                return false;
            }
        }

        int boxRowStart = row - row % 3;
        int boxColStart = col - col % 3;

        for (int rowIterator = boxRowStart; rowIterator < boxRowStart + 3; rowIterator++) {
            for (int colIterator = boxColStart; colIterator < boxColStart + 3; colIterator++) {
                if (board[rowIterator][colIterator] == n) {       //check for repetition in 3x3 box/square
                    return false;
                }
            }
        }
        return true;
    }

    private boolean solveBoard() {
        int row = -1;
        int col = -1;
        boolean isEmpty = true;
        for (int rowIterator = 0; rowIterator < 9; rowIterator++) {
            for (int colIterator = 0; colIterator < 9; colIterator++) {
                if (board[rowIterator][colIterator] == 0) {       //looking for field where value is 0 (not filled yet)
                    row = rowIterator;
                    col = colIterator;
                    isEmpty = false;                              //double break in case of finding it
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }
        if (isEmpty) {
            return true;                                          //board is filled, return from recursion
        }

        for (int num = 1; num <= 9; num++) {
            if (isValid(row, col, num)) {                         //check if any of numbers 1-9 can fit current field
                board[row][col] = num;
                if (solveBoard()) {                               //recursively check if this insertion leads to solution
                    return true;
                } else {
                    board[row][col] = 0;
                }
            }
        }
        return false;                                             //if none of the values make board solvable - backtrack
    }

    public void print() {                                         //output method
        for (int rowIterator = 0; rowIterator < 9; rowIterator++) {
            for (int colIterator = 0; colIterator < 9; colIterator++) {
                System.out.print(board[rowIterator][colIterator]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    public void fillBoard() {
        for (int row = 0; row < 9; row++) {                       //fill with 0s
            for (int col = 0; col < 9; col++) {
                board[row][col] = 0;
            }
        }

        for (int i = 1; i <= 9; i++) {                            //fill 9 random fields with digits 1-9
            board[random.nextInt(9)][random.nextInt(9)] = i;
        }
        this.solveBoard();                                       //fill remaining fields according to sudoku rules
    }

}
