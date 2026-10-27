package ud2.appalumnado.instituto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ud2.appalumnado.instituto.dto.ModuloDTO;
import ud2.appalumnado.instituto.excepciones.DataAccessException;

public class ModuloDAOImpl implements ModuloDAO  {


    private final Connection con;

    public ModuloDAOImpl(Connection con) {
        this.con = con;
    }

    @Override
    public List<ModuloDTO> listarTodos() {
        String sql = "SELECT id, nombre, horas FROM Modulos";
        List<ModuloDTO> cursos = new ArrayList<>();

        try (Statement sentenciaSQL = con.createStatement();
             ResultSet rs = sentenciaSQL.executeQuery(sql)) {

            while (rs.next()) {
                cursos.add(mapear(rs));
            }
            return cursos;

        } catch (SQLException e) {
            // Traducir y propagar, conservando la causa
            throw new DataAccessException("Error al listar todos los cursos", e);
        }
    }

    @Override
    public boolean insertar(ModuloDTO curso) {
        String sql = """
                INSERT INTO Modulos (nombre, horas)
                VALUES (?, ?)
                """;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, curso.getNombre());
            ps.setInt(2, curso.getHoras());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException(
                "Error al insertar curso: " + curso.getNombre(), e);
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM Modulos WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            // false = "no existía esa fila" (info de negocio, no error)
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Error al eliminar curso con id " + id, e);
        }
    }

    @Override
    public ModuloDTO buscarPorId(int id) {
        String sql = "SELECT id, nombre, horas FROM Modulos WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                // null = "no existe" (info de negocio legítima)
                return rs.next() ? mapear(rs) : null;
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error al buscar curso con id " + id, e);
        }
    }

    @Override
    public boolean actualizar(ModuloDTO curso) {
        String sql = """
                UPDATE Modulos
                SET nombre = ?, horas = ?
                WHERE id = ?
                """;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, curso.getNombre());
            ps.setInt(2, curso.getHoras());
            ps.setInt(3, curso.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException(
                "Error al actualizar curso con id " + curso.getId(), e);
        }
    }

    private ModuloDTO mapear(ResultSet rs) throws SQLException {
        return new ModuloDTO(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getInt("horas"));
    }

}
