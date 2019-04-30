/**
 * Your implementation of a circular singly linked list.
 *
 * @author Hwuiwon Kim
 * @userid hkim944
 * @version 1.0
 */
public class SinglyLinkedList<T> {
    // Do not add new instance variables or modify existing ones.
    private LinkedListNode<T> head;
    private int size;

    /**
     * Adds the element to the index specified.
     *
     * Adding to indices 0 and {@code size} should be O(1), all other cases are
     * O(n).
     *
     * @param index the requested index for the new element
     * @param data the data for the new element
     * @throws IndexOutOfBoundsException if index is negative or
     * index > size
     * @throws IllegalArgumentException if data is null
     */
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index should be"
                    + " between 0 and size");
        } else if (data == null) {
            throw new IllegalArgumentException("Data can't be null");
        }
        LinkedListNode<T> tmp = new LinkedListNode<>(data);
        LinkedListNode<T> current = head;
        if (size == 0) {
            head = tmp;
            head.setNext(head);
        } else if (index == 0) {
            tmp.setNext(head.getNext());
            tmp.setData(head.getData());
            head.setNext(tmp);
            head.setData(data);
        } else if (index == size) {
            while (current.getNext() != head) {
                current = current.getNext();
            }
            current.setNext(tmp);
            tmp.setNext(head);
        } else {
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            tmp.setNext(current.getNext());
            current.setNext(tmp);
        }
        size++;
    }

    /**
     * Adds the element to the front of the list.
     *
     * Must be O(1) for all cases.
     *
     * @param data the data for the new element
     * @throws IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        addAtIndex(0, data);
    }

    /**
     * Adds the element to the back of the list.
     *
     * Must be O(1) for all cases.
     *
     * @param data the data for the new element
     * @throws IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        addAtIndex(size, data);
    }

    /**
     * Removes and returns the element from the index specified.
     *
     * Removing from index 0 should be O(1), all other cases are O(n).
     *
     * @param index the requested index to be removed
     * @return the data formerly located at index
     * @throws IndexOutOfBoundsException if index is negative or
     * index >= size
     */
    public T removeAtIndex(int index) {
        if (isEmpty()) {
            return null;
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index should be"
                    + " between 0 and size");
        }

        T tmp;
        LinkedListNode<T> current = head;
        if (index == 0) {
            tmp = head.getData();
            head.setData(head.getNext().getData());
            head.setNext(head.getNext().getNext());
        } else if (index == size) {
            while (current.getNext().getNext() != head) {
                current = current.getNext();
            }
            tmp = current.getNext().getData();
            current.setNext(head);
        } else {
            for (int i = 1; i < index; i++) {
                current = current.getNext();
            }
            tmp = current.getNext().getData();
            current.setNext(current.getNext().getNext());
        }
        if (--size == 0) {
            head = null;
        }
        return tmp;
    }

    /**
     * Removes and returns the element at the front of the list. If the list is
     * empty, return {@code null}.
     *
     * Must be O(1) for all cases.
     *
     * @return the data formerly located at the front, null if empty list
     */
    public T removeFromFront() {
        return removeAtIndex(0);
    }

    /**
     * Removes and returns the element at the back of the list. If the list is
     * empty, return {@code null}.
     *
     * Must be O(n) for all cases.
     *
     * @return the data formerly located at the back, null if empty list
     */
    public T removeFromBack() {
        return removeAtIndex(size - 1);
    }

    /**
     * Removes the last copy of the given data from the list.
     *
     * Must be O(n) for all cases.
     *
     * @param data the data to be removed from the list
     * @return the removed data occurrence from the list itself (not the data
     * passed in), null if no occurrence
     * @throws IllegalArgumentException if data is null
     */
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data can't be null");
        }
        if (size == 0) {
            return null;
        } else {
            LinkedListNode<T> tmp = head;
            LinkedListNode<T> tmp2 = null;
            if (head.getData().equals(data)) {
                tmp2 = tmp;
            }
            tmp = tmp.getNext();
            while (tmp != head && tmp != null) {
                if (tmp.getNext().getData().equals(data)) {
                    tmp2 = tmp;
                }
                tmp = tmp.getNext();
            }
            if (tmp2 != null) {
                tmp = tmp2.getNext();
                tmp2.setNext(tmp2.getNext().getNext());
                if (--size == 0) {
                    head = null;
                }
            }
            return tmp.getData();
        }
    }

    /**
     * Returns the element at the specified index.
     *
     * Getting index 0 should be O(1), all other cases are O(n).
     *
     * @param index the index of the requested element
     * @return the object stored at index
     * @throws IndexOutOfBoundsException if index < 0 or
     * index >= size
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index should be"
                    + " between 0 and size");
        } else if (index == 0) {
            return head.getData();
        } else {
            LinkedListNode<T> tmp = head;
            for (int i = 0; i < index; i++) {
                tmp = tmp.getNext();
            }
            return tmp.getData();
        }
    }

    /**
     * Returns an array representation of the linked list.
     *
     * Must be O(n) for all cases.
     *
     * @return an array of length {@code size} holding all of the objects in
     * this list in the same order
     */
    public Object[] toArray() {
        T[] arr = (T[]) new Object[size];
        LinkedListNode<T> tmp = head;
        for (int i = 0; i < size; i++) {
            arr[i] = tmp.getData();
            tmp = tmp.getNext();
        }
        return arr;
    }

    /**
     * Returns a boolean value indicating if the list is empty.
     *
     * Must be O(1) for all cases.
     *
     * @return true if empty; false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the list of all data.
     *
     * Must be O(1) for all cases.
     */
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Returns the number of elements in the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }

    /**
     * Returns the head node of the linked list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return node at the head of the linked list
     */
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }
}