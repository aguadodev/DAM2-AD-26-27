package ud2.appalumnado.videojuegos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class VideojuegosDAOImpl implements VideojuegoDAO {
    Connection con;

    public VideojuegosDAOImpl(Connection con) {
        this.con = con;
    }

    @Override
    public void crearBD() {
        try (Statement stm = con.createStatement();) {

            String crearBaseDatos;
            // String sql;
            crearBaseDatos = """
                    CREATE TABLE IF NOT EXISTS videojuegos(
                    id INTEGER PRIMARY KEY AUTO_INCREMENT,
                    nombre VARCHAR(50),
                    fechaLanzamiento DATE,
                    precio DECIMAL)
                    """;
            stm.executeUpdate(crearBaseDatos);
            /*
             * sql =
             * "INSERT INTO videojuegos VALUES(1, 'God of War Ragnarok', '2019-9-19', '59.99')"
             * ;
             * stm.executeUpdate(sql);
             * sql =
             * "INSERT INTO videojuegos VALUES(2, 'Grand Theft Auto V', '2013-9-17', '59.99')"
             * ;
             * stm.executeUpdate(sql);
             * sql =
             * "INSERT INTO videojuegos VALUES(3, 'Rocket League', '2015-7-7', '0.00')";
             * stm.executeUpdate(sql);
             * sql =
             * "INSERT INTO videojuegos VALUES(4, 'Counter Strike 2', '2023-9-27', '0.00')";
             * stm.executeUpdate(sql);
             * sql =
             * "INSERT INTO videojuegos VALUES(5, 'Phasmopobia', '2020-9-18', '19.99')";
             * stm.executeUpdate(sql);
             * sql =
             * "INSERT INTO videojuegos VALUES(6, 'Call of Duty Black Ops 7', '2025-11-14', '79.99')"
             * ;
             * stm.executeUpdate(sql);
             */
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<VideojuegoDTO> findAll() {
        List<VideojuegoDTO> juegos = new ArrayList<>();
        String sql = """
                SELECT * FROM videojuegos
                """;
        try (PreparedStatement ps = con.prepareStatement(sql);) {

            ResultSet rs = ps.executeQuery();
            System.out.println("------Juegos------");
            while (rs.next()) {
                juegos.add(new VideojuegoDTO(rs.getInt("id"), rs.getString("nombre"),
                        rs.getDate("fechaLanzamiento").toLocalDate(), rs.getDouble("precio")));
            }
            return juegos;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public VideojuegoDTO findById(int id) {
        VideojuegoDTO juego = null;
        try (PreparedStatement ps = con.prepareStatement("SELECT * FROM videojuegos WHERE id = ?")) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                juego = new VideojuegoDTO(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDate("fechaLanzamiento").toLocalDate(),
                        rs.getDouble("precio"));
            }
            return juego;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean update(VideojuegoDTO v) {
        try (PreparedStatement ps = con.prepareStatement(
                "UPDATE videojuegos SET nombre = ?, fechaLanzamiento = ?, precio = ? WHERE id = ?")) {
            ps.setString(1, v.getNombre());
            ps.setDate(2, Date.valueOf(v.getFechaLanzamiento()));
            ps.setDouble(3, v.getPrecio());
            ps.setInt(4, v.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(VideojuegoDTO v) {
        try (PreparedStatement ps = con.prepareStatement("DELETE FROM videojuegos WHERE id = ?")) {
            ps.setInt(1, v.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean insert(VideojuegoDTO v) {
        try (PreparedStatement ps = con.prepareStatement("INSERT INTO videojuegos VALUES(?,?,?)")) {
            ps.setString(1, v.getNombre());
            ps.setDate(2, Date.valueOf(v.fechaLanzamiento));
            ps.setDouble(3, v.getPrecio());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* public void insertarJuegos() {
        try {
            try {
                con.setAutoCommit(false);
                // PreparedStatement sp = conn.prepareStatement(null);
            } catch (SQLException e) {
                con.rollback();
            } finally {
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    } */

}
