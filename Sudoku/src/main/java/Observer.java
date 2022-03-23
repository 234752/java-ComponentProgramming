
public class Observer {

    private boolean checkBoard(SudokuBoard board) {

        for (int col = 0; col < 9; col++) {
            if (!board.getColumn(col).verify()) {
                return false;
            }
        }

        for (int row = 0; row < 9; row++) {
            if (!board.getRow(row).verify()) {
                return false;
            }
        }

        for (int row = 0; row < 9; row += 3) {
            for (int col = 0; col < 9; col += 3) {
                if (!board.getBox(row,col).verify()) {
                    return false;
                }
            }
        }
        return true;
    } //used for checking whole board

    public void update(SudokuBoard board) {
        if (!checkBoard(board)) System.out.println("invalid value was inserted");
    }
}
