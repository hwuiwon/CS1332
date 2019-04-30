import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * These tests are not exhaustive.
 * @author CS 1332 TAs
 * @version 1.0
 */
public class AVLStudentTests {
    private static final int TIMEOUT = 200;
    private AVL<Integer> avlTree;

    @Before
    public void setup() {
        avlTree = new AVL<>();
    }

    @Test(timeout = TIMEOUT)
    public void testAddRightRotation() {
        /*
                    5                   4
                   /                   / \
                  4         ->        3   5
                 /
                3
         */
        avlTree.add(5);
        avlTree.add(4);
        avlTree.add(3);

        assertEquals(3, avlTree.size());

        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 4, root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        assertEquals((Integer) 3, root.getLeft().getData());
        assertEquals(0, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getBalanceFactor());
        assertEquals((Integer) 5, root.getRight().getData());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testAddRightLeftRotationRoot() {
        /*
                3               4
                 \             / \
                  5     ->    3   5
                 /
                4
         */
        avlTree.add(3);
        avlTree.add(5);
        avlTree.add(4);

        assertEquals(3, avlTree.size());

        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 4, root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        assertEquals((Integer) 3, root.getLeft().getData());
        assertEquals(0, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getBalanceFactor());
        assertEquals((Integer) 5, root.getRight().getData());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testRemove() {
        /*
                    646                     646
                   /   \                   /   \
                 477   856      ->       526   856
                 / \                     /
               386 526                 386
         */
        Integer toBeRemoved = new Integer(477);
        avlTree.add(646);
        avlTree.add(toBeRemoved);
        avlTree.add(856);
        avlTree.add(386);
        avlTree.add(526);

        assertSame(toBeRemoved, avlTree.remove(new Integer(477)));

        assertEquals(4, avlTree.size());

        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 646, root.getData());
        assertEquals(2, root.getHeight());
        assertEquals(1, root.getBalanceFactor());
        assertEquals((Integer) 526, root.getLeft().getData());
        assertEquals(1, root.getLeft().getHeight());
        assertEquals(1, root.getLeft().getBalanceFactor());
        assertEquals((Integer) 856, root.getRight().getData());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());
        assertEquals((Integer) 386, root.getLeft().getLeft().getData());
        assertEquals(0, root.getLeft().getLeft().getHeight());
        assertEquals(0, root.getLeft().getLeft().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        /*
                        477
                       /   \
                     386   526
                              \
                              646
         */
        Integer maximum = new Integer(646);
        avlTree.add(477);
        avlTree.add(526);
        avlTree.add(386);
        avlTree.add(maximum);

        assertSame(maximum, avlTree.get(new Integer(646)));
    }

    @Test(timeout = TIMEOUT)
    public void testContains() {
        /*
                        477
                       /   \
                     386   526
                              \
                              646
         */
        avlTree.add(new Integer(477));
        avlTree.add(new Integer(526));
        avlTree.add(new Integer(386));
        avlTree.add(new Integer(646));

        assertEquals(true, avlTree.contains(new Integer(477)));
        assertEquals(true, avlTree.contains(new Integer(386)));
        assertEquals(true, avlTree.contains(new Integer(646)));
        assertEquals(false, avlTree.contains(new Integer(387)));
        assertEquals(false, avlTree.contains(new Integer(700)));
        assertEquals(false, avlTree.contains(new Integer(500)));
    }



    @Test(timeout = TIMEOUT)
    public void testHeight() {
        /*
                    646
                   /   \
                 386   856
                 / \
               526 477
         */
        avlTree.add(646);
        avlTree.add(386);
        avlTree.add(856);
        avlTree.add(526);
        avlTree.add(477);

        assertEquals(2, avlTree.height());
    }

    @Test(timeout = TIMEOUT)
    public void testDeepestBranches() {
        /*
                                10
                            /        \
                           5          15
                         /   \      /    \
                        2     7    13    20
                       / \   / \     \  / \
                      1   4 6   8   14 17  25
                     /           \          \
                    0             9         30
         */

        avlTree.add(10);
        avlTree.add(5);
        avlTree.add(15);
        avlTree.add(2);
        avlTree.add(7);
        avlTree.add(13);
        avlTree.add(20);
        avlTree.add(1);
        avlTree.add(4);
        avlTree.add(6);
        avlTree.add(8);
        avlTree.add(14);
        avlTree.add(17);
        avlTree.add(25);
        avlTree.add(0);
        avlTree.add(9);
        avlTree.add(30);

        List<Integer> expected = new ArrayList<>();
        expected.add(10);
        expected.add(5);
        expected.add(2);
        expected.add(1);
        expected.add(0);
        expected.add(7);
        expected.add(8);
        expected.add(9);
        expected.add(15);
        expected.add(20);
        expected.add(25);
        expected.add(30);

        assertEquals(expected, avlTree.deepestBranches());
    }

    @Test(timeout = TIMEOUT)
    public void testSortedInBetween() {
        /*
                                10
                            /        \
                           5          15
                         /   \      /    \
                        2     7    13    20
                       / \   / \     \  / \
                      1   4 6   8   14 17  25
                     /           \          \
                    0             9         30
         */

        avlTree.add(10);
        avlTree.add(5);
        avlTree.add(15);
        avlTree.add(2);
        avlTree.add(7);
        avlTree.add(13);
        avlTree.add(20);
        avlTree.add(1);
        avlTree.add(4);
        avlTree.add(6);
        avlTree.add(8);
        avlTree.add(14);
        avlTree.add(17);
        avlTree.add(25);
        avlTree.add(0);
        avlTree.add(9);
        avlTree.add(30);

        List<Integer> expected = new ArrayList<>();
        expected.add(8);
        expected.add(9);
        expected.add(10);
        expected.add(13);

        assertEquals(expected, avlTree.sortedInBetween(
            new Integer(7), new Integer(14)));
    }

    @Test(timeout = TIMEOUT)
    public void testConstructorAndClear() {
        /*
                     7
                    / \
                   1   24
        */

        List<Integer> toAdd = new ArrayList<>();
        toAdd.add(7);
        toAdd.add(24);
        toAdd.add(1);
        avlTree = new AVL<>(toAdd);

        avlTree.clear();
        assertEquals(null, avlTree.getRoot());
        assertEquals(0, avlTree.size());
    }
}