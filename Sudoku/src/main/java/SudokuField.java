public class SudokuField {

    private int value;
    private SudokuBoard observer;

    SudokuField(SudokuBoard observingBoard) {
        value = 0;
        observer = observingBoard;
    }

    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(int newValue) {
        value = newValue;
    }
}
