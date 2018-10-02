package CS222HW3;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by paypaytr on 3/22/18.
 */
public class part2Test {
    @Test
    public void disable() throws Exception {
        part1 a = new part1();
        part1csvread.CSVRead.readCsvFile("course.csv",a.list);
        //List read into ->part1 linked list to be used with part2
        part2 ker = new part2(a.list);
        //printing function lazim
        ker.disable(18);
        ker.showDisabled();
        // -> it goes into disabled list
        // -> if you tried to access within disable list it would show

    }

    @Test
    public void enable() throws Exception {
        part1 a = new part1();
        part1csvread.CSVRead.readCsvFile("course.csv",a.list);
        //List read into ->part1 linked list to be used with part2
        part2 ker = new part2(a.list);
        //printing function lazim
        ker.disable(19);
        ker.showDisabled();
        //printing 19th element
        ker.enable(19);
        ker.showDisabled();
        //printing no element is in disabed list
    }

    @Test
    public void showDisabled() throws Exception {
        part1 a = new part1();
        part1csvread.CSVRead.readCsvFile("course.csv",a.list);
        //List read into ->part1 linked list to be used with part2
        part2 ker = new part2(a.list);
        //printing function lazim
        ker.disable(15);
        ker.disable(16);
        ker.showDisabled();
        ker.enable(15);
        ker.showDisabled();

    }

}