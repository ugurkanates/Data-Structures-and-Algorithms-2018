package CS222HW1;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by paypaytr on 2/28/18.
 */
public class DemoMode {

    public static void test(){

        Hotel GTU;
        Receptionist Recep;
        ArrayList<Guest> konuklistesi;

        //TEST SCENARIO INTRODUCTION

        //IN THIS TEST
        //We will create new Hotel Object as GTU
        //We will create (1) Receptionist and a few Guest to test primary and important methods
        // LETS START


        GTU = new Hotel();
        konuklistesi = new ArrayList<>();
        CSVRead haydi = new CSVRead();
        haydi.readCsvFile("test.csv",konuklistesi,GTU);

        // Create and Initialize our Hotel GTU
        // Our hotel as seen made with 3rd floor each has 5 rooms.(for Testing purposes)

        Recep = new Receptionist("Recep the Reception", "Kel", "MALE", "TURKISH", false, 20,"admin","admin");
        //Create our Receptionist with info he need as HUMAN
        //He also has guest list(people he booked or interacted)
         Recep.print();
        // Print his info , used with it's superclass constructor plus it's exclusive info
        //exclusive info = he is the Receptionist

        // Now we are start adding Guests with different background to system.


        Guest mor = new Guest("Yilmaz", "Mor", "MAN", "Turk", true, 30, 10, 4,"default","default");

        //Hence we didnt randomize our rooms in initilization , all rooms are same with size of 3.
        // Mor Family wont able to fit in Hotel considering all rooms are same.
        //Let see before editing hotel rooms.
        //it wont find any rooms available for family in this room

        if(GTU.ifTheyFit(mor))
            System.out.println("We fit to hotel");
        else
                System.out.println("We are not fit to hotel.");


        // Else would be printed as predicted..

        //Lets edit some random room size as 4.(or any given Mor Family size
        //3 floor  5 rooms->
        Random ra = new Random();
        GTU.getRoom(ra.nextInt(Hotel.length),ra.nextInt(Hotel.roomnum)).setmSize(mor.getmTotalPeople());

        //Random Seed 0 - hotel length bound


        //now IT IS CERTAIN some random room within borders of hotel will be sized to keep 4 people.
        if(GTU.ifTheyFit(mor))
            System.out.println("We fit to hotel");
        else
                System.out.println("We are not fit to hotel.");


        //Let see which room they bookmarked? IT WILL BE RANDOMIZED EACH TIME ->

        System.out.println(mor.getmRoom().getmRoomNo());

        // Lets add one more manually guest before filling most of Hotel

   Guest can = new Guest("Remziye", "Can", "FEMALE", "Turk", true, 42, 7, 3,"default","default");

        konuklistesi.add(mor);
        konuklistesi.add(can);

        //Lets try this one with Receptionist Book out-
        //find room fits with Guest

        //before bookmarking a user by Receptionist you must add him to Receptionist Guest List
        Recep.addGuest(can);

        for(int i=0;i<Hotel.length;i++) {
        if (can.getmRoom() == null) {
            for (int j = 0; j < Hotel.roomnum; j++) {
                if (Recep.bookRoom(GTU.getRoom(i, j)))
                    break;
            }
        }
    }

        Guest kan = new Guest("Ucan", "Araba", "FEMALE", "Turk", true, 19, 7, 5,"abi","liseli");
        GTU.getRoom(ra.nextInt(Hotel.length),ra.nextInt(Hotel.roomnum)).setmSize(kan.getmTotalPeople());

        //recep new guest
        Guest zan = new Guest("Ermer", "Pasotanya", "FEMALE", "English", true, 19, 7, 3,"ucan","liseli");

        konuklistesi.add(kan);
        konuklistesi.add(zan);
        //adding last 2 remaning guest to guestlist.

        //adding  object zan (guest) to Recep  list
        //lets make him book a room (Recep for zan)
        //and Check that room
        //see its full now zan in it.
        //then check out

        Recep.addGuest(zan);
        for(int i=0;i<Hotel.length;i++) {
            if (zan.getmRoom() == null) {
                for (int j = 0; j < Hotel.roomnum; j++) {
                    if (Recep.bookRoom(GTU.getRoom(i, j)))
                        break;
                }
            }
        }
        // it will be same room unless we change and randomize rooms not be 3 default.
        // NOTICE THAT IF USER not staying in room line " MY OTEL ..."  is not appearing !

        zan.print();
        //While some otelroom id is attached to user with booking he is not staying yet (check in needed)
        Recep.checkIn(Recep.getGuest(Recep.getGuestSize()-1));

        //he made check let  see that in string !
       System.out.println(Recep.getCheckIn().toString());
        //ta da !!
        CSVWrite yazici = new CSVWrite();
        yazici.writeCsvFile("konuk.csv", konuklistesi);

    }
}
