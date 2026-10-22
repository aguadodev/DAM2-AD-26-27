package ud2.appalumnado.canciones;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CancionDAO2 implements CancionDAOInterfaz2 {
    Connection conn;

    public CancionDAO2(Connection conn) {
        this.conn = conn;
    }

    public boolean insertarCancion(CancionDTO3 cancion) {
        String sql = """
                    INSERT INTO canciones (nombre,genero,fPublicacion,visualizaciones,duracion) VALUES (?,?,?,?,?)
                """;

        try (PreparedStatement pStatement = conn.prepareStatement(sql);) {

            pStatement.setString(1, cancion.getNombre());   //empieza en 1 no como arrays o asi que empieza a contar en 0
            pStatement.setString(2, cancion.getGenero());
            pStatement.setDate(3, Date.valueOf(cancion.getfPublicacion()));
            pStatement.setInt(4, cancion.getVisualizaciones());
            pStatement.setDouble(5, cancion.getDuracion());
            return pStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<CancionDTO3> findAll() {
        String sql = "SELECT * FROM canciones";
        List<CancionDTO3> canciones = new ArrayList<>();
        try (PreparedStatement pStatement = conn.prepareStatement(sql);
                ResultSet rs = pStatement.executeQuery();) {

            while (rs.next()) {
                CancionDTO3 cancion = new CancionDTO3(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("genero"),
                        rs.getDate("fPublicacion").toLocalDate(),
                        rs.getInt("visualizaciones"),
                        rs.getDouble("duracion"));

                canciones.add(cancion);
            }

            return canciones;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public CancionDTO3 find(int idCancion) {
        CancionDTO3 cancion = null;
        String sql = """
                    SELECT * FROM canciones WHERE id=?
                """;
        try (PreparedStatement pStatement = conn.prepareStatement(sql);) {

            pStatement.setInt(1, idCancion);

            ResultSet rs = pStatement.executeQuery();
            if (rs.next()) {
                cancion = new CancionDTO3(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("genero"),
                        rs.getDate("fPublicacion").toLocalDate(),
                        rs.getInt("visualizaciones"),
                        rs.getDouble("duracion"));
            }
            rs.close();

            return cancion;
        } catch (SQLException e) {
            e.printStackTrace();
            return cancion;
        }

    }

    public boolean actualizar(CancionDTO3 cancion) {
        String sql = """
                    UPDATE canciones SET visualizaciones=? WHERE id=?
                """;
        try (PreparedStatement pStatement = conn.prepareStatement(sql);) {

            pStatement.setInt(1, cancion.getVisualizaciones());
            pStatement.setInt(2, cancion.getId());
            return pStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean actualizar(int id, int visualizacionesNvas) {
        String sql = """
                    UPDATE canciones SET visualizaciones=? WHERE id=?
                """;
        try (PreparedStatement pStatement = conn.prepareStatement(sql);) {

            CancionDTO3 cancAux = this.find(id);    //le anado las visualizaciones nuevas (AL ANADIRLE EL DATO A LA BD SE LA ANADE AUTOMATICAMNETE TAMBIEN AL OBJETO)
            visualizacionesNvas = cancAux.getVisualizaciones()+visualizacionesNvas;

            pStatement.setInt(1, visualizacionesNvas);
            pStatement.setInt(2, id);
            return pStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean borrar(int idCancion) {
        String sql = """
                    DELETE FROM canciones WHERE id=?
                """;
        try (PreparedStatement pStatement = conn.prepareStatement(sql);) {

            pStatement.setInt(1, idCancion);
            return pStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void insertarVariasCanciones(List<CancionDTO3> listaCanciones) { 
        //transaccion
        try {
            conn.setAutoCommit(false);  //desactivar el Auto-Commit

            for (CancionDTO3 cancion : listaCanciones) {
                if (!insertarCancion(cancion)) {
                    throw new SQLException();
                }
            }
            conn.commit();  //aplicar manualmente todos los cambios hechos
        } catch (SQLException e) {
            try {
                conn.rollback();    //si algo falla no se hace nada
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);   //al acabar sea lo que sea activar de vuelta el Auto-Commit
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        
    }
}
