import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Your implementation of a binary search tree.
 *
 * @author Hwuiwon Kim
 * @userid hkim944
 * @version 1.0
 */
public class BST<T extends Comparable<? super T>> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no-argument constructor that should initialize an empty BST.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Initializes the BST with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * Hint: Not all Collections are indexable like Lists, so a regular for loop
     * will not work here. However, all Collections are Iterable, so what type
     * of loop would work?
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        if (data == null || data.contains(null)) {
            throw new IllegalArgumentException("Data can't be null");
        }
        size = 0;
        for (T tmp : data) {
            add(tmp);
        }
    }

    /**
     * Add the data as a leaf in the BST. Should traverse the tree to find the
     * appropriate location. If the data is already in the tree, then nothing
     * should be done (the duplicate shouldn't get added, and size should not be
     * incremented).
     * 
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n).
     *
     * @throws IllegalArgumentException if the data is null
     * @param data the data to be added
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data can't be null");
        }
        if (root == null) {
            root = new BSTNode<>(data);
            size++;
        } else {
            addHelper(data, root);
        }
    }

    /**
     * Helper method for add(T data)
     *
     * @param data the data to be added
     * @param node the root of a tree
     */
    private void addHelper(T data, BSTNode<T> node) {
        if (data.compareTo(node.getData()) > 0) {
            if (node.getRight() == null) {
                node.setRight(new BSTNode<>(data));
                size++;
            } else {
                addHelper(data, node.getRight());
            }
        } else if (data.compareTo(node.getData()) < 0) {
            if (node.getLeft() == null) {
                node.setLeft(new BSTNode<>(data));
                size++;
            } else {
                addHelper(data, node.getLeft());
            }
        }
    }

    /**
     * Removes the data from the tree. There are 3 cases to consider:
     *
     * 1: the data is a leaf (no children). In this case, simply remove it.
     * 2: the data has one child. In this case, simply replace it with its
     * child.
     * 3: the data has 2 children. Use the predecessor to replace the data.
     * You MUST use recursion to find and remove the predecessor (you will
     * likely need an additional helper method to handle this case efficiently).
     *
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n).
     *
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     * @param data the data to remove from the tree.
     * @return the data removed from the tree. Do not return the same data
     * that was passed in. Return the data that was stored in the tree.
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data can't be null");
        }
        BSTNode<T> removed = new BSTNode<>(null);
        root = removeHelper(data, root, removed);
        return removed.getData();
    }

    /**
     * Helper method for remove(T data)
     *
     * @param data the data to remove from the tree
     * @param node the current node
     * @param removed the node to be removed
     * @return parent node of node that will get removed
     */
    private BSTNode<T> removeHelper(T data,
                                    BSTNode<T> node, BSTNode<T> removed) {
        if (node == null) {
            throw new NoSuchElementException("Data is not found");
        } else {
            int value = data.compareTo(node.getData());
            if (value > 0) {
                node.setRight(removeHelper(data, node.getRight(), removed));
            } else if (value < 0) {
                node.setLeft(removeHelper(data, node.getLeft(), removed));
            } else {
                removed.setData(node.getData());
                size--;
                if (node.getRight() == null) {
                    return node.getLeft();
                } else if (node.getLeft() == null) {
                    return node.getRight();
                } else {
                    BSTNode<T> child = new BSTNode<>(null);
                    node.setLeft(predecessorHelper(node.getLeft(), child));
                    node.setData(child.getData());
                }
            }
        }
        return node;
    }

    /**
     * Helper method for remove()
     * Finds predecessor node
     *
     * @param node the current node
     * @param child the child of a node that will be removed
     * @return predecessor node of an element that will be removed
     */
    private BSTNode<T> predecessorHelper(BSTNode<T> node, BSTNode<T> child) {
        if (node.getRight() == null) {
            child.setData(node.getData());
            return node.getLeft();
        }
        node.setRight(predecessorHelper(node.getRight(), child));
        return node;
    }

    /**
     * Returns the data in the tree matching the parameter passed in (think
     * carefully: should you use value equality or reference equality?).
     *
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n).
     *
     * @throws IllegalArgumentException if the data is null
     * @throws java.util.NoSuchElementException if the data is not found
     * @param data the data to search for in the tree.
     * @return the data in the tree equal to the parameter. Do not return the
     * same data that was passed in.  Return the data that was stored in the
     * tree.
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data can't be null");
        }
        return getHelper(data, root);
    }

    /**
     * Helper method for get(T data)
     *
     * @param data the data to search for
     * @param node the root node to inspect
     * @return data of a node that matches passed in parameter
     */
    private T getHelper(T data, BSTNode<T> node) {
        if (node == null) {
            throw new NoSuchElementException("Data is not found");
        }
        int value = data.compareTo(node.getData());
        if (data.equals(node.getData())) {
            return node.getData();
        } else if (value < 0) {
            return getHelper(data, node.getLeft());
        } else if (value > 0) {
            return getHelper(data, node.getRight());
        }
        return null;
    }

    /**
     * Returns whether or not data equivalent to the given parameter is
     * contained within the tree. The same type of equality should be used as
     * in the get method.
     *
     * Should have a running time of O(log n) for a balanced tree, and a worst
     * case of O(n).
     *
     * @throws IllegalArgumentException if the data is null
     * @param data the data to search for in the tree.
     * @return whether or not the parameter is contained within the tree.
     */
    public boolean contains(T data) {
        try {
            get(data);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    /**
     * Should run in O(n).
     *
     * @return a preorder traversal of the tree
     */
    public List<T> preorder() {
        List<T> data = new ArrayList<>();
        preorderHelper(data, root);
        return data;
    }

    /**
     * Helper method for preorder()
     *
     * @param list the list that stores the data
     * @param node the root node of a tree
     */
    private void preorderHelper(List<T> list, BSTNode<T> node) {
        if (node == null) {
            return;
        } else {
            list.add(node.getData());
            preorderHelper(list, node.getLeft());
            preorderHelper(list, node.getRight());
        }
    }

    /**
     * Should run in O(n).
     *
     * @return an inorder traversal of the tree
     */
    public List<T> inorder() {
        List<T> data = new ArrayList<>();
        inorderHelper(data, root);
        return data;
    }

    /**
     * Helper method for inorder()
     *
     * @param list the list that stores the data
     * @param node the root node of a tree
     */
    private void inorderHelper(List<T> list, BSTNode<T> node) {
        if (node == null) {
            return;
        } else {
            inorderHelper(list, node.getLeft());
            list.add(node.getData());
            inorderHelper(list, node.getRight());
        }
    }

    /**
     * Should run in O(n).
     *
     * @return a postorder traversal of the tree
     */
    public List<T> postorder() {
        List<T> data = new ArrayList<>();
        postorderHelper(data, root);
        return data;
    }

    /**
     * Helper method for postorder()
     *
     * @param list the list that stores the data
     * @param node the root node of a tree
     */
    private void postorderHelper(List<T> list, BSTNode<T> node) {
        if (node == null) {
            return;
        } else {
            postorderHelper(list, node.getLeft());
            postorderHelper(list, node.getRight());
            list.add(node.getData());
        }
    }

    /**
     * Generate a level-order traversal of the tree.
     *
     * To do this, add the root node to a queue. Then, while the queue isn't
     * empty, remove one node, add its data to the list being returned, and add
     * its left and right child nodes to the queue. If what you just removed is
     * {@code null}, ignore it and continue with the rest of the nodes.
     *
     * Should run in O(n). This does not need to be done recursively.
     *
     * @return a level order traversal of the tree
     */
    public List<T> levelorder() {
        Queue<BSTNode<T>> queue = new LinkedList<>();
        List<T> data = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BSTNode<T> tmp = queue.poll();
            data.add(tmp.getData());
            if (tmp.getLeft() != null) {
                queue.add(tmp.getLeft());
            }
            if (tmp.getRight() != null) {
                queue.add(tmp.getRight());
            }
        }
        return data;
    }

    /**
     * This method checks whether a binary tree meets the criteria for being
     * a binary search tree.
     *
     * This method is a static method that takes in a BSTNode called
     * {@code treeRoot}, which is the root of the tree that you should check.
     *
     * You may assume that the tree passed in is a proper binary tree; that is,
     * there are no loops in the tree, the parent-child relationship is
     * correct, that there are no duplicates, and that every parent has at
     * most 2 children. So, what you will have to check is that the order
     * property of a BST is still satisfied.
     *
     * Should run in O(n). However, you should stop the check as soon as you
     * find evidence that the tree is not a BST rather than checking the rest
     * of the tree.
     *
     * @param <T> the generic typing
     * @param treeRoot the root of the binary tree to check
     * @return true if the binary tree is a BST, false otherwise
     */
    public static <T extends Comparable<? super T>> boolean isBST(
            BSTNode<T> treeRoot) {
        return isBSTHelper(treeRoot);
    }

    /**
     * Helper method for isBST(BSTNode treeRoot)
     *
     * @param node the root of the binary tree to check
     * @param <T> the generic typing
     * @return boolean value indicating if the tree is BST
     */
    private static <T extends Comparable<? super T>> boolean isBSTHelper(
            BSTNode<T> node) {
        if (node != null) {
            if (node.getLeft() != null) {
                if (node.getData().compareTo(node.getLeft().getData()) < 0) {
                    return false;
                } else {
                    return isBSTHelper(node.getLeft());
                }
            }
            if (node.getRight() != null) {
                if (node.getData().compareTo(node.getRight().getData()) > 0) {
                    return false;
                } else {
                    return isBSTHelper(node.getRight());
                }
            }
        }
        return true;
    }

    /**
     * Clears the tree.
     *
     * Should run in O(1).
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Calculate and return the height of the root of the tree. A node's
     * height is defined as {@code max(left.height, right.height) + 1}. A leaf
     * node has a height of 0 and a null child should be -1.
     *
     * Should be calculated in O(n).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return heightHelper(root);
    }

    /**
     * Helper method for height()
     *
     * @param node the starter node to find the height from
     * @return height of a node
     */
    private int heightHelper(BSTNode<T> node) {
        return node == null ? -1
            : Math.max(heightHelper(node.getLeft()),
                heightHelper(node.getRight())) + 1;
    }

    /**
     * Returns the size of the BST.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the number of elements in the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * Returns the root of the BST.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}