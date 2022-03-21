
public class SudokuBoard {

    private SudokuField[][] internalBoard = new SudokuField[9][9];
    private SudokuSolver sudokuSolver;

    public SudokuBoard(SudokuSolver solver) {
        sudokuSolver = solver;
        for (int x=0; x<9; x++) {
            for (int y=0; y<9; y++) {
                internalBoard[x][y] = new SudokuField();
            }
        }
    }

    public int get(int x, int y) {
        return internalBoard[x][y].getFieldValue();
    }

    public void set(int x, int y, int value) {
        internalBoard[x][y].setFieldValue(value);
    }

    private boolean isValidRow(int row, int n) {
        for (int i = 0; i < 9; i++) {
            if (this.get(row,i) == n) {
                return false;
            }
        }
        return true;
    } //check for repetition in row

    private boolean isValidCol(int col, int n) {
        for (int i = 0; i < 9; i++) {
            if (this.get(i, col) == n) {
                return false;
            }
        }
        return true;
    } //check for repetition in column

    private boolean isValidBox(int row, int col, int n) {
        int boxRowStart = row - row % 3;
        int boxColStart = col - col % 3;

        for (int rowIterator = boxRowStart; rowIterator < boxRowStart + 3; rowIterator++) {
            for (int colIterator = boxColStart; colIterator < boxColStart + 3; colIterator++) {
                if (this.get(rowIterator, colIterator) == n) {
                    return false;
                }
            }
        }
        return true;
    } //check for repetition in 3x3 box/square

    public boolean isValid(int row, int col, int n) {
        return (isValidRow(row, n) && isValidCol(col, n) && isValidBox(row, col, n));
    } //combine 3 isValid methods

    public void solveGame() {
        sudokuSolver.solve(this);
    }
}
