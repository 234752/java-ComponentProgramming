import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BacktrackingSudokuSolver implements SudokuSolver {

    @Override
    public boolean solve(SudokuBoard board) {
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
            board.set(row, col, values[num]);
            if (board.isValid(row, col) && solve(board)) {        //check if any of numbers 1-9 can fit current field
                return true;                                      //and if it leads to solution (recursively)
            } else board.set(row, col, 0);
        }
        return false;                                             //if none of the values make board solvable - backtrack
    }


}
