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
            System.out.println("\nALUMNOS");
            System.out.println("=======\n");
            while (rs.next()) {
                System.out.println("Id: " + rs.getInt("id"));
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Apellidos: " + rs.getString("apellidos"));
                System.out.println("Fecha de nacimiento: " + rs.getDate("fecha_nacimiento"));
                System.out.println("Curso: " + rs.getString("curso"));
                System.out.println("Nota media: " + rs.getDouble("nota_media"));
                System.out.println("\n");
            }

            rs.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
