package Question2;

public class BTree<Key extends Comparable<Key>, Value>  {
    // max children per B-tree node = M-1
    // (must be even and greater than 2)
    private static final int M = 4;

    private Node root;       // root of the B-tree
    private int height;      // height of the B-tree
    private int n;           // number of key-value pairs in the B-tree

    // helper B-tree node data type
    private static final class Node {
        private int m;                             // number of children
        private Entry[] children = new Entry[M];   // the array of children

        // create a node with k children
        private Node(int k) {
            m = k;
        }
    }

    // internal nodes: only use key and next
    // external nodes: only use key and value
     static class Entry {
        private Comparable key;
        private final Object val;
        private Node next;     // helper field to iterate over array entries
        public Entry(Comparable key, Object val, Node next) {
            this.key  = key;
            this.val  = val;
            this.next = next;
        }
        Comparable getKey(){
            return key;
        }
    }

    /**
     * Initializes an empty B-tree.
     */
    public BTree() {
        root = new Node(0);
    }

    /**
     * Returns true if this symbol table is empty.
     * @return {@code true} if this symbol table is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return n;
    }

    /**
     * Returns the height of this B-tree (for debugging).
     *
     * @return the height of this B-tree
     */
    public int height() {
        return height;
    }


    /**
     * Returns the value associated with the given key.
     *
     * @param  key the key
     * @return the value associated with the given key if the key is in the symbol table
     *         and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        return search(root, key, height);
    }

    private Value search(Node x, Key key, int ht) {
        Entry[] children = x.children;

        // external node
        if (ht == 0) {
            for (int j = 0; j < x.m; j++) {
                if (eq(key, children[j].key)) return (Value) children[j].val;
            }
        }

        // internal node
        else {
            for (int j = 0; j < x.m; j++) {
                if (j+1 == x.m || less(key, children[j+1].key))
                    return search(children[j].next, key, ht-1);
            }
        }
        return null;
    }

    // A utility function to search a given key in BST
    public Node bsearch(Node root, Key key)
    {
        // Base Cases: root is null or key is present at root
        if (root==null || eq(key,root.children[root.m].key))
            return root;

        // val is greater than root's key
        if (less(root.children[root.m].key,key))  // < isareti vardi
            return bsearch(root.children[root.m].next, key);

        // val is less than root's key
        return bsearch(root.children[root.m].next, key);
    }

    /**
     * Inserts the key-value pair into the symbol table, overwriting the old value
     * with the new value if the key is already in the symbol table.
     * If the value is {@code null}, this effectively deletes the key from the symbol table.
     *
     * @param  key the key
     * @param  val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("argument key to put() is null");
        Node u = insert(root, key, val, height);
        n++;
        if (u == null) return;

        // need to split root
        Node t = new Node(2);
        t.children[0] = new Entry(root.children[0].key, null, root);
        t.children[1] = new Entry(u.children[0].key, null, u);
        root = t;
        height++;
    }

    private Node insert(Node h, Key key, Value val, int ht) {
        int j;
        Entry t = new Entry(key, val, null);

        // external node
        if (ht == 0) {
            for (j = 0; j < h.m; j++) {
                if (less(key, h.children[j].key)) break;
            }
        }

        // internal node
        else {
            for (j = 0; j < h.m; j++) {
                if ((j+1 == h.m) || less(key, h.children[j+1].key)) {
                    Node u = insert(h.children[j++].next, key, val, ht-1);
                    if (u == null) return null;
                    t.key = u.children[0].key;
                    t.next = u;
                    break;
                }
            }
        }

        for (int i = h.m; i > j; i--)
            h.children[i] = h.children[i-1];
        h.children[j] = t;
        h.m++;
        if (h.m < M) return null;
        else         return split(h);
    }

    // split node in half
    private Node split(Node h) {
        Node t = new Node(M/2);
        h.m = M/2;
        for (int j = 0; j < M/2; j++)
            t.children[j] = h.children[M/2+j];
        return t;
    }

    /**
     * Returns a string representation of this B-tree (for debugging).
     *
     * @return a string representation of this B-tree.
     */
    public String toString() {
        return toString(root, height, "") + "\n";
    }

