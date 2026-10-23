package ud2.appalumnado;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class JdbcUtilProp {

    private static final String CONFIG_FILE = "./BD/config.properties";

    public static Connection getConnection() throws IOException, SQLException {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            props.load(fis);
        }

        String url  = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String pass = props.getProperty("db.password", "");

        return DriverManager.getConnection(url, user, pass);
    }
}