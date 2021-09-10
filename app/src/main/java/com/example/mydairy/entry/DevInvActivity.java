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
import com.example.mydairy.Details.devInv;
import com.example.mydairy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DevInvActivity extends AppCompatActivity {
    EditText cowPurchase,cowSelling,expansionDetails,expansionAmount;

    Button Save;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private devInv devinv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev_inv);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("DevInv Entry");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        cowPurchase = (EditText)findViewById(R.id.cow_purchase_editText);
        cowSelling = (EditText)findViewById(R.id.cow_selling_editText);
        expansionDetails=(EditText)findViewById(R.id.expansion_details_editText);
        expansionAmount=(EditText)findViewById(R.id.expansion_amount_editText);

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
        String CowPurchase = cowPurchase.getText().toString();
        String CowSelling= cowSelling.getText().toString();
        String ExpansionDetails= expansionDetails.getText().toString();
        String ExpansionAmount = expansionAmount.getText().toString();
        database = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String keyid = user.getUid();

        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss aa");
        String datetime = dateformat.format(c.getTime());
        devinv = new devInv(CowPurchase,CowSelling,ExpansionDetails,ExpansionAmount);

        databaseReference.child("users").child(keyid).child("DevInv").child(datetime).setValue(devinv);

        finish();
        Toast.makeText(getApplicationContext(),"Successfully Saved",Toast.LENGTH_SHORT).show();
    }
}