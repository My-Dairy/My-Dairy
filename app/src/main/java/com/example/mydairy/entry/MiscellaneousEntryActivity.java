package com.example.mydairy.entry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mydairy.Details.Misc;
import com.example.mydairy.Details.ROI;
import com.example.mydairy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MiscellaneousEntryActivity extends AppCompatActivity {
    EditText gas_bill_edit,electricity_bill_edit,maintenance_details,maintenance_edit,others_details,others_edit;
    Button Save;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private Misc misc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miscellaneous_entry);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Miscellaneous Entry");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gas_bill_edit= (EditText)findViewById(R.id.gas_bill_edit);
        electricity_bill_edit =  (EditText)findViewById(R.id.electricity_bill_edit);
        maintenance_details = (EditText)findViewById(R.id.maintenance_details);
        maintenance_edit= (EditText)findViewById(R.id.maintenance_edit);
        others_details =  (EditText)findViewById(R.id.others_details);
        others_edit = (EditText)findViewById(R.id.others_edit);

        Save = (Button) findViewById(R.id.save_btn);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save_response();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home)
        {
            finish();
        }
        else
        {
            save_response();
        }
        return super.onOptionsItemSelected(item);
    }

    private void save_response()
    {
        String gas_bill  = gas_bill_edit.getText().toString();
        String electricity_bill = electricity_bill_edit.getText().toString();
        String Mdetails = maintenance_details.getText().toString();
        String Medit  = maintenance_edit.getText().toString();
        String Odetails = others_details.getText().toString();
        String Oedit = others_edit.getText().toString();


        database = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String keyid = user.getUid();

        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss aa");
        String datetime = dateformat.format(c.getTime());

        misc=new Misc(gas_bill,electricity_bill,Mdetails,Medit,Odetails,Oedit);
        databaseReference.child("users").child(keyid).child("Misc").child(datetime).setValue(misc);
        finish();
        Toast.makeText(getApplicationContext(),"Successfully Saved",Toast.LENGTH_SHORT).show();
    }

}