package pl.cp.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileSudokuBoardDao<SudokuBoard> implements Dao<SudokuBoard> {

    private String filename;

    public FileSudokuBoardDao(String filename) {
        this.filename = filename;
    }

    @Override
    public SudokuBoard read() {
        try (FileInputStream fileInput = new FileInputStream(filename);
             ObjectInputStream objectInput = new ObjectInputStream(fileInput)) {
            SudokuBoard board = (SudokuBoard)objectInput.readObject();
            fileInput.close();
            objectInput.close();
            return board;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    @Override
    public void write(SudokuBoard obj) {
        try (FileOutputStream fileOutput = new FileOutputStream(filename);
             ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput)) {
            objectOutput.writeObject(obj);
            fileOutput.close();
            objectOutput.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closed pl.cp.dao.FileSudokuBoardDao");
    }
}