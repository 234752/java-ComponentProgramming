import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileSudokuBoardDao<SudokuBoard> implements Dao<SudokuBoard>, AutoCloseable {

    private String filename;

    public FileSudokuBoardDao (String filename) {
        this.filename = filename;
    }

    @Override
    public SudokuBoard read() {
        FileInputStream input = new FileInputStream(filename);
        SudokuBoard board = (SudokuBoard)input.read();
        input.close();
        return board;
    }

    @Override
    public void write(SudokuBoard obj) {
        FileOutputStream output = new FileOutputStream(filename);
        output.write(obj);
        output.close();
    }

    @Override
    public void close() throws Exception {

    }
}
