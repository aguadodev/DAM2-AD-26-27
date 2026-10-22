package ud2.appalumnado.gimnasio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JbcUtil {

    public static Connection getConnection() {
        String url = "jdbc:h2:./BD/gimnasio";
        String user = "sa";
        String pass = "";

        Connection con = null;

        try {
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return con;
    }

}
