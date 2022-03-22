public class SudokuSetOfNineFields {

    public boolean verifyFields(SudokuField[] fields) {

        int[] values = new int[10]; //counter for each digit 1-9

        for (int i = 0; i < 9; i++) {
            values[fields[i].getFieldValue()]++;
        } //count amount of appearances of each digit
        for (int v = 1; v <= 9; v++) {
            if (values[v] >= 2) {
                return false;
            }
        } //check if any digit repeats
        return true;
    }
}
