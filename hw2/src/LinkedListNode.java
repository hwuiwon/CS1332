/**
 * Node class used for implementing the circular SinglyLinkedList.
 *
 * DO NOT ALTER THIS FILE!!
 *
 * @author CS 1332 TAs
 * @version 1.0
 */

public class LinkedListNode<T> {
    private T data;
    private LinkedListNode<T> next;

    /**
     * Creates a new LinkedListNode with the given T object and next reference.
     *
     * @param data the data stored in the new node
     * @param next the next node in the list
     */
    public LinkedListNode(T data, LinkedListNode<T> next) {
        this.data = data;
        this.next = next;
    }

    /**
     * Creates a new LinkedListNode with only the given T object.
     *
     * @param data the data stored in the new node
     */
    public LinkedListNode(T data) {
        this(data, null);
    }

    /**
     * Gets the data stored in the node.
     *
     * @return the data in this node
     */
    public T getData() {
        return data;
    }

    /**
     * Sets the data stored in the node.
     *
     * @param data the new data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Gets the next node.
     *
     * @return the next node
     */
    public LinkedListNode<T> getNext() {
        return next;
    }

    /**
     * Sets the next node.
     *
     * @param next the new next node
     */
    public void setNext(LinkedListNode<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node containing: " + data;
    }
}