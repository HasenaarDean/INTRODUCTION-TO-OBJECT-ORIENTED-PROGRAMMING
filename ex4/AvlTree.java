package oop.ex4.data_structures;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * This class is an implementation of the AVL tree data structure for integers.
 * It has a subclass named TreeNode which represents the tree's nodes.
 * In addition, the AvlTree class implements Iterable so that any AVL Tree object
 * is iterable.
 */


public class AvlTree implements Iterable<Integer>{

    // The tree's root.
    private TreeNode root;

    // The size of the tree (the number of nodes in the tree).
    private int size;

    // linked list which helps iterating over the AVL tree.
    private LinkedList<Integer> linkedList = new LinkedList<Integer>();

    //Constants:
    private static final int NOT_FOUND = -1;
    private static final int LEFT_VIOLATION = -2;
    private static final int RIGHT_VIOLATION = 2;

    /**
     * This private class represents the nodes of the AVL Tree.
     */
    private class TreeNode{
        private int data;
        private int balance;
        private int height;
        private TreeNode parent;
        private TreeNode leftChild;
        private TreeNode rightChild;

        /**
         * This is the constructor of the subclass.
         * @param intNumber the number stored in the node.
         */
        TreeNode(int intNumber) {

            this.data = intNumber;
        }

        /**
         * This is another constructor of the subclass.
         * @param intNumber the number stored in the node.
         * @param parent the parent of the node.
         */
        TreeNode(int intNumber, TreeNode parent) {
            this.data = intNumber;
            this.parent = parent;
        }
    }

    /**
     * The default constructor.
     */
    public AvlTree(){

        this.root = null;
    }

    /**
     * A constructor that builds a new AVL tree containing all unique values in the input array.
     * @param data - the values to add to tree.
     */
    public AvlTree(int[] data) {
        if (data == null || data.length == 0){
            throw new NullPointerException();}
        else{
            this.root = new TreeNode(data[0]);
            this.size += 1;
            for (int num : data){
                add(num);
            }
        }
    }

    /**
     * A copy constructor that creates a deep copy of the given AvlTree. The new tree contains all the
     * values of the given tree, but not necessarily in the same structure.
     * @param avlTree - an AVL tree.
     */
    public AvlTree(AvlTree avlTree){
        if (avlTree == null){
            throw new NullPointerException();
        }
        for(int num : avlTree) {
            add(num);
        }
    }


    /**
     * Add a new node with the given key to the tree.
     * @param newValue - the value of the new node to add.
     * @return true if the value to add is not already in the tree and it was successfully added, false
     * otherwise.
     */
    public boolean add(int newValue){
        if (root == null){
            root = new TreeNode(newValue, null);
            size++;
        }

        else {
            TreeNode tempNode = root;
            TreeNode parent;
            while (true){
                if(tempNode.data == newValue)
                    return false;
                parent = tempNode;

                boolean shouldGoLeft = tempNode.data > newValue;
                tempNode = shouldGoLeft ? tempNode.leftChild : tempNode.rightChild;

                if(tempNode == null){
                    if(shouldGoLeft){
                        parent.leftChild = new TreeNode(newValue, parent);
                        size++;
                    } else{
                        parent.rightChild = new TreeNode(newValue, parent);
                        size++;
                    }
                    keepBalanced(parent);
                    break;
                }
            }
        }
        return true;
    }

    /**
     * Check whether the tree contains the given input value.
     * @param searchVal - the value to search for
     * @return the depth of the node (0 for the root) with the given value if it was found in the tree,
     * -1 otherwise.
     */
    public int contains(int searchVal){
        int depth = 0;
        if(root == null)
            return NOT_FOUND;
        else {
            TreeNode tempNode = root;
            while (tempNode != null){
                if(tempNode.data == searchVal)
                    return depth;
                boolean shouldGoLeft = tempNode.data > searchVal;
                tempNode = shouldGoLeft ? tempNode.leftChild : tempNode.rightChild;
                depth++;
            }
            return NOT_FOUND;
        }
    }


    /**
     * Removes the node with the given value from the tree, if it exists.
     * @param toDelete - the value to remove from the tree.
     * @return true if the given value was found and deleted, false otherwise.
     */
    public boolean delete(int toDelete){
        if (root == null)
            return false;
        TreeNode node;
        TreeNode child = root;

        while(child != null){
            node = child;
            child = (toDelete >= node.data) ? node.rightChild : node.leftChild;
            if (toDelete == node.data){
                deleteNode(node);
                size--;
                return true;
            }
        }
        return false;
    }

    /**
     * Removes the node from the AVL Tree.
     * @param node the node that needs to be removed.
     */
    private void deleteNode(TreeNode node){
        if(node.leftChild == null && node.rightChild == null){
            if(node.parent == null)
                root = null;
            else{
                TreeNode parent = node.parent;
                if(parent.leftChild == node)
                    parent.leftChild = null;
                else parent.rightChild = null;
                keepBalanced(parent);
            }
            return;
        }
        if(node.leftChild != null){
            TreeNode child = node.leftChild;
            while(child.rightChild != null) child = child.rightChild;
            node.data = child.data;
            deleteNode(child);
        }
        else {
            TreeNode child = node.rightChild;
            while (child.leftChild != null) child = child.leftChild;
            node.data = child.data;
            deleteNode(child);
        }
    }

