package ud2.appalumnado.pokemon;

import java.util.List;

public interface PokemonDAO {

    public List<PokemonDTO> getAllPokemon();

    public PokemonDTO getPokemonById(int id);

    public boolean insertar(PokemonDTO pokemon);

    public boolean actualizar(PokemonDTO pokemon);

    public boolean eliminar(PokemonDTO pokemon);

    public boolean getPokemon(int id);

}