package CS222HW1;

import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by paypaytr on 2/27/18.
 */
public class Menu {

    int secim = 0;
    private ArrayList<Guest> konuklistesi;

    String username,password;
    Hotel GTU;
    Receptionist Recep;

    public void main(){
     GTU = new Hotel();
    konuklistesi = new ArrayList<>();
    CSVRead haydi = new CSVRead();
    haydi.readCsvFile("test.csv",konuklistesi,GTU);


    // Create and Initialize our Hotel GTU
    // Our hotel as seen made with 3rd floor each has 5 rooms.(for Testing purposes)

        Recep = new Receptionist("Recep the Reception", "Kel", "MALE", "TURKISH", false, 20,"admin","admin");

        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("1-DEMO MODE with TEST Scenario ");
        System.out.println("2-NORMAL MODE WITH USER LOGIN/LOGOUT ");
        int chosen = reader.nextInt();
        reader.nextLine();
        if(chosen==1) {
            // You need to read Comments to Understand the Demo presented in DemoMode.Java
            // CTRL + B while cursor on DemoMode also goes to file
            
            DemoMode.test();
        }
        else if(chosen == 2) {

            while (secim != 3) {
                System.out.println("1-login");
                System.out.println("2-new");
                System.out.println("3-exit");
                secim = reader.nextInt();
                reader.nextLine();

                if (secim == 0) {

                } else if (secim == 1) {
                    System.out.println("Enter Username");
                    username = reader.nextLine();
                    System.out.println("Enter Password");
                    password = reader.nextLine();
                    LogInfo go;
                    do {
                        go = login(konuklistesi, username, password);
                        if (go.isTrue) {
                           GuestFunctions(go);

                        }
                        else if (username.equals("admin") && password.equals("admin")){
                            ReceptionFunctions();

                        }
                        else {

                            System.out.println("try again !");
                            System.out.println("Enter Username");
                            username = reader.nextLine();
                            System.out.println("Enter Password");
                            password = reader.nextLine();


                        }
                    } while (!go.isTrue);

                } else if (secim == 2) {
                    System.out.println("Enter a new username");
                    username = reader.nextLine();
                    System.out.println("Enter a new password");
                    password = reader.nextLine();
                    System.out.println("Congrats,you registered to the Matrix.Please login!");

                    String tempName, tempSurname, tempSex, tempNation;
                    Boolean tempBool;
                    int tempAge, tempDays, tempPeople;

                    System.out.println("Enter your name:");
                    tempName = reader.nextLine();
                    System.out.println("Enter your  surname");
                    tempSurname = reader.nextLine();
                    System.out.println("Enter your gender");
                    tempSex = reader.nextLine();
                    System.out.println("Enter your Nationality");
                    tempNation = reader.nextLine();
                    System.out.println("Are you Married?");
                    System.out.println("1-Yes Married");
                    System.out.println("2-No Single");
                    Scanner integer = new Scanner(System.in);
                    if (integer.nextInt() == 1)
                        tempBool = true;
                    else
                        tempBool = false;
                    System.out.println("Your AGE");
                    tempAge = integer.nextInt();
                    System.out.println("How many people with you");
                    tempPeople = integer.nextInt();
                    System.out.println("How long are you gonna stay?");
                    tempDays = integer.nextInt();


                    Guest temp = new Guest(tempName, tempSurname, tempSex, tempNation, tempBool, tempAge, tempDays, tempPeople, username, password);
                    konuklistesi.add(temp);
                    Recep.addGuest(temp);

                }

            }


            //oh great they are in "DIFFERENT rooms with different sizes as you see


            //mor.print();
            //default param olan instruc edilince null room . fonksiyona ulaşınca hata verir

            //mor.bookRoom(bizimhote.getRoom(0,0));
            //mor.print();
            CSVWrite yazici = new CSVWrite();
            yazici.writeCsvFile("test.csv", konuklistesi);
        }
}

    private void ReceptionFunctions() {
        int breaker = -1;
        while (breaker == -1) {
            System.out.println("1-Book a Room");
            System.out.println("2-Check In");
            System.out.println("3-Check Out");
            System.out.println("4-Who Am I ?");
            System.out.println("5-LOGOUT");
            Scanner reader = new Scanner(System.in);  // Reading from System.in
            int choice = reader.nextInt();

            switch (choice) {
                case 1:
                    //book a room based on last user added to Receptionist list
                    for(int i=0;i<Hotel.length;i++) {
                        if (Recep.getGuest(Recep.getGuestSize()-1).getmRoom() == null) {
                            for (int j = 0; j < Hotel.roomnum; j++) {
                                if (Recep.bookRoom(GTU.getRoom(i, j)))
                                    break;
                            }
                        }
                    }
                    System.out.println(Recep.getGuest(Recep.getGuestSize()-1).getmRoom().getmRoomNo());

                    break;
                case 2:
                    //Check in on last user in Receptionist list
                    Recep.setCheckIn(new Date());
                    break;
                case 3:
                    Recep.setCheckOut(new Date());
                    break;
                case 4:
                    //Who Am I represent who is the user making calls now ;)
                    Recep.print();
                    break;

                case 5:
                    breaker = 0;
                    break;
            }
        }
    }

    private void GuestFunctions(LogInfo go) {
        int breaker = -1;
        while (breaker == -1) {
            System.out.println("1-Book a Room");
            System.out.println("2-Cancel a Reservation");
            System.out.println("3-Who Am I ?");
            System.out.println("4-Whats Current Bill?");
            System.out.println("5-LOGOUT");
            Scanner reader = new Scanner(System.in);  // Reading from System.in
            int choice = reader.nextInt();

            switch (choice) {
                case 1:
                    //book a room based on index user -> who logged and made the call
                    try{
                        if(!GTU.ifTheyFit(konuklistesi.get(go.index)))
                            throw new Exception("failed system call sorry.");
                    }
                    catch (Exception e){
                        continue;
                    }
                    System.out.println(konuklistesi.get(go.index).getmRoom().getmRoomNo());

                    break;
                case 2:
                        //cancel a room based on index user -> who logged and wants to cancel
                        konuklistesi.get(go.index).cancelRoom();
                    break;
                case 3:
                        //Who Am I represent who is the user making calls now ;)
                        konuklistesi.get(go.index).print();
                    break;
                case 4:
                    if(konuklistesi.get(go.index).getmRoom()!=null)
                    System.out.println("Your bill is : " +
                            konuklistesi.get(go.index).getmDaysStay()*konuklistesi.get(go.index).getmRoom().getmPriceDailyRate());
                    break;
                case 5:
                    breaker = 0;
                    break;
            }
        }
    }

    public  LogInfo login(ArrayList<Guest>konuklar,String user,String pass){
        for (int i = 0; i<konuklar.size();i++){
            if(konuklar.get(i).getmUser().equals(user) && konuklar.get(i).getmPass().equals(pass))
                return new LogInfo(true,i);

        }
        return new LogInfo(false,0);
    }

    private class LogInfo {
        public boolean isTrue = false;
        public int index = 0 ;

        LogInfo(boolean a,int b){
            isTrue = a;
            index = b;
        }

    }
}
