package pl.cp.model;

public class SudokuRow extends SudokuSetOfNineFields {

    public void set(int x, int value) {
        fields.get(x).setFieldValue(value);
    }
}
