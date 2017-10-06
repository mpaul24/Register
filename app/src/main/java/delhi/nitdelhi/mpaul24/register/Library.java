package delhi.nitdelhi.mpaul24.register;

import com.google.gson.Gson;

import java.util.Date;

/**
 * Created by Mpaul24 on 10/5/2017.
 */

public class Library extends Facility {
    Record r;
    boolean inLibrary=false;
    static Library library;

    static Library getInstance(){
        if(library==null){
            library = new Library();
            return library;
        }
        return library;
    }

    static void RestoreObject(Library l){
        library =  l;
    }

    boolean getLibraryStatus(){
        return inLibrary;
    }

    boolean checkedIn(){
        if(!Hostel.getInstance().getHostelStatus()){//out of hostel
            return false;
        }else if(ComputerCenter.getInstance().getCCStatus() || getLibraryStatus()){//in CC
            return false;
        }
        r = new Record(Student.getInstance(), new Date());
        inLibrary = true;
        return true;
    }

    boolean checkedOut(){
        if(!getLibraryStatus()) {
            return false;
        }
        r.setTime_in(new Date());
        r.setDuration();
        inLibrary = false;
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