    private String toString(Node h, int ht, String indent) {
        StringBuilder s = new StringBuilder();
        Entry[] children = h.children;

        if (ht == 0) {
            for (int j = 0; j < h.m; j++) {
                s.append(indent + children[j].key + " " + children[j].val + "\n");
            }
        }
        else {
            for (int j = 0; j < h.m; j++) {
                if (j > 0) s.append(indent + "(" + children[j].key + ")\n");
                s.append(toString(children[j].next, ht-1, indent + "     "));
            }
        }
        return s.toString();
    }


    // comparison functions - make Comparable instead of Key to avoid casts
    private boolean less(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) < 0;
    }

    private boolean eq(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) == 0;
    }


    /**
     * Unit tests the {@code BTree} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        BTree<String, String> st = new BTree<String, String>();

    //String representation of Team players ->
        st.put("Besiktas", "Rustu Recber");
        st.put("Besiktas", "Sergen Yalcin");
        st.put("Besiktas",    "Mert Nobre");
        st.put("Besiktas",         "Querasma");
        st.put("Fenerbahce",     "Volkan Demirel");
        st.put("Fenerbahce",       "Van Hojdong");
        st.put("Fenerbahce",          "Alex De Souza");
        st.put("Galatasaray",        "Mondragon");
        st.put("Galatasaray",         "Muslera");
        st.put("TurkiyeSpor",       "Ahmet Template");
        st.put("TrabzonSpor",      "Hamsi Bey");
        st.put("TrabzonSpor",    "UcanHamsi");
        st.put("Kasimspor",          "Kasim Bey");
        st.put("Manchester",       "Ronaldon");
        st.put("Real Madrid",      "GUTI");
        st.put("TEMPLATESpor",    "Templater");
        st.put("Real Sosyaded",      "Real Guti");
        st.put("Chelsea",    "Drogba");
        st.put("Chelsea",      "Lampard");
        st.put("Arsenal",    "Henry");
       /* st.put("Real Madrid",      "GUTI");
        st.put("TEMPLATESpor",    "Templater");
        st.put("Real Madrid",      "GUTI");
        st.put("TEMPLATESpor",    "Templater");
        st.put("Real Madrid",      "GUTI");
        st.put("TEMPLATESpor",    "Templater");
        st.put("Real Madrid",      "GUTI");
        st.put("TEMPLATESpor",    "Templater");
        */




        System.out.println("Besiktaslılar:  " + st.get("Besiktas"));
        System.out.println("Fenerbahceliler: " + st.get("Fenerbahce"));
        System.out.println("Galatasaraylılar:      " + st.get("Galatasaray"));
        System.out.println("TurkiyeSporlular:         " + st.get("TurkiyeSpor"));
        System.out.println("TrabzonSporlular:          " + st.get("TrabzonSpor"));
        System.out.println("Kasimsporlular:          " + st.get("Kasimspor"));
        System.out.println("Kasimsporlular:          " + st.get("Kasimspor"));
        System.out.println("Kasimsporlular:          " + st.get("Kasimspor"));
        System.out.println("Manchesterliler:          " + st.get("Manchester"));
        System.out.println("Real Madridliler:          " + st.get("Real Madrid"));
        System.out.println("TEMPLATESporlur:          " + st.get("TEMPLATESpor"));
        System.out.println("Chelsealiler:          " + st.get("Chelsea"));
        System.out.println("Arsenalcılar:          " + st.get("Arsenal"));



        System.out.println();

        System.out.println("size:    " + st.size());
        System.out.println("height:  " + st.height());
        System.out.println(st);
        System.out.println();
    }

}