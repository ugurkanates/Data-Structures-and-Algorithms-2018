package CS222HW1;

/**
 * Created by paypaytr on 2/25/18.
 */
public class Hotel {
    //our Hotel will be 3 floor to 5(rooms)
    public static final int length = 3;
    public static final int roomnum = 5;
    public static final int theHotel = length*roomnum;

    private Room[][] mRoom = new Room[length][roomnum];
    Hotel(){
        test_init();
    }

/*
    public Room getmRoom() {
        return mRoom;
    }

    public void setmRoom(Room mRoom) {
        this.mRoom = mRoom;
    }
    */

    public void test_init(){
        int roomNon = 100;
        for (int i = 0; i <length; i++)
        {
            roomNon*=(i+1);

            for (int j = 0; j <roomnum; j++)
            {

                mRoom[i][j]=new Room();
                mRoom[i][j].setmRoomNo(roomNon);
                roomNon++;
            }
            roomNon=100;
        }

    }
    public Room getRoom(int x,int y){
        return mRoom[x][y];
    }
    public boolean ifTheyFit(Guest obje){
        for(int i=0;i<Hotel.length;i++)
            for(int j=0;j<Hotel.roomnum;j++)
                if(obje.bookRoom(mRoom[i][j]))
                    return true;

        return false;
    }



    //giris saati
}
