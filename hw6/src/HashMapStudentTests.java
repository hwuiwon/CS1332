import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
/**
 * A set of basic tests to test your HashMap.
 *
 * These tests are NOT exhaustive. Write your own test to ensure code coverage.
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public class HashMapStudentTests {

    private HashMap<Integer, String> map;
    private static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        // [(0, A), (1, B), (2, C), (3, D), (4, E), _, _, _, _, _, _]
        map = new HashMap<>();
        map.put(new Integer(0), "A");
        map.put(new Integer(1), "B");
        map.put(new Integer(2), "C");
        map.put(new Integer(3), "D");
        map.put(new Integer(4), "E");
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(11, HashMap.INITIAL_CAPACITY);
        assertEquals(0.67, HashMap.MAX_LOAD_FACTOR, 0.001);

        map = new HashMap<>();
        assertEquals(0, map.size());
        assertEquals(HashMap.INITIAL_CAPACITY, map.getTable().length);

        map = new HashMap<>(23);
        assertEquals(0, map.size());
        assertEquals(23, map.getTable().length);
    }

    @Test(timeout = TIMEOUT)
    public void testPut() {
        // [(0, A), (1, B), (2, C), (3, D), (4, E), _, _, _, _, _, _] ->
        // [(0, A), (1, B), (2, C), (3, D), (4, E), _, (6, F), _, _, _, _]
        assertEquals(null, map.put(new Integer(6), "F"));
        assertNotEquals(null, map.getTable()[6]);
    }

    @Test(timeout = TIMEOUT)
    public void testResize() {
        // [(0, A), (1, B), (2, C), (3, D), (4, E), _, _, _, _, _, _] ->
        // [(0, A), (1, B), (2, C), (3, D), (4, E)]
        map.resizeBackingTable(5);
        assertEquals(5, map.getTable().length);
    }

    @Test(timeout = TIMEOUT)
    public void testRemove() {
        // [(0, A), (1, B), (2, C), (3, D), (4, E), _, _, _, _, _, _] ->
        // [(0, A), (1, B), (2, C), _, (4, E), _, _, _, _, _, _]
        assertEquals("D", map.remove(new Integer(3)));
        assertEquals(null, map.getTable()[3]);
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        // [(0, A), (1, B), (2, C), (3, D), (4, E), _, _, _, _, _, _]
        assertEquals("D", map.get(new Integer(3)));
    }

    @Test(timeout = TIMEOUT)
    public void testContainsKey() {
        // [(0, A), (1, B), (2, C), (3, D), (4, E), _, _, _, _, _, _]
        assertEquals(true, map.containsKey(new Integer(3)));
        assertEquals(false, map.containsKey(new Integer(5)));
    }

    @Test(timeout = TIMEOUT)
    public void testKeySet() {
        // [(0, A), (1, B), (2, C), (3, D), (4, E), _, _, _, _, _, _]
        Set<Integer> keySet = new HashSet<>();
        keySet.add(0);
        keySet.add(1);
        keySet.add(2);
        keySet.add(3);
        keySet.add(4);

        assertEquals(keySet, map.keySet());
    }

    @Test(timeout = TIMEOUT)
    public void testValues() {
        // [(0, A), (1, B), (2, C), (3, D), (4, E), _, _, _, _, _, _]
        List<String> values = new ArrayList<>();
        values.add("A");
        values.add("B");
        values.add("C");
        values.add("D");
        values.add("E");

        assertEquals(values, map.values());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        // [(0, A), (1, B), (2, C), (3, D), (4, E), _, _, _, _, _, _] ->
        // [_, _, _, _, _, _, _, _, _, _, _]
        map.clear();
        for (int i = 0; i < HashMap.INITIAL_CAPACITY; i++) {
            assertEquals(null, map.getTable()[i]);
        }
        assertEquals(0, map.size());
    }
}