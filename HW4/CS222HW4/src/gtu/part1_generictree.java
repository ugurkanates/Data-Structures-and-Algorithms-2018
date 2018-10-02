package gtu;

import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by paypaytr on 4/11/18.
 */
public class part1_generictree<E extends Comparable<E>>
        extends BinaryTree<E>{


    public static void main(String[] args){


        part1_generictree<String> GTU = new part1_generictree<String>();
        GTU.test();
        StringBuilder a = new StringBuilder();
        System.out.println(GTU);


    }

    public void test() {
        addChild((Node<E>) new Node<String>("william1"),null);
        addChild(root, (Node<E>) new Node<String>("robert"));
        addChild(root, (Node<E>) new Node<String>("WILLIAM2"));
        addChild(root, (Node<E>) new Node<String>("ADELA"));
        addChild(root, (Node<E>) new Node<String>("HENRY1"));
        addChild(new Node<E>((E) "HENRY1"),new Node<E>((E) "william3"));
        addChild(new Node<E>((E) "HENRY1"),new Node<E>((E) "MATILDA"));
        addChild(new Node<E>((E) "MATILDA"),new Node<E>((E) "HENRY2"));
        addChild(new Node<E>((E) "HENRY2"),new Node<E>((E) "HENRY3"));
        addChild(new Node<E>((E) "HENRY2"),new Node<E>((E) "RICHARD1"));
        addChild(new Node<E>((E) "HENRY2"),new Node<E>((E) "GOFRET"));
        addChild(new Node<E>((E) "GOFRET"),new Node<E>((E) "ARTHUR"));
        addChild(new Node<E>((E) "HENRY2"),new Node<E>((E) "JOHN"));
        addChild(new Node<E>((E) "JOHN"),new Node<E>((E) "HENRY4"));
        addChild(new Node<E>((E) "ADELA"),new Node<E>((E) "STEPHEN"));
        addChild(new Node<E>((E) "william1"),new Node<E>((E) "sonofRobert"));




        //System.out.println("kay");
    }



    private Node<E> find(Node<E> node, E value) {
        // Finds the node that contains the value and returns a reference to the node.
        // Returns null if value does not exist in the tree.
        if (node == null) return null;

        if (node.getDataNow() == value) {
            return node;
        } else {
            Node<E> left = find(node.left, value);
            Node<E> right = find(node.right, value);
            if (left != null) {
                return left;
            }else {
                return right;
            }
        }
    }
    public Node<E> postOrderSearch(Node<E> node, int depth, StringBuilder sb,Node<E>given) {
        for (int i = 1; i < depth; i++) {
            //sb.append("  ");
        }
        if (node == null) {
            // sb.append("null\n");
        }
        else if(node == given){
            return given;
        }
        else {

            postOrderSearch(node.left, depth + 1, sb,given);
            postOrderSearch(node.right, depth + 1, sb,given);
            sb.append(node.toString());
            sb.append("\n");


        }
        return null;
    }
    public Node<E> inOrderSearch(Node<E> node, int depth, StringBuilder test,Node <E> given) {
        for (int i = 1; i < depth; i++) {
            //sb.append("  ");
        }
        if (node == null) {
            // sb.append("null\n");
        } else {

            inOrderSearch(node.left, depth + 1, test,given);

            test.append(node.toString());
            test.append("\n");

            inOrderSearch(node.right, depth + 1, test,given);
        }

        return null;
    }

   public boolean addChild(Node<E> parent,Node<E> child){
       Node<E> item = find(root,parent.getDataNow());
       //child.getDataNow();

        if(root == null){
            root = parent;
            return true;
        }
       if(child == null){
           return false;
       }
       /*if (item == null) {
           // item is not in the tree � insert it.


           //return false;
       }

       else {
       */
           if (item.left == null) { //if that parent has no child?
               item.left = child;
               //item.right = child;
           } else {
               while(item.right!=null){
                   item = item.right;
               }
               item.right = child;
           }
           return true;
       }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }


    @Override
    public void preOrderTraverse(Node<E> node, int depth,
                                  StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append("  ");
        }
        if (node == null) {
            sb.append("--\n");
        } else {
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }


   }


