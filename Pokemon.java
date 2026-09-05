package pokemon;

import java.io.Serializable;

/**
 * The class represents a single Pokémon, includes a default constructor that defines default values for each of the fields, a copy constructor,a clone method that copies an object and returns a new copy of it, a method that overrides the equals method and verifies that the two objects are equal, and getters and setters for each of the fields.
 */
public class Pokemon implements Serializable { //We made the class Serializable because the class instances are used as values in the HashMap found in the pokemon.Pokedex class, which we made Serializable.
    private String name;
    private int attack;
    private int defense;
    private int health;
    private Type type;

    public Pokemon() { //Default constructor
        this.name = "Magikarp";
        this.attack = 10;
        this.defense = 55;
        this.health = 20;
        this.type = new Type("Water");
    }
    public Pokemon(String name, Type type, int attack, int defense, int health) throws PokemonException{//General constructor
        if (name==null){
            this.name = "Pikachu";
            throw new PokemonException("Empty");
        }
        else {setName(name);}
        help_method(name, attack, defense, health);//Called to the method responsible for throwing errors if invalid values were entered in the constructor.
        setAttack(attack); //In order to initialize the fields with correct values, we will use the setters, which verify that the values are correct and, if necessary, set default values.
        setDefense(defense);
        setHealth(health);
        if (type == null) {
            this.type = new Type("Water");//If the pokemon.Type field contains a null value, we will create a new object of type "pokemon.Type" with the value "water"
        }
        else {setType(type);}

    }
    public Pokemon (Pokemon other) {//Copy constructor
        this();
        if (other != null) {
            name = other.getName();
            attack = other.getAttack();
            defense = other.getDefense();
            health = other.getHealth();
            type = other.getType();//Because the type field contains a complex object, in order for the copy to be deep, we will need to create a new place for it in memory(this happens in the copy constructor.).
        }
    }
    public String getName(){
        return name;
    }
    public void setName(String name) throws PokemonException {
        if (name == null) {
            this.name = "Pikachu";
        }
        else {this.name = name;}
    }
    public Type getType(){
        return new Type(type.getType());//Because the type field contains a complex object, in order for the copy to be deep, we will need to create a new place for it in memory.
    }
    public void setType(Type type) {
        if (type == null) {
            this.type = new Type("Water");//If the pokemon.Type field contains a null value, we will create a new object of type "pokemon.Type" with the value "water"
        }
        else {this.type = type;}
    }
    public int getAttack(){
        return attack;
    }
    public void setAttack (int attack) {//If the attack points entered for the Pokémon are less than 0, we will set them to the minimum value (0).
        if (attack>=0){
            this.attack = attack;
        }
        else {
            this.attack = 0;
        }
    }
    public int getDefense(){
        return defense;
    }
    public void setDefense(int defense)  {//If the defense points entered for the Pokémon are less than 0, we will set them to the minimum value (0).
        if (defense>=0){
            this.defense = defense;
        }
        else {
            this.defense = 0;
        }
    }
    public int getHealth(){
        return health;
    }
    public void setHealth(int health)  {//If the life points entered for the pokemon.Pokemon are less than 1, we will set them to the minimum value (1).
        if (health>=1){
            this.health = health;
        }
        else {
            this.health = 1;
        }
    }
    public int calcAttack(){
        return this.attack;

    }
    @Override
    /**
     * A method that checks whether two Pokémon are equal in their fields and type.
     */
    public boolean equals(Object obj) {
        if (this == obj) {//If two objects have the same memory location, they are the same object, so of course it is equal to itself.
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {//If we try to compare a pokemon.Pokemon with an object that has a null value or the objects have a different class, we will return false.We did not use instanceof here because the pokemon.Pokemon class is a parent class, which could lead to incorrect comparisons when comparing an object belonging to the pokemon.Pokemon class with an object belonging to the inheriting class, "Special pokemon.Pokemon".
            return false;
        }
        return name.equals(((Pokemon) obj).getName()) && attack == ((Pokemon) obj).getAttack() && defense == ((Pokemon) obj).getDefense() && health == ((Pokemon) obj).getHealth() && type.getType().equals(((Pokemon) obj).getType().getType());//If the two previous conditions are not met, it remains to compare the fields that exist in the two objects and if they are equal, we will return true.
    }
    @Override
    /**
     * A method that will be used in the pokemon.Pokedex class, when we want to add a copy of a Pokémon but we don't know whether it is a regular or special Pokémon. In order for the copy to be done correctly, we added the clone method to both the regular and special Pokémon, which is responsible for copying the object correctly.
     */
    public Pokemon clone() {
        Pokemon cloned = new Pokemon();
        cloned.name = this.name;
        cloned.attack = this.attack;
        cloned.defense = this.defense;
        cloned.health = this.health;
        cloned.type = this.getType();
        return cloned; //We will return the cloned pokemon.Pokemon to which we copied all the pokemon.Pokemon field values.
    }
    protected void help_method (String name, int attack, int defense, int health) throws PokemonException {//A method that is responsible for throwing errors if the values entered into the constructor are not valid according to the settings we defined in set.
        if (name == null && ((health <1) || (attack <0) || (defense <0))) {
            throw new PokemonException("Empty");
        } else if (name == null) {
            throw new PokemonException("Empty");
        } else if (health <1) {
            throw new PokemonException(health);
        } else if (attack <0) {
            throw new PokemonException(attack);
        } else if (defense <0) {
            throw new PokemonException(defense);
        }
    }
}
