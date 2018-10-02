package gtu;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by paypaytr on 4/12/18.
 */
public class part2<E extends Comparable<E>>
        extends BinaryTree<E> {
    public int whichxyz = -1;


    public static void main(String[] args){


        part2<Integer>GTU = new part2<Integer>();
        GTU.test();
        StringBuilder a = new StringBuilder();
        System.out.println(GTU);


    }

    private void test() {
        addChild((Node<E>) new Node<String>(40,45,13),null);

        //adding root -> first
        addChild(root, (Node<E>) new Node<String>(15,70,10));
        // will be left  15<40 -> left 15:

        addChild(root, (Node<E>) new Node<String>(15,50,10));

        addChild((Node<E>) new Node<String>(15,50,10), (Node<E>) new Node<String>(35,50,24));
        addChild((Node<E>) new Node<String>(35,50,24), (Node<E>) new Node<String>(34,13,84));







        //System.out.println("kay");
    }


    public boolean addChild(BinaryTree.Node<E> parent, BinaryTree.Node<E> child) {

        //child.getDataNow();

        if (root == null) {
            root = parent;
            return true;
        }
        if (child == null)
            return false;

        if (whichxyz == 0) {
            Node<E> item = (Node<E>) findX(root,parent.getX());

            if (child.getX() < item.getX()) { //if that parent has no child?
                item.left = child;
            } else {
                item.right = child;
            }
        } else if (whichxyz == 1) {
            Node<E> item = (Node<E>) findY(root, parent.getY());

            if (child.getY() < item.getY()) { //if that parent has no child?
                item.left = child;
            } else {
                item.right = child;
            }

        } else if (whichxyz == 2) {
            Node<E> item = (Node<E>) findZ(root, parent.getZ());

            if (child.getZ() < item.getZ()) { //if that parent has no child?
                item.left = child;
            } else {
                item.right = child;
            }
        }
        whichxyz++;
        if(whichxyz>2)
            whichxyz = whichxyz%3;





        return true;
    }
    @Override
    public void preOrderTraverse(Node<E> node, int depth,
                                 StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append("  ");
        }
        if (node == null) {
            sb.append("null\n");
        } else {
            sb.append(node.getX()+" "+node.getY()+" "+node.getZ());
            sb.append("\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();

    }
    public E findbiggestnode(BinaryTree.Node<E>  parent){
        if (parent.right.right == null) {
            E returnValue = parent.right.data;
            parent.right = parent.right.left;
            return returnValue;
        } else {
            return findbiggestnode(parent.right);
        }

    }
    public Node<E> findX(Node<E> node, int value){
        // Finds the node that contains the value and returns a reference to the node.
        // Returns null if value does not exist in the tree.
        if (node == null) return null;

        if (node.getX() == value) {
            return node;
        } else {
            Node<E> left = findX(node.left, value);
            Node<E> right = findX(node.right, value);
            if (left != null) {
                return left;
            }else {
                return right;
            }
        }
    }

    public Node<E> findY(Node<E> node, int value){
        // Finds the node that contains the value and returns a reference to the node.
        // Returns null if value does not exist in the tree.
        if (node == null) return null;

        if (node.getY() == value) {
            return node;
        } else {
            Node<E> left = findY(node.left, value);
            Node<E> right = findY(node.right, value);
            if (left != null) {
                return left;
            }else {
                return right;
            }
        }
    }

    public Node<E> findZ(Node<E> node, int value){
        // Finds the node that contains the value and returns a reference to the node.
        // Returns null if value does not exist in the tree.
        if (node == null) return null;

        if (node.getZ() == value) {
            return node;
        } else {
            Node<E> left = findZ(node.left, value);
            Node<E> right = findZ(node.right, value);
            if (left != null) {
                return left;
            }else {
                return right;
            }
        }
    }
}
