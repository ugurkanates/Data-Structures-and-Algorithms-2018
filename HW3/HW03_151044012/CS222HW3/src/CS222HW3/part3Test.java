package CS222HW3;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by paypaytr on 3/22/18.
 */
public class part3Test {
    @Test
    public void next() throws Exception {
        part3 a = new part3();
        part1course na = new part1course();
        part1course nb = new part1course(2,"MALZEMEKOURSU",2,2,"MALZEMELERALIR");
        part1course nc = new part1course(3,"MIMARLARALSIN",2,2,"mimariseyler");
        part1course nd  = null;

        a.add(na);
        a.add(nb);
        a.add(nc);

        //Let start iterating next elements and assign&print to ND

        nd = a.next();
        nd.printer();
        //first element is gotta be default element considering NA is no param constructor
        nd= a.next();
        nd.printer();
        //now NB with malzemekorsu  title is malzemeleralır
        nd= a.next();
        nd.printer();
        // mimarlar alsin with mimari seyler


    }

    @Test
    public void nextSemester() throws Exception {
        part3 a = new part3();
        part1course na = new part1course();
        part1course nb = new part1course(2,"MALZEMEKOURSU",2,2,"MALZEMELERALIR");
        part1course nc = new part1course(3,"MIMARLARALSIN",2,2,"mimariseyler");
        part1course nf = new part1course(2,"TESTALSIN",2,2,"acayipseyler");

        part1course nd  = null;

        a.add(na);
        a.add(nb);
        a.add(nc);
        a.add(nf);

        nd = a.next();
        //after first next we are at nb with  SEMESTER 2 if i go next semester testalsin with semester 2 should appear
        nd=a.nextSemester();
        nd.printer();
        //if i go next semester again malzemekorsu should appear again
        nd = a.nextSemester();
        nd.printer();
    }

    @Test
    public void add() throws Exception {
        part3 a = new part3();
        part1course na = new part1course();
        part1course nb = new part1course(2,"MALZEMEKOURSU",2,2,"MALZEMELERALIR");
        part1course nc = new part1course(3,"MIMARLARALSIN",2,2,"mimariseyler");
        part1course nf = new part1course(2,"TESTALSIN",2,2,"acayipseyler");

        part1course nd  = null;

        a.add(na);
        a.add(nb);
        a.add(nc);
        a.add(nf);

        nd = a.next();
        nd.printer();
        nd = a.next();
        nd.printer();

        //it shows elements added and print them to screen
    }

    @Test
    public void remove() throws Exception {
        part3 a = new part3();
        part1course na = new part1course();
        part1course nb = new part1course(2,"MALZEMEKOURSU",2,2,"MALZEMELERALIR");
        part1course nc = new part1course(3,"MIMARLARALSIN",2,2,"mimariseyler");
        part1course nf = new part1course(2,"TESTALSIN",2,2,"acayipseyler");


        a.add(na);
        a.add(nb);
        a.add(nc);
        a.add(nf);

        System.out.printf("size is% d\n",a.size());
        a.remove(na);
        System.out.printf("size is% d\n",a.size());

    }

    @Test
    public void size() throws Exception {
        part3 a = new part3();
        part1course na = new part1course();
        part1course nb = new part1course(2,"MALZEMEKOURSU",2,2,"MALZEMELERALIR");
        part1course nc = new part1course(3,"MIMARLARALSIN",2,2,"mimariseyler");
        part1course nf = new part1course(2,"TESTALSIN",2,2,"acayipseyler");


        a.add(na);
        a.add(nb);
        a.add(nc);
        a.add(nf);

        System.out.printf("size is% d\n",a.size());

    }

}