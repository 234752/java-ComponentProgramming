import model.SudokuBoard;

public class SudokuBoardDaoFactory {

    public static Dao<SudokuBoard> getFileDao(String filename) {
        return new FileSudokuBoardDao<>(filename);
    }
}
