package pl.cp.model;

public class SudokuBox extends SudokuSetOfNineFields {

    public void set(int x, int y, int value) {
        int index = 3 * x + y;
        super.set(index, value);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        SudokuBox box = new SudokuBox();
        for (int i = 0; i < this.fields.size(); i++) {
            box.addNextField((SudokuField) this.fields.get(i).clone());
        }
        return box;
    }
}
