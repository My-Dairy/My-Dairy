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

import com.example.mydairy.Details.DanKhanEntry;
import com.example.mydairy.Details.DavaDaru;
import com.example.mydairy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DavaDaruEntryActivity extends AppCompatActivity {

    EditText medicineExternal, medicalVisitDairy, medicalVisitExternal, doseDairy,doseExternal;
    Button Save;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private DavaDaru davaDaru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dava_daru_entry);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("DavaDaru Entry");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        medicineExternal = (EditText)findViewById(R.id.medicine_external);
        medicalVisitDairy =  (EditText)findViewById(R.id.medical_visit_dairy);
        medicalVisitExternal = (EditText)findViewById(R.id.medical_visit_external);
        doseDairy = (EditText)findViewById(R.id.dose_from_dairy);
        doseExternal = (EditText)findViewById(R.id.dose_external);

        Save = (Button) findViewById(R.id.save_btn);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save_response();
            }
        });

    }

    private void save_response() {
        String medicalVisitD  = medicalVisitDairy.getText().toString();
        String medicineE = medicineExternal.getText().toString();
        String medicalVisitE = medicalVisitExternal.getText().toString();
        String doseD = doseDairy.getText().toString();
        String doseE = doseExternal.getText().toString();
        database = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String keyid = user.getUid();

        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss aa");
        String datetime = dateformat.format(c.getTime());
        davaDaru = new DavaDaru(medicineE,medicalVisitD,medicalVisitE,doseD,doseE);

        databaseReference.child("users").child(keyid).child("DavaDaru").child(datetime).setValue(davaDaru);

        finish();
        Toast.makeText(getApplicationContext(),"Successfully Saved",Toast.LENGTH_SHORT).show();
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


}