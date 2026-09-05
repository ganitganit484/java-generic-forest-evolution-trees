package pokemon;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

/**
 *A class that manages a HashMap of Pokémon. The class includes a method that checks whether a particular Pokémon already exists in the map, another method that adds a Pokémon to the map, and a copy constructor.
 */
public class Pokedex implements Iterable <Pokemon> , Serializable { //For a class to be iterable it must implement Iterable. We made the class Serializable so that the class has the ability to save and load from a text file.
    private HashMap<String, Pokemon> pokemonMap;
    private MyForest<String> forest;


    public Pokedex(){
        this.pokemonMap = new HashMap<>();
        this.forest = new MyForest<>();
    }
    public Pokedex(Pokedex other){//Copy constructor
        this();//We will use the default constructor and create a new map into which we will copy the objects.
        for (String name : other.pokemonMap.keySet()) {//We will loop over the keys and values and add them to the empty map we created. Since the values are objects of type "pokemon.Pokemon" (a complex object), in order to perform a deep copy we will create a new object with the same values of the copied object.
            this.pokemonMap.put(name,new Pokemon(other.pokemonMap.get(name)));
        }
        for (String name : other.pokemonMap.keySet()) { //We will loop over the keys and values and add them to the empty forest we created.
            this.forest.add(name);
        }
    }
    public Pokemon getByName(String name) throws PokemonException {//A method that checks whether a particular name is in the map keys
        for (String pok_name : this.pokemonMap.keySet()) {
            if (pok_name.equals(name)) {
                return this.pokemonMap.get(name); //If one of the names received in the loop is equal to the name found in the function input, we will return the pokemon.Pokemon.
            }
        }
        throw new PokemonException(name); //If we don't reach return in the loop, it means that the key we are looking for is not in the data structure. Therefore, we will throw an error.
    }
    public void addPokemon(Pokemon toAdd) throws PokemonException {//A method whose function is to add Pokémon to the map we created
        if (toAdd != null) {
            try {
                this.getByName(toAdd.getName()); //We will use the getByname function to figure out if the key we want to add is in the data structure.
            }
            catch (PokemonException e) {//If we reach catch, it means the Pokémon is not in the data structure, so we will add the new Pokémon.
                pokemonMap.put(toAdd.getName(),toAdd.clone());//If the Pokémon we want to add is not equal to null and the key (the Pokémon's name) is not in the map keys, we will add it using the clone method in the relevant class (since we do not know whether the Pokémon we want to add is normal or special and both classes have clone, the method from the appropriate class will be chosen)
                this.forest.add(toAdd.getName());
                return;//The return ensures that we don't continue after we've added the pokemon.Pokemon to the data structure and actually don't get to the error being raised.
            }
            throw new PokemonException(toAdd.getName()); //If it is, then we will not reach the catch and continue with the code to throw the error (we don't want to add someone who already exists).
        }
    }
    public void addPokemon(Pokemon toAdd, String parent) throws PokemonException {
        if (toAdd != null) {
            try {
                this.getByName(toAdd.getName()); //We will use the getByname function to figure out if the key we want to add is in the data structure.
            } catch (PokemonException e) {//If we reach catch, it means the Pokémon is not in the data structure.
                if (parent == null) {//If the Pokemon's parent is null we will add it as the root of a new tree
                    this.forest.add(toAdd.getName());
                } else if (this.forest.exists(parent)) {//If the parent is not null, we check if it exists in the forest. If so, we add the child to the appropriate tree.
                    this.forest.add(parent,toAdd.getName());
                } else {
                    throw new PokemonException(parent);//If the parent does not exist and is not null, we will throw an appropriate exception.
                }
                return;
            }
            throw new PokemonException(toAdd.getName()); //If it is, then we will not reach the catch and continue with the code to throw the error (we don't want to add someone who already exists).
        }
    }
    public MyTree<String> getEvolutionTreeByName(String name){//A method that returns the evolution tree of a Pokémon (the method uses a method from the Forest class)
        return this.forest.getTree(name);
    }


    @Override
    public Iterator<Pokemon> iterator() {//A method that returns an iterator over the values in a HashMap
        return pokemonMap.values().iterator();//When we write pokemonMap.values() we get a collection of all the values in the HashMap. When we write iterator() it iterates over each value in the collection allowing us to return the pokemon.Pokemon.
    }
}
