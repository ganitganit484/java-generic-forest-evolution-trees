package pokemon;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class that implements a generic tree and contains various methods such as insert, delete, search, find the predecessor and successor, and other methods.
 */

public class MyTree<T> implements Serializable {//We made the class Serializable because now the MyForest class has a field that contains an object of this class.
    private T node;
    private ArrayList<MyTree<T>> children;
    public MyTree(T element) {
        this.node = element;//We will initialize the tree with a root and an arraylist that will contain the descendants of that node. The children of the root will be initialized as an empty list.
        this.children = new ArrayList<>();
    }
    public boolean remove(T element) {//A method that receives a vertex, searches for it in the tree recursively and if found, deletes it and all its subtrees (if the vertex is equal to the root, an error occurs)
        if (this.node.equals(element)) {
            throw new IllegalArgumentException();//Throwing the error
        }
        for (int child_index = 0; child_index < this.children.size(); child_index++) {//We loop over the list of children of the root. If we find the desired vertex, we delete it and its subtree. If we don't find it, we perform a recursive search on each of the lists of children.
            MyTree<T> curr_child = this.children.get(child_index);
            if (curr_child.node.equals(element)) {
                this.children.remove(child_index);
                return true;
            } else {
                if (curr_child.remove(element)) {
                    return true;
                }
            }
        }
        return false;//If the vertex is not found in any call, we will return false. If we found it, we will return true.
    }
    public MyTree<T> get(T element) {//A method that receives a vertex and searches the tree to find it. If the vertex is found, the method returns the entire subtree of the vertex. If the vertex is not found in the tree, we return null.
        if (this.node.equals(element)) {
            return this;
        }
        for (int child_index = 0; child_index < this.children.size(); child_index++) {
            MyTree<T> curr_child = this.children.get(child_index);
            if (curr_child.node.equals(element)) {
                return curr_child;
            }
            else {
                MyTree <T> to_return = curr_child.get(element);//Recursive call
                if (to_return != null) {
                    return to_return;
                }
            }
        }
        return null;
    }
    public boolean add(T parent, T element) {//The get method is called with the vertex that is defined as a parent. If it is in the tree (null is not received from the get method), we will add the vertex that is defined as a child in the input to its children list, and return true. If the parent vertex is not in the tree, we will return false.
        MyTree<T> parent_val = this.get(parent);
        if (parent_val != null) {
            MyTree<T> newChild = new MyTree<>(element);
            parent_val.children.add(newChild);
            return true;
        }
        return false;
    }
    public boolean exists(T element) { //To check whether a vertex exists in the tree, we call the get method and return true or false according to what the get method returned (if null then we return false and if not then true).
        return this.get(element) != null;
    }

    public boolean isSuccessorOf(T child, T parent) {//A method that checks whether a particular vertex is a descendant of another vertex. Basically, we will use the get method to return the subtree of the desired parent, and on it we will invoke the exist method to check whether the child is a descendant of the parent.
        if (child.equals(parent)) {//If the parent is equal to the child, we will return false.
            return false;
        }
        MyTree<T> tocheck = this.get(parent);
        if (tocheck != null) {
            return tocheck.exists(child);
        }
        return false;
    }

    public boolean isPredecessorOf(T parent, T child) {//Logically, if we want to check the opposite of a previous method, we will still need to check whether the child appears in the parent's list of children, so we will use the isPredecessorOf method.
        return this.isSuccessorOf(child,parent);
    }
    public int size() {
        int general_size = 1;//We will initialize the tree to be 1 (the height of the tree with only the root)
        for (MyTree<T> child : children) {//We will loop over the list of children, and on each child we will perform a recursive call that will run until we reach a vertex that has no children. Each time a recursive call closes, we will increment the size of the children of the current vertex.
            general_size += child.size();
        }
        return general_size;//We will return the total size.
    }
    public T getData() {// Returns the data stored in this tree node.
        return this.node;
    }

}
