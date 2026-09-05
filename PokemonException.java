package pokemon;

/**
 *A class that contains two errors that are raised when working with objects of the Pokémon class and its descendants, and in the pokemon.Pokedex class.
 */

public class PokemonException extends Exception {

    public PokemonException(String pokemonName) {
        super("bad name: " + pokemonName);
    }

    public PokemonException(int parameter) {
        super("bad parameter: " + parameter);
    }
}
