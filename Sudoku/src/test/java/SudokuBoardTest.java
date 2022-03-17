
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SudokuBoardTest {

    public SudokuBoardTest() {
    }

    private boolean checkNumber(SudokuBoard SB, int row, int col, int n) {
        for (int i = 0; i < 9; i++) {
            if (SB.get(row, i) == n && i != col) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (SB.get(i, col) == n && i != row) {
                return false;
            }
        }

        int boxRowStart = row - row % 3;
        int boxColStart = col - col % 3;

        for (int rowIterator = boxRowStart; rowIterator < boxRowStart + 3; rowIterator++) {
            for (int colIterator = boxColStart; colIterator < boxColStart + 3; colIterator++) {
                if (SB.get(rowIterator, colIterator) == n && (rowIterator != row || colIterator != col)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    public void testBoard() {
        SudokuBoard testedBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        testedBoard.solveGame();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (!checkNumber(testedBoard, row, col, testedBoard.get(row, col))) fail("one of the numbers is not valid");
            }
        }

        testedBoard.set(0,0,1);
        testedBoard.set(0,1,1);
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (!checkNumber(testedBoard, row, col, testedBoard.get(row, col))) return;
            }
        }
        fail("invalid board passed checking process");
    }

    @Test
    public void testIfBoardsAreDifferent() {
        SudokuBoard testedBoard1 = new SudokuBoard(new BacktrackingSudokuSolver());
        testedBoard1.solveGame();                    //fill 1st
        SudokuBoard testedBoard2 = new SudokuBoard(new BacktrackingSudokuSolver());
        testedBoard2.solveGame();                   //fill 2nd
        //compare
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (testedBoard1.get(row, col) != testedBoard2.get(row, col)) return;
            }
        }
        fail("all numbers are on the same positions in both boards");
    }

}
