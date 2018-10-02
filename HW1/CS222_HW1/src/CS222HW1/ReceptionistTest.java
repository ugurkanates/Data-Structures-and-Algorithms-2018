package CS222HW1;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by paypaytr on 3/4/18.
 */
public class ReceptionistTest {
    @Test
    public void addGuest() throws Exception {
       Receptionist  Recep = new Receptionist("Recep the Reception",
               "Kel", "MALE", "TURKISH", false, 20,"admin","admin");
        Guest mor = new Guest("Yilmaz",
                "Mor", "MAN", "Turk", true, 30, 10, 4,"default","default");
        Recep.addGuest(mor);
        Recep.getGuest(Recep.getGuestSize()-1).print();

    }

    @Test
    public void bookRoom() throws Exception {
        Receptionist  Recep = new Receptionist("Recep the Reception",
                "Kel", "MALE", "TURKISH", false, 20,"admin","admin");
        Guest mor = new Guest("Yilmaz",
                "Mor", "MAN", "Turk", true, 30, 10, 3,"default","default");
        Recep.addGuest(mor);
        Room test = new Room(101,3,false,false,false);
        //last always used
        if(Recep.bookRoom(test));
            System.out.println("receptionist booked a room");

    }

    @Test
    public void cancelRoom() throws Exception {
        Receptionist Recep = new Receptionist("Recep the Reception",
                "Kel", "MALE", "TURKISH", false, 20, "admin", "admin");
        Guest mor = new Guest("Yilmaz",
                "Mor", "MAN", "Turk", true, 30, 10, 3, "default", "default");
        Recep.addGuest(mor);
        Room test = new Room(101,3,false,false,false);
        Recep.bookRoom(test);
        Recep.cancelRoom();
        System.out.println("receptionist cancelled a room");

    }
    @Test
    public void checkIn() throws Exception {
        Receptionist Recep = new Receptionist("Recep the Reception",
                "Kel", "MALE", "TURKISH", false, 20, "admin", "admin");
        Guest mor = new Guest("Yilmaz",
                "Mor", "MAN", "Turk", true, 30, 10, 3, "default", "default");
        Recep.addGuest(mor);
        Room test = new Room(101,3,false,false,false);
        Recep.bookRoom(test);
        Recep.checkIn(mor);
        System.out.println("checkin done");

    }

    @Test
    public void checkOut() throws Exception {
        Receptionist Recep = new Receptionist("Recep the Reception",
                "Kel", "MALE", "TURKISH", false, 20, "admin", "admin");
        Guest mor = new Guest("Yilmaz",
                "Mor", "MAN", "Turk", true, 30, 10, 3, "default", "default");
        Recep.addGuest(mor);
        Room test = new Room(101,3,false,false,false);
        Recep.bookRoom(test);
        Recep.checkIn(mor);
        System.out.println("checkin done");
        Recep.checkOut(mor);
        System.out.println("checkout done");
    }

}