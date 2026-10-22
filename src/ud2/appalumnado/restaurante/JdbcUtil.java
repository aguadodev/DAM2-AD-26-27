
package ud2.appalumnado.restaurante;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtil {

    // Guarda la BD en fichero dentro de la carpeta /BD del proyecto
    public static Connection getConnection() {
        final String URL = "jdbc:h2:./BD/restaurante;DB_CLOSE_DELAY=-1";
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