package pl.cp.model;

public class SudokuRow extends SudokuSetOfNineFields {

    public void set(int x, int value) {
        super.set(x, value);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        SudokuRow row = new SudokuRow();
        for (int i = 0; i < this.fields.size(); i++) {
            row.addNextField(new SudokuField());
            row.set(i, this.fields.get(i).getFieldValue());
        }
        return row;
    }
}
