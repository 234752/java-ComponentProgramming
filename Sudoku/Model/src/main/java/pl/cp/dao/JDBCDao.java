package pl.cp.dao;

import pl.cp.exception.DaoException;

import java.sql.*;

public class JDBCDao<SudokuBoard> implements Dao<SudokuBoard> {


    Connection conn;

    public static void main(String[] args) throws SQLException {
        JDBCDao db = new JDBCDao();

        db.connect();
        db.createNewBoard(102);
    }

    public void connect() throws SQLException {
        String dbUrl = "jdbc:derby:memory:SudokuDB;create=true";
        conn = DriverManager.getConnection(dbUrl);
    }

    public void createNewBoard(int id) throws SQLException{

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

        //statement.executeUpdate("insert into boards (id) values (102)");
        //for (int i = 0; i<3; i++) {
        //    for (int j = 0; j<3; j++) {
        //        String sql = "update boards set f"+i+j+" = " +j+ " where id = 102";
        //        PreparedStatement ps2 = conn.prepareStatement(sql);
        //        ps2.execute();
        //    }
        //}


        /*
        // query
        ResultSet set = statement.executeQuery("SELECT * FROM boards");

        // print out query result
        while (set.next()) {
           System.out.printf("%d\t\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n", set.getInt("id"),
                    set.getInt("f00"),
                    set.getInt("f01"),
                    set.getInt("f02"),
                    set.getInt("f10"),
                    set.getInt("f11"),
                    set.getInt("f12"),
                    set.getInt("f20"),
                    set.getInt("f21"),
                    set.getInt("f22")
            );
        }

         */
    }


    @Override
    public SudokuBoard read() throws DaoException {
        return null;
    }

    @Override
    public void write(SudokuBoard obj) throws DaoException {

    }

    @Override
    public void close() throws Exception {

    }
}
