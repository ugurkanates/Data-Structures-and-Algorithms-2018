package CS222HW1;

import java.util.Random;

/**
 * Created by paypaytr on 2/25/18.
 */
public class Room {
    private int mRoomNo;
    private int mSize;
    private boolean mReserved;
    private boolean mChildRoom;
    private double mPriceDailyRate;
    private boolean mStayingNow;

    Room(){
        mRoomNo = 0;
        mSize = new Random().nextInt(5)+1;
        mChildRoom = false;
        mPriceDailyRate = 1.5;
        mReserved = false;
        mStayingNow = false;
    }
    Room(int roomNo,int size,boolean reserve,boolean child,boolean stayingnow){
        mRoomNo = roomNo;
        mSize = size;
        mReserved = reserve;
        mChildRoom = child;
        mPriceDailyRate = determineRate();
        mStayingNow = stayingnow;

    }

    public int getmRoomNo() {
        return mRoomNo;
    }

    public void setmRoomNo(int mRoomNo) {
        this.mRoomNo = mRoomNo;
    }

    public int getmSize() {
        return mSize;
    }

    public void setmSize(int mSize) {
        this.mSize = mSize;
    }

    public boolean ismReserved() {
        return mReserved;
    }

    public void setmReserved(boolean mReserved) {
        this.mReserved = mReserved;
    }

    public boolean ismChildRoom() {
        return mChildRoom;
    }

    public void setmChildRoom(boolean mChildRoom) {
        this.mChildRoom = mChildRoom;
    }

    public double getmPriceDailyRate() {
        return mPriceDailyRate;
    }

    public void setmPriceDailyRate(double mPriceDailyRate) {
        this.mPriceDailyRate = mPriceDailyRate;
    }
    public double determineRate(){
        if(mSize<=2)
            return 1.25;
        else if(mSize == 3)
            return 1.5;
        else
            return 2.0;
    }

    public boolean ismStayingNow() {
        return mStayingNow;
    }

    public void setmStayingNow(boolean mStayingNow) {
        this.mStayingNow = mStayingNow;
    }
}
