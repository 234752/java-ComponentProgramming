import org.apache.commons.lang3.builder.ToStringBuilder;

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
        return new ToStringBuilder(this)
                .append("value", value)
                .toString();
    }
}
