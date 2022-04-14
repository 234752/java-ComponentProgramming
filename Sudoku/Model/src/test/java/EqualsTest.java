import static org.junit.jupiter.api.Assertions.*;

import model.SudokuBoard;
import model.SudokuField;
import model.SudokuRow;
import org.junit.jupiter.api.Test;
import solver.BacktrackingSudokuSolver;

public class EqualsTest {

    public EqualsTest() {
    }
    SudokuBoard testedBoard = new SudokuBoard(new BacktrackingSudokuSolver());

    @Test
    public void testEqualFields() {
        SudokuField testedField1 = new SudokuField(testedBoard);
        testedField1.setFieldValue(7);
        SudokuField testedField2 = new SudokuField(testedBoard);
        testedField1.setFieldValue(7);
        assertFalse(testedField1.equals(testedField2));
        SudokuField testedField3 = testedField2;
        assertTrue(testedField3.equals(testedField2));

        //need code coverage in tests
        SudokuRow r1 = new SudokuRow();
        assertFalse(testedField1.equals(r1));
        assertFalse(testedField1.equals(null));
    }

    @Test
    public void testEqualRow() {
        SudokuRow r1 = new SudokuRow();
        SudokuRow r2 = r1;
        assertTrue(r1.equals(r2));
        assertTrue(testedBoard.getRow(1).equals(testedBoard.getRow(1)));
        assertEquals(testedBoard.getRow(1).hashCode(), testedBoard.getRow(2).hashCode());
        testedBoard.set(0,0,7);
        assertFalse(testedBoard.getRow(1).equals(testedBoard.getRow(0)));
        assertNotEquals(testedBoard.getRow(1).hashCode(), testedBoard.getRow(0).hashCode());
        assertFalse(testedBoard.getRow(1).equals(testedBoard.getColumn(2)));

        assertFalse(r1.equals(new SudokuField(testedBoard)));
        assertFalse(r1.equals(null));
    }

    @Test
    public void checkConsistency()
    {
        SudokuField testedField1 = new SudokuField(testedBoard);
        testedField1.setFieldValue(7);
        SudokuField testedField2 = testedField1;
        assertEquals(testedField1.hashCode(), testedField2.hashCode());
    }

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
        assertEquals(testedBoard.getBox(5,5).hashCode(), testedBoard.getBox(3,3).hashCode());

        assertFalse(testedBoard.getBox(0,0).equals(testedBoard.getBox(3,0)));
        assertFalse(testedBoard.getBox(5,5).equals(testedBoard.getBox(3,2)));
        assertNotEquals(testedBoard.getBox(5,5).hashCode(), testedBoard.getBox(3,2).hashCode());
    }

    @Test
    public void testEqualBoard() {
        SudokuBoard b1 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard b2 = new SudokuBoard(new BacktrackingSudokuSolver());
        b1.set(5,7,9);
        assertTrue(b1.equals(b1));
        assertFalse(b2.equals(b1));
        assertNotEquals(b1.hashCode(), b2.hashCode());

        assertFalse(b1.equals(null));
        assertFalse(b1.equals(new SudokuRow()));
    }

    @Test
    public void testToString() {
        SudokuBoard b1 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuRow r1 = new SudokuRow();
        SudokuField f1 = new SudokuField(b1);
        b1.toString();
        r1.toString();
        f1.toString();
    }
}
