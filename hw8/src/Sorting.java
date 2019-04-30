import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Hwuiwon Kim
 * @userid hkim944
 * @version 1.0
 */
public class Sorting {

    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Null array or comparator");
        }
        for (int i = 1; i < arr.length; i++) {
            T tmp = arr[i];
            int j = i - 1;
            while (j >= 0 && comparator.compare(arr[j], tmp) > 0) {
                arr[j + 1] = arr[j--];
            }
            arr[j + 1] = tmp;
        }
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     *  in-place
     *  unstable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n^2)
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Null array or comparator");
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (comparator.compare(arr[j], arr[min]) < 0) {
                    min = j;
                }
            }
            swap(arr, min, i);
        }
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     *  out-of-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You can create more arrays to run mergesort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Null array or comparator");
        }
        if (arr.length > 1) {
            T[] left = (T[]) new Object[arr.length / 2];
            T[] right = (T[]) new Object[arr.length - left.length];
            for (int i = 0; i < left.length; i++) {
                left[i] = arr[i];
            }
            for (int i = 0; i < right.length; i++) {
                right[i] = arr[i + left.length];
            }
            mergeSort(left, comparator);
            mergeSort(right, comparator);
            merge(arr, comparator, left, right);
        }
    }

    /**
     * Merges separated arrays
     *
     * @param arr the array to be sorted
     * @param comparator comparator used to compare values
     * @param leftArr left array
     * @param rightArr right Array
     * @param <T> data type to sort
     */
    private static <T> void merge(T[] arr, Comparator<T> comparator,
                                  T[] leftArr, T[] rightArr) {
        int i = 0;
        int j = 0;
        for (int k = 0; k < arr.length; k++) {
            if (j >= rightArr.length || (i < leftArr.length
                    && comparator.compare(leftArr[i], rightArr[j]) <= 0)) {
                arr[k] = leftArr[i++];
            } else {
                arr[k] = rightArr[j++];
            }
        }
    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots. For example if you
     * need a pivot between a (inclusive) and b (exclusive) where b > a, use
     * the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * If your recursion uses an inclusive b instead of an exclusive one,
     * the formula changes by adding 1 to the nextInt() call:
     *
     * int pivotIndex = rand.nextInt(b - a + 1) + a;
     *
     * It should be:
     *  in-place
     *  unstable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not receive
     * credit if you do not use the one we have taught you!
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null || comparator == null || rand == null) {
            throw new IllegalArgumentException("Null array or comparator");
        }
        qsHelper(arr, 0, arr.length - 1, rand, comparator);
    }

    /**
     * Helper method for quick sort
     *
     * @param arr the array to be sorted
     * @param first starting index
     * @param last ending index
     * @param rand the Random object used to select pivots
     * @param comparator the Comparator used to compare the data in arr
     * @param <T> data type to sort
     */
    private static <T> void qsHelper(T[] arr, int first, int last,
                                     Random rand, Comparator<T> comparator) {
        if (last > first) {
            int pIndex = partition(arr, first, last,
                    rand.nextInt(last - first) + first, comparator);
            qsHelper(arr, first, pIndex - 1, rand, comparator);
            qsHelper(arr, pIndex + 1, last, rand, comparator);
        }
    }

    /**
     * Partitions array for quick sort
     *
     * @param arr the array to be sorted
     * @param first starting index
     * @param last ending index
     * @param pIndex pivot index
     * @param comparator the Comparator used to compare the data in arr
     * @param <T> data type to sort
     * @return new pivot index
     */
    private static <T> int partition(T[] arr, int first, int last,
                                     int pIndex, Comparator<T> comparator) {
        T pivot = arr[pIndex];
        swap(arr, pIndex, last);
        int ind = first;
        for (int i = first; i < last; i++) {
            if (comparator.compare(arr[i], pivot) <= 0) {
                swap(arr, i, ind);
                ind++;
            }
        }
        swap(arr, last, ind);
        return ind;
    }

    /**
     * Swap two elements in an array
     *
     * @param arr the array that contains items to swap
     * @param a first item
     * @param b second item
     * @param <T>  data type to sort
     */
    private static <T> void swap(T[] arr, int a, int b) {
        T tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     *  out-of-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * You are allowed to make an initial O(n) passthrough of the array to
     * determine the number of iterations you need.
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array can't be null");
        }
        LinkedList<Integer>[] buckets = new LinkedList[19];
        for (int i = 0; i < 19; i++) {
            buckets[i] = new LinkedList<>();
        }
        int mod = 10;
        int div = 1;
        boolean cont = true;
        while (cont) {
            cont = false;
            for (int num : arr) {
                int bucket = num / div;
                if (bucket / 10 != 0) {
                    cont = true;
                }
                if (buckets[bucket % mod + 9] == null) {
                    buckets[bucket % mod + 9] = new LinkedList<>();
                }
                buckets[bucket % mod + 9].add(num);
            }
            int arrIdx = 0;
            for (LinkedList<Integer> bucket : buckets) {
                if (bucket != null) {
                    for (int num : bucket) {
                        arr[arrIdx++] = num;
                    }
                    bucket.clear();
                }
            }
            div *= 10;
        }
    }
}