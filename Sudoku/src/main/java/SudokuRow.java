public class SudokuRow extends SudokuSetOfNineFields {

    private SudokuField[] row = new SudokuField[9];

    public void set(int index, SudokuField field) {
        row[index] = field;
    }

    public boolean verify() {
        return verifyFields(row);
    }
}
