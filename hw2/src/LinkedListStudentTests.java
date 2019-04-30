import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

/**
 * This is a basic set of unit tests for SinglyLinkedList. Passing these does
 * NOT guarantee any grade on this assignment. This is only a sanity check to
 * help you get started on the homework and writing JUnits in general.
 *
 * @author The 1332 TAs
 * @version 1.0
 */
public class LinkedListStudentTests {
    private SinglyLinkedList<String> list;

    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        list = new SinglyLinkedList<String>();
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsGeneral() {
        assertEquals(0, list.size());
        assertNull(list.getHead());

        list.addAtIndex(0, "0a"); //0a
        list.addAtIndex(1, "1a"); //0a 1a
        list.addAtIndex(2, "2a"); //0a 1a 2a
        list.addAtIndex(3, "3a"); //0a 1a 2a 3a

        assertEquals(4, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("0a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("1a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("2a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("3a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertSame(list.getHead(), current);
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsFront() {
        assertEquals(0, list.size());

        list.addToFront("0a");
        list.addToFront("1a");
        list.addToFront("2a");
        list.addToFront("3a");
        list.addToFront("4a");
        list.addToFront("5a"); //5a 4a 3a 2a 1a 0a

        assertEquals(6, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("5a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("4a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("3a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("2a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("1a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("0a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertSame(list.getHead(), current);
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsBack() {
        assertEquals(0, list.size());

        list.addToBack("0a");
        list.addToBack("1a");
        list.addToBack("2a");
        list.addToBack("3a");
        list.addToBack("4a");
        list.addToBack("5a"); //0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("0a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("1a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("2a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("3a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("4a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("5a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertSame(list.getHead(), current);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStringsGeneral() {
        assertEquals(0, list.size());

        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, "4a");
        list.addAtIndex(5, "5a"); //0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        assertEquals("2a", list.removeAtIndex(2)); //0a 1a 3a 4a 5a

        assertEquals(5, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("0a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("1a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("3a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("4a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("5a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertSame(list.getHead(), current);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStringsFront() {
        assertEquals(0, list.size());

        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, "4a");
        list.addAtIndex(5, "5a"); //0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        assertEquals("0a", list.removeFromFront()); //1a 2a 3a 4a 5a

        assertEquals(5, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("1a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("2a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("3a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("4a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("5a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertSame(list.getHead(), current);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStringsBack() {
        assertEquals(0, list.size());

        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, "4a");
        list.addAtIndex(5, "5a"); //0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        assertEquals("5a", list.removeFromBack()); //0a 1a 2a 3a 4a

        assertEquals(5, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("0a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("1a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("2a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("3a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("4a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertSame(list.getHead(), current);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testRemoveLastOccurrenceNullItemPassed() {
        list.removeLastOccurrence(null);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveLastOccurrence() {
        assertEquals(0, list.size());

        String temp = new String("4a");
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, temp);
        list.addAtIndex(5, "5a"); //0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        assertEquals(temp,
            list.removeLastOccurrence(new String("4a"))); //0a 1a 2a 3a 5a

        assertEquals(5, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertEquals("0a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("1a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("2a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("3a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertEquals("5a", current.getData());

        current = current.getNext();
        assertNotNull(current);
        assertSame(list.getHead(), current);
    }

    @Test(timeout = TIMEOUT)
    public void testGetGeneral() {
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, "4a");
        list.addAtIndex(5, "5a"); //0a 1a 2a 3a 4a 5a

        assertEquals("0a", list.get(0));
        assertEquals("1a", list.get(1));
        assertEquals("2a", list.get(2));
        assertEquals("3a", list.get(3));
        assertEquals("4a", list.get(4));
        assertEquals("5a", list.get(5));
    }

    @Test(timeout = TIMEOUT)
    public void testToArray() {
        String[] expectedItems = new String[10];

        // Adding items 0a, 1a, ..., 8a, 9a
        for (int x = 0; x < expectedItems.length; x++) {
            expectedItems[x] = "a" + x;
            list.addToBack(expectedItems[x]);
        }

        Object[] array = list.toArray();
        assertArrayEquals(expectedItems, array);
    }

    @Test(timeout = TIMEOUT)
    public void testClearAndIsEmpty() {
        list.addAtIndex(0, "0a");
        list.addAtIndex(1, "1a");
        list.addAtIndex(2, "2a");
        list.addAtIndex(3, "3a");
        list.addAtIndex(4, "4a"); //0a 1a 2a 3a 4a

        assertEquals(5, list.size());
        assertEquals(false, list.isEmpty());

        list.clear();
        assertEquals(0, list.size());
        assertEquals(null, list.getHead());
        assertEquals(true, list.isEmpty());
    }
}