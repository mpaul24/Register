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

    boolean checkedIn(){
        if(!Hostel.getInstance().getHostelStatus()){//not out of hostel
            return false;
        }else if(Library.getInstance().getLibraryStatus() || getCCStatus()){// in Library
            return false;
        }
        r = new Record(Student.getInstance(), new Date());
        inCC = true;
        return true;
    }

    boolean checkedOut(){
        if(!getCCStatus()) {
            return false;
        }
        r.setTime_in(new Date());
        r.setDuration();
        inCC = false;
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