    /**
     * This method does a left rotation, and is called when there is
     * a left balance violation.
     * @param node1 the node that should be rotated left.
     * @return the new parent of node1.
     */
    private TreeNode leftRotation(TreeNode node1){
        TreeNode node2 = node1.rightChild;
        node2.parent = node1.parent;
        node1.rightChild = node2.leftChild;

        if(node1.rightChild != null)
            node1.rightChild.parent = node1;
        node2.leftChild = node1;
        node1.parent = node2;

        if(node2.parent != null){
            if(node2.parent.rightChild == node1)
                node2.parent.rightChild = node2;
            else {
                node2.parent.leftChild = node2;
            }
        }
        initializeBalance(node1, node2);
        return node2;
    }

    /**
     * This method does a right rotation, and is called when there is
     * a right balance violation.
     * @param node1 the node that should be rotated right.
     * @return the new parent of node1.
     */
    private TreeNode rightRotation(TreeNode node1){
        TreeNode node2 = node1.leftChild;
        node2.parent = node1.parent;

        node1.leftChild = node2.rightChild;
        if(node1.leftChild != null)
            node1.leftChild.parent = node1;

        node2.rightChild = node1;
        node1.parent = node2;

        if(node2.parent != null){
            if(node2.parent.rightChild == node1)
                node2.parent.rightChild = node2;
            else {
                node2.parent.leftChild = node2;
            }
        }
        initializeBalance(node1, node2);
        return node2;
    }

    /**
     * This method does a right-then-left rotation, and is called when there is
     * a right-then-left balance violation.
     * @param node the node that should be rotated right-then-left.
     * @return the new parent of node.
     */
    private TreeNode rightLeftRotation(TreeNode node){
        node.rightChild = rightRotation(node.rightChild);
        return leftRotation(node);
    }

    /**
     * This method does a left-then-right rotation, and is called when there is
     * a left-then-right balance violation.
     * @param node the node that should be rotated left-then-right.
     * @return the new parent of node.
     */
    private TreeNode leftRightRotation(TreeNode node){
        node.leftChild = leftRotation(node.leftChild);
        return rightRotation(node);
    }

    /**
     * This method fixes the Tree Nodes' balance when needed.
     * @param nodes the nodes that need to be fixed.
     */
    private void initializeBalance(TreeNode... nodes){
        for(TreeNode node : nodes){
            fixHeight(node);
            node.balance = checkHeight(node.rightChild) - checkHeight(node.leftChild);
        }
    }

    /**
     * This method fixes the balance of the AVL Tree when deleting or adding to the tree.
     * @param node the node that we start to fix from.
     */
    private void keepBalanced(TreeNode node){
        initializeBalance(node);
        if(node.balance == LEFT_VIOLATION){
            if(checkHeight(node.leftChild.leftChild) >= checkHeight(node.leftChild.rightChild))
                node = rightRotation(node);
            else {
                node = leftRightRotation(node);
            }

        } else if(node.balance == RIGHT_VIOLATION) {
            if(checkHeight(node.rightChild.rightChild) >= checkHeight(node.rightChild.leftChild))
                node = leftRotation(node);
            else {
                node = rightLeftRotation(node);
            }
        } if (node.parent != null)
            keepBalanced(node.parent);
        else {
            root = node;
        }

    }

    /**
     * This method fixes the height of the input Tree Node.
     * @param node the node that need to be fixed.
     */
    private void fixHeight(TreeNode node){
        if(node != null)
            node.height = 1 + Math.max(checkHeight(node.leftChild), checkHeight(node.rightChild));
    }

    /**
     * This method returns the node's height, or -1 if the node is null.
     * @param node the node that we want to get its height.
     * @return the node's height, or -1 if the node is null.
     */
    private int checkHeight(TreeNode node){
        if(node == null)
            return NOT_FOUND;
        return node.height;
    }

    /**
     * @return the number of nodes in the tree.
     */
    public int size(){

        return size;
    }

    /**
     * @return an iterator for the Avl Tree. The returned iterator iterates over the tree nodes in an
     * ascending order, and does NOT implement the remove() method.
     */
    public Iterator<Integer> iterator(){
        linkedList.clear();
        if(root != null)
            iteratorHelper(root);
        return linkedList.listIterator();
    }

    /**
     * This method helps iterating over the AVL Tree nodes.
     * @param node the node that we start iterating from.
     */
    private void iteratorHelper(TreeNode node){
        if (node.leftChild != null)
            iteratorHelper(node.leftChild);
        linkedList.add(node.data);

        if (node.rightChild != null)
            iteratorHelper(node.rightChild);
    }


    /**
     * Calculates the minimum number of nodes in an AVL tree of height h.
     * @param h - The height of the tree (a non-negative number) in question.
     * @return the minimum number of nodes in an AVL tree of the given height.
     */
    public static int findMinNodes(int h){
        return (int)(Math.round(((Math.sqrt(5) + 2) / Math.sqrt(5)) *
                Math.pow((1 + Math.sqrt(5)) / 2, h) - 1));
    }

    /**
     * Calculates the maximum number of nodes in an AVL tree of height h.
     * @param h - The height of the tree (a non-negative number) in question.
     * @return the maximum number of nodes in an AVL tree of the given height.
     */
    public static int findMaxNodes(int h){

        return (int)Math.pow(2, h + 1) - 1;
    }
}
