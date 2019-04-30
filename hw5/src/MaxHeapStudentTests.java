import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
/**
  * Simple test cases for a max heap.
  * Write your own tests to ensure you cover all edge cases.
  *
  * @author CS 1332 TAs
  * @version 1.0
  */
public class MaxHeapStudentTests {

    private static final int TIMEOUT = 200;
    private MaxHeap<Integer> maxHeap;

    @Before
    public void setUp() {
        maxHeap = new MaxHeap<>();
    }

    @Test(timeout = TIMEOUT)
    public void testBuildHeap() {
        /*
                25                   40
               /  \                 /  \
              10  30     --->      35  30
             /  \                 /  \
            35  40               25  10
        */
        ArrayList<Integer> passedIn = new ArrayList<>();
        passedIn.add(25);
        passedIn.add(10);
        passedIn.add(30);
        passedIn.add(35);
        passedIn.add(40);

        Integer[] expected = new Integer[11];
        expected[1] = 40;
        expected[2] = 35;
        expected[3] = 30;
        expected[4] = 25;
        expected[5] = 10;

        maxHeap = new MaxHeap<>(passedIn);
        assertEquals(5, maxHeap.size());
        assertArrayEquals(expected, maxHeap.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAdd() {
        /*
                89
               /  \
              64  43
             /  \
            15  17
        */
        maxHeap.add(43);
        maxHeap.add(15);
        maxHeap.add(64);
        maxHeap.add(17);
        maxHeap.add(89);

        Integer[] expected = new Integer[MaxHeap.INITIAL_CAPACITY];
        expected[1] = 89;
        expected[2] = 64;
        expected[3] = 43;
        expected[4] = 15;
        expected[5] = 17;
        assertEquals(5, maxHeap.size());
        assertArrayEquals(expected, maxHeap.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemove() {
        /*
                89
               /  \                 17
              64  43     --->      /
             /  \                 15
            15  17
        */
        maxHeap.add(43);
        maxHeap.add(15);
        maxHeap.add(64);
        maxHeap.add(17);
        maxHeap.add(89);

        assertEquals((Integer) 89, maxHeap.remove());
        assertEquals((Integer) 64, maxHeap.remove());
        assertEquals((Integer) 43, maxHeap.remove());
        assertEquals(2, maxHeap.size());

        Integer[] expected = new Integer[MaxHeap.INITIAL_CAPACITY];
        expected[1] = new Integer(17);
        expected[2] = new Integer(15);
        assertArrayEquals(expected, maxHeap.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testMiscellaneous() {
        /*
                89
               /  \
              64  43
             /  \
            15  17
        */
        assertEquals(true, maxHeap.isEmpty());
        maxHeap.add(43);
        maxHeap.add(15);
        maxHeap.add(64);
        maxHeap.add(17);
        maxHeap.add(89);

        assertEquals(false, maxHeap.isEmpty());
        assertEquals((Integer) 89, maxHeap.getMax());

        maxHeap.clear();
        assertEquals(true, maxHeap.isEmpty());
        assertEquals(0, maxHeap.size());
        assertArrayEquals(new Integer[MaxHeap.INITIAL_CAPACITY],
            maxHeap.getBackingArray());
    }
}