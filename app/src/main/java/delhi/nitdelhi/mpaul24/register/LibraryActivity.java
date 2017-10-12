package delhi.nitdelhi.mpaul24.register;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class LibraryActivity extends AppCompatActivity {
    Button ckin,ckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("REGISTER");
        setSupportActionBar(toolbar);

        ckin = (Button) findViewById(R.id.checkinlib);
        ckout = (Button) findViewById(R.id.checkoutlib);

        ckin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Library library = Library.getInstance();
                        String status="";
                        int s = library.checkedIn();
                        if(s==1){
                            status="Check In Successful";
                        }
                        else if(s==0){
                            status="Unsuccessful";
                            AlertDialog.Builder builder = new AlertDialog.Builder(LibraryActivity.this);
                            builder.setTitle("Notice");
                            builder.setMessage("Check out from Computer Center!!");
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
                        else if(s==-1){
                            status="Unsuccessful";
                            AlertDialog.Builder builder = new AlertDialog.Builder(LibraryActivity.this);
                            builder.setTitle("Notice");
                            builder.setMessage("Check out from Hostel!!");
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();


                                }
                            });
                            builder.setCancelable(false);
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }else{
                            status="Unsuccessful";
                            AlertDialog.Builder builder = new AlertDialog.Builder(LibraryActivity.this);
                            builder.setTitle("Notice");
                            builder.setMessage("Already in Library!!");
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

                        Snackbar.make(v, status, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
        );

        ckout.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String status="";
                        if(Library.getInstance().checkedOut()){
                            status = "Check out Successful";
                        }else{
                            status = "No Previous Record!!";
                        }
                        Snackbar.make(v, status, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
        );

    }

}
