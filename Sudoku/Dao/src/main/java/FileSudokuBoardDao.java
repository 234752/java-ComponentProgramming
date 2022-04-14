public class FileSudokuBoardDao<SudokuBoard> implements Dao<SudokuBoard>, AutoCloseable {

    private String filename;

    public FileSudokuBoardDao (String filename) {
        this.filename = filename;
    }

    @Override
    public SudokuBoard read() {
        return null;
    }

    @Override
    public void write(SudokuBoard obj) {

    }

    @Override
    public void close() throws Exception {

    }
}
