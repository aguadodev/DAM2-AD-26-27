package ud2.appalumnado.restaurante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlatoDAOImpl implements PlatoDAO {

    private Connection con;

    
    public PlatoDAOImpl(Connection con) {
        this.con = con;
    }

    @Override
    public void crearTabla() {
        String sql = """
                    CREATE TABLE IF NOT EXISTS platos (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        nombre VARCHAR(50) NOT NULL,
                        precio DECIMAL(5, 2) NOT NULL,
                        categoria VARCHAR(50) NOT NULL,
                        disponible BOOLEAN
                    )
                """;

        try (Statement sentenciaSQL = con.createStatement()) {
            sentenciaSQL.execute(sql);

            System.out.println("Tabla 'platos' creada o verificada.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PlatoDTO> listarTodos() {

        List<PlatoDTO> platos = null;

        String sql = "SELECT * FROM platos";

        try (Statement sentenciaSQL = con.createStatement();
                ResultSet rs = sentenciaSQL.executeQuery(sql)) {

            platos = new ArrayList<>();
            
            while (rs.next()) {
                platos.add(new PlatoDTO(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getString("categoria"),
                        rs.getBoolean("disponible")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return platos;
    }

    @Override
    public boolean insertar(PlatoDTO plato) {

        String sql = """
                    INSERT INTO platos
                    (nombre, precio, categoria, disponible)
                    VALUES
                    (?, ?, ?, ?)
                """;

        try (PreparedStatement sentenciaSQL = con.prepareStatement(sql)) {

            sentenciaSQL.setString(1, plato.getNombre());
            sentenciaSQL.setDouble(2, plato.getPrecio());
            sentenciaSQL.setString(3, plato.getCategoria());
            sentenciaSQL.setBoolean(4, plato.isDisponible());

            return sentenciaSQL.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {

        String sql = """
                    DELETE FROM platos
                    WHERE ID = ?
                """;

        try (PreparedStatement sentenciaSQL = con.prepareStatement(sql)) {

            sentenciaSQL.setInt(1, id);

            return sentenciaSQL.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }

    @Override
    public PlatoDTO buscarPorId(int id) {

        PlatoDTO platoRes = null;

        String sql = """
                    SELECT * FROM platos
                    WHERE ID = ?
                """;

        try (PreparedStatement sentenciaSQL = con.prepareStatement(sql)) {

            sentenciaSQL.setInt(1, id);
            
            ResultSet rs = sentenciaSQL.executeQuery();

            if (rs.next()) {
                platoRes = new PlatoDTO(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getString("categoria"),
                        rs.getBoolean("disponible"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return platoRes;
    }

    @Override
    public boolean actualizar(PlatoDTO plato) {

        String sql = """
                    UPDATE platos
                    SET nombre = ?, precio = ?, categoria = ?, disponible = ?
                    WHERE ID = ?
                """;

        try (PreparedStatement sentenciaSQL = con.prepareStatement(sql)) {
            
            sentenciaSQL.setString(1, plato.getNombre());
            sentenciaSQL.setDouble(2, plato.getPrecio());
            sentenciaSQL.setString(3, plato.getCategoria());
            sentenciaSQL.setBoolean(4, plato.isDisponible());
            sentenciaSQL.setInt(5, plato.getId());

            return sentenciaSQL.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}