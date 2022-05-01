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
}
