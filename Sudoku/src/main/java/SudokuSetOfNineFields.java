public class SudokuSetOfNineFields {

    private SudokuField[] fields = new SudokuField[9];

    public boolean verify() {

        int[] values = new int[10];

        for (int i=0; i<9; i++) {
            values[fields[i].getFieldValue()]++;
        } //count amount of appearances of each digit
        for (int v=1; v<=9; v++) {
            if (values[v] >= 2) return false;
        } //check if any digit repeats
        return true;
    }
}
