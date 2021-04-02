package sample.Model;

/**
 * The Customizable interface ensures that those classes that implement it
 * can add and remove items from a list that the class contains.
 * @author Christopher Yong, Maya Ravichandran
 */
public interface Customizable {
    boolean add (Object obj);
    boolean remove (Object obj);
}
