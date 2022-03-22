public class SudokuRow extends SudokuSetOfNineFields {

    private SudokuField[] row = new SudokuField[9];

    public boolean verify() {
        return verifyFields(row);
    }
}
