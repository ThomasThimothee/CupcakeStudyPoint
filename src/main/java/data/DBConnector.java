package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private Connection con;
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://188.166.91.15:3306/Cupcake";
    private static final String ID = "cupcake";
    private static final String PW = "cupcake";

    public Connection getConnection() {
        con = null;
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, ID, PW);  

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("\n*** Remember to insert your  ID and PW in the DBConnector class! ***\n");
            System.out.println("error in DBConnector.getConnection()");
            System.out.println(e);
        }

        return con;
    }

    public void releaseConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}

