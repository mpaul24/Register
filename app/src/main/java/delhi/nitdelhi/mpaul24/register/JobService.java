package delhi.nitdelhi.mpaul24.register;

import android.app.job.JobParameters;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

/**
 * Created by Mpaul24 on 10/7/2017.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class JobService extends android.app.job.JobService {
    @Override
    public boolean onStartJob(JobParameters params) {
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return true;
    }
}
