import org.junit.jupiter.api.Test;
import model.SudokuBoard;
import solver.BacktrackingSudokuSolver;

public class DaoTest {


    @Test
    public void testReadWrite() {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        Dao<SudokuBoard> dao = factory.getFileDao("test.txt");
        dao.write(board);
    }
}
