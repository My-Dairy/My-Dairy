package com.example.mydairy.entry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mydairy.Details.LabourCharge;
import com.example.mydairy.Details.ROI;
import com.example.mydairy.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ROIEntryActivity extends AppCompatActivity {
    EditText Monthly,Withdraw,Balance;
    FloatingActionButton Save;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private ROI roi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_r_o_i_entry);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.primary_color, this.getTheme()));
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ROI Entry");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Monthly= (EditText)findViewById(R.id.monthly_editText);
        Withdraw =  (EditText)findViewById(R.id.withdraw_editText);
        Balance = (EditText)findViewById(R.id.balance_editText);

        Save = (FloatingActionButton) findViewById(R.id.save_btn);

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
        String monthly  = Monthly.getText().toString();
        String withdraw = Withdraw.getText().toString();
        String balance = Balance.getText().toString();

        database = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String keyid = user.getUid();

        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss aa");
        String datetime = dateformat.format(c.getTime());

        roi = new ROI(monthly,withdraw,balance);
        databaseReference.child("users").child(keyid).child("ROI").child(datetime).setValue(roi);

        finish();
        Toast.makeText(getApplicationContext(),"Successfully Saved",Toast.LENGTH_SHORT).show();

    }
}