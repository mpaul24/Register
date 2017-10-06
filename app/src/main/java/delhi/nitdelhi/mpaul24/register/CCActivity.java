package delhi.nitdelhi.mpaul24.register;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class CCActivity extends AppCompatActivity {
    Button ckin,ckout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cc);
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

        ckin = (Button) findViewById(R.id.checkincc);
        ckout = (Button) findViewById(R.id.checkoutcc);

        ckin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ComputerCenter cc = ComputerCenter.getInstance();
                        String status="";
                        if(cc.checkedIn()) status="Check In Successfull";
                        else status="Check IN Unsuccessfull";

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
                        if(ComputerCenter.getInstance().checkedOut()){
                            status = "Checke out Successfull";
                        }else{
                            status = "Not Checked in CC";
                        }
                        Snackbar.make(v, status, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
        );
    }

}
