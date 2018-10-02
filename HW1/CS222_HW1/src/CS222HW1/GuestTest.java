package CS222HW1;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by paypaytr on 3/4/18.
 */
public class GuestTest {
    @Test
    public void print() throws Exception {
        Guest testle = new Guest();
        testle.print();

    }

    @Test
    public void bookRoom() throws Exception {
        Room test = new Room();
        Guest testle = new Guest();
        if(testle.bookRoom(test)) {
            System.out.println("booked room");
            testle.print();
        }

    }

    @Test
    public void cancelRoom() throws Exception {
        Room test = new Room();
        Guest testle = new Guest();
        testle.bookRoom(test);
        if(testle.cancelRoom()) {
            System.out.println("cancelled room");
            testle.print();
        }


    }

}