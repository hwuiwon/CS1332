import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

/**
 * Sample JUnit test cases for BST.
 *
 * @version 1.0
 * @author CS 1332 TAs
 */
public class BSTStudentTests {
    private BST<Integer> bst;
    public static final int TIMEOUT = 200;

    @Before
    public void setup() {
        bst = new BST<Integer>();
    }

    @Test(timeout = TIMEOUT)
    public void testAdd() {
        /*
                      2
                     / \
                    1   3
        */
        bst.add(2);
        bst.add(1);
        bst.add(3);

        assertEquals(3, bst.size());
        assertEquals((Integer) 2, bst.getRoot().getData());
        assertEquals((Integer) 1, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 3, bst.getRoot().getRight().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testRemove() {
        /*
                      2                    2
                     / \                  / \
                    1   3                1   4
                         \     ->             \
                          4                    5
                           \
                            5
        */
        bst.add(2);
        bst.add(1);
        bst.add(3);
        bst.add(4);
        bst.add(5);

        assertEquals((Integer) 3, bst.remove(3));
        assertEquals(4, bst.size());
        assertEquals((Integer) 2, bst.getRoot().getData());
        assertEquals((Integer) 1, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 4, bst.getRoot().getRight().getData());
        assertEquals((Integer) 5, bst.getRoot().getRight()
                .getRight().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testGetContains() {
        /*
                       24
                    /      \
                   1        94
                    \      /
                     7    58
                      \    \
                      12    73
        */
        bst.add(24);
        bst.add(1);
        bst.add(7);
        bst.add(12);
        bst.add(94);
        bst.add(58);
        bst.add(73);

        assertTrue(bst.contains(58));
        assertEquals((Integer) 58, bst.get(58));
        assertTrue(bst.contains(12));
        assertEquals((Integer) 12, bst.get(12));
        assertTrue(bst.contains(94));
        assertEquals((Integer) 94, bst.get(94));
        assertTrue(bst.contains(24));
        assertEquals((Integer) 24, bst.get(24));
    }

    @Test(timeout = TIMEOUT)
    public void testGetDifferent() {
        /*
                       24
                    /      \
                   1        94
                    \      /
                     7    58
                      \    \
                      12    73
        */

        Integer testingInteger = new Integer(12);

        bst.add(24);
        bst.add(1);
        bst.add(7);
        bst.add(testingInteger);
        bst.add(94);
        bst.add(58);
        bst.add(73);

        // We want to make sure the data we retrieve is the one from the tree,
        // not the data that was passed in.
        assertSame(testingInteger, bst.get(new Integer(12)));
    }

    @Test(timeout = TIMEOUT)
    public void testLevelorder() {
        /*
                       24
                    /      \
                   1        94
                    \      /
                     7    58
                      \    \
                      12    73
                            / \
                           68  77
        */

        bst.add(24);
        bst.add(1);
        bst.add(7);
        bst.add(12);
        bst.add(94);
        bst.add(58);
        bst.add(73);
        bst.add(77);
        bst.add(68);

        List<Integer> levelorder = new ArrayList<>();
        levelorder.add(24);
        levelorder.add(1);
        levelorder.add(94);
        levelorder.add(7);
        levelorder.add(58);
        levelorder.add(12);
        levelorder.add(73);
        levelorder.add(68);
        levelorder.add(77);

        // Should be [24, 1, 94, 7, 58, 12, 73, 68, 77]
        assertEquals(levelorder, bst.levelorder());
    }

    @Test(timeout = TIMEOUT)
    public void testPreorder() {
        /*
                       24
                    /      \
                   1        94
                    \      /
                     7    58
                      \    \
                      12    73
                            / \
                           68  77
        */

        bst.add(24);
        bst.add(1);
        bst.add(7);
        bst.add(12);
        bst.add(94);
        bst.add(58);
        bst.add(73);
        bst.add(77);
        bst.add(68);

        List<Integer> preorder = new ArrayList<>();
        preorder.add(24);
        preorder.add(1);
        preorder.add(7);
        preorder.add(12);
        preorder.add(94);
        preorder.add(58);
        preorder.add(73);
        preorder.add(68);
        preorder.add(77);

        // Should be [24, 1, 7, 12, 94, 58, 73, 68, 77]
        assertEquals(preorder, bst.preorder());
    }

    @Test(timeout = TIMEOUT)
    public void testInorder() {
        /*
                       24
                    /      \
                   1        94
                    \      /
                     7    58
                      \    \
                      12    73
                            / \
                           68  77
        */

        bst.add(24);
        bst.add(1);
        bst.add(7);
        bst.add(12);
        bst.add(94);
        bst.add(58);
        bst.add(73);
        bst.add(77);
        bst.add(68);

        List<Integer> inorder = new ArrayList<>();
        inorder.add(1);
        inorder.add(7);
        inorder.add(12);
        inorder.add(24);
        inorder.add(58);
        inorder.add(68);
        inorder.add(73);
        inorder.add(77);
        inorder.add(94);

        // Should be [1, 7, 12, 24, 58, 68, 73, 77, 94]
        assertEquals(inorder, bst.inorder());
    }

    @Test(timeout = TIMEOUT)
    public void testPostorder() {
        /*
                       24
                    /      \
                   1        94
                    \      /
                     7    58
                      \    \
                      12    73
                            / \
                           68  77
        */

        bst.add(24);
        bst.add(1);
        bst.add(7);
        bst.add(12);
        bst.add(94);
        bst.add(58);
        bst.add(73);
        bst.add(77);
        bst.add(68);

        List<Integer> postorder = new ArrayList<>();
        postorder.add(12);
        postorder.add(7);
        postorder.add(1);
        postorder.add(68);
        postorder.add(77);
        postorder.add(73);
        postorder.add(58);
        postorder.add(94);
        postorder.add(24);

        // Should be [12, 7, 1, 68, 77, 73, 58, 94, 24]
        assertEquals(postorder, bst.postorder());
    }

    @Test(timeout = TIMEOUT)
    public void testIsBST() {
        /*
                    50
                  /    \   should return false
                75      25
        */

        BSTNode<Integer> root = new BSTNode<>(50);
        root.setLeft(new BSTNode<>(75));
        root.setRight(new BSTNode<>(25));

        assertEquals(false, BST.isBST(root));

        /*
                    50
                  /    \   should return true
                25      75
        */

        root = new BSTNode<>(50);
        root.setLeft(new BSTNode<>(25));
        root.setRight(new BSTNode<>(75));

        assertEquals(true, BST.isBST(root));
    }

    @Test(timeout = TIMEOUT)
    public void testConstructorAndClear() {
        /*
                     24
                    /
                   1
                    \
                     7
        */

        List<Integer> toAdd = new ArrayList<>();
        toAdd.add(24);
        toAdd.add(1);
        toAdd.add(7);
        bst = new BST<>(toAdd);

        assertEquals((Integer) 24, bst.getRoot().getData());
        assertEquals((Integer) 1, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 7, bst.getRoot().getLeft().getRight().getData());

        bst.clear();
        assertEquals(null, bst.getRoot());
        assertEquals(0, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void testHeight() {
        /*
                     24
                    /
                   1
                    \
                     7
        */

        bst.add(24);
        bst.add(1);
        bst.add(7);

        assertEquals(2, bst.height());
    }
}