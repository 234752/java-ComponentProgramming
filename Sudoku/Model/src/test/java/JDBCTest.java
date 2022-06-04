import org.junit.jupiter.api.Test;
import pl.cp.dao.JdbcDao;
import pl.cp.dao.SudokuBoardDaoFactory;
import pl.cp.exception.DaoException;
import pl.cp.model.SudokuBoard;
import pl.cp.solver.BacktrackingSudokuSolver;

import static org.junit.jupiter.api.Assertions.*;

public class JDBCTest {

    @Test
    public void testReadWrite() {

        try (JdbcDao dao = SudokuBoardDaoFactory.getJdbcDao(); JdbcDao dao2 = SudokuBoardDaoFactory.getJdbcDao()) {
            dao.connect();
            dao2.connect();

            dao.createTables();
            dao.createTables();
            dao.createNewBoard("tested board 1");
            dao.selectBoard("tested board 1");
            dao2.selectBoard("tested board 1");

            SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
            board.solveGame();
            board.lockField(1,1);
            dao.write(board);

            SudokuBoard board2 = dao2.read();
            assertEquals(board, board2);
            assertNotSame(board, board2);
            dao.getAllBoardNames();
        } catch (DaoException ex) {
            fail(ex);
        }
    }

    @Test
    public void nukeDatabase() {

        try (JdbcDao dao = SudokuBoardDaoFactory.getJdbcDao()) {
            dao.connect();
            dao.nukeDatabase();
            dao.nukeDatabase();
        } catch (DaoException ex) {
            fail(ex);
        }
    }

    @Test
    public void testExceptions() {
        try (JdbcDao dao = SudokuBoardDaoFactory.getJdbcDao()) {
            dao.connect();
            assertThrows(DaoException.class, () -> dao.createNewBoard("board"));
            assertThrows(DaoException.class, dao::read);
            assertThrows(DaoException.class, () -> dao.write(new SudokuBoard(new BacktrackingSudokuSolver())));
            assertThrows(DaoException.class, () -> dao.getAllBoardNames());
            dao.createTables();
            dao.selectBoard("not existing board");
            assertThrows(DaoException.class, () -> dao.read());
            dao.verifyBoardName("board");
            dao.verifyBoardName("not existing board");
            dao.nukeDatabase();
        } catch (DaoException ex) {
            fail(ex);
        }
    }
}
