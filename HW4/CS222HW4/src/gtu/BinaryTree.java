package gtu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;


public class BinaryTree<E> implements Serializable {

    protected static class Node<E> implements Serializable {
        // Data Fields

        public E data;
        public Node<E> left;
        public Node<E> right;
        public int x,y,z;


        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }
        public Node(int xe,int ye,int ze) {
           x = xe;
           y = ye;
           z = ze;
        }



        @Override
        public String toString() {
            return data.toString();
        }

        public E getDataNow() {
            return data;
        }
        public int getX() { return x;}
        public int getY() { return y;}
        public int getZ() { return z;}


    }

    public Node<E> root;

    public BinaryTree() {
        root = null;
    }


    protected BinaryTree(Node<E> root) {
        this.root = root;
    }




    public void preOrderTraverse(Node<E> node, int depth,
                                  StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append("  ");
        }
        if (node == null) {
            sb.append("null\n");
        } else {
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }

 }
