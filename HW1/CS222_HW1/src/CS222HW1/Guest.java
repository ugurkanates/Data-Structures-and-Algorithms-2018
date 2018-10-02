package CS222HW1;

import java.util.Date;

/**
 * Created by paypaytr on 2/25/18.
 */
public class Guest extends SystemUser {
    private Room mRoom;
    private int mTotalPeople;
    private int mDaysStay;
    private int mDaysCurrent;
    public Date checkIn,checkOut;
    // these dates are public because while they must be in Guest only mutator is Receptionist which is not inheritted
    //I didndt added them in System User (which is superclass of both guest and reception because reception doesnt have
    //a check in or checkout . This is only solution is acceptable.




    // no Setter.

    Guest(){
        super();
        mRoom = null;
        mTotalPeople = 0;
        mDaysStay = 0;
        mDaysCurrent = 0;
        checkIn = new Date(1);
        checkOut = new Date(1);

    }
    Guest(String name,String surname,String sex,String nation,boolean marital,int age,int days,int people,String user,String pass){
        super(name,surname,sex,nation,marital,age,user,pass);
        mRoom = null;
        mTotalPeople = people;
        mDaysStay = days;
        mDaysCurrent = 0;
        checkIn = new Date(1);
        checkOut = new Date(1);


    }

    @Override
    public void print() {
        super.print();
        System.out.println("I am a hotel guest staying with("+getmTotalPeople()+")people including me.");
        System.out.println("I am staying for"+getmDaysStay());
       if(mRoom!=null)
        System.out.println("My otel room is "+mRoom.getmRoomNo());


    }

    public Room getmRoom() {
        return mRoom;
    }

    public void setmRoom(Room object){
        this.mRoom = object;
    }

     public boolean bookRoom(Room object){
        if(!object.ismReserved() && object.getmSize() == this.getmTotalPeople()){
            this.mRoom = object;
            mRoom.setmReserved(true);
            return true;
        }
         System.out.println("Couldnt Book Room");

         return false;
     }
    public boolean cancelRoom(){
        if(mRoom.ismReserved()){
            mRoom.setmReserved(false);
            this.mRoom = null;
            return true;
        }
        System.out.println("Couldnt cancel Room");

        return false;
    }

    public int getmTotalPeople() {
        return mTotalPeople;
    }

    public void setmTotalPeople(int mTotalPeople) {
        this.mTotalPeople = mTotalPeople;
    }

    public int getmDaysStay() {
        return mDaysStay;
    }

    public void setmDaysStay(int mDaysStay) {
        this.mDaysStay = mDaysStay;
    }

    public int getmDaysCurrent() {
        return mDaysCurrent;
    }

    public void setmDaysCurrent(int mDaysCurrent) {
        this.mDaysCurrent = mDaysCurrent;
    }
}
