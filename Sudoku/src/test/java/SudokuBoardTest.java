
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SudokuBoardTest {

    public SudokuBoardTest() {
    }

    @BeforeEach
    private void setupTestedBoard() {
        SudokuBoard testedBoard = new SudokuBoard(new BacktrackingSudokuSolver());
    }
}
