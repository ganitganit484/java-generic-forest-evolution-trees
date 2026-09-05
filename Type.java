package pokemon;

import java.io.Serializable;

/**
 * A class that includes the Pokémon type and contains a constructor and a getter.
 */
public class Type implements Comparable <Type> , Serializable {//We made the class Serializable because the class instances are used as values in the type field found in the pokemon.Pokemon class, which we made Serializable.
    private String type;

    public Type(String type){
        if (type == null){//If the pokemon.Type field contains a null value, we will create a new object of type "pokemon.Type" with the value "water"
            this.type = "Water";
        }
        else {this.type = type;
        }
    }
    public String getType(){
        return type;
    }

    @Override
    public int compareTo(Type other) { //We will return 0,-1,1 according to the table comparing the strength of the different Pokémon types.From now on, when we want to compare two Pokémon based on type, we will be able to know who is stronger than whom based on the result that is returned.
        if (other == null) {
            return 1;
        }
        if (type.equals(other.getType())) {
            return 0;
        } else if (type.equals("Fire")) {//Fire is stronger than water but weaker than Electric and Grass.
            if (other.getType().equals("Water")) return -1;
            else if (other.getType().equals("Grass") || other.getType().equals("Electric")) return 1;
            else return 0;
        } else if (type.equals("Water")) {//Water is stronger than Fire but weaker than Electric and Grass.
            if (other.getType().equals("Fire")) return 1;
            else if (other.getType().equals("Grass") || other.getType().equals("Electric")) return -1;
            else return 0;
        } else if (type.equals("Grass")) {//Grass is stronger than Fire but weaker than Electric and Water.
            if (other.getType().equals("Fire")) return -1;
            else if (other.getType().equals("Water") || other.getType().equals("Electric")) return 1;
            else return 0;
        } else if (type.equals("Electric")) {//Electric is stronger than Water but weaker than Fire and Grass.
            if (other.getType().equals("Water")) return 1;
            else if (other.getType().equals("Grass") || other.getType().equals("Fire")) return -1;
            else return 0;
        }
        return 0;//If the type is not recognized, the default will be equal to 0.
    }
}

