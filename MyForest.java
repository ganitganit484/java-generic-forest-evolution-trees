package pokemon;

import java.io.Serializable;
import java.util.ArrayList;

public class MyForest<T> implements Serializable {//We made the class Serializable because now the Pokedex class has a field that contains an object of this class.
    private ArrayList<MyTree<T>> trees_arrayList; //A data structure that will contain all the trees in the forest
    public MyForest() {
        trees_arrayList = new ArrayList<>();//Initially there are no trees in the forest so an empty arraylist is initialized.
    }
    public boolean add(T element) {//A method that accepts an element and defines it as the root of a new tree in the data structure.
        if (this.exists(element)) {//If the forest already contains the element we want to add, we will return false.
            return false;
        }
        MyTree<T> new_tree = new MyTree<>(element);
        trees_arrayList.add(new_tree);//Add the new tree to the data structure
        return true;
    }

    public boolean add(T parent, T element) {
        if (parent == null) {//If the parent is equal to null, we add the element as the root of a new tree.
            return add(element);
        }
        if (this.areRelated(parent,element)) {//If we try to add a child to a parent, one of which is already a child of the other.
            return false;
        }
        for (int i=0; i<trees_arrayList.size();i++) {
            MyTree<T> parentNode = trees_arrayList.get(i).get(parent);//We will loop through the entire array and use the get method to check whether we found the parent in one of the trees in the data structure. If so, we will add the element to the parent's subtree.
            if (parentNode != null){
                return parentNode.add(parent,element);
            }
        }
        return false;
    }
    public boolean remove(T element) {
        for (int i=0; i<trees_arrayList.size();i++) {//We will loop through the entire array and for each tree in the array we will check whether the remove method returns true. If so, we will return true (meaning the removal was successful). If we caught an error, it means we tried to delete the root, in which case we will delete the entire tree from the data structure.
            try {
                if (trees_arrayList.get(i).remove(element)) {
                    return true;
                }
            } catch (IllegalArgumentException e) {
                trees_arrayList.remove(i);
                return true;
            }
        }
        return false;//If we tried to remove an element that does not exist in the data structure, we would return false.
    }

    public boolean areRelated(T element1, T element2) {//To check if one is a descendant of the other or vice versa, we will run the isSuccessorOf method twice, each time swapping who is defined as the parent and who is defined as the child. If one of them returns true, we will return true.
        if (element1.equals(element2)) {//Two equal elements are considered children of each other.
            return true;
        }
        for (int i=0; i<trees_arrayList.size();i++) {
            if (trees_arrayList.get(i).get(element1) != null && trees_arrayList.get(i).get(element2) != null) {
                if (trees_arrayList.get(i).isSuccessorOf(element1,element2) || trees_arrayList.get(i).isSuccessorOf(element2,element1))
                return true;
            }
        }
        return false;//Otherwise we will return false.
    }
    public boolean exists(T element) {//We will loop through the entire array. If we have not entered the condition that checks that the get function does not return null (meaning the element exists in the tree), we will return false. If we have entered it, it means that the element exists and we will return true.
        for (int i=0; i<trees_arrayList.size();i++) {
            if (trees_arrayList.get(i).get(element) != null) {
                return true;
            }
        }
        return false;
    }
    public MyTree<T> getTree(T element){ //We will loop over the array, each time we run the existing function. Once it returns true, it means we have found the relevant tree that we want to return.
        for (int i=0; i<trees_arrayList.size();i++) {
            if (trees_arrayList.get(i).exists(element)){
                return trees_arrayList.get(i);
            }
        }
        return null;//If we did not find the element in any of the trees, it means it does not exist in the data structure and we will return null.
    }
}
