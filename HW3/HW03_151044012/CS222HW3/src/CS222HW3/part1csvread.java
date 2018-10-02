/**
 * Created by paypaytr on 3/21/18.
 */
package CS222HW3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
public class part1csvread {


    /**
     * Created by paypaytr on 2/28/18.
     */
    public static class CSVRead {

        //Delimiter used in CSV file
        private static final String FILE_HEADER = "Semester,Course Code,Course Title,ECTS Credits,GTU Credits,H+T+L";
        private static final String COMMA_DELIMITER = ",";
        //namede name+surname boslukla split et atarken
        //bos gelen room no -1 ata yada bisi ata unutma


        //Student attributes index
        private static final int SEMESTER = 0;
        private static final int CODE= 1;
        private static final int TITLE = 2;
        private static final int ECTS = 3;
        private static final int GTU = 4;
        private static final int HTL = 5;






        public static void readCsvFile(String fileName, LinkedList<part1course> liste) {

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
                        part1course konuk = new part1course();
                        // Name Surname Tokens ->
                            konuk.setSemester(Integer.parseInt(tokens[SEMESTER]));

                        konuk.setCourseCode(tokens[CODE]);
                        konuk.setCourseTitle(tokens[TITLE]);

                        konuk.setEctsCredits(Double.parseDouble(tokens[ECTS]));
                        konuk.setGtuCredits(Double.parseDouble(tokens[GTU]));
                        try {
                            konuk.setHTL(tokens[HTL]);
                        }
                        catch (IndexOutOfBoundsException e){
                            konuk.setHTL("0+0+0");
                        }
                        liste.add(konuk);


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
        public static void readCsvFileForPart3(String fileName,part3 liste) {

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
                        part1course konuk = new part1course();
                        // Name Surname Tokens ->
                        konuk.setSemester(Integer.parseInt(tokens[SEMESTER]));

                        konuk.setCourseCode(tokens[CODE]);
                        konuk.setCourseTitle(tokens[TITLE]);

                        konuk.setEctsCredits(Double.parseDouble(tokens[ECTS]));
                        konuk.setGtuCredits(Double.parseDouble(tokens[GTU]));
                        try {
                            konuk.setHTL(tokens[HTL]);
                        }
                        catch (IndexOutOfBoundsException e){
                            konuk.setHTL("0+0+0");
                        }
                        liste.add(konuk);


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

}
