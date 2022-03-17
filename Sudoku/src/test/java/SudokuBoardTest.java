
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SudokuBoardTest {

    public SudokuBoardTest() {
    }
    SudokuBoard testedBoard = new SudokuBoard(new BacktrackingSudokuSolver());

    @Test
    public void testInvalidRow() {
        assertTrue(testedBoard.isValid(0,0,1));
        testedBoard.set(0,8,1);
        assertFalse(testedBoard.isValid(0,0,1));
    }

    @Test
    public void testInvalidColumn() {
        assertTrue(testedBoard.isValid(0,0,1));
        testedBoard.set(8,0,1);
        assertFalse(testedBoard.isValid(0,0,1));
    }

    @Test
    public void testInvalidBox() {
        assertTrue(testedBoard.isValid(0,0,1));
        testedBoard.set(2,2,1);
        assertFalse(testedBoard.isValid(0,0,1));
    }
}
