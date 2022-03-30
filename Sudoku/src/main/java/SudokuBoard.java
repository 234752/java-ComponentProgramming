import java.util.ArrayList;
import java.util.List;

public class SudokuBoard {

    private List<List<SudokuField>> fields = new ArrayList<>();
    private SudokuSolver sudokuSolver;
    private boolean observerTurnedOn = false;

    public SudokuBoard(SudokuSolver solver) {
        sudokuSolver = solver;
        for (int row = 0; row < 9; row++) {
            fields.add(new ArrayList<SudokuField>());
            for (int col = 0; col < 9; col++) {
                fields.get(row).add(new SudokuField(this));
            }
        }
    }

    public void switchObserver(boolean observerTurnedOn) {
        this.observerTurnedOn = observerTurnedOn;
    }

    public void notifyBoard() {
        if (observerTurnedOn) {
            if (checkBoard()) {
                System.out.println("valid value of field inserted");
            } else {
                System.out.println("invalid value of field inserted");
            }
        }
    }

    public int get(int x, int y) {
        return fields.get(x).get(y).getFieldValue();
    }

    public void set(int x, int y, int value) {
        fields.get(x).get(y).setFieldValue(value);
    }

    private boolean checkBoard() {

        for (int col = 0; col < 9; col++) {
            if (!getColumn(col).verify()) {
                return false;
            }
        }

        for (int row = 0; row < 9; row++) {
            if (!getRow(row).verify()) {
                return false;
            }
        }

        for (int row = 0; row < 9; row += 3) {
            for (int col = 0; col < 9; col += 3) {
                if (!getBox(row,col).verify()) {
                    return false;
                }
            }
        }
        return true;
    } //used for checking whole board

    public SudokuRow getRow(int x) {
        SudokuRow row = new SudokuRow();
        for (int y = 0; y < 9; y++) {
            row.addNextField(fields.get(x).get(y));
        }
        return row;
    }

    public SudokuColumn getColumn(int y) {
        SudokuColumn col = new SudokuColumn();
        for (int x = 0; x < 9; x++) {
            col.addNextField(fields.get(x).get(y));
        }
        return col;
    }

    public SudokuBox getBox(int x, int y) {
        SudokuBox box = new SudokuBox();
        int rowStart = x - x % 3;
        int colStart = y - y % 3;
        for (int row = rowStart; row < rowStart + 3; row++) {
            for (int col = colStart; col < colStart + 3; col++) {
                box.addNextField(fields.get(row).get(col));
            }
        }
        return box;
    }

    public boolean isValid(int row, int col) {
        return getRow(row).verify() && getColumn(col).verify() && getBox(row, col).verify();
    } //combine 3 isValid methods

    public void solveGame() {
        sudokuSolver.solve(this);
    }
}
