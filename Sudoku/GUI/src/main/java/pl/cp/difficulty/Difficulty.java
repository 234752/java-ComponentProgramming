package pl.cp.difficulty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import pl.cp.model.SudokuBoard;

public enum Difficulty {

    EASY(20),
    MEDIUM(40),
    HARD(60);

    private final int fieldsToRemove;

    Difficulty(int amount) {
        this.fieldsToRemove = amount;
    }

    public void removeFields(SudokuBoard board) {
        List<Integer> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            x.add(i);
            y.add(i);
        }

        for (int i = 0; i < fieldsToRemove; i++) {
            while (true) {
                Collections.shuffle(x);
                Collections.shuffle(y);
                if (board.get(x.get(0), y.get(0)) != 0) {
                    board.set(x.get(0), y.get(0), 0);
                    break;
                }
            }

        }
    }

}
