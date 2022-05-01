import pl.cp.difficulty.Difficulty;
import pl.cp.model.SudokuBoard;
import org.junit.jupiter.api.Test;
import pl.cp.solver.BacktrackingSudokuSolver;
import static org.junit.jupiter.api.Assertions.*;


public class DifficultyTest {

    @Test
    public void checkLevels() {
        SudokuBoard testedBoard = new SudokuBoard(new BacktrackingSudokuSolver());

        Difficulty easy = Difficulty.EASY;
        Difficulty med = Difficulty.MEDIUM;
        Difficulty hard = Difficulty.HARD;

        testedBoard.solveGame();
        easy.removeFields(testedBoard);
        int amountEasy = 0;
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (testedBoard.get(x, y) == 0) amountEasy++;
            }
        }

        testedBoard.solveGame();
        med.removeFields(testedBoard);
        int amountMed = 0;
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (testedBoard.get(x, y) == 0) amountMed++;
            }
        }

        testedBoard.solveGame();
        hard.removeFields(testedBoard);
        int amountHard = 0;
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (testedBoard.get(x, y) == 0) amountHard++;
            }
        }

        assertEquals(amountEasy, 20);
        assertEquals(amountMed, 40);
        assertEquals(amountHard, 60);
    }

}
