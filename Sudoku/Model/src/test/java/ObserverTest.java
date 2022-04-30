import static org.junit.jupiter.api.Assertions.*;

import pl.cp.model.SudokuBoard;
import org.junit.jupiter.api.Test;
import pl.cp.solver.BacktrackingSudokuSolver;

public class ObserverTest {

    public ObserverTest() {
    }
    SudokuBoard testedBoard = new SudokuBoard(new BacktrackingSudokuSolver());

    @Test
    public void testIfObserverActivates() {
        testedBoard.set(8,8,1);
        testedBoard.set(7,7,1);//error message should NOT be printed

        testedBoard.switchObserver(true);
        //row
        testedBoard.set(8,8,4);
        testedBoard.set(8,0,4); //error message should be printed
        testedBoard.set(8,0,0);
        //column
        testedBoard.set(8,8,5);
        testedBoard.set(0,8,5); //error message should be printed
        testedBoard.set(0,8,0);
        //box
        testedBoard.set(8,8,6);
        testedBoard.set(7,7,6); //error message should be printed
        testedBoard.set(7,7,0);
        assertDoesNotThrow(() -> testedBoard.setObserverOfFields());
    }
}
