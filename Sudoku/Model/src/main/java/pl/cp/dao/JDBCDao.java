package pl.cp.dao;

import pl.cp.exception.DaoException;

import java.sql.*;

public class JDBCDao<SudokuBoard> implements Dao<SudokuBoard> {


        Connection conn;

        public static void main(String[] args) throws SQLException {
            JDBCDao db = new JDBCDao();

            db.connect();
            db.makeBoard();
        }

        public void connect() throws SQLException {
            String dbUrl = "jdbc:derby:memory:SudokuDB;create=true";
            conn = DriverManager.getConnection(dbUrl);
        }

        public void makeBoard() throws SQLException{

            Statement statement = conn.createStatement();


            // create table
            //statement.executeUpdate("Create table boards (id int primary key)");
            String pog = "Create table boards (id int primary key";

            for (int i = 0; i<3; i++) {
                for (int j = 0; j<3; j++) {
                    pog += ", f"+i+j+" int";
                }
            }

            pog+=")";

            PreparedStatement ps = conn.prepareStatement(pog);
            ps.execute();

            // insert 2 rows
            statement.executeUpdate("insert into boards values (102,1,2,3,4,5,6,7,8,9)");

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
        }

        public void DDL() throws SQLException {
            Statement statement = conn.createStatement();

            // drop table
            // stmt.executeUpdate("Drop Table users");

            // create table
            statement.executeUpdate("Create table boards (id int primary key, f00 int, f01 int, f02 int)");

            // insert 2 rows
            statement.executeUpdate("insert into boards values (1,1,2,3)");
            statement.executeUpdate("insert into boards values (2,4,5,6)");


            // query
            ResultSet set = statement.executeQuery("SELECT * FROM boards");

            // print out query result
            while (set.next()) {
                System.out.printf("%d\t%s\t%s\t%s\n", set.getInt("id"),
                        set.getInt("f00"),
                        set.getInt("f01"),
                        set.getInt("f02")
                );
            }
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
