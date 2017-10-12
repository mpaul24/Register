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
        if(getHostelStatus()){
            return false;
        }
        outOfHostel = true;
        this.r = r;
        r.setTime_out(new Date());
        return true;
    }

    int checkedIn(){
        if(Library.getInstance().getLibraryStatus()){
            return -1;
        }else if(ComputerCenter.getInstance().getCCStatus()){
            return -2;
        }else if(!getHostelStatus()){
            return 0;
        }
        r.setTime_in(new Date());
        r.setDuration();
        outOfHostel = false;
        addRecord();
        return 1;
    }


    @Override
    void addRecord() {
        Gson gson = new Gson();
        String record = gson.toJson(r);
        Collection.getInstance().add(r);
    }

    @Override
    void delRecord() {

    }

    @Override
    boolean checkRecord() {
        return false;
    }
}
