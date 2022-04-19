import org.junit.jupiter.api.Test;
import model.SudokuBoard;
import solver.BacktrackingSudokuSolver;

import static org.junit.jupiter.api.Assertions.*;

public class DaoTest { //mvn clean install -U   is needed when output stream re-throws old exception

    @Test
    public void testReadWrite() {
        SudokuBoard board1 = new SudokuBoard(new BacktrackingSudokuSolver());
        board1.set(0,0,7);
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        Dao<SudokuBoard> dao = factory.getFileDao("src/test/java/sb.txt");
        dao.write(board1);
        SudokuBoard board2 = dao.read();
        assertTrue(board1.equals(board2));
        assertTrue(board2.equals(board1));
        board2.setObserverOfFields();
        board2.solveGame();
    }
}
