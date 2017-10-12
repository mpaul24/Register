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

import java.util.Date;

public class HostelActivity extends AppCompatActivity {
    Button ckin,ckout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("REGISTER");
        setSupportActionBar(toolbar);


        ckin = (Button) findViewById(R.id.checkinhos);
        ckout = (Button) findViewById(R.id.checkouthos);

        ckin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String status="";
                        int s = Hostel.getInstance().checkedIn();
                        if(s==1){
                            status="Check In Successful";
                        }
                        else if(s==0){
                            status="Check In Unsuccessful";
                            createDialog("Already in Hostel!!");
                        }else if(s==-1){
                            status="Check In Unsuccessful";
                            createDialog("Check out from Library!!");
                        }else if(s==-2){
                            status="Check In Unsuccessful";
                            createDialog("Check out from Computer Center!!");
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
                        boolean s = Hostel.getInstance().checkedOut(new Record(Student.getInstance(),RecordType.Hostel));
                        if(s){
                            Snackbar.make(v, "Checkout Successful", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }else{
                            Snackbar.make(v, "Checkout Hostel Unsuccessful", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }

                    }
                }
        );
    }

    void createDialog(String s){
        AlertDialog.Builder builder = new AlertDialog.Builder(HostelActivity.this);
        builder.setTitle("Notice");
        builder.setMessage(s);
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

}
