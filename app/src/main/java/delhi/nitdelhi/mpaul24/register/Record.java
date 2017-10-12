package delhi.nitdelhi.mpaul24.register;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mpaul24 on 10/5/2017.
 */

public class Record {
    RecordType rt;
    Student student;
    Date time_in;
    Date time_out;
    long duration;
    String tIn;
    String tOut;

    public void setDuration() {
        this.duration = Math.abs(time_in.getTime()-time_out.getTime());
    }

    public Record(Student student, RecordType rc) {
        this.student = student;
        this.rt = rc;
    }

    public Date getTime_in() {
        return time_in;
    }

    public Date getTime_out() {
        return time_out;
    }

    public void setTime_out(Date time_out) {
        this.time_out = time_out;
        SimpleDateFormat ft =
                new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        tOut = ft.format(time_out);
    }

    public void setTime_in(Date time_in) {
        this.time_in = time_in;
        SimpleDateFormat ft =
                new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        tIn = ft.format(time_in);
    }
}
