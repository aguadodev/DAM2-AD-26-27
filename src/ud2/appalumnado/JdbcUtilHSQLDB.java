package ud2.appalumnado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtilHSQLDB {
    public static Connection getConnection() {
        final String URL = "jdbc:hsqldb:./BD/instituto-HSQLDB";
        final String USER = "sa";
        final String PASS = "";

        Connection con = null;

        try {
            con = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return con;
    }

}
