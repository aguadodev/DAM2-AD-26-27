package ud2.appalumnado;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtilProp {
    public static Connection getConnection() {
        final String FICHERO = "src/ud2/appalumnado/JdbcUtilProp.java";
        Properties properties = new Properties();
        Connection con = null;
        try {
            properties.load(new FileInputStream(new File(FICHERO)));

            final String URL = properties.getProperty("URL");
            final String USER = properties.getProperty("db.user");
            final String PASS = properties.getProperty("db.password");
            con = DriverManager.getConnection(URL, USER, PASS);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }

    public static void main(String[] args) {
        System.out.println(getConnection());
    }

}
