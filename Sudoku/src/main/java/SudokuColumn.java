public class SudokuColumn extends SudokuSetOfNineFields {

    private SudokuField[] column = new SudokuField[9];

    public void set(int index, SudokuField field) {
        column[index] = field;
    }

    public boolean verify() {
        return verifyFields(column);
    }
}
