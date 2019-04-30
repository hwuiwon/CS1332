import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;

/**
 * Basic tests for the stack and queue classes.
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public class StacksQueuesStudentTests {

    private ArrayStack<Integer> arrayStack;
    private ArrayQueue<Integer> arrayQueue;
    private LinkedStack<Integer> linkedStack;
    private LinkedQueue<Integer> linkedQueue;

    public static final int TIMEOUT = 200;

    @Test(timeout = TIMEOUT)
    public void testArrayStackPush() {
        arrayStack = new ArrayStack<>();
        assertEquals(0, arrayStack.size());

        // [34, 29, 48, 59, _, _, _, _, _]
        arrayStack.push(34);
        arrayStack.push(29);
        arrayStack.push(48);
        arrayStack.push(59);

        assertEquals(4, arrayStack.size());

        Object[] backingArray = arrayStack.getBackingArray();

        Object[] expected = new Object[ArrayStack.INITIAL_CAPACITY];
        expected[0] = 34;
        expected[1] = 29;
        expected[2] = 48;
        expected[3] = 59;

        assertArrayEquals(expected, backingArray);
    }

    @Test(timeout = TIMEOUT)
    public void testArrayStackPop() {
        arrayStack = new ArrayStack<>();
        assertEquals(0, arrayStack.size());

        // [34, 29, 48, 59, _, _, _, _, _]
        arrayStack.push(34);
        arrayStack.push(29);
        arrayStack.push(48);
        arrayStack.push(59);

        // [34, 29, 48, _, _, _, _, _, _]
        assertEquals((Integer) 59, arrayStack.pop());

        assertEquals(3, arrayStack.size());

        Object[] backingArray = arrayStack.getBackingArray();

        Object[] expected = new Object[ArrayStack.INITIAL_CAPACITY];
        expected[0] = 34;
        expected[1] = 29;
        expected[2] = 48;

        assertArrayEquals(expected, backingArray);
    }

    @Test(timeout = TIMEOUT)
    public void testArrayStackPeek() {
        arrayStack = new ArrayStack<>();
        assertEquals(0, arrayStack.size());

        // [34, 29, 48, 59, _, _, _, _, _]
        arrayStack.push(34);
        arrayStack.push(29);
        arrayStack.push(48);
        arrayStack.push(59);

        assertEquals((Integer) 59, arrayStack.peek());
        assertEquals(4, arrayStack.size());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedStackPush() {
        linkedStack = new LinkedStack<>();
        assertEquals(0, linkedStack.size());

        // 59 -> 48 -> 29 -> 34
        linkedStack.push(34);
        linkedStack.push(29);
        linkedStack.push(48);
        linkedStack.push(59);

        assertEquals(4, linkedStack.size());

        LinkedNode<Integer> curr = linkedStack.getHead();
        assertNotEquals(null, curr);
        assertEquals((Integer) 59, curr.getData());

        curr = curr.getNext();
        assertNotEquals(null, curr);
        assertEquals((Integer) 48, curr.getData());

        curr = curr.getNext();
        assertNotEquals(null, curr);
        assertEquals((Integer) 29, curr.getData());

        curr = curr.getNext();
        assertNotEquals(null, curr);
        assertEquals((Integer) 34, curr.getData());

        curr = curr.getNext();
        assertEquals(null, curr);
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedStackPop() {
        linkedStack = new LinkedStack<>();
        assertEquals(0, linkedStack.size());

        // 59 -> 48 -> 29 -> 34
        linkedStack.push(34);
        linkedStack.push(29);
        linkedStack.push(48);
        linkedStack.push(59);

        // 48 -> 29 -> 34
        assertEquals((Integer) 59, linkedStack.pop());

        assertEquals(3, linkedStack.size());

        LinkedNode<Integer> curr = linkedStack.getHead();
        assertNotEquals(null, curr);
        assertEquals((Integer) 48, curr.getData());

        curr = curr.getNext();
        assertNotEquals(null, curr);
        assertEquals((Integer) 29, curr.getData());

        curr = curr.getNext();
        assertNotEquals(null, curr);
        assertEquals((Integer) 34, curr.getData());

        curr = curr.getNext();
        assertEquals(null, curr);
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedStackPeek() {
        linkedStack = new LinkedStack<>();
        assertEquals(0, linkedStack.size());

        // 59 -> 48 -> 29 -> 34
        linkedStack.push(34);
        linkedStack.push(29);
        linkedStack.push(48);
        linkedStack.push(59);

        assertEquals((Integer) 59, linkedStack.peek());
        assertEquals(4, linkedStack.size());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayQueueEnqueue() {
        arrayQueue = new ArrayQueue<>();
        assertEquals(0, arrayQueue.size());

        // [34, 29, 38, 59, _, _, _, _, _]
        arrayQueue.enqueue(34);
        arrayQueue.enqueue(29);
        arrayQueue.enqueue(48);
        arrayQueue.enqueue(59);

        assertEquals(4, arrayQueue.size());

        Object[] backingArray = arrayQueue.getBackingArray();

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = 34;
        expected[1] = 29;
        expected[2] = 48;
        expected[3] = 59;

        assertArrayEquals(expected, backingArray);
    }

    @Test(timeout = TIMEOUT)
    public void testArrayQueueDequeue() {
        arrayQueue = new ArrayQueue<>();
        assertEquals(0, arrayQueue.size());

        // [34, 29, 38, 59, _, _, _, _, _]
        arrayQueue.enqueue(34);
        arrayQueue.enqueue(29);
        arrayQueue.enqueue(48);
        arrayQueue.enqueue(59);

        // [_, 29, 38, 59, _, _, _, _, _]
        assertEquals((Integer) 34, arrayQueue.dequeue());

        assertEquals(3, arrayQueue.size());

        Object[] backingArray = arrayQueue.getBackingArray();

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[1] = 29;
        expected[2] = 48;
        expected[3] = 59;

        assertArrayEquals(expected, backingArray);
    }

    @Test(timeout = TIMEOUT)
    public void testArrayQueuePeek() {
        arrayQueue = new ArrayQueue<>();
        assertEquals(0, arrayQueue.size());

        // [34, 29, 38, 59, _, _, _, _, _]
        arrayQueue.enqueue(34);
        arrayQueue.enqueue(29);
        arrayQueue.enqueue(48);
        arrayQueue.enqueue(59);

        assertEquals((Integer) 34, arrayQueue.peek());
        assertEquals(4, arrayQueue.size());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedQueueEnqueue() {
        linkedQueue = new LinkedQueue<>();
        assertEquals(0, linkedQueue.size());

        // 34 -> 29 -> 48 -> 59
        linkedQueue.enqueue(34);
        linkedQueue.enqueue(29);
        linkedQueue.enqueue(48);
        linkedQueue.enqueue(59);

        assertEquals(4, linkedQueue.size());

        LinkedNode<Integer> curr = linkedQueue.getHead();
        assertNotEquals(null, curr);
        assertEquals((Integer) 34, curr.getData());

        curr = curr.getNext();
        assertNotEquals(null, curr);
        assertEquals((Integer) 29, curr.getData());

        curr = curr.getNext();
        assertNotEquals(null, curr);
        assertEquals((Integer) 48, curr.getData());

        curr = curr.getNext();
        assertNotEquals(null, curr);
        assertEquals((Integer) 59, curr.getData());
        assertSame(linkedQueue.getTail(), curr);

        curr = curr.getNext();
        assertEquals(null, curr);
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedQueueDequeue() {
        linkedQueue = new LinkedQueue<>();
        assertEquals(0, linkedQueue.size());

        // 34 -> 29 -> 48 -> 59
        linkedQueue.enqueue(34);
        linkedQueue.enqueue(29);
        linkedQueue.enqueue(48);
        linkedQueue.enqueue(59);

        // 29 -> 48 -> 59
        assertEquals((Integer) 34, linkedQueue.dequeue());

        assertEquals(3, linkedQueue.size());

        LinkedNode<Integer> curr = linkedQueue.getHead();
        assertNotEquals(null, curr);
        assertEquals((Integer) 29, curr.getData());

        curr = curr.getNext();
        assertNotEquals(null, curr);
        assertEquals((Integer) 48, curr.getData());

        curr = curr.getNext();
        assertNotEquals(null, curr);
        assertEquals((Integer) 59, curr.getData());
        assertSame(linkedQueue.getTail(), curr);

        curr = curr.getNext();
        assertEquals(null, curr);
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedQueuePeek() {
        linkedQueue = new LinkedQueue<>();
        assertEquals(0, linkedQueue.size());

        // 34 -> 29 -> 48 -> 59
        linkedQueue.enqueue(34);
        linkedQueue.enqueue(29);
        linkedQueue.enqueue(48);
        linkedQueue.enqueue(59);

        assertEquals((Integer) 34, linkedQueue.peek());
        assertEquals(4, linkedQueue.size());
    }
}