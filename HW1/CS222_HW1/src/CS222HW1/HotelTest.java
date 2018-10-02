package CS222HW1;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by paypaytr on 3/4/18.
 */
public class HotelTest {
    @Test
    public void test_init() throws Exception {
        Hotel testle = new Hotel();
        // no input taken
        testle.test_init();
    }

    @Test
    public void ifTheyFit() throws Exception {
        Hotel testle = new Hotel();
        Guest testkonuk = new Guest();
        testkonuk.setmTotalPeople(3);
        //setting unit test
        if(testle.ifTheyFit(testkonuk))
            System.out.println("they fit");
        else
            System.out.println("they dont fit");

    }

}