
public class SudokuBoard {

    private int[][] internalBoard = new int[9][9];
    private SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();

    public int get(int row, int col) {
        return internalBoard[row][col];
    }

    public void set(int row, int col, int value){
        internalBoard[row][col] = value;
    }

    public void solveGame() {

        sudokuSolver.solve(this);                                       //fill remaining fields according to sudoku rules
    }
}
