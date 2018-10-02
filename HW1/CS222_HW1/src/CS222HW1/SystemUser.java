package CS222HW1;



/**
 * Created by paypaytr on 2/25/18.
 */
public abstract class SystemUser implements Human {
    private String mName,mSurname,mSex,mNation;
    private boolean mMarital;
    private int mAge;
    private String mUser,mPass;

    SystemUser(){
        mName = "default";
        mSurname = "surDefault";
        mSex = "MAN";
        mNation = "English";
        mMarital = false;
        mAge = 13;

    }

    SystemUser(String name,String surname,String sex,String nation,boolean marital,int age,String User,String Pass){
        mName = name;
        mSurname = surname;
        mNation = nation;
        mSex   = sex;
        mMarital = marital;
        mAge = age;
        mUser = User;
        mPass = Pass;



    }
    // This is going to print information about human.(Name SName Age Nationality etcetera)
    public void print(){
        System.out.println("I am"+" "+getName()+" "+getAge()+" "+getSex()+" "+getNationality()+" "+getMaritalToString()+".");
        // no more information because Receptionist is not going to stay in hotel.

    }
    //  Set Name and Surname
    public void setName(String name,String surname){
        mName = name;
    }
    // Set Age
    public void setAge(int age){
        mAge = age;
    }
    // Set Nationality
    public void setNationality(String nation){
        mNation = nation;
    }
    // Set Marital Status 0= not married 1=yes married
    public void setMarital(boolean marital){
        mMarital = marital;
    }
    // Set Sex -> string because there could be different types or other(not want to giv info)
    public void setSex(String sex){
        mSex = sex;
    }
    //  Set Name and Surname
    public String  getName(){
        return mName+" "+mSurname;
    }
    // Set Age
    public int getAge(){
        return mAge;
    }
    // Set Nationality
    public String getNationality(){
        return mNation;
    }
    // Set Marital Status 0= not married 1=yes married
    public boolean getMarital(){
        return mMarital;
    }
    // Set Sex
    public String getSex(){
        return mSex;
    }

    public String getMaritalToString() {

        if(mMarital)
            return "married";
        else
            return "single";
    }
    //return 1 if success on Booking,else 0

    abstract public boolean bookRoom(Room object);
    abstract public boolean cancelRoom();


    public String getmPass() {
        return mPass;
    }

    public void setmPass(String mPass) {
        this.mPass = mPass;
    }

    public String getmUser() {
        return mUser;
    }

    public void setmUser(String mUser) {
        this.mUser = mUser;
    }


}
