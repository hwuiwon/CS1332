import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * This is a basic set of unit tests for ArrayList. Passing these does
 * NOT guarantee any grade on this assignment. This is only a sanity check to
 * help you get started on the homework and writing JUnits in general.
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public class ArrayListStudentTests {

    private ArrayList<String> list;

    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        list = new ArrayList<String>();
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsFront() {
        assertEquals(0, list.size());

        list.addToFront("0a"); // 0a
        list.addToFront("1a"); // 1a 0a
        list.addToFront("2a"); // 2a 1a 0a
        list.addToFront("3a"); // 3a 2a 1a 0a
        list.addToFront("4a"); // 4a 3a 2a 1a 0a

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "4a";
        expected[1] = "3a";
        expected[2] = "2a";
        expected[3] = "1a";
        expected[4] = "0a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsBack() {
        assertEquals(0, list.size());

        list.addToBack("0a"); // 0a
        list.addToBack("1a"); // 0a 1a
        list.addToBack("2a"); // 0a 1a 2a
        list.addToBack("3a"); // 0a 1a 2a 3a

        assertEquals(4, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsGeneral() {
        assertEquals(0, list.size());

        list.addAtIndex(0, "2a"); // 2a
        list.addAtIndex(0, "1a"); // 1a 2a
        list.addAtIndex(2, "4a"); // 1a 2a 4a
        list.addAtIndex(2, "3a"); // 1a 2a 3a 4a

        assertEquals(4, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "1a";
        expected[1] = "2a";
        expected[2] = "3a";
        expected[3] = "4a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStringsFront() {
        assertEquals(0, list.size());
        String temp = new String("0a"); // For equality checking.
        list.addAtIndex(0, temp); // 0a
        list.addAtIndex(1, "1a"); // 0a 1a
        list.addAtIndex(2, "2a"); // 0a 1a 2a
        list.addAtIndex(3, "3a"); // 0a 1a 2a 3a
        list.addAtIndex(4, "4a"); // 0a 1a 2a 3a 4a
        list.addAtIndex(5, "5a"); // 0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        // assertSame checks for reference equality whereas assertEquals checks
        // value equality.
        assertSame(temp, list.removeFromFront()); // 1a 2a 3a 4a 5a

        assertEquals(5, list.size());
        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "1a";
        expected[1] = "2a";
        expected[2] = "3a";
        expected[3] = "4a";
        expected[4] = "5a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStringsBack() {
        assertEquals(0, list.size());
        String temp = new String("5a"); // For equality checking.
        list.addAtIndex(0, "0a"); // 0a
        list.addAtIndex(1, "1a"); // 0a 1a
        list.addAtIndex(2, "2a"); // 0a 1a 2a
        list.addAtIndex(3, "3a"); // 0a 1a 2a 3a
        list.addAtIndex(4, "4a"); // 0a 1a 2a 3a 4a
        list.addAtIndex(5, temp); // 0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        // assertSame checks for reference equality whereas assertEquals checks
        // value equality.
        assertSame(temp, list.removeFromBack()); // 0a 1a 2a 3a 4a

        assertEquals(5, list.size());
        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStringsGeneral() {
        assertEquals(0, list.size());
        String temp = new String("2a"); // For equality checking.
        list.addAtIndex(0, "0a"); // 0a
        list.addAtIndex(1, "1a"); // 0a 1a
        list.addAtIndex(2, temp); // 0a 1a 2a
        list.addAtIndex(3, "3a"); // 0a 1a 2a 3a
        list.addAtIndex(4, "4a"); // 0a 1a 2a 3a 4a
        list.addAtIndex(5, "5a"); // 0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        // assertSame checks for reference equality whereas assertEquals checks
        // value equality.
        assertSame(temp, list.removeAtIndex(2)); // 0a 1a 3a 4a 5a

        assertEquals(5, list.size());
        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "3a";
        expected[3] = "4a";
        expected[4] = "5a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testGetGeneral() {
        list.addAtIndex(0, "0a"); // 0a
        list.addAtIndex(1, "1a"); // 0a 1a
        list.addAtIndex(2, "2a"); // 0a 1a 2a
        list.addAtIndex(3, "3a"); // 0a 1a 2a 3a
        list.addAtIndex(4, "4a"); // 0a 1a 2a 3a 4a

        assertEquals("0a", list.get(0));
        assertEquals("1a", list.get(1));
        assertEquals("2a", list.get(2));
        assertEquals("3a", list.get(3));
        assertEquals("4a", list.get(4));
    }

    @Test(timeout = TIMEOUT)
    public void testLastIndexOfGeneral() {
        String temp = new String("3a");
        list.addAtIndex(0, "0a"); // 0a
        list.addAtIndex(1, "1a"); // 0a 1a
        list.addAtIndex(2, "2a"); // 0a 1a 2a
        list.addAtIndex(3, temp); // 0a 1a 2a 3a
        list.addAtIndex(4, "4a"); // 0a 1a 2a 3a 4a

        assertEquals(0, list.lastIndexOf("0a"));
        assertEquals(4, list.lastIndexOf("4a"));
        assertEquals(3, list.lastIndexOf(new String("3a")));
        assertEquals(-1, list.lastIndexOf("5a"));
    }

    @Test(timeout = TIMEOUT)
    public void testIsEmptyAndClear() {
        // Should be empty at initialization
        assertEquals(true, list.isEmpty());

        // Should not be empty after adding elements
        list.addAtIndex(0, "0a"); // 0a
        list.addAtIndex(1, "1a"); // 0a 1a
        list.addAtIndex(2, "2a"); // 0a 1a 2a
        list.addAtIndex(3, "3a"); // 0a 1a 2a 3a
        list.addAtIndex(4, "4a"); // 0a 1a 2a 3a 4a
        assertEquals(false, list.isEmpty());

        // Clearing the list should empty the array and reset size
        list.clear();
        assertEquals(true, list.isEmpty());
        assertEquals(0, list.size());
        assertArrayEquals(new Object[ArrayList.INITIAL_CAPACITY],
                list.getBackingArray());
    }
}
