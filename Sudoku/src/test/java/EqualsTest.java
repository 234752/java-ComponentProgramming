import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class EqualsTest {

    public EqualsTest() {
    }
    SudokuBoard testedBoard = new SudokuBoard(new BacktrackingSudokuSolver());

    @Test
    public void testEqualBox() {
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

        assertTrue(testedBoard.getBox(0,0).equals(testedBoard.getBox(2,1)));
        assertTrue(testedBoard.getBox(5,5).equals(testedBoard.getBox(3,3)));

        assertFalse(testedBoard.getBox(0,0).equals(testedBoard.getBox(3,0)));
        assertFalse(testedBoard.getBox(5,5).equals(testedBoard.getBox(3,2)));


    }
}
