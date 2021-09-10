package com.example.mydairy.entry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mydairy.Details.DavaDaru;
import com.example.mydairy.Details.GassCharo;
import com.example.mydairy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GassCharoEntryActivity extends AppCompatActivity {

    EditText quantity,SukhocharoAmount ,tractorCharge,seeds,fertilizers,labourCharge,lilocharoSeeds,lilocharoFertilizers;
    CheckBox cb1,cb2,cb3,cb4,cb5;
    Button Save;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private GassCharo gassCharo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gass_charo_entry);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("GassCharo Entry");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cb1 = (CheckBox)findViewById(R.id.cb_1);
        cb2 = (CheckBox)findViewById(R.id.cb_2);
        cb3 = (CheckBox)findViewById(R.id.cb_3);
        cb4 = (CheckBox)findViewById(R.id.cb_4);
        cb5 = (CheckBox)findViewById(R.id.cb_5);

        quantity = (EditText)findViewById(R.id.quantity_editText);
        SukhocharoAmount= (EditText)findViewById(R.id.sukocharoAmount_editText);
        tractorCharge=(EditText)findViewById(R.id.tractorCharge_editText);
        seeds= (EditText)findViewById(R.id.seeds_editText);
        fertilizers=(EditText)findViewById(R.id.fertilizers_editText);
        labourCharge=(EditText)findViewById(R.id.labourCharge_editText);
        lilocharoSeeds=(EditText)findViewById(R.id.lilocharoSeeds_editText);
        lilocharoFertilizers=(EditText)findViewById(R.id.lilocharoFertilizers_editText);

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

    private void save_response() {
        //  EditText quantity,SukhocharoAmount ,tractorCharge,seeds,fertilizers,labourCharge,lilocharoSeeds,lilocharoFertilizers;
        String Quantity  = quantity.getText().toString();
        String sukhocharoAmount = SukhocharoAmount.getText().toString();
        String TractorCharge = tractorCharge.getText().toString();
        String Seeds = seeds.getText().toString();
        String Fertilizers = fertilizers.getText().toString();
        String LabourCharge = labourCharge.getText().toString();
        String LilocharoSeeds = lilocharoSeeds.getText().toString();
        String LilocharoFertilizers = lilocharoFertilizers.getText().toString();
        database = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String keyid = user.getUid();
        String CheckboxString="";
        if(cb1.isChecked())
            CheckboxString=CheckboxString+"1";
        else CheckboxString = CheckboxString + "0";
        if(cb2.isChecked())
            CheckboxString=CheckboxString+"1";
        else CheckboxString = CheckboxString + "0";
        if(cb3.isChecked())
            CheckboxString=CheckboxString+"1";
        else CheckboxString = CheckboxString + "0";
        if(cb4.isChecked())
            CheckboxString=CheckboxString+"1";
        else CheckboxString = CheckboxString + "0";
        if(cb5.isChecked())
            CheckboxString=CheckboxString+"1";
        else CheckboxString = CheckboxString + "0";

        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss aa");
        String datetime = dateformat.format(c.getTime());
        gassCharo = new GassCharo(CheckboxString,Quantity,sukhocharoAmount,TractorCharge,Seeds,Fertilizers,LabourCharge,LilocharoSeeds,LilocharoFertilizers);

        databaseReference.child("users").child(keyid).child("GassCharo").child(datetime).setValue(gassCharo);

        finish();
        Toast.makeText(getApplicationContext(),"Successfully Saved",Toast.LENGTH_SHORT).show();
    }
}