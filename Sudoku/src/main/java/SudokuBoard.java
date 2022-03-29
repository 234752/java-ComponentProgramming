public class SudokuBoard {

    private SudokuField[][] fields = new SudokuField[9][9];
    private SudokuSolver sudokuSolver;
    //private List<SudokuRow> rows = new ArrayList<SudokuRow>();
    //private SudokuColumn[] columns = new SudokuColumn[9];
    //private SudokuBox[][] boxes = new SudokuBox[3][3];
    private boolean observerTurnedOn = false;

    public SudokuBoard(SudokuSolver solver) {

        sudokuSolver = solver;

        for (int i = 0; i < 9; i++) {
            //rows[i] = new SudokuRow();
            //rows.add(new SudokuRow());
            //columns[i] = new SudokuColumn();
        }
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                //boxes[x][y] = new SudokuBox();
            }
        }

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                fields[row][col] = new SudokuField(this);
                //rows.get(row).set(col, fields[row][col]); //assigment to rows
                //columns[col].set(row, fields[row][col]); //assigment to columns
                //boxes[(row - row % 3) / 3][(col - col % 3) / 3].set(row % 3, col % 3, fields[row][col]);
                //assignment to boxes
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
        return (getRow(row).verify() && getColumn(col).verify() && getBox(row, col).verify());
    } //combine 3 isValid methods

    public void solveGame() {
        sudokuSolver.solve(this);
    }
}
