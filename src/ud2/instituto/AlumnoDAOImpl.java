package ud2.instituto;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AlumnoDAOImpl implements AlumnoDAO {

    public List<AlumnoDTO> listarTodos() {
        List<AlumnoDTO> alumnos = null;
        
        String sql = "SELECT * FROM alumnos";
        try (Connection con = JdbcUtil.getConnection();
             Statement sentenciaSQL = con.createStatement();
             ResultSet rs = sentenciaSQL.executeQuery(sql);) {
            
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alumnos;
    }

    public boolean insertar(AlumnoDTO alumno) {
        String sql = """
                INSERT INTO alumnos
                (nombre, apellidos, fecha_nacimiento, curso, nota_media)
                VALUES
                (?, ?, ?, ?, ?)
                """;
        try (Connection con = JdbcUtil.getConnection();
             PreparedStatement sentenciaSQL = con.prepareStatement(sql);) {
            sentenciaSQL.setString(1, alumno.getNombre());
            sentenciaSQL.setString(2, alumno.getApellidos());
            sentenciaSQL.setDate(3, Date.valueOf(alumno.getFechaNacimiento()));
            sentenciaSQL.setString(4, alumno.getCurso());
            sentenciaSQL.setDouble(5, alumno.getNotaMedia());
            return sentenciaSQL.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = """
                DELETE FROM alumnos
                WHERE ID = ?
                """;
        try (Connection con = JdbcUtil.getConnection();
             PreparedStatement sentenciaSQL = con.prepareStatement(sql);) {
            sentenciaSQL.setInt(1, id);
            return sentenciaSQL.executeUpdate() > 0;
        } catch (SQLException e) {
                e.printStackTrace();
            return false;
        }
    }

    public AlumnoDTO buscarPorId(int id) {
        AlumnoDTO alumnoRes = null;
        
        String sql = """
                SELECT * FROM alumnos
                WHERE ID = ?
                """;
        try (Connection con = JdbcUtil.getConnection();
             PreparedStatement sentenciaSQL = con.prepareStatement(sql);) {

            sentenciaSQL.setInt(1, id);

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alumnoRes;
    }

    public boolean actualizar(AlumnoDTO alumno) {
        String sql = """
                UPDATE alumnos
                SET nombre = ?,
                    apellidos = ?,
                    fecha_nacimiento = ?,
                    curso = ?,
                    nota_media = ?
                WHERE ID = ?
                """;
        try (Connection con = JdbcUtil.getConnection();
             PreparedStatement sentenciaSQL = con.prepareStatement(sql);) {
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
}
