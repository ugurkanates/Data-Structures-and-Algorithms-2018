package CS222HW1;

/**
 * Created by paypaytr on 2/25/18.
 */
public interface Human {
    // This is going to print information about human.(Name SName Age Nationality etcetera)
    void print();
    //  Set Name and Surname
    void setName(String name,String surname);
    // Set Age
    void setAge(int age);
    // Set Nationality
    void setNationality(String nation);
    // Set Marital Status 0= not married 1=yes married
    void setMarital(boolean marital);
    // Set Sex
    void setSex(String sex);
    //  Set Name and Surname
    String getName();
    // Set Age
    int getAge();
    // Set Nationality
    String getNationality();
    // Set Marital Status 0= not married 1=yes married
    boolean getMarital();
    // Set Sex
    String getSex();



}
