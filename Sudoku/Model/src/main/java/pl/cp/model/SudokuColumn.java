package pl.cp.model;

public class SudokuColumn extends SudokuSetOfNineFields {

    public void set(int x, int value) {
        super.set(x, value);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        SudokuColumn col = new SudokuColumn();
        for (int i = 0; i < this.fields.size(); i++) {
            col.addNextField(new SudokuField());
            col.set(i, this.fields.get(i).getFieldValue());
        }
        return col;
    }
}
