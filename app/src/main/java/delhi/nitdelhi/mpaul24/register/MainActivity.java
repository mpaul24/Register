package delhi.nitdelhi.mpaul24.register;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    Button register, hosButton, libButton, ccButton, updateButton;
    public static final String SHAREDPREF = "myPreference";
    public static final String HOSTEL = "hostel";
    public static final String CC = "cc";
    public static final String LIBRARY = "library";
    public static final String STUDENT = "student";
    public static final String TAG = "tag";
    public static final String COLLECTION = "collection";
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = getSharedPreferences(SHAREDPREF, MODE_PRIVATE);
        if (pref.contains(HOSTEL)) {
            String h = pref.getString(HOSTEL, "");
            String l = pref.getString(LIBRARY, "");
            String c = pref.getString(CC, "");
            String s = pref.getString(STUDENT, "");
            String col = pref.getString(COLLECTION, "");
            Gson gson = new Gson();
            Hostel.RestoreObject(gson.fromJson(h, Hostel.class));
            Library.RestoreObject(gson.fromJson(l, Library.class));
            ComputerCenter.RestoreObject(gson.fromJson(c, ComputerCenter.class));
            Student.RestoreObject(gson.fromJson(s, Student.class));
            Collection.RestoreObject(gson.fromJson(col, Collection.class));
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("REGISTER");
        setSupportActionBar(toolbar);


        register = (Button) findViewById(R.id.register);
        hosButton = (Button) findViewById(R.id.hosButton);
        libButton = (Button) findViewById(R.id.libButton);
        ccButton = (Button) findViewById(R.id.ccButton);
        updateButton = (Button) findViewById(R.id.update);
        if(Student.getInstance()==null){
            register.setVisibility(View.VISIBLE);
            hosButton.setVisibility(View.GONE);
            libButton.setVisibility(View.GONE);
            ccButton.setVisibility(View.GONE);
            updateButton.setVisibility(View.GONE);
        }else if(Student.getInstance().getHostel().equals("DAY SCHOLAR")){
            register.setVisibility(View.GONE);
            hosButton.setVisibility(View.GONE);
            libButton.setVisibility(View.VISIBLE);
            ccButton.setVisibility(View.VISIBLE);
            updateButton.setVisibility(View.VISIBLE);
        }else{
            register.setVisibility(View.GONE);
            hosButton.setVisibility(View.VISIBLE);
            libButton.setVisibility(View.VISIBLE);
            ccButton.setVisibility(View.VISIBLE);
            updateButton.setVisibility(View.VISIBLE);
        }
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });

        hosButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, HostelActivity.class));
                    }
                }
        );

        libButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, LibraryActivity.class));
                    }
                }
        );

        ccButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, CCActivity.class));
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
            startActivity(new Intent(MainActivity.this, Main2Activity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroyed");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Student.getInstance()==null){
            register.setVisibility(View.VISIBLE);
            hosButton.setVisibility(View.GONE);
            libButton.setVisibility(View.GONE);
            ccButton.setVisibility(View.GONE);
            updateButton.setVisibility(View.GONE);
        }else if(Student.getInstance().getHostel().equals("DAY SCHOLAR")){
            register.setVisibility(View.GONE);
            hosButton.setVisibility(View.GONE);
            libButton.setVisibility(View.VISIBLE);
            ccButton.setVisibility(View.VISIBLE);
            updateButton.setVisibility(View.VISIBLE);
        }else{
            register.setVisibility(View.GONE);
            hosButton.setVisibility(View.VISIBLE);
            libButton.setVisibility(View.VISIBLE);
            ccButton.setVisibility(View.VISIBLE);
            updateButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        pref = getSharedPreferences(SHAREDPREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();
        editor.putString(HOSTEL, gson.toJson(Hostel.getInstance()));
        editor.putString(LIBRARY, gson.toJson(Library.getInstance()));
        editor.putString(CC, gson.toJson(ComputerCenter.getInstance()));
        editor.putString(STUDENT, gson.toJson(Student.getInstance()));
        editor.putString(COLLECTION, gson.toJson(Collection.getInstance()));
        Log.e(TAG, gson.toJson(Student.getInstance()));
        editor.apply();
        Log.e(TAG, "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
    }

    public void show(View v) {
        Collection collection = Collection.getInstance();
        Gson gson = new Gson();
        String var = gson.toJson(collection);
        Log.e(TAG, var);
        if(collection.getSize()>0)
            if(isOnline(this))
                new Background(this).execute(var);
            else
                Toast.makeText(this, "No Network!!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No-Record to Update!!", Toast.LENGTH_SHORT).show();

    }

    public boolean isOnline(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        //should check null because in airplane mode it will be null
        return (netInfo != null && netInfo.isConnected());
    }

    private class Background extends AsyncTask<String, Void, String> {
        String registration_link = "http://10.10.49.11/check.php";
        // String registration_link = "http://manojit.tk/images/login_check.php";
        Context context;
        AlertDialog.Builder builder;
        Activity activity;
        ProgressBar progressBar;
        //ProgressDialog progressDialog;

        public Background(Context context) {
            this.context = context;
            activity = (Activity) context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
//            progressDialog = new ProgressDialog(activity);
//            progressDialog.setTitle("Working!!");
//            progressDialog.setIndeterminate(true);
//            progressDialog.setCancelable(false);
//            progressDialog.show();
            progressBar = new ProgressBar(activity, null, android.R.attr.progressBarStyleSmall);
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                URL url = new URL(registration_link);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("json", "UTF-8") + "=" + URLEncoder.encode(params[0], "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }
                connection.disconnect();
                Thread.sleep(2000);
                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
//                progressDialog.dismiss();
                Log.e(TAG,"onPostExecute"+"   "+s);
                progressBar.setVisibility(View.GONE);
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("server_response");
                JSONObject JO = jsonArray.getJSONObject(0);
                String code = JO.getString("code");
                String message = JO.getString("message");
                if (code.equals("true")) {
                    Collection.getInstance().clear();
                    builder = new AlertDialog.Builder(context);
                    builder.setTitle("Result");
                    builder.setMessage("Successful!!\n"+message+" Records Added!!");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();


                        }
                    });
                    builder.setCancelable(false);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {
                    builder = new AlertDialog.Builder(context);
                    builder.setTitle("Result");
                    builder.setMessage("Unsuccessful!!");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();


                        }
                    });
                    builder.setCancelable(false);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }
}
