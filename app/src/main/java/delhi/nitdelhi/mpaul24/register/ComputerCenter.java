package delhi.nitdelhi.mpaul24.register;

import com.google.gson.Gson;

import java.util.Date;

/**
 * Created by Mpaul24 on 10/5/2017.
 */

public class ComputerCenter extends Facility {

    Record r;
    boolean inCC=false;
    static ComputerCenter computerCenter;

    static ComputerCenter getInstance(){
        if(computerCenter==null){
            computerCenter = new ComputerCenter();
            return computerCenter;
        }
        return computerCenter;
    }

    static void RestoreObject(ComputerCenter c){
        computerCenter =  c;
    }

    boolean getCCStatus(){
        return inCC;
    }

    int checkedIn(){
        if(Library.getInstance().getLibraryStatus() ){// in Library
            return -1;
        }else if(getCCStatus()){
            return -2;
        }else if(!Hostel.getInstance().getHostelStatus() && !Student.getInstance().getHostel().equals("DAY SCHOLAR")){//not out of hostel
            return 0;
        }
        r = new Record(Student.getInstance(),RecordType.ComputerCenter);
        r.setTime_in(new Date());
        inCC = true;
        return 1;
    }

    boolean checkedOut(){
        if(!getCCStatus()) {
            return false;
        }
        r.setTime_out(new Date());
        r.setDuration();
        inCC = false;
        addRecord();
        return true;
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
