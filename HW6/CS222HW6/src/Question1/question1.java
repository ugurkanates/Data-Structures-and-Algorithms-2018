package Question1; /**
 * Created by paypaytr on 5/16/18.
 */
import java.io.PrintWriter;
/*

Some parts of implementations comes from STUDENT code
 */
public class question1 <T extends Comparable<T>>{

    private static final char BLACK = 'B';
    private static final char RED = 'R';
    private RBNode<T> root;
    public question1() {
        root = null;
    }

    public RBNode<T> getRoot(){
        return root;
    }
    public void insert(T data) throws IllegalArgumentException {
        try {
            root = put( root, data );
            root.setColour( BLACK );
        } catch(IllegalArgumentException e) {
            throw e;
        }
    }

    private RBNode<T> put( RBNode<T> node, T data ) throws IllegalArgumentException {
        if( node == null ) {
            RBNode<T> newNode = new RBNode<T>(data);
            return newNode;
        }
        int cmp = data.compareTo( node.getData() );

        if( cmp < 0 ) {
            node.setLeftChild( put( node.getLeftChild(), data ) );
        } else if( cmp > 0 ) {
            node.setRightChild( put( node.getRightChild(), data ) );
        } else {
            throw new IllegalArgumentException("Data already exists in tree: "
                    + data.toString());
        }

        //Red-red conflict with outside grandchild
        if( isRed( node.getLeftChild() ) && isRed( node.getLeftChild().getLeftChild() ) ) {
            node.setColour( RED );
            node.getLeftChild().setColour( BLACK );
            node = rightRotation(node);
        }

        //Red-red conflict with right-outside grandchild
        if( isRed( node.getRightChild() ) && isRed( node.getRightChild().getRightChild() ) ) {
            node.setColour( RED );
            node.getRightChild().setColour( BLACK );
            node = leftRotation(node);
        }

        //Red-red conflict with left-right inside grandchild
        if( isRed( node.getLeftChild() ) && isRed( node.getLeftChild().getRightChild() ) ) {
            node.setColour( RED );
            node.getLeftChild().getRightChild().setColour( BLACK );
            node.setLeftChild( leftRotation(node.getLeftChild() ) );
            node = rightRotation( node );
        }

        //Red-red conflict with right-left inside grandchild
        if( isRed( node.getRightChild() ) && isRed( node.getRightChild().getLeftChild() ) ) {
            node.setColour( RED );
            node.getRightChild().getLeftChild().setColour( BLACK );
            node.setRightChild( rightRotation( node.getRightChild() ) );
            node = leftRotation( node );
        }

        colourFlip(node);
        return node;
    }


    public RBNode<T> contains(T data) {
        RBNode<T> current = root;
        while( data.compareTo(current.getData()) != 0 ) {
            if( data.compareTo(current.getData()) < 0 ) {
                current = current.getLeftChild();
            } else {
                current = current.getRightChild();
            }
            if( current == null || current.isDeleted() ) {
                return null;
            }
        }
        return current;
    }


    public boolean removeElement( T data ) throws Exception {
        RBNode<T> node = contains( data );

        if( node != null ) {
            node.delete();
            return true;
        } else {
            throw new Exception("Data does not exist: " + data.toString());
        }
    }


    public boolean isRed( RBNode<T> node ) {
        if( node == null ) {
            return false;
        }
        return node.getColour() == RED;
    }


    private void colourFlip( RBNode<T> parent ) {
        if( parent.getRightChild() == null ||
                parent.getLeftChild() == null ) {
            return;
        }

        if( !isRed(parent) && isRed(parent.getRightChild())
                && isRed(parent.getLeftChild()) ) {
            if( parent != root ) {
                parent.setColour( RED );
            }
            parent.getRightChild().setColour( BLACK );
            parent.getLeftChild().setColour( BLACK );
        }
    }


    private RBNode<T> rightRotation( RBNode<T> grandparent ) {
        RBNode<T> parent = grandparent.getLeftChild();
        RBNode<T> rightChildOfParent = parent.getRightChild();
        parent.setRightChild( grandparent );
        grandparent.setLeftChild( rightChildOfParent );
        return parent;
    }


