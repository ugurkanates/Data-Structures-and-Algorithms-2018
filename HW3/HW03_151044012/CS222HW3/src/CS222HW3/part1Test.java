package CS222HW3;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

/**
 * Created by paypaytr on 3/22/18.
 */
public class part1Test {
    public static void printCourses(LinkedList<part1course> obje){
        for (int i = 0 ; i< obje.size();i++){
            System.out.printf("Semester :%d\n",obje.get(i).getSemester());
            System.out.printf("Course code:%s\n",obje.get(i).getCourseCode());
            System.out.printf("Course Title:%s\n",obje.get(i).getCourseTitle());
            System.out.printf("ECTS Credits:%f\n",obje.get(i).getEctsCredits());
            System.out.printf("GTU Credits:%f\n",obje.get(i).getGtuCredits());
            System.out.printf("H+T+L:%s\n",obje.get(i).getHTL());


        }
    }
    @org.junit.Test
    public void getByCode() throws Exception {
        part1 a = new part1();
        part1csvread.CSVRead.readCsvFile("coursehastwice312.csv",a.list);
        /*
         This is same csv but only added twice of CSE 312 with different title
         credits etc course to see  list gets all c 312 courses not
         abandoning after getting first.

         */
        LinkedList<part1course> terrified= a.getByCode("CSE 312");
        part1Test.printCourses(terrified);
    }

    @org.junit.Test
    public void listSemesterCourses() throws Exception {
        part1 a = new part1();
        part1csvread.CSVRead.readCsvFile("coursehastwice312.csv",a.list);
        LinkedList<part1course> terrified= a.listSemesterCourses(1);
        part1Test.printCourses(terrified);


    }

    @org.junit.Test
    public void getByRange() throws Exception {
        part1 a = new part1();
        part1csvread.CSVRead.readCsvFile("coursehastwice312.csv",a.list);
        LinkedList<part1course> terrified= a.getByRange(20,25);
        part1Test.printCourses(terrified);
    }

}