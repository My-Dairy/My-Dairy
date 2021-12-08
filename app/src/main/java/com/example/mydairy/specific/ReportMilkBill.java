package com.example.mydairy.specific;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import com.example.mydairy.Details.DailyEntry;
import com.example.mydairy.MainActivity;
import com.example.mydairy.R;
import com.example.mydairy.adapter.ReportAdapter;
import com.example.mydairy.adapter.ReportMilkBillAdapter;
import com.example.mydairy.entry.DailyEntryActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ReportMilkBill extends AppCompatActivity {

    ListView listView;
    Calendar myCalendar, myCalendar1;
    EditText startdate, enddate;
    private FirebaseDatabase database;
    private DatabaseReference mPostReference;
    ArrayList<DailyEntry> dailyEntryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_milk_bill);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.primary_color, this.getTheme()));
        }

        // Loading all the data.
        listView = (ListView) findViewById(R.id.milk_bill_recycler);
        dailyEntryList = new ArrayList<>();
        loadalldata();

        //Setting the datepicker.
        myCalendar = Calendar.getInstance();
        startdate = (EditText) findViewById(R.id.start_dte_edit);

        DatePickerDialog.OnDateSetListener date  = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateStartLabel();
            }
        };

        startdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog datePickerDialog = new DatePickerDialog(ReportMilkBill.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        myCalendar1 = Calendar.getInstance();
        enddate = (EditText) findViewById(R.id.end_dte_edit);
        DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar1.set(Calendar.YEAR, year);
                myCalendar1.set(Calendar.MONTH, month);
                myCalendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateEndLabel();
            }
        };

        enddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                DatePickerDialog datePickerDialog = new DatePickerDialog(ReportMilkBill.this, date1, myCalendar1
                        .get(Calendar.YEAR), myCalendar1.get(Calendar.MONTH),
                        myCalendar1.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });


        Button Search = (Button) findViewById(R.id.search_btn);

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    loadspecificdata();
                } catch (ParseException e) {
                    e.printStackTrace();
                    System.out.println("SPPatel");
                }
            }
        });

        Button Reset = (Button) findViewById(R.id.reset_btn);

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadalldata();
            }
        });
    }

    private void updateStartLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        startdate.setText(sdf.format(myCalendar.getTime()));
    }

    private void updateEndLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        enddate.setText(sdf.format(myCalendar1.getTime()));
    }

    public void loadspecificdata() throws ParseException {
        dailyEntryList.clear();
        listView.setAdapter(null);

        EditText Min = (EditText) findViewById(R.id.start_dte_edit);
        String mindate = Min.getText().toString();
        Date datemin = new SimpleDateFormat("MM/dd/yy", Locale.US).parse(mindate);
        System.out.println("SPPatel: "+mindate);

        EditText Max = (EditText) findViewById(R.id.end_dte_edit);
        String maxdate = Max.getText().toString();
        Date datemax = new SimpleDateFormat("MM/dd/yy", Locale.US).parse(maxdate);
        System.out.println("SPPatel: "+maxdate);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String keyid = user.getUid();

        mPostReference = FirebaseDatabase.getInstance().getReference().child("users").child(keyid).child("DailyEntry");

        mPostReference.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    String date = snapshot.getKey();
                    date = date.replace("-","/");

                    try {
                        Date date1 = new SimpleDateFormat("MM/dd/yy", Locale.US).parse(date);
                        System.out.println("ooooo"+date);
                        System.out.println("ooooo"+date1);
                        if((date1.compareTo(datemin)>=0)&&(date1.compareTo(datemax)<=0))
                        {
                            for(DataSnapshot snapshot1:snapshot.getChildren())
                            {
                                String time = snapshot1.getKey();

                                if(time.equals("morning"))
                                {
                                    time = "Morning";
                                }
                                else
                                    time = "Evening";

                                String Fat = snapshot1.child("fat").getValue().toString();
                                String Qty = snapshot1.child("quantity").getValue().toString();
                                String Amt = snapshot1.child("amount").getValue().toString();

                                DailyEntry dailyEntry = new DailyEntry(Fat,Qty,Amt,date,time);

                                dailyEntryList.add(dailyEntry);
                            }
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                        System.out.println("SPPatel");
                    }
                }

                ReportMilkBillAdapter adapter = new ReportMilkBillAdapter(ReportMilkBill.this, dailyEntryList);

                listView.setAdapter(adapter);
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("SPPatel");
            }
        });
    }

    public void loadalldata()
    {
        dailyEntryList.clear();
        listView.setAdapter(null);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String keyid = user.getUid();

        mPostReference = FirebaseDatabase.getInstance().getReference().child("users").child(keyid).child("DailyEntry");

        mPostReference.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    String date = snapshot.getKey();

                    for(DataSnapshot snapshot1:snapshot.getChildren())
                    {
                        String time = snapshot1.getKey();

                        if(time.equals("morning"))
                        {
                            time = "Morning";
                        }
                        else
                            time = "Evening";

                        String Fat = snapshot1.child("fat").getValue().toString();
                        String Qty = snapshot1.child("quantity").getValue().toString();
                        String Amt = snapshot1.child("amount").getValue().toString();

                        DailyEntry dailyEntry = new DailyEntry(Fat,Qty,Amt,date,time);

                        dailyEntryList.add(dailyEntry);
                    }
                }

                ReportMilkBillAdapter adapter = new ReportMilkBillAdapter(ReportMilkBill.this, dailyEntryList);

                listView.setAdapter(adapter);
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("SPPatel");
            }
        });
    }
}