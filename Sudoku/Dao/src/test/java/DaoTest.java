import org.junit.jupiter.api.Test;
import model.SudokuBoard;
import solver.BacktrackingSudokuSolver;

import static org.junit.jupiter.api.Assertions.*;

public class DaoTest { //mvn clean install -U   is needed when output stream re-throws old exception

    @Test
    public void testReadWrite() throws Exception {
        SudokuBoard board1 = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard board2 = new SudokuBoard(new BacktrackingSudokuSolver());
        board1.set(0,0,7);
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();

        try (Dao<SudokuBoard> dao = factory.getFileDao("src/test/java/sb.txt")) {
            dao.write(board1);
            board2 = dao.read();
            dao.close();        //+1 FileSudokuBoard/Dao<SudokuBoard> is closed in test methods.
                                //task requirement?
        } catch (Exception exception) {
            System.out.println(exception);
        }

        assertTrue(board1.equals(board2));
        assertTrue(board2.equals(board1));
        board2.setObserverOfFields();
        board2.solveGame();
    }
}
