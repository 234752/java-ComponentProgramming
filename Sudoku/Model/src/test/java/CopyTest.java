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
        SudokuField field = new SudokuField(board);
        SudokuField clonedField = field.clone();

        assertEquals(field, clonedField);
        assertNotSame(field, clonedField);
    }
}
