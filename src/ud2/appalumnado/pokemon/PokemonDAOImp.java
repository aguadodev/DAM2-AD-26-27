package ud2.appalumnado.pokemon;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PokemonDAOImp implements PokemonDAO {

    Connection con = ConexionBD.getConnection();

    public PokemonDAOImp(Connection con) {
        this.con = con;
    }

    @Override
    public List<PokemonDTO> getAllPokemon() {
        List<PokemonDTO> pokemons = new ArrayList<>();

        try (Statement stm = con.createStatement();
                ResultSet rs = stm.executeQuery("SELECT * FROM POKEMON;")) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String region = rs.getString("region");
                String tipo = rs.getString("tipo");
                LocalDate captura = rs.getDate("captura").toLocalDate();
                Double peso = rs.getDouble("peso");
                pokemons.add(new PokemonDTO(id, nombre, region, tipo, peso, captura));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pokemons;
    }

    @Override
    public boolean insertar(PokemonDTO pokemon) {
        String sql = """
                    INSERT INTO POKEMON (nombre, region, tipo, peso, captura) VALUES (?,?,?,?,?);
                """;

        try (PreparedStatement sentenciaSQL = con.prepareStatement(sql)) {
            sentenciaSQL.setString(1, pokemon.nombre);
            sentenciaSQL.setString(2, pokemon.region);
            sentenciaSQL.setString(3, pokemon.tipo);
            sentenciaSQL.setDouble(4, pokemon.peso);
            sentenciaSQL.setDate(5, Date.valueOf(pokemon.getCaptura()));

            return sentenciaSQL.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean actualizar(PokemonDTO pokemon) {
        String sql = """
                UPDATE POKEMON 
                SET nombre = ?,
                    tipo = ?,
                    region = ?,
                    peso = ?,
                    captura = ?
                WHERE id = ?
                """;
        try (PreparedStatement sentenciaSQL = con.prepareStatement(sql)) {
            sentenciaSQL.setString(1, pokemon.nombre);
            sentenciaSQL.setString(2, pokemon.tipo);
            sentenciaSQL.setString(3, pokemon.region);
            sentenciaSQL.setDouble(4, pokemon.peso);
            sentenciaSQL.setDate(5, Date.valueOf(pokemon.captura));
            sentenciaSQL.setInt(6, pokemon.npokedex);
            return sentenciaSQL.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new UnsupportedOperationException("Unimplemented method 'actualizar'");
    }

    @Override
    public boolean eliminar(PokemonDTO pokemon) {
        String sql = """
                DELETE FROM POKEMON WHERE id = ?;
                """;
        try (PreparedStatement sentenciaSQL = con.prepareStatement(sql)) {
            sentenciaSQL.setInt(1, pokemon.getNpokedex());
            return sentenciaSQL.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new UnsupportedOperationException("Unimplemented method 'eliminar'");
    }

    @Override
    public boolean getPokemon(int id) {
        throw new UnsupportedOperationException("Unimplemented method 'getPokemon'");
    }

    @Override
    public PokemonDTO getPokemonById(int idPokemon) {
        PokemonDTO p = null;

        try (PreparedStatement sentenciaSQL = con.prepareStatement("SELECT * FROM POKEMON WHERE id = ?")) {
            sentenciaSQL.setInt(1, idPokemon);

            try (ResultSet rs = sentenciaSQL.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    String region = rs.getString("region");
                    String tipo = rs.getString("tipo");
                    LocalDate captura = rs.getDate("captura").toLocalDate();
                    Double peso = rs.getDouble("peso");
                    p = new PokemonDTO(id, nombre, region, tipo, peso, captura);
                }
                return p;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new UnsupportedOperationException("Unimplemented method 'getPokemonById'");
    }
}