package pl.first.firstjava;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SudokuBoardTest {

    public SudokuBoardTest() {
    }

    private boolean checkNumber(SudokuBoard SB, int row, int col, int n) {
        for (int i = 0; i < 9; i++) {
            if (SB.getNumber(row, i) == n && i != col) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (SB.getNumber(i, col) == n && i != row) {
                return false;
            }
        }

        int boxRowStart = row - row % 3;
        int boxColStart = col - col % 3;

        for (int rowIterator = boxRowStart; rowIterator < boxRowStart + 3; rowIterator++) {
            for (int colIterator = boxColStart; colIterator < boxColStart + 3; colIterator++) {
                if (SB.getNumber(rowIterator, colIterator) == n && (rowIterator != row || colIterator != col)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    public void testBoard() {
        SudokuBoard testedBoard = new SudokuBoard();
        testedBoard.fillBoard();

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (!checkNumber(testedBoard, row, col, testedBoard.getNumber(row, col))) fail("one of the numbers is not valid");
            }
        }
    }

    @Test
    public void testMultipleBoards() {
        SudokuBoard testedBoard1 = new SudokuBoard();
        testedBoard1.fillBoard();                    //fill 1st

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (!checkNumber(testedBoard1, row, col, testedBoard1.getNumber(row, col))) fail("one of the numbers is not valid in 1st board");
            }
        }
        SudokuBoard testedBoard2 = new SudokuBoard();
        testedBoard2.fillBoard();                   //fill 2nd

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (!checkNumber(testedBoard2, row, col, testedBoard2.getNumber(row, col))) fail("one of the numbers is not valid in 2nd board");
            }
        }

        //compare
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (testedBoard1.getNumber(row, col) != testedBoard2.getNumber(row, col)) return;
            }
        }
        fail("all numbers are on the same positions in both boards");
    }

}
