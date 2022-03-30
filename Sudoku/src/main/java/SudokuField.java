public class SudokuField extends Observable {

    private int value;
    private SudokuBoard observer;

    SudokuField(Observer observer) {
        value = 0;
    }

    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(int newValue) {
        value = newValue;
    }
}
