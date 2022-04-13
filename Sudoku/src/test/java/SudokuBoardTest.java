import model.SudokuBoard;
import org.junit.jupiter.api.Test;
import solver.BacktrackingSudokuSolver;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardTest {

    public SudokuBoardTest() {
    }
    SudokuBoard testedBoard = new SudokuBoard(new BacktrackingSudokuSolver());

    @Test
    public void testInvalidRow() {
        assertTrue(testedBoard.getRow(0).verify());
        testedBoard.set(0,8,1);
        testedBoard.set(0,7,1);
        assertFalse(testedBoard.getRow(0).verify());
    }

    @Test
    public void testInvalidColumn() {
        assertTrue(testedBoard.getColumn(0).verify());
        testedBoard.set(8,0,1);
        testedBoard.set(7,0,1);
        assertFalse(testedBoard.getColumn(0).verify());
    }

    @Test
    public void testInvalidBox() {
        assertTrue(testedBoard.getBox(0,0).verify());
        testedBoard.set(2,2,1);
        testedBoard.set(1,1,1);
        assertFalse(testedBoard.getBox(0,0).verify());
    }

    @Test
    public void solveTemplate() {
        testedBoard.set(0,8,5);
        testedBoard.set(1,1,6);
        testedBoard.set(1,4,7);
        testedBoard.set(2,2,2);
        testedBoard.set(2,5,8);
        testedBoard.set(2,6,4);
        testedBoard.set(2,7,9);
        testedBoard.set(3,7,1);
        testedBoard.set(4,2,9);
        testedBoard.set(4,5,4);
        testedBoard.set(4,6,3);
        testedBoard.set(4,7,8);
        testedBoard.set(5,0,2);
        testedBoard.set(5,3,9);
        testedBoard.set(6,2,3);
        testedBoard.set(6,8,1);
        testedBoard.set(7,0,7);
        testedBoard.set(7,4,8);
        testedBoard.set(7,6,5);
        testedBoard.set(7,7,3);
        testedBoard.set(8,5,5);
        testedBoard.set(8,8,2);
        testedBoard.solveGame();
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                assertEquals(testedBoard.getRow(x).verify(),true);
                assertEquals(testedBoard.getColumn(y).verify(),true);
                assertEquals(testedBoard.getBox(x,y).verify(),true);
                //System.out.print(testedBoard.get(x,y) + " ");
            } //System.out.println("");
        }
    }
}
