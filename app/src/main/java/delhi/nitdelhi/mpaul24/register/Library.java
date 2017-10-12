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

    int checkedIn(){
        if(ComputerCenter.getInstance().getCCStatus() ){//in CC
            return 0;
        }else if(getLibraryStatus()){
            return -2;
        }else if(!Hostel.getInstance().getHostelStatus() && !Student.getInstance().getHostel().equals("DAY SCHOLAR")){//not out of hostel
            return -1;
        }
        r = new Record(Student.getInstance(),RecordType.Library);
        r.setTime_in(new Date());
        inLibrary = true;
        return 1;
    }

    boolean checkedOut(){
        if(!getLibraryStatus()) {
            return false;
        }
        r.setTime_out(new Date());
        r.setDuration();
        inLibrary = false;
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
