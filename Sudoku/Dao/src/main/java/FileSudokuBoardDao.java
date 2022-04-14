import solver.BacktrackingSudokuSolver;

import java.io.*;

public class FileSudokuBoardDao<SudokuBoard> implements Dao<SudokuBoard>, AutoCloseable {

    private String filename;

    public FileSudokuBoardDao (String filename) {
        this.filename = filename;
    }

    @Override
    public SudokuBoard read() {
        //SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        try {
            FileInputStream fileInput = new FileInputStream(new File(filename));
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            SudokuBoard board = (SudokuBoard)objectInput.readObject();
            fileInput.close();
            objectInput.close();
            return board;
        }
        catch (Exception ex) {

        }
        return null;

    }

    @Override
    public void write(SudokuBoard obj) {
        try {
            FileOutputStream fileOutput = new FileOutputStream(new File(filename));
            ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
            objectOutput.writeObject(obj);
            fileOutput.close();
            objectOutput.close();
        }
        catch (Exception ex) {

        }
    }

    @Override
    public void close() throws Exception {

    }
}