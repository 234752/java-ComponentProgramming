import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class BacktrackingSudokuSolverTest {

    @Test
    public void testAlgorithm() {
        SudokuBoard testedBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        testedBoard.set(0,0,1);
        testedBoard.set(0,1,1);

        BacktrackingSudokuSolver testedSolver = new BacktrackingSudokuSolver();
        boolean isSolvable = testedSolver.solve(testedBoard);
        assertEquals(isSolvable, false);
    }

}
