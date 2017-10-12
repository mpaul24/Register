package delhi.nitdelhi.mpaul24.register;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Mpaul24 on 10/7/2017.
 */

public class NetworkChangeReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(MainActivity.TAG,"onRecieve");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //if(isOnline(context))
                Util.scheduleJob(context);
        }
    }

    public boolean isOnline(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        //should check null because in airplane mode it will be null
        Log.e(MainActivity.TAG,"Hello");
        return (netInfo != null && netInfo.isConnected());
    }
}
