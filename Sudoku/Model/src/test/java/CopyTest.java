import static org.junit.jupiter.api.Assertions.*;

import pl.cp.model.*;
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

    @Test
    public void testColumn_deepCopy() {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuColumn column = new SudokuColumn();
        SudokuField f1 = new SudokuField();
        f1.setFieldValue(9);
        SudokuField f2 = new SudokuField();
        f2.setFieldValue(8);
        SudokuField f3 = new SudokuField();
        f3.setFieldValue(7);

        column.addNextField(f1);
        column.addNextField(f2);
        column.addNextField(f3);

        SudokuColumn clonedColumn = new SudokuColumn();
        try {
            clonedColumn = (SudokuColumn) column.clone();
        } catch (CloneNotSupportedException ex) { }

        assertEquals(column, clonedColumn);

        column.set(2,1);
        assertNotEquals(column, clonedColumn);
    }

    @Test
    public void testBox_deepCopy() {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBox box = new SudokuBox();
        SudokuField f1 = new SudokuField();
        f1.setFieldValue(9);
        SudokuField f2 = new SudokuField();
        f2.setFieldValue(8);
        SudokuField f3 = new SudokuField();
        f3.setFieldValue(7);

        box.addNextField(f1);
        box.addNextField(f2);
        box.addNextField(f3);

        SudokuBox clonedBox = new SudokuBox();
        try {
            clonedBox = (SudokuBox) box.clone();
        } catch (CloneNotSupportedException ex) { }

        assertEquals(box, clonedBox);

        box.set(0,2,5);
        assertNotEquals(box, clonedBox);
    }

    @Test
    public void testBoard_deepCopy() {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.set(7,7,7);
        board.set(0,0,3);
        board.set(3,7,8);

        SudokuBoard clonedBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        try {
            clonedBoard = (SudokuBoard) board.clone();
        } catch (CloneNotSupportedException ex) { }

        assertEquals(board, clonedBoard);

        board.set(0,2,5);
        assertNotEquals(board, clonedBoard);

        clonedBoard.set(0,2,5);
        assertEquals(board, clonedBoard);
    }

    @Test
    public void testBoard_prototype() {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.set(7,7,7);
        board.set(0,0,3);
        board.set(3,7,8);

        SudokuBoard copiedBoard = new SudokuBoard(board);
        try {
            copiedBoard = (SudokuBoard) board.clone();
        } catch (CloneNotSupportedException ex) { }

        assertEquals(board, copiedBoard);

        board.set(0,2,5);
        assertNotEquals(board, copiedBoard);

        copiedBoard.set(0,2,5);
        assertEquals(board, copiedBoard);
    }
}
