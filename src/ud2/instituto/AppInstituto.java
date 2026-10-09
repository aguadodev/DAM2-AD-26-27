package ud2.instituto;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

public class AppInstituto {
    public static void main(String[] args) {

        Connection con = ConexionBD.conectar();

        try (Statement statement = con.createStatement();) {

            String sql = """
                    CREATE TABLE alumnos (
                      id INT PRIMARY KEY AUTO_INCREMENT,
                      nombre VARCHAR (50),
                      apellidos VARCHAR (100),
                      fecha_nacimiento DATE,
                      curso VARCHAR (100),
                      nota_media DECIMAL (4,2)
                    );
                    """;

            System.out.println(statement.executeUpdate(sql));

            sql = "INSERT INTO alumnos VALUES (1, 'Pepe', 'García', '2000-12-12', 'DAM2', NULL)";
            statement.executeUpdate(sql);
            sql = "INSERT INTO alumnos VALUES (2, 'Marta', 'Castro', '2001-2-12', 'ASIR1', NULL)";
            statement.executeUpdate(sql);
            sql = "INSERT INTO alumnos VALUES (3, 'Lolo', 'Martínez', '2002-1-12', 'DAM2', NULL)";
            statement.executeUpdate(sql);
            sql = "INSERT INTO alumnos VALUES (4, 'Lola', 'López', '2003-4-20', 'DAM2', NULL)";
            statement.executeUpdate(sql);
            sql = "INSERT INTO alumnos VALUES (5, 'Juan', 'Vázquez', '2004-5-10', 'ASIR1', NULL)";
            statement.executeUpdate(sql);           

            Alumnos.findAll();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
