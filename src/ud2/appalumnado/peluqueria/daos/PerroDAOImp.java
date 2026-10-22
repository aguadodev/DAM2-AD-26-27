package ud2.appalumnado.peluqueria.daos;

/** @author ANDRE PAZO */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ud2.peluqueria.dtos.Perro;

public class PerroDAOImp implements PerroDAO {

    private static final String SQL_FIND_ALL = "SELECT * FROM perros";
    private static final String SQL_FIND_BY_CHIP = "SELECT * FROM perros WHERE chip = ?";
    private static final String SQL_INSERT = "INSERT INTO perros (chip, nombre, raza, edad, desparasitado) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE perros SET chip =?, nombre = ?, raza = ?, edad = ?, desparasitado = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM perros WHERE chip = ?";

    private final Connection conn;

    public PerroDAOImp(Connection conn) {
        this.conn = conn;
    }

    private Perro mapearPerro(ResultSet rs) throws SQLException {
        return new Perro(
                rs.getInt("id"),
                rs.getInt("chip"),
                rs.getString("nombre"),
                rs.getString("raza"),
                rs.getInt("edad"),
                rs.getBoolean("desparasitado"));
    }

    @Override
    public void insert(Perro perro) {
        try (PreparedStatement ps = conn.prepareStatement(SQL_INSERT)) {
            ps.setInt(1, perro.getChip());
            ps.setString(2, perro.getNombre());
            ps.setString(3, perro.getRaza());
            ps.setInt(4, perro.getEdad());
            ps.setBoolean(5, perro.isDesparasitado());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error insertando perro: " + e.getMessage());
        }
    }

    @Override
    public void update(Perro perro) {
        try (PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {
            ps.setInt(1, perro.getChip());
            ps.setString(2, perro.getNombre());
            ps.setString(3, perro.getRaza());
            ps.setInt(4, perro.getEdad());
            ps.setBoolean(5, perro.isDesparasitado());
            ps.setInt(6, perro.getId());
            if (ps.executeUpdate() == 0) {
                System.err.println("No existe ningún perro con id " + perro.getId());
            }
        } catch (SQLException e) {
            System.err.println("Error actualizando perro " + perro.getId() + ": " + e.getMessage());
        }
    }

    @Override
    public void delete(int chip) {
        try (PreparedStatement ps = conn.prepareStatement(SQL_DELETE)) {
            ps.setInt(1, chip);
            if (ps.executeUpdate() == 0) {
                System.err.println("No existe ningún perro con chip " + chip);
            }
        } catch (SQLException e) {
            System.err.println("Error borrando perro " + chip + ": " + e.getMessage());
        }
    }

    @Override
    public Perro find(int chip) {
        try (PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_CHIP)) {
            ps.setInt(1, chip);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearPerro(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error buscando perro " + chip + ": " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Perro> findAll() {
        List<Perro> perros = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(SQL_FIND_ALL);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                perros.add(mapearPerro(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error listando perros: " + e.getMessage());
        }
        return perros;
    }
}
