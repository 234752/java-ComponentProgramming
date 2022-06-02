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
            dao.createNewBoard("tested board 1");
            dao.selectBoard("tested board 1");
            dao2.selectBoard("tested board 1");

            SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
            board.solveGame();
            dao.write(board);

            SudokuBoard board2 = dao2.read();
            assertEquals(board, board2);
            assertNotSame(board, board2);

        } catch (DaoException ex) {
            fail(ex);
        }
    }

    @Test
    public void nukeDatabase() {

        try (JdbcDao dao = SudokuBoardDaoFactory.getJdbcDao()) {
            dao.connect();
            dao.nukeDatabase();
        } catch (DaoException ex) {
            fail(ex);
        }
    }
}
