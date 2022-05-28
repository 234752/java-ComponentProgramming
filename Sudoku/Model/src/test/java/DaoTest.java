import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.cp.dao.Dao;
import pl.cp.dao.SudokuBoardDaoFactory;
import pl.cp.exception.DaoException;
import pl.cp.model.SudokuBoard;
import pl.cp.solver.BacktrackingSudokuSolver;
import static org.junit.jupiter.api.Assertions.*;

public class DaoTest { //mvn clean install -U   is needed when output stream re-throws old exception

    private static Logger logger = LoggerFactory.getLogger(SudokuBoard.class.getName());

    @Test
    public void testReadWrite() {
        SudokuBoard board1 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard board2 = new SudokuBoard(new BacktrackingSudokuSolver());
        board1.set(0,0,7);

        try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getFileDao("target/boardSavedFromDaoTest.txt")) {
            dao.write(board1);
            board2 = dao.read();
        } catch (Exception exception) {
            logger.info(exception.toString());
        }

        assertTrue(board1.equals(board2));
        assertTrue(board2.equals(board1));
        board2.setObserverOfFields();
        board2.solveGame();
    }

    @Test
    public void testInvalidRead() {
        SudokuBoard board1 = new SudokuBoard(new BacktrackingSudokuSolver());

        try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getFileDao("src/test/java/read.txt")) {
            board1 = dao.read();
        } catch (Exception exception) {
            logger.info(exception.toString());
        }
    }

    @Test
    public void testInvalidWrite() {
        SudokuBoard board1 = new SudokuBoard(new BacktrackingSudokuSolver());

        try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getFileDao("src/test/java/write")) {
            dao.write(board1);
        } catch (Exception exception) {
            logger.info(exception.toString());
        }
    }
}
