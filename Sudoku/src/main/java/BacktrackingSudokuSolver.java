import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BacktrackingSudokuSolver implements SudokuSolver {

    private boolean isValid(SudokuBoard board, int row, int col, int n) {
        for (int i = 0; i < 9; i++) {
            if (board.get(row,i) == n) {                             //check for repetition in row
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (board.get(i, col) == n) {                             //check for repetition in column
                return false;
            }
        }

        int boxRowStart = row - row % 3;
        int boxColStart = col - col % 3;

        for (int rowIterator = boxRowStart; rowIterator < boxRowStart + 3; rowIterator++) {
            for (int colIterator = boxColStart; colIterator < boxColStart + 3; colIterator++) {
                if (board.get(rowIterator, colIterator) == n) {       //check for repetition in 3x3 box/square
                    return false;
                }
            }
        }
        return true;
    }

    private boolean solveBoard(SudokuBoard board) {
        int row = -1;
        int col = -1;
        boolean isEmpty = true;
        for (int rowIterator = 0; rowIterator < 9; rowIterator++) {
            for (int colIterator = 0; colIterator < 9; colIterator++) {
                if (board.get(rowIterator, colIterator) == 0) {       //looking for field where value is 0 (not filled yet)
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

        Integer[] values = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        List<Integer> valList = Arrays.asList(values);
        Collections.shuffle(valList);
        valList.toArray(values);

        for (int num = 0; num < 9; num++) {
            if (isValid(board, row, col, values[num])) {                         //check if any of numbers 1-9 can fit current field
                board.set(row, col, values[num]);
                if (solveBoard(board)) {                               //recursively check if this insertion leads to solution
                    return true;
                } else {
                    board.set(row, col, 0);
                }
            }
        }
        return false;                                             //if none of the values make board solvable - backtrack
    }

    public void solve(SudokuBoard board) {
        this.solveBoard(board);
    }
}
