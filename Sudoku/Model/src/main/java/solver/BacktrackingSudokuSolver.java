package solver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.SudokuBoard;
import org.apache.commons.lang3.builder.EqualsBuilder;

public class BacktrackingSudokuSolver implements SudokuSolver, Serializable {

    private List<Integer> digits = new ArrayList<>();

    public BacktrackingSudokuSolver() {
        for (int i = 1; i <= 9; i++) {
            digits.add(i);
        }
    }

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

        List<Integer> values = new ArrayList<>(digits);
        Collections.shuffle(values);

        for (int num = 0; num < 9; num++) {
            board.set(row, col, values.get(num));
            if (board.isValid(row, col) && solve(board)) {        //check if any of numbers 1-9 can fit current field
                return true;                                      //and if it leads to solution (recursively)
            } else {
                board.set(row, col, 0);
            }
        }
        return false;                                             //if none of the values make board solvable - backtrack
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BacktrackingSudokuSolver that = (BacktrackingSudokuSolver) o;

        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
