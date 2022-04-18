import org.junit.jupiter.api.Test;
import model.SudokuBoard;
import solver.BacktrackingSudokuSolver;

import static org.junit.jupiter.api.Assertions.*;

public class DaoTest {


    @Test
    public void testReadWrite() {
        SudokuBoard board1 = new SudokuBoard(new BacktrackingSudokuSolver());
        board1.set(0,0,7);
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        Dao<SudokuBoard> dao = factory.getFileDao("src/test/java/test.txt");
        dao.write(board1);
        SudokuBoard board2 = dao.read();
        assertTrue(board2.equals(board2));
    }
}
