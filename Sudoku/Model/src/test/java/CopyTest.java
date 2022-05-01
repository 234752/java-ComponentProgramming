import static org.junit.jupiter.api.Assertions.*;

import pl.cp.model.SudokuBoard;
import pl.cp.model.SudokuField;
import pl.cp.model.SudokuRow;
import org.junit.jupiter.api.Test;
import pl.cp.solver.BacktrackingSudokuSolver;

public class CopyTest {

    @Test
    public void testField_shallowCopy() {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuField field = new SudokuField();
        SudokuField clonedField = new SudokuField();
        field.setFieldValue(7);
        try {
            clonedField = (SudokuField) field.clone();
        } catch (CloneNotSupportedException ex) { }

        assertEquals(field, clonedField);
        assertNotSame(field, clonedField);

        clonedField.setObserver(board);
        assertEquals(field, clonedField);
    }

    @Test
    public void testRow_deepCopy() {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuRow row = new SudokuRow();
        SudokuField f1 = new SudokuField();
        f1.setFieldValue(1);
        SudokuField f2 = new SudokuField();
        f2.setFieldValue(2);
        SudokuField f3 = new SudokuField();
        f3.setFieldValue(3);

        row.addNextField(f1);
        row.addNextField(f2);
        row.addNextField(f3);

        SudokuRow clonedRow = new SudokuRow();
        try {
            clonedRow = (SudokuRow) row.clone();
        } catch (CloneNotSupportedException ex) { }

        assertEquals(row, clonedRow);

        row.set(1,7);
        assertNotEquals(row, clonedRow);
    }
}
