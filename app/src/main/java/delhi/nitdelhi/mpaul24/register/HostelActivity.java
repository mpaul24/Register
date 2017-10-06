package delhi.nitdelhi.mpaul24.register;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ckin = (Button) findViewById(R.id.checkinhos);
        ckout = (Button) findViewById(R.id.checkouthos);

        ckin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String status="";
                        if(Hostel.getInstance().checkedIn()) status="Check In Successfull";
                        else status="Check In Unsuccessful";

                        Snackbar.make(v, status, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
        );

        ckout.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean s = Hostel.getInstance().checkedOut(new Record(Student.getInstance(),new Date()));
                        if(s){
                            Snackbar.make(v, "Checkout Hostel Successfull", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }else{
                            Snackbar.make(v, "Checkout Hostel Unsuccessfull", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }

                    }
                }
        );
    }

}
