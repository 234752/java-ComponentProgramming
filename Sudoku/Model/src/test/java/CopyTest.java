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
        SudokuBoard board2 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuField field = new SudokuField(board);
        SudokuField clonedField = new SudokuField(null);
        field.setFieldValue(7);
        try {
            clonedField = (SudokuField) field.clone();
        } catch (CloneNotSupportedException ex) { }

        assertEquals(field, clonedField);
        assertNotSame(field, clonedField);

        clonedField.setObserver(board2);
        assertEquals(field, clonedField);
    }

    @Test
    public void testRow_deepCopy() {


        SudokuRow row = new SudokuRow();
        row.set(0, 5);
        row.set(2, 7);
        SudokuRow clonedRow = new SudokuRow();
        try {
            clonedRow = (SudokuRow) row.clone();
        } catch (CloneNotSupportedException ex) { }
        assertEquals(row, clonedRow);

        clonedRow.set(1, 3);
        assertNotEquals(row, clonedRow);
    }
}
