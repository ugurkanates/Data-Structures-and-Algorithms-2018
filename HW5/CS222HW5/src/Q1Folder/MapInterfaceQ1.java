package Q1Folder;

/**
 * Created by paypaytr on 5/2/18.
 */
public interface MapInterfaceQ1 {

    /*
     * An interface for  hashmap with D for collusions ->open addressing


     */
        /*
         * Returns the value to which the specified key is mapped, or 0 if this map contains no mapping for the key.

         */
        long getter(int key);

        /*

         */
        void putter(int key, long value);

        /*
         * Returns the number  mappings in this map.
         */
        int size();

}
