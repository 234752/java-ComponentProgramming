package pl.cp.model;

import java.io.Serializable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.cp.observe.Observer;
import pl.cp.solver.SudokuSolver;

public class SudokuBoard extends Observer implements Serializable, Cloneable {

    private static Logger logger = LoggerFactory.getLogger(SudokuBoard.class.getName());

    private SudokuField[][] fields = new SudokuField[9][9];
    private transient SimpleStringProperty[][] fieldsProperties;
    private SudokuSolver sudokuSolver;
    private boolean[][] lockedFields = new boolean[9][9];

    public SudokuBoard(SudokuSolver solver) {
        sudokuSolver = solver;
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                fields[row][col] = new SudokuField();
                fields[row][col].setObserver(this);
            }
        }
    }

    public SudokuBoard(SudokuBoard prototype) {
        SudokuSolver prototypeSolver = prototype.sudokuSolver;
        SudokuBoard newBoard = new SudokuBoard(prototypeSolver);
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                newBoard.fields[x][y].setFieldValue(prototype.fields[x][y].getFieldValue());
            }
        }
    }

    private void addListener(int x, int y) {
        fieldsProperties[x][y].addListener((observableValue, oldValue, newValue)
                -> {
            if (newValue.matches("[1-9]")) {
                set(x, y, Integer.parseInt(fieldsProperties[x][y].getValue()));
            }
            if (newValue.matches("")) {
                set(x, y, 0);
            }
        });
    }

    public void initializeProperties() {
        fieldsProperties = new SimpleStringProperty[9][9];
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                fieldsProperties[x][y] = new SimpleStringProperty(this.get(x,y) == 0 ? "" : Integer.toString(this.get(x,y)));
                addListener(x, y);
            }
        }
    }

    public StringProperty getProperty(int x, int y) {
        return fieldsProperties[x][y];
    }

    public void lockField(int x, int y) {
        lockedFields[x][y] = true;
    }

    public void unlockField(int x, int y) {
        lockedFields[x][y] = false;
    }

    public boolean isLocked(int x, int y) {
        return lockedFields[x][y];
    }

    public void setObserverOfFields() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                fields[x][y].setObserver(this);
            }
        }
    }

    @Override
    public void update() {
        if (observerIsTurnedOn) {
            if (checkBoard()) {
                logger.info("valid value of field inserted");
            } else {
                logger.info("invalid value of field inserted");
            }
        }
    }

    public int get(int x, int y) {
        return fields[x][y].getFieldValue();
    }

    public void set(int x, int y, int value) {
        fields[x][y].setFieldValue(value);
        if (fieldsProperties != null && fieldsProperties[x][y] != null) {
            fieldsProperties[x][y].set(value == 0 ? "" : Integer.toString(value));
        }
    }

    public boolean checkBoard() {

        for (int col = 0; col < 9; col++) {
            if (!getColumn(col).verify()) {
                return false;
            }
        }

        for (int row = 0; row < 9; row++) {
            if (!getRow(row).verify()) {
                return false;
            }
        }

        for (int row = 0; row < 9; row += 3) {
            for (int col = 0; col < 9; col += 3) {
                if (!getBox(row,col).verify()) {
                    return false;
                }
            }
        }
        return true;
    } //used for checking whole board

    public SudokuRow getRow(int x) {
        SudokuRow row = new SudokuRow();
        for (int y = 0; y < 9; y++) {
            row.addNextField(fields[x][y]);
        }
        return row;
    }

    public SudokuColumn getColumn(int y) {
        SudokuColumn col = new SudokuColumn();
        for (int x = 0; x < 9; x++) {
            col.addNextField(fields[x][y]);
        }
        return col;
    }

    public SudokuBox getBox(int x, int y) {
        SudokuBox box = new SudokuBox();
        int rowStart = x - x % 3;
        int colStart = y - y % 3;
        for (int row = rowStart; row < rowStart + 3; row++) {
            for (int col = colStart; col < colStart + 3; col++) {
                box.addNextField(fields[row][col]);
            }
        }
        return box;
    }

    public boolean isValid(int row, int col) {
        return getRow(row).verify() && getColumn(col).verify() && getBox(row, col).verify();
    } //combine 3 isValid methods

    public void solveGame() {
        sudokuSolver.solve(this);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("fields", fields)
                .append("sudokuSolver", sudokuSolver)
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

        SudokuBoard that = (SudokuBoard) o;

        return new EqualsBuilder()
                .append(fields, that.fields)
                .append(sudokuSolver, that.sudokuSolver)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(fields)
                .append(sudokuSolver)
                .toHashCode();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        SudokuBoard board = new SudokuBoard(this.sudokuSolver);
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                board.fields[x][y] = (SudokuField) this.fields[x][y].clone();
            }
        }
        return board;
    }
}
