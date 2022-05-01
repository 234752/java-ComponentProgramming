package pl.cp.model;

import java.io.Serializable;
import pl.cp.observe.Observable;
import pl.cp.observe.Observer;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SudokuField extends Observable implements Serializable, Comparable<SudokuField>, Cloneable {

    private int value;

    public SudokuField(Observer observer) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SudokuField that = (SudokuField) o;

        return new EqualsBuilder().append(value, that.value).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(value).toHashCode();
    }

    @Override
    public int compareTo(SudokuField field) {
        return this.getFieldValue() - field.getFieldValue();
    }

    @Override
    public SudokuField clone() {
        SudokuField field = new SudokuField(this.observer);
        field.setFieldValue(this.getFieldValue());
        return field;
    }
}
