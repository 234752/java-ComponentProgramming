
public class SudokuBoard {

    private SudokuField[][] internalBoard = new SudokuField[9][9];
    private SudokuSolver sudokuSolver;
    private SudokuRow[] rows = new SudokuRow[9];
    private SudokuColumn[] columns = new SudokuColumn[9];
    private SudokuBox[][] boxes = new SudokuBox[3][3];

    public SudokuBoard(SudokuSolver solver) {

        for (int i=0; i<9; i++) {
            rows[i] = new SudokuRow();
            columns[i] = new SudokuColumn();
        }
        for (int x=0; x<3; x++) {
            for (int y=0; y<3; y++) {
                boxes[x][y] = new SudokuBox();
            }
        }

        sudokuSolver = solver;
        for (int row=0; row<9; row++) {
            for (int col=0; col<9; col++) {
                internalBoard[row][col] = new SudokuField();

                rows[row].set(col, internalBoard[row][col]); //assigment to rows
                columns[col].set(row, internalBoard[row][col]); //assigment to columns
                boxes[(row - row%3)/3][(col - col%3)/3].set(row%3, col%3, internalBoard[row][col]);
            }
        }
    }

    public int get(int x, int y) {
        return internalBoard[x][y].getFieldValue();
    }

    public void set(int x, int y, int value) {
        internalBoard[x][y].setFieldValue(value);
    }

    public SudokuRow getRow(int y) {
        return rows[y];
    }

    public SudokuColumn getColumn(int x) {
        return columns[x];
    }

    public SudokuBox getBox(int x, int y) {
        return boxes[(x - x%3)/3][(y - y%3)/3];
    }

    public boolean isValid(int row, int col) {
        return (getRow(row).verify() && getColumn(col).verify() && getBox(row, col).verify());
    } //combine 3 isValid methods

    public void solveGame() {
        sudokuSolver.solve(this);
    }
}
