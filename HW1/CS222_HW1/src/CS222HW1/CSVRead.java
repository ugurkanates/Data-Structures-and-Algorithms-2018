package CS222HW1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by paypaytr on 2/28/18.
 */
public class CSVRead {

    //Delimiter used in CSV file
    private static final String FILE_HEADER = "Full Name,Sex,Nation,Marital,Age,Username,Password,Room ID," +
            "Total People,Total Days to Stay,Current Days,CheckIn Date,CheckOut Date";
    private static final String COMMA_DELIMITER = ",";
    //namede name+surname boslukla split et atarken
    //bos gelen room no -1 ata yada bisi ata unutma


    //Student attributes index
    private static final int NAME = 0;
    private static final int SEX= 1;
    private static final int NATION = 2;
    private static final int MARITAL = 3;
    private static final int AGE = 4;
    private static final int USER = 5;
    private static final int PASS = 6;
    private static final int ROOMID = 7;
    private static final int TOTALP = 8;
    private static final int TOTALD = 9;
    private static final int CURRENT = 10;
    private static final int CHECKIN = 11;
    private static final int CHECKOUT = 12;






    public static void readCsvFile(String fileName,ArrayList<Guest> konuklar,Hotel GTU) {

        BufferedReader fileReader = null;

        try {

            //Create a new list of student to be filled by CSV file data

            String line = "";

            //Create the file reader
            fileReader = new BufferedReader(new FileReader(fileName));

            //Read the CSV file header to skip it
            fileReader.readLine();

            //Read the file line by line starting from the second line
            while ((line = fileReader.readLine()) != null) {
                //Get all tokens available in line
                String[] tokens = line.split(COMMA_DELIMITER);
                if (tokens.length > 0) {
                    //Create a new student object and fill his  data
                    Guest konuk = new Guest();
                    // Name Surname Tokens ->
                    String[] adlar = tokens[NAME].split(" ");
                    konuk.setName(adlar[0],adlar[1]);
                    konuk.setSex(tokens[SEX]);
                    konuk.setNationality(tokens[NATION]);
                    if(tokens[MARITAL].equals("married"))
                        konuk.setMarital(true);
                    else
                        konuk.setMarital(false);
                    konuk.setAge(Integer.parseInt(tokens[AGE]));
                    konuk.setmUser(tokens[USER]);
                    konuk.setmPass(tokens[PASS]);
                    if(!tokens[ROOMID].equals(" ")) {
                        konuk.setmRoom(GTU.getRoom((Integer.parseInt(tokens[ROOMID])/100)-1,(Integer.parseInt(tokens[ROOMID])%100)));
                            //103 ,  103 / 100 = 1  103%100 = 3  , 1,3 x,y rooms in  -1 for 103 = actually 0 3 you know
                        konuk.getmRoom().setmReserved(true);
                        konuk.getmRoom().setmStayingNow(true);
                    }

                    konuk.setmTotalPeople(Integer.parseInt(tokens[TOTALP]));
                    konuk.setmDaysStay(Integer.parseInt(tokens[TOTALD]));
                    konuk.setmDaysCurrent(Integer.parseInt(tokens[CURRENT]));
                   DateFormat format = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);

                   //BUNU YAPMAK COK ZORDU -> oh be thanks

                    konuk.checkIn = format.parse(tokens[CHECKIN]);
                    konuk.checkOut = format.parse(tokens[CHECKOUT]);



                    konuklar.add(konuk);
                }
            }


        }
        catch (Exception e) {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                System.out.println("Error while closing fileReader !!!");
                e.printStackTrace();
            }
        }

    }
}
