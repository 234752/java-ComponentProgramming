import org.junit.jupiter.api.Test;
import model.SudokuBoard;
import solver.BacktrackingSudokuSolver;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class DaoTest {


    @Test
    public void testReadWrite() {
        SudokuBoard board1 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        Dao<SudokuBoard> dao = factory.getFileDao("src/test/java/test.txt");
        dao.write(board1);


            SudokuBoard board2 = dao.read();

        //assertTrue(board2.equals(board1));
    }
}
