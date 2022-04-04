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

    @Override
    public String toString() {
        return "SudokuField{" +
                "value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SudokuField that = (SudokuField) o;
        return value == that.value;
    }
}
