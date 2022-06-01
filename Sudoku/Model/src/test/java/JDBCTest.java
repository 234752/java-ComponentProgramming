import org.junit.jupiter.api.Test;
import pl.cp.dao.Dao;
import pl.cp.dao.JDBCDao;
import pl.cp.dao.SudokuBoardDaoFactory;
import pl.cp.exception.DaoException;
import pl.cp.model.SudokuBoard;
import pl.cp.solver.BacktrackingSudokuSolver;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class JDBCTest {

    @Test
    public void testReadWrite() {
        JDBCDao dao = new JDBCDao();
        try {
            dao.connect();
            dao.createNewBoard(102);
            SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
            board.set(1,1,7);
            dao.write(board);
            SudokuBoard board2 = dao.read();
            assertEquals(board2.get(1,1), 7);
        } catch (Exception ex) {
            fail();
        }


    }
}
