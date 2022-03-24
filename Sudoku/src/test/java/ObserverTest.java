
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ObserverTest {

    public ObserverTest() {
    }
    SudokuBoard testedBoard = new SudokuBoard(new BacktrackingSudokuSolver());

    @Test
    public void testIfObserverActivates() {
        testedBoard.set(8,8,1);
        testedBoard.set(7,7,1);//error message should NOT be printed
        testedBoard.addObserver(new Observer());
        testedBoard.set(7,7,0); //info message should be printed
        testedBoard.set(7,7,1); //error message should be printed
    }
}
