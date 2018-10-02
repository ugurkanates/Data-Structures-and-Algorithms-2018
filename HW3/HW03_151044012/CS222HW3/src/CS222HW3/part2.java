package CS222HW3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by paypaytr on 3/19/18.
 */
public class part2<E> extends LinkedList<E> {
    public static void main(String[] args) {

        part2<part1course> hello = new part2<part1course>();
        /*hello.add("asd");
        hello.add("das");
        hello.add("a");
        hello.add("b");
        h*ello.disable(2);
        hello.enable(2);
        hello.showDisabled();*/
        part1 a = new part1();
        part1csvread.CSVRead.readCsvFile("course.csv",a.list);
        LinkedList<part1course> liste = new LinkedList<>(a.list);
        //hello = liste;
        part2 ker = new part2(a.list);
        ker.disable(18);


        System.out.println("ss");


    }
    public void disable(int index){
        E newItem = get(index);
        checkEnough(index);
        disabledList.add(newItem);
        remove(index);
    }
    public void enable(int index){
        //if already true or false exception falan yapak oho vakitalcak.
        //if anything else removed find that->
        E item = findItem(index);
        disabledList.add(index+1,disabledList.get(0));
        add(index,item);
        disabledList.remove(index);
    }

    private void checkEnough(int index) {
        for(int i = disabledList.size()-1 ; i < index-1;i++){
            E temp = null;
            disabledList.add(temp);
        }

    }

    private E findItem(int index) {
            if(disabledList.get(index) != null) {
                E willReturn = disabledList.get(index);
                return willReturn;
            }
        else return null;
    }

    public void showDisabled(){
        int a=0;
        for(int i = 0 ; i < disabledList.size();i++){
            if(disabledList.get(i) != null) {
                System.out.printf("This %d numbered item from list is disabled.Re-enable to use methods on this item.\n", i);
                a++;
            }
        }
        if(a==0)
            System.out.println("no element in disabled list.");
    }
    public void printForInt(){
        for(int i = 0 ; i <size();i++)
            System.out.printf("Item index %d and value of item",i,get(i).toString());
        //100de yuz 100 alır

    }

    ArrayList<E> disabledList;
    part2(){
        disabledList = (ArrayList<E>) new ArrayList<E>();

    }
    part2(LinkedList<part1course> liste){
        disabledList = (ArrayList<E>) new ArrayList<E>();

        for(int i= 0;i<liste.size();i++){
            add((E) liste.get(i));
        }
    }

    //constructor 10 yapcak min ->

    //overriding methods to work with my class

}
