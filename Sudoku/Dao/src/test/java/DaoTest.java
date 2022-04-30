import org.junit.jupiter.api.Test;
import pl.cp.dao.Dao;
import pl.cp.dao.SudokuBoardDaoFactory;
import pl.cp.model.SudokuBoard;
import pl.cp.solver.BacktrackingSudokuSolver;

import static org.junit.jupiter.api.Assertions.*;

public class DaoTest { //mvn clean install -U   is needed when output stream re-throws old exception

    @Test
    public void testReadWrite() {
        SudokuBoard board1 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard board2 = new SudokuBoard(new BacktrackingSudokuSolver());
        board1.set(0,0,7);

        try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getFileDao("src/test/java/sb.txt")) {
            dao.write(board1);
            board2 = dao.read();
            dao.close();
        } catch (Exception exception) {
            System.out.println(exception);
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
            System.out.println(exception);
        }
    }

    @Test
    public void testInvalidWrite() {
        SudokuBoard board1 = new SudokuBoard(new BacktrackingSudokuSolver());

        try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getFileDao("src/test/java/write")) {
            dao.write(board1);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}
