package ud2.appalumnado.gimnasio;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EjercicioDAOImpl implements EjercicioDAO {

    Connection con;

    public EjercicioDAOImpl(Connection con) {
        this.con = con;
    }

    @Override 
    public boolean insertar(EjercicioDTO ejercicio) {
        String sql = """
                INSERT INTO ejercicio
                    (nombre, musculosInvolucrados, series, repeticiones)
                VALUES
                    (?, ?, ?, ?)
                """;
        ;

        try (PreparedStatement sentenciaSQL = con.prepareStatement(sql)) {

            sentenciaSQL.setString(1, ejercicio.getNombre());
            sentenciaSQL.setString(2, ejercicio.getMusculosInvolucrados());
            sentenciaSQL.setInt(3, ejercicio.getSeries());
            sentenciaSQL.setInt(4, ejercicio.getRepeticiones());

            return sentenciaSQL.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override 
    public EjercicioDTO buscarPorId(int id) {
        EjercicioDTO ejercicio = null;

        String sql = """
                SELECT * FROM ejercicio
                WHERE id = ?
                """;

        try (PreparedStatement sentenciaSQL = con.prepareStatement(sql)) {

            sentenciaSQL.setInt(1, id);

            ResultSet rs = sentenciaSQL.executeQuery();

            if (rs.next()) {
                ejercicio = new EjercicioDTO(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("musculosInvolucrados"),
                        rs.getInt("series"),
                        rs.getInt("repeticiones"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ejercicio;
    }

    @Override
    public List<EjercicioDTO> listarTodos() {
        List<EjercicioDTO> ejercicios = new ArrayList<>();

        String sql = "SELECT * FROM ejercicio";

        try (Statement sentenciaSQL = con.createStatement();
                ResultSet rs = sentenciaSQL.executeQuery(sql)) {

            while (rs.next()) {
                ejercicios.add(new EjercicioDTO(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("musculosInvolucrados"),
                        rs.getInt("series"),
                        rs.getInt("repeticiones")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ejercicios;

    }

    @Override
    public boolean eliminar(int id) {
        String sql = """
                DELETE FROM ejercicio
                WHERE id = ?
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
    public boolean actualizar(EjercicioDTO ejercicio) {
        String sql = """
                UPDATE ejercicio
                SET nombre = ?,
                    musculosInvolucrados = ?,
                    series = ?,
                    repeticiones = ?
                WHERE ID = ?
                """;

        try (PreparedStatement sentenciaSQL = con.prepareStatement(sql);) {
            sentenciaSQL.setString(1, ejercicio.getNombre());
            sentenciaSQL.setString(2, ejercicio.getMusculosInvolucrados());
            sentenciaSQL.setInt(3, ejercicio.getSeries());
            sentenciaSQL.setInt(4, ejercicio.getRepeticiones());
            sentenciaSQL.setInt(5, ejercicio.getId());    

            return sentenciaSQL.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}