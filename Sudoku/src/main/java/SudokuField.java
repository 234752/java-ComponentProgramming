import java.util.Objects;

public class SudokuField extends Observable {

    private int value;

    SudokuField(Observer observer) {
        value = 0;
        setObserver(observer);
    }

    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(int newValue) {
        value = newValue;
        notifyObserver();
    }
}
