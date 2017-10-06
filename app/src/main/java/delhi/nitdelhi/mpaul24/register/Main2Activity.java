package delhi.nitdelhi.mpaul24.register;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    EditText rollNo,name,phno,email,roomno;
    Spinner sp;
    String hostel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                Toast.makeText(Main2Activity.this, rollNo.getText().toString(), Toast.LENGTH_SHORT).show();
                Student.makeEntry(rollNo.getText().toString(),phno.getText().toString(),name.getText().toString(),
                        email.getText().toString(),hostel,roomno.getText().toString());
            }
        });
        rollNo = (EditText) findViewById(R.id.rollno);
        name = (EditText) findViewById(R.id.name);
        phno = (EditText) findViewById(R.id.phno);
        email = (EditText) findViewById(R.id.email);
        roomno = (EditText) findViewById(R.id.roomno);
        sp = (Spinner) findViewById(R.id.hostel);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.hostels_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        hostel = (String) parent.getItemAtPosition(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        hostel = (String) parent.getItemAtPosition(0);
                    }
                }
        );

    }

}
