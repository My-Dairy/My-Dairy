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

import com.example.mydairy.Details.DanKhanEntry;
import com.example.mydairy.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DanKhanEntryActivity extends AppCompatActivity {

    EditText PashudanD, PashudanE, MakaiO, MakaiE, PapdiE, PashuaharD, PashuaharA;
    FloatingActionButton Save;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private DanKhanEntry entry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dan_khan_entry);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.primary_color, this.getTheme()));
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("DanKhan Entry");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        PashudanD = (EditText) findViewById(R.id.pashudan_dairy_edit);
        PashudanE = (EditText) findViewById(R.id.pashudan_extrnl_edit);
        MakaiO = (EditText) findViewById(R.id.makai_own_edit);
        MakaiE = (EditText) findViewById(R.id.makai_ext_edit);
        PapdiE = (EditText) findViewById(R.id.papdi_extrnl_edit);
        PashuaharD = (EditText) findViewById(R.id.pashuahar_othr_spcfy);
        PashuaharA = (EditText) findViewById(R.id.pashuahar_othr_edit);

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
        String pashudanD = PashudanD.getText().toString();
        String pashudanE = PashudanE.getText().toString();
        String makaiO = MakaiO.getText().toString();
        String makaiE = MakaiE.getText().toString();
        String papdiE = PapdiE.getText().toString();
        String pashuaharD = PashuaharD.getText().toString();
        String pashuaharA = PashuaharA.getText().toString();

        database = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String keyid = user.getUid();

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        String strDate = formatter.format(date);
        strDate = strDate.replace("/","-");

        entry = new DanKhanEntry(pashudanD, pashudanE, makaiO,makaiE,papdiE,pashuaharD,pashuaharA);

        databaseReference.child("users").child(keyid).child("DanKhan").child(strDate).setValue(entry);

        finish();
        Toast.makeText(getApplicationContext(),"Successfully Saved",Toast.LENGTH_SHORT).show();
    }
}