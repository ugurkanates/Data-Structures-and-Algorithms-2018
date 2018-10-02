package Q45Folder;

import java.util.Random;

/**
 * Created by paypaytr on 5/2/18.
 */
public class AverageAnalysis {



    public static void main(String[] args) {

       /* LinkedList list = new LinkedList();
        list.head = new Node(10);
        list.head.next = new Node(30);
        list.head.next.next = new Node(3);
        list.head.next.next.next = new Node(4);
        list.head.next.next.next.next = new Node(20);
        list.head.next.next.next.next.next = new Node(5);


        Node node = null;
        node = list.mergeSort(head);
        System.out.println("Linked list after sorting :");
        list.print(node);
        */

        //to see difference all comparisons will be like this ->

        Integer[] array1 = new Integer[10];
        // 10 Element Test Subject

        Integer[] array2 = new Integer[50];
        // 50 Element Test Subject


        Integer[] array3 = new Integer[100];
        // 100 Element Test Subject


        Integer[] array4 = new Integer[1000];
        // 1000 Element Test Subject


        Integer[] array5 = new Integer[5000];
        // 5000 Element Test Subject


        Integer[] array6 = new Integer[10000];
        // 10 000 Element Test Subject


        Integer[] array7 = new Integer[5];
        // 20 000 Element Test Subject


        Integer[] array8 = new Integer[3];
        // 35 000 Element Test Subject


        Integer[] array9 = new Integer[15];
        // 50 000 Element Test Subject


        Integer[] array10 = new Integer[7];
        // 49 999 Element Test Subject

        //InsertionSort sort1 = new InsertionSort();
        //sort1.sort(array1);
        long totalTime[][] = new long[10][5]; // size 10 array for calculations of each -> array, try all 10 time and give
        // calculation average output.

        Calculator(array1,0,totalTime);
        Calculator(array2,1,totalTime);
        Calculator(array3,2,totalTime);
        Calculator(array4,3,totalTime);
        Calculator(array5,4,totalTime);
        Calculator(array6,5,totalTime);
        Calculator(array7,6,totalTime);
        Calculator(array8,7,totalTime);
        Calculator(array9,8,totalTime);
        Calculator(array10,9,totalTime);



        for(int i=0;i<10;i++) {
            System.out.println("Insertion Sort");
            System.out.println(totalTime[i][0]);
            System.out.println("Heap Sort");
            System.out.println(totalTime[i][1]);
            System.out.println("Quick Sort");
            System.out.println(totalTime[i][2]);
            System.out.println("Merge Sort");
            System.out.println(totalTime[i][3]);
            System.out.println("MERGED DOUBLE LINKED LIST, Sort");
            System.out.println(totalTime[i][4]);


        }
    }
    public static void Randomizer(Integer array[]){
        Random rInt = new Random(); // For random number generation

        for(int i=0;i<array.length;i++)
            array[i] = rInt.nextInt();
    }
    public static void arrayToLinked(Integer array[],MergeSortWithDLL list){
        MergeSortWithDLL.Node temp = list.getHead();
        for(int i=0;i<array.length;i++){
            list.head = new MergeSortWithDLL.Node(array[i]);
            list.head = list.head.next;
        }
        list.head = temp;

    }
    public static void Calculator(Integer array[],int j,long totalTime[][]){
        HeapSort heaper = new HeapSort();
        InsertionSort inserter = new InsertionSort();
        MergeSort merger = new MergeSort();
        MergeSortWithDLL merger2 = new MergeSortWithDLL();

        QuickSort quicker = new QuickSort();
        for (int i = 0; i < 10; i++) {
            //for each sort reset and calculate nanotimes ->
            /*
               0 = insertion sort
               1 = heap sort
               2 = quick sort
               3= merge sort book
               4 = merge sort me with DLL

         */
            //STARTING WITH INSERTION SORT->
            Randomizer(array);
            long startTime = System.nanoTime();
            inserter.sort(array);
            long endTime = System.nanoTime();
            totalTime[j][0] += (endTime - startTime);

             startTime = System.nanoTime();
            heaper.sort(array);
             endTime = System.nanoTime();
            totalTime[j][1] += (endTime - startTime);

             startTime = System.nanoTime();
            quicker.sort(array);
             endTime = System.nanoTime();
            totalTime[j][2] += (endTime - startTime);


             startTime = System.nanoTime();
            merger.sort(array);
             endTime = System.nanoTime();
            totalTime[j][3] += (endTime - startTime);

            //-> merger sort with dll is different so treat differently
            //adding elements is different .
            arrayToLinked(array,merger2);
             startTime = System.nanoTime();
            merger2.mergeSort(merger2.getHead());
             endTime = System.nanoTime();
            totalTime[j][4] += (endTime - startTime);



        }
        totalTime[j][0] /=10;
        totalTime[j][1] /=10;
        totalTime[j][2] /=10;
        totalTime[j][3] /=10;
        totalTime[j][4] /=10;


    }
}
