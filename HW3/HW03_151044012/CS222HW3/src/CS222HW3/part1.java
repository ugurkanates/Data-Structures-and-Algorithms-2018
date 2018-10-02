package CS222HW3;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by paypaytr on 3/19/18.
 */
public class part1 {
    public static void main(String[] args) {
        part1 a = new part1();
        part1csvread.CSVRead.readCsvFile("course.csv",a.list);
        LinkedList<part1course> terrified= a.getByCode("CSE 312");
        LinkedList<part1course> abi= a.listSemesterCourses(1);
        LinkedList<part1course> anne = a.getByRange(1,4);
        part1Test.printCourses(a.list);

    }

    public LinkedList<part1course> list = new LinkedList<part1course>();

        public LinkedList<part1course> getByCode(String code) {
            LinkedList<part1course> willReturn = new LinkedList<part1course>();
            Iterator<part1course> Iteratore = list.iterator();
            while (Iteratore.hasNext()) {
                part1course obje = Iteratore.next(); //dogrumu bak next mi bi oncesimi
                if (obje.getCourseCode().equals(code))
                    willReturn.add(obje);
            }
            return willReturn;
        }

       public LinkedList<part1course> listSemesterCourses(int semester){
           LinkedList<part1course> willReturn = new LinkedList<part1course>();
            Iterator<part1course> Iteratore= list.iterator();
            while (Iteratore.hasNext()) {
                part1course obje = Iteratore.next();
                if(obje.getSemester() == semester)
                    willReturn.add(obje);
            }
            return willReturn;
        }


        public LinkedList<part1course> getByRange(int start_index,int last_index){
           //NOTICE -> INDEX OF CLASSES IN CSV STARTS FROM 2 while program starts from 0 ->
            //tag -> not included as node element of course
            // also index starts from 0

           // if(start_index<0 && last_index>list.size())
             //   throw new ArrayIndexOutOfBoundsException("Start Index or Last Index are wrong !");
            LinkedList<part1course> willReturn = new LinkedList<part1course>();
            for(int i=start_index;i<last_index;i++){
                willReturn.add(list.get(i));
            }
            return willReturn;
        }


    };





