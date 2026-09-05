package pokemon;

import java.io.*;
import java.util.Scanner;

/**
 * A class that describes the "Special Pokémon" object. The class includes a general constructor, a default constructor, a method that calculates the Pokémon's attack power, and an implementation of an equals method.
 */
public class SpecialPokemon extends Pokemon {//The class inherits from the Pokémon class.
    private int specialAttack;

    public SpecialPokemon(String name, Type type, int attack, int defense, int health, int specialAttack) throws PokemonException {//General constructor
        super(name, type, attack, defense, health); //With this line we inherit all the fields that exist in the pokemon.Pokemon class.
        this.setspecialAttack(specialAttack);//We will add the additional field that does not exist in the parent class.
    }

    public SpecialPokemon(File file) throws PokemonException {//A constructor that receives a file extracts the Pokémon's field information from it and, if they are correct, updates the fields of a specialpokemon to be equal to the details in the file.
        try (Scanner scanner = new Scanner(file)) {//We will use a new object from the Scanner class to easily navigate through the file. We will put everything inside a test because when updating the special pokemon.Pokemon fields, errors defined in the parent class can occur.
            String orig_name = null; //We will initialize the variables that will later be updated according to the contents of the file and used to update the object.
            int orig_attack = 0;
            int orig_defense = 0;
            int orig_health = 0;

            while (scanner.hasNext()) {//We will go through the lines in the file as long as there is another line.
                String next_line = scanner.nextLine();
                String[] splited = next_line.split(": ");//Every time we see ": " in a line, we will split the line at that position into 2 and create an array that contains both parts of the sentence.
                if (splited[0].equals("Name")) {//For each first part of the sentence, we will check whether it matches one of the field names we are looking for. If so, we will update the field value to be equal to the second part of the sentence.
                    orig_name = splited[1];//In addition to the fields, we will also update the variables we defined for what the file contains so that we can later check whether they meet the requirements or whether an error should be raised.
                    this.setName(splited[1]);
                } else if (splited[0].equals("pokemon.Type")) {
                    this.setType(new Type(splited[1]));
                } else if (splited[0].equals("Attack")) {
                    orig_attack = Integer.parseInt(splited[1]);
                    this.setAttack(Integer.parseInt(splited[1]));
                } else if (splited[0].equals("Defense")) {
                    orig_defense = Integer.parseInt(splited[1]);
                    this.setDefense(Integer.parseInt(splited[1]));
                } else if (splited[0].equals("Health")) {
                    orig_health = Integer.parseInt(splited[1]);
                    this.setHealth(Integer.parseInt(splited[1]));
                } else if (splited[0].equals("Special Attack")) {
                    this.setspecialAttack(Integer.parseInt(splited[1]));
                }
            }
            this.help_method(orig_name,orig_attack,orig_defense,orig_health); //A function defined in the parent class and responsible for throwing errors if one of the values that appear in the file for the fields in the class is not appropriate.
        } catch (FileNotFoundException e) {//Catches an error if the file we inserted does not exist.
            return;
        }

    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public void setspecialAttack (int specialAttack) throws PokemonException {//If the specialAttack points entered for the Pokémon are less than 0, we will set them to the minimum value (0).
        if (specialAttack>=0){
            this.specialAttack = specialAttack;
        }
        else {
            this.specialAttack = 0;
            throw new PokemonException(specialAttack);
        }
    }

    public int calcAttack() {//A method that calculates the strength of the attack
        return specialAttack * this.getAttack();

    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof SpecialPokemon) {//Here, instanceof can be used because the class does not have a parent class, and thus there cannot be a problem where two objects from different classes are considered equal just because one class inherits from the other.
            return specialAttack == ((SpecialPokemon) obj).getSpecialAttack() && super.equals(obj);//We will use the equals method implemented in the parent class to check the shared fields and also check whether the separate field is also equal in both objects. If everything is equal, we will return true, otherwise false.
        } else {
            return false;
        }
    }

    @Override
    public SpecialPokemon clone() {//We will use the copy constructor of the parent class and store them in a new pokemon.Pokemon variable. Using this variable, we will create a new pokemon.Pokemon, this time of type "Special pokemon.Pokemon", and using the getters from the parent class, we will insert its fields, along with the field unique to the current class.
        try {
            Pokemon common_fields = new Pokemon(this);
            return new SpecialPokemon(common_fields.getName(), common_fields.getType(), common_fields.getAttack(), common_fields.getDefense(), common_fields.getHealth(), this.specialAttack);
        } catch (PokemonException e) {
            return null;
        }
    }

    public void saveToFile(File file) { //We will write the Pokémon's field values in the file we received according to the required format.
        PrintWriter writer_to_file = null;
        try {
            writer_to_file = new PrintWriter(new FileWriter(file));//We will create a new object of the PrintWriter class with which we can write to the given file.
            writer_to_file.println("Name: " + this.getName());
            writer_to_file.println("pokemon.Type: " + this.getType().getType());
            writer_to_file.println("Attack: " + this.getAttack());
            writer_to_file.println("Defense: " + this.getDefense());
            writer_to_file.println("Health: " + this.getHealth());
            writer_to_file.println("Special Attack: " + this.getSpecialAttack());
        } catch (IOException e) {//We will catch the IEO error that can occur when working with files.
            return;
        }
        finally {//Finally we will close the file.
            if (writer_to_file != null) {
                writer_to_file.close();
            }
        }
    }
}