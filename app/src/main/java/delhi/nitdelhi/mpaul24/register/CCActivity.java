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

public class CCActivity extends AppCompatActivity {
    Button ckin,ckout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cc);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("REGISTER");
        setSupportActionBar(toolbar);

        ckin = (Button) findViewById(R.id.checkincc);
        ckout = (Button) findViewById(R.id.checkoutcc);

        ckin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ComputerCenter cc = ComputerCenter.getInstance();
                        String status="";
                        int s = cc.checkedIn();
                        if(s==1) status="Check In Successful";
                        else if(s==0){
                            status="Unsuccessful";
                            createDialog("Checkout from Hostel!!");
                        }else if(s==-1){
                            status="Unsuccessful";
                            createDialog("Checkout from Library!!");
                        }else{
                            status="Unsuccessful";
                            createDialog("Already in CC!!");
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
                        if(ComputerCenter.getInstance().checkedOut()){
                            status = "Check out Successful";
                        }else{
                            status = "Not Checked in CC";
                        }
                        Snackbar.make(v, status, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
        );
    }

    void createDialog(String s){
        AlertDialog.Builder builder = new AlertDialog.Builder(CCActivity.this);
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
