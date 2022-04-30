import pl.cp.difficulty.Difficulty;
import pl.cp.model.SudokuBoard;
import org.junit.jupiter.api.Test;
import pl.cp.solver.BacktrackingSudokuSolver;
import static org.junit.jupiter.api.Assertions.*;


public class DifficultyTest {

    @Test
    public void checkEasy()
    {
        SudokuBoard testedBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        testedBoard.solveGame();
        Difficulty dif = Difficulty.EASY;
        dif.removeFields(testedBoard);

        int amount = 0;
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (testedBoard.get(x, y) == 0) amount++;
            }
        }
        assertEquals(amount, 20);
    }

}
