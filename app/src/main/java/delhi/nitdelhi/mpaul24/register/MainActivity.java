package delhi.nitdelhi.mpaul24.register;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    Button register,hosButton,libButton,ccButton;
    public static final String SHAREDPREF = "myPreference";
    public static final String HOSTEL = "hostel";
    public static final String CC = "cc";
    public static final String LIBRARY = "library";
    public static final String STUDENT = "student";
    public static final String TAG = "tag";
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = getSharedPreferences(SHAREDPREF,MODE_PRIVATE);
        if(pref.contains(HOSTEL)){
            String h = pref.getString(HOSTEL,"");
            String l = pref.getString(LIBRARY,"");
            String c = pref.getString(CC,"");
            String s = pref.getString(STUDENT,"");
            Gson gson = new Gson();
            Hostel.RestoreObject(gson.fromJson(h,Hostel.class));
            Library.RestoreObject(gson.fromJson(l,Library.class));
            ComputerCenter.RestoreObject(gson.fromJson(c,ComputerCenter.class));
            Student.RestoreObject(gson.fromJson(s,Student.class));
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        register = (Button) findViewById(R.id.register);
        hosButton = (Button) findViewById(R.id.hosButton);
        libButton = (Button) findViewById(R.id.libButton);
        ccButton = (Button) findViewById(R.id.ccButton);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
            }
        });

        hosButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this,HostelActivity.class));
                    }
                }
        );

        libButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this,LibraryActivity.class));
                    }
                }
        );

        ccButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this,CCActivity.class));
                    }
                }
        );



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroyed");
    }

    @Override
    protected void onStop() {
        super.onStop();
        pref = getSharedPreferences(SHAREDPREF,MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();
        editor.putString(HOSTEL,gson.toJson(Hostel.getInstance()));
        editor.putString(LIBRARY,gson.toJson(Library.getInstance()));
        editor.putString(CC,gson.toJson(ComputerCenter.getInstance()));
        editor.putString(STUDENT,gson.toJson(Student.getInstance()));
        Log.e(TAG,gson.toJson(Student.getInstance()));
        editor.apply();
        Log.e(TAG,"onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"onPause");
    }

    public void show(View v){
        TextView textView = (TextView) findViewById(R.id.textt);
        Collection collection = Collection.getInstance();
        for(int i=0;i<collection.getSize();i++){
            textView.append(collection.get(i));
            textView.append("\n");
        }
    }

}
