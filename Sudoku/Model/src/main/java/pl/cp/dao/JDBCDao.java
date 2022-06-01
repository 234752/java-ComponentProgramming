package pl.cp.dao;

import pl.cp.exception.DaoException;
import pl.cp.model.SudokuBoard;
import pl.cp.model.SudokuField;
import pl.cp.model.SudokuRow;
import pl.cp.solver.BacktrackingSudokuSolver;

import java.sql.*;
import java.util.ResourceBundle;

public class JDBCDao implements Dao<SudokuBoard> {

    Connection conn;

    public void connect() throws DaoException {
        try {
            String dbUrl = "jdbc:derby:memory:SudokuDB;create=true";
            conn = DriverManager.getConnection(dbUrl);
        } catch (SQLException ex) {
            throw DaoException.getDaoException(ResourceBundle.getBundle("Exceptions_PL"), "daoConnectError");
        }
    }

    public void createNewBoard(int id) throws DaoException {
        try {
            Statement statement = conn.createStatement();
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
            Statement statement = conn.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM boards where id = 102");
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
            String id = "102";
            for (int i = 0; i<9; i++) {
                for (int j = 0; j<9; j++) {
                    PreparedStatement updateBoard = conn.prepareStatement("update boards set f"+i+j+" = " +obj.get(i,j)+ " where id = ?");
                    updateBoard.setObject(1, id);
                    updateBoard.execute();
                }
            }
        } catch (Exception ex) {
            throw DaoException.getDaoException(ResourceBundle.getBundle("Exceptions_PL"), "daoWriteError");
        }
    }

    @Override
    public void close() throws Exception {

    }
}
