import org.junit.jupiter.api.Test;
import pl.cp.dao.Dao;
import pl.cp.dao.JDBCDao;
import pl.cp.dao.SudokuBoardDaoFactory;
import pl.cp.exception.DaoException;
import pl.cp.model.SudokuBoard;
import pl.cp.solver.BacktrackingSudokuSolver;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class JDBCTest {

    @Test
    public void testReadWrite() {
        JDBCDao dao = new JDBCDao();
        try {
            dao.connect();
            dao.createNewBoard(102);
            SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
            dao.write(board);
        } catch (Exception ex) {
            fail();
        }


    }
}
