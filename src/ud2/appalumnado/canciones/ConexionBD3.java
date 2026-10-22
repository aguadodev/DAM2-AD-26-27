package ud2.appalumnado.canciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD3 {
    public static Connection conectar() {
        String url = "jdbc:h2:mem:dbCanciones;DB_CLOSE_DELAY=-1";
        String user = "sa";
        String password = "";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
