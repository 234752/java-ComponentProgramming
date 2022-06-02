package pl.cp.dao;

import pl.cp.model.SudokuBoard;

public class SudokuBoardDaoFactory {

    private SudokuBoardDaoFactory() {

    }

    public static Dao<SudokuBoard> getFileDao(String filename) {
        return new FileSudokuBoardDao<>(filename);
    }

    public static JdbcDao getJdbcDao() {
        return new JdbcDao();
    }
}
