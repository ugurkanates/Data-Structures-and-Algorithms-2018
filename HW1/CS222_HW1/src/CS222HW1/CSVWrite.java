package CS222HW1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by paypaytr on 2/28/18.
 */
public class CSVWrite {

    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String FILE_HEADER = "Full Name,Sex,Nation,Marital,Age,Username,Password,Room ID," +
            "Total People,Total Days to Stay,Current Days,CheckIn Date,CheckOut Date";

    public static void writeCsvFile(String fileName, ArrayList<Guest> konuklar) {


        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(fileName);

            //Write the CSV file header
            fileWriter.append(FILE_HEADER.toString());

            //Add a new line separator after the header
            fileWriter.append(NEW_LINE_SEPARATOR);

            //Write a new student object list to the CSV file
            for (Guest konuk : konuklar) {
                fileWriter.append(String.valueOf(konuk.getName()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(konuk.getSex());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(konuk.getNationality());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(konuk.getMaritalToString());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(konuk.getAge()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(konuk.getmUser()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(konuk.getmPass()));
                fileWriter.append(COMMA_DELIMITER);
                if(konuk.getmRoom()!=null)
                    fileWriter.append(String.valueOf(konuk.getmRoom().getmRoomNo()));
                else
                    fileWriter.append(" ");
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(konuk.getmTotalPeople()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(konuk.getmDaysStay()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(konuk.getmDaysCurrent()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(konuk.checkIn.toString()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(konuk.checkOut.toString()));
                fileWriter.append(NEW_LINE_SEPARATOR);


            }


            System.out.println("CSV file was created successfully !!!");

        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        } finally {

            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }

        }
    }

}




