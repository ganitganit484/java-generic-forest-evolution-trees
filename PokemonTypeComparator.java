package pokemon;

import java.util.Comparator;

/**
 * Since the Pokémon class does not have a clear comparison, we created two classes, one of which is this, that compare two Pokémon based on different parameters. This class compares based on a comparison method we created in the pokemon.Type class.
 */

public class PokemonTypeComparator implements Comparator <Pokemon>{

    @Override
    public int compare(Pokemon o1, Pokemon o2) { //A method that compares according to the compareTo of a type and accordingly returns which of the Pokémon (if they are not equal to null) is stronger. If one is equal to null, the other is automatically considered stronger.
        if (o1 == null && o2 == null) {
            return 0;
        }
        if (o1 != null && o2 == null) {
            return 1;
        }
        if (o1 == null && o2 != null) {
            return -1;
        }
        return o1.getType().compareTo(o2.getType());
    }
}
