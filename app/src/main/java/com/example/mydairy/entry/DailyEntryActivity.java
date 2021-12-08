
package com.example.mydairy.entry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.mydairy.Details.DailyEntry;
import com.example.mydairy.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DailyEntryActivity extends AppCompatActivity {

    Calendar myCalendar;
    EditText DateText, Fat, Quantity, Amount;
    RadioGroup Time;
    FloatingActionButton Save;
    private DailyEntry entry;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    String time = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_entry);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.primary_color, this.getTheme()));
        }


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Daily Entry");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myCalendar = Calendar.getInstance();
        DateText=findViewById(R.id.date);
        Fat = (EditText) findViewById(R.id.fat);
        Quantity = (EditText) findViewById(R.id.quantity);
        Amount = (EditText) findViewById(R.id.amount);
        Save = (FloatingActionButton) findViewById(R.id.save_btn);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save_response();
            }
        });

        DatePickerDialog.OnDateSetListener date  = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };


        DateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog datePickerDialog = new DatePickerDialog(DailyEntryActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        DateText.setText(sdf.format(myCalendar.getTime()));
    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();


        switch(view.getId()) {
            case R.id.day:
                if (checked)
                    time = "morning";
                    break;
            case R.id.night:
                if (checked)
                    time = "evening";
                    break;
        }
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

        String date = DateText.getText().toString();

        date = date.replace("/","-");
        String fat = Fat.getText().toString();
        String quantity = Quantity.getText().toString();
        String amount = Amount.getText().toString();


        if(date.trim().equals("")||fat.trim().equals("")||quantity.trim().equals("")||amount.trim().equals("")||time.equals(""))
        {
            Toast.makeText(getApplicationContext(),"Please enter all the details properly.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            entry = new DailyEntry(fat, quantity, amount);

            database = FirebaseDatabase.getInstance();
            databaseReference = FirebaseDatabase.getInstance().getReference();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String keyid = user.getUid();

            databaseReference.child("users").child(keyid).child("DailyEntry").child(date).child(time).setValue(entry);

            finish();
            Toast.makeText(getApplicationContext(),"Successfully Saved",Toast.LENGTH_SHORT).show();
        }
    }
}