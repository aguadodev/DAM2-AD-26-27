package ud2.appalumnado.instituto.dao;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ud2.appalumnado.instituto.dto.CursoDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CursoDAOImpl implements CursoDAO {
    private Connection con;

    public CursoDAOImpl(Connection con) {
        this.con = con;
    }

    public List<CursoDTO> listarTodos() {
        List<CursoDTO> cursos = null;

        String sql = "SELECT * FROM Cursos";
        try (Statement sentenciaSQL = con.createStatement();
                ResultSet rs = sentenciaSQL.executeQuery(sql);) {

            cursos = new ArrayList<>();
            while (rs.next()) {
                cursos.add(new CursoDTO(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getInt("horas")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursos;
    }

    public boolean insertar(CursoDTO Curso) {
        String sql = """
                INSERT INTO Cursos
                (nombre, horas)
                VALUES
                (?, ?)
                """;
        try (PreparedStatement sentenciaSQL = con.prepareStatement(sql);) {
            sentenciaSQL.setString(1, Curso.getNombre());
            sentenciaSQL.setInt(2, Curso.getHoras());
            return sentenciaSQL.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = """
                DELETE FROM Cursos
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

    public CursoDTO buscarPorId(int id) {
        CursoDTO CursoRes = null;

        String sql = """
                SELECT * FROM Cursos
                WHERE ID = ?
                """;
        try (PreparedStatement sentenciaSQL = con.prepareStatement(sql);) {

            sentenciaSQL.setInt(1, id);
            try (ResultSet rs = sentenciaSQL.executeQuery();) {
                if (rs.next()) {
                    CursoRes = new CursoDTO(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getInt("horas"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return CursoRes;
    }

    public boolean actualizar(CursoDTO Curso) {
        String sql = """
                UPDATE Cursos
                SET nombre = ?,
                    horas = ?
                WHERE ID = ?
                """;
        try (PreparedStatement sentenciaSQL = con.prepareStatement(sql);) {
            sentenciaSQL.setString(1, Curso.getNombre());
            sentenciaSQL.setInt(2, Curso.getHoras());
            sentenciaSQL.setInt(3, Curso.getId());
            return sentenciaSQL.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
