/**
 * Node class used in linked data structure implementations.
 *
 * DO NOT ALTER THIS FILE!!
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public class LinkedNode<T> {

    private T data;
    private LinkedNode<T> next;

    /**
     * Create a new LinkedNode with the given data object and next node.
     *
     * @param data data to store in the node
     * @param next the next node
     */
    public LinkedNode(T data, LinkedNode<T> next) {
        this.data = data;
        this.next = next;
    }

    /**
     * Creates a new LinkedNode with the given data object and no next node.
     *
     * @param data data to store in this node
     */
    public LinkedNode(T data) {
        this(data, null);
    }

    /**
     * Gets the data stored in the node.
     *
     * @return data in this node
     */
    public T getData() {
        return data;
    }

    /**
     * Gets the next node.
     *
     * @return the next node
     */
    public LinkedNode<T> getNext() {
        return next;
    }

    /**
     * Sets the next node.
     *
     * @param next the new next node
     */
    public void setNext(LinkedNode<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node containing: " + data;
    }

}