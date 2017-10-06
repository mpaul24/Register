package delhi.nitdelhi.mpaul24.register;

import java.util.Date;

/**
 * Created by Mpaul24 on 10/5/2017.
 */

public class Record {
    Student student;
    Date time_in;
    Date time_out;
    long duration;

    public void setDuration() {
        this.duration = Math.abs(time_in.getTime()-time_out.getTime());
    }

    public Record(Student student, Date time_out) {
        this.student = student;
        this.time_out = time_out;
    }

    public void setTime_in(Date time_in) {
        this.time_in = time_in;
    }
}
