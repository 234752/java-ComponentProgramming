import java.io.*;

public class FileSudokuBoardDao<SudokuBoard> implements Dao<SudokuBoard>, AutoCloseable {

    private String filename;

    public FileSudokuBoardDao (String filename) {
        this.filename = filename;
    }

    @Override
    public SudokuBoard read() {
        //FileInputStream fileInput = new FileInputStream(new File(filename));
        //ObjectInputStream objectInput = new ObjectInputStream(fileInput);
        //SudokuBoard board = (SudokuBoard)objectInput.readObject();
        //fileInput.close();
        //objectInput.close();
        return null;
    }

    @Override
    public void write(SudokuBoard obj) {
        //FileOutputStream output = new FileOutputStream(filename);
        //output.write(obj);
        //output.close();
    }

    @Override
    public void close() throws Exception {

    }
}
