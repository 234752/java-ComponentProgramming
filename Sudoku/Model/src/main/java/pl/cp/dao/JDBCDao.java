package pl.cp.dao;

import pl.cp.exception.DaoException;
import pl.cp.model.SudokuBoard;
import pl.cp.model.SudokuField;
import pl.cp.model.SudokuRow;
import pl.cp.solver.BacktrackingSudokuSolver;

import java.sql.*;
import java.util.ResourceBundle;

public class JDBCDao implements Dao<SudokuBoard> {

    private Connection conn;
    private Statement statement;
    private String boardName;

    public void selectBoard(String name) {
        boardName = name;
    }

    public void connect() throws DaoException {
        try {
            String dbUrl = "jdbc:derby:./target/SudokuDB;create=true";
            //String dbUrl = "jdbc:derby:memory:SudokuDB;create=true";
            conn = DriverManager.getConnection(dbUrl);
            statement = conn.createStatement();
        } catch (SQLException ex) {
            throw DaoException.getDaoException(ResourceBundle.getBundle("Exceptions_PL"), "daoConnectError");
        }
    }

    public void createDatabase() throws DaoException {
        try {
            throw new SQLException();
        } catch (SQLException ex) {
            throw DaoException.getDaoException(ResourceBundle.getBundle("Exceptions_PL"), "daoCreateDatabaseError");        //NEW
        }
    }

    public void nukeDatabase() throws DaoException {
        try {

            statement.executeUpdate("drop table boards");
        } catch (SQLException ex) {
            throw DaoException.getDaoException(ResourceBundle.getBundle("Exceptions_PL"), "daoCreateDatabaseError");                //NEW
        }
    }

    public void createNewBoard(int id) throws DaoException {
        try {
            String ddl = "Create table boards (id int primary key";

            for (int i = 0; i<9; i++) {
                for (int j = 0; j<9; j++) {
                    ddl += ", f"+i+j+" int";
                }
            }
            ddl+=")";

            statement.executeUpdate(ddl);

            PreparedStatement initialInsert = conn.prepareStatement("insert into boards (id) values (?)");
            initialInsert.setObject(1, id);
            initialInsert.execute();
        } catch (SQLException ex) {
            throw DaoException.getDaoException(ResourceBundle.getBundle("Exceptions_PL"), "daoCreateError");
        }

    }


    @Override
    public SudokuBoard read() throws DaoException {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        try {
            ResultSet set = statement.executeQuery("SELECT * FROM boards where id = "+boardName);
            if(set.next()) {
                for (int i = 0; i<9; i++) {
                    for (int j = 0; j<9; j++) {
                        board.set(i, j, set.getInt("f"+i+j));
                    }
                }
                return board;
            }
            return null;

        } catch (SQLException ex) {
            throw DaoException.getDaoException(ResourceBundle.getBundle("Exceptions_PL"), "daoReadError");
        }
    }

    @Override
    public void write(SudokuBoard obj) throws DaoException {
        try {
            for (int i = 0; i<9; i++) {
                for (int j = 0; j<9; j++) {
                    PreparedStatement updateBoard = conn.prepareStatement("update boards set f"+i+j+" = " +obj.get(i,j)+ " where id = ?");
                    updateBoard.setObject(1, boardName);
                    updateBoard.execute();
                }
            }
        } catch (Exception ex) {
            throw DaoException.getDaoException(ResourceBundle.getBundle("Exceptions_PL"), "daoWriteError");
        }
    }

    @Override
    public void close() throws DaoException {

    }
}
