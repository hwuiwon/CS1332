import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Your implementation of a max heap.
 *
 * @author Hwuiwon Kim
 * @userid hkim944
 * @version 1.0
 */
public class MaxHeap<T extends Comparable<? super T>> {

    // DO NOT ADD OR MODIFY THESE INSTANCE/CLASS VARIABLES.
    public static final int INITIAL_CAPACITY = 13;

    private T[] backingArray;
    private int size;

    /**
     * Creates a Heap with an initial capacity of INITIAL_CAPACITY
     * for the backing array.
     *
     * Use the constant field provided. Do not use magic numbers!
     */
    public MaxHeap() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Creates a properly ordered heap from a set of initial values.
     *
     * You must use the BuildHeap algorithm that was taught in lecture! Simply
     * adding the data one by one using the add method will not get any credit.
     * As a reminder, this is the algorithm that involves building the heap
     * from the bottom up by repeated use of downHeap operations.
     *
     * The data in the backingArray should be in the same order as it appears
     * in the passed in ArrayList before you start the Build Heap Algorithm.
     *
     * The backingArray should have capacity 2n + 1 where n is the
     * number of data in the passed in ArrayList (not INITIAL_CAPACITY from
     * the interface). Index 0 should remain empty, indices 1 to n should
     * contain the data in proper order, and the rest of the indices should
     * be empty.
     *
     * @param data a list of data to initialize the heap with
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public MaxHeap(ArrayList<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data can't be null");
        }
        backingArray = (T[]) new Comparable[data.size() * 2 + 1];
        size = data.size();
        for (int i = 0; i < size; i++) {
            if (data.get(i) == null) {
                throw new IllegalArgumentException("Data can't be null");
            }
            backingArray[i + 1] = data.get(i);
        }
        for (int i = size / 2; i >= 1; i--) {
            maxHeapify(i);
        }
    }

    /**
     * Exchanges the value of specified indexes
     *
     * @param i the index of a value getting exchanged with j
     * @param j the index of a value getting exchanged with i
     */
    private void exchange(int i, int j) {
        T tmp = backingArray[i];
        backingArray[i] = backingArray[j];
        backingArray[j] = tmp;
    }

    /**
     * Adds an item to the heap. If the backing array is full and you're trying
     * to add a new item, then double its capacity.
     *
     * @throws IllegalArgumentException if the item is null
     * @param item the item to be added to the heap
     */
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Data can't be null");
        }
        if (size == backingArray.length - 1) {
            T[] tmp = (T[]) new Comparable[backingArray.length * 2];
            for (int i = 0; i < backingArray.length; i++) {
                tmp[i] = backingArray[i];
            }
            backingArray = tmp;
        }
        backingArray[++size] = item;
        int i = size;
        while (i > 1
                && backingArray[i / 2].compareTo(backingArray[i]) < 0) {
            exchange(i, i / 2);
            i /= 2;
        }
    }

    /**
     * Removes and returns the max item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     *
     * @throws java.util.NoSuchElementException if the heap is empty
     * @return the removed item
     */
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is Empty");
        }
        T data = backingArray[1];
        exchange(1, size--);
        maxHeapify(1);
        backingArray[size + 1] = null;
        return data;
    }

    /**
     * Turns tree to max heap
     *
     * @param i the index of a value
     */
    private void maxHeapify(int i) {
        while (2 * i <= size) {
            int j = 2 * i;
            if (j < size && backingArray[j]
                    .compareTo(backingArray[j + 1]) < 0) {
                j++;
            }
            if (!(backingArray[i].compareTo(backingArray[j]) < 0)) {
                break;
            }
            exchange(i, j);
            i = j;
        }
    }

    /**
     * Returns the maximum element in the heap.
     *
     * @return the maximum element, null if the heap is empty
     */
    public T getMax() {
        if (size == 0) {
            return null;
        }
        return backingArray[1];
    }

    /**
     * Returns if the heap is empty or not.
     *
     * @return true if the heap is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the heap and rests the backing array to a new array of capacity
     * {@code INITIAL_CAPACITY}.
     */
    public void clear() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return number of items in the heap
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the heap
     */
    public Object[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }
}