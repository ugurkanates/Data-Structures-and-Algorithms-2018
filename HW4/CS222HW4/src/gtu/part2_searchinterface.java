package gtu;

/**
 * Created by paypaytr on 4/12/18.
 */
public interface part2_searchinterface<E>{

    public boolean addChild(BinaryTree.Node<E> parent, BinaryTree.Node<E> child);
    public String toString();
    public E findbiggestnode(BinaryTree.Node<E>  parent);
    public E find(BinaryTree.Node<E>  localRoot, E target);
    public E find(E target);


    }