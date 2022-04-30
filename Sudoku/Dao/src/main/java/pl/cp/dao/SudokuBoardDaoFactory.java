package pl.cp.dao;

import pl.cp.dao.Dao;
import pl.cp.dao.FileSudokuBoardDao;
import pl.cp.model.SudokuBoard;

public class SudokuBoardDaoFactory {

    private SudokuBoardDaoFactory() {}

    public static Dao<SudokuBoard> getFileDao(String filename) {
        return new FileSudokuBoardDao<>(filename);
    }
}
