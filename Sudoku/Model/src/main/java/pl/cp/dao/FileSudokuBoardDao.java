package pl.cp.dao;

import pl.cp.exception.DaoException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileSudokuBoardDao<SudokuBoard> implements Dao<SudokuBoard> {

    private static Logger logger = Logger.getLogger(FileSudokuBoardDao.class.getName());

    private String filename;

    public FileSudokuBoardDao(String filename) {
        this.filename = filename;
    }

    @Override
    public SudokuBoard read() throws DaoException {
        try (FileInputStream fileInput = new FileInputStream(filename);
             ObjectInputStream objectInput = new ObjectInputStream(fileInput)) {
            SudokuBoard board = (SudokuBoard)objectInput.readObject();
            fileInput.close();
            objectInput.close();
            return board;
        } catch (Exception ex) {
            logger.log(Level.WARNING, "read error");
            throw new DaoException(ex.getMessage());
        }
    }

    @Override
    public void write(SudokuBoard obj) throws DaoException {
        try (FileOutputStream fileOutput = new FileOutputStream(filename);
             ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput)) {
            objectOutput.writeObject(obj);
        } catch (Exception ex) {
            logger.log(Level.WARNING, "write error");
            throw new DaoException(ex.getMessage());
        }
    }

    @Override
    public void close() throws Exception {
        logger.log(Level.INFO, "closed");
    }
}
