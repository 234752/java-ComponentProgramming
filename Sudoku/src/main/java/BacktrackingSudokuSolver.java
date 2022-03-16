import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BacktrackingSudokuSolver implements SudokuSolver {

    private boolean backtrackingAlgorithm(SudokuBoard board) {
        int row = -1;
        int col = -1;
        boolean isEmpty = true;
        for (int rowIterator = 0; rowIterator < 9; rowIterator++) {
            for (int colIterator = 0; colIterator < 9; colIterator++) {
                if (board.get(rowIterator, colIterator) == 0) {   //looking for field where value is 0 (not filled yet)
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
            if (isValid(board, row, col, values[num])) {          //check if any of numbers 1-9 can fit current field
                board.set(row, col, values[num]);
                if (backtrackingAlgorithm(board)) {               //recursively check if this insertion leads to solution
                    return true;
                } else {
                    board.set(row, col, 0);
                }
            }
        }
        return false;                                             //if none of the values make board solvable - backtrack
    }

    public void solve(SudokuBoard board) {
        this.backtrackingAlgorithm(board);
    }
}
