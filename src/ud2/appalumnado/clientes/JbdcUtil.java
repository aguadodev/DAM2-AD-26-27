package ud2.appalumnado.clientes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * @author Adrián Gómez
 */
public class JbdcUtil {
    public static Connection getConnection() {
        String url = "jdbc:h2:./BD/empresa";
        String user = "sa";
        String password = "";

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Error de sql: " + e.getMessage());
        }

        return conn;
    }
}