    private RBNode<T> leftRotation( RBNode<T> grandparent ) {
        RBNode<T> parent = grandparent.getRightChild();
        RBNode<T> leftChildOfParent = parent.getLeftChild();
        parent.setLeftChild( grandparent );
        grandparent.setRightChild( leftChildOfParent );
        return parent;
    }

    public void displayElements(PrintWriter p) {
        displaySubtreeInOrder(root, p);
    }


    private void displaySubtreeInOrder(RBNode<T> current, PrintWriter p) {
        if( current != null ) {
            displaySubtreeInOrder( current.getLeftChild(), p );
            p.println( "Data is " + current.getData()
                    + "Node colour: " + current.getColour() );
            displaySubtreeInOrder( current.getRightChild(), p );
        }
    }


    public void printStructure() {
        if(root == null) {
            System.out.println("null");
        } else {
            System.out.println("*****************************************");
            root.display(0);
            System.out.println("*****************************************");
        }
        System.out.println();
    }

    public class RBNode<T extends Comparable< T >> {

        private final static char RED = 'R';
        private final static char BLACK = 'B';
        private T data;
        private char colour;
        private RBNode<T> rightChild;
        private RBNode<T> leftChild;
        private boolean deleted;


        public RBNode(T data) {
            this.data = data;
            colour = RED;
            rightChild = null;
            leftChild = null;
            deleted = false;
        }

        public void setData(T data){
            this.data = data;
        }

        public boolean setColour( char c ) {
            //If the colour is black or red (a legal input), and it is also
            //not the same as the current colour, change the colour
            if( ( c == RED || c == BLACK ) && c != colour ) {
                colour = c;
                return true;
            }
            return false;
        }

        public void setLeftChild( RBNode<T> node ) {
            leftChild = node;
        }
        public void setRightChild( RBNode<T> node ) {
            rightChild = node;
        }
        public void delete() {
            deleted = true;
        }
        public T getData() {
            return data;
        }
        public char getColour() {
            return colour;
        }
        public RBNode<T> getLeftChild() {
            return leftChild;
        }
        public RBNode<T> getRightChild() {
            return rightChild;
        }
        public boolean isDeleted() {
            return deleted;
        }
        public void display(int n) {
            String indent = "- ";

            //Print the indents for this level
            for(int i = 1; i <= n; i++) {
                System.out.print(indent);
            }
            //Print the node contents
            System.out.println("ROOT: " + data + ", colour: " + colour);
            //Indent
            for(int i = 1; i <= n; i++) {
                System.out.print(indent);
            }
            //Print the left child of the node
            System.out.println("LEFT");
            if( leftChild == null) {
                for(int i = 1; i <= n+1; i++) {
                    System.out.print(indent);
                }
                System.out.println("null");
            } else {
                leftChild.display(n+1);
            }
            //Indent
            for(int i = 1; i <= n; i++) {
                System.out.print(indent);
            }

            //Print the right child of the node
            System.out.println("RIGHT");
            if( rightChild == null) {
                for(int i = 1; i <= n+1; i++) {
                    System.out.print(indent);
                }
                System.out.println("null");
            } else {
                rightChild.display(n+1);
            }
        }
    }
    public static void main(String args[]) {
        question1<Integer> tree = new question1<>();
        tree.insert(7);
        tree.printStructure();

        tree.insert(61);
        tree.insert(52);
        tree.printStructure();

        tree.insert(4);
        tree.insert(34);
        tree.insert(21);

        tree.printStructure();
        tree.insert(1);
        tree.insert(53);
        tree.insert(39);

        tree.printStructure();
        tree.insert(20);
        tree.insert(14);
        tree.insert(13);

        tree.printStructure();
        tree.insert(44);
        tree.insert(55);
        tree.insert(63);

        tree.printStructure();
        tree.insert(49);
        tree.insert(33);
        tree.insert(2);

        tree.printStructure();
        tree.insert(10);
        tree.printStructure();

        /*
        REMOVAL Of elements now !
         */
        try {
            tree.removeElement(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        tree.printStructure();

        try {
            tree.removeElement(55);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
