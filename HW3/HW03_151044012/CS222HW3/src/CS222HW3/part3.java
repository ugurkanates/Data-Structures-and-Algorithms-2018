package CS222HW3;

/**
 * Created by paypaytr on 3/21/18.
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class part3 {
    private Node first;
    private Node last;
    private Node current;
    private Node currentSemester;



    private int sizer;

    public part3() {
        first = null;
        last = null;
        sizer = 0;


    }

    public part1course next(){
        if(current !=null){
            part1course item = current.data;
            current = current.next;
            return item;
        }
        return null;
    }

    public part1course nextSemester(){
        if(current !=null){
            int sameSem  = current.data.getSemester();
            part1course item;
            currentSemester = current;

            do {
                currentSemester = currentSemester.next;
                if(currentSemester == null){
                    currentSemester = first;
                }
                item = currentSemester.data;

            }
            while(item.getSemester() != sameSem && current!=null);
            current = currentSemester;
            return item;
        }
        return null;
    }


    public void add(part1course item) {
        if (item == null) { throw new NullPointerException("The first argument for addLast() is null."); }
        if (!isEmpty()) {
            Node prev = last;
            last = new Node(item, null);
            prev.next = last;
        }
        else {
            last = new Node(item, null);
            first = last;
            current = first;
            currentSemester = first;
        }
        sizer++;
    }
    public boolean remove(part1course item) {
        if (isEmpty()) { throw new IllegalStateException("Cannot remove() from and empty list."); }
        boolean result = false;
        Node prev = first;
        Node curr = first;
        while (curr.next != null || curr == last) {
            if (curr.data.equals(item)) {
                // remove the last remaining element
                if (sizer == 1) { first = null; last = null; }
                // remove first element
                else if (curr.equals(first)) { first = first.next; }
                // remove last element
                else if (curr.equals(last)) { last = prev; last.next = null; }
                // remove element
                else { prev.next = curr.next; }
                sizer--;
                result = true;
                break;
            }
            prev = curr;
            curr = prev.next;
        }
        return result;
    }
    public int size() {
        return sizer;
    }
    public boolean isEmpty() {
        return sizer == 0;
    }

    private class Node {
        private part1course data;
        private Node next;
        private Node nextS;
        public Node(part1course data, Node next) {
            this.data = data;
            this.next = next;

        }

    }

    public static void main(String[] args) {
       part3 a = new part3();
        part1course nd  = null;

        part1csvread.CSVRead.readCsvFileForPart3("course.csv",a);
        nd = a.next();
        nd.printer();
        nd = a.nextSemester();
        nd.printer();
        nd = a.nextSemester();
        nd.printer();
        nd = a.nextSemester();
        nd.printer();
        nd = a.nextSemester();
        nd.printer();
        nd = a.nextSemester();
        nd.printer();
        nd = a.nextSemester();
        nd.printer();
        nd = a.nextSemester();
        nd.printer();

        //last element is again first element :)


    }
}
//ne eksik ? current nexts prev dogru atamiyo kontrol + add yapcagi zaman veya remove nexts kısmndanda cikarcak oda
