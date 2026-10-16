package ud2.instituto;

import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlumnoDAO {
    public static List<AlumnoDTO> findAll() {
        List<AlumnoDTO> alumnos = null;
        Connection con = ConexionBD.conectar();

        try {
            Statement sentenciaSQL = con.createStatement();
            ResultSet rs = sentenciaSQL.executeQuery("SELECT * FROM alumnos");
            alumnos = new ArrayList<>();
            while (rs.next()) {
                alumnos.add(new AlumnoDTO(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getDate("fecha_nacimiento").toLocalDate(),
                        rs.getString("curso"),
                        rs.getDouble("nota_media")));
            }
            rs.close();
            sentenciaSQL.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alumnos;
    }

    public static boolean insertar(AlumnoDTO alumno) {

        Connection con = ConexionBD.conectar();
        String sql = """
                INSERT INTO alumnos
                VALUES
                (?, ?, ?, ?, ?, ?)
                """;
        try {
            PreparedStatement sentenciaSQL = con.prepareStatement(sql);
            sentenciaSQL.setInt(1, alumno.getId());
            sentenciaSQL.setString(2, alumno.getNombre());
            sentenciaSQL.setString(3, alumno.getApellidos());
            sentenciaSQL.setDate(4, Date.valueOf(alumno.getFechaNacimiento()));
            sentenciaSQL.setString(5, alumno.getCurso());
            sentenciaSQL.setDouble(6, alumno.getNotaMedia());
            return sentenciaSQL.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean borrar(int id) {
        Connection con = ConexionBD.conectar();
        String sql = """
                DELETE FROM alumnos
                WHERE ID = ?
                """;
        try {
            PreparedStatement sentenciaSQL = con.prepareStatement(sql);
            sentenciaSQL.setInt(1, id);
            return sentenciaSQL.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean borrar(AlumnoDTO alumno) {
        Connection con = ConexionBD.conectar();
        String sql = """
                DELETE FROM alumnos
                WHERE ID = ?
                """;
        try {
            PreparedStatement sentenciaSQL = con.prepareStatement(sql);
            sentenciaSQL.setInt(1, alumno.getId());
            return sentenciaSQL.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static AlumnoDTO find(AlumnoDTO alumno) {
        AlumnoDTO alumnoRes = null;
        Connection con = ConexionBD.conectar();
        String sql = """
                SELECT * FROM alumnos
                WHERE ID = ?
                """;
        try {
            PreparedStatement sentenciaSQL = con.prepareStatement(sql);
            sentenciaSQL.setInt(1, alumno.getId());
            ResultSet rs = sentenciaSQL.executeQuery();
            if (rs.next()) {
                alumnoRes = new AlumnoDTO(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getDate("fecha_nacimiento").toLocalDate(),
                        rs.getString("curso"),
                        rs.getDouble("nota_media"));
            }
            rs.close();
            sentenciaSQL.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alumnoRes;

    }


    public static boolean actualizar(AlumnoDTO alumno) {
        Connection con = ConexionBD.conectar();
        String sql = """
                UPDATE alumnos
                SET nombre = ?,
                    apellidos = ?,
                    fecha_nacimiento = ?,
                    curso = ?,
                    nota_media = ?
                WHERE ID = ?
                """;
        try {
            PreparedStatement sentenciaSQL = con.prepareStatement(sql);
            sentenciaSQL.setString(1, alumno.getNombre());
            sentenciaSQL.setString(2, alumno.getApellidos());
            sentenciaSQL.setDate(3, Date.valueOf(alumno.getFechaNacimiento()));
            sentenciaSQL.setString(4, alumno.getCurso());
            sentenciaSQL.setDouble(5, alumno.getNotaMedia());
            sentenciaSQL.setInt(6, alumno.getId());            
            return sentenciaSQL.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }




    /*
     * MÉTODOS SIN PATROÓN DTO
     */
    public static boolean insertar(int id, String nombre, String apellidos, LocalDate fechaNacimiento, String curso,
            Double notaMedia) {

        Connection con = ConexionBD.conectar();
        String sql = """
                INSERT INTO alumnos
                VALUES
                (?, ?, ?, ?, ?, ?)
                """;
        try {
            PreparedStatement sentenciaSQL = con.prepareStatement(sql);
            sentenciaSQL.setInt(1, id);
            sentenciaSQL.setString(2, nombre);
            sentenciaSQL.setString(3, apellidos);
            sentenciaSQL.setDate(4, Date.valueOf(fechaNacimiento));
            sentenciaSQL.setString(5, curso);
            sentenciaSQL.setDouble(6, notaMedia);
            return sentenciaSQL.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void findAllOld() {
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
