import static org.junit.jupiter.api.Assertions.*;

import pl.cp.model.SudokuBoard;
import pl.cp.model.SudokuField;

import org.junit.jupiter.api.Test;
import pl.cp.solver.BacktrackingSudokuSolver;

public class SudokuFieldTest {

    @Test
    public void testCompareFields() {
        SudokuBoard testedBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuField testedField1 = new SudokuField(testedBoard);
        SudokuField testedField2 = new SudokuField(testedBoard);

        testedField1.setFieldValue(7);
        testedField2.setFieldValue(7);
        assertEquals(testedField1.compareTo(testedField2), 0);

        testedField1.setFieldValue(1);
        testedField2.setFieldValue(9);
        assertEquals(testedField1.compareTo(testedField2), -8);

        testedField1.setFieldValue(6);
        testedField2.setFieldValue(3);
        assertEquals(testedField1.compareTo(testedField2), 3);

    }
}
