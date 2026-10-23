package ud2.appalumnado.clientes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
/**
 * @author Adrián Gómez
 */
public class ClienteDAOImpl implements ClienteDAO {
    public List<ClienteDTO> listarTodos() {
        List<ClienteDTO> clientes = null;

        try (Connection conn = JbdcUtil.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM clientes")) {

            clientes = new ArrayList<>();
            while (rs.next()) {
                clientes.add(new ClienteDTO(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getDate("fecha_alta").toLocalDate(),
                        rs.getBoolean("vip")));
            }

            return clientes;

        } catch (SQLException e) {
            System.out.println("Error de sql: " + e.getMessage());
            return null;
        }
    }

    public boolean insertar(ClienteDTO cliente) {

        String sql = """
                INSERT INTO clientes (nombre, apellidos, fecha_alta, vip)
                VALUES (?, ?, ?, ?)
                """;
        try (Connection con = JbdcUtil.getConnection();
                PreparedStatement sentenciaSql = con.prepareStatement(sql)) {

            sentenciaSql.setString(1, cliente.getNombre());
            sentenciaSql.setString(2, cliente.getApellidos());
            sentenciaSql.setDate(3, Date.valueOf(cliente.getFechaAlta()));
            sentenciaSql.setBoolean(4, cliente.isVip());
            return sentenciaSql.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error de sql en insertar: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = """
                DELETE FROM clientes
                WHERE ID = ?
                """;
        try (Connection con = JbdcUtil.getConnection();
                PreparedStatement sentenciaSql = con.prepareStatement(sql)) {

            sentenciaSql.setInt(1, id);
            return sentenciaSql.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(ClienteDTO cliente) {
        String sql = """
                DELETE FROM clientes
                WHERE ID = ?
                """;
        try (Connection con = JbdcUtil.getConnection();
                PreparedStatement sentenciaSql = con.prepareStatement(sql)) {

            sentenciaSql.setInt(1, cliente.getId());
            return sentenciaSql.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ClienteDTO buscarPorId(int id) {
        ClienteDTO clienteRes = null;

        String sql = """
                SELECT * FROM clientes
                WHERE ID = ?
                """;
        try (Connection con = JbdcUtil.getConnection();
                PreparedStatement sentenciaSql = con.prepareStatement(sql)) {

            sentenciaSql.setInt(1, id);
            ResultSet rs = sentenciaSql.executeQuery();
            if (rs.next()) {
                clienteRes = new ClienteDTO(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getDate("fecha_alta").toLocalDate(),
                        rs.getBoolean("vip"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clienteRes;
    }

    public boolean actualizar(ClienteDTO cliente) {

        String sql = """
                UPDATE clientes
                SET nombre = ?,
                    apellidos = ?,
                    fecha_alta = ?,
                    vip = ?
                    WHERE ID = ?
                """;
        try (Connection con = JbdcUtil.getConnection();
                PreparedStatement sentenciaSql = con.prepareStatement(sql)) {

            sentenciaSql.setString(1, cliente.getNombre());
            sentenciaSql.setString(2, cliente.getApellidos());
            sentenciaSql.setDate(3, Date.valueOf(cliente.getFechaAlta()));
            sentenciaSql.setBoolean(4, cliente.isVip());
            sentenciaSql.setInt(5, cliente.getId());
            return sentenciaSql.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void insertarClientes(List<ClienteDTO> clientes) {
        try (Connection con = JbdcUtil.getConnection()) {
            try {
                con.setAutoCommit(false);

                String sql = """
                        INSERT INTO clientes (nombre, apellidos, fecha_alta, vip)
                        VALUES (?, ?, ?, ?)
                        """;
                PreparedStatement sentenciaSql = con.prepareStatement(sql);

                for (ClienteDTO c : clientes) {
                    sentenciaSql.setString(1, c.getNombre());
                    sentenciaSql.setString(2, c.getApellidos());
                    sentenciaSql.setDate(3, Date.valueOf(c.getFechaAlta()));
                    sentenciaSql.setBoolean(4, c.isVip());
                    sentenciaSql.setInt(5, c.getId());
                    sentenciaSql.executeUpdate();
                }

                con.commit();

            } catch (SQLException e) {
                e.printStackTrace();
                con.rollback();
            } finally {
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
