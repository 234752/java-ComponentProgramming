public class SudokuBoard extends Observer {

    private SudokuField[][] fields = new SudokuField[9][9];
    private SudokuSolver sudokuSolver;

    public SudokuBoard(SudokuSolver solver) {
        sudokuSolver = solver;
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                fields[row][col] = new SudokuField(this);
            }
        }
    }

    @Override
    public void update() {
        if (observerIsTurnedOn) {
            if (checkBoard()) {
                System.out.println("valid value of field inserted");
            } else {
                System.out.println("invalid value of field inserted");
            }
        }
    }

    public int get(int x, int y) {
        return fields[x][y].getFieldValue();
    }

    public void set(int x, int y, int value) {
        fields[x][y].setFieldValue(value);
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
            row.addNextField(fields[x][y]);
        }
        return row;
    }

    public SudokuColumn getColumn(int y) {
        SudokuColumn col = new SudokuColumn();
        for (int x = 0; x < 9; x++) {
            col.addNextField(fields[x][y]);
        }
        return col;
    }

    public SudokuBox getBox(int x, int y) {
        SudokuBox box = new SudokuBox();
        int rowStart = x - x % 3;
        int colStart = y - y % 3;
        for (int row = rowStart; row < rowStart + 3; row++) {
            for (int col = colStart; col < colStart + 3; col++) {
                box.addNextField(fields[row][col]);
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

    @Override
    public String toString() {
        return "SudokuBoard{" +
                "observerIsTurnedOn=" + observerIsTurnedOn +
                '}';
    }
}
