package Q1Folder;

/**
 * Created by paypaytr on 5/2/18.
 */
public class OpenAdressQ1 implements MapInterfaceQ1 {
    private Pair[] array;
    private static final int DEFAULT_CAPACITY = 15;
    private int size;

    public OpenAdressQ1() {
        array = new Pair[DEFAULT_CAPACITY];
        size = 0;
    }
    public void printer(){
        for(int i=0;i<array.length;i++)
            if(array[i]!=null)
                System.out.println(array[i]);
            else
                break;
    }

    public long getter(int key) {
        return array[hash(key)] != null ? array[hash(key)].getValue() : 0;
    }


    public void putter(int key, long value) {
        //the index of the array cell to store the value
        int index = hash(key);
        //if the standard index of the array cell is busy linear probing takes place
        if (array[index] != null) {
            int tryNumber = 1;
            do {
                index = linearProbing(index, tryNumber);
            } while (array[index] != null);
        }
        array[index] = new Pair(key, value);
        size++;
        //rehashes the map if the number of elements crosses 0.75 times the capacity
        if (array.length * 0.75 < size) {
            rehashing();
        }
    }

    /**
     * {@inheritDoc}
     */
    public int size() {
        return size;
    }

    /*
     * Returns the hashcode(array index) for a key in this array

     */
    private int hash(int key) {
        if (key == Integer.MIN_VALUE) {
            return 0;
        }

        return Math.abs(key) % array.length;
    }


    private void rehashing() {
        int newCapacity = (int) (this.array.length * 1.5);
        Pair[] oldArr = this.array;
        this.array = new Pair[newCapacity];
        //setting number of the elements in the new array to zero
        //the value will be restored after copying all elements from old array to the new one
        size = 0;
        for (Pair pair : oldArr) {
            if (pair != null) {
                putter(pair.getKey(), pair.getValue());
            }
        }
    }


    private int linearProbing(int hash, int i) {
        return (hash + i) % array.length;
    }

    /*
     * Represents a pair of key-value set for a hashmap
     */
    protected class Pair {
        private final int key;
        private long value;

        Pair(int key, long value) {
            this.key = key;
            this.value = value;
        }

        int getKey() {
            return key;
        }

        long getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }

    }
}
