package pl.cp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.cp.exception.DaoException;
import pl.cp.model.SudokuBoard;
import pl.cp.solver.BacktrackingSudokuSolver;

public class JdbcDao implements Dao<SudokuBoard> {

    private Connection conn;
    private Statement statement;
    private String boardName;

    public void selectBoard(String name) {
        boardName = name;
    }

    public void connect() throws DaoException {
        try {
            String dbUrl = "jdbc:derby:./database/SudokuDB;create=true";
            //String dbUrl = "jdbc:derby:memory:SudokuDB;create=true";
            conn = DriverManager.getConnection(dbUrl);
            statement = conn.createStatement();
        } catch (SQLException ex) {
            throw DaoException.getDaoException(ResourceBundle.getBundle("Exceptions_PL"), "daoConnectError");
        }
    }

    public void createTables() throws DaoException {
        try {
            ResultSet boardsExist = conn.getMetaData().getTables(null, null, "BOARDS", null);
            ResultSet fieldsExist = conn.getMetaData().getTables(null, null, "FIELDS", null);

            if (boardsExist.next()) {
                return;
            }
            statement.execute("create table boards"
                    + "(id int primary key generated by default "
                    + "as identity (start with 0, increment by 1),"
                    + "board_name varchar(255))");

            if (fieldsExist.next()) {
                return;
            }
            statement.execute("create table fields"
                    + "(id int primary key generated by default "
                    + "as identity (start with 0, increment by 1),"
                    + "board_id int references boards(id), x int, y int, value int,"
                    + "is_locked boolean)");
        } catch (SQLException ex) {
            throw DaoException.getDaoException(ResourceBundle.getBundle("Exceptions_PL"), "daoCreateDatabaseError");
        }
    }

    public void nukeDatabase() throws DaoException {
        try {
            ResultSet boardsExist = conn.getMetaData().getTables(null, null, "BOARDS", null);
            ResultSet fieldsExist = conn.getMetaData().getTables(null, null, "FIELDS", null);

            if (fieldsExist.next()) {
                statement.executeUpdate("drop table fields");
            }
            if (boardsExist.next()) {
                statement.executeUpdate("drop table boards");
            }

        } catch (SQLException ex) {
            throw DaoException.getDaoException(ResourceBundle.getBundle("Exceptions_PL"), "daoNukeDatabaseError");
        }
    }

    public void createNewBoard(String name) throws DaoException {
        try {
            PreparedStatement initialInsert = conn.prepareStatement("insert into boards (board_name) values (?)");
            initialInsert.setObject(1, name);
            initialInsert.execute();
        } catch (SQLException ex) {
            throw DaoException.getDaoException(ResourceBundle.getBundle("Exceptions_PL"), "daoCreateError");
        }
    }

    public ObservableList getAllBoardNames() throws DaoException {
        try {
            ObservableList names = FXCollections.observableArrayList();
            ResultSet set = statement.executeQuery("select * from boards");
            while (set.next()) {
                names.add(set.getString("board_name"));
            }
            return names;
        } catch (SQLException ex) {
            throw DaoException.getDaoException(ResourceBundle.getBundle("Exceptions_PL"), "daoGetBoardNamesError");
        }
    }

    @Override
    public SudokuBoard read() throws DaoException {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        try {
            int boardId = fetchBoardId(boardName);
            ResultSet set = statement.executeQuery("select * from fields where board_id = " + boardId);
            while (set.next()) {
                board.set(set.getInt("x"), set.getInt("y"), set.getInt("value"));
                if (set.getBoolean("is_locked")) {
                    board.lockField(set.getInt("x"), set.getInt("y"));
                } else {
                    board.unlockField(set.getInt("x"), set.getInt("y"));
                }
            }
            return board;
        } catch (SQLException ex) {
            throw DaoException.getDaoException(ResourceBundle.getBundle("Exceptions_PL"), "daoReadError");
        }
    }

    @Override
    public void write(SudokuBoard obj) throws DaoException {
        try {
            int boardId = fetchBoardId(boardName);
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    PreparedStatement updateBoard = conn.prepareStatement(
                            "insert into fields (board_id, x, y, value, is_locked) values ("
                                    + boardId + ", "
                                    + i + ", "
                                    + j + ", "
                                    + obj.get(i,j) + ", "
                                    + obj.isLocked(i,j)
                                    + ")");
                    updateBoard.execute();
                }
            }
        } catch (Exception ex) {
            throw DaoException.getDaoException(ResourceBundle.getBundle("Exceptions_PL"), "daoWriteError");
        }
    }

    private int fetchBoardId(String name) throws SQLException {
        ResultSet set = statement.executeQuery("select * from boards where board_name = '" + name + "'");
        if (set.next()) {
            return set.getInt("id");
        } else {
            throw new SQLException();
        }
    }

    @Override
    public void close() throws DaoException {

    }
}
