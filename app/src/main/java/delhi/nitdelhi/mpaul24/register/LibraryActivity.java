package delhi.nitdelhi.mpaul24.register;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        ckin = (Button) findViewById(R.id.checkinlib);
        ckout = (Button) findViewById(R.id.checkoutlib);

        ckin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Library library = Library.getInstance();
                        String status="";
                        if(library.checkedIn()) status="Check In Successfull";
                        else status="Check In Unsuccessfull";

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
                            status = "Checke out Successfull";
                        }else{
                            status = "Not Checked in Library";
                        }
                        Snackbar.make(v, status, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
        );

    }

}
