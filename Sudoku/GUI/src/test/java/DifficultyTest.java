import pl.cp.difficulty.Difficulty;
import pl.cp.model.SudokuBoard;
import org.junit.jupiter.api.Test;
import pl.cp.solver.BacktrackingSudokuSolver;


public class DifficultyTest {

    @Test
    public void checkEasy()
    {
        SudokuBoard testedBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        testedBoard.solveGame();
        Difficulty dif = Difficulty.EASY;
        dif.removeFields(testedBoard);

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                System.out.print(testedBoard.get(x,y) + " ");
            } System.out.println("");
        }
    }

}
