package ud2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejemplo1JdbcH2File {

    public static void main(String[] args) {
        // URL de conexión para H2 en memoria
        // DB_CLOSE_DELAY=-1 mantiene la BD activa mientras la JVM esté viva
        String url = "jdbc:h2:./BD/test;DB_CLOSE_DELAY=-1";
        String user = "sa";
        String password = "";

        // Conexión con try-resources. Se cierra automáticamente
        try (Connection conn = DriverManager.getConnection(url, user, password);
                Statement statement = conn.createStatement()) {

            // Recuperar Listado de usuarios
            System.out.println("LISTADO DE USUARIOS:");
            String sql = "SELECT * FROM users";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println("Usuario " + id + ": " + name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
