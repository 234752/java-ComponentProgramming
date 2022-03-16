
public class SudokuBoard {

    private int[][] internalBoard = new int[9][9];
    private SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();

    public int get(int x, int y) {
        return internalBoard[x][y];
    }

    public void set(int x, int y, int value) {
        internalBoard[x][y] = value;
    }

    public void solveGame() {
        sudokuSolver.solve(this);
    }
}
