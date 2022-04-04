import java.util.ArrayList;
import java.util.List;

public class SudokuSetOfNineFields {

    private List<SudokuField> fields = new ArrayList<>();

    public boolean verify() {

        int[] values = new int[10]; //counter for each digit 1-9

        for (int i = 0; i < 9; i++) {
            values[fields.get(i).getFieldValue()]++;
        } //count amount of appearances of each digit
        for (int v = 1; v <= 9; v++) {
            if (values[v] >= 2) {
                return false;
            }
        } //check if any digit repeats
        return true;
    }

    public void addNextField(SudokuField field) {
        fields.add(field);
    }

    @Override
    public String toString() {
        return "SudokuSetOfNineFields{" +
                "fields=" + fields +
                '}';
    }
}
