package Q45Folder;

import java.util.Random;

/**
 * Created by paypaytr on 5/2/18.
 */
public class InsertionSort {


    /**
     * Sort the table using insertion sort algorithm.

     * @param table The array to be sorted
     */
    public <T extends Comparable<T>>  void sort(T[] table) {
        for (int nextPos = 1; nextPos < table.length; nextPos++) {
            // Invariant: table[0 . . . nextPos - 1] is sorted.
            // Insert element at position nextPos
            // in the sorted subarray.
            insert(table, nextPos);
        } // End for.
    } // End sort.

    /**
     * Insert the element at nextPos where it belongs
     * in the array.

     * @param table The array being sorted
     * @param nextPos The position of the element to insert
     */
    private static <T extends Comparable<T>>  void insert(T[] table,
                                                          int nextPos) {
        T nextVal = table[nextPos]; // Element to insert.
        while (nextPos > 0
                && nextVal.compareTo(table[nextPos - 1]) < 0) {
            table[nextPos] = table[nextPos - 1]; // Shift down.
            nextPos--; // Check next smaller element.
        }
        // Insert nextVal at nextPos.
        table[nextPos] = nextVal;
    }



    /*

     * Main method.
     * @param args

    public static void main(String[] args) {

        InsertionSort app = new InsertionSort();

        //Generate an integer array of length 7
        int[] input = app.generateRandomNumbers(7);

        //Before sort
        System.out.println(Arrays.toString(input));

        //After sort
        System.out.println(Arrays.toString(app.insertionSort(input)));

    }


     * This method sort the input integer array using insertion sort.
     * @param input the array of integers to sort.
     * @return sorted input array of integers.

    private int[] insertionSort(int[] input){

        int temp;

        for (int i = 1; i < input.length; i++) {
            for(int j = i ; j > 0 ; j--){
                if(input[j] < input[j-1]){
                    temp = input[j];
                    input[j] = input[j-1];
                    input[j-1] = temp;
                }
            }
        }
        return input;
    }
         * This method generate array of random integers with length n.
     * @param n the length of the array to generate.
     * @return array of random integers with length n.
    private int[] generateRandomNumbers(int n){

        int[] result = new int[n];
        Random random = new Random();

        for (int i = 0; i < result.length; i++) {
            result[i] = random.nextInt(n * 10);
        }

        return result;
    }
    */
}
