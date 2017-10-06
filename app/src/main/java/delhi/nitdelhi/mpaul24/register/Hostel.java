package delhi.nitdelhi.mpaul24.register;

import android.util.Log;

import com.google.gson.Gson;

import java.util.Date;

/**
 * Created by Mpaul24 on 10/5/2017.
 */

public class Hostel extends Facility {
    boolean outOfHostel=false;
    Record r;
    static Hostel hostel ;

    static Hostel getInstance(){//singleton
        if(hostel==null){
            hostel = new Hostel();
            return hostel;
        }
        return hostel;
    }

    static void RestoreObject(Hostel h){
        hostel =  h;
    }

    boolean getHostelStatus(){
        return outOfHostel;
    }

    boolean checkedOut(Record r){
        Log.e(MainActivity.TAG,""+outOfHostel);
        if(getHostelStatus()){
            return false;
        }
        outOfHostel = true;
        this.r = r;
        return true;
    }

    boolean checkedIn(){
        Log.e(MainActivity.TAG,""+outOfHostel);
        if(Library.getInstance().getLibraryStatus() || ComputerCenter.getInstance().getCCStatus() ||
                !getHostelStatus()){
            Log.e(MainActivity.TAG,Library.getInstance().getLibraryStatus()+"\n"+ComputerCenter.getInstance().getCCStatus()+
            "\n"+!getHostelStatus()+"\n");
            return false;
        }
        r.setTime_in(new Date());
        r.setDuration();
        outOfHostel = false;
        addRecord();
        return true;
    }


    @Override
    void addRecord() {
        Gson gson = new Gson();
        String record = gson.toJson(r);
        Collection.getInstance().add(record);
    }

    @Override
    void delRecord() {

    }

    @Override
    boolean checkRecord() {
        return false;
    }
}
