package ud2.appalumnado.instituto.dao;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ud2.appalumnado.instituto.dto.AlumnoConCursoDTO;
import ud2.appalumnado.instituto.dto.AlumnoDTO;
import ud2.appalumnado.instituto.dto.CursoDTO;
import ud2.appalumnado.instituto.excepciones.DataAccessException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlumnoDAOImpl implements AlumnoDAO {
    private Connection con;

    public AlumnoDAOImpl(Connection con) {
        this.con = con;
    }

    public List<AlumnoDTO> listarTodos() {
        List<AlumnoDTO> alumnos = null;

        String sql = "SELECT * FROM alumnos";
        try (Statement sentenciaSQL = con.createStatement();
                ResultSet rs = sentenciaSQL.executeQuery(sql);) {

            alumnos = new ArrayList<>();
            while (rs.next()) {
                alumnos.add(new AlumnoDTO(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        null, // TODO rs.getDate("fecha_nacimiento").toLocalDate(),
                        rs.getString("email"),
                        rs.getDouble("nota_media"),
                        rs.getInt("curso_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alumnos;
    }

    public int insertar(AlumnoDTO alumno) {
        String sql = """
                INSERT INTO alumnos
                (nombre, apellidos, fecha_nacimiento, email, nota_media, curso_id)
                VALUES
                (?, ?, ?, ?, ?, ?)
                """;
        try (PreparedStatement sentenciaSQL = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            sentenciaSQL.setString(1, alumno.getNombre());
            sentenciaSQL.setString(2, alumno.getApellidos());
            sentenciaSQL.setDate(3, Date.valueOf(alumno.getFechaNacimiento()));
            sentenciaSQL.setString(4, alumno.getEmail());
            sentenciaSQL.setDouble(5, alumno.getNotaMedia());
            sentenciaSQL.setInt(6, alumno.getCurso());
            sentenciaSQL.executeUpdate();
            ResultSet rs = sentenciaSQL.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);

        } catch (SQLException e) {
            throw new DataAccessException(
                "Error al insertar alumno: ", e);
        }
    }

    public boolean eliminar(int id) {
        String sql = """
                DELETE FROM alumnos
                WHERE ID = ?
                """;
        try (PreparedStatement sentenciaSQL = con.prepareStatement(sql);) {
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
        try (PreparedStatement sentenciaSQL = con.prepareStatement(sql);) {

            sentenciaSQL.setInt(1, id);

            ResultSet rs = sentenciaSQL.executeQuery();
            if (rs.next()) {
                alumnoRes = new AlumnoDTO(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        null, //rs.getDate("fecha_nacimiento").toLocalDate(),
                        rs.getString("email"),
                        rs.getDouble("nota_media"),
                        rs.getInt("curso_id"));
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
                    email = ?,
                    nota_media = ?,
                    curso_id = ?
                WHERE ID = ?
                """;
        try (PreparedStatement sentenciaSQL = con.prepareStatement(sql);) {
            sentenciaSQL.setString(1, alumno.getNombre());
            sentenciaSQL.setString(2, alumno.getApellidos());
            sentenciaSQL.setDate(3, Date.valueOf(alumno.getFechaNacimiento()));
            sentenciaSQL.setString(4, alumno.getEmail());
            sentenciaSQL.setDouble(5, alumno.getNotaMedia());
            sentenciaSQL.setInt(6, alumno.getCurso());
            sentenciaSQL.setInt(7, alumno.getId());
            return sentenciaSQL.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void matricularAlumnos(List<AlumnoDTO> alumnos) {
        try {
            con.setAutoCommit(false);

            // Ejecutar sentencias de la transacción
            for (AlumnoDTO alumnoDTO : alumnos) {
                if (insertar(alumnoDTO) >= 0)
                    throw new SQLException();
            }

            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
            }
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public AlumnoConCursoDTO buscarAlumnoConCurso(int alumnoId) {
        AlumnoDTO alumno = buscarPorId(alumnoId);
        CursoDAO cursoDAO = new CursoDAOImpl(con);
        CursoDTO curso = cursoDAO.buscarPorId(alumno.getCurso());
        return new AlumnoConCursoDTO(alumnoId, alumno.getNombre(), curso.getId(), curso.getNombre());
    }

    @Override
    public List<AlumnoDTO> buscarPorCurso(int cursoId) {
        List<AlumnoDTO> alumnosPorCurso =  new ArrayList<>();

        for (AlumnoDTO alumnoDTO : listarTodos()) {
            if (alumnoDTO.getCurso() == cursoId) 
                alumnosPorCurso.add(alumnoDTO);
        }

        return alumnosPorCurso;
    }
}
