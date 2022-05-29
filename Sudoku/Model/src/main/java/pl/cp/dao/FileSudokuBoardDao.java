package pl.cp.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.cp.exception.DaoException;

public class FileSudokuBoardDao<SudokuBoard> implements Dao<SudokuBoard> {

    private static Logger logger = LoggerFactory.getLogger(pl.cp.model.SudokuBoard.class.getName());

    private ResourceBundle bundle = ResourceBundle.getBundle("Exceptions_PL");

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
            logger.info("daoReadError");
            throw  DaoException.getDaoException(bundle, "daoReadError");
        }
    }

    @Override
    public void write(SudokuBoard obj) throws DaoException {
        try (FileOutputStream fileOutput = new FileOutputStream(filename);
             ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput)) {
            objectOutput.writeObject(obj);
        } catch (Exception ex) {
            logger.info("daoWriteError");
            throw  DaoException.getDaoException(bundle, "daoWriteError");
        }
    }

    @Override
    public void close() throws Exception {
        logger.info("daoClosed");
    }
}
