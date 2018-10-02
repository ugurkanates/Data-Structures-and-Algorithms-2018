package Question3;

/*<listing chapter="9" section="2">*/
/**

 */
public class AVLTree<E extends Comparable<E>>
        extends BinarySearchTreeWithRotate<E> {

    public static String[] METHODS = {"insert", "remove", "find", "display", "quit"};

    // Insert nested class AVLNode<E> here.
    /*<listing chapter="9" number="2">*/

    /**
     * Class to represent an AVL Node. It extends the
     * BinaryTree.Node by adding the balance field.
     */
    private static class AVLNode<E> extends Node<E> {

        /**
         * Constant to indicate left-heavy
         */
        public static final int LEFT_HEAVY = -1;
        /**
         * Constant to indicate balanced
         */
        public static final int BALANCED = 0;
        /**
         * Constant to indicate right-heavy
         */
        public static final int RIGHT_HEAVY = 1;
        /**
         * balance is right subtree height - left subtree height
         */
        private int balance;

        // Methods

        /**
         * Construct a node with the given item as the data field.
         *
         * @param item The data field
         */
        public AVLNode(E item) {
            super(item);
            balance = BALANCED;
        }

        /**
         * Return a string representation of this object.
         * The balance value is appended to the contents.
         *
         * @return String representation of this object
         */
        @Override
        public String toString() {
            return balance + ": " + super.toString();
        }
    }
    /*</listing>*/
    // Data Fields
    /**
     * Flag to indicate that height of tree has increased.
     */
    private boolean increase;
    /**
     * Flag to indicate that height of tree has decreased.
     */
    private boolean decrease;

    // Methods
    public static boolean isMethodSupported(String method) {
        method = method.toLowerCase();
        for (String m : METHODS) {
            if (method.equals(m))
                return true;
        }
        return false;
    }

    /**
     * add starter method.
     *
     * @param item The item being inserted.
     * @return true if the object is inserted; false
     * if the object already exists in the tree
     * @throws ClassCastException if item is not Comparable
     *  the item to insert implements the Comparable interface.
     */
    public boolean insert(E item) {
        increase = false;
        root = add((AVLNode<E>) root, item);
        return addReturn;
    }

    /**
     * Recursive add method. Inserts the given object into the tree.
     *
     * @param localRoot The local root of the subtree
     * @param item      The object to be inserted
     * @return The new local root of the subtree with the item
     * inserted
     *  addReturn is set true if the item is inserted,
     * false if the item is already in the tree.
     */
    private AVLNode<E> add(AVLNode<E> localRoot, E item) {
        if (localRoot == null) {
            addReturn = true;
            increase = true;
            return new AVLNode<E>(item);
        }

        if (item.compareTo(localRoot.data) == 0) {
            // Item is already in the tree.
            increase = false;
            addReturn = false;
            return localRoot;
        } else if (item.compareTo(localRoot.data) < 0) {
            // item < data
            localRoot.left = add((AVLNode<E>) localRoot.left, item);

            if (increase) {
                decrementBalance(localRoot);
                if (localRoot.balance < AVLNode.LEFT_HEAVY) {
                    increase = false;
                    return rebalanceLeft(localRoot);
                }
            }
            return localRoot; // Rebalance not needed.
        } else { // item > data
// Insert solution to programming exercise 2, section 2, chapter 9 here
            localRoot.right = add((AVLNode<E>) localRoot.right, item);

            if (increase) {
                incrementBalance(localRoot);
                if (localRoot.balance > AVLNode.RIGHT_HEAVY) {
                    increase = false;
                    return rebalanceRight(localRoot);
                }
            }
            return localRoot;
        }
    }

    // Insert solution to programming project 5, chapter -1 here

    /**
     * Starter method remove.
     *
     * @param target The object to be deleted
     * @return The object deleted from the tree
     * or null if the object was not in the tree
     * @throws ClassCastException if target does not implement
     *                            Comparable
     * The object is not in the tree.
     */
    public E remove(E target) {
        decrease = false;
        deleteReturn = null;
        root = delete((AVLNode<E>) root, target);
        return deleteReturn;
    }

    /**
     * Recursive delete method.
     *
     * @param localRoot The root of the current subtree
     * @param item      The item to be deleted
     * @return The modified local root that does not contain
     * the item
     *  The item is not in the tree;
     * deleteReturn is equal to the deleted item
     * as it was stored in the tree or null
     * if the item was not found.
     */
    private Node<E> delete(AVLNode<E> localRoot, E item) {
        if (localRoot == null) {
            // item is not in the tree.
            deleteReturn = null;
            return null;
        }

        // Search for item to delete.
        int compResult = item.compareTo(localRoot.data);
        if (compResult < 0) {
            // item is smaller than localRoot.data.
            localRoot.left = delete((AVLNode<E>) localRoot.left, item);
            if (decrease) {
                incrementBalance(localRoot);
                if (localRoot.balance > AVLNode.RIGHT_HEAVY) {
                    return rebalanceRight(localRoot);
                }
            }
            return localRoot;
        } else if (compResult > 0) {
            // item is larger than localRoot.data.
            localRoot.right = delete((AVLNode<E>) localRoot.right, item);

            if (decrease) {
                decrementBalance(localRoot);
                if (localRoot.balance < AVLNode.LEFT_HEAVY) {
                    return rebalanceLeft(localRoot);
                }
            }
            return localRoot;
        } else {
            // item is at local root.
            deleteReturn = localRoot.data;
            decrease = true;

            if (localRoot.left == null) {
                // If there is no left child, return right child
                // which can also be null.
                return localRoot.right;

            } else if (localRoot.right == null) {
                // If there is no right child, return left child.
                return localRoot.left;

            } else {
                // Node being deleted has 2 children, replace the data
                // with inorder predecessor.
                if (localRoot.left.right == null) {
                    // The left child has no right child.
                    // Replace the data with the data in the
                    // left child.
                    localRoot.data = localRoot.left.data;
                    // Replace the left child with its left child.
                    localRoot.left = localRoot.left.left;

                    incrementBalance(localRoot);
                    if (localRoot.balance > AVLNode.RIGHT_HEAVY) {
                        return rebalanceRight(localRoot);
                    }
                    return localRoot;

                } else {
                    // Search for the inorder predecessor (ip) and
                    // replace deleted node's data with ip.
                    localRoot.data = findLargestChild(localRoot.left);

                    decrementBalance(localRoot);
                    if (localRoot.balance < AVLNode.LEFT_HEAVY) {
                        return rebalanceLeft(localRoot);
                    }
                    return localRoot;
                }
            }
        }
    }

    /*<listing chapter="9" number="3">*/

    /**
     * Method to rebalance left.
     *
     * @param localRoot Root of the AVL subtree
     *                  that needs rebalancing
     * @return a new localRoot
     * localRoot is the root of an AVL subtree that is
     * critically left-heavy.
     *  Balance is restored.
     */
    private AVLNode<E> rebalanceLeft(AVLNode<E> localRoot) {
        // Obtain reference to left child.
        AVLNode<E> leftChild = (AVLNode<E>) localRoot.left;
        // See whether left-right heavy.
        if (leftChild.balance > AVLNode.BALANCED) {
            // Obtain reference to left-right child.
            AVLNode<E> leftRightChild = (AVLNode<E>) leftChild.right;
            // Adjust the balances to be their new values after
            // the rotations are performed.
            if (leftRightChild.balance < AVLNode.BALANCED) {
                leftChild.balance = AVLNode.BALANCED;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.RIGHT_HEAVY;
            } else if (leftRightChild.balance > AVLNode.BALANCED) {
                leftChild.balance = AVLNode.LEFT_HEAVY;
                leftRightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            } else {
                leftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }
            // Perform left rotation.
            localRoot.left = rotateLeft(leftChild);
        } else { //Left-Left case
            // In this case the leftChild (the new root)
            // and the root (new right child) will both be balanced
            // after the rotation.
            leftChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
        }
        // Now rotate the local root right.
        return (AVLNode<E>) rotateRight(localRoot);
    }
    /*</listing>*/

// Insert solution to programming exercise 1, section 2, chapter 9 here

    /**
     * Method to rebalance right.
     *
     * @param localRoot Root of the AVL subtree
     *                  that needs rebalancing
     * @return a new localRoot
     *  localRoot is the root of an AVL subtree that is
     * critically right-heavy.
     *  Balance is restored.
     */
    private AVLNode<E> rebalanceRight(AVLNode<E> localRoot) {
        // Obtain reference to left child.
        AVLNode<E> rightChild = (AVLNode<E>) localRoot.right;
        // See whether right-left heavy.
        if (rightChild.balance < AVLNode.BALANCED) {
            // Obtain reference to right-left child.
            AVLNode<E> rightLeftChild = (AVLNode<E>) rightChild.left;
            // Adjust the balances to be their new values after
            // the rotations are performed.
            if (rightLeftChild.balance < AVLNode.BALANCED) {
                rightChild.balance = AVLNode.BALANCED;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.RIGHT_HEAVY;
            } else if (rightLeftChild.balance > AVLNode.BALANCED) {
                rightChild.balance = AVLNode.BALANCED;
                rightLeftChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.LEFT_HEAVY;
            } else {
                rightChild.balance = AVLNode.BALANCED;
                localRoot.balance = AVLNode.BALANCED;
            }

            // Perform right rotation.
            localRoot.right = rotateRight(rightChild);
        } else { //Right-Right case
            // In this case the rightChild (the new root)
            // and the root (new left child) will both be balanced
            // after the rotation.
            rightChild.balance = AVLNode.BALANCED;
            localRoot.balance = AVLNode.BALANCED;
        }
        // Now rotate the local root left.
        return (AVLNode<E>) rotateLeft(localRoot);
    }

    /**
     * Method to decrement the balance field and to reset the value of
     * increase.
     *
     *  node The AVL node whose balance is to be decremented
     *  The balance field was correct prior to an insertion [or
     * removal,] and an item is either been added to the left[
     * or removed from the right].
     *  The balance is decremented and the increase flags is set
     * to false if the overall height of this subtree has not
     * changed.
     */
    private void decrementBalance(AVLNode<E> node) {
        // Decrement the balance.
        node.balance--;
        if (node.balance == AVLNode.BALANCED) {
            // If now balanced, overall height has not increased.
            increase = false;

        } else if (node.balance == AVLNode.LEFT_HEAVY || node.balance == AVLNode.RIGHT_HEAVY) {
            // If went from balanced to unbalanced, overall height has not decreased.
            decrease = false;
        }
    }

// Insert solution to programming exercise 3, section 2, chapter 9 here

    /**
     * Method to increment the balance field and to reset the value of
     * increase.
     *
     *  node The AVL node whose balance is to be incremented
     *  The balance field was correct prior to an insertion [or
     * removal,] and an item is either been added to the right[
     * or removed from the left].
     *  The balance is incremented and the increase flags is set
     * to false if the overall height of this subtree has not
     * changed.
     */
    private void incrementBalance(AVLNode<E> node) {
        // Increment the balance.
        node.balance++;
        if (node.balance == AVLNode.BALANCED) {
            // If now balanced, overall height has not increased.
            increase = false;

        } else if (node.balance == AVLNode.LEFT_HEAVY || node.balance == AVLNode.RIGHT_HEAVY) {
            // If went from balanced to unbalanced, overall height has not decreased.
            decrease = false;
        }

    }

    /***
     * Prints a representation of the tree using in-order traversal.
     */
    public void display() {
        StringBuilder sb = new StringBuilder();
        buildDisplay((AVLNode<E>) this.root, sb, 0, true);
        System.out.println(sb.toString());
    }

    /***
     * Recursively traverse the tree in-order, constructing a string representation of the nodes.
     * @param localRoot Current root.
     * @param sb StringBuilder instance where node data values are stored.
     * @param depth Depth of the current node.
     * @param showBalance Flag to determine whether to include the balance value in the output.
     */
    private void buildDisplay(AVLNode<E> localRoot, StringBuilder sb, int depth, boolean showBalance) {
        if (localRoot == null)
            return;

        buildDisplay((AVLNode<E>) localRoot.left, sb, depth + 1, showBalance);

        for (int i = 0; i < depth; i++)
            sb.append(".");
        if (showBalance)
            sb.append(localRoot.data).append(" (").append(localRoot.balance).append(")").append("\n");
        else
            sb.append(localRoot.data).append("\n");

        buildDisplay((AVLNode<E>) localRoot.right, sb, depth + 1, showBalance);
    }

    /***
     * Runs the argument-less method specified by the string argument method on this tree.
     * @param method String representation of the method to run.
     * @throws InterruptedException if the method specified is "quit"
     */
    public void runMethod(String method) throws InterruptedException {
        if (method.equals(METHODS[3]))
            this.display();
        else if (method.equals(METHODS[4]))
            throw new InterruptedException();
    }

    /***
     * Runs the method specified by the string argument method on this tree with the given argument arg.
     * @param method String representation of the method to run.
     * @param arg Argument to pass to the method being run.
     */
    public void runMethod(String method, E arg) {
        if (method.equals(METHODS[0])) {
            if (this.insert(arg))
                System.out.println(String.format("Inserted %1s", arg));
            else
                System.out.println(String.format("%1s cannot be inserted'", arg));
        } else if (method.equals(METHODS[1]))
            if (this.remove(arg) != null)
                System.out.println(String.format("Removed %1s", arg));
            else
                System.out.println(String.format("%1s not found'", arg));
        else if (method.equals(METHODS[2]))
            if (this.find(arg) != null)
                System.out.println(String.format("Found %1s", arg));
            else
                System.out.println(String.format("%1s not found'", arg));
    }

    public static void main(String args[]) {
        AVLNode<Integer> root = null;
        AVLTree<Integer>  i = new AVLTree<>();
        root = i.add(root, 5);
        root = i.add(root, 6);
        root = i.add(root, 7);
        root = i.add(root, 5);
        root = i.add(root, 32);
        root = i.add(root, 9);
        root = i.add(root, 14);
        root = i.add(root, 11);
        root = i.add(root, 10);
        root = i.add(root, 20);
        root = i.add(root, 44);
        root = i.add(root, 25);
        root = i.add(root, 13);
        root = i.add(root, 58);
        root = i.add(root, 61);
        root = i.add(root, 91);
        root = i.add(root, 12);
        root = i.add(root, 11);
        root = i.add(root, 14);
        root = i.add(root, 25);
        System.out.print("Inorder Traversal of  AVL Tree :\n");
        i.root = root;
        i.display();
        i.remove(5);
        i.remove(25);
        System.out.println("After Removal of TARGETS printing structure again");
        i.display();
        //System.out.print("\n New Root of AVL Tree is : " + root.data);


    }
}