package CS222HW1;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by paypaytr on 2/25/18.
 */
public class Receptionist extends SystemUser {

    private ArrayList<Guest> mGuest;

    Receptionist(){
        super();
        mGuest = new ArrayList<Guest>();
    }
    Receptionist(String name,String surname,String sex,String nation,boolean marital,int age,String user,String pass){
        super(name,surname,sex,nation,marital,age,user,pass);
        // Receptionist not going to stay in Hotel.
        mGuest = new ArrayList<Guest>();


    }

    public Guest getGuest(int index) {
        return mGuest.get(index);
    }
    public int getGuestSize() {
        return mGuest.size();
    }


    public void addGuest(Guest aGuest) {
        mGuest.add(aGuest);
    }

    public boolean bookRoom(Room object){

        //Hence clever programming and using bookRoom twice last booking will be used as index.
        if(!object.ismReserved() && object.getmSize() == mGuest.get(mGuest.size()-1).getmTotalPeople()){
            mGuest.get(mGuest.size()-1).setmRoom(object);
            mGuest.get(mGuest.size()-1).getmRoom().setmReserved(true);
            return true;
        }
        System.out.println("Couldnt Book Room");
        return false;
    }
    public boolean cancelRoom(){

        if(mGuest.get(mGuest.size()-1).getmRoom().ismReserved()){
            mGuest.get(mGuest.size()-1).getmRoom().setmReserved(false);
            return true;
        }
        return false;
    }

    public void checkIn(Guest Konuk){
        Konuk.getmRoom().setmStayingNow(true);


    }

    public void checkOut(Guest Konuk){
        Konuk.getmRoom().setmStayingNow(false);

    }
    public Date getCheckIn() {
        return mGuest.get(getGuestSize()-1).checkIn;
    }

    public void setCheckIn(Date checkIne) {
        mGuest.get(getGuestSize()-1).checkIn = checkIne;
    }

    public Date getCheckOut() {
        return mGuest.get(getGuestSize()-1).checkOut;
    }

    public void setCheckOut(Date checkOute) {
        mGuest.get(getGuestSize()-1).checkIn = checkOute;
    }

}
