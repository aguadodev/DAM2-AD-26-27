package ud2.instituto;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Alumnos {
    public static void findAll() {
        Connection con = ConexionBD.conectar();

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM alumnos");

            while (rs.next()) {
                System.out.println("Id: " + rs.getInt("id"));
                System.out.println("Nombre: " + rs.getInt("nombre"));
            }

            rs.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
