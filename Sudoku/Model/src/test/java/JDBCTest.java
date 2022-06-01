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
    public void createDatabase() {

        try (JDBCDao dao = new JDBCDao()) {
            dao.connect();
            dao.createTables();
            dao.createNewBoard(102);
            SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
            board.set(1,1,9);
            dao.write(board);
        } catch (DaoException ex) {
            fail(ex);
        }
    }

    @Test
    public void nukeDatabase() {

        try (JDBCDao dao = new JDBCDao()) {
            dao.connect();
            dao.nukeDatabase();
        } catch (DaoException ex) {
            fail(ex);
        }
    }

    @Test
    public void testReadWrite() {

        try (JDBCDao dao = new JDBCDao(); JDBCDao dao2 = new JDBCDao();) {
            dao.connect();
            dao2.connect();
            dao.nukeDatabase();
            dao.createTables();
            dao.createNewBoard(102);
            SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
            board.set(1,1,9);
            dao.selectBoard("102");
            dao2.selectBoard("102");
            dao.write(board);
            SudokuBoard board2 = dao2.read();
            assertEquals(board2.get(1,1), 9);

        } catch (DaoException ex) {
            fail(ex);
        }
    }
}
